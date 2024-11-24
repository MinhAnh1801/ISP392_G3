/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import DAL.SubjectGradeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Subjects;

/**
 *
 * @author admin
 */
@WebServlet("/markreport")
public class SubjectReportController extends HttpServlet {

    private final SubjectGradeDAO d = new SubjectGradeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user = (int) session.getAttribute("user");

        ArrayList<Subjects> data = d.getSubjectForGradeReport(user);
        request.setAttribute("data", data);
        request.getRequestDispatcher("ViewListSubjectGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subject_name = request.getParameter("subject_name");
        SubjectGradeDAO d = new SubjectGradeDAO();
        doGet(request, response);

    }

}
