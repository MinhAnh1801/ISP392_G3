/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Materials;
import Model.News;
import Model.Notifications;
import Model.Student_Profile;
import Model.Subjects;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import java.sql.Blob;

/**
 *
 * @author khucx
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            // Use DecimalFormat to format the number as a plain string (no scientific notation)
            DecimalFormat df = new DecimalFormat("0"); // No decimals
            return df.format(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            return "";
        }
    }

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "select * from Users";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public User checkLogin(String username, String password) {
        List<User> list = getAllUser();
        for (User u : list) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
    public List<Notifications> getNotificationsByRole(String role) {
        List<Notifications> notifications = new ArrayList<>();

        String sql = "SELECT TOP 9 id, title, content, role, upload_time "
                + "FROM Notifications "
                + "WHERE role = ? "
                + "ORDER BY upload_time DESC ";  // Sắp xếp theo thời gian mới nhất
        // Kết nối cơ sở dữ liệu
        try (Connection connection = new DBContext().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, role);  // Thiết lập giá trị cho tham số role

            // Thực thi câu truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();

            // Duyệt qua các kết quả truy vấn
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String notificationRole = resultSet.getString("role");
                Timestamp uploadTime = resultSet.getTimestamp("upload_time");
                // Định dạng thời gian thành yyyy-MM-dd HH:mm
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedUploadTime = sdf.format(uploadTime);
                // Tạo đối tượng Notification và thêm vào danh sách
                Notifications notification = new Notifications(id, title, content, notificationRole, formattedUploadTime);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Return true if count > 0, i.e., email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if email is not found or an exception occurs
    }

    public boolean resetPassword(String email, String newPassword) {
        // Check if the new password meets the 8-character limit
        if (newPassword == null || newPassword.length() < 8) {
            // If password is shorter than 8 characters, return false or handle error
            System.err.println("Password must be at least 8 characters long.");
            return false;
        }

        String sql = "UPDATE Users SET password = ? WHERE email = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the new password and the corresponding email
            ps.setString(1, newPassword);  // You may also hash the password here before storing
            ps.setString(2, email);

            // Execute the update
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // Return true if the password was updated successfully

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the update failed
    }

    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, img, content, upload_date FROM News ORDER BY upload_date DESC";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            // Loop through each result in the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String img = rs.getString("img");   // File name of the image
                String content = rs.getString("content");
                Timestamp uploadTime = rs.getTimestamp("upload_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedUploadTime = sdf.format(uploadTime);
                // Create a new News object
                News news = new News(id, title, img, content, formattedUploadTime);

                // Add the News object to the list
                newsList.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;
    }
    public List<News> getTop3News() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT TOP 3 id, title, img, content, upload_date FROM News ORDER BY upload_date DESC";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            // Loop through each result in the ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String img = rs.getString("img");   // File name of the image
                String content = rs.getString("content");
                Timestamp uploadTime = rs.getTimestamp("upload_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedUploadTime = sdf.format(uploadTime);
                // Create a new News object
                News news = new News(id, title, img, content, formattedUploadTime);

                // Add the News object to the list
                newsList.add(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;
    }

    // Method to retrieve materials uploaded by a specific lecturer (based on lecturer_id)
    public List<Materials> getMaterialsByLecturerId(int lecturerId) {
        List<Materials> materialsList = new ArrayList<>();
        String query = "SELECT m.id, s.code AS subject_code, m.material_name, m.material_file, m.uploaded_at, m.description "
                + "FROM Materials m "
                + "JOIN Subjects s ON m.subject_id = s.id "
                + "JOIN Lecturer_Profile lp ON m.uploaded_by = lp.lecturer_id "
                + "WHERE m.uploaded_by = ? ORDER BY uploaded_at";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

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

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

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

    // Method to add a new material to the database
    public boolean addMaterial(int subjectId, String materialName, String materialFile, Timestamp uploadedAt, int uploadedBy, String description) {
        String query = "INSERT INTO Materials (subject_id, material_name, material_file, uploaded_at, uploaded_by, description) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, subjectId);
            ps.setString(2, materialName);
            ps.setString(3, materialFile);
            ps.setTimestamp(4, uploadedAt);
            ps.setInt(5, uploadedBy);
            ps.setString(6, description);

            // Execute the insert statement
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Returns true if the insert was successful

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;  // Returns false if there was an error
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        // Lấy thông báo cho vai trò "admin"
        List<News> adminNotifications = dao.getTop3News();
        // In ra danh sách thông báo
        for (News notification : adminNotifications) {
            System.out.println(notification);
        }
    }

}