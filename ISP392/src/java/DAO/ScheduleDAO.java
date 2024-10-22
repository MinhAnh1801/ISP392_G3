package DAO;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Classrooms;
import Model.Schedule;
import models.Classes;

public class ScheduleDAO {

    // SQL queries to get all classes and classrooms
    private static final String GET_ALL_CLASSES = "SELECT class_id, class_name, capacity FROM Class";
    private static final String GET_ALL_CLASSROOMS = "SELECT * FROM Classrooms";
    // SQL query to insert a new schedule
    private static final String INSERT_SCHEDULE
            = "INSERT INTO Schedule (day_of_week, start_time, end_time, class_id, classroom_id, subject_id) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    // SQL query to check for duplicate schedules
    private static final String CHECK_DUPLICATE_SCHEDULE
            = "SELECT COUNT(*) FROM Schedule "
            + "WHERE class_id = ? AND day_of_week = ? AND start_time = ? AND end_time = ? AND subject_id = ?";
    private static final String GET_ALL_SCHEDULES
            = "SELECT s.id, s.day_of_week, s.start_time, s.end_time, "
            + "c.class_name, cr.name AS classroom_name, subj.code "
            + "FROM Schedule s "
            + "JOIN Class c ON s.class_id = c.class_id "
            + "JOIN Classrooms cr ON s.classroom_id = cr.id "
            + "JOIN Subjects subj ON s.subject_id = subj.id";

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
    public boolean isDuplicateSchedule(String classId, String dayOfWeek, String startTime, String endTime, int subjectid) {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_DUPLICATE_SCHEDULE)) {

            ps.setString(1, classId);
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

    // Method to insert a new schedule
    public boolean createSchedule(String dayOfWeek, String startTime, String endTime, String classId, int classroomId, int subjectId) {
        // Check for duplicate schedule
        if (isDuplicateSchedule(classId, dayOfWeek, startTime, endTime, subjectId)) {
            System.out.println("Duplicate schedule detected.");
            return false;  // Abort if a duplicate is found
        }
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SCHEDULE)) {

            ps.setString(1, dayOfWeek);
            ps.setString(2, startTime);
            ps.setString(3, endTime);
            ps.setString(4, classId);
            ps.setInt(5, classroomId);
            ps.setInt(6, subjectId);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;  // Return true if the insert was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all schedules
    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL_SCHEDULES); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("id"),
                        rs.getString("class_name"),
                        rs.getString("classroom_name"),
                        rs.getString("code"),
                        rs.getString("day_of_week"),
                        rs.getString("start_time"),
                        rs.getString("end_time")
                );
                scheduleList.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    public static void main(String[] args) {
        ScheduleDAO dao = new ScheduleDAO();
        System.out.println(dao.getAllSchedules());
    }
}
