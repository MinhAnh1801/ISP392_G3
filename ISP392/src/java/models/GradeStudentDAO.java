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

/**
 *
 * @author admin
 */
public class GradeStudentDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public GradeStudentDAO() {
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

    public ArrayList<GradeStudent> getGradeStudents() {
        ArrayList<GradeStudent> data = new ArrayList<GradeStudent>();

        try {
            String strSQL = "SELECT g.id, sp.full_name, g.grade \n"
                    + "FROM grades g \n"
                    + "JOIN student_profile sp ON g.student_id = sp.student_id \n"
                    + "WHERE g.student_id = 3";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String full_name = rs.getString(2);
                String grade = rs.getString(3);

                GradeStudent gs = new GradeStudent(id, full_name, grade);
                data.add(gs);
            }

        } catch (Exception e) {
            System.out.println("getGradeStudent:" + e.getMessage());
        }
        return data;
    }

}
