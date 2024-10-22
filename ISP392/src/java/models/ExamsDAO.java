/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ExamsDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;

    public ExamsDAO() {
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

    public ArrayList<Exams> getExams() {
        ArrayList<Exams> data = new ArrayList<Exams>();
        try {
            String strSQL = "select*from Exams";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String subject_id = rs.getString(2);
                String exam_date = rs.getString(3);
                String start_time = rs.getString(4);
                String end_time = rs.getString(5);
                String exam_room = rs.getString(6);
                String exam_type = rs.getString(7);
                Exams e = new Exams(id, subject_id, exam_date,
                        start_time, end_time, exam_room, exam_type);
                data.add(e);
            }
        } catch (Exception e) {
            System.out.println("getExams:" + e.getMessage());
        }
        return data;
    }

    public void upload(Exams e) {
        try {
            String strSQL = "insert into Exams(subject_id,exam_date,start_time,end_time,exam_room,exam_type) values(?,?,?,?,?,?)";

            stm = cnn.prepareStatement(strSQL);
//            stm.setString(1, e.id);
            stm.setString(1, e.subjectID);
            stm.setString(2, e.exam_date);
            stm.setString(3, e.start_time);
            stm.setString(4, e.end_time);
            stm.setString(5, e.exam_room);
            stm.setString(6, e.exam_type);

            stm.executeUpdate();
        } catch (Exception ec) {
            System.out.println("upload exams:" + ec.getMessage());
        }
    }
}
