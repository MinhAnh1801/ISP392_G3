/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.Feedback;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO extends DBcontext {

    public boolean addFeedback(Feedback feedback) {
        String query = "INSERT INTO [TEST].[dbo].[Feedback] ([rating], [student_id], [lecturer_id], [feedback_question_id], [status]) VALUES (?, ?, ?, ?, ?)";
        boolean isSuccess = false;

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, feedback.getRating());
            ps.setInt(2, feedback.getStudentId());
            ps.setInt(3, feedback.getLecturerId());
            ps.setInt(4, feedback.getFeedbackQuestionId());
            ps.setBoolean(5, feedback.isStatus());

            int rowsAffected = ps.executeUpdate();
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding feedback: " + e.getMessage());
        }

        return isSuccess;
    }

    public boolean hasFeedback(int studentId, int lecturerId) {
        String query = "SELECT COUNT(*) FROM [TEST].[dbo].[Feedback] WHERE student_id = ? AND lecturer_id = ? AND status = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setInt(2, lecturerId);
            ps.setBoolean(3, true);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking feedback: " + e.getMessage());
        }
        return false;
    }
    
public boolean updateFeedback(Feedback feedback) {
    String query = "UPDATE [TEST].[dbo].[Feedback] SET [rating] = ?, [student_id] = ?, [lecturer_id] = ?, [feedback_question_id] = ?, [status] = ? WHERE [feedback_id] = ?";
    boolean isSuccess = false;

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, feedback.getRating());
        ps.setInt(2, feedback.getStudentId());
        ps.setInt(3, feedback.getLecturerId());
        ps.setInt(4, feedback.getFeedbackQuestionId());
        ps.setBoolean(5, feedback.isStatus());
        ps.setInt(6, feedback.getFeedbackId()); // Giả sử id là khóa chính của bản ghi Feedback cần cập nhật

        int rowsAffected = ps.executeUpdate();
        isSuccess = rowsAffected > 0;
    } catch (SQLException e) {
        System.err.println("Error updating feedback: " + e.getMessage());
    }

    return isSuccess;
}
 public Feedback getFeedbackById(int feedbackId) {
        Feedback feedback = null;
        String query = "SELECT * FROM Feedback WHERE id = ?";

        try (
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, feedbackId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setFeedbackId(rs.getInt("id"));
                    feedback.setRating(rs.getString("rating"));
                    feedback.setStudentId(rs.getInt("student_id"));
                    feedback.setLecturerId(rs.getInt("lecturer_id"));
                    feedback.setFeedbackQuestionId(rs.getInt("feedback_question_id"));
                    feedback.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }
 
  public List<Feedback> getFeedbackByLecturerId(int lecturerId) {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT * FROM Feedback WHERE lecturer_id = ?";

        try (
             PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, lecturerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedbackId(rs.getInt("feedback_id"));
                    feedback.setRating(rs.getString("rating"));
                    feedback.setStudentId(rs.getInt("student_id"));
                    feedback.setLecturerId(rs.getInt("lecturer_id"));
                    feedback.setFeedbackQuestionId(rs.getInt("feedback_question_id"));
                    feedback.setStatus(rs.getBoolean("status"));

                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }
    public static void main(String[] args) {
        
    }
}
