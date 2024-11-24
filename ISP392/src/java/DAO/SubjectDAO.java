package DAO;

import Context.DBContext;
import Model.Schedule;
import Model.Subjects;
import Model.Subjects1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    private static final String GET_ELIGIBLE_SUBJECTS_FOR_STUDENT
            = "SELECT DISTINCT sub.id AS subject_id, sub.name AS subject_name, sub.code AS subject_code\n"
            + "FROM Subjects sub\n"
            + "JOIN Student_Profile sp ON sp.major_id = sub.major_id AND sp.hoc_ky_hien_tai = sub.semester\n"
            + "LEFT JOIN Timetable t ON t.student_id = sp.student_id AND t.schedule_id IN \n"
            + "    (SELECT sch.id FROM Schedule sch WHERE sch.subject_id = sub.id)\n"
            + "WHERE sp.student_id = ? AND t.schedule_id IS NULL\n"
            + "ORDER BY sub.code;";

    // Method to get subjects available for registration for a specific student
    public List<Subjects> getEligibleSubjectsForStudent(int studentId) {
        List<Subjects> subjects = new ArrayList<>();
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ELIGIBLE_SUBJECTS_FOR_STUDENT)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subjects subject = new Subjects();
                    subject.setId(rs.getInt("subject_id"));
                    subject.setName(rs.getString("subject_name"));
                    subject.setCode(rs.getString("subject_code"));

                    subjects.add(subject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<Schedule> getSubjectsByLecturer(int lecturerId) throws SQLException {
        List<Schedule> subjects = new ArrayList<>();
        String query = """
            SELECT DISTINCT sub.id,sub.code AS subject_code,c.class_name, c.class_id
                        FROM Lecturer_Timetable lt
                        JOIN Schedule s ON lt.schedule_id = s.id
                        JOIN Subjects sub ON s.subject_id = sub.id
            			JOIN Class c on c.class_id=s.class_id
                        WHERE lt.lecturer_id = ?
                        ORDER BY sub.code
        """;

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, lecturerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Schedule s = new Schedule();
                    s.setSubject_id(rs.getInt("id"));
                    s.setSubject_code(rs.getString("subject_code"));
                    s.setClass_name(rs.getString("class_name"));
                    s.setClass_id(rs.getInt("class_id"));
                    subjects.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
// Phương thức lấy thông tin môn học theo ID

    public Subjects1 getsubjectById(int aInt) {
        Subjects1 subject = null;

        String query = "SELECT TOP (1000) [id]\n"
                + "      ,[code]\n"
                + "      ,[name]\n"
                + "      ,[credits]\n"
                + "      ,[description]\n"
                + "      ,[semester]\n"
                + "      ,[tuition]\n"
                + "      ,[major_id]\n"
                + "  FROM [dbo].[Subjects] WHERE id = ?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, aInt); // Gán giá trị id vào câu truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Tạo đối tượng Subjects1 từ kết quả truy vấn
                subject = new Subjects1();
                subject.setId(resultSet.getInt("id"));
                subject.setCode(resultSet.getString("code"));
                subject.setName(resultSet.getString("name"));
                subject.setCredits(resultSet.getInt("credits"));
                subject.setDescription(resultSet.getString("description"));
                subject.setSemester(resultSet.getInt("semester"));
            }

            // Đóng kết nối sau khi truy vấn xong
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject;
    }

    public List<Subjects1> getAllSubject() {
        List<Subjects1> subjects = new ArrayList<>();
        String query = "SELECT [id], [code], [name], [credits], [description], [semester], [tuition], [major_id] FROM [dbo].[Subjects]";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subjects1 subject = new Subjects1();
                subject.setId(rs.getInt("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setCredits(rs.getInt("credits"));
                subject.setDescription(rs.getString("description"));
                subject.setSemester(rs.getInt("semester"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public static void main(String[] args) {
        SubjectDAO dao = new SubjectDAO();
        Subjects1 getsubjectById = dao.getsubjectById(1);
        System.out.println(getsubjectById.getCode());

    }

}
