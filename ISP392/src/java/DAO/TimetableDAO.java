package DAO;

import Context.DBContext;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TimetableDAO {

    private static final String INSERT_TIMETABLE =
        "INSERT INTO Timetable (student_id, schedule_id) VALUES (?, ?)";

    public void addScheduleToTimetable(int studentId, int scheduleId) throws SQLException {
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_TIMETABLE)) {

            ps.setInt(1, studentId);
            ps.setInt(2, scheduleId);
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
    
    public static void main(String[] args) {
    }
    
}
