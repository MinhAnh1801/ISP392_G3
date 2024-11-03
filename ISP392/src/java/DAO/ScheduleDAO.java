package DAO;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Classrooms;
import Model.Schedule;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Classes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ClassDAO;

public class ScheduleDAO {

    // SQL queries to get all classes and classrooms
    private static final String GET_ALL_CLASSES = "SELECT class_id, class_name, capacity FROM Class";
    private static final String GET_ALL_CLASSROOMS = "SELECT * FROM Classrooms";
    // SQL query to insert a new schedule
    private static final String INSERT_SCHEDULE
            = "INSERT INTO Schedule (day_of_week, start_time, end_time, class_id, classroom_id, subject_id,due_date,status,available_slot) "
            + "VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
    // SQL query to check for duplicate schedules
    private static final String CHECK_DUPLICATE_SCHEDULE
            = "SELECT COUNT(*) FROM Schedule "
            + "WHERE class_id = ? AND day_of_week = ? AND start_time = ? AND end_time = ? AND subject_id = ?";
    private static final String GET_ALL_SCHEDULES
            = "SELECT s.id, s.day_of_week, s.start_time, s.end_time, s.status, s.due_date,s.available_slot,"
            + "c.class_name, cr.name AS classroom_name, subj.code "
            + "FROM Schedule s "
            + "JOIN Class c ON s.class_id = c.class_id "
            + "JOIN Classrooms cr ON s.classroom_id = cr.id "
            + "JOIN Subjects subj ON s.subject_id = subj.id";
    // SQL query to update status to false if the current date exceeds the due_date
    private static final String UPDATE_EXPIRED_SCHEDULES
            = "UPDATE Schedule SET status = 0 WHERE due_date < GETDATE() AND status = 1";
    private static final String UPDATE_FULL_SCHEDULES
            = "UPDATE Schedule SET status = 0 WHERE available_slot = 0 AND status = 1";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String GET_CLASSROOM = "Select capacity from Classrooms where id = ?";
    private static final String GET_CLASS = "Select capacity from Class where id = ?";

