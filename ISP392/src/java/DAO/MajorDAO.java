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
import Model.GuideDetails;
import Model.Guidelines;
import Model.Major;
import Model.Student_Profile;
import Model.Subjects;
import Model.User;
import javax.security.auth.Subject;

/**
 *
 * @author trung
 */
public class MajorDAO extends DBContext {

    public Major getMajorById(int majorId) {
        String sql = "SELECT [id], [major_name] FROM [dbo].[Major] WHERE [id] = ?";
        Major major = new Major();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                major.setId(rs.getInt("id"));
                major.setName(rs.getString("major_name").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return major;
    }

    public List<Subjects> getListSubjectByUserId(Integer id) {
        // lấy major từ user
        UserDAO udao = new UserDAO();
        Student_Profile user = udao.getStudentProfile(id);

        int majorId = user.getMajor_id().getId();

        MajorDAO mdao = new MajorDAO();
        List<Subjects> subjects = mdao.getSubjectByMajorId(majorId);

        return subjects;
    }

    public List<Subjects> getSubjectByMajorId(int majorId) {
        List<Subjects> subjects = new ArrayList<>();
        String sql = "SELECT s.id, s.code, s.name, s.credits, s.description, s.semester "
                + "FROM Subjects s "
                + "JOIN Curriculum c ON s.id = c.subject_id "
                + "WHERE c.major_id = ? ORDER BY s.semester ASC";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, majorId); // Đặt giá trị majorId vào câu lệnh SQL
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setCredits(rs.getInt("credits"));
                subject.setDescription(rs.getString("description"));
                subject.setSemester(rs.getInt("semester"));
                subjects.add(subject); // Thêm môn học vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects; 
    }

    
    public static void main(String[] args) {
        MajorDAO mdao = new MajorDAO();
        System.out.println(mdao.getMajorById(2));
    }
    
}
