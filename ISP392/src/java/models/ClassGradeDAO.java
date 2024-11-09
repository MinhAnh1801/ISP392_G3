/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ClassGradeDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public ClassGradeDAO() {
        connectDB();
    }

    private void connectDB() {
        cnn = connection;
        if (cnn != null) {
            System.out.println("Connect success");
        } else {
            System.out.println("Connect fail");
        }
    }

    public List<Subjects> getClassesName(int lecturer_id) {
        String query = """
            SELECT DISTINCT sub.code AS subject_code, cl.class_name
            FROM Lecturer_Timetable lt
            JOIN Schedule s ON lt.schedule_id = s.id
            JOIN Class cl ON s.class_id = cl.class_id
            JOIN Subjects sub ON s.subject_id = sub.id
            WHERE lt.lecturer_id = ?
            ORDER BY sub.code, cl.class_name
        """;

        Map<String, Subjects> subjectMap = new HashMap<>();

        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, lecturer_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String subjectCode = rs.getString("subject_code");
                    String className = rs.getString("class_name");

                    // If the subject isn't already in the map, add it
                    Subjects subject = subjectMap.get(subjectCode);
                    if (subject == null) {
                        subject = new Subjects(subjectCode, new ArrayList<>());
                        subjectMap.put(subjectCode, subject);
                    }

                    // Add the class to the subject's list of classes
                    subject.getClasses().add(new Classes(className));
                }
            }
        } catch (Exception e) {
            System.out.println("getAssignmentsByClassName: " + e.getMessage());
        }

        return new ArrayList<>(subjectMap.values());
    }

    public static void main(String[] args) {
        ClassGradeDAO dao = new ClassGradeDAO();
        System.out.println(dao.getClassesName(5));
    }
}
