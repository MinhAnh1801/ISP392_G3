/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package FeedbackController;

import DAL.FeedbackDAO;
import Model.Feedback;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class UpdateFeedback extends HttpServlet {
       private static final long serialVersionUID = 1L;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UpdateFeedback</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFeedback at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String feedbackIdStr = request.getParameter("id");
    log(request.getParameter("id"));
    int feedbackId = -1; // Đặt giá trị mặc định nếu không phân tích được số nguyên

    if (feedbackIdStr != null && !feedbackIdStr.isEmpty()) {
        try {
            feedbackId = Integer.parseInt(feedbackIdStr);
        } catch (NumberFormatException e) {
            System.err.println("Invalid feedbackId format: " + feedbackIdStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid feedback ID format");
            return; // Kết thúc nếu xảy ra lỗi
        }
    } else {
        System.err.println("Feedback ID is missing or empty");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Feedback ID is required");
        return; // Kết thúc nếu không có feedbackId
    }
        int id = Integer.parseInt(request.getParameter("feedbackId"));
        log(request.getParameter("feedbackId"));
        String rating = request.getParameter("rating");
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
        int feedbackQuestionId = Integer.parseInt(request.getParameter("feedbackQuestionId"));
        boolean status = request.getParameter("status") != null; // Kiểm tra checkbox

        // Tạo đối tượng Feedback với dữ liệu lấy được
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(id);
        feedback.setRating(rating);
        feedback.setStudentId(studentId);
        feedback.setLecturerId(lecturerId);
        feedback.setFeedbackQuestionId(feedbackQuestionId);
        feedback.setStatus(status);

        // Gọi FeedbackDAO để cập nhật dữ liệu
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        boolean isUpdated = feedbackDAO.updateFeedback(feedback);

        // Kiểm tra nếu cập nhật thành công
        if (isUpdated) {
            // Chuyển hướng đến trang danh sách giảng viên hoặc hiển thị thông báo thành công
            response.sendRedirect("listLecturer.jsp?updateSuccess=true"); // Thông báo thành công
        } else {
            // Nếu cập nhật thất bại, chuyển hướng đến trang lỗi hoặc thông báo lỗi
            request.setAttribute("errorMessage", "Update failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editFeedback.jsp");
            dispatcher.forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
