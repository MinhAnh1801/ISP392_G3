
import DAO.LecturerScheduleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/finalizeAssignment")
public class LecturerTimetable extends HttpServlet {

    private final LecturerScheduleDAO lecturerScheduleDAO = new LecturerScheduleDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
        String[] selectedScheduleIds = request.getParameterValues("scheduleIds");

        if (selectedScheduleIds == null || selectedScheduleIds.length == 0) {
            request.setAttribute("error", "No schedules selected. Please select schedules to assign.");
            request.getRequestDispatcher("assignLecturer.jsp").forward(request, response);
            return;
        }
        List<Integer> overlappingSchedules = new ArrayList<>();

        try {
            for (String scheduleIdStr : selectedScheduleIds) {
                int scheduleId = Integer.parseInt(scheduleIdStr);

                // Check if this schedule overlaps
                if (lecturerScheduleDAO.isScheduleOverlapping(lecturerId, scheduleId)) {
                    overlappingSchedules.add(scheduleId);
                    request.setAttribute("error", "Schedule overlap");
                    request.getRequestDispatcher("assignLecturer.jsp").forward(request, response);
                    break;
                }
            }
            if (!overlappingSchedules.isEmpty()) {
                // Abort assignment if any overlap exists and forward error to JSP
                request.setAttribute("error", "Some selected schedules overlap with the lecturer's existing timetable.");
                request.getRequestDispatcher("assignLecturer.jsp").forward(request, response);
                return;
            }
            // Second pass: Assign all non-overlapping schedules
            for (String scheduleIdStr : selectedScheduleIds) {
                int scheduleId = Integer.parseInt(scheduleIdStr);
                lecturerScheduleDAO.assignScheduleToLecturer(lecturerId, scheduleId);
            }
            // Set success message
            request.setAttribute("msg", "Schedules assigned successfully.");
            request.getRequestDispatcher("assignLecturer.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred during assignment. Please try again.");
        }
    }
}
