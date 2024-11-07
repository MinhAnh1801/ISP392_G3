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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "Assignments", urlPatterns = {"/assignments"})
@MultipartConfig
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

        int studentId = (int) request.getSession().getAttribute("user");
        Map<Integer, String> progressMap = new HashMap<>();
        Map<Integer, String> reviewStatusMap = new HashMap<>();

        for (Assignments assignment : listAssignments) {
            String progress = getStudentProgress(assignment, studentId);
            String reviewStatus = getInstructorReviewStatus(assignment.getID(), studentId);

            progressMap.put(assignment.getID(), progress);
            reviewStatusMap.put(assignment.getID(), reviewStatus);
        }

        request.setAttribute("listAssignments", listAssignments);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("servletA", this);
        request.setAttribute("progressMap", progressMap);
        request.setAttribute("reviewStatusMap", reviewStatusMap);

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
                int studentId = (int) request.getSession().getAttribute("user");
                
                boolean isGraded = assignmentSubmissionsDAO.isGraded(assignmentId, studentId);
                request.setAttribute("isGraded", isGraded);

                request.setAttribute("assignment", assignment);
                request.getRequestDispatcher("Student/SubmitAssignment.jsp").forward(request, response);
                break;
            case "submit":
                handleSubmission(request, assignmentSubmissionsDAO);
                response.sendRedirect("assignments");
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

    private void handleSubmission(HttpServletRequest request, AssignmentSubmissionsDAO assignmentSubmissionsDAO) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int submittedAssignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        Integer studentId = (Integer) session.getAttribute("user");
        int classId = (assignmentsDAO.getAssignmentById(submittedAssignmentId).getClassID());

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

        request.getSession().setAttribute("message", "Bài làm đã được nộp thành công!");
    }

    public String getInstructorReviewStatus(int assignmentId, int studentId) {
        boolean isGraded = assignmentSubmissionsDAO.isGraded(assignmentId, studentId);

        if (isGraded) {
            return "Graded";
        } else {
            return "Not reviewed";
        }
    }

    public String getStudentProgress(Assignments assignment, int studentId) {
        Date dueDate = assignment.getDueDate();
        Date currentDate = new Date();
        boolean isSubmitted = assignmentSubmissionsDAO.isAssignmentSubmitted(assignment.getID(), studentId);

        if (isSubmitted) {
            return "Submitted";
        } else if (currentDate.after(dueDate)) {
            return "Overdue, Not Submitted";
        } else {
            return "Pending Submission";
        }
    }
}
