/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import Model.GradeReport;
import DAL.GetGradeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
@WebServlet("/getgrade")
public class GetGradeController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       GetGradeDAO d = new GetGradeDAO();
        HttpSession session = request.getSession();
        int uid = (int)session.getAttribute("user");
       int subject_id = Integer.parseInt(request.getParameter("subject_id"));
       ArrayList<GradeReport> data = d.getGradeReport(uid, subject_id);
       request.setAttribute("subjectcode", data.get(1).getSubject_name());
       request.setAttribute("data", data);
       request.getRequestDispatcher("displayGrade.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        double grade = Double.parseDouble(request.getParameter("grade"));
        String uploadDate = request.getParameter("uploadDate");
        String comments = request.getParameter("comments");
        int type = Integer.parseInt(request.getParameter("type"));
        int percent = Integer.parseInt(request.getParameter("percent"));
        
        
        
        GradeReport d = new GradeReport(type, grade, uploadDate, comments, type, percent);
        doGet(request, response);
       
    }
    
}
