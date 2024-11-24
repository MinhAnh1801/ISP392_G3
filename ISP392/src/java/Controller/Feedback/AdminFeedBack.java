/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Feedback;

import DAO.FeedBackDAO;
import Model.FeedbackForms;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
@WebServlet(name = "AdminFeedBack", urlPatterns = {"/adminFeedBack"})
public class AdminFeedBack extends HttpServlet {

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
            out.println("<title>Servlet AdminFeedBack</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminFeedBack at " + request.getContextPath() + "</h1>");
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
        FeedBackDAO fdao = new FeedBackDAO();

        List<FeedbackForms> listFeedbackForms = fdao.getAllFeedbackForms();
        request.setAttribute("listFeedbackForms", listFeedbackForms);
        request.getRequestDispatcher("FeedBack/AdminFeedBack.jsp").forward(request, response);

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
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)) {
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
            String endDateString = request.getParameter("endDate");
            String startDateString = request.getParameter("startDate");

            // Chuyển đổi endDate từ String thành Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = null;
            Date startDate = null;

            try {
                endDate = sdf.parse(endDateString);
                startDate = sdf.parse(startDateString);

                if (startDate == null || endDate == null || !endDate.after(startDate)) {
                    request.setAttribute("error", "End date must be greater than Start date.");
                    doGet(request, response);
                    return;
                }

            } catch (ParseException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu ngày không hợp lệ
            }

            // Tiến hành cập nhật trong database (ví dụ, gọi phương thức updateFeedbackEndDate())
            if (endDate != null) {
                FeedBackDAO feedbackDAO = new FeedBackDAO();
                boolean check = feedbackDAO.updateFeedbackEndDate(feedbackId, endDate);

                if (check) {
                    request.setAttribute("mess", "Update success");

                } else {
                    request.setAttribute("error", "Update false");

                }
            } else {

                request.setAttribute("error", "Error: Invalid date format");
            }
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
