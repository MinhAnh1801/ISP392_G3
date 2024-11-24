/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import DAO.ApplicationTypeDAO;
import Model.ApplicationType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Dell
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100)   // 100MB
public class AddApplicationType extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UPLOAD_DIRECTORY = "C:/Users/khucx/OneDrive/Documents/temp";  // Adjust the path

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddApplicationType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddApplicationType at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AddApplicationType.jsp").forward(request, response);
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

        String typeName = request.getParameter("type_name");
        ApplicationTypeDAO dao = new ApplicationTypeDAO();

        // Kiểm tra xem ApplicationType đã tồn tại hay chưa
        if (dao.isApplicationTypeExists(typeName)) {
            // Nếu đã tồn tại, gửi thông báo lỗi và forward lại về trang nhập liệu
            request.setAttribute("error", "Application type name already exists.");
            request.getRequestDispatcher("AddApplicationType.jsp").forward(request, response);
            return;
        }
        // Retrieve file data
        Part filePart = request.getPart("template");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get the file name
        // Save the file to the server
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // Create directory if it doesn't exist
        }
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        filePart.write(filePath);  // Write the file to the server
        // Nếu không tồn tại, tiến hành thêm mới
        int newId = dao.generateMaxID();
        ApplicationType appType = new ApplicationType(newId, typeName, fileName);
        dao.insertApplicationType(appType);
        response.sendRedirect("ViewListApplicationType.jsp");
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