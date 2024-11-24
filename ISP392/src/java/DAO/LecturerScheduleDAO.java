package DAO;

import Context.DBContext;
import Model.Lecturer_Profile;
import Model.Subjects;
import Model.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Classes;

public class LecturerScheduleDAO {

    // Method to get major_id of a lecturer from Lecturer_Profile based on lecturerId
    public int getMajorIdByLecturerId(int lecturerId) throws SQLException {
        int majorId = -1;
        String query = "SELECT major_id FROM Lecturer_Profile WHERE lecturer_id = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                majorId = rs.getInt("major_id");
            }
        }
        return majorId;
    }

    // Method to fetch subjects by major_id from Subjects table
    public List<Subjects> getSubjectsByMajor(int majorId) throws SQLException {
        List<Subjects> subjects = new ArrayList<>();
        String query = "SELECT id, code FROM Subjects WHERE major_id = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, majorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subjects.add(subject);
            }
        }
        return subjects;
    }

    // Method to fetch distinct classes (class_id) based on subject_id from Schedule table
    public List<Classes> getClassesBySubject(int subjectId) throws SQLException {
        List<Classes> classes = new ArrayList<>();
        String query = "SELECT DISTINCT s.class_id,cl.class_name FROM Schedule s\n"
                + "JOIN Class cl on cl.class_id = s.class_id\n"
                + "WHERE subject_id = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Classes cl = new Classes();
                String class_id = rs.getString("class_id");
                String class_name = rs.getString("class_name");
                cl.setClass_id(class_id);
                cl.setClass_name(class_name);
                classes.add(cl);
            }
        }
        return classes;
    }

    public Map<String, List<Schedule>> getGroupedSchedulesBySubject(int subjectId) throws SQLException {
        Map<String, List<Schedule>> groupedSchedules = new HashMap<>();
        String query = "SELECT id, s.class_id, class_name, day_of_week, start_time, end_time\n"
                + "           FROM Schedule s join Class c on s.class_id = c.class_id WHERE subject_id = ? and status = 0 ORDER BY class_name";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                String class_name = rs.getString("class_name");
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setClass_name(rs.getString("class_name"));
                schedule.setDay_of_week(rs.getString("day_of_week"));
                schedule.setStart_time(rs.getString("start_time"));
                schedule.setEnd_time(rs.getString("end_time")); 
                groupedSchedules.computeIfAbsent(class_name, k -> new ArrayList<>()).add(schedule);
            }
        }
        return groupedSchedules;
    }

    // Method to fetch schedules based on class_id from Schedule table
    public List<Schedule> getSchedulesByClassId(int classId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT id, day_of_week, start_time, end_time FROM Schedule WHERE class_id = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setDay_of_week(rs.getString("day_of_week"));
                schedule.setStart_time(rs.getString("start_time"));
                schedule.setEnd_time(rs.getString("end_time"));
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    // Method to get all lecturers from Lecturer_Profile table
    public List<Lecturer_Profile> getAllLecturers() throws SQLException {
        List<Lecturer_Profile> lecturers = new ArrayList<>();
        String query = "SELECT lecturer_id, full_name,email,department,office,major_id FROM Lecturer_Profile";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lecturer_Profile lecturer = new Lecturer_Profile();
                lecturer.setLecturerId(rs.getInt("lecturer_id"));
                lecturer.setFullName(rs.getString("full_name"));
                lecturer.setMajor_id(rs.getInt("major_id"));
                lecturer.setDepartment(rs.getString("department"));
                lecturer.setEmail(rs.getString("email"));
                lecturer.setOffice(rs.getString("office"));
                lecturers.add(lecturer);
            }
        }
        return lecturers;
    }

     // Method to check if a schedule overlaps with any existing schedule for the lecturer
    public boolean isScheduleOverlapping(int lecturerId, int scheduleId) throws SQLException {
        String query = """
            SELECT COUNT(*) AS overlapCount
            FROM Lecturer_Timetable lt
            JOIN Schedule s1 ON lt.schedule_id = s1.id
            JOIN Schedule s2 ON s2.id = ?
            WHERE lt.lecturer_id = ? AND 
                  s1.day_of_week = s2.day_of_week AND
                  (s1.start_time < s2.end_time AND s2.start_time < s1.end_time)
            """;
        
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, scheduleId);
            ps.setInt(2, lecturerId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("overlapCount") > 0;
            }
        }
    }

    // Method to assign a non-overlapping schedule to a lecturer
    public void assignScheduleToLecturer(int lecturerId, int scheduleId) throws SQLException {
        String query = "INSERT INTO Lecturer_Timetable (lecturer_id, schedule_id) VALUES (?, ?)";
        
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lecturerId);
            ps.setInt(2, scheduleId);
            ps.executeUpdate();
        }
    }
    
    public static void main(String[] args) {
        LecturerScheduleDAO dao = new LecturerScheduleDAO();
        try {
            System.out.println(dao.getGroupedSchedulesBySubject(1));
        } catch (Exception e) {
        }

    }

}
