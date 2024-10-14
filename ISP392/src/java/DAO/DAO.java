/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Model.Notifications;
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

    public static void main(String[] args) {
        DAO dao = new DAO();
        // Lấy thông báo cho vai trò "admin"
        List<Notifications> adminNotifications = dao.getNotificationsByRole("lecturer");
        // In ra danh sách thông báo
        for (Notifications notification : adminNotifications) {
            System.out.println(notification);
        }
    }

}
