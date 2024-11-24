package Controller;

import DAO.ScheduleDAO;
import Model.Schedule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/viewSchedules")
public class ViewSchedule extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null) {
            response.sendRedirect("login");
            return;
        }
        List<Schedule> scheduleList = scheduleDAO.getAllSchedules();
        request.setAttribute("scheduleList", scheduleList);
        request.getRequestDispatcher("viewSchedule.jsp").forward(request, response);
    }
    

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("error|" + message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            // Parse start and end dates from the request
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            Date startDate = DATE_FORMAT.parse(startDateStr);
            Date endDate = DATE_FORMAT.parse(endDateStr);
            Date currentDate = new Date();

            // Validate that start date is later than the current date
            if (!startDate.after(currentDate)) {
                sendErrorResponse(response, "Start date must be later than the current date.");
                return;
            }

            // Calculate the date three months from the start date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH, 3);
            Date threeMonthsLater = calendar.getTime();

            // Validate that end date is at least three months after the start date
            if (!endDate.after(threeMonthsLater)) {
                sendErrorResponse(response, "End date must be at least three months after the start date.");
                return;
            }

            // Store dates in the session if validation passes
            session.setAttribute("semesterStartDate", startDate);
            session.setAttribute("semesterEndDate", endDate);

            // Send success response with dates in pipe-separated format
            String successResponse = "success|" + startDateStr + "|" + endDateStr;
            response.setContentType("text/plain");
            response.getWriter().write(successResponse);
            response.sendRedirect("viewSchedules");
        } catch (ParseException e) {
            e.printStackTrace();
            sendErrorResponse(response, "Invalid date format.");
        }
    }
}
