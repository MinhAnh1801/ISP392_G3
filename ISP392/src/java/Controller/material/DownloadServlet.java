package Controller.material;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for downloading files.
 * 
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("filePath");

        // Kiểm tra nếu filePath là null hoặc rỗng
        if (filePath == null || filePath.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File path is required.");
            return;
        }

        // Giải mã đường dẫn file an toàn
        String decodedFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8.toString());

        // Thêm đường dẫn thư mục gốc của ứng dụng
        String appPath = request.getServletContext().getRealPath("");
        String fullPath = appPath + File.separator + "uploadFiles" + File.separator + decodedFilePath;

        // In ra để kiểm tra đường dẫn đầy đủ (nếu cần)
        System.out.println("Full path to file: " + fullPath);

        File file = new File(fullPath);

        // Kiểm tra nếu file tồn tại và là file hợp lệ
        if (file.exists() && file.isFile()) {
            response.setContentType(getServletContext().getMimeType(file.getName()));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString()) + "\"");
            response.setContentLengthLong(file.length());

            // Tải xuống file
            try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error in file download.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is supported only.");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for downloading files";
    }
}
