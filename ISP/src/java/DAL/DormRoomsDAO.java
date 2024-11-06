package DAL;

import Model.DormRooms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DormRoomsDAO extends DBcontext {

    public List<DormRooms> getAllDormRooms() {
        List<DormRooms> dormRooms = new ArrayList<>();
        String query = "SELECT * FROM DormRooms";

        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DormRooms dormRoom = new DormRooms();
                dormRoom.setId(rs.getInt("id"));
                dormRoom.setRoomNumber(rs.getString("room_number"));
                dormRoom.setCapacity(rs.getInt("capacity"));
                dormRoom.setAvailableCapacity(rs.getInt("available_capacity"));
                dormRoom.setBuilding(rs.getString("building"));
                dormRoom.setRoomType(rs.getString("room_type"));
                dormRooms.add(dormRoom);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving dorm rooms: " + e.getMessage());
        }
        return dormRooms;
    }

    public DormRooms getDormRoomById(int id) {
        DormRooms dormRoom = null;
        String query = "SELECT * FROM DormRooms WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dormRoom = new DormRooms();
                dormRoom.setId(rs.getInt("id"));
                dormRoom.setRoomNumber(rs.getString("room_number"));
                dormRoom.setCapacity(rs.getInt("capacity"));
                dormRoom.setAvailableCapacity(rs.getInt("available_capacity"));
                dormRoom.setBuilding(rs.getString("building"));
                dormRoom.setRoomType(rs.getString("room_type"));
                dormRoom.setDetail(rs.getString("detail")); 
                dormRoom.setPrice(rs.getInt("price"));   
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return dormRoom;
    }

    public void insertDormRoom(DormRooms dormRoom) {
        String query = "INSERT INTO DormRooms (room_number, capacity, available_capacity, building, room_type, detail, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dormRoom.getRoomNumber());
            ps.setInt(2, dormRoom.getCapacity());
            ps.setInt(3, dormRoom.getAvailableCapacity());
            ps.setString(4, dormRoom.getBuilding());
            ps.setString(5, dormRoom.getRoomType());
            ps.setString(6, dormRoom.getDetail()); 
            ps.setDouble(7, dormRoom.getPrice());  

            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    public void insertDormTemp(DormRooms dormRoom) {
        String query = "INSERT INTO Dorm_Temp (room_number, capacity, available_capacity, building, room_type, detail, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dormRoom.getRoomNumber());
            ps.setInt(2, dormRoom.getCapacity());
            ps.setInt(3, dormRoom.getAvailableCapacity());
            ps.setString(4, dormRoom.getBuilding());
            ps.setString(5, dormRoom.getRoomType());
            ps.setString(6, dormRoom.getDetail()); 
            ps.setDouble(7, dormRoom.getPrice());  

            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateDormRoom(DormRooms dormRoom) {
        String query = "UPDATE DormRooms SET room_number = ?, capacity = ?, available_capacity = ?, building = ?, room_type = ?, detail = ?, price = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dormRoom.getRoomNumber());
            ps.setInt(2, dormRoom.getCapacity());
            ps.setInt(3, dormRoom.getAvailableCapacity());
            ps.setString(4, dormRoom.getBuilding());
            ps.setString(5, dormRoom.getRoomType());
            ps.setString(6, dormRoom.getDetail()); 
            ps.setDouble(7, dormRoom.getPrice()); 
            ps.setInt(8, dormRoom.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public void deleteDormRoom(int id) {
        String query = "DELETE FROM DormRooms WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isRoomNumberExists(String roomNumber) {
        String query = "SELECT COUNT(*) FROM DormRooms WHERE room_number = ?";
        try  {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, roomNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public ArrayList<DormRooms> searchDormRooms(String keyword) {
    ArrayList<DormRooms> dormRooms = new ArrayList<>();
    try {
        String query = "SELECT * FROM DormRooms WHERE room_number LIKE ? OR building LIKE ?";
        PreparedStatement ps = connection.prepareStatement(query);
        keyword = "%" + keyword + "%";
        ps.setString(1, keyword);
        ps.setString(2, keyword);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String roomNumber = rs.getString("room_number");
            int capacity = rs.getInt("capacity");
            int availableCapacity = rs.getInt("available_capacity");
            String building = rs.getString("building");
            String roomType = rs.getString("room_type");
            int price = rs.getInt("price");
            String detail = rs.getString("detail");

            DormRooms room = new DormRooms(id, roomNumber, capacity, availableCapacity, building, roomType, detail, price);
            dormRooms.add(room);
        }
    } catch (Exception e) {
        System.out.println("searchDormRooms: " + e.getMessage());
    }
    return dormRooms;
}

public List<DormRooms> getAvailableDormRooms() {
        List<DormRooms> availableDormRooms = new ArrayList<>();
        String query = "SELECT * FROM DormRooms WHERE available_capacity > 0";

        try {
             PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String roomNumber = rs.getString("room_number");
                int capacity = rs.getInt("capacity");
                int availableCapacity = rs.getInt("available_capacity");
                String building = rs.getString("building");
                String roomType = rs.getString("room_type");
                int price = rs.getInt("price");
                String detail = rs.getString("detail");

            DormRooms room = new DormRooms(id, roomNumber, capacity, availableCapacity, building, roomType, detail, price);
                availableDormRooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return availableDormRooms;
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




     public static void main(String[] args) {
        // Assuming DormRoomsDAO is the class that contains getAvailableDormRooms()
        DormRoomsDAO dao = new DormRoomsDAO(); 

        // Fetch available dorm rooms
        List<DormRooms> availableDormRooms = dao.getAvailableDormRooms();

        // Print out each available dorm room
        if (availableDormRooms.isEmpty()) {
            System.out.println("No available dorm rooms.");
        } else {
            System.out.println("Available Dorm Rooms:");
            for (DormRooms room : availableDormRooms) {
                System.out.println("Room ID: " + room.getId());
                System.out.println("Room Number: " + room.getRoomNumber());
                System.out.println("Capacity: " + room.getCapacity());
                System.out.println("Available Capacity: " + room.getAvailableCapacity());
                System.out.println("Building: " + room.getBuilding());
                System.out.println("Room Type: " + room.getRoomType());
                System.out.println("Price: " + room.getPrice());
                System.out.println("Detail: " + room.getDetail());
                System.out.println("--------------------------");
            }
        }
    }
}
