package DAO;

import Context.DBContext;
import Model.Schedule;
import Model.Subjects;
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

    public static void main(String[] args) {
        SubjectDAO dao = new SubjectDAO();
        try {
             System.out.println(dao.getSubjectsByLecturer(2));
        } catch (Exception e) {
        }
       
    }
}
