package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.SubjectDetail;
import models.SubjectDetailDAO;

public class SubjectDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDetailDAO d = new SubjectDetailDAO();
        ArrayList<SubjectDetail> data = d.getSubjectDetails();

       
        request.setAttribute("data", data);
        request.getRequestDispatcher("ViewSubjectDetail.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String subjectName = request.getParameter("subjectName");
        String subjectDescription = request.getParameter("subjectDescription");
        String credits = request.getParameter("credits");
        String materialName = request.getParameter("materialName");
        String materialLink = request.getParameter("materialLink");
        String classroomName = request.getParameter("classroomName");
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String lecturerName = request.getParameter("lecturerName");
        String lecturerEmail = request.getParameter("lecturerEmail");
        
        SubjectDetailDAO d = new SubjectDetailDAO();
              
        doGet(request, response);
    }
    
}
