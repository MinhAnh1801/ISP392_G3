package Controller.Admin;

import DAO.ClassroomFacilitiesDAO;
import DAO.ClassroomsDAO;
import Model.ClassroomFacilities;
import Model.Classrooms;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardClassrooms extends HttpServlet {

    private ClassroomsDAO classroomsDAO = new ClassroomsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Lấy danh sách tất cả các phòng học
        List<Classrooms> listClassrooms = classroomsDAO.findAll();
        request.setAttribute("listClassrooms", listClassrooms);

        request.getRequestDispatcher("dashboardClassrooms.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (action) {
            case "add":
                addClassroom(request, response);
                response.sendRedirect("dashboardClassrooms");
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Classrooms classroom = classroomsDAO.findById(id);
                request.setAttribute("classroom", classroom);
                request.getRequestDispatcher("editClassroom.jsp").forward(request, response);
                break;
            case "update":
                updateClassroom(request, response);
                break;
            case "delete":
                deleteClassroom(request, response);
                response.sendRedirect("dashboardClassrooms");
                break;
            case "checkClassroomName":
                checkClassroomName(request, response);
                break;
            case "detail":
                viewDetails(request, response);
                break;
//            case "updateFacilities":
//                updateFacilities(request, response);
//                break;
            default:
                response.sendRedirect("dashboardClassrooms");
        }
    }

    private void addClassroom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        Classrooms classroom = new Classrooms();
        classroom.setName(name);
        classroom.setCapacity(capacity);
        classroom.setLocation(location);

        classroomsDAO.insert(classroom);
    }

    private void checkClassroomName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String classroomName = request.getParameter("classroomname");
        boolean exists = classroomsDAO.classroomNameExists(classroomName);

        // Trả về kết quả dưới dạng văn bản
        if (exists) {
            response.getWriter().write("exists");
        } else {
            response.getWriter().write("available");
        }
    }

    private void updateClassroom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        // Lấy thông tin phòng học hiện tại từ cơ sở dữ liệu
        Classrooms currentClassroom = classroomsDAO.findById(id);

        // Kiểm tra nếu tên phòng học đã tồn tại và không trùng với phòng học hiện tại
        if (!currentClassroom.getName().equals(name) && classroomsDAO.classroomNameExists(name)) {
            // Nếu tên phòng học đã tồn tại và khác với tên hiện tại -> Trả về thông báo lỗi
            request.setAttribute("errorMessage", "Classroom name already exists. Please choose a different name.");
            request.setAttribute("classroom", currentClassroom); // Trả về thông tin phòng học hiện tại để điền vào form
            request.getRequestDispatcher("editClassroom.jsp").forward(request, response);
            return;
        }

        // Nếu không trùng tên, tiến hành cập nhật thông tin phòng học
        Classrooms classroom = new Classrooms();
        classroom.setID(id);
        classroom.setName(name);
        classroom.setCapacity(capacity);
        classroom.setLocation(location);

        classroomsDAO.update(classroom);

        // Đặt thông báo thành công
        request.getSession().setAttribute("successMessage", "Classroom updated successfully!");

        // Gọi sendRedirect để quay lại trang danh sách sau khi cập nhật thành công
        response.sendRedirect("dashboardClassrooms");
    }

    private void deleteClassroom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        classroomsDAO.deleteById(id);
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("id"));

        // Lấy thông tin cơ sở vật chất của lớp học từ DAO
        ClassroomFacilitiesDAO facilitiesDAO = new ClassroomFacilitiesDAO();
        ClassroomFacilities facilities = facilitiesDAO.findFacilitiesByClassroomId(classroomID);

        // Đặt thông tin vào request để chuyển đến trang JSP chi tiết
        request.setAttribute("facilities", facilities);

        // Chuyển đến trang JSP chi tiết cơ sở vật chất
        request.getRequestDispatcher("detailsClassroom.jsp").forward(request, response);
    }

//    private void updateFacilities(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int classroomID = Integer.parseInt(request.getParameter("id"));
//        int numberOfTables = Integer.parseInt(request.getParameter("numberOfTables"));
//        String tableCondition = request.getParameter("tableCondition");
//        int numberOfChairs = Integer.parseInt(request.getParameter("numberOfChairs"));
//        String chairCondition = request.getParameter("chairCondition");
//        int numberOfLights = Integer.parseInt(request.getParameter("numberOfLights"));
//        String lightCondition = request.getParameter("lightCondition");
//        int numberOfProjectors = Integer.parseInt(request.getParameter("numberOfProjectors"));
//        String projectorCondition = request.getParameter("projectorCondition");
//
//        ClassroomFacilities facilities = new ClassroomFacilities();
//        facilities.setClassroomID(classroomID);
//        facilities.setNumberOfTables(numberOfTables);
//        facilities.setTableCondition(tableCondition);
//        facilities.setNumberOfChairs(numberOfChairs);
//        facilities.setChairCondition(chairCondition);
//        facilities.setNumberOfLights(numberOfLights);
//        facilities.setLightCondition(lightCondition);
//        facilities.setNumberOfProjectors(numberOfProjectors);
//        facilities.setProjectorCondition(projectorCondition);
//
//        ClassroomFacilitiesDAO facilitiesDAO = new ClassroomFacilitiesDAO();
//        facilitiesDAO.update(facilities);
//
//        response.sendRedirect("dashboardClassrooms?action=detail&id=" + classroomID);
//    }

}
