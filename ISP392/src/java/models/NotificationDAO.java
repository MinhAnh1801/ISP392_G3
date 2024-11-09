/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class NotificationDAO extends DBContext {
    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;
    
    public NotificationDAO() {
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
    
    public ArrayList<Notification> getNotification() {
        ArrayList<Notification> data = new ArrayList<Notification>();
        try {
            String strSQL = "select*from Notifications";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String role = rs.getString(4);

                Notification n = new Notification(id, title, content, role);
                data.add(n);
            }

        } catch (Exception e) {
            System.out.println("getNotification:" + e.getMessage());
        }

        return data;
    }
    
    public void upload(String title, String content, String role,Timestamp uploadedAt){
        try {
            String strSQL="insert into Notifications(title,content,role,upload_time)"
                    +"values(?,?,?,?)";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, title);
            stm.setString(2, content);
            stm.setString(3, role);
            stm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));  // Set current timestamp
            
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("upload notice: "+e.getMessage());
        }
    }
}
