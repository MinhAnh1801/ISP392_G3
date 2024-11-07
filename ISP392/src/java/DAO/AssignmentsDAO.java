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
        String sql = "INSERT INTO Assignments (lecturer_id, subject_id, class_id, assignment_name, assignment_description, assigned_date, due_date, file_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, assignment.getLecturerID());
            stmt.setInt(2, assignment.getSubjectID());
            stmt.setInt(3, assignment.getClassID());
            stmt.setString(4, assignment.getAssignmentName());
            stmt.setString(5, assignment.getAssignmentDecription());
            stmt.setDate(6, new java.sql.Date(assignment.getAssignedDate().getTime()));
            stmt.setDate(7, new java.sql.Date(assignment.getDueDate().getTime()));
            stmt.setString(8, assignment.getFilePath());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Assignments> findAll() {
        List<Assignments> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignments";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Assignments assignment = Assignments.builder()
                        .ID(rs.getInt("assignment_id"))
                        .LecturerID(rs.getInt("lecturer_id"))
                        .SubjectID(rs.getInt("subject_id"))
                        .ClassID(rs.getInt("class_id"))
                        .AssignmentName(rs.getString("assignment_name"))
                        .AssignmentDecription(rs.getString("assignment_description"))
                        .AssignedDate(rs.getTimestamp("assigned_date"))
                        .DueDate(rs.getTimestamp("due_date"))
                        .filePath(rs.getString("file_path"))
                        .build();

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

    public Assignments getAssignmentById(int assignmentId) {
        String sql = "SELECT * FROM Assignments WHERE assignment_id = ?";
        DBContext dbContext = new DBContext();
        Assignments assignment = null;

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignmentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    assignment = Assignments.builder()
                            .ID(rs.getInt("assignment_id"))
                            .LecturerID(rs.getInt("lecturer_id"))
                            .SubjectID(rs.getInt("subject_id"))
                            .ClassID(rs.getInt("class_id"))
                            .AssignmentName(rs.getString("assignment_name"))
                            .AssignmentDecription(rs.getString("assignment_description"))
                            .AssignedDate(rs.getTimestamp("assigned_date"))
                            .DueDate(rs.getTimestamp("due_date"))
                            .filePath(rs.getString("file_path"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignment;
    }

}
