/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 *
 * @author DELL
 */
@WebServlet(name="DownloadTemplateController", urlPatterns={"/DownloadTemplateController"})
public class DownloadTemplateController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String templateDir = getServletContext().getRealPath("") + File.separator + "templates";
        String zipFilePath = templateDir + File.separator + "AppTemplate.zip";
        
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=template.zip");
        response.setContentLength((int) zipFile.length());

        try (FileInputStream fis = new FileInputStream(zipFile);
             OutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }

}
