/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Model.Major;
import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SubjectsDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public SubjectsDAO() {
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

    public List<Major> getMajors() {
        List<Major> data = new ArrayList<>();
        try {
            String strSQL = "select*from Major";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt(1);
                String Name = rs.getString(2);
                Major s = new Major(ID, Name);
                data.add(s);
            }

        } catch (Exception e) {
            System.out.println("getSubjects:" + e.getMessage());
        }

        return data;
    }

    public ArrayList<Subjects> getSubjects() {
        ArrayList<Subjects> data = new ArrayList<Subjects>();
        try {
            String strSQL = "select*from Subjects s join Major m on s.major_id = m.id";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String Code = rs.getString(2);
                String Name = rs.getString(3);
                String Credits = String.valueOf(rs.getInt(4));
                String Description = rs.getString(5);
                String Semester = rs.getString(6);
                int tuition = rs.getInt(7);
                String major = rs.getString("major_name");
                Subjects s = new Subjects(ID, Code, Name, Credits,
                        Description, Semester, tuition, major);
                data.add(s);
            }

        } catch (Exception e) {
            System.out.println("getSubjects:" + e.getMessage());
        }

        return data;
    }

    public void insert(Subjects s) {
        try {
            String strSQL = "insert into Subjects(code,name,credits,description"
                    + ",semester,tuition,major_id)" + "values(?,?,?,?,?,?,?) ";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, s.code);
            stm.setString(2, s.name);
            stm.setInt(3, Integer.parseInt(s.credits));
            stm.setString(4, s.description);
            stm.setString(5, s.semester);
            stm.setInt(6, s.tuition);
            stm.setInt(7, s.major_id);
            stm.executeQuery();
        } catch (Exception e) {
            System.out.println("insert Subject:" + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            String strSQL = "delete from Subjects where id=?";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, Integer.parseInt(id));
            stm.executeQuery();
        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        }
    }

    public Subjects getSubjectsById(String id) {
        Subjects s = new Subjects();
        try {
            String strSQL = "select*from Subjects s join Major m on s.major_id = m.id where s.id=?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(2);
                String name = rs.getString(3);
                String credits = String.valueOf(rs.getInt(4));
                String description = rs.getString(5);
                String semester = rs.getString(6);
                int tuition = rs.getInt("tuition");
                String major_name = rs.getString("major_name");
                s = new Subjects(id, code, name, credits, description, semester, tuition, major_name);
            }

        } catch (Exception e) {
            System.out.println("getSubjectById:" + e.getMessage());
        }

        return s;
    }

    public void update(Subjects s) {
        try {
            String strSQL = "UPDATE Subjects SET code=?, name=?, credits=?"
                    + ", description=?, semester=? , tuition = ? , major_id =? WHERE id=?";

            PreparedStatement stm = connection.prepareStatement(strSQL);

            stm.setString(1, s.getCode());
            stm.setString(2, s.getName());
            stm.setInt(3, Integer.parseInt(s.getCredits()));
            stm.setString(4, s.getDescription());
            stm.setString(5, s.getSemester());
            stm.setInt(8, Integer.parseInt(s.getId()));
            stm.setInt(6, s.getTuition());
            stm.setString(7, s.getSemester());
            int rowsAffected = stm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public boolean isDuplicated(int major_id, String subject_code) {
        try {
            String query = "SELECT COUNT(*) FROM Subjects WHERE major_id = ? AND code = ?";
            stm = cnn.prepareStatement(query);
            stm.setInt(1, major_id);
            stm.setString(2, subject_code);
            rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<Subjects> getSubjectByName(String name) {
        ArrayList<Subjects> data = new ArrayList<>();
        try {
            String strSQL = "select * from Subjects s join Major m on s.major_id = m.id where code like ? ";
            stm = cnn.prepareStatement(strSQL);
            name = "%" + name + "%";
            stm.setString(1, name);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String Code = rs.getString(2);
                String Name = rs.getString(3);
                String Credits = String.valueOf(rs.getInt(4));
                String Description = rs.getString(5);
                String Semester = rs.getString(6);
                int tuition = rs.getInt(7);
                String major = rs.getString("major_name");
                Subjects s = new Subjects(ID, Code, Name, Credits,
                        Description, Semester, tuition, major);
                data.add(s);
            }

        } catch (Exception e) {
            System.out.println("getSubjectByName:" + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        SubjectsDAO dao = new SubjectsDAO();
        System.out.println(dao.getMajors());
    }

}
