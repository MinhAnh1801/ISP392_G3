package TimetableController;

import DAO.TimetableDAO;
import Model.Lecturer_Timetable;
import Model.Schedule;
import Model.Timetable;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewtimetable")
public class LecturerTimetable extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final TimetableDAO timetableDAO = new TimetableDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        int lecturer_id = (int) session.getAttribute("user");
        try {
            log(String.valueOf(lecturer_id));
            // Fetch the student's timetable
            List<Lecturer_Timetable> timetable = timetableDAO.getLecturerTimetable(lecturer_id);
            for (Lecturer_Timetable timetable1 : timetable) {
                log(timetable1.getDay_of_week());
            }
            // Set the timetable as a request attribute and forward to the JSP
            request.setAttribute("timetable", timetable);
            request.getRequestDispatcher("LecturerTimetable.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the timetable.");
        }
    }
}
