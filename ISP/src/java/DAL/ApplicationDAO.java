/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.Applications;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ApplicationDAO extends DBcontext {

    public void saveApplication(Applications application) {
        String sql = "INSERT INTO applications (student_id, application_type, content, created_at, status, last_updated, attachedFile) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, application.getStudentId());
            ps.setInt(2, application.getApplicationType());
            ps.setString(3, application.getContent());
            ps.setTimestamp(4, new java.sql.Timestamp(application.getCreatedAt().getTime()));
            ps.setString(5, application.getStatus());
            ps.setTimestamp(6, new java.sql.Timestamp(application.getLastUpdated().getTime()));
            ps.setString(7, application.getAttachedFile());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Applications> getApplicationsByStudentId(int studentId) {
        List<Applications> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE student_id = ? order by id desc";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            ApplicationTypeDAO typeDao = new ApplicationTypeDAO();
            while (rs.next()) {
                Applications application = new Applications();
                application.setStudentId(rs.getInt("student_id"));
                application.setApplicationType(rs.getInt("application_type"));
                application.setContent(rs.getString("content"));
                application.setCreatedAt(rs.getTimestamp("created_at"));
                application.setStatus(rs.getString("status"));
                application.setLastUpdated(rs.getTimestamp("last_updated"));
                application.setType(typeDao.getApplicationTypeById(rs.getInt("application_type")));
                application.setAttachedFile(rs.getString("attachedFile"));
                application.setResponse(rs.getString("response"));
                applications.add(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }

    public List<Applications> getAllApplications() {
        List<Applications> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE status = 'pending' order by id desc";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ApplicationTypeDAO typeDao = new ApplicationTypeDAO();
            while (rs.next()) {
                Applications application = new Applications();
                application.setId(rs.getInt("id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setApplicationType(rs.getInt("application_type"));
                application.setContent(rs.getString("content"));
                application.setCreatedAt(rs.getTimestamp("created_at"));
                application.setStatus(rs.getString("status"));
                application.setLastUpdated(rs.getTimestamp("last_updated"));
                application.setType(typeDao.getApplicationTypeById(rs.getInt("application_type")));
                application.setAttachedFile(rs.getString("attachedFile"));
                application.setResponse(rs.getString("response"));
                applications.add(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }

    public void updateApplicationStatus(int applicationId, String status, String responseText) {
        String sql = "UPDATE applications SET status = ?, response = ?, last_updated = ? WHERE id = ?";

        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status); 
            ps.setString(2, responseText); // Cập nhật response từ tham số
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Cập nhật last_updated với thời gian hiện tại
            ps.setInt(4, applicationId); // Truyền vào applicationId
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static void main(String[] args) {
        // Tạo một đối tượng ApplicationDAO
        ApplicationDAO applicationDAO = new ApplicationDAO();

        // Thay thế các giá trị dưới đây cho phù hợp với dữ liệu trong CSDL
        int applicationId = 13; // ID của ứng dụng bạn muốn cập nhật
        String status = "Approved"; // Trạng thái mới
        String responseText = "Your application has been approved."; // Phản hồi mới

        // Gọi phương thức updateApplicationStatus
        applicationDAO.updateApplicationStatus(applicationId, status, responseText);

        // In thông báo để xác nhận rằng phương thức đã được gọi
        System.out.println("Application status updated successfully.");
    }
}
