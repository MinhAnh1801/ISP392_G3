import DAO.ScheduleDAO;
import DAO.TimetableDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

        // Debugging parameters
        request.getParameterMap().forEach((key, value) -> log("Parameter: " + key + " = " + String.join(", ", value)));
        
        String classIdParam = request.getParameter("classId");
        log("class "+classIdParam);
        int classId = (classIdParam != null && !classIdParam.isEmpty()) ? Integer.parseInt(classIdParam) : -1;
        String subjectIdParam = request.getParameter("subject_id");
        log("sid "+subjectIdParam);
        int subjectId = (subjectIdParam != null && !subjectIdParam.isEmpty()) ? Integer.parseInt(subjectIdParam) : -1;
        
        if (classId == -1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid class selection. Please try again.");
            return;
        }

        try {
            List<Integer> scheduleIds = scheduleDAO.getScheduleIdsByClassId(classId, subjectId);
            if (scheduleIds.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("No schedules found for the selected class.");
                return;
            }

            // Check for overlapping schedules before registration
            for (int scheduleId : scheduleIds) {
                log(String.valueOf(scheduleId));
                String dayOfWeek = scheduleDAO.getScheduleById(scheduleId,classId).getDay_of_week();
                log(dayOfWeek);
                String startTime = scheduleDAO.getScheduleById(scheduleId,classId).getStart_time();
                log(startTime);
                String endTime = scheduleDAO.getScheduleById(scheduleId,classId).getEnd_time();
                log(endTime);
                // Check if this schedule would overlap with the student's existing schedules
                boolean hasOverlap = timetableDAO.hasOverlap(studentId, dayOfWeek, startTime, endTime);
                if (hasOverlap) {
                    request.setAttribute("msg", "Schedule conflict detected. Please select a different class or time.");
                    request.getRequestDispatcher("availableSchedules.jsp").forward(request, response);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    response.getWriter().write("Schedule conflict detected. Please select a different class or time.");
                    String check = "overlap";
                    log(check);
                    return;
                }
                else{
                    request.setAttribute("msg", "Register successfully, please check paid item.");
                    request.getRequestDispatcher("availableSchedules.jsp").forward(request, response);
                }
            }

            // Register each non-overlapping schedule for the student
            for (int scheduleId : scheduleIds) {
                timetableDAO.addScheduleToTimetable(studentId, scheduleId);
                timetableDAO.updateSchedule(scheduleId);
            }
            
            // Add payment entry
            int amount = scheduleDAO.getTuitionBySubjectId(subjectId);
            timetableDAO.addPayment(studentId, amount, "Pending", "Tuition");
            timetableDAO.addStu_Sub(studentId, subjectId);
            timetableDAO.addtoClass(studentId, classId);
            
            response.sendRedirect("availableSubjects");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred during registration. Please try again.");
        }
    }
}
