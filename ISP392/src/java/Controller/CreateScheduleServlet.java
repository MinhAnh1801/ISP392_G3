package Controller;

import DAO.DAO;
import DAO.ScheduleDAO;
import Model.Classrooms;
import Model.Subjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.Classes;

@WebServlet("/createSchedule")
public class CreateScheduleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    private final DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the list of classes and classrooms from DAO
        List<Classes> classList = scheduleDAO.getAllClasses();
        List<Classrooms> classroomList = scheduleDAO.getAllClassrooms();
        List<Subjects> subjectList = dao.getAllSubjectCodes();
        // Set attributes for the JSP
        request.setAttribute("classList", classList);
        request.setAttribute("classroomList", classroomList);
        request.setAttribute("subjectList", subjectList);
        // Forward to the JSP
        request.getRequestDispatcher("createSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Process the form submission (same as before)
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String classId = request.getParameter("classId");
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));
        int subjectID = Integer.parseInt(request.getParameter("subjectid"));
        boolean success = scheduleDAO.createSchedule(dayOfWeek, startTime, endTime, classId, classroomId,subjectID);

        if (success) {
            response.sendRedirect("createSchedule.jsp?success=true");
        } else {
            response.sendRedirect("createSchedule.jsp?error=true");
        }
    }
}
