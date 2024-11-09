package DAL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DAL.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.GradeReport;

/**
 *
 * @author admin
 */
public class GetGradeDAO extends DBcontext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;

    public GetGradeDAO() {
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

    public ArrayList<GradeReport> getGradeReport(int student_id, int subject_id) {
        ArrayList<GradeReport> data = new ArrayList<GradeReport>();
        try {
            String strSQL = "select *\n"
                    + "from Grades g join typeGrade tg on g.type = tg.id\n"
                    + "join PercentOptions p on g.percentId = p.percentId\n"
                    + "join Subjects s on s.id = g.subject_id\n"
                    + "where student_id = ?  and subject_id = ?";
            stm = cnn.prepareStatement(strSQL);
            stm.setInt(1, student_id);
            stm.setInt(2, subject_id);
            rs = stm.executeQuery();

            while (rs.next()) {
                double grade = rs.getDouble("grade");
                String uploadDate = rs.getString("upload_date");
                String comment = rs.getString("comments");
                int type = rs.getInt("type");
                int percent = rs.getInt("percentId");
                String type_name = rs.getString("type_name");
                float percent_value = rs.getFloat("percent_value");
                double gradecalculate = grade * percent_value / 100;
                String subject_name = rs.getString("code");
                GradeReport gr = new GradeReport(type, gradecalculate, uploadDate,
                        comment, type, percent, type_name,
                        percent_value,subject_name);

                data.add(gr);

            }

        } catch (Exception e) {

        }
        return data;
    }

    public static void main(String[] args) {
        GetGradeDAO gr = new GetGradeDAO();
        System.out.println(gr.getGradeReport(2, 1));
    }

}
