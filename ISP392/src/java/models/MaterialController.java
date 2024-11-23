/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package models;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.Subject;
import models.Materials;
import models.MaterialsDAO;
import models.Subjects;

/**
 *
 * @author admin
 */
@WebServlet("/lecturer/material")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100)   // 100MB
public class MaterialController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "C:/Users/khucx/OneDrive/Documents/temp";  // Adjust the path

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialsDAO d = new MaterialsDAO();
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                d.delete(id);
                response.sendRedirect("materials");
                return;
            } else {
                System.out.println("Delete operation failed: ID is null or empty.");
            }
        }

        if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Materials m = d.getMaterialsById(id);
            List<Subjects> s = d.getAllSubjectCodes();
            for (Subjects subjects : s) {
                log(subjects.getCode());
            }
            request.setAttribute("sid", d.getsId(m.getSubjectCode()));
            request.setAttribute("subjectList", s);
            request.setAttribute("m", m);
            request.getRequestDispatcher("UpdateMaterial.jsp").forward(request, response);
        }
//
//        ArrayList<Materials> data = d.getMaterials();
//        request.setAttribute("data", data);
//        request.getRequestDispatcher("ViewMaterial.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve file data
        Part filePart = request.getPart("materialFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get the file name

        // Save the file to the server
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // Create directory if it doesn't exist
        }
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        filePart.write(filePath);  // Write the file to the server
        // Lấy giá trị từ request
        int id = Integer.parseInt(request.getParameter("id"));  // Đảm bảo lấy đúng id
        String materialName = request.getParameter("materialName");
//        String materialFile = request.getParameter("materialFile");
        String description = request.getParameter("description");
        int subjectCode = Integer.parseInt(request.getParameter("subject_id"));
        log("id " + request.getParameter("id"));
        log("name " + materialName);
        log("sid " + request.getParameter("subject_id"));
        log("des" + description);
        MaterialsDAO d = new MaterialsDAO();
        d.update(id, materialName, fileName, description, subjectCode);
        response.sendRedirect("materials");
        doGet(request, response);
    }

}
