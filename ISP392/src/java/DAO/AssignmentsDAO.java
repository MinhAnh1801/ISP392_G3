/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Assignments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsDAO {

    public void insert(Assignments assignment) {
        String sql = "INSERT INTO Assignments (LecturerID, SubjectID, ClassID, AssignmentDescription, AssignedDate, DueDate) VALUES (?, ?, ?, ?, ?, ?)";
        DBContext dbContext = new DBContext(); // Khởi tạo đối tượng DBContext

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, assignment.getLecturerID());
            stmt.setInt(2, assignment.getSubjectID());
            stmt.setInt(3, assignment.getClassID());
            stmt.setString(4, assignment.getAssignmentDecription());
            stmt.setDate(5, new java.sql.Date(assignment.getAssignedDate().getTime()));
            stmt.setDate(6, new java.sql.Date(assignment.getDueDate().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm findAll
    public List<Assignments> findAll() {
        List<Assignments> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignments";
        DBContext dbContext = new DBContext(); // Khởi tạo đối tượng DBContext

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Assignments assignment = Assignments.builder()
                        .ID(rs.getInt("ID"))
                        .LecturerID(rs.getInt("LecturerID"))
                        .SubjectID(rs.getInt("SubjectID"))
                        .ClassID(rs.getInt("ClassID"))
                        .AssignmentDecription(rs.getString("AssignmentDescription"))
                        .AssignedDate(rs.getTimestamp("AssignedDate"))
                        .DueDate(rs.getTimestamp("DueDate"))
                        .build();

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }
}
