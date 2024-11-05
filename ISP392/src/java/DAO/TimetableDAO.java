package DAO;

import Context.DBContext;
import Model.Lecturer_Timetable;
import Model.Schedule;
import Model.Timetable;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimetableDAO {

    private static final String INSERT_TIMETABLE =
        "INSERT INTO Timetable (student_id, schedule_id) VALUES (?, ?)";
    private static final String UPDATE_CAPACITY =
        "UUPDATE Schedule SET available_slot = available_slot - 1 where id = ?";

    public void addScheduleToTimetable(int studentId, int scheduleId) throws SQLException {
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_TIMETABLE)) {

            ps.setInt(1, studentId);
            ps.setInt(2, scheduleId);
            ps.executeUpdate();
        }
    }
    public void updateSchedule(int id) throws SQLException {
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CAPACITY)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    private static final String INSERT_PAYMENT =
        "INSERT INTO Payments (user_id, amount, payment_date, status, payment_type) VALUES (?, ?, ?, ?, ?)";

    public void addPayment(int userId, double amount, String status, String paymentType) throws SQLException {
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PAYMENT)) {

            ps.setInt(1, userId);
            ps.setDouble(2, amount);
            ps.setTimestamp(3, new Timestamp(new Date().getTime())); // Current date and time
            ps.setString(4, status);
            ps.setString(5, paymentType);
            ps.executeUpdate();
        }
    }
    
    public void addStu_Sub(int student_id, int subject_id) throws SQLException {
        String query = " INSERT INTO Student_Subjects(student_id, subect_id,status,enrollment_date) VALUES (?,?,?,?)";
        try (Connection conn = new DBContext().getConnection();               
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, student_id);
            ps.setDouble(2, subject_id);
            ps.setTimestamp(4, new Timestamp(new Date().getTime()));
            ps.setString(3, "Enrolled");
            ps.executeUpdate();
        }
    }
    
    public void addtoClass(int student_id, int class_id) throws SQLException {
        String query = " INSERT INTO StudentClass(student_id, class_id) VALUES (?,?)";
        try (Connection conn = new DBContext().getConnection();               
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, student_id);
            ps.setDouble(2, class_id);
            ps.executeUpdate();
        }
    }
    
    // Method to get the student's timetable
    public List<Timetable> getStudentTimetable(int studentId) throws SQLException {
        List<Timetable> timetable = new ArrayList<>();

        String query = "SELECT s.day_of_week, s.start_time, s.end_time,\n" +
"                       sub.code AS subject_code, cl.name AS classroom_name\n" +
"                       FROM Timetable ss\n" +
"                       JOIN Schedule s ON ss.schedule_id = s.id\n" +
"                       JOIN Subjects sub ON s.subject_id = sub.id\n" +
"                       JOIN Classrooms cl ON s.classroom_id = cl.id\n" +
"                       WHERE ss.student_id = ?\n" +
"                       ORDER BY s.day_of_week, s.start_time";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp start = rs.getTimestamp("start_time");
                Timestamp end = rs.getTimestamp("end_time");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String start_time = sdf.format(start);
                String end_time = sdf.format(end);
                Timetable timetableEntry = new Timetable();
                timetableEntry.setDay_of_week(rs.getString("day_of_week"));
                timetableEntry.setStart_time(rs.getString("start_time"));
                timetableEntry.setEnd_time(rs.getString("end_time"));
                timetableEntry.setSubjectName(rs.getString("subject_code"));
                timetableEntry.setClassroomName(rs.getString("classroom_name"));

                timetable.add(timetableEntry);
            }
        }

        return timetable;
    }
    public List<Lecturer_Timetable> getLecturerTimetable(int lecturer_id) throws SQLException {
        List<Lecturer_Timetable> timetable = new ArrayList<>();

        String query = "SELECT s.day_of_week, s.start_time, s.end_time,\n" +
"                       sub.code AS subject_code, cl.name AS classroom_name\n" +
"                       FROM Lecturer_Timetable ss\n" +
"                       JOIN Schedule s ON ss.schedule_id = s.id\n" +
"                       JOIN Subjects sub ON s.subject_id = sub.id\n" +
"                       JOIN Classrooms cl ON s.classroom_id = cl.id\n" +
"                       WHERE ss.lecturer_id = ?\n" +
"                       ORDER BY s.day_of_week, s.start_time";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lecturer_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp start = rs.getTimestamp("start_time");
                Timestamp end = rs.getTimestamp("end_time");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String start_time = sdf.format(start);
                String end_time = sdf.format(end);
                Lecturer_Timetable timetableEntry = new Lecturer_Timetable();
                timetableEntry.setDay_of_week(rs.getString("day_of_week"));
                timetableEntry.setStart_time(rs.getString("start_time"));
                timetableEntry.setEnd_time(rs.getString("end_time"));
                timetableEntry.setSubjectName(rs.getString("subject_code"));
                timetableEntry.setClassroomName(rs.getString("classroom_name"));

                timetable.add(timetableEntry);
            }
        }

        return timetable;
    }
    
    // Method to check if a new schedule overlaps with existing schedules
    public boolean hasOverlap(int studentId, String dayOfWeek, String startTime, String endTime) throws SQLException {
        String query = "SELECT 1 FROM Timetable t " +
                       "JOIN Schedule s ON t.schedule_id = s.id " +
                       "WHERE t.student_id = ? " +
                       "AND s.day_of_week = ? " +
                       "AND ((s.start_time BETWEEN ? AND ?) " +
                       "OR (s.end_time BETWEEN ? AND ?) " +
                       "OR (? BETWEEN s.start_time AND s.end_time))";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setString(2, dayOfWeek);
            ps.setString(3, startTime);
            ps.setString(4, endTime);
            ps.setString(5, startTime);
            ps.setString(6, endTime);
            ps.setString(7, startTime);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // If any result is returned, an overlap exists
        }
    }
    
    
    
    public static void main(String[] args) {
        TimetableDAO dao = new TimetableDAO();
        try {
            System.out.println(dao.getStudentTimetable(4));
        } catch (Exception e) {
        }
        
    }
    
}
