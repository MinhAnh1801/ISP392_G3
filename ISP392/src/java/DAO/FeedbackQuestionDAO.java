package DAL;

import DAL.DBcontext;
import Model.FeedbackQuestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackQuestionDAO extends DBcontext {

    public List<FeedbackQuestion> getAllFeedbackQuestions() {
        List<FeedbackQuestion> feedbackQuestions = new ArrayList<>();
        String query = "SELECT TOP (1000) [feedback_question_id], [name], [created_at] FROM [TEST].[dbo].[FeedbackQuestion]";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FeedbackQuestion feedbackQuestion = new FeedbackQuestion();
                
                feedbackQuestion.setId(rs.getInt("feedback_question_id"));
                feedbackQuestion.setName(rs.getString("name"));
                feedbackQuestion.setCreatedAt(rs.getDate("created_at"));

                feedbackQuestions.add(feedbackQuestion);
            }
        } catch (SQLException e) {
            System.out.println("Get feedback questions: " + e.getMessage());
        }

        return feedbackQuestions;
    }
    

    public static void main(String[] args) {
        FeedbackQuestionDAO feedbackQuestionDAO = new FeedbackQuestionDAO();

        // Lấy tất cả các câu hỏi phản hồi
        List<FeedbackQuestion> feedbackQuestions = feedbackQuestionDAO.getAllFeedbackQuestions();

        // Hiển thị thông tin các câu hỏi phản hồi
        for (FeedbackQuestion question : feedbackQuestions) {
            System.out.println("ID: " + question.getId());
            System.out.println("Name: " + question.getName());
            System.out.println("Created At: " + question.getCreatedAt());
            System.out.println("---------------------------");
        }
    }
}
