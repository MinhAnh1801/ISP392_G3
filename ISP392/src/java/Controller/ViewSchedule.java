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
import java.util.List;

@WebServlet("/viewSchedules")
public class ViewSchedule extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();

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
}
