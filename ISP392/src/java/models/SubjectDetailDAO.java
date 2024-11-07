package models;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectDetailDAO extends DBContext {

    Connection cnn; // kết nối đến DB
    PreparedStatement stm; // thực hiện các câu lệnh SQL
    ResultSet rs;

    public SubjectDetailDAO() {
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

    // Lấy tất cả các môn học với chi tiết của chúng
    public ArrayList<SubjectDetail> getSubjectDetails() {
        ArrayList<SubjectDetail> data = new ArrayList<>();
        try {
            String strSQL = "SELECT \n"
                    + "    s.name AS subjectName,\n"
                    + "    s.description AS subjectDescription,\n"
                    + "    s.credits AS credits,\n"
                    + "    m.material_name AS materialName,\n"
                    + "    m.material_file AS materialLink,\n"
                    + "    c.name AS classroomName,\n"
                    + "    sch.day_of_week AS dayOfWeek,\n"
                    + "    sch.start_time AS startTime,\n"
                    + "    sch.end_time AS endTime,\n"
                    + "    l.full_name AS lecturerName,\n"
                    + "    l.email AS lecturerEmail\n"
                    + "FROM \n"
                    + "    Subjects s\n"
                    + "LEFT JOIN \n"
                    + "    Materials m ON s.id = m.subject_id\n"
                    + "LEFT JOIN \n"
                    + "    Schedule sch ON s.id = sch.subject_id\n"
                    + "LEFT JOIN \n"
                    + "    Classrooms c ON sch.classroom_id = c.id\n"
                    + "LEFT JOIN \n"
                    + "    Lecturer_Profile l ON sch.id = l.lecturer_id;";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subjectName");
                String subjectDescription = rs.getString("subjectDescription");
                int credits = rs.getInt("credits");
                String materialName = rs.getString("materialName");
                String materialLink = rs.getString("materialLink");
                String classroomName = rs.getString("classroomName");
                String dayOfWeek = rs.getString("dayOfWeek");
                String startTime = rs.getString("startTime");
                String endTime = rs.getString("endTime");
                String lecturerName = rs.getString("lecturerName");
                String lecturerEmail = rs.getString("lecturerEmail");

                SubjectDetail sd = new SubjectDetail(
                        subjectName, subjectDescription, credits, materialName, materialLink,
                        classroomName, dayOfWeek, startTime, endTime, lecturerName, lecturerEmail
                );
                data.add(sd);
            }
        } catch (Exception e) {
            System.out.println("getSubjectDetails: " + e.getMessage());
        }
        return data;
    }

}
