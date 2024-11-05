package DAO;

import Context.DBContext;
import Model.Assignment_Submissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AssignmentSubmissionsDAO {

    public void saveSubmission(Assignment_Submissions submission) {
        String sql = "INSERT INTO assignment_submissions (assignment_id, student_id, class_id, submission_date, submission_content) VALUES (?, ?, ?, ?, ?)";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, submission.getAssignmentID());
            stmt.setInt(2, submission.getStudentID());
            stmt.setInt(3, submission.getClassID());
            stmt.setDate(4, new java.sql.Date(submission.getSubmissionDate().getTime()));
            stmt.setString(5, submission.getSubmissionContent());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Khởi tạo DAO
        AssignmentSubmissionsDAO submissionsDAO = new AssignmentSubmissionsDAO();

        // Tạo một đối tượng Assignment_Submissions để test
        Assignment_Submissions testSubmission = Assignment_Submissions.builder()
                .AssignmentID(1) // ID bài tập
                .StudentID(9) // ID sinh viên
                .ClassID(1) // ID lớp
                .SubmissionDate(new Date()) // Ngày nộp hiện tại
                .SubmissionContent("/uploads/test_assignment_file.txt") // Đường dẫn file bài nộp thử
                .build();

        // Gọi hàm saveSubmission để lưu bài làm
        submissionsDAO.saveSubmission(testSubmission);
    }
}
