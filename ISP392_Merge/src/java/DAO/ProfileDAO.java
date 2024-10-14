/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Profile;
import Model.Student_Profile;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author khucx
 */
public class ProfileDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Profile getProfileById(HttpSession session) {
        // Lấy id và role từ session
        Integer userId = (Integer) session.getAttribute("user");

        // Kiểm tra nếu session không có id hoặc role
        if (userId == null) {
            return null;  // Hoặc bạn có thể trả về giá trị lỗi
        }

        // Truy vấn SQL để lấy thông tin profile từ cơ sở dữ liệu
        String sql = "SELECT full_name, date_of_birth, phone_number, address, gender, profile_picture, bio "
                + "FROM Profile WHERE user_id = ?";

        Profile profile = null;

        try (Connection connection = new DBContext().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);  // Thiết lập giá trị id

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Lấy kết quả
            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                String profilePicture = resultSet.getString("profile_picture");
                String bio = resultSet.getString("bio");

                // Tạo đối tượng Profile
                profile = new Profile(userId, fullName, dateOfBirth, phoneNumber, address, gender, profilePicture, bio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public Student_Profile getStudentProfile(HttpSession session) {
        Integer student_id = (Integer) session.getAttribute("user");
        String sql = "Select * from Student_Profile where student_id = ?";
        Student_Profile studentProfile = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, student_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int majorId = rs.getInt("major_id");
                String yearOfStudy = rs.getString("year_of_study");

                // Tạo đối tượng StudentProfile
               studentProfile = new Student_Profile(student_id, majorId, yearOfStudy);
            }
        } catch (Exception e) {
        }
        return studentProfile;
    }
    
    public String generateRollNumber(int studentId, String yearOfStudy) {
        int yearOfStudyInt = Integer.parseInt(yearOfStudy);  // Chuyển đổi String thành int
        // Bước 1: Tính toán year_of_study - 2004
        int yearPart = yearOfStudyInt - 2004;
        
        // Bước 2: Kết hợp yearPart với studentId
        // Nếu studentId nhỏ hơn 6 ký tự, thêm 0 vào trước để đủ 6 ký tự
        String rollNumber = String.format("%02d%04d", yearPart, studentId);

        return rollNumber;
    }
    
    public String generateLecturerFormattedId(HttpSession session) {
        // Lấy thông tin profile từ cơ sở dữ liệu
        Profile profile = getProfileById(session);

        // Sử dụng hàm generateLecturerFormattedId() của Profile để tạo ID
        return profile.generateLecturerFormattedId();
    }
}
