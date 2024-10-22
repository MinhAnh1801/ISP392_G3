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
        String sql = "SELECT TOP (1000) [student_id]\n"
                + "      ,[major_id]\n"
                + "      ,[year_of_study]\n"
                + "      ,[full_name]\n"
                + "      ,[date_of_birth]\n"
                + "      ,[phone_number]\n"
                + "      ,[email]\n"
                + "      ,[gender]\n"
                + "      ,[student_code]\n"
                + "      ,[address]\n"
                + "      ,[emergency_contact]\n"
                + "      ,[national_id]\n"
                + "      ,[class_id]\n"
                + "      ,[enrollment_year]\n"
                + "      ,[graduation_year]\n"
                + "      ,[gpa]\n"
                + "      ,[scholarship_status]\n"
                + "      ,[medical_conditions]\n"
                + "      ,[ten_phu_huynh]\n"
                + "      ,[so_dien_thoai_phu_huynh]\n"
                + "      ,[dia_chi_phu_huynh]\n"
                + "      ,[email_phu_huynh]\n"
                + "      ,[nghe_nghiep_phu_huynh]\n"
                + "      ,[noi_lam_viec_phu_huynh]\n"
                + "      ,[so_bang_dang_ky]\n"
                + "      ,[so_bang_dang_ky_cu]\n"
                + "      ,[ma_thanh_vien]\n"
                + "      ,[ngay_ghi_danh]\n"
                + "      ,[hinh_thuc_hoc]\n"
                + "      ,[trang_thai]\n"
                + "      ,[hoc_ky_hien_tai]\n"
                + "      ,[nganh_hoc]\n"
                + "      ,[chuong_trinh_hoc]\n"
                + "      ,[de_tai_tot_nghiep]\n"
                + "      ,[so_du_tai_khoan]\n"
                + "      ,[nganh_cu]\n"
                + "      ,[quyet_dinh_chuyen_nganh]\n"
                + "      ,[quyet_dinh_cong_nhan_sinh_vien_chinh_quy]\n"
                + "      ,[ngay_cong_nhan_sinh_vien_chinh_quy]\n"
                + "      ,[quyet_dinh_cong_nhan_sinh_vien_du_bi]\n"
                + "      ,[thoi_han_hoc_tai_truong]\n"
                + "      ,[lop_chinh]\n"
                + "      ,[loai_tai_chinh]\n"
                + "      ,[quyet_dinh_thoi_hoc]\n"
                + "      ,[quyet_dinh_chuyen_co_so]\n"
                + "      ,[quyet_dinh_ky_luat]\n"
                + "      ,[quyet_dinh_cong_nhan_tot_nghiep]\n"
                + "      ,[ngay_cong_nhan_tot_nghiep]\n"
                + "      ,[trang_thai_den]\n"
                + "      ,[chuyen_nganh]\n"
                + "      ,[photo]\n"
                + "  FROM [TEST].[dbo].[Student_Profile] WHERE [student_id] = ?";
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

                studentProfile.setFull_name(rs.getString("full_name"));
                studentProfile.setDate_of_birth(rs.getDate("date_of_birth"));
                studentProfile.setPhone_number(rs.getString("phone_number"));
                studentProfile.setEmail(rs.getString("email"));
                studentProfile.setGender(rs.getString("gender"));
                studentProfile.setStudent_code(rs.getString("student_code"));
                studentProfile.setAddress(rs.getString("address"));
                studentProfile.setEmergency_contact(rs.getString("emergency_contact"));
                studentProfile.setNational_id(rs.getString("national_id"));
                studentProfile.setClass_id(rs.getString("class_id"));
                studentProfile.setEnrollment_year(rs.getInt("enrollment_year"));
                studentProfile.setGraduation_year(rs.getInt("graduation_year"));
                studentProfile.setGpa(rs.getDouble("gpa"));
                studentProfile.setScholarship_status(rs.getBoolean("scholarship_status"));
                studentProfile.setMedical_conditions(rs.getString("medical_conditions"));
                studentProfile.setTen_phu_huynh(rs.getString("ten_phu_huynh"));
                studentProfile.setSo_dien_thoai_phu_huynh(rs.getString("so_dien_thoai_phu_huynh"));
                studentProfile.setDia_chi_phu_huynh(rs.getString("dia_chi_phu_huynh"));
                studentProfile.setEmail_phu_huynh(rs.getString("email_phu_huynh"));
                studentProfile.setNghe_nghiep_phu_huynh(rs.getString("nghe_nghiep_phu_huynh"));
                studentProfile.setNoi_lam_viec_phu_huynh(rs.getString("noi_lam_viec_phu_huynh"));
                studentProfile.setSo_bang_dang_ky(rs.getString("so_bang_dang_ky"));
                studentProfile.setSo_bang_dang_ky_cu(rs.getString("so_bang_dang_ky_cu"));
                studentProfile.setMa_thanh_vien(rs.getString("ma_thanh_vien"));
                studentProfile.setNgay_ghi_danh(rs.getString("ngay_ghi_danh"));
                studentProfile.setTrang_thai(rs.getString("trang_thai"));
                studentProfile.setHoc_ky_hien_tai(rs.getString("hoc_ky_hien_tai"));
                studentProfile.setNganh_hoc(rs.getString("nganh_hoc"));
                studentProfile.setChuong_trinh_hoc(rs.getString("chuong_trinh_hoc"));
                studentProfile.setDe_tai_tot_nghiep(rs.getString("de_tai_tot_nghiep"));
                studentProfile.setSo_du_tai_khoan(rs.getString("so_du_tai_khoan"));
                studentProfile.setNganh_cu(rs.getString("nganh_cu"));
                studentProfile.setQuyet_dinh_chuyen_nganh(rs.getString("quyet_dinh_chuyen_nganh"));
                studentProfile.setQuyet_dinh_cong_nhan_sinh_vien_chinh_quy(rs.getString("quyet_dinh_cong_nhan_sinh_vien_chinh_quy"));
                studentProfile.setNgay_cong_nhan_sinh_vien_chinh_quy(rs.getString("ngay_cong_nhan_sinh_vien_chinh_quy"));
                studentProfile.setQuyet_dinh_cong_nhan_sinh_vien_du_bi(rs.getString("quyet_dinh_cong_nhan_sinh_vien_du_bi"));
                studentProfile.setThoi_han_hoc_tai_truong(rs.getString("thoi_han_hoc_tai_truong"));
                studentProfile.setLop_chinh(rs.getString("lop_chinh"));
                studentProfile.setLoai_tai_chinh(rs.getString("loai_tai_chinh"));
                studentProfile.setQuyet_dinh_thoi_hoc(rs.getString("quyet_dinh_thoi_hoc"));
                studentProfile.setQuyet_dinh_chuyen_co_so(rs.getString("quyet_dinh_chuyen_co_so"));
                studentProfile.setQuyet_dinh_ky_luat(rs.getString("quyet_dinh_ky_luat"));
                studentProfile.setQuyet_dinh_cong_nhan_tot_nghiep(rs.getString("quyet_dinh_cong_nhan_tot_nghiep"));
                studentProfile.setNgay_cong_nhan_tot_nghiep(rs.getString("ngay_cong_nhan_tot_nghiep"));
                studentProfile.setTrang_thai_den(rs.getString("trang_thai_den"));
                studentProfile.setChuyen_nganh(rs.getString("chuyen_nganh"));
                studentProfile.setPhoto(rs.getString("photo"));
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

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

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

    public boolean updateStudentProfile(Integer id, String phoneNumber, String address, String parentName, String parentPhone, String parentAddress, String parentOccupation, String parentWorkplace) {
        Connection conn = null; // Khởi tạo kết nối
        PreparedStatement pstmt = null; // Khởi tạo PreparedStatement
        String sql = "UPDATE Student_Profile SET "
                + "phone_number = ?, "
                + "address = ?, "
                + "ten_phu_huynh = ?, "
                + "so_dien_thoai_phu_huynh = ?, "
                + "dia_chi_phu_huynh = ?, "
                + "nghe_nghiep_phu_huynh = ?, "
                + "noi_lam_viec_phu_huynh = ? "
                + "WHERE student_id = ?"; // Điều kiện để cập nhật theo student_id

        try {
            Connection connection = getConnection();
            pstmt = connection.prepareStatement(sql);

            // Gán giá trị cho các tham số trong câu lệnh SQL
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, address);
            pstmt.setString(3, parentName);
            pstmt.setString(4, parentPhone);
            pstmt.setString(5, parentAddress);
            pstmt.setString(6, parentOccupation);
            pstmt.setString(7, parentWorkplace);
            pstmt.setInt(8, id);

            // Thực hiện cập nhật
            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu có dòng nào được cập nhật
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return false; // Trả về false nếu có lỗi
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        int studentId = 2; // Thay đổi ID sinh viên mà bạn muốn cập nhật
        String newPhoneNumber = "0123456789";
        String newAddress = "123 Đường ABC, Quận XYZ";

        Student_Profile getStudentProfile = userDAO.getStudentProfile(studentId);

        System.out.println(getStudentProfile.toString());
    }

}
