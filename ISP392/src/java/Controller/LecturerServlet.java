package Controller;

import DAO.LecturerScheduleDAO;
import Model.Lecturer_Profile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/lecturers")
public class LecturerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final LecturerScheduleDAO lecturerDAO = new LecturerScheduleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Lecturer_Profile> lecturers = lecturerDAO.getAllLecturers();
            request.setAttribute("lecturers", lecturers);
            request.getRequestDispatcher("lecturerList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve lecturers");
            request.getRequestDispatcher("lecturerList.jsp").forward(request, response);
        }
    }
}
