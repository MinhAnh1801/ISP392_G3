/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.StudentClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class StudentClassDAO extends DBcontext {

    public List<StudentClass> getStudentsInClass(String classname) {
        List<StudentClass> students = new ArrayList<>();
        String query = "SELECT distinct s.student_id, sp.full_name FROM StudentClass s JOIN Student_Profile sp on s.student_id = sp.student_id \n"
                + "					Join Class c on s.class_id =  c.class_id where c.class_name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, classname);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StudentClass studentClass = new StudentClass();
                studentClass.setStudentId(rs.getString("student_id"));
                studentClass.setStudentName(rs.getString("full_name"));
                students.add(studentClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) {
        StudentClassDAO studentClassDAO = new StudentClassDAO();
        String classId = "3"; // ID lớp mà bạn muốn xem sinh viên
        List<StudentClass> studentList = studentClassDAO.getStudentsInClass(classId);

        // Hiển thị danh sách sinh viên
        if (studentList.isEmpty()) {
            System.out.println("No students found in class " + classId);
        } else {
            System.out.println("Students in class " + classId + ":");
            for (StudentClass studentClass : studentList) {
                System.out.println("ID: " + studentClass.getStudentId() + ", Name: " + studentClass.getStudentName());
            }
        }
    }
}
