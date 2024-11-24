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
import java.sql.Timestamp;
import java.util.ArrayList;
import models.Notification;
import models.NotificationDAO;

/**
 *
 * @author admin
 */
public class NotificationController extends HttpServlet {
   
    
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        NotificationDAO d = new NotificationDAO();
        
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            request.getRequestDispatcher("UploadNotification.jsp").forward(request, response);
        }
        
        ArrayList<Notification> data = d.getNotification();
        request.setAttribute("data", data);
        request.getRequestDispatcher("ViewNotification.jsp").forward(request, response);
        
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
//        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String role = request.getParameter("role");
        
        NotificationDAO d = new NotificationDAO();
        if (request.getParameter("upload") != null) {
            d.upload(title, content, role,new Timestamp(System.currentTimeMillis()));
        }
        
        doGet(request, response);
        
    }

   

}
