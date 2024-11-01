package Controller.Admin;

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
}

