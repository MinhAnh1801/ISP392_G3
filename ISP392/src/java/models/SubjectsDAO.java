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

    public ArrayList<Subjects> getSubjects() {
        ArrayList<Subjects> data = new ArrayList<Subjects>();
        try {
            String strSQL = "select*from Subjects";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String Code = rs.getString(2);
                String Name = rs.getString(3);
                String Credits = String.valueOf(rs.getInt(4));
                String Description = rs.getString(5);
                String Semester = rs.getString(6);

                Subjects s = new Subjects(ID, Code, Name, Credits,
                        Description, Semester);
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
                    + ",semester)" + "values(?,?,?,?,?) ";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, s.code);
            stm.setString(2, s.name);
            stm.setInt(3, Integer.parseInt(s.credits));
            stm.setString(4, s.description);
            stm.setString(5, s.semester);

            stm.executeQuery();
        } catch (Exception e) {
            System.out.println("insert Subject:" + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            String strSQL = "delete from Subjects where ID=?";
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
            String strSQL = "select*from Subjects where ID=?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                String code = rs.getString(2);
                String name = rs.getString(3);
                String credits = String.valueOf(rs.getInt(4));
                String description = rs.getString(5);
                String semester = rs.getString(6);

                s = new Subjects(id, code, name, credits, description, semester);
            }

        } catch (Exception e) {
            System.out.println("getSubjectById:" + e.getMessage());
        }

        return s;
    }

    public void update(Subjects s) {
        try {
            String strSQL = "UPDATE Subjects SET code=?, name=?, credits=?"
                    + ", description=?, semester=? WHERE id=?";

            PreparedStatement stm = connection.prepareStatement(strSQL);

            stm.setString(1, s.getCode());
            stm.setString(2, s.getName());
            stm.setInt(3, Integer.parseInt(s.getCredits()));
            stm.setString(4, s.getDescription());
            stm.setString(5, s.getSemester());
            stm.setInt(6, Integer.parseInt(s.getId()));

            int rowsAffected = stm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public ArrayList<Subjects> getSubjectByName(String name) {
        ArrayList<Subjects> data = new ArrayList<>();
        try {
            String strSQL = "select * from Subjects where name like ? ";
            stm = cnn.prepareStatement(strSQL);
            name = "%" + name + "%";
            stm.setString(1, name);
            rs = stm.executeQuery();
            while (rs.next()) {

                String Id = rs.getString(1);
                String Code = rs.getString(2);
                String Name = rs.getString(3);
                String Credits = rs.getString(4);
                String Descriptions = rs.getString(5);
                String Semester = rs.getString(6);
                
                
                Subjects s = new Subjects(Id, Code, Name, Credits
                        , Descriptions, Semester);
                data.add(s);
            }
            
        } catch (Exception e) {
           System.out.println("getSubjectByName:" + e.getMessage()); 
        }
        return data;
    }
    
    
}
