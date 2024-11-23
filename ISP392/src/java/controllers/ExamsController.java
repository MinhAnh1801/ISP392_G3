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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Exams;
import models.ExamsDAO;

/**
 *
 * @author admin
 */
public class ExamsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    ExamsDAO d = new ExamsDAO();
        HttpSession session = request.getSession();
    if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            request.getRequestDispatcher("UploadExams.jsp").forward(request, response);
        }
    int uid = (int) session.getAttribute("user");
        ArrayList<Exams> data = d.getExams(uid);
        request.setAttribute("data", data);
        request.getRequestDispatcher("ViewExams.jsp").forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String subjectID = request.getParameter("subject_id");
        String exam_date = request.getParameter("exam_date");
        String start_time = request.getParameter("start_time");
        String end_time = request.getParameter("end_time");
        String exam_room = request.getParameter("exam_room");
        String exam_type = request.getParameter("exam_type");
        
        ExamsDAO d = new ExamsDAO();
        if (request.getParameter("upload")!=null) {
            d.upload(new Exams(id,subjectID,exam_date,start_time,end_time,
                    exam_room,exam_type));
        }
        
    }

    

}
