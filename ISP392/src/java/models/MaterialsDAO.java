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
import models.Materials;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author khucx
 */
public class MaterialsDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public MaterialsDAO() {
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

    public ArrayList<Materials> getMaterials() {
        ArrayList<Materials> data = new ArrayList<Materials>();
        try {
            String strSQL = "SELECT \n"
                    + "    m.id, \n"
                    + "    m.material_name AS materialName,\n"
                    + "    m.material_file AS materialFile,\n"
                    + "    m.uploaded_at AS uploadAt,\n"
                    + "    m.description,\n"
                    + "    s.code AS subjectCode\n"
                    + "FROM \n"
                    + "    Materials m\n"
                    + "JOIN \n"
                    + "    Subjects s ON m.subject_id = s.id;";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String materialName = rs.getString(2);
                String materialFile = rs.getString(3);
                String uploadAt = rs.getString(4);
                String description = rs.getString(5);
                String subjectCode = rs.getString(6);

                Materials m = new Materials(id, subjectCode, materialName,
                        materialFile, uploadAt, description);
                data.add(m);
            }
        } catch (Exception e) {
            System.out.println("getMaterials:" + e.getMessage());
        }
        return data;
    }
    
    public int getsId(String scode){
        int sId = -1;
        String query = "select id from subjects where code = ?";
        try {
            PreparedStatement stm = cnn.prepareStatement(query);
            stm.setString(1, scode);
            rs = stm.executeQuery();
            if(rs.next()){
                sId = rs.getInt("id");
            }
        } catch (Exception e) {
            return -1;
        }
        return sId;
    }
    
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Delete error: ID is null or empty.");
            return;
        }

        try {
            String strSQL = "DELETE FROM Materials WHERE id = ?";
            PreparedStatement stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, Integer.parseInt(id));
            stm.executeUpdate(); // Sử dụng executeUpdate thay vì executeQuery cho DELETE
            System.out.println("Material with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            System.out.println("delete materials:" + e.getMessage());
        }
    }

    public Materials getMaterialsById(int id) {
        Materials m = new Materials();
        try {
            String strSQL = "SELECT m.id, s.code AS subject_code, m.material_name, m.material_file, m.uploaded_at, m.description\n"
                    + "                FROM Materials m \n"
                    + "                JOIN Subjects s ON m.subject_id = s.id\n"
                    + "                JOIN Lecturer_Profile lp ON m.uploaded_by = lp.lecturer_id\n"
                    + "                WHERE m.id = ? ORDER BY uploaded_at";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                String materialName = rs.getString("material_name");
                String materialFile = rs.getString("material_file");
                String uploadAt = rs.getString("uploaded_at");
                String description = rs.getString("description");
                String subjectCode = rs.getString("subject_code");

                m = new Materials(id, subjectCode, materialName, materialFile,
                        uploadAt, description);
            }

        } catch (Exception e) {
            System.out.println("getMaterialsById: " + e.getMessage());
        }

        return m;
    }

// Phương thức update
    public void update(int id,String m_name, String mfile, String des,int sid) {
        try {
            String strSQL = "UPDATE Materials SET\n" +
"                    material_name = ?,\n" +
"                    material_file = ?,\n" +
"                    description = ?,\n" +
"                    subject_id = ?\n" +
"                    WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(strSQL);

            stm.setString(1, m_name);
            stm.setString(2, mfile);
            stm.setString(3, des);
            stm.setInt(4, sid);
            stm.setInt(5, id);

            int rowsAffected = stm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (Exception e) {
            System.out.println("update material: " + e.getMessage());
        }
    }

    // Method to retrieve materials uploaded by a specific lecturer (based on lecturer_id)
    public List<Materials> getMaterialsByLecturerId(int lecturerId) {
        List<Materials> materialsList = new ArrayList<>();
        String query = "SELECT m.id, s.code AS subject_code, m.material_name, m.material_file, m.uploaded_at, m.description "
                + "FROM Materials m "
                + "JOIN Subjects s ON m.subject_id = s.id "
                + "JOIN Lecturer_Profile lp ON m.uploaded_by = lp.lecturer_id "
                + "WHERE m.uploaded_by = ? ORDER BY uploaded_at";

        try (PreparedStatement ps = cnn.prepareStatement(query)) {

            // Set the lecturer_id as the parameter
            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String subjectCode = rs.getString("subject_code");
                String materialName = rs.getString("material_name");
                String materialFile = rs.getString("material_file");
                String description = rs.getString("description");
                Timestamp uploadTime = rs.getTimestamp("uploaded_at");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedUploadTime = sdf.format(uploadTime);
                // Create a new Material object and add it to the list
                Materials material = new Materials(id, subjectCode, materialName, materialFile, formattedUploadTime, description);
                materialsList.add(material);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return materialsList;
    }

    public List<Subjects> getAllSubjectCodes() {
        List<Subjects> subjectList = new ArrayList<>();
        String query = "SELECT id, code FROM Subjects";

        try (PreparedStatement ps = cnn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            // Process the result set
            while (rs.next()) {
                int subjectId = rs.getInt("id");
                String code = rs.getString("code");

                // Create a new Subject object and add it to the list
                Subjects subject = new Subjects(subjectId, code);
                subjectList.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjectList;
    }

    public static void main(String[] args) {
        MaterialsDAO dao = new MaterialsDAO();
        System.out.println(dao.getsId("CSI"));
    }
}
