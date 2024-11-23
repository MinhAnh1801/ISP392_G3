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

    public static void main(String[] args) {
        MaterialDAO dao = new MaterialDAO();
        System.out.println(dao.getALLMaterials(1));
    }

}
