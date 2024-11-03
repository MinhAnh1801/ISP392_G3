/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Grades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.GuideDetails;
import Model.Guidelines;
import Model.Student_Profile;
import Model.Subjects;
import Model.User;


public class GradeDAO  extends DBContext{
        private final Connection connection = getConnection();

  public List<Grades> getGradeById(Integer studentId) {
    List<Grades> gradesList = new ArrayList<>();
    String sql = "SELECT [id], [student_id], [subject_id], [grade], [upload_date], [comments] " +
                 "FROM [TEST].[dbo].[Grades] " +
                 "WHERE [student_id] = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, studentId); // Đặt giá trị cho tham số trong câu lệnh SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // Tạo đối tượng Grades và lấy dữ liệu từ ResultSet
            Grades grade = new Grades();
            grade.setId(resultSet.getInt("id"));
            
            
            UserDAO udao  = new UserDAO();
            Student_Profile student = udao.getStudentProfile(resultSet.getInt("student_id"));            
            grade.setStudentId(student);
            
            MajorDAO mdao = new MajorDAO();
            Subjects subject = mdao.getSubjectById(resultSet.getInt("subject_id")) ;
            grade.setSubjectId(subject);
            
            
            grade.setGrade(resultSet.getDouble("grade"));
            grade.setDate(resultSet.getDate("upload_date")); 
            grade.setComment(resultSet.getString("comments"));
            gradesList.add(grade); // Thêm vào danh sách
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi xảy ra
    } finally {
        try {
            if (connection != null) {
                connection.close(); // Đảm bảo đóng kết nối khi hoàn tất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return gradesList; // Trả về danh sách các điểm số
}

   public void insertGrade(int studentId, int subjectId, double grade, String comments, int typeId) {
    String query = "INSERT INTO [dbo].[Grades] (student_id, subject_id, grade, comments, type,upload_date) VALUES (?, ?, ?, ?, ?,GETDATE())";

    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, studentId);
        stmt.setInt(2, subjectId);
        stmt.setDouble(3, grade);
        stmt.setString(4, comments);
        stmt.setInt(5, typeId);

        // Thực thi câu lệnh chèn dữ liệu
        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Grade inserted successfully.");
        } else {
            System.out.println("Failed to insert grade.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error while inserting grade: " + e.getMessage());
    }
}

        

   
}
