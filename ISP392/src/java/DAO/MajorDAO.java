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
        String sql = "  SELECT TOP (1000)\n"
                + "    s.id,\n"
                + "    s.code,\n"
                + "    s.name,\n"
                + "    s.description,\n"
                + "    s.lecturer_id,\n"
                + "    c.major_id,\n"
                + "    c.subject_id,\n"
                + "	c.[semester],\n"
                + "	c.[credits],\n"
                + "    c.condition_subject_1,\n"
                + "    c.condition_subject_2\n"
                + "FROM\n"
                + "    [TEST].[dbo].[Subjects] s\n"
                + "JOIN\n"
                + "    [TEST].[dbo].[Curriculum] c ON s.id = c.subject_id\n"
                + "WHERE\n"
                + "    c.major_id = ?\n"
                + "ORDER BY\n"
                + "    c.semester ASC;";

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
        String sql = "SELECT id, code, name, description, lecturer_id "
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
                subject.setDescription(rs.getString("description"));
                subject.setLecturerId(rs.getInt("lecturer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject;
    }

    public List<Curriculum> getListCurriculum() {
        List<Curriculum> curriculumList = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Curriculum]";

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

                curriculum.setCredits(rs.getInt("credits"));
                curriculum.setSemester(rs.getInt("semester"));

                curriculumList.add(curriculum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return curriculumList;
    }

    public boolean updateByMajorIdSubjectId(int majorId, int subjectId, int conditionSubject1, int conditionSubject2, int credits) {
        String checkSql = "SELECT COUNT(*) FROM [TEST].[dbo].[Curriculum] WHERE [major_id] = ? AND [subject_id] = ?";
        String updateSql = "UPDATE [TEST].[dbo].[Curriculum] "
                + "SET [condition_subject_1] = ?, "
                + "[condition_subject_2] = ?, "
                + "[credits] = ? "
                + "WHERE [major_id] = ? AND [subject_id] = ?";

        try (Connection connection = getConnection(); PreparedStatement checkStmt = connection.prepareStatement(checkSql); PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

            // Kiểm tra xem bản ghi có tồn tại hay không
            checkStmt.setInt(1, majorId);
            checkStmt.setInt(2, subjectId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) { // Nếu có ít nhất một bản ghi
                // Thực hiện cập nhật
                updateStmt.setInt(1, conditionSubject1);
                updateStmt.setInt(2, conditionSubject2);
                updateStmt.setInt(3, credits);
                updateStmt.setInt(4, majorId);
                updateStmt.setInt(5, subjectId);

                int rowsAffected = updateStmt.executeUpdate();
                return rowsAffected > 0; // Trả về true nếu có bản ghi được cập nhật
            } else {
                System.out.println("Bản ghi không tồn tại.");
                return false; // Trả về false nếu không tìm thấy bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public List<Major> getAllMajor() {
        String sql = "SELECT [id], [major_name] FROM [dbo].[Major]";
        List<Major> majors = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Major major = new Major();
                major.setId(rs.getInt("id"));
                major.setName(rs.getString("major_name"));
                majors.add(major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return majors;
    }

    public boolean createCurriculum(int majorId, int subjectId, int conditionSubject1, int conditionSubject2, int semester, int credits, int credits1) {
        String insertSql = "INSERT INTO [dbo].[Curriculum] "
                + "([major_id], [subject_id], [condition_subject_1], [condition_subject_2], [semester], [credits]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            // Thiết lập các tham số cho câu lệnh SQL
            insertStmt.setInt(1, majorId);
            insertStmt.setInt(2, subjectId);
            insertStmt.setInt(3, conditionSubject1);
            insertStmt.setInt(4, conditionSubject2);
            insertStmt.setInt(5, semester);
            insertStmt.setInt(6, credits);

            // Thực thi câu lệnh INSERT
            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu bản ghi được tạo thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public static void main(String[] args) {
        // Tạo đối tượng của lớp chứa phương thức createCurriculum
        MajorDAO curriculumDAO = new MajorDAO();

        // Các giá trị mẫu để kiểm tra
        int majorId = 3; // ID chuyên ngành
        int subjectId = 21; // ID môn học
        int conditionSubject1 = 20; // Môn học điều kiện 1
        int conditionSubject2 = 21; // Môn học điều kiện 2
        int semester = 3; // Học kỳ
        int credits = 4; // Số tín chỉ

        // Gọi phương thức createCurriculum và kiểm tra kết quả
        boolean result = curriculumDAO.createCurriculum(majorId, subjectId, conditionSubject1, conditionSubject2, semester, credits, credits);

        // Hiển thị kết quả kiểm tra
        if (result) {
            System.out.println("Thêm bản ghi mới thành công!");
        } else {
            System.out.println("Thêm bản ghi mới thất bại!");
        }
    }

    public List<Subjects> getAllSubjects() {
        List<Subjects> subjectList = new ArrayList<>();
        String sql = "SELECT  * FROM [dbo].[Subjects]";

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Tạo đối tượng Subjects và thêm vào danh sách
                Subjects subject = new Subjects(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("lecturer_id")
                );
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectList;
    }

   public boolean deleteCurriculum(int majorId, int subjectId) {
    String sql = "DELETE FROM [dbo].[Curriculum] WHERE [major_id] = ? AND [subject_id] = ?";
    
    try (Connection connection = getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Thiết lập tham số cho câu lệnh SQL
        stmt.setInt(1, majorId);
        stmt.setInt(2, subjectId);

        // Thực hiện câu lệnh xóa
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Trả về true nếu có ít nhất một bản ghi được xóa
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Trả về false nếu có lỗi xảy ra
    }
}


}
