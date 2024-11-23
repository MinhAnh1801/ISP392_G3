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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.ClassGradeDAO;
import models.Classes;
import models.Subjects;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
@WebServlet("/classgrade")
public class ClassGradeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassGradeDAO d = new ClassGradeDAO();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        int lecturerId = (int)session.getAttribute("user");
        List<Subjects> subjects = d.getClassesName(lecturerId);
        request.setAttribute("data", subjects);
        request.getRequestDispatcher("ClassGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String class_name = request.getParameter("class_name");
        ClassGradeDAO d = new ClassGradeDAO();
        doGet(request, response);

    }

}
