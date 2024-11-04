/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AssignmentsDAO;
import Model.Assignments;
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
 * @author FPTSHOP
 */
@WebServlet(name = "DashboardAssignments", urlPatterns = {"/uploadAssignment"})
public class UploadAssignments extends HttpServlet {

    private AssignmentsDAO assignmentsDAO = new AssignmentsDAO();

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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        List<Assignments> listAssignments = assignmentsDAO.findAll();
        request.setAttribute("listAssignments", listAssignments);

        request.getRequestDispatcher("lecturer/uploadAssigntments.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "add":
                uploadAsm(request, response);
                response.sendRedirect("uploadAssignment");
                break;
            default:
                response.sendRedirect("uploadAssignment");
        }
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

    private void uploadAsm(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Integer lecturerID = (Integer) session.getAttribute("lecturerID");

            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int classID = Integer.parseInt(request.getParameter("classID"));
            String assignmentName = request.getParameter("assignmentName");
            String description = request.getParameter("assignmentDescription");
            java.sql.Date assignedDate = java.sql.Date.valueOf(request.getParameter("assignedDate"));
            java.sql.Date dueDate = java.sql.Date.valueOf(request.getParameter("dueDate"));

            Assignments assignment = Assignments.builder()
                    .LecturerID(lecturerID)
                    .SubjectID(subjectID)
                    .ClassID(classID)
                    .AssignmentName(assignmentName)
                    .AssignmentDecription(description)
                    .AssignedDate(assignedDate)
                    .DueDate(dueDate)
                    .build();

            assignmentsDAO.insert(assignment);

            request.setAttribute("message", "Assignment uploaded successfully!");
            request.getRequestDispatcher("success.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error uploading assignment. Please try again.");
            try {
                request.getRequestDispatcher("uploadAssignments.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
