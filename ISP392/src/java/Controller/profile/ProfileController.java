/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.profile;

import DAO.UserDAO;
import Model.Lecturer_Profile;
import Model.Profile;
import Model.Student_Profile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author trung
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
    // Lấy thông tin session
    HttpSession session = request.getSession();
    Integer userId = (Integer) session.getAttribute("user");
    Integer role = (Integer) session.getAttribute("role");

    // Kiểm tra session hợp lệ
    if (userId == null || role == null) {
        response.sendRedirect("login");
        return;
    }

    // Khởi tạo DAO
    UserDAO userDAO = new UserDAO();

    try {
        // Xử lý dựa trên vai trò
        if (role == 1) { // Role Student
            Student_Profile studentProfile = userDAO.getStudentProfile(userId);
            if (studentProfile == null) { // Trường hợp không tìm thấy profile
                response.sendRedirect("login");
                return;
            }
            request.setAttribute("studentProfile", studentProfile);
            request.getRequestDispatcher("/profile/viewStudentProfile.jsp").forward(request, response);

        } else if (role == 2) { // Role Lecturer
            Lecturer_Profile lecturerProfile = userDAO.getLecturerProfileById(userId);
            if (lecturerProfile == null) { // Trường hợp không tìm thấy profile
                response.sendRedirect("login");
                return;
            }
            request.setAttribute("lecturerProfile", lecturerProfile);
            request.getRequestDispatcher("/profile/viewLecturerProfile.jsp").forward(request, response);

        } else {
            // Trường hợp vai trò không hợp lệ
            response.sendRedirect("login");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("login"); // Chuyển hướng về login nếu có lỗi
    }
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
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("user");
        // check action null
        if (action.isEmpty()) {
            request.setAttribute("error", "action is empty");
        } // check update profile lecturer
        else if (action.equalsIgnoreCase("lecturer")) {

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String department = request.getParameter("department");
            String expertise = request.getParameter("expertise");
            int researchSkill = Integer.parseInt(request.getParameter("researchSkill"));
            int teachingSkill = Integer.parseInt(request.getParameter("teachingSkill"));
            int mentoringSkill = Integer.parseInt(request.getParameter("mentoringSkill"));

            UserDAO udao = new UserDAO();
            boolean updateProfile = udao.updateProfileLecturerById(id, fullName, email, phoneNumber, department,
                    expertise, researchSkill, teachingSkill, mentoringSkill);

            if (updateProfile) {
                request.setAttribute("mess", "Update Successes");
            } else {
                request.setAttribute("error", "Update False");
            }
        } else if (action.equalsIgnoreCase("student")) {
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String parentName = request.getParameter("parentName");
            String parentPhone = request.getParameter("parentPhone");
            String parentAddress = request.getParameter("parentAddress");
            String parentOccupation = request.getParameter("parentOccupation");
            String parentWorkplace = request.getParameter("parentWorkplace");

            UserDAO udao = new UserDAO();
            boolean updateProfile = udao.updateStudentProfile(id, phoneNumber, address, parentName, parentPhone, parentAddress, parentOccupation, parentWorkplace);

            if (updateProfile) {
                request.setAttribute("mess", "Cập nhật thành công");
            } else {
                request.setAttribute("error", "Cập nhật thất bại");
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