    // Method to get all classes
    public List<Classes> getAllClasses() {
        List<Classes> classList = new ArrayList<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL_CLASSES); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Classes classInfo = new Classes(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                classList.add(classInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    // Method to get all classrooms
    public List<Classrooms> getAllClassrooms() {
        List<Classrooms> classroomList = new ArrayList<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL_CLASSROOMS); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Classrooms classroom = new Classrooms(
                        rs.getInt(1),
                        rs.getInt(3),
                        rs.getString(2),
                        rs.getString(4)
                );
                classroomList.add(classroom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classroomList;
    }

    // Method to check for duplicate schedules
    public boolean isDuplicateSchedule(int classId, String dayOfWeek, String startTime, String endTime, int subjectid) {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_DUPLICATE_SCHEDULE)) {

            ps.setInt(1, classId);
            ps.setString(2, dayOfWeek);
            ps.setString(3, startTime);
            ps.setString(4, endTime);
            ps.setInt(5, subjectid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Return true if a duplicate exists
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to validate if due date is in the future
    private boolean isDueDateValid(String dueDateStr) throws ParseException {
        Timestamp dueDate = new Timestamp(DATE_FORMAT.parse(dueDateStr).getTime());
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        return dueDate.after(currentDate);  // Check if the due date is after the current date
    }

    public int getCapacity(int id) {
        int available_slot = 1;
        try (Connection conn = new DBContext().getConnection(); PreparedStatement p = conn.prepareStatement(GET_CLASSROOM);) {
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                available_slot = rs.getInt("capacity");
            }
        } catch (Exception e) {
            return 0;
        }
        return available_slot;
    }
    public int getCLassCapacity(int id) {
        int available_slot = 1;
        try (Connection conn = new DBContext().getConnection(); PreparedStatement p = conn.prepareStatement(GET_CLASS);) {
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                available_slot = rs.getInt("capacity");
            }
        } catch (Exception e) {
            return 0;
        }
        return available_slot;
    }

    // Method to insert a new schedule
    public boolean createSchedule(String dayOfWeek, String startTime, String endTime, int classId, int classroomId, int subjectId, String dueDateStr, int status) {
        try {
            int available_slot = getCapacity(classroomId);
            int classcapacity = getCLassCapacity(classId);
            if(available_slot<classcapacity){
                return false;
            }
            // Check for duplicate schedule
            if (isDuplicateSchedule(classId, dayOfWeek, startTime, endTime, subjectId)) {
                System.out.println("Duplicate schedule detected.");
                return false;  // Abort if a duplicate is found
            }
            // Validate if the due date is in the future
            if (!isDueDateValid(dueDateStr)) {
                System.out.println("Error: Due date is in the past.");
                return false;
            }
            try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SCHEDULE)) {
                Timestamp dueDate = new Timestamp(DATE_FORMAT.parse(dueDateStr).getTime());
                ps.setString(1, dayOfWeek);
                ps.setString(2, startTime);
                ps.setString(3, endTime);
                ps.setInt(4, classId);
                ps.setInt(5, classroomId);
                ps.setInt(6, subjectId);
                ps.setTimestamp(7, dueDate);
                ps.setInt(8, status);
                ps.setInt(9, available_slot);
                int rowsInserted = ps.executeUpdate();
                return rowsInserted > 0;  // Return true if the insert was successful
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all schedules
    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL_SCHEDULES); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Timestamp start = rs.getTimestamp("start_time");
                Timestamp end = rs.getTimestamp("end_time");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String start_time = sdf.format(start);
                String end_time = sdf.format(end);
                // Format the due date as "YYYY-MM-DD HH:mm"
                Timestamp dueDateTimestamp = rs.getTimestamp("due_date");
                String dueDateFormatted = DATE_FORMAT.format(dueDateTimestamp);
                boolean status = rs.getBoolean("status");
                String sche_status;
                if (status) {
                    sche_status = "Opening";
                } else {
                    sche_status = "Closed";
                }
                int available_slot = rs.getInt("available_slot");
                Schedule schedule = new Schedule(
                        rs.getInt("id"),
                        rs.getString("class_name"),
                        rs.getString("classroom_name"),
                        rs.getString("code"),
                        rs.getString("day_of_week"),
                        start_time,
                        end_time,
                        dueDateFormatted,
                        sche_status,
                        available_slot
                );
                scheduleList.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    // Method to update expired schedules
    public void updateExpiredSchedules() {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_EXPIRED_SCHEDULES)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update full schedules
    public void updateFullSchedules() {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_FULL_SCHEDULES)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String GET_SCHEDULES_BY_MAJOR
            = "SELECT distinct s.id AS schedule_id,\n"
            + "       s.day_of_week,\n"
            + "       s.start_time,\n"
            + "       s.end_time,\n"
            + "       sub.name AS subject_name,\n"
            + "       sub.code AS subject_code,\n"
            + "	   cl.class_name, cl.class_id,\n"
            + "	   cr.name as classroom_name\n"
            + "FROM Schedule s\n"
            + "JOIN Class cl on cl.class_id = s.class_id\n"
            + "JOIN Classrooms cr on cr.id = s.classroom_id\n"
            + "JOIN Subjects sub ON s.subject_id = sub.id\n"
            + "JOIN Student_Profile sp ON sp.major_id = sub.major_id and sp.hoc_ki_hien_tai = sub.semester\n"
            + "LEFT JOIN Timetable t ON t.schedule_id = s.id AND t.student_id = ? \n"
            + "WHERE s.status = 1 and s.subject_id = ?\n"
            + "  and t.id is null-- Only active schedules\n"
            + "ORDER BY cl.class_id,s.day_of_week, s.start_time;";

    public Map<Integer,List<Schedule>> getSchedulesForStudentMajor(int studentId, int subject_id) {
        List<Schedule> schedules = new ArrayList<>();
        Map<Integer, List<Schedule>> classSchedulesMap = new HashMap<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_SCHEDULES_BY_MAJOR)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subject_id);
            try (ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    int classId = rs.getInt("class_id");
                    Timestamp start = rs.getTimestamp("start_time");
                    Timestamp end = rs.getTimestamp("end_time");
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String start_time = sdf.format(start);
                    String end_time = sdf.format(end);
                    Schedule schedule = new Schedule();
                    schedule.setId(rs.getInt("schedule_id"));
                    schedule.setDay_of_week(rs.getString("day_of_week"));
                    schedule.setStart_time(start_time);
                    schedule.setEnd_time(end_time);
                    schedule.setSubject_code(rs.getString("subject_code"));
                    schedule.setClass_name(rs.getString("class_name"));
                    schedule.setClassroom_name(rs.getString("classroom_name"));
                    // Group schedules by classId
                    classSchedulesMap.computeIfAbsent(classId, k -> new ArrayList<>()).add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classSchedulesMap;
    }

    private static final String CHECK_EXISTING_REGISTRATION
            = "SELECT COUNT(*) FROM Timetable t "
            + "JOIN Schedule s ON t.schedule_id = s.id "
            + "WHERE t.student_id = ? AND s.subject_id = ?";

    // Method to check if student is already registered for a subject
    public boolean isStudentRegisteredForSubject(int studentId, int subjectId) {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_EXISTING_REGISTRATION)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // True if the student is already registered
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get all schedule IDs by class ID
    public List<Integer> getScheduleIdsByClassId(int classId, int subjectId) throws SQLException {
        List<Integer> scheduleIds = new ArrayList<>();
        String query = "SELECT id FROM Schedule WHERE class_id = ? and subject_id = ?";
        
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, classId);
            ps.setInt(2, subjectId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                scheduleIds.add(rs.getInt("id"));
            }
        }
        
        return scheduleIds;
    }
    
    // New method to get tuition by subject_id
    public int getTuitionBySubjectId(int subjectId) throws SQLException {
        String query = "SELECT tuition FROM Subjects WHERE id = ?";
        int tuition = 0;

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tuition = rs.getInt("tuition");
            }
        }    
        return tuition;
    }
    
    public static void main(String[] args) {
        ScheduleDAO dao = new ScheduleDAO();
        try {
            System.out.println(dao.getScheduleIdsByClassId(4, 1));
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
