package DAO;

import Context.DBContext;
import Model.Attendance;
import Model.Subjects;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;

public class AttendanceDAO extends DBContext {

    private final Connection connection = getConnection();

    public List<Attendance> getAllListAttendanceByStudenid(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT [id], [student_id], [subject_id], [attendance_date], [status], [reason] " +
                     "FROM [dbo].[Attendance] WHERE [student_id] = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getInt("id"));
                
                UserDAO udao = new UserDAO();
                User user = udao.getUserById(rs.getInt("student_id"));                
                attendance.setStudentId(user);
                
                MajorDAO mdao = new MajorDAO();
                Subjects subject=mdao.getSubjectById(rs.getInt("subject_id"));
                attendance.setSubject(subject);
                
                
                attendance.setAttendance_date(rs.getDate("attendance_date"));
                
                attendance.setStatus(rs.getString("status"));
                attendance.setReason(rs.getString("reason"));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceList;
    }
}
