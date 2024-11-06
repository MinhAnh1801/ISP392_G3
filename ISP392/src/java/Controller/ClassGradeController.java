/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.ClassGradeDAO;
import models.Classes;

/**
 *
 * @author admin
 */
public class ClassGradeController extends HttpServlet {
   
    
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       ClassGradeDAO d = new ClassGradeDAO();
       ArrayList<Classes> data = d.getClassesName();
       request.setAttribute("data", data);
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
