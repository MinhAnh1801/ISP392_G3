/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Curriculum;
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
                major.setName(rs.getString("major_name"));
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
        // Ensure the SQL query includes condition_subject_1 and condition_subject_2
        String sql = "SELECT s.id, s.code, s.name, s.credits, s.description, s.semester, s.lecturer_id, "
                + "c.condition_subject_1, c.condition_subject_2 "
                + "FROM Subjects s "
                + "JOIN Curriculum c ON s.id = c.subject_id "
                + "WHERE c.major_id = ? ORDER BY s.semester ASC";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setCredits(rs.getInt("credits"));
                subject.setDescription(rs.getString("description"));
                subject.setSemester(rs.getInt("semester"));
                subject.setLecturerId(rs.getInt("lecturer_id"));

                MajorDAO mdao = new MajorDAO();

                // Retrieve condition_subject_1
                int conditionSubject1Id = rs.getInt("condition_subject_1");
                if (!rs.wasNull()) { // Check if the value is not null
                    Subjects cs1 = mdao.getSubject(conditionSubject1Id);
                    subject.setConditionSubject1(cs1);

                }

                // Retrieve condition_subject_2
                int conditionSubject2Id = rs.getInt("condition_subject_2");
                if (!rs.wasNull()) { // Check if the value is not null
                    Subjects cs2 = mdao.getSubject(conditionSubject2Id);
                    subject.setConditionSubject1(cs2);
                }

                subjects.add(subject); // Add the subject to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return subjects;
    }

    private Subjects getSubject(int id) {
        Subjects subject = null;
        String sql = "SELECT id, code, name, credits, description, semester, lecturer_id "
                + "FROM Subjects "
                + "WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                subject = new Subjects();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setCredits(rs.getInt("credits"));
                subject.setDescription(rs.getString("description"));
                subject.setSemester(rs.getInt("semester"));
                subject.setLecturerId(rs.getInt("lecturer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject;
    }

    public List<Curriculum> getListCurriculum() {
        List<Curriculum> curriculumList = new ArrayList<>();
        String sql = "SELECT  [major_id], [subject_id], [condition_subject_1], [condition_subject_2] FROM [dbo].[Curriculum]";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Lấy thông tin major
                Curriculum curriculum = new Curriculum();
                MajorDAO mdao = new MajorDAO();
                Major major = mdao.getMajorById(rs.getInt("major_id"));
                curriculum.setMajor_id(major);

                Subjects subject = mdao.getSubject(rs.getInt("subject_id"));
                curriculum.setSubject_id(subject);

                // Lấy thông tin điều kiện môn học
                int conditionSubject1Id = rs.getInt("condition_subject_1");
                if (!rs.wasNull()) {
                    Subjects condition1 = mdao.getSubject(conditionSubject1Id);
                    curriculum.setCondition_subject_1(condition1);
                }

                int conditionSubject2Id = rs.getInt("condition_subject_2");
                if (!rs.wasNull()) {
                    Subjects condition2 = mdao.getSubject(conditionSubject2Id);
                    curriculum.setCondition_subject_2(condition2);
                }

                curriculumList.add(curriculum);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        return curriculumList;
    }

    public static void main(String[] args) {
        MajorDAO mdao = new MajorDAO();
         List<Curriculum> curriculumList = mdao.getListCurriculum();
        for (Curriculum curriculum : curriculumList) {
            System.out.println(curriculum.getMajor_id().getName());            
        }
    }



}
