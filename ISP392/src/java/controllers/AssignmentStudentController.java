/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.AssignmentStudent;
import models.AssignmentStudentDAO;

/**
 *
 * @author admin
 */
@WebServlet("/assignment")
public class AssignmentStudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AssignmentStudentDAO d = new AssignmentStudentDAO();

        // Lấy class_name từ tham số yêu cầu
        String className = request.getParameter("class_name");
        String subjectName = request.getParameter("subject_name");
        // Nếu class_name có giá trị, lọc các assignment theo lớp
        if (className != null && !className.isEmpty()) {
            ArrayList<AssignmentStudent> data = d.getAssignmentsByClassName(className);
            request.setAttribute("data", data);
            request.setAttribute("className", className); // Truyền tên lớp vào để hiển thị
            request.setAttribute("subject_name", subjectName); // Truyền tên lớp vào để hiển thị
        }
        

        request.getRequestDispatcher("AssignmentStudentGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AssignmentStudentDAO dao = new AssignmentStudentDAO();

        // Lấy ID của sinh viên từ nút "Save" được nhấn
        String studentId = request.getParameter("save");

        if (studentId != null) {
            // Lấy điểm từ input grade_<studentId>
            String gradeParam = "grade_" + studentId;
            String gradeStr = request.getParameter(gradeParam);

            if (gradeStr != null && !gradeStr.isEmpty()) {
                double grade = Double.parseDouble(gradeStr);

                // Tạo đối tượng AssignmentStudent với thông tin cần thiết
                AssignmentStudent as = new AssignmentStudent();
                as.setId(studentId);
                as.setGrade(grade);

                // Cập nhật điểm cho sinh viên trong cơ sở dữ liệu
                dao.update(as);
            }
        }

        // Sau khi cập nhật, gọi doGet để lấy lại dữ liệu mới
        doGet(request, response);

    }
}
