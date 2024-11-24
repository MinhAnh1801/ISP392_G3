/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.ApplicationType;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Dell
 */
public class ApplicationTypeDAO extends DBcontext{
    public List<ApplicationType> getAllApplicationTypes() {
        List<ApplicationType> applicationTypes = new ArrayList<>();
        String query = "SELECT * FROM application_type";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ApplicationType applicationType = new ApplicationType();
                applicationType.setId(rs.getInt("id"));
                applicationType.setTypeName(rs.getString("type_name"));
                applicationType.setTemplate(rs.getString("template"));
                applicationTypes.add(applicationType);
            }
        } catch (Exception e) {
            System.out.println("Error getting application types: " + e.getMessage());
        }
        return applicationTypes;
    }

    public ApplicationType getApplicationTypeById(int id) {
        ApplicationType applicationType = null;
        String query = "SELECT * FROM application_type WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                applicationType = new ApplicationType();
                applicationType.setId(rs.getInt("id"));
                applicationType.setTypeName(rs.getString("type_name"));
                applicationType.setTemplate(rs.getString("template"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting application type by ID: " + e.getMessage());
        }
        return applicationType;
    }

    public void insertApplicationType(ApplicationType applicationType) {
        String query = "INSERT INTO application_type (id, type_name) VALUES (?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, applicationType.getId());
            ps.setString(2, applicationType.getTypeName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting application type: " + e.getMessage());
        }
    }

    public void updateApplicationType(ApplicationType applicationType) {
        String query = "UPDATE application_type SET type_name = ?, template = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, applicationType.getTypeName());
            ps.setString(2, applicationType.getTemplate());
            ps.setInt(3, applicationType.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating application type: " + e.getMessage());
        }
    }

    public void deleteApplicationType(int id) {
        String query = "DELETE FROM application_type WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting application type: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ApplicationTypeDAO dao = new ApplicationTypeDAO();
        List<ApplicationType> applicationTypes = dao.getAllApplicationTypes();

        System.out.println(dao.getAllApplicationTypes());
    }
    public int generateMaxID() {
        int maxID = 0;
        String query = "SELECT MAX(id) AS max_id FROM application_type";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println("Error generating max ID: " + e.getMessage());
        }

        return maxID + 1; // Trả về ID lớn nhất + 1
    }
    public boolean isApplicationTypeExists(String typeName) {
    String query = "SELECT COUNT(*) FROM ApplicationType WHERE type_name = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, typeName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Trả về true nếu đã tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Không tồn tại
}

}