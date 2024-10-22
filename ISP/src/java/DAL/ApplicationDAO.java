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
public class ApplicationDAO extends DBcontext{
    public void saveApplication(Applications application) {
        String sql = "INSERT INTO applications (student_id, application_type, content, created_at, status, last_updated, attachedFile) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try
              {
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
    String sql = "SELECT * FROM applications order by id desc";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
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

}
