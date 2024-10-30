/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Grade;
import models.GradeDAO;

/**
 *
 * @author admin
 */
public class GradeController extends HttpServlet {
   
    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            GradeDAO d = new GradeDAO();
            ArrayList<Grade> data = d.getGrades();
            
            if (request.getParameter("search") != null && !request.getParameter("student_id").isBlank()) {
            String student_id = request.getParameter("student_id");
            data = d.getGradeByStudentID(student_id);
        }
            request.setAttribute("data", data);
            request.getRequestDispatcher("ViewGrades.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            String student_id = request.getParameter("student_id");
            String subject_id = request.getParameter("subject_id");
            String grade = request.getParameter("grade");
            String upload_date = request.getParameter("upload_date");
            String comments = request.getParameter("comments");
            
            GradeDAO d = new GradeDAO();
            
            doGet(request, response);
        
    }

   

}
