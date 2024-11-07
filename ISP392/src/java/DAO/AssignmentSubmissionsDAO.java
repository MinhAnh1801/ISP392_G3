package DAO;

import Context.DBContext;
import Model.Assignment_Submissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;

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

    public boolean isAssignmentSubmitted(int assignmentId, int studentId) {
        String sql = "SELECT COUNT(*) FROM Assignment_Submissions WHERE assignment_id = ? AND student_id = ?";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignmentId);
            stmt.setInt(2, studentId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        AssignmentSubmissionsDAO dao = new AssignmentSubmissionsDAO();

        // Gán assignmentId và studentId mẫu để test
        int assignmentId = 6; // Thay bằng ID của bài tập thực tế
        int studentId = 9; // Thay bằng ID của sinh viên thực tế

        boolean graded = dao.isGraded(assignmentId, studentId);

        // In kết quả kiểm tra
        if (graded) {
            System.out.println("Bài tập " + assignmentId + " của sinh viên " + studentId + " đã được chấm điểm.");
        } else {
            System.out.println("Bài tập " + assignmentId + " của sinh viên " + studentId + " chưa được chấm điểm.");
        }
    }

    public boolean isGraded(int assignmentId, int studentId) {
        String sql = "SELECT grade FROM Assignment_Submissions WHERE assignment_id = ? AND student_id = ? AND grade IS NOT NULL";
        DBContext dbContext = new DBContext();

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignmentId);
            stmt.setInt(2, studentId);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Trả về true nếu có một bản ghi với grade khác null
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu chưa có điểm
    }
}
