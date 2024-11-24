/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Classs;
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
import Model.Materials;
import Model.PercentOption;
import Model.Student_Profile;
import Model.Subjects;
import Model.Subjects1;
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

    public List<Subjects1> getListSubjectByUserId(Integer id) {
        // lấy major từ user
        UserDAO udao = new UserDAO();
        Student_Profile user = udao.getStudentProfile(id);

        int majorId = user.getMajor_id().getId();
        System.out.println(majorId);

        MajorDAO mdao = new MajorDAO();
        List<Subjects1> subjects = mdao.getSubjectByMajorId(majorId);

        return subjects;
    }

    public List<Subjects1> getSubjectByMajorId(int majorId) {
        List<Subjects1> subjects = new ArrayList<>();
        // Ensure the SQL query includes condition_subject_1 and condition_subject_2
        String sql = "   SELECT TOP (1000)\n" +
"    s.id,\n" +
"    s.code,\n" +
"    s.name,\n" +
"    s.description,\n" +
"    c.major_id,\n" +
"    c.subject_id,\n" +
"    c.[semester],\n" +
"    c.[credits],\n" +
"    c.condition_subject_1,\n" +
"    c.condition_subject_2\n" +
"FROM\n" +
"    [dbo].[Subjects] s\n" +
"JOIN\n" +
"    [dbo].[Curriculum] c ON s.id = c.subject_id\n" +
"WHERE\n" +
"    c.major_id = ?\n" +
"ORDER BY\n" +
"    c.semester ASC;";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, majorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subjects1 subject = new Subjects1();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setCredits(rs.getInt("credits"));
                subject.setDescription(rs.getString("description"));
                subject.setSemester(rs.getInt("semester"));

                MajorDAO mdao = new MajorDAO();

                // Retrieve condition_subject_1
                int conditionSubject1Id = rs.getInt("condition_subject_1");
                if (!rs.wasNull()) { // Check if the value is not null
                    Subjects1 cs1 = mdao.getSubject(conditionSubject1Id);
                    subject.setConditionSubject1(cs1);

                }

                // Retrieve condition_subject_2
                int conditionSubject2Id = rs.getInt("condition_subject_2");
                if (!rs.wasNull()) { // Check if the value is not null
                    Subjects1 cs2 = mdao.getSubject(conditionSubject2Id);
                    subject.setConditionSubject1(cs2);
                }

                subjects.add(subject); // Add the subject to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return subjects;
    }

    private Subjects1 getSubject(int id) {
        Subjects1 subject = null;
        String sql = "SELECT id as s_id, code, name, description FROM Subjects WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subjects1();
                subject.setId(rs.getInt("s_id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
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

                Subjects1 subject = mdao.getSubject(rs.getInt("subject_id"));
                curriculum.setSubject_id(subject);

                // Lấy thông tin điều kiện môn học
                int conditionSubject1Id = rs.getInt("condition_subject_1");
                if (!rs.wasNull()) {
                    Subjects1 condition1 = mdao.getSubject(conditionSubject1Id);
                    curriculum.setCondition_subject_1(condition1);
                }

                int conditionSubject2Id = rs.getInt("condition_subject_2");
                if (!rs.wasNull()) {
                    Subjects1 condition2 = mdao.getSubject(conditionSubject2Id);
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
        String checkSql = "SELECT COUNT(*) FROM [dbo].[Curriculum] WHERE [major_id] = ? AND [subject_id] = ?";
        String updateSql = "UPDATE [dbo].[Curriculum] "
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
                        rs.getString("description")
                //                        rs.getInt("lecturer_id")
                );
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectList;
    }

    public static void main(String[] args) {
        MajorDAO mdao = new MajorDAO();

        System.out.println(mdao.getClassBySubjectId(2, 8));
    }

    public boolean deleteCurriculum(int majorId, int subjectId) {
        String sql = "DELETE FROM [dbo].[Curriculum] WHERE [major_id] = ? AND [subject_id] = ?";

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

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

    public boolean checkCurriculum(String major, String ss) {
        String sql = "SELECT COUNT(*) FROM [dbo].[Curriculum] WHERE major_id = ? AND subject_id = ?";

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Thiết lập tham số cho câu lệnh SQL
            stmt.setString(1, major);
            stmt.setString(2, ss);

            // Thực hiện truy vấn
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi xảy ra hoặc không tìm thấy bản ghi
    }

    public Subjects getSubjectById(int subjectId) {
        Subjects subject = null; // Khởi tạo biến subject
        String sql = "SELECT id, code, name, description FROM [dbo].[Subjects] WHERE id = ?"; // Truy vấn SQL

        try (Connection connection = getConnection(); // Kết nối cơ sở dữ liệu
                 PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, subjectId); // Thiết lập giá trị cho tham số trong truy vấn
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn

            if (rs.next()) { // Kiểm tra nếu có kết quả
                subject = new Subjects(); // Tạo đối tượng Subjects
                subject.setId(rs.getInt("id")); // Thiết lập ID
                subject.setCode(rs.getString("code")); // Thiết lập mã môn học
                subject.setName(rs.getString("name")); // Thiết lập tên môn học
                subject.setDescription(rs.getString("description")); // Thiết lập mô tả
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        return subject; // Trả về đối tượng môn học
    }

    public List<Classs> getAllClass() {
        List<Classs> classList = new ArrayList<>();

        String sql = "SELECT class_id, class_name FROM [dbo].[Class]";

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int classId = rs.getInt("class_id");
                String className = rs.getString("class_name");

                Classs classObj = new Classs();
                classObj.setClass_id(classId);
                classObj.setClass_name(className);

                classList.add(classObj);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in production code
        }

        return classList;
    }

    public List<Subjects> getSubjectByTeacher(int userId) {
        List<Subjects> subjectsList = new ArrayList<>();
        String query = "SELECT sb.* FROM Subjects sb\n"
                + "JOIN Schedule s on s.subject_id = sb.id\n"
                + "JOIN Lecturer_Timetable lt on lt.schedule_id = s.id\n"
                + "WHERE lt.lecturer_id = ?"; // Join Subjects with Lecturer_Timetable

        try (Connection connection = getConnection(); // Ensure connection is managed
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subjects subject = new Subjects();
                subject.setId(resultSet.getInt("id"));
                subject.setCode(resultSet.getString("code"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("description"));
                subjectsList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectsList;
    }

    public List<Classs> getClassBySubjectId(int uid,int subjectId) {
        List<Classs> classList = new ArrayList<>();
        String sql = "SELECT DISTINCT cl.class_id,cl.class_name\n"
                + "FROM Lecturer_Timetable lt\n"
                + "JOIN Schedule s ON lt.schedule_id = s.id\n"
                + "JOIN Class cl ON s.class_id = cl.class_id\n"
                + "WHERE lt.lecturer_id = ? and s.subject_id=?;";

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, uid);
            ps.setInt(2, subjectId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Classs classObj = new Classs();
                classObj.setClass_id(rs.getInt("class_id"));
                classObj.setClass_name(rs.getString("class_name"));
                classList.add(classObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }

    public List<PercentOption> getAllPercent() {
        List<PercentOption> percentOptions = new ArrayList<>();
        String query = "SELECT percentId, percent_value FROM PercentOptions";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PercentOption option = new PercentOption();
                option.setPercentId(resultSet.getInt("percentId"));
                option.setPercent_value(resultSet.getInt("percent_value"));
                percentOptions.add(option);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return percentOptions;
    }

  
}
