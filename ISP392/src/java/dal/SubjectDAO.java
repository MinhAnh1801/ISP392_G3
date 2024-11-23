/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Dell
 */
import models.Subjects;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubjectDAO extends DBcontext {

    public List<Subjects> getAllSubjects() {
        List<Subjects> subjects = new ArrayList<>();
        String query = "SELECT major_name, s.id , code, name, description, tuition FROM Subjects s join Major m on s.major_id = m.id";

        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Subjects subject = new Subjects();
                subject.setId(rs.getString("id"));
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
                int number = rs.getInt("tuition");
                NumberFormat formatter = NumberFormat.getInstance(new Locale("de", "DE"));
                String formattedNumber = formatter.format(number);
                subject.setTu(formattedNumber);
                subject.setMajor_name(rs.getString("major_name"));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public static void main(String[] args) {
        SubjectDAO dao = new SubjectDAO();
        System.out.println(dao.getAllSubjects());
    }
}
