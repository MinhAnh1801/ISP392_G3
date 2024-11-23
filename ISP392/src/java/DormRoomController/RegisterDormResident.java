package DormRoomController;

import DAL.DormResidentDAO;
import DAL.DormRoomsDAO;
import Model.DormResident;
import Model.DormRooms;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@WebServlet("/registerDormResident")
public class RegisterDormResident extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddDormRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDormRoom at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DormRoomsDAO dormRoomsDAO = new DormRoomsDAO();
        List<DormRooms> dormRooms = dormRoomsDAO.getAvailableDormRooms();

        String successMessage = request.getParameter("successMessage");
        String errorMessage = request.getParameter("errorMessage");
        String redirectMessage = request.getParameter("redirectmsg");

        request.setAttribute("dormRooms", dormRooms);
        request.setAttribute("successMessage", successMessage);
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("redirectmsg", redirectMessage);
        request.getRequestDispatcher("/registerDormResident.jsp").forward(request, response);
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session= request.getSession();
        int studentId = (int)session.getAttribute("user"); // Giả định studentId là 2, bạn có thể sửa để lấy từ session hoặc request
        int dormRoomId;
        
        DormResidentDAO dormResidentDAO = new DormResidentDAO();
    boolean hasRegistered = dormResidentDAO.hasExistingDormRoom(studentId);
    if (hasRegistered) {
        response.sendRedirect("registerDormResident?errorMessage=You have already registered for a dorm room.");
        return;
    }

        try {
            dormRoomId = Integer.parseInt(request.getParameter("dormRoomId"));
        } catch (NumberFormatException e) {
            response.sendRedirect("registerDormResident?errorMessage=Invalid dorm room ID.");
            return;
        }

        String checkInDateStr = request.getParameter("checkInDate");
        String checkOutDateStr = request.getParameter("checkOutDate");

        if (checkInDateStr == null || checkOutDateStr == null) {
            response.sendRedirect("registerDormResident?errorMessage=Check-in or check-out date cannot be empty.");
            return;
        }

        Date checkInDate = null;
        Date checkOutDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date parsedCheckInDate = sdf.parse(checkInDateStr);
            java.util.Date parsedCheckOutDate = sdf.parse(checkOutDateStr);
            checkInDate = new Date(parsedCheckInDate.getTime());
            checkOutDate = new Date(parsedCheckOutDate.getTime());
        } catch (ParseException e) {
            response.sendRedirect("registerDormResident?errorMessage=Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        if (checkOutDate.before(checkInDate)) {
            response.sendRedirect("registerDormResident?errorMessage=Check-out date cannot be earlier than check-in date.");
            return;
        }

    // Lấy giá phòng từ DormRoomsDAO
    DormRoomsDAO dormRoomsDAO = new DormRoomsDAO();
    BigDecimal roomPrice = dormRoomsDAO.getDormRoomPrice(dormRoomId);
    DormCostCalculator calculator = new DormCostCalculator(studentId, dormRoomId, checkInDate, checkOutDate, roomPrice);
    BigDecimal totalCost = calculator.calculateTotalCost();
    log(totalCost.toString());
    // Tạo bản ghi Payment ban đầu với giá phòng
   // DormResidentDAO dormResidentDAO = new DormResidentDAO();
    boolean paymentCreated = dormResidentDAO.addPayment(studentId, totalCost, "Dorm");
    if (!paymentCreated) {
        response.sendRedirect("registerDormResident?errorMessage=Unable to create payment.");
        return;
    }
        // Kiểm tra nếu thanh toán đã được hoàn tất (status là 'Paid')
            DormResident dormResident = new DormResident();
            dormResident.setStudentId(studentId);
            dormResident.setDormRoomId(dormRoomId);
            dormResident.setCheckInDate(checkInDate);
            dormResident.setCheckOutDate(checkOutDate);
            dormResident.setStatus("Active");
            dormResidentDAO.addDormTemp(dormResident);
            response.sendRedirect("registerDormResident?redirectmsg=Please go to the payment section to complete the procedure");
//            boolean success = dormResidentDAO.addDormResident(dormResident);
//            if (success) {
//                response.sendRedirect("registerDormResident?successMessage=Registration successful.");
//            } else {
//                response.sendRedirect("registerDormResident?errorMessage=Registration failed. Please try again.");
//            }
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }

}