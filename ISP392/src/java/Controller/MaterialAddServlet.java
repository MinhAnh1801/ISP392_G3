package Controller;

import DAO.DAO;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/lecturer/addMaterial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100)   // 100MB
public class MaterialAddServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "C:/Users/khucx/OneDrive/Documents/temp";  // Adjust the path

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create a Gson instance to return the JSON response
        Gson gson = new Gson();
        try {

            // Retrieve form data
            String subjectIdStr = request.getParameter("subject_id");
            String materialName = request.getParameter("title");
            String description = request.getParameter("description");

            // Retrieve file data
            Part filePart = request.getPart("material_file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get the file name

            // Save the file to the server
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();  // Create directory if it doesn't exist
            }
            String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
            filePart.write(filePath);  // Write the file to the server

            // Get lecturer ID from the session
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("role") == null) {
                response.sendRedirect("ISP392/login");
                return;
            }
            int lecturerId = (Integer) session.getAttribute("user");

            // Parse subject ID
            int subjectId = Integer.parseInt(subjectIdStr);

            // Create a DAO instance and add the material to the database
            DAO materialsDAO = new DAO();
            boolean isAdded = materialsDAO.addMaterial(subjectId, materialName, fileName, new Timestamp(System.currentTimeMillis()), lecturerId, description);

            // Return JSON response based on the result
            if (isAdded) {
                response.getWriter().write(gson.toJson(new Result("success", "Material added successfully!")));
            } else {
                response.getWriter().write(gson.toJson(new Result("error", "Failed to add material. Please try again.")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return error JSON response
            response.getWriter().write(gson.toJson(new Result("error", "An unexpected error occurred.")));
        }
    }

    // Helper class to return the response message as JSON
    class Result {

        private String status;
        private String message;

        public Result(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
