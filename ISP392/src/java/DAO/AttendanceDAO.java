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
        String sql = "SELECT [id], [student_id], [subject_id], [attendance_date], [status], [reason] "
                + "FROM [dbo].[Attendance] WHERE [student_id] = ?";

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
                Subjects subject = mdao.getSubjectById(rs.getInt("subject_id"));
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

    public List<Attendance> getAttendanceForClassAndDate(int classId, int subjectId, String attendanceDate) {
        List<Attendance> attendanceList = new ArrayList<>();

        String query = "SELECT a.[id], sp.student_id, sp.full_name, a.attendance_date, a.status, a.reason "
                + "FROM [dbo].[StudentClass] sc "
                + "JOIN [dbo].[Student_Profile] sp ON sc.student_id = sp.student_id "
                + "LEFT JOIN [dbo].[Attendance] a ON sp.student_id = a.student_id AND a.subject_id = ? "
                + "WHERE sc.class_id = ? AND a.attendance_date = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, subjectId); 
            stmt.setInt(2, classId);   
            stmt.setString(3, attendanceDate); 

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getInt("id"));
                UserDAO udao = new UserDAO();
                User usert = udao.getUserById(rs.getInt("student_id"));
                attendance.setStudentId(usert);
                attendance.setFullName(rs.getString("full_name"));
                attendance.setAttendance_date(rs.getDate("attendance_date"));
                attendance.setStatus(rs.getString("status"));
                attendance.setReason(rs.getString("reason"));

                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as appropriate
        }

        return attendanceList;
    }

  public boolean updateAttendance(int attendanceId) {
    String query = "UPDATE [dbo].[Attendance] "
                 + "SET [status] = CASE "
                 + "WHEN [status] IS NULL THEN 'Absent' "
                 + "WHEN [status] = 'Present' THEN 'Absent' "
                 + "WHEN [status] = 'Absent' THEN 'Present' "
                 + "ELSE [status] END "
                 + "WHERE [id] = ?";

    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, attendanceId);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Trả về false nếu có lỗi xảy ra
    }
}


}
