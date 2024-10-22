/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Guidelines;
import Model.Lecturer_Profile;
import Model.Major;
import Model.Profile;
import Model.Student_Profile;
import Model.User;

/**
 *
 * @author trung
 */
public class UserDAO extends DBContext {

    public User getUserById(int userId) {
        String sql = "SELECT [id], [username], [email], [password], [role], [status] FROM [dbo].[Users] WHERE [id] = ?";
        User user = new User();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // lấy thống tin bảng profile 
    public Profile getProfileById(int userId) {
        String sql = "SELECT [user_id], [full_name], [date_of_birth], [phone_number],"
                + " [address], [gender], [profile_picture],"
                + " [bio] FROM [dbo].[Profile] WHERE [user_id] = ?";
        Profile profile = new Profile();

        // khởi tạo db lấy dữ liệu từ sql 
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            // lấy userid truyền vào dấu ? thứ nhất trong sql 
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserDAO uDAO = new UserDAO();
                User user = uDAO.getUserById(rs.getInt("user_id"));
                profile.setUser_id(user);

                profile.setFull_name(rs.getString("full_name"));
                profile.setDate_of_birth(rs.getDate("date_of_birth"));
                profile.setPhone_number(rs.getString("phone_number"));
                profile.setAddress(rs.getString("address"));
                profile.setGender(rs.getString("gender"));
                profile.setProfile_picture(rs.getString("profile_picture"));
                profile.setBio(rs.getString("bio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

//    public Student_Profile getStudentProfile(int userId) {
//        String sql = "SELECT [student_id], [major_id], [year_of_study] FROM [dbo].[Student_Profile] WHERE [student_id] = ?";
//        Student_Profile studentProfile = new Student_Profile();
//
//        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                UserDAO uDao = new UserDAO();
//                User user = uDao.getUserById(rs.getInt("student_id"));
//                studentProfile.setStudent_id(user);
//
//                MajorDAO mDao = new MajorDAO();
//                Major major = mDao.getMajorById(rs.getInt("major_id"));
//                studentProfile.setMajor_id(major);
//
//                studentProfile.setYear_of_study(rs.getString("year_of_study"));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return studentProfile;
//    }
    public Student_Profile getStudentProfile(int userId) {
        String sql = "SELECT [student_id], [major_id], [year_of_study], [full_name], [date_of_birth], [phone_number], "
                + "[email], [gender], [student_code], [address], [emergency_contact], [photo], [national_id], "
                + "[class_id], [enrollment_year], [graduation_year], [gpa], [scholarship_status], [medical_conditions] "
                + "FROM [dbo].[Student_Profile] WHERE [student_id] = ?";
        Student_Profile studentProfile = new Student_Profile();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserDAO uDao = new UserDAO();
                User user = uDao.getUserById(rs.getInt("student_id"));
                studentProfile.setStudent_id(user);

                MajorDAO mDao = new MajorDAO();
                Major major = mDao.getMajorById(rs.getInt("major_id"));
                studentProfile.setMajor_id(major);

                studentProfile.setYear_of_study(rs.getString("year_of_study"));
                studentProfile.setFull_name(rs.getString("full_name"));
                studentProfile.setDate_of_birth(rs.getDate("date_of_birth"));
                studentProfile.setPhone_number(rs.getString("phone_number"));
                studentProfile.setEmail(rs.getString("email"));
                studentProfile.setGender(rs.getString("gender"));
                studentProfile.setStudent_code(rs.getString("student_code"));
                studentProfile.setAddress(rs.getString("address"));
                studentProfile.setEmergency_contact(rs.getString("emergency_contact"));
                studentProfile.setPhoto(rs.getString("photo"));
                studentProfile.setNational_id(rs.getString("national_id"));
                studentProfile.setClass_id(rs.getString("class_id"));
                studentProfile.setEnrollment_year(rs.getInt("enrollment_year"));
                studentProfile.setGraduation_year(rs.getInt("graduation_year"));
                studentProfile.setGpa(rs.getDouble("gpa"));
                studentProfile.setScholarship_status(rs.getBoolean("scholarship_status"));
                studentProfile.setMedical_conditions(rs.getString("medical_conditions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentProfile;
    }

    public boolean checkCurrentPassword(int userId, String currentPassword) {
        String sql = "SELECT [password] FROM [dbo].[Users] WHERE [id] = ?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(currentPassword); // So sánh mật khẩu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(int id, String newPassword) {
        String sql = "UPDATE [dbo].[Users] SET [password] = ? WHERE [id] = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Lecturer_Profile getLecturerProfileById(int lecturerId) {
        Lecturer_Profile lecturerProfile = null;

        String sql = "SELECT * FROM [dbo].[Lecturer_Profile] WHERE lecturer_id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, lecturerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lecturerProfile = new Lecturer_Profile();
                lecturerProfile.setLecturerId(resultSet.getInt("lecturer_id"));
                lecturerProfile.setExpertise(resultSet.getString("expertise"));
                lecturerProfile.setOffice(resultSet.getString("office"));
                lecturerProfile.setFullName(resultSet.getString("full_name"));
                lecturerProfile.setEmail(resultSet.getString("email"));
                lecturerProfile.setPhoneNumber(resultSet.getString("phone_number"));
                lecturerProfile.setDepartment(resultSet.getString("department"));
                lecturerProfile.setJoiningDate(resultSet.getDate("joining_date"));
                lecturerProfile.setBio(resultSet.getString("bio"));
                lecturerProfile.setPhotoUrl(resultSet.getString("photo_url"));
                lecturerProfile.setResearchInterest(resultSet.getString("research_interest"));
                lecturerProfile.setPublications(resultSet.getString("publications"));
                lecturerProfile.setAwards(resultSet.getString("awards"));
                lecturerProfile.setCreatedAt(resultSet.getTimestamp("created_at"));
                lecturerProfile.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                lecturerProfile.setResearchSkill(resultSet.getInt("researchSkill"));
                lecturerProfile.setTeachingSkill(resultSet.getInt("teachingSkill"));
                lecturerProfile.setMentoringSkill(resultSet.getInt("mentoringSkill"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lecturerProfile;
    }

  

    public boolean updateProfileLecturerById(Integer id, String fullName, String email, String phoneNumber, String department, String expertise, int researchSkill, int teachingSkill, int mentoringSkill) {
        String sql = "UPDATE [dbo].[Lecturer_Profile] SET [full_name] = ?, [email] = ?, [phone_number] = ?,"
                + " [department] = ?, [expertise] = ?, [researchSkill] = ?, [teachingSkill] = ?, [mentoringSkill] = ?, [updated_at] = GETDATE()"
                + " WHERE [lecturer_id] = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setString(4, department);
            ps.setString(5, expertise);
            ps.setInt(6, researchSkill);
            ps.setInt(7, teachingSkill);
            ps.setInt(8, mentoringSkill);
            ps.setInt(9, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the update failed
    }

   public boolean updateStudentProfile(int studentId, String phoneNumber, String address) {
    String sql = "UPDATE [dbo].[Student_Profile] SET "
            + "[phone_number] = ?, "
            + "[address] = ? "
            + "WHERE [student_id] = ?";

    try (Connection connection = getConnection(); 
         PreparedStatement ps = connection.prepareStatement(sql)) {
         
        ps.setString(1, phoneNumber);
        ps.setString(2, address);
        ps.setInt(3, studentId); // Đảm bảo bạn truyền vào studentId

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;  // Trả về false nếu cập nhật thất bại
}

    public static void main(String[] args) {
          UserDAO userDAO = new UserDAO();

    int studentId = 4; // Thay đổi ID sinh viên mà bạn muốn cập nhật
    String newPhoneNumber = "0123456789";
    String newAddress = "123 Đường ABC, Quận XYZ";

    Student_Profile p = userDAO.getStudentProfile(studentId);
        System.out.println(p);

//    if (isUpdated) {
//        System.out.println("Cập nhật thông tin sinh viên thành công.");
//    } else {
//        System.out.println("Cập nhật thông tin sinh viên thất bại.");
//    }
    }

}
