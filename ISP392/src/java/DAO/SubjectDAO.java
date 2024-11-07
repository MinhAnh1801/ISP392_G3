package DAO;

import Context.DBContext;
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
            + "JOIN Student_Profile sp ON sp.major_id = sub.major_id AND sp.hoc_ki_hien_tai = sub.semester\n"
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

    public static void main(String[] args) {
        SubjectDAO dao = new SubjectDAO();
        System.out.println(dao.getEligibleSubjectsForStudent(4));
    }
}
