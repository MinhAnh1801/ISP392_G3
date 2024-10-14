package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImgServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "C:/Users/khucx/Downloads";  // Path to your uploads folder

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the image filename from the request
        String imageName = request.getParameter("name");
        
        // Build the full path to the image file
        File imageFile = new File(UPLOAD_DIRECTORY + File.separator + imageName);

        // Check if file exists and send the image
        if (imageFile.exists()) {
            response.setContentType(getServletContext().getMimeType(imageName));
            response.setContentLength((int) imageFile.length());

            try (FileInputStream in = new FileInputStream(imageFile);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 if file not found
        }
    }
}
