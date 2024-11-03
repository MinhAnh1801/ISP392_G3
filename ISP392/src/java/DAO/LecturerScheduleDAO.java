package DAO;

import Context.DBContext;
import Model.Lecturer_Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class LecturerScheduleDAO {

    private static final String INSERT_WISHLIST
            = "INSERT INTO Lecturer_Wishlist (lecturer_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";

    // Method to insert work time wishlist for a lecturer
    public boolean addWishlist(int lecturerId, String dayOfWeek, String startTimeStr, String endTimeStr) {
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_WISHLIST)) {

            // Convert start and end time strings to SQL Time format
            ps.setInt(1, lecturerId);
            ps.setString(2, dayOfWeek);
            ps.setTime(3, java.sql.Time.valueOf(startTimeStr + ":00"));
            ps.setTime(4, java.sql.Time.valueOf(endTimeStr + ":00"));

            return ps.executeUpdate() > 0;  // Return true if the insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static final String GET_WISHLIST_BY_LECTURER_ID
            = "SELECT id, lecturer_id, day_of_week, start_time, end_time "
            + "FROM Lecturer_Wishlist WHERE lecturer_id = ?";

    // Method to fetch wishlist entries for a specific lecturer
    public List<Lecturer_Wishlist> getWishlistByLecturerId(int lecturerId) {
        List<Lecturer_Wishlist> wishlist = new ArrayList<>();

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(GET_WISHLIST_BY_LECTURER_ID)) {

            ps.setInt(1, lecturerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Lecturer_Wishlist entry = new Lecturer_Wishlist(
                            rs.getInt("id"),
                            rs.getInt("lecturer_id"),
                            rs.getString("day_of_week"),
                            rs.getString("start_time"),
                            rs.getString("end_time")
                    );
                    wishlist.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

}
