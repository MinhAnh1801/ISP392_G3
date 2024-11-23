

import DAL.ApplicationDAO;
import DAL.ApplicationTypeDAO;
import Model.Applications;
import Model.ApplicationType;
import Utils.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ApplicationController", urlPatterns = {"/ApplicationController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
                 maxFileSize = 1024 * 1024 * 10,  
                 maxRequestSize = 1024 * 1024 * 50) 
public class ApplicationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action != null ? action : "viewApplications";
        if (action == null || action.equals("showForm")) {
            showApplicationForm(request, response);
        } else if (action.equals("viewApplications")) {
            viewApplicationsByStudent(request, response);
        }else if (action.equals("viewAllApplications")) {
            viewAllApplications(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("submitApplication")) {
            submitApplication(request, response);
        }else if (action != null && action.equals("updateStatus")) {
        updateApplicationStatus(request, response);
    }
    }

    private void showApplicationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationTypeDAO applicationTypeDAO = new ApplicationTypeDAO();
        List<ApplicationType> applicationTypes = applicationTypeDAO.getAllApplicationTypes();
        request.setAttribute("applicationTypes", applicationTypes);
        request.getRequestDispatcher("applicationForm.jsp").forward(request, response);
    }

    private void submitApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int studentId = (int)session.getAttribute("user");
        int applicationTypeId = Integer.parseInt(request.getParameter("applicationType"));
        String content = request.getParameter("content");

        Applications application = new Applications();
        application.setStudentId(studentId);
        application.setApplicationType(applicationTypeId);
        application.setContent(content);
        application.setCreatedAt(new Date());
        application.setStatus("Pending");
        application.setLastUpdated(new Date());

        Part filePart = request.getPart("file"); 
        if (filePart != null && filePart.getSize() > 0) {
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
            FileHandler fileHandler = new FileHandler(uploadDir);

            String fileName = fileHandler.saveFile(filePart);
            application.setAttachedFile(fileName); 
        }

        ApplicationDAO applicationDAO = new ApplicationDAO();
        applicationDAO.saveApplication(application);
        response.sendRedirect("ApplicationController?action=viewApplications");
    }

    private void viewApplicationsByStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<Applications> applications = applicationDAO.getApplicationsByStudentId((int)session.getAttribute("user"));
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("viewApplications.jsp").forward(request, response);
    }

    private void viewAllApplications(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<Applications> applications = applicationDAO.getAllApplications();
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("viewAllApplications.jsp").forward(request, response);
    }    

   private void updateApplicationStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        String status = request.getParameter("status");
        String responseText = request.getParameter("response");

        ApplicationDAO applicationDAO = new ApplicationDAO();
        applicationDAO.updateApplicationStatus(applicationId, status, responseText);
        
        response.sendRedirect("ApplicationController?action=viewAllApplications");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
