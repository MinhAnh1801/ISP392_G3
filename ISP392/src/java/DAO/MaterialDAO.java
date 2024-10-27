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

public class MaterialDAO extends DBContext {

    private final Connection connection = getConnection();

    public List<Materials> getALLMaterials() {
        List<Materials> materialsList = new ArrayList<>();
        String sql = "SELECT TOP (1000) [id]\n"
                + "      ,[subject_id]\n"
                + "      ,[material_name]\n"
                + "      ,[material_file]\n"
                + "      ,[uploaded_at]\n"
                + "      ,[uploaded_by]\n"
                + "      ,[description]\n"
                + "  FROM [TEST].[dbo].[Materials] ORDER BY \n"
                + "      [id] DESC;";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Materials material = new Materials();
                material.setId(resultSet.getInt("id"));
                material.setMaterial_name(resultSet.getString("material_name"));
                material.setMaterial_file(resultSet.getString("material_file"));

                UserDAO udao = new UserDAO();
                Lecturer_Profile lecturer = udao.getLecturerProfileById(resultSet.getInt("uploaded_by"));
                material.setUploaded_by(lecturer);

                material.setUploaded_at(resultSet.getDate("uploaded_at"));

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
            ps.setInt(4, material.getUploaded_by().getLecturerId());
            ps.setString(5, material.getMaterial_file());

            // Thực thi câu lệnh SQL và kiểm tra kết quả
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu insert thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

}
