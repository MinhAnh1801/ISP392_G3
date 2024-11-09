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
public class ClassDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public ClassDAO() {
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

    public ArrayList<Classes> getClasses() {
        ArrayList<Classes> data = new ArrayList<Classes>();
        try {
            String strSQL = "select*from Class";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String class_id = rs.getString(1);
                String class_name = rs.getString(2);
                int capacity = rs.getInt(3);
                Classes c = new Classes(class_id, class_name, capacity);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("getClass:" + e.getMessage());
        }

        return data;
    }

    public void insert(Classes c) {
        try {
            String strSQL = "insert into Class(class_name,capacity)" + "values(?,?) ";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, c.class_name);
            stm.setInt(2, c.capacity);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("insert:" + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            String strSQL = "delete from Class where class_id=?";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, Integer.parseInt(id));
            stm.executeQuery();
        } catch (Exception e) {
            System.out.println("delete Class:" + e.getMessage());
        }
    }

    public Classes getClassesById(String id) {
        Classes c = new Classes();
        try {
            String strSQL = "select*from Class where class_id=?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                String class_name = rs.getString(2);
                int capacity = rs.getInt(3);
                c = new Classes(id, class_name, capacity);
            }

        } catch (Exception e) {
            System.out.println("getClassById:" + e.getMessage());
        }

        return c;
    }

    public void update(Classes c) {
        try {
            String strSQL = "UPDATE Class SET class_name=?, capacity =? WHERE class_id=?";

            PreparedStatement stm = connection.prepareStatement(strSQL);

            stm.setString(1, c.class_name);
            stm.setString(3, c.class_id);
            stm.setInt(2, c.capacity);
            int rowsAffected = stm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public boolean isDuplicated(String name) {
        try {
            String query = "SELECT * FROM Class WHERE class_name = ?;";
            stm = cnn.prepareStatement(query);
            stm.setString(1, name);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<Classes> getClassByName(String class_name) {
        ArrayList<Classes> data = new ArrayList<>();
        try {
            String strSQL = "select * from Class  where class_name like ? ";
            stm = cnn.prepareStatement(strSQL);
            class_name = "%" + class_name + "%";
            stm.setString(1, class_name);
            rs = stm.executeQuery();
            while (rs.next()) {

                String class_id = rs.getString(1);
                String Class_name = rs.getString(2);
                int capacity = rs.getInt(3);
                Classes c = new Classes(class_id, Class_name, capacity);
                data.add(c);
            }

        } catch (Exception e) {
            System.out.println("getClassByName:" + e.getMessage());
        }
        return data;
    }
    public static void main(String[] args) {
        ClassDAO dao = new ClassDAO();
        if(dao.isDuplicated("IS1701")){
            System.out.println("1");
        }
    }
}
