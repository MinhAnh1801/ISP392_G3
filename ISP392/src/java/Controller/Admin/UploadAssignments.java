/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15)   // 15 MB

/**
 *
 * @author FPTSHOP
 */
@WebServlet(name = "DashboardAssignments", urlPatterns = {"/uploadAssignment"})
public class UploadAssignments extends HttpServlet {

    private final AssignmentsDAO assignmentsDAO = new AssignmentsDAO();
    private final ClassDAO classDAO = new ClassDAO();
    private final SubjectsDAO subjectsDAO = new SubjectsDAO();

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

//        List<Assignments> listAssignments = assignmentsDAO.findAll();
//        request.setAttribute("listAssignments", listAssignments);
        List<Class> listClass = classDAO.findAllClasses();
        List<Subjects> listSubjects = subjectsDAO.findAllSubjects();

        request.setAttribute("listClass", listClass);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("servletA", this);

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
                response.sendRedirect("home");
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

    public String getSubjectCode(int subjectId) {
        return subjectsDAO.getSubjectCodeById(subjectId);
    }

    public String getClassName(int classId) {
        return classDAO.getClassNameById(classId);
    }

    public List<Integer> getAllSubjectIds() {
        return subjectsDAO.getAllSubjectIds(); 
    }

    public List<Integer> getAllClassIds() {
        return classDAO.getAllClassIds(); 
    }

    private void uploadAsm(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Integer lecturerID = (Integer) session.getAttribute("user");

            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int classID = Integer.parseInt(request.getParameter("classID"));
            String assignmentName = request.getParameter("assignmentName");
            String description = request.getParameter("assignmentDescription");
            java.sql.Date assignedDate = java.sql.Date.valueOf(request.getParameter("assignedDate"));
            java.sql.Date dueDate = java.sql.Date.valueOf(request.getParameter("dueDate"));

            Part filePart = request.getPart("assignmentFile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/uploads");

            Files.createDirectories(Paths.get(uploadPath));

            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(uploadPath).resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            // Xử lý file tải lên
//            Part filePart = request.getPart("assignmentFile");
//            String fileName = filePart.getSubmittedFileName();
//            String uploadPath = "uploads/" + fileName;
//            filePart.write(uploadPath); // Lưu file vào thư mục "uploads"
            Assignments assignment = Assignments.builder()
                    .LecturerID(lecturerID)
                    .SubjectID(subjectID)
                    .ClassID(classID)
                    .AssignmentName(assignmentName)
                    .AssignmentDecription(description)
                    .AssignedDate(assignedDate)
                    .DueDate(dueDate)
                    .filePath(uploadPath)
                    .build();

            assignmentsDAO.insert(assignment);

            request.setAttribute("message", "Assignment uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error uploading assignment. Please try again.");
        }
    }

}
