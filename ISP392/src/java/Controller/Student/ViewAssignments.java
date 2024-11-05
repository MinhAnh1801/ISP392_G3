/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

import DAO.AssignmentsDAO;
import DAO.ClassDAO;
import DAO.SubjectsDAO;
import DAO.AssignmentSubmissionsDAO;
import Model.Assignment_Submissions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Assignments;
import Model.Subjects;
import Model.Class;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "Assignments", urlPatterns = {"/assignments"})
public class ViewAssignments extends HttpServlet {

    private final AssignmentsDAO assignmentsDAO = new AssignmentsDAO();
    private final ClassDAO classDAO = new ClassDAO();
    private final SubjectsDAO subjectsDAO = new SubjectsDAO();
    private final AssignmentSubmissionsDAO assignmentSubmissionsDAO = new AssignmentSubmissionsDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Assignments</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Assignments at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        List<Model.Class> listClass = classDAO.findAllClasses();
        List<Subjects> listSubjects = subjectsDAO.findAllSubjects();

        request.setAttribute("listAssignments", listAssignments);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("servletA", this);

        request.getRequestDispatcher("Student/assignments.jsp").forward(request, response);
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

        List<Assignments> listAssignments = assignmentsDAO.findAll();
        List<Model.Class> listClass = classDAO.findAllClasses();
        List<Subjects> listSubjects = subjectsDAO.findAllSubjects();

        request.setAttribute("listClass", listClass);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("servletA", this);
        request.setAttribute("listAssignments", listAssignments);

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "do":
                int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
                Assignments assignment = assignmentsDAO.getAssignmentById(assignmentId);
                request.setAttribute("assignment", assignment);
                request.getRequestDispatcher("Student/SubmitAssignment.jsp").forward(request, response);
                break;
            case "submit":
                int submittedAssignmentId = Integer.parseInt(request.getParameter("assignmentId"));
                int studentId = Integer.parseInt(request.getParameter("studentId"));
                int classId = Integer.parseInt(request.getParameter("classId"));
                String submissionContent = request.getParameter("submissionContent");

                // Nhận file từ form
                Part filePart = request.getPart("fileUpload");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/uploads");
                Files.createDirectories(Paths.get(uploadPath));

                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(uploadPath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                }

                String filePath = request.getContextPath() + "/uploads/" + fileName;

                Assignment_Submissions submission = Assignment_Submissions.builder()
                        .AssignmentID(submittedAssignmentId)
                        .StudentID(studentId)
                        .ClassID(classId)
                        .SubmissionDate(new Date())
                        .SubmissionContent(filePath)
                        .build();

                assignmentSubmissionsDAO.saveSubmission(submission);

                request.setAttribute("message", "Bài làm đã được nộp thành công!");
                request.getRequestDispatcher("Student/submitAssignment.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("assignments");
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

    public Subjects getSubjectById(int subjectId) {
        return subjectsDAO.getSubjectById(subjectId);
    }

    public Class getClassById(int classId) {
        return classDAO.getClassById(classId);
    }

    public List<Integer> getAllSubjectIds() {
        return subjectsDAO.getAllSubjectIds();
    }

    public List<Integer> getAllClassIds() {
        return classDAO.getAllClassIds();
    }

}
