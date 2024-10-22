/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.curriculum;

import DAO.MajorDAO;
import Model.Curriculum;
import Model.Major;
import Model.Subjects;
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
@WebServlet(name = "CreateCurriculum", urlPatterns = {"/createCurriculum"})
public class CreateCurriculum extends HttpServlet {

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
            out.println("<title>Servlet CreateCurriculum</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCurriculum at " + request.getContextPath() + "</h1>");
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

        MajorDAO mdao = new MajorDAO();
        List<Curriculum> listCurriculum = mdao.getListCurriculum();
        request.setAttribute("listCurriculum", listCurriculum);

        List<Subjects> listAllSubject1 = mdao.getAllSubjects();
        
        request.setAttribute("listAllSubject1", listAllSubject1);
       

        List<Major> listMajor = mdao.getAllMajor();
        request.setAttribute("listMajor", listMajor);

        request.getRequestDispatcher("curriculum/createCurriculum.jsp").forward(request, response);
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
        // Lấy dữ liệu từ form
        String major = request.getParameter("major");
        String ss = request.getParameter("subject");
        String conditionSubject1 = request.getParameter("conditionSubject1");
        String conditionSubject2 = request.getParameter("conditionSubject2");
        String semester = request.getParameter("semester");
        String credits = request.getParameter("credits");

        // Kiểm tra xem dữ liệu có hợp lệ không
        if (major != null && conditionSubject1 != null && conditionSubject2 != null && semester != null && credits != null) {
            try {

                int major1 = Integer.parseInt(major);
                int subject = Integer.parseInt(ss);
                int conditionSubject11 = Integer.parseInt(conditionSubject1);
                int conditionSubject21 = Integer.parseInt(conditionSubject2);
                int semester1 = Integer.parseInt(semester);
                int credits1 = Integer.parseInt(credits);

                MajorDAO mdao = new MajorDAO();

                boolean check = mdao.createCurriculum(major1, subject, conditionSubject21, conditionSubject21, semester1, credits1, credits1);
                if (check) {
                    request.setAttribute("mess", "Tạo thành công");
                } else {
                    request.setAttribute("error", "Tạo thất bại");

                }

            } catch (NumberFormatException e) {
                // Xử lý nếu có lỗi chuyển đổi kiểu dữ liệu
                request.setAttribute("error", "Dữ liệu nhập không hợp lệ.");

            }
        } else {
            // Nếu thiếu dữ liệu, hiển thị lỗi
            request.setAttribute("error", "Vui lòng điền đầy đủ các trường.");
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
