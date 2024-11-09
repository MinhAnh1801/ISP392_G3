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
        String sql = "INSERT INTO Assignments (lecturer_id, subject_id, class_id, assignment_name, assignment_description, assigned_date, due_date, fileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

    public List<Assignments> findAll(int uid) {
        List<Assignments> assignments = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Assignments a\n"
                + "JOIN Student_Subjects ss ON ss.subject_id = a.subject_id AND ss.class_id = a.class_id\n"
                + "WHERE ss.student_id = ?";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, uid);
            ResultSet rs = stmt.executeQuery();
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
                        .filePath(rs.getString("fileName"))
                        .build();

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

    public static void main(String[] args) {
        AssignmentsDAO dao = new AssignmentsDAO();
        System.out.println(dao.getAssignmentById(1));
    }

    public Assignments getAssignmentById(int assignmentId) {
        String sql = "SELECT * FROM assignments WHERE assignment_id = ?";
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
                            .filePath(rs.getString("fileName"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignment;
    }

    public List<Assignments> getAssignmentByLecturerId(int lecturerId) {
        List<Assignments> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignments WHERE lecturer_id = ?";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số lecturer_id
            stmt.setInt(1, lecturerId);

            try (ResultSet rs = stmt.executeQuery()) {
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
                            .filePath(rs.getString("fileName"))
                            .build();

                    assignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignments;
    }

    public void updateAssignment(Assignments assignment) {
        String sql = "UPDATE Assignments SET assignment_name = ?, assignment_description = ?, fileName = ?, due_date = ? WHERE assignment_id = ?";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, assignment.getAssignmentName());
            stmt.setString(2, assignment.getAssignmentDecription());
            stmt.setString(3, assignment.getFilePath());
            stmt.setDate(4, new java.sql.Date(assignment.getDueDate().getTime()));
            stmt.setInt(5, assignment.getID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
