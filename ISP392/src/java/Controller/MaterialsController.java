/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DAO;
import Model.Materials;
import Model.Subjects;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author khucx
 */
@WebServlet(name = "MaterialsController", urlPatterns = {"/lecturer/materials"})
public class MaterialsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null) {
            response.sendRedirect("/ISP392/login");
            return;
        }
        int lecturerId = (Integer) session.getAttribute("user");
        DAO dao = new DAO();
        List<Materials> materials = dao.getMaterialsByLecturerId(lecturerId);
        List<Subjects> subjectList = dao.getAllSubjectCodes();
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("materials", materials);
        request.getRequestDispatcher("materialsLect.jsp").forward(request, response);

                request.getRequestDispatcher("/profile/viewStudentProfile.jsp").forward(request, response);

            } else if (role ==2) {

                Lecturer_Profile lecturer = uDao.getLecturerProfileById(user_id);
                request.setAttribute("lecturerProfile", lecturer);

                request.getRequestDispatcher("/profile/viewLecturerProfile.jsp").forward(request, response);

            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("user");
        // check action null
        if (action.isEmpty()) {
            request.setAttribute("error", "action is empty");
        } // check update profile lecturer
        else if (action.equalsIgnoreCase("lecturer")) {

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String department = request.getParameter("department");
            String expertise = request.getParameter("expertise");
            int researchSkill = Integer.parseInt(request.getParameter("researchSkill"));
            int teachingSkill = Integer.parseInt(request.getParameter("teachingSkill"));
            int mentoringSkill = Integer.parseInt(request.getParameter("mentoringSkill"));

            UserDAO udao = new UserDAO();
            boolean updateProfile = udao.updateProfileLecturerById(id, fullName, email, phoneNumber, department,
                    expertise, researchSkill, teachingSkill, mentoringSkill);

            if (updateProfile) {
                request.setAttribute("mess", "Update Successes");
            } else {
                request.setAttribute("error", "Update False");
            }
        } else if (action.equalsIgnoreCase("student")) {
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            UserDAO udao = new UserDAO();
            boolean updateProfile = udao.updateStudentProfile(id, phoneNumber, address);

            if (updateProfile) {
                request.setAttribute("mess", "Update Successes");
            } else {
                request.setAttribute("error", "Update False");
            }
        }

        doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
