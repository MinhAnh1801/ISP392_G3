/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Context.DBContext;
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

    public ArrayList<Exams> getExams(int uid) {
        ArrayList<Exams> data = new ArrayList<Exams>();
        try {
            String strSQL = "SELECT \n"
                    + "    e.exam_date,\n"
                    + "    e.start_time,\n"
                    + "    e.end_time,\n"
                    + "	e.exam_room,\n"
                    + "    sj.code\n"
                    + "FROM \n"
                    + "    Exams e\n"
                    + "JOIN \n"
                    + "    Schedule s ON e.subject_id = s.subject_id\n"
                    + "JOIN \n"
                    + "    Timetable t ON t.schedule_id = s.id\n"
                    + "	JOIN Subjects sj on s.subject_id = sj.id\n"
                    + "WHERE \n"
                    + "    t.student_id = ?; ";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                String subject_name = rs.getString("code");
                String exam_date = rs.getString("exam_date");
                String start_time = rs.getString("start_time");
                String end_time = rs.getString("end_time");
                String exam_room = rs.getString("exam_room");
                Exams e = new Exams(exam_date, start_time, end_time, exam_room, subject_name);
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
    public static void main(String[] args) {
        ExamsDAO dao = new ExamsDAO();
        System.out.println(dao.getExams(29));
    }
    
}
