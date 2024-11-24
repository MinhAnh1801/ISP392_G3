/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.LecturerProfile;
import java.util.ArrayList;
import java.util.List;
import Model.StudentClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LecturerProfileDAO extends DBcontext {

    public List<LecturerProfile> getLecturersByStudentId(int studentId) {
        List<LecturerProfile> lecturers = new ArrayList<>();
        String query = """
        SELECT DISTINCT l.[lecturer_id], l.[full_name]
        FROM [StudentClass] sc
        JOIN [Class] c ON sc.[class_id] = c.[class_id]
        JOIN [Assignment_Submissions] asub ON sc.[student_id] = asub.[student_id] AND sc.[class_id] = asub.[class_id]
        JOIN [Assignments] a ON asub.[assignment_id] = a.[assignment_id]
        JOIN [Lecturer_Profile] l ON a.[lecturer_id] = l.[lecturer_id]
        WHERE sc.[student_id] = ?
        """;

        try ( PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, studentId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LecturerProfile lecturer = new LecturerProfile();
                    lecturer.setLecturerId(rs.getInt("lecturer_id"));
                    lecturer.setFullName(rs.getString("full_name"));

                    lecturers.add(lecturer);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching lecturers for student ID " + studentId + ": " + e.getMessage());
        }
        return lecturers;
    }

}
