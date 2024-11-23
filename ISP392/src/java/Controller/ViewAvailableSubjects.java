package Controller;

import DAO.SubjectDAO;
import Model.Subjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/availableSubjects")
public class ViewAvailableSubjects extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        int studentId = (int) session.getAttribute("user"); // Assume student ID is stored in session
        // Retrieve eligible subjects for the student
        List<Subjects> eligibleSubjects = subjectDAO.getEligibleSubjectsForStudent(studentId);

        // Set eligible subjects as request attribute and forward to JSP
        request.setAttribute("eligibleSubjects", eligibleSubjects);
        request.getRequestDispatcher("availableSubjects.jsp").forward(request, response);
    }
}
