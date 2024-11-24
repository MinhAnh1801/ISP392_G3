/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Feedback;

import DAL.LecturerProfileDAO;
import DAO.FeedBackDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
import Model.Lecturer_Profile;
import Model.Subjects;
import Model.Subjects1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author trung
 */
@WebServlet(name = "CreateFeedback", urlPatterns = {"/createFeedback"})
public class CreateFeedback extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateFeedback at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("role");
        if (id == null || id != 0) {
            response.sendRedirect("login");
            return;

        }

        SubjectDAO sdao = new SubjectDAO();
        List<Subjects1> listSubject = sdao.getAllSubject();
        request.setAttribute("listSubject", listSubject);

        if (request.getParameter("subject_id") != null) {

            int subject_id = Integer.parseInt(request.getParameter("subject_id"));

            UserDAO udao = new UserDAO();
            List<Lecturer_Profile> listLecturerBySubject = udao.getListLecturerBySubject(subject_id);
            request.setAttribute("listLecturerBySubject", listLecturerBySubject);
            request.setAttribute("subject_id", subject_id);
        }

        request.getRequestDispatcher("FeedBack/CreateFeedback.jsp").forward(request, response);

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
        // Lấy thông tin từ form
        int subjectId = Integer.parseInt(request.getParameter("subject_id"));
        int lecturerId = Integer.parseInt(request.getParameter("lecturer_id"));
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");

        FeedBackDAO fdao = new FeedBackDAO();

        boolean check = fdao.createFeedback(subjectId, lecturerId, startDate, endDate);
        if (check) {
            request.setAttribute("mess", "Create Success");
        } else {
            request.setAttribute("error", "Create false");
        }

        doGet(request, response);
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
