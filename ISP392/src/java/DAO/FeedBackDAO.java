/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DormRooms;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Context.DBContext;
import Model.FeedbackForms;
import Model.Lecturer_Profile;
import Model.Student_Profile;
import Model.Subjects;
import Model.Subjects1;
public class FeedBackDAO extends DBContext{
        Connection connection;
  // Phương thức lấy tất cả các form phản hồi
    public List<FeedbackForms> getAllFeedbackForms() {
        List<FeedbackForms> feedbackFormsList = new ArrayList<>();
        
        // Câu truy vấn SQL để lấy tất cả các form phản hồi
        String query = "SELECT * FROM FeedbackForms";

        // Thực thi câu truy vấn và lấy kết quả
        try {
            connection = getConnection(); // Lấy kết nối từ DBContext (class cha)
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Duyệt qua kết quả và tạo đối tượng FeedbackForms từ dữ liệu
            while (resultSet.next()) {
                FeedbackForms feedbackForm = new FeedbackForms();
                feedbackForm.setId(resultSet.getInt("id"));
                feedbackForm.setStartDate(resultSet.getDate("start_date"));
                feedbackForm.setEndDate(resultSet.getDate("end_date"));
                feedbackForm.setCreatedAt(resultSet.getTimestamp("created_at"));
                
                UserDAO udao = new UserDAO();
                Lecturer_Profile lprofile = udao.getLecturerProfileById(resultSet.getInt("lecturer_id"));
                feedbackForm.setLecturerId(lprofile);
                                
                SubjectDAO sdao = new SubjectDAO();
                Subjects1 subject = sdao.getsubjectById(resultSet.getInt("subject_id"));
                feedbackForm.setSubjectId(subject);
                
                // Thêm đối tượng vào danh sách
                feedbackFormsList.add(feedbackForm);
            }
            
            // Đóng kết nối sau khi truy vấn xong
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return feedbackFormsList;
    }

        
        
}
