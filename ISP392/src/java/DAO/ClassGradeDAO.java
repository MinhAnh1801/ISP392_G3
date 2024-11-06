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
public class ClassGradeDAO extends DBContext {
    
    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 
    
    public ClassGradeDAO(){
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
    
    public ArrayList<Classes> getClassesName(){
        ArrayList<Classes> data = new ArrayList<>();
        try {
            String strSQL = "SELECT class_name FROM Class";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String class_name = rs.getString(1);
                Classes c = new Classes(class_name);
                data.add(c);
            }
        } catch (Exception e) {
            System.out.println("getClassName: " + e.getMessage());
        }
        return data;
    }
}
