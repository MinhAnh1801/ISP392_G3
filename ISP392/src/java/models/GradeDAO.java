/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class GradeDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;

    public GradeDAO() {
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

    public ArrayList<Grade> getGrades() {
        ArrayList<Grade> data = new ArrayList<Grade>();
        try {
            String strSQL = "select*from Grades";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String student_id = rs.getString(2);
                String subject_id = rs.getString(3);
                String grade = rs.getString(4);
                String upload_date = rs.getString(5);
                String comments = rs.getString(6);

                Grade g = new Grade(id, student_id, subject_id, grade, upload_date, comments);
                data.add(g);
            }
        } catch (Exception e) {
            System.out.println("getGrades:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Grade> getGradeByStudentID(String student_id) {
        ArrayList<Grade> data = new ArrayList<>();
        try {
            String strSQL = "SELECT * FROM Grades WHERE student_id LIKE ?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, "%" + student_id + "%");
            rs = stm.executeQuery();

            while (rs.next()) {

                String Id = rs.getString(1);
                String Student_ID = rs.getString(2);
                String Subject_ID = rs.getString(3);
                String Grade = rs.getString(4);
                String Upload_date = rs.getString(5);
                String Comments = rs.getString(6);

                Grade g = new Grade(Id, Student_ID, Subject_ID, Grade,
                        Upload_date, Comments);
                data.add(g);
            }
        } catch (Exception e) {
            System.out.println("getGradeByStudentID:" + e.getMessage());
        }
        return data;
    }
}
