

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
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("submitApplication")) {
            submitApplication(request, response);
        }
    }

    private void showApplicationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationTypeDAO applicationTypeDAO = new ApplicationTypeDAO();
        List<ApplicationType> applicationTypes = applicationTypeDAO.getAllApplicationTypes();
        request.setAttribute("applicationTypes", applicationTypes);
        request.getRequestDispatcher("applicationForm.jsp").forward(request, response);
    }

    private void submitApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = 2; 
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
        int studentId = 2; 
        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<Applications> applications = applicationDAO.getApplicationsByStudentId(studentId);
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("viewApplications.jsp").forward(request, response);
    }
}
