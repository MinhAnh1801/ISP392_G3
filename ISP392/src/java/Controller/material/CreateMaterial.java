package Controller.material;

import DAO.MajorDAO;
import DAO.MaterialDAO;
import DAO.UserDAO;
import Model.Lecturer_Profile;
import Model.Subjects;
import Model.Materials;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 *
 * @author trung
 */
@WebServlet(name = "CreateMaterial", urlPatterns = {"/createMaterial"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CreateMaterial extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";
    private static final Logger LOGGER = Logger.getLogger(CreateMaterial.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("user");
        if (id == null) {
            response.sendRedirect("login");
            return;
        }

        MajorDAO mdao = new MajorDAO();
        List<Subjects> listSubject = mdao.getAllSubjects();
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("userId", id);

        request.getRequestDispatcher("material/createMaterial.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy thông tin từ form
        String materialName = request.getParameter("material_name");
        String description = request.getParameter("description");
        String subjectIdStr = request.getParameter("subject_id");
        Part filePart = request.getPart("material_file");

        // Kiểm tra dữ liệu đầu vào
        if (materialName == null || subjectIdStr == null || filePart == null || filePart.getSize() == 0) {
            request.setAttribute("error", "All fields are required.");
            doGet(request, response);
            return;
        }

        // Chuyển đổi subjectId
        int subjectId;
        try {
            subjectId = Integer.parseInt(subjectIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid subject ID.");
            doGet(request, response);
            return;
        }

        // Kiểm tra định dạng file
        String fileName = filePart.getSubmittedFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        if (!fileExtension.matches("pdf|docx|pptx")) {
            request.setAttribute("error", "Invalid file format. Only PDF, DOCX, and PPTX files are allowed.");
            doGet(request, response);
            return;
        }

        // Lưu file tải lên
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        // Kiểm tra tên file trùng lặp
        String filePath = savePath + File.separator + fileName;
        File fileToCheck = new File(filePath);
        int count = 1;
        while (fileToCheck.exists()) {
            String newFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "_" + count++ + fileName.substring(fileName.lastIndexOf('.'));
            filePath = savePath + File.separator + newFileName;
            fileToCheck = new File(filePath);
        }

        // Ghi file vào thư mục
        try {
            filePart.write(filePath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "File upload failed", e);
            request.setAttribute("error", "File upload failed.");
            doGet(request, response);
            return;
        }

        // Tạo đối tượng Material
        Materials material = new Materials();
        material.setMaterial_name(materialName);
        material.setDescription(description);

        MajorDAO mdao = new MajorDAO();
        Subjects subject = mdao.getSubjectById(subjectId);
        material.setSubjectId(subject);

        UserDAO udao = new UserDAO();
        Lecturer_Profile userr = udao.getLecturerProfileById(userId);
        material.setUploaded_by(userr);

        // Lấy URL để lưu vào biến
        String url = request.getContextPath() + "/" + SAVE_DIR + "/" + fileName;
        material.setMaterial_file(url);  // Lưu URL vào đối tượng Material

        // Lưu Material vào cơ sở dữ liệu
        MaterialDAO materialDAO = new MaterialDAO();
        boolean success = materialDAO.saveMaterial(material);

        if (success) {
            request.setAttribute("mess", "Material created successfully!");
            LOGGER.info("Material created successfully: " + materialName + " by User ID: " + userId);
        } else {
            request.setAttribute("error", "Failed to create material.");
            LOGGER.severe("Failed to create material: " + materialName);
        }

        response.sendRedirect("viewAllMaterial");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for creating new material files and saving them to the database.";
    }
}
