package Controller;

import DAO.ScheduleDAO;
import DAO.TimetableDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/registerSchedule")
public class RegisterScheduleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    private final TimetableDAO timetableDAO = new TimetableDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int studentId = (int) session.getAttribute("user");
// Print all parameters for debugging
        request.getParameterMap().forEach((key, value) -> log("Parameter: " + key + " = " + String.join(", ", value)));
        // Retrieve classId from the form data
        String classIdParam = request.getParameter("classId");
        log("classid"+request.getParameter("classId"));
        int classId = (classIdParam != null && !classIdParam.isEmpty()) ? Integer.parseInt(classIdParam) : -1;
        // Retrieve subject_id from the URL parameter
        String subjectIdParam = request.getParameter("subject_id");
        int subjectId = (subjectIdParam != null && !subjectIdParam.isEmpty()) ? Integer.parseInt(subjectIdParam) : -1;
        if (classId == -1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid class selection. Please try again.");
            return;
        }

        try {
            log("subject "+subjectIdParam);
            // Retrieve all schedules associated with the selected class
            List<Integer> scheduleIds = scheduleDAO.getScheduleIdsByClassId(classId,subjectId);
            if (scheduleIds.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("No schedules found for the selected class.");
                return;
            }
            // Register each schedule for the student
            for (int scheduleId : scheduleIds) {
                timetableDAO.addScheduleToTimetable(studentId, scheduleId);
            }

            // Add a payment entry
            int amount = scheduleDAO.getTuitionBySubjectId(subjectId);
            timetableDAO.addPayment(studentId, amount, "Pending", "Tuition");
            timetableDAO.addStu_Sub(studentId, subjectId);
            timetableDAO.addtoClass(studentId, classId);
            response.sendRedirect("availableSubjects");
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred during registration. Please try again.");
        }
    }
}
