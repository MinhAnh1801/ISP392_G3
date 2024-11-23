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
import models.GradeStudentDAO;
import models.GradeStudent;

/**
 *
 * @author admin
 */
@WebServlet("/gradestudent")
public class GradeStudentController extends HttpServlet {
   
    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        GradeStudentDAO d = new GradeStudentDAO();
        
        ArrayList<GradeStudent> data = d.getGradeStudents();
        
        request.setAttribute("data", data);
        request.getRequestDispatcher("ViewGradeStudent.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String full_name = request.getParameter("full_name");
        String grade = request.getParameter("grade");
        
        GradeStudentDAO d = new GradeStudentDAO();
        doGet(request, response);
        
    }

     
}
