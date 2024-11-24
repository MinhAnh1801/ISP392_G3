/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Notifications;
import Model.Profile;
import Model.Student_Profile;
import Model.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author khucx
 */
public class ProfileDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private final UserDAO userDAO = new UserDAO();
    private final MajorDAO majorDAO = new MajorDAO();

    public Student_Profile getProfileById(HttpSession session) {
        // Lấy id và role từ session
        Integer userId = (Integer) session.getAttribute("user");

        // Kiểm tra nếu session không có id hoặc role
        if (userId == null) {
            return null;  // Hoặc bạn có thể trả về giá trị lỗi
        }

        // Truy vấn SQL để lấy thông tin profile từ cơ sở dữ liệu
        String sql = "SELECT * "
                + "FROM Student_Profile WHERE user_id = ?";

        Student_Profile profile = null;

        try (Connection connection = new DBContext().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);  // Thiết lập giá trị id

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Lấy kết quả
            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int wallet = resultSet.getInt("so_du_tai_khoan");

                // Tạo đối tượng Profile
                profile = new Student_Profile(studentId, wallet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public Student_Profile getStudentProfile(HttpSession session) {
        Integer student_id = (Integer) session.getAttribute("user");
        String sql = "SELECT * FROM Student_Profile WHERE student_id = ?";
        Student_Profile studentProfile = null;
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
//                    int majorId = rs.getInt("major_id");
                    String yearOfStudy = rs.getString("year_of_study");
                    String wallet = rs.getString("so_du_tai_khoan");  // Lấy số dư ví
                    studentProfile = new Student_Profile();
                    studentProfile.setStudent_id(userDAO.getUserById(rs.getInt("student_id")));
                    studentProfile.setMajor_id(majorDAO.getMajorById(rs.getInt("major_id")));
                    studentProfile.setSo_du_tai_khoan(wallet);
                    studentProfile.setYear_of_study(yearOfStudy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


    public void updateStudentProfile(Student_Profile profile) throws SQLException {
        String sql = "UPDATE Student_Profile SET so_du_tai_khoan = ? WHERE student_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, profile.getWallet()); 
            ps.setInt(2, profile.getStudent_id().getId());
            ps.executeUpdate();
        }
    }

//    public Student_Profile getStudentProfileByUserId(int userId) {
//        String sql = "SELECT student_id, so_du_tai_khoan FROM Student_Profile WHERE student_id = ?";
//        Student_Profile studentProfile = null;
//
//        try (Connection connection = new DBContext().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, userId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
////                int studentId = resultSet.getInt("student_id");
////                int majorId = resultSet.getInt("major_id");
//                String yearOfStudy = resultSet.getString("year_of_study");
//                int wallet = resultSet.getInt("wallet");
//                studentProfile = new Student_Profile();
//                studentProfile.setStudent_id(userDAO.getUserById(resultSet.getInt("student_id")));
//                studentProfile.setMajor_id(majorDAO.getMajorById(resultSet.getInt("major_id")));
//                studentProfile.setWallet(wallet);
//                studentProfile.setYear_of_study(yearOfStudy);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return studentProfile;
//    }

    public static void main(String[] args) {
        ProfileDAO profileDAO = new ProfileDAO();
        // Thay thế id của sinh viên với một id hợp lệ trong cơ sở dữ liệu của bạn
        int testUserId = 2; // Ví dụ: giả sử userId là 1, bạn cần thay thế bằ   ng ID hợp lệ

    }

}