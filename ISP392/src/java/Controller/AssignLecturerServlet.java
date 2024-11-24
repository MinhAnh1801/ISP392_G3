package Controller;

import DAO.LecturerScheduleDAO;
import Model.Schedule;
import Model.Subjects;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/assignLecturer")
public class AssignLecturerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final LecturerScheduleDAO lecturerScheduleDAO = new LecturerScheduleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("fetchGroupedSchedules".equals(action)) {
                // Get the subjectId parameter and check if it's valid
                String subjectIdParam = request.getParameter("subjectId");
                log(subjectIdParam);
                if (subjectIdParam == null || subjectIdParam.trim().isEmpty()) {
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Missing or invalid subject ID\"}");
                    return;
                }
                int subjectId = Integer.parseInt(subjectIdParam.trim());

                // Fetch classes and schedules grouped by class for the specified subject
                Map<String, List<Schedule>> groupedSchedules = lecturerScheduleDAO.getGroupedSchedulesBySubject(subjectId);
                log(groupedSchedules.toString());
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(groupedSchedules));

            } else {
                // Default action to load the assignLecturer.jsp with subjects
                String lecturerIdParam = request.getParameter("lecturerId");
                if (lecturerIdParam == null || lecturerIdParam.trim().isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid lecturer ID");
                    return;
                }
                int lecturerId = Integer.parseInt(lecturerIdParam.trim());

                // Fetch the lecturer's major and associated subjects
                int majorId = lecturerScheduleDAO.getMajorIdByLecturerId(lecturerId);
                List<Subjects> subjects = lecturerScheduleDAO.getSubjectsByMajor(majorId);

                // Set attributes for the JSP and forward
                request.setAttribute("lecturerId", lecturerId);
                request.setAttribute("subjects", subjects);
                request.getRequestDispatcher("assignLecturer.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid number format for subject or lecturer ID.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unable to fetch data.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submission for assigning schedules to lecturer
        try {
            int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
            String subjectId = request.getParameter("subjectId");

            // Get the selected schedule IDs as an array
            String[] selectedScheduleIds = request.getParameterValues("scheduleIds");

            if (selectedScheduleIds != null && selectedScheduleIds.length > 0) {
                for (String scheduleIdStr : selectedScheduleIds) {
                    int scheduleId = Integer.parseInt(scheduleIdStr);
//                    lecturerScheduleDAO.assignScheduleToLecturer(lecturerId, scheduleId);
                }
                // After successful assignment, redirect or notify success
                response.sendRedirect("assignLecturer?lecturerId=" + lecturerId + "&success=true");
            } else {
                // No schedules were selected
                response.sendRedirect("assignLecturer?lecturerId=" + lecturerId + "&error=noSelection");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("assignLecturer?lecturerId=" + request.getParameter("lecturerId") + "&error=assignmentFailed");
        }
    }
}
