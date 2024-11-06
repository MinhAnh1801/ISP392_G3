/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LecturerController;

import FeedbackController.*;
import DormRoomController.*;
import DAL.DormRoomsDAO;
import DAL.FeedbackDAO;
import DAL.FeedbackQuestionDAO;
import DAL.LecturerProfileDAO;
import Model.DormRooms;
import Model.FeedbackQuestion;
import Model.LecturerProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Dell
 */
public class GetLecturerForFeedback extends HttpServlet {

    private LecturerProfileDAO lecturerProfileDAO;

    @Override
    public void init() throws ServletException {
        lecturerProfileDAO = new LecturerProfileDAO();
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
        int studentId = 2; // Thay đổi giá trị này theo cách bạn cần
        List<LecturerProfile> lecturers = lecturerProfileDAO.getLecturersByStudentId(studentId);

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        for (LecturerProfile lecturer : lecturers) {
            boolean hasFeedback = feedbackDAO.hasFeedback(studentId, lecturer.getLecturerId());
            lecturer.setHasFeedback(hasFeedback);
        }

        request.setAttribute("lecturers", lecturers);
        request.getRequestDispatcher("listLecturer.jsp").forward(request, response);
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
