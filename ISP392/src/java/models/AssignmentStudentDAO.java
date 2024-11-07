/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author admin
 */
public class AssignmentStudentDAO extends DBContext {

    Connection cnn;//ket noi DB
    PreparedStatement stm;//thuc hien cac cau lenh sql
    ResultSet rs;//dung de luu tru va xu ly du lieu lay ve tu select 

    public AssignmentStudentDAO() {
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

    public ArrayList<AssignmentStudent> getAssignmentStudents() {
        ArrayList<AssignmentStudent> data = new ArrayList<AssignmentStudent>();
        try {
            String strSQL = "SELECT \n"
                    + "    asub.id,\n"
                    + "    asub.assignment_id,\n"
                    + "    sp.full_name,\n"
                    + "    asub.submission_content,\n"
                    + "    asub.submission_date,\n"
                    + "    asub.grade,\n"
                    + "    a.assignment_description\n"
                    + "FROM \n"
                    + "    assignment_submissions asub\n"
                    + "JOIN \n"
                    + "    Student_Profile sp ON asub.student_id = sp.student_id\n"
                    + "JOIN \n"
                    + "    assignments a ON asub.assignment_id = a.assignment_id\n"
                    + "where asub.class_id = 6;	\n"
                    + "	;";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                int assignmentId = rs.getInt(2);
                String studentName = rs.getString(3);
                String submissionContent = rs.getString(4);
//                Timestamp subdate = rs.getTimestamp(5);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
//                String submissiondate = sdf.format(subdate);
                String submissionDate = rs.getString(5);
                double grade = rs.getDouble(6);
                String comment = rs.getString(7);

                AssignmentStudent as = new AssignmentStudent(id, assignmentId, studentName, submissionContent, comment, submissionDate, grade);
                data.add(as);
            }

        } catch (Exception e) {
            System.out.println("getStudentAssignment:" + e.getMessage());
        }

        return data;
    }

    public void update(AssignmentStudent as) {
        try {
            String strSQL = "UPDATE assignment_submissions SET grade = ? WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(strSQL);

            // Thiết lập các tham số
            stm.setDouble(1, as.getGrade());
            stm.setString(2, as.getId());

            // Thực thi câu lệnh
            int rowsUpdated = stm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful!");
            } else {
                System.out.println("No record updated.");
            }

        } catch (Exception e) {
            System.out.println("updateGrade: " + e.getMessage());
        }
    }

    public ArrayList<AssignmentStudent> getAssignmentsByClassName(String className) {
        ArrayList<AssignmentStudent> data = new ArrayList<>();
        try {
            String strSQL = "SELECT asub.id, asub.assignment_id, sp.full_name, asub.submission_content, "
                    + "asub.submission_date, asub.grade, a.assignment_description "
                    + "FROM assignment_submissions asub "
                    + "JOIN Student_Profile sp ON asub.student_id = sp.student_id "
                    + "JOIN assignments a ON asub.assignment_id = a.assignment_id "
                    + "JOIN Class c ON asub.class_id = c.class_id "
                    + "WHERE c.class_name = ?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, className);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                int assignmentId = rs.getInt(2);
                String studentName = rs.getString(3);
                String submissionContent = rs.getString(4); // Đường dẫn file
                String submissionDate = rs.getString(5);
                double grade = rs.getDouble(6);
                String comment = rs.getString(7);

                // Kiểm tra định dạng file
                boolean isValidFile = submissionContent != null
                        && (submissionContent.endsWith(".doc") || submissionContent.endsWith(".docx")
                        || submissionContent.endsWith(".xls") || submissionContent.endsWith(".xlsx"));

                // Nếu file hợp lệ, thêm vào danh sách, nếu không thì bỏ qua
                if (isValidFile) {
                    AssignmentStudent as = new AssignmentStudent(id, assignmentId, studentName, submissionContent, comment, submissionDate, grade);
                    data.add(as);
                } else {
                    // Nếu file không hợp lệ, bạn có thể đặt submissionContent là "File không hợp lệ" hoặc bỏ qua
                    AssignmentStudent as = new AssignmentStudent(id, assignmentId, studentName, "File không hợp lệ", comment, submissionDate, grade);
                    data.add(as);
                }
            }
        } catch (Exception e) {
            System.out.println("getAssignmentsByClassName: " + e.getMessage());
        }
        return data;
    }

}
