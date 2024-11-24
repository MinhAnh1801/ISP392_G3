package Controller.material;

import DAO.MaterialDAO;
import DAO.MajorDAO;
import DAO.UserDAO;
import Model.Materials;
import Model.Subjects;
import Model.Lecturer_Profile;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
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
@WebServlet(name = "UpdateMaterial", urlPatterns = {"/updateMaterial"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateMaterial extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy dữ liệu từ form
        String materialIdStr = request.getParameter("material_id");
        String materialName = request.getParameter("material_name");
        String description = request.getParameter("description");
        String subjectIdStr = request.getParameter("subject_id");
        Part filePart = request.getPart("material_file");

        if (materialIdStr == null || materialName == null || subjectIdStr == null) {
            request.setAttribute("error", "All fields are required.");
            doGet(request, response);
            return;
        }

        int materialId = Integer.parseInt(materialIdStr);
        int subjectId = Integer.parseInt(subjectIdStr);

        // Lấy tài liệu từ DB
        MaterialDAO materialDAO = new MaterialDAO();
        Materials material = materialDAO.getMaterialById(materialId);
        if (material == null) {
            request.setAttribute("error", "Material not found.");
            doGet(request, response);
            return;
        }

        // Cập nhật thông tin cơ bản
        material.setMaterial_name(materialName);
        material.setDescription(description);

        // Lấy môn học
        MajorDAO majorDAO = new MajorDAO();
        Subjects subject = majorDAO.getSubjectById(subjectId);
        material.setSubjectId(subject);
        
        
         // Thiết lập Lecturer_Profile cho Material
        UserDAO udao = new UserDAO();
        Lecturer_Profile userr = udao.getLecturerProfileById(userId);
        material.setUpload_by(userr);

        // Nếu có file mới, xử lý upload file
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + SAVE_DIR;

            // Kiểm tra thư mục lưu trữ
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            // Kiểm tra trùng tên file
            String filePath = savePath + File.separator + fileName;
            File fileToCheck = new File(filePath);
            int count = 1;
            while (fileToCheck.exists()) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.')) + "_" + count++ + fileName.substring(fileName.lastIndexOf('.'));
                filePath = savePath + File.separator + fileName;
                fileToCheck = new File(filePath);
            }

            // Lưu file mới
            filePart.write(filePath);

            // Cập nhật tên file vào DB
            material.setMaterial_file(fileName);
        }

        // Lưu cập nhật vào cơ sở dữ liệu
        boolean success = materialDAO.updateMaterial(material);
        if (success) {
            response.sendRedirect("viewAllMaterial");
        } else {
            request.setAttribute("error", "Failed to update material.");

            response.sendRedirect("createMaterial?action=edit&id="+materialIdStr);
        }
    }
}
