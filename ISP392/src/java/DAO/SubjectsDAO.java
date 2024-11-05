/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Subjects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FPTSHOP
 */
public class SubjectsDAO {

    public List<Subjects> findAllSubjects() {
        List<Subjects> subjectList = new ArrayList<>();
        String sql = "SELECT * FROM Subjects";
        DBContext dbContext = new DBContext(); // Khởi tạo đối tượng DBContext

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Subjects subject = Subjects.builder()
                        .id(rs.getInt("id"))
                        .code(rs.getString("code"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .build();

                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectList;
    }

    public String getSubjectCodeById(int subjectId) {
        String sql = "SELECT code FROM Subjects WHERE id = ?";
        DBContext dbContext = new DBContext();
        String code = null;

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                code = rs.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return code;
    }
    
    public List<Integer> getAllSubjectIds() {
    List<Integer> subjectIds = new ArrayList<>();
    String sql = "SELECT id FROM Subjects";
    DBContext dbContext = new DBContext();

    try (Connection conn = dbContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            subjectIds.add(rs.getInt("id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return subjectIds;
}


    public static void main(String[] args) {
        SubjectsDAO subjectsDAO = new SubjectsDAO();

        int subjectId = 19; // Thay thế bằng subjectId mong muốn để kiểm tra
        String subjectCode = subjectsDAO.getSubjectCodeById(subjectId);

        if (subjectCode != null) {
            System.out.println("Subject Code for ID " + subjectId + ": " + subjectCode);
        } else {
            System.out.println("No subject found for ID " + subjectId);
        }
    }

}
