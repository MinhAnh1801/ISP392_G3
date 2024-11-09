/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Subjects;

/**
 *
 * @author admin
 */
public class SubjectGradeDAO extends DBcontext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select

    public SubjectGradeDAO() {
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

    public ArrayList<Subjects> getSubjectForGradeReport(int id) {
        ArrayList<Subjects> data = new ArrayList<Subjects>();

        try {
            String strSQL = "SELECT distinct s.id , s.code \n"
                    + "FROM Grades g\n"
                    + "join Subjects s on g.subject_id=s.id\n"
                    + "where student_id = ?";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString(2);
                int subject_id = rs.getInt(1);
                Subjects sg = new Subjects(subject_id, name);
                data.add(sg);
            }

        } catch (Exception e) {
        }
        return data;
    }
    
    public static void main(String[] args) {
        SubjectGradeDAO sg = new SubjectGradeDAO();
        System.out.println(sg.getSubjectForGradeReport(29)); 
    }
}
