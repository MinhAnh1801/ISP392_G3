package DAL;

import Model.DormResident;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class DormResidentDAO extends DBcontext {

    public boolean addDormResident(DormResident dormResident) {
        String query = "INSERT INTO Dorm_Residents (student_id, dorm_room_id, check_in_date, check_out_date, status) VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, dormResident.getStudentId());
            ps.setInt(2, dormResident.getDormRoomId());
            ps.setDate(3, dormResident.getCheckInDate());
            ps.setDate(4, dormResident.getCheckOutDate());
            ps.setString(5, dormResident.getStatus());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error adding DormResident: " + e.getMessage());
            return false;
        }
    }
    public boolean addDormTemp(DormResident dormResident) {
        String query = "INSERT INTO Dorm_Temp (student_id, dorm_room_id, check_in_date, check_out_date, status) VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, dormResident.getStudentId());
            ps.setInt(2, dormResident.getDormRoomId());
            ps.setDate(3, dormResident.getCheckInDate());
            ps.setDate(4, dormResident.getCheckOutDate());
            ps.setString(5, dormResident.getStatus());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error adding DormResident: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addPayment(int userId, BigDecimal amount, String paymentType) {
    String query = "INSERT INTO Payments (user_id, amount, status, payment_type) VALUES (?, ?, 'Pending', ?)";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        ps.setBigDecimal(2, amount);
        ps.setString(3, paymentType);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error adding Payment: " + e.getMessage());
        return false;
    }
}

public boolean checkPaymentStatus(int userId, String paymentType) {
    String query = "SELECT TOP 1 status FROM Payments WHERE user_id = ? AND payment_type = ? ORDER BY payment_date DESC";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userId);
        ps.setString(2, paymentType);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String status = rs.getString("status");
            System.out.println("Payment status found: " + status); // Thêm dòng in kiểm tra
            return "Paid Successfully".equalsIgnoreCase(status);
        } else {
            System.out.println("No payment record found for user_id: " + userId + ", payment_type: " + paymentType);
        }
    } catch (SQLException e) {
        System.out.println("Error checking Payment status: " + e.getMessage());
    }
    return false;
}

public boolean transferDormTempToDormResident(int userId) {
    String transferQuery = "INSERT INTO dorm_residents (student_id, dorm_room_id, check_in_date, check_out_date, status) " +
                           "SELECT student_id, dorm_room_id, check_in_date, check_out_date, 'Active' " +
                           "FROM dorm_temp WHERE student_id = ?";
    String deleteQuery = "DELETE FROM Dorm_Temp WHERE student_id = ?";
    String updateCapacityQuery = "UPDATE DormRooms SET available_capacity = available_capacity - 1 " +
                                 "WHERE id = (SELECT dorm_room_id FROM dorm_temp WHERE student_id = ?)";

    try (PreparedStatement transferStmt = connection.prepareStatement(transferQuery);
         PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
         PreparedStatement updateCapacityStmt = connection.prepareStatement(updateCapacityQuery)) {

        transferStmt.setInt(1, userId);
        int rowsInserted = transferStmt.executeUpdate();

        if (rowsInserted > 0) {
            updateCapacityStmt.setInt(1, userId);
            updateCapacityStmt.executeUpdate();

            deleteStmt.setInt(1, userId);
            deleteStmt.executeUpdate();
            return true;
        }
    } catch (SQLException e) {
        System.out.println("Error transferring data: " + e.getMessage());
    }
    return false;
}



public BigDecimal getDormRoomPrice(int dormRoomId) {
    String query = "SELECT price FROM DormRooms WHERE id = ?";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, dormRoomId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBigDecimal("price");
        }
    } catch (SQLException e) {
        System.out.println("Error fetching Dorm Room price: " + e.getMessage());
    }
    return BigDecimal.ZERO; // Trả về 0 nếu có lỗi hoặc không tìm thấy
}

public boolean hasExistingDormRoom(int studentId) {
    String query = "SELECT COUNT(*) FROM Dorm_Residents WHERE student_id = ? AND status = 'Active'";
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Nếu số lượng > 0 nghĩa là đã có phòng
        }
    } catch (SQLException e) {
        System.out.println("Error checking existing dorm room: " + e.getMessage());
    }
    return false;
}
 public boolean decreaseAvailableCapacity(int dormRoomId) {
        String query = "UPDATE DormRooms SET available_capacity = available_capacity - 1 WHERE id = ? AND available_capacity > 0";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, dormRoomId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error decreasing available capacity: " + e.getMessage());
            return false;
        }
    }

    public DormResident getDormResidentByStudentId(int studentId) {
        DormResident dormResident = null;
        String query = "SELECT * FROM Dorm_Residents WHERE student_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dormResident = new DormResident();
                    dormResident.setStudentId(rs.getInt("student_id"));
                    dormResident.setDormRoomId(rs.getInt("dorm_room_id"));
                    dormResident.setCheckInDate(rs.getDate("check_in_date"));
                    dormResident.setCheckOutDate(rs.getDate("check_out_date"));
                    dormResident.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving dorm resident by student ID: " + e.getMessage());
        }
        
        return dormResident;
    }
    
   
    public static void main(String[] args) {
        DormResidentDAO dao = new DormResidentDAO();
        System.out.println(dao.checkPaymentStatus(3, "Dorm"));
    }
}


