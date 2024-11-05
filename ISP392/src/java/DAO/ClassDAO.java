/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FPTSHOP
 */
public class ClassDAO {

    public List<Class> findAllClasses() {
        List<Class> classList = new ArrayList<>();
        String sql = "SELECT * FROM Class";
        DBContext dbContext = new DBContext(); // Khởi tạo đối tượng DBContext

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Class clazz = Class.builder()
                        .ID(rs.getInt("class_id"))
                        .ClassName(rs.getString("class_name"))
                        .build();

                classList.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }

    public String getClassNameById(int classId) {
        String sql = "SELECT class_name FROM Class WHERE class_id = ?";
        DBContext dbContext = new DBContext();
        String className = null;

        try (Connection conn = dbContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                className = rs.getString("class_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return className;
    }
    
    public List<Integer> getAllClassIds() {
    List<Integer> classIds = new ArrayList<>();
    String sql = "SELECT class_id FROM Class";
    DBContext dbContext = new DBContext();

    try (Connection conn = dbContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            classIds.add(rs.getInt("class_id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return classIds;
}


    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();

        int classId = 1; // Thay thế bằng classId mong muốn để kiểm tra
//        String className = classDAO.getClassNameById(classId);
//
//        if (className != null) {
//            System.out.println("Class Name for ID " + classId + ": " + className);
//        } else {
//            System.out.println("No class found for ID " + classId);
//        }
classDAO.getAllClassIds().stream().forEach(item -> {System.out.println(item);});
    }
}
