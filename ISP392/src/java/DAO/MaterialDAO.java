/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Grades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.GuideDetails;
import Model.Guidelines;
import Model.Lecturer_Profile;
import Model.Materials;
import Model.Student_Profile;
import Model.Subjects;
import Model.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MaterialDAO extends DBContext {

    private final Connection connection = getConnection();

    
      public List<Materials> getALLMaterials() {
        List<Materials> materialsList = new ArrayList<>();
        String sql = " SELECT TOP (1000) \n" +
"    m.[id],\n" +
"    m.[subject_id],\n" +
"    s.[code] AS subject_code,\n" +
"    m.[material_name],\n" +
"    m.[material_file],\n" +
"    m.[uploaded_at],\n" +
"    m.[uploaded_by],\n" +
"    m.[description]\n" +
"FROM [dbo].[Materials] m\n" +
"LEFT JOIN [TEST2].[dbo].[Subjects] s\n" +
"    ON m.[subject_id] = s.[id]\n" +
"ORDER BY \n" +
"    m.[id] DESC;";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Materials material = new Materials();
                material.setId(resultSet.getInt("id"));
                material.setMaterialName(resultSet.getString("material_name"));
                material.setMaterial_file(resultSet.getString("material_file"));

                UserDAO udao = new UserDAO();
                Lecturer_Profile lecturer = udao.getLecturerProfileById(resultSet.getInt("uploaded_by"));
                material.setUpload_by(lecturer);

                material.setUploaded_at(resultSet.getDate("uploaded_at"));

                material.setDescription(resultSet.getString("description"));
                
                material.setSubjectCode(resultSet.getString("subject_code"));
                materialsList.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialsList;
    }
    
    
    public List<Materials> getALLMaterials(int sid) {
        List<Materials> materialsList = new ArrayList<>();
        String sql = "Select m.*,s.code from Materials m join Subjects s on m.subject_id = s.id\n"
                + "where m.subject_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Materials material = new Materials();
                material.setId(resultSet.getInt("id"));
                material.setMaterialName(resultSet.getString("material_name"));
                material.setMaterialFile(resultSet.getString("material_file"));
                material.setSubjectCode(resultSet.getString("code"));
                UserDAO udao = new UserDAO();
                String lecturer = udao.getLecturerProfileById(resultSet.getInt("uploaded_by")).getFullName();
                material.setUploaded_by(lecturer);
                Timestamp uploadTime = resultSet.getTimestamp("uploaded_at");
                // Định dạng thời gian thành yyyy-MM-dd HH:mm
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedUploadTime = sdf.format(uploadTime);
                material.setUploadedAt(formattedUploadTime);

                material.setDescription(resultSet.getString("description"));
                materialsList.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialsList;
    }
    
    
    
      public boolean saveMaterial(Materials material) {
        String sql = "INSERT INTO [dbo].[Materials]([material_name], [description], [subject_id], [uploaded_by], [material_file], [uploaded_at]) VALUES (?, ?, ?, ?, ?, GETDATE())";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            ps.setString(1, material.getMaterial_name());
            ps.setString(2, material.getDescription());
            ps.setInt(3, material.getSubjectId().getId());
            ps.setInt(4, material.getUpload_by().getLecturerId());
            ps.setString(5, material.getMaterial_file());

            // Thực thi câu lệnh SQL và kiểm tra kết quả
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu insert thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }


    public static void main(String[] args) {
        MaterialDAO dao = new MaterialDAO();

         List<Materials> materialsList = dao.getALLMaterials();
         for (Materials materials : materialsList) {
             System.out.println(materials.getMaterialName());
             System.out.println(materials.getMaterial_file());
             System.out.println(materials.getUpload_by().getFullName());
             System.out.println(materials.getUploaded_at());
             System.out.println(materials.getDescription());
             System.out.println(materials.getSubjectCode());
        }
    }

}
