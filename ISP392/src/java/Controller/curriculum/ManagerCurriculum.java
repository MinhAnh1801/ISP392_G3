/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.curriculum;

import DAO.MajorDAO;
import Model.Curriculum;
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
@WebServlet(name = "ManagerCurriculum", urlPatterns = {"/managerCurriculum"})
public class ManagerCurriculum extends HttpServlet {

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
            out.println("<title>Servlet ManagerCurriculum</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerCurriculum at " + request.getContextPath() + "</h1>");
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

        List<Subjects1> listAllSubject1 = mdao.getSubjectByMajorId(1);
        List<Subjects1> listAllSubject2 = mdao.getSubjectByMajorId(2);
        List<Subjects1> listAllSubject3 = mdao.getSubjectByMajorId(3);
        request.setAttribute("listAllSubject1", listAllSubject1);
        request.setAttribute("listAllSubject2", listAllSubject2);
        request.setAttribute("listAllSubject3", listAllSubject3);

        request.getRequestDispatcher("curriculum/viewAllCurriculum.jsp").forward(request, response);
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
        MajorDAO mdao = new MajorDAO();

        if (request.getParameter("action") != null) {
            int majorId = Integer.parseInt(request.getParameter("majorId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));

            boolean check = mdao.deleteCurriculum(majorId, subjectId);

            if (check) {
                request.setAttribute("mess", "Delete success");
            } else {
                request.setAttribute("error", "Delete false");

            }
            doGet(request, response);
            return;
        }

        int majorId = Integer.parseInt(request.getParameter("majorId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int conditionSubject1 = Integer.parseInt(request.getParameter("conditionSubject1"));
        int conditionSubject2 = Integer.parseInt(request.getParameter("conditionSubject2"));
        int credits = Integer.parseInt(request.getParameter("credits"));

        boolean checkUpdate = mdao.updateByMajorIdSubjectId(majorId, subjectId, conditionSubject1, conditionSubject2, credits);

        if (checkUpdate) {
            request.setAttribute("mess", "Update success");
        } else {
            request.setAttribute("error", "Update false");

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
