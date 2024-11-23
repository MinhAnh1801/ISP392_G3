package Controller;

import Context.DBContext;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/upload")
@MultipartConfig
public class ImportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SQL_INSERT_USER
            = "INSERT INTO Users (username, email, password, role, status) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_STUDENT_PROFILE
            = "INSERT INTO Student_Profile (student_id, major_id, year_of_study, full_name, date_of_birth, "
            + "phone_number, email, gender, student_code, address, emergency_contact, national_id, "
            + "enrollment_year, graduation_year, gpa, scholarship_status, medical_conditions, ten_phu_huynh, "
            + "so_dien_thoai_phu_huynh, dia_chi_phu_huynh, email_phu_huynh, nghe_nghiep_phu_huynh, noi_lam_viec_phu_huynh, "
            + "so_bang_dang_ky, so_bang_dang_ky_cu, ma_thanh_vien, ngay_ghi_danh, hinh_thuc_hoc, trang_thai, "
            + "hoc_ky_hien_tai, nganh_hoc, chuong_trinh_hoc, de_tai_tot_nghiep, so_du_tai_khoan, nganh_cu, "
            + "quyet_dinh_chuyen_nganh, quyet_dinh_cong_nhan_sinh_vien_chinh_quy, ngay_cong_nhan_sinh_vien_chinh_quy, "
            + "quyet_dinh_cong_nhan_sinh_vien_du_bi, thoi_han_hoc_tai_truong, lop_chinh, loai_tai_chinh, "
            + "quyet_dinh_thoi_hoc, quyet_dinh_chuyen_co_so,quyet_dinh_ky_luat,quyet_dinh_cong_nhan_tot_nghiep, ngay_cong_nhan_tot_nghiep, trang_thai_den, "
            + "chuyen_nganh, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(false);
        Connection connection = null;
        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int urole = (int) session.getAttribute("role");
        if (urole != 0) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
            return;
        }

        Part filePart = request.getPart("file");
        if (filePart == null) {
            request.setAttribute("errorMessage", "No file uploaded");
            request.getRequestDispatcher("importstudent.jsp").forward(request, response);
            return;
        }

        try (InputStream inputStream = filePart.getInputStream(); XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Updated code to map each column to the correct field in the specified order
            try {
                connection = new DBContext().getConnection();
                connection.setAutoCommit(false);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0 || isRowEmpty(row)) {
                        continue;
                    }

                    // User Table Columns
                    String username = getCellValue(row.getCell(0));
                    String email = getCellValue(row.getCell(1));
                    String password = getCellValue(row.getCell(2));
                    String role = getCellValue(row.getCell(3)).toLowerCase();
                    String status = getCellValue(row.getCell(4)).toLowerCase();

                    // Insert into Users table
                    int studentId;
                    try (PreparedStatement userStmt = connection.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        userStmt.setString(1, username);
                        userStmt.setString(2, email);
                        userStmt.setString(3, password);
                        userStmt.setString(4, role);
                        userStmt.setString(5, status);
                        userStmt.executeUpdate();

                        // Retrieve generated student_id
                        try (ResultSet rs = userStmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                studentId = rs.getInt(1);
                            } else {
                                throw new SQLException("Failed to retrieve generated student_id.");
                            }
                        }
                    }

                    // Student_Profile Table Columns in Order
                    int majorId = parseInt(getCellValue(row.getCell(5)));
                    String yearOfStudy = getCellValue(row.getCell(6));
                    String fullName = getCellValue(row.getCell(7));
                    Date dateOfBirth = parseDate(getCellValue(row.getCell(8)));
                    String phoneNumber = getCellValue(row.getCell(9));
                    String gender = getCellValue(row.getCell(11));
                    String studentCode = getCellValue(row.getCell(12));
                    String address = getCellValue(row.getCell(13));
                    String emergencyContact = getCellValue(row.getCell(14));
                    String nationalId = getCellValue(row.getCell(15));
                    int enrollmentYear = parseInt(getCellValue(row.getCell(16)));
                    int graduationYear = parseInt(getCellValue(row.getCell(17)));
                    double gpa = parseDouble(getCellValue(row.getCell(18)));
                    boolean scholarshipStatus = Boolean.parseBoolean(getCellValue(row.getCell(19)));
                    String medicalConditions = getCellValue(row.getCell(20));
                    String tenPhuHuynh = getCellValue(row.getCell(21));
                    String soDienThoaiPhuHuynh = getCellValue(row.getCell(22));
                    String diaChiPhuHuynh = getCellValue(row.getCell(23));
                    String emailPhuHuynh = getCellValue(row.getCell(24));
                    String ngheNghiepPhuHuynh = getCellValue(row.getCell(25));
                    String noiLamViecPhuHuynh = getCellValue(row.getCell(26));
                    String soBangDangKy = getCellValue(row.getCell(27));
                    String soBangDangKyCu = getCellValue(row.getCell(28));
                    String maThanhVien = getCellValue(row.getCell(29));
                    String ngayGhiDanh = getCellValue(row.getCell(30));
                    String hinhThucHoc = getCellValue(row.getCell(31));
                    String trangThai = getCellValue(row.getCell(32));
                    String hocKyHienTai = getCellValue(row.getCell(33));
                    String nganhHoc = getCellValue(row.getCell(34));
                    String chuongTrinhHoc = getCellValue(row.getCell(35));
                    String deTaiTotNghiep = getCellValue(row.getCell(36));
                    int soDuTaiKhoan = parseInt(getCellValue(row.getCell(37)));
                    String nganhCu = getCellValue(row.getCell(38));
                    String quyetDinhChuyenNganh = getCellValue(row.getCell(39));
                    String quyetDinhCongNhanSinhVienChinhQuy = getCellValue(row.getCell(40));
                    String ngayCongNhanSinhVienChinhQuy = getCellValue(row.getCell(41));
                    String quyetDinhCongNhanSinhVienDuBi = getCellValue(row.getCell(42));
                    String thoiHanHocTapTaiTruong = getCellValue(row.getCell(43));
                    String lopChinh = getCellValue(row.getCell(44));
                    String loaiTaiChinh = getCellValue(row.getCell(45));
                    String quyetDinhThoiHoc = getCellValue(row.getCell(46));
                    String quyetDinhChuyenCoSo = getCellValue(row.getCell(47));
                    String quyetDinhKyLuat = getCellValue(row.getCell(48));
                    String quyetDinhCongNhanTotNghiep = getCellValue(row.getCell(49));
                    String ngayCongNhanTotNghiep = getCellValue(row.getCell(50));
                    String trangThaiDen = getCellValue(row.getCell(51));
                    String chuyenNganh = getCellValue(row.getCell(52));
                    String photo = getCellValue(row.getCell(53));

                    // Insert into Student_Profile table
                    try (PreparedStatement profileStmt = connection.prepareStatement(SQL_INSERT_STUDENT_PROFILE)) {
                        profileStmt.setInt(1, studentId);
                        profileStmt.setInt(2, majorId);
                        profileStmt.setString(3, yearOfStudy);
                        profileStmt.setString(4, fullName);
                        profileStmt.setDate(5, new java.sql.Date(dateOfBirth.getTime()));
                        profileStmt.setString(6, phoneNumber);
                        profileStmt.setString(7, email);
                        profileStmt.setString(8, gender);
                        profileStmt.setString(9, studentCode);
                        profileStmt.setString(10, address);
                        profileStmt.setString(11, emergencyContact);
                        profileStmt.setString(12, nationalId);
                        profileStmt.setInt(13, enrollmentYear);
                        profileStmt.setInt(14, graduationYear);
                        profileStmt.setDouble(15, gpa);
                        profileStmt.setBoolean(16, scholarshipStatus);
                        profileStmt.setString(17, medicalConditions);
                        profileStmt.setString(18, tenPhuHuynh);
                        profileStmt.setString(19, soDienThoaiPhuHuynh);
                        profileStmt.setString(20, diaChiPhuHuynh);
                        profileStmt.setString(21, emailPhuHuynh);
                        profileStmt.setString(22, ngheNghiepPhuHuynh);
                        profileStmt.setString(23, noiLamViecPhuHuynh);
                        profileStmt.setString(24, soBangDangKy);
                        profileStmt.setString(25, soBangDangKyCu);
                        profileStmt.setString(26, maThanhVien);
                        profileStmt.setString(27, ngayGhiDanh);
                        profileStmt.setString(28, hinhThucHoc);
                        profileStmt.setString(29, trangThai);
                        profileStmt.setString(30, hocKyHienTai);
                        profileStmt.setString(31, nganhHoc);
                        profileStmt.setString(32, chuongTrinhHoc);
                        profileStmt.setString(33, deTaiTotNghiep);
                        profileStmt.setInt(34, soDuTaiKhoan);
                        profileStmt.setString(35, nganhCu);
                        profileStmt.setString(36, quyetDinhChuyenNganh);
                        profileStmt.setString(37, quyetDinhCongNhanSinhVienChinhQuy);
                        profileStmt.setString(38, ngayCongNhanSinhVienChinhQuy);
                        profileStmt.setString(39, quyetDinhCongNhanSinhVienDuBi);
                        profileStmt.setString(40, thoiHanHocTapTaiTruong);
                        profileStmt.setString(41, lopChinh);
                        profileStmt.setString(42, loaiTaiChinh);
                        profileStmt.setString(43, quyetDinhThoiHoc);
                        profileStmt.setString(44, quyetDinhChuyenCoSo);
                        profileStmt.setString(45, quyetDinhKyLuat);
                        profileStmt.setString(46, quyetDinhCongNhanTotNghiep);
                        profileStmt.setString(47, ngayCongNhanTotNghiep);
                        profileStmt.setString(48, trangThaiDen);
                        profileStmt.setString(49, chuyenNganh);
                        profileStmt.setString(50, photo); // Set photo as the final parameter if it's required

                        profileStmt.executeUpdate();
                    }

                }
                connection.commit();
                response.sendRedirect("homepage.jsp?status=success");
            } catch (Exception e) {
                e.printStackTrace();
                if (connection != null) {
                    connection.rollback();
                }
                request.setAttribute("errorMessage", "Error processing file: " + e.getMessage());
                request.getRequestDispatcher("importstudent.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing file: " + e.getMessage());
            request.getRequestDispatcher("importstudent.jsp").forward(request, response);
        }
    }

    private boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK && !getCellValue(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String getCellValue(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return ""; // Default empty string for blank or null cells
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    return String.valueOf((int) cell.getNumericCellValue()); // Assumes integer numeric cell
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private int parseInt(String str) {
        if (str == null || str.isEmpty()) {
            return 0; // Default to 0 for empty cells
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0; // Default to 0 if parsing fails
        }
    }

    private double parseDouble(String str) {
        if (str == null || str.isEmpty()) {
            return 0.0; // Default to 0.0 for empty cells
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0.0 if parsing fails
        }
    }

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return new Date(); // Default to current date for empty cells
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            return new Date(); // Default to current date if parsing fails
        }
    }

}
