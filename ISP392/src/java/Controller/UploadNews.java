package Controller;

import Context.DBContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/insertNews")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadNews extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "C:\\Users\\khucx\\Downloads";  // Change to your upload folder

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form fields from the request
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Handle the uploaded file (image)
        Part filePart = request.getPart("img");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get file name

        // Ensure upload directory exists
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save the file to the server
        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
        filePart.write(filePath);  // Write file to server

        // Insert into the database
        String sql = "INSERT INTO News (img, content ,title, upload_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(3, title);                     // Set title
            ps.setString(1, fileName);                  // Store only the file name in the database
            ps.setString(2, content);                   // Set content
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));  // Set current timestamp

            // Execute insert
            ps.executeUpdate();

            // If successful, forward to success page
            request.setAttribute("message", "News added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error while adding news: " + e.getMessage());
        }

        // Forward to the JSP page with success or error message
        response.sendRedirect("newsAdmin");      
    }
}
