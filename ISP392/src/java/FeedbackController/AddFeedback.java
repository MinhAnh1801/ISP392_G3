/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FeedbackController;

import DAL.FeedbackDAO;
import DAL.FeedbackQuestionDAO;
import Model.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class AddFeedback extends HttpServlet {
    
    private FeedbackQuestionDAO feedbackQuestionDAO;
    
    @Override
    public void init() throws ServletException {
        feedbackQuestionDAO = new FeedbackQuestionDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewListDormRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewListDormRoom at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = 2; // Cập nhật giá trị này theo cách bạn cần
        int lecId = Integer.parseInt(request.getParameter("lecturerId"));
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbackList = new ArrayList<>();
        String[] feedbackQuestionIds = request.getParameterValues("feedbackQuestionIds");
        
        for (String feedbackQuestionIdStr : feedbackQuestionIds) {
            int feedbackQuestionId = Integer.parseInt(feedbackQuestionIdStr);
            String rating = request.getParameter("feedback_" + feedbackQuestionId);
            
            if (rating != null) {
                Feedback feedback = new Feedback();
                feedback.setRating(rating);
                feedback.setLecturerId(lecId);
                feedback.setStudentId(studentId);
                feedback.setFeedbackQuestionId(feedbackQuestionId);
                feedback.setStatus(true);
                feedbackList.add(feedback);
            }
        }
        
        for (Feedback feedback : feedbackList) {
            feedbackDAO.addFeedback(feedback);
        }
        response.sendRedirect(request.getContextPath() + "/viewLecturer");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
