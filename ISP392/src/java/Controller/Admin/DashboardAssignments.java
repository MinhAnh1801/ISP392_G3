/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AssignmentSubmissionsDAO;
import DAO.AssignmentsDAO;
import DAO.SubjectsDAO;
import DAO.ClassDAO;
import Model.Assignments;
import Model.Subjects;
import Model.Class;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig

@WebServlet(name = "dashboardAssignments", urlPatterns = {"/dashboardAssignments"})
public class DashboardAssignments extends HttpServlet {

    private final AssignmentsDAO assignmentsDAO = new AssignmentsDAO();
    private final ClassDAO classDAO = new ClassDAO();
    private final SubjectsDAO subjectsDAO = new SubjectsDAO();
    private final AssignmentSubmissionsDAO assignmentSubmissionsDAO = new AssignmentSubmissionsDAO();

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

        HttpSession session = request.getSession();
        Integer lecturerID = (Integer) session.getAttribute("user");

        List<Assignments> listAssignments = assignmentsDAO.getAssignmentByLecturerId(lecturerID);
        List<Model.Class> listClass = classDAO.findAllClasses();
        List<Subjects> listSubjects = subjectsDAO.findAllSubjects();

        request.setAttribute("listAssignments", listAssignments);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("servletA", this);

        request.getRequestDispatcher("lecturer/dashboardAssignments.jsp").forward(request, response);
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
            case "view":
                int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
                Assignments assignment = assignmentsDAO.getAssignmentById(assignmentId);
                request.setAttribute("assignment", assignment);
                request.getRequestDispatcher("lecturer/updateAssignment.jsp").forward(request, response);
                break;
            case "update":
                updateAsm(request, response);
                response.sendRedirect("dashboardAssignments");
                break;
            default:
                response.sendRedirect("dashboardAssignments");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void updateAsm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        String assignmentName = request.getParameter("assignmentName");
        String assignmentDescription = request.getParameter("assignmentDescription");
        java.sql.Date dueDate = java.sql.Date.valueOf(request.getParameter("dueDate"));
        Part filePart = request.getPart("fileUpload");
        String filePath = null;

        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            filePath = "uploads/" + fileName;
            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }

        Assignments updatedAssignment = Assignments.builder()
                .ID(assignmentId)
                .AssignmentName(assignmentName)
                .AssignmentDecription(assignmentDescription)
                .DueDate(dueDate)
                .filePath(filePath)
                .build();

        assignmentsDAO.updateAssignment(updatedAssignment);

        request.getSession().setAttribute("message", "Assignment updated successfully!");
    }

}
