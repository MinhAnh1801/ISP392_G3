package DAO;

import Context.DBContext;
import Model.ClassroomFacilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomFacilitiesDAO extends DBContext {

    public ClassroomFacilities findFacilitiesByClassroomId(int classroomID) {
        String sql = "SELECT * FROM ClassroomFacilities WHERE classroomID = ?";
        ClassroomFacilities facilities = null;

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classroomID);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    facilities = new ClassroomFacilities();
                    facilities.setClassroomID(rs.getInt("classroomID"));
                    facilities.setNumberOfTables(rs.getInt("numberOfTables"));
                    facilities.setTotalTables(rs.getInt("totalTables"));
                    facilities.setNumberOfChairs(rs.getInt("numberOfChairs"));
                    facilities.setTotalChairs(rs.getInt("totalChairs"));
                    facilities.setNumberOfLights(rs.getInt("numberOfLights"));
                    facilities.setTotalLights(rs.getInt("totalLights"));
                    facilities.setNumberOfProjectors(rs.getInt("numberOfProjectors"));
                    facilities.setTotalProjectors(rs.getInt("totalProjectors"));
                    facilities.setTableCondition(rs.getString("tableCondition"));
                    facilities.setChairCondition(rs.getString("chairCondition"));
                    facilities.setLightCondition(rs.getString("lightCondition"));
                    facilities.setProjectorCondition(rs.getString("projectorCondition"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facilities;
    }

    public void insert(ClassroomFacilities facilities) {
        String sql = "INSERT INTO ClassroomFacilities (classroomID, numberOfTables, totalTables, tableCondition, "
                + "numberOfChairs, totalChairs, chairCondition, numberOfLights, totalLights, lightCondition, "
                + "numberOfProjectors, totalProjectors, projectorCondition) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameters for the insert statement
            statement.setInt(1, facilities.getClassroomID());
            statement.setInt(2, facilities.getNumberOfTables());
            statement.setInt(3, facilities.getTotalTables());
            statement.setString(4, facilities.getTableCondition());
            statement.setInt(5, facilities.getNumberOfChairs());
            statement.setInt(6, facilities.getTotalChairs());
            statement.setString(7, facilities.getChairCondition());
            statement.setInt(8, facilities.getNumberOfLights());
            statement.setInt(9, facilities.getTotalLights());
            statement.setString(10, facilities.getLightCondition());
            statement.setInt(11, facilities.getNumberOfProjectors());
            statement.setInt(12, facilities.getTotalProjectors());
            statement.setString(13, facilities.getProjectorCondition());

            // Execute the insert
            statement.executeUpdate();
            System.out.println("New classroom facilities inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ClassroomFacilities facilities) {
        String sql = "UPDATE ClassroomFacilities SET "
                + "numberOfTables = ?, totalTables = ?, tableCondition = ?, "
                + "numberOfChairs = ?, totalChairs = ?, chairCondition = ?, "
                + "numberOfLights = ?, totalLights = ?, lightCondition = ?, "
                + "numberOfProjectors = ?, totalProjectors = ?, projectorCondition = ? "
                + "WHERE classroomID = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameters for the update statement
            statement.setInt(1, facilities.getNumberOfTables());
            statement.setInt(2, facilities.getTotalTables());
            statement.setString(3, facilities.getTableCondition());
            statement.setInt(4, facilities.getNumberOfChairs());
            statement.setInt(5, facilities.getTotalChairs());
            statement.setString(6, facilities.getChairCondition());
            statement.setInt(7, facilities.getNumberOfLights());
            statement.setInt(8, facilities.getTotalLights());
            statement.setString(9, facilities.getLightCondition());
            statement.setInt(10, facilities.getNumberOfProjectors());
            statement.setInt(11, facilities.getTotalProjectors());
            statement.setString(12, facilities.getProjectorCondition());
            statement.setInt(13, facilities.getClassroomID());

            // Execute the update
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Tạo đối tượng ClassroomFacilities với dữ liệu cần thêm mới
        ClassroomFacilities facilities = new ClassroomFacilities();
        facilities.setClassroomID(8); 
        facilities.setNumberOfTables(10);
        facilities.setTotalTables(15);
        facilities.setTableCondition("Good");
        facilities.setNumberOfChairs(20);
        facilities.setTotalChairs(25);
        facilities.setChairCondition("Fair");
        facilities.setNumberOfLights(8);
        facilities.setTotalLights(10);
        facilities.setLightCondition("Excellent");
        facilities.setNumberOfProjectors(1);
        facilities.setTotalProjectors(2);
        facilities.setProjectorCondition("Good");

        // Tạo đối tượng ClassroomFacilitiesDAO để gọi phương thức insert
        ClassroomFacilitiesDAO dao = new ClassroomFacilitiesDAO();
        dao.insert(facilities);

        // Kiểm tra xem dữ liệu đã được thêm thành công
        System.out.println("Insert completed!");
    }

}
