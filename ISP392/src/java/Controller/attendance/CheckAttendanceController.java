/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.attendance;

import DAO.AttendanceDAO;
import DAO.MajorDAO;
import Model.Attendance;
import Model.Classs;
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

@WebServlet(name = "CheckAttendanceController", urlPatterns = {"/checkAttendance"})
public class CheckAttendanceController extends HttpServlet {

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
            out.println("<title>Servlet CheckAttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckAttendanceController at " + request.getContextPath() + "</h1>");
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

        Integer user = (Integer) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AttendanceDAO adao = new AttendanceDAO();
        MajorDAO mdao = new MajorDAO();

        List<Subjects> listSubjectBylecture = mdao.getSubjectByTeacher(user);

        request.setAttribute("listSubjectBylecture", listSubjectBylecture);

        request.getRequestDispatcher("attendanceReport/checkAttendance.jsp").forward(request, response);
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

        HttpSession session = request.getSession();

        Integer user = (Integer) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("chooseSubject")) {

            request.removeAttribute("listAttendance");
            int subjectId = Integer.parseInt(request.getParameter("subjects"));
            MajorDAO mdao = new MajorDAO();
            List<Classs> listClassBySubjectId = mdao.getClassBySubjectId(subjectId);
            request.setAttribute("listClassBySubjectId", listClassBySubjectId);
            request.setAttribute("subjectId", subjectId);
        } else if (action.equalsIgnoreCase("chooseClass")) {
            request.removeAttribute("listAttendance");

            int subjectId = Integer.parseInt(request.getParameter("subjectId"));

            int classId = Integer.parseInt(request.getParameter("classes"));
            String attendanceDate = request.getParameter("attendanceDate");

            AttendanceDAO adao = new AttendanceDAO();

            List<Attendance> listAttendance = adao.getAttendanceForClassAndDate(classId, subjectId, attendanceDate);

            MajorDAO mdao = new MajorDAO();
            List<Classs> listClassBySubjectId = mdao.getClassBySubjectId(subjectId);
            request.setAttribute("listClassBySubjectId", listClassBySubjectId);

            request.setAttribute("listAttendance", listAttendance);
            request.setAttribute("attendanceDate", attendanceDate);
            request.setAttribute("subjectId", subjectId);
            request.setAttribute("classId", classId);

        } else if (action.equalsIgnoreCase("updateAttendance")) {
            int attendanceId = Integer.parseInt(request.getParameter("attendanceId"));
            AttendanceDAO adao = new AttendanceDAO();

            boolean checkUpdate = adao.updateAttendance(attendanceId);

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
