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
import java.util.Map;

@WebServlet("/viewAvailableSchedules")
public class ViewAvailableSchedulesServlet extends HttpServlet {

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
        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        log(request.getParameter("subject_id"));
        // Retrieve available schedules for the student’s major
        int studentId = (int) session.getAttribute("user");
        // Retrieve schedules grouped by class for eligible subjects only
        Map<Integer, List<Schedule>> classSchedulesMap = scheduleDAO.getSchedulesForStudentMajor(studentId,subject_id);

        // Set grouped schedules as request attribute and forward to JSP
        request.setAttribute("classSchedulesMap", classSchedulesMap);
        request.getRequestDispatcher("availableSchedules.jsp").forward(request, response);
    }
}
