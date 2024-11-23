/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import Model.Major;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.ClassDAO;
import models.Classes;
import models.Subjects;
import models.SubjectsDAO;

/**
 *
 * @author admin
 */
public class SubjectsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SubjectsDAO d = new SubjectsDAO();

        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            List<Major> majors = d.getMajors();
            log(majors.get(1).getName());
            request.setAttribute("majors", majors);
            request.getRequestDispatcher("AddSubjects.jsp").forward(request, response);
        } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            String id = request.getParameter("id");
            d.delete(id);
            response.sendRedirect("subject");
        } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
            String id = request.getParameter("id");
            Subjects s = d.getSubjectsById(id);
            List<Major> majors = d.getMajors();
            request.setAttribute("s", s);
            request.setAttribute("majors", majors);
            request.getRequestDispatcher("UpdateSubjects.jsp").forward(request, response);
        } else {
            ArrayList<Subjects> data = d.getSubjects();
            //search
            if (request.getParameter("search") != null && !request.getParameter("name").isBlank()) {
                String name = request.getParameter("name");
                data = d.getSubjectByName(name);
            }
            request.setAttribute("data", data);
            request.getRequestDispatcher("ListSubjects.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String credits = request.getParameter("credits");
        String description = request.getParameter("description");
        String semester = request.getParameter("semester");
        int tuition = Integer.parseInt(request.getParameter("tuition"));
        int major_id = Integer.parseInt(request.getParameter("selectedMajor"));
        SubjectsDAO d = new SubjectsDAO();

        if (request.getParameter("add") != null) {
            if (!d.isDuplicated(major_id, code)) {
                d.insert(new Subjects("", code, name, credits, description, semester, tuition, major_id));
                response.sendRedirect("subject");
            } else {
                ArrayList<Subjects> data = d.getSubjects();
                request.setAttribute("data", data);
                request.setAttribute("duplicateMessage", "Subject already exists with this major and name.");
                request.getRequestDispatcher("ListSubjects.jsp").forward(request, response);
            }
        }
        if (request.getParameter("update") != null) {
            String id1 = request.getParameter("id");
            if (!d.isDuplicated(major_id, code)) {
                d.update(new Subjects(id1, code, name, credits, description, semester, tuition, major_id));
                response.sendRedirect("subject");
            } else {
                request.setAttribute("msg", "Duplicated");
                request.getRequestDispatcher("UpdateSubjects.jsp").forward(request, response);
            }
        }
    }

}
