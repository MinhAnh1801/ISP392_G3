package Controller.Grade;

import DAO.GradeDAO;
import DAO.UserDAO;
import Model.Grades;
import Model.Student_Profile;
import Model.Subjects;
import Model.typeGrade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name = "UploadGradeController", urlPatterns = {"/uploadGrade"})
@MultipartConfig
public class UploadGradeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("user");
        if (id == null) {
            response.sendRedirect("login");
            return;

        }

        request.getRequestDispatcher("grade/uploadGrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        File tempFile = File.createTempFile("grades", ".xlsx");
        filePart.write(tempFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(tempFile); Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            GradeDAO gdao = new GradeDAO();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                // lấy ra mã sinh viên từ cột 0
                String studentMSV = row.getCell(0).getStringCellValue();
                UserDAO userDao = new UserDAO();
                int sp = userDao.getStudentIdByMSV(studentMSV);
                
                
                
                int subjectId = (int) row.getCell(2).getNumericCellValue();
                double grade = row.getCell(5).getNumericCellValue();
                String comments = row.getCell(6).getStringCellValue();
                int typeId = (int) row.getCell(7).getNumericCellValue();
                int percentId = (int) row.getCell(4).getNumericCellValue();

                gdao.insertGrade(sp, subjectId, grade, comments, typeId, percentId);

            }

            response.getWriter().write("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "    <title>Upload Grades</title>\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            background-color: #ffffff;\n"
                    + "            font-family: Arial, sans-serif;\n"
                    + "            display: flex;\n"
                    + "            justify-content: center;\n"
                    + "            align-items: center;\n"
                    + "            height: 100vh;\n"
                    + "            flex-direction: column;\n"
                    + "        }\n"
                    + "        .upload-container {\n"
                    + "            width: 50%;\n"
                    + "            padding: 20px;\n"
                    + "            border: 1px solid #dee2e6;\n"
                    + "            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\n"
                    + "            border-radius: 8px;\n"
                    + "        }\n"
                    + "        h1 {\n"
                    + "            color: #ff5722;\n"
                    + "            text-align: center;\n"
                    + "            margin-bottom: 20px;\n"
                    + "        }\n"
                    + "        label {\n"
                    + "            font-size: 16px;\n"
                    + "            margin-bottom: 10px;\n"
                    + "            display: block;\n"
                    + "        }\n"
                    + "        input[type=\"file\"] {\n"
                    + "            margin: 10px 0;\n"
                    + "            padding: 10px;\n"
                    + "            font-size: 16px;\n"
                    + "            width: 100%;\n"
                    + "            border: 1px solid #dee2e6;\n"
                    + "            border-radius: 4px;\n"
                    + "        }\n"
                    + "        input[type=\"submit\"] {\n"
                    + "            background-color: #ff9800;\n"
                    + "            color: #ffffff;\n"
                    + "            padding: 10px 20px;\n"
                    + "            border: none;\n"
                    + "            cursor: pointer;\n"
                    + "            font-size: 16px;\n"
                    + "            border-radius: 4px;\n"
                    + "            margin-top: 20px;\n"
                    + "            width: 100%;\n"
                    + "        }\n"
                    + "        input[type=\"submit\"]:hover {\n"
                    + "            background-color: #e68900;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <div class=\"upload-container\">\n"
                    + "        <h1>File uploaded and data inserted successfully.</h1>\n"
                    + "        <a>\n"
                    + "            <input type=\"submit\" class=\"btn\" value=\"Home\">\n"
                    + "        </a>\n"
                    + "                    \n"
                    + "\n"
                    + "       \n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "</html>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error while processing the file: " + e.getMessage());
        } finally {
            tempFile.delete(); // Xóa file tạm sau khi xử lý xong
        }
    }
}
