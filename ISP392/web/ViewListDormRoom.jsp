<%@ page import="java.util.List" %>
<%@ page import="Model.DormRooms" %>
<%@ page import="DAO.DormRoomsDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List of Dorm Rooms</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <!-- Custom styles for this page -->
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .table th {
                background-color: #ff9800;
                color: white;
            }
            .btn-custom {
                background-color: #ff9800;
                color: white;
            }
        </style>

        <!-- JavaScript để xác nhận việc xóa -->
        <script type="text/javascript">
            function confirmDelete(id) {
                var result = confirm("Are you sure you want to delete this dorm room?");
                if (result) {
                    window.location.href = 'deletedorm?id=' + id;
                } else {
                    return false;
                }
            }
        </script>
    </head>

    <body>

        <!-- Navigation bar -->
        <nav class="navbar navbar-light bg-[#d76325] mb-4">
            <div class="container-fluid">
                <a class="navbar-brand text-3xl text-white" href="home">University Academic Portal</a>
                <div class="d-flex">
                </div>
            </div>
        </nav>

        <!-- Main content -->
        <div class="container">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4>List of Dorm Rooms</h4>
                </div>
                <div class="card-body">
                    <a href="AddDormRoom.jsp" class="btn btn-custom mb-3">Add New Dorm Room</a>
                    <div class="card-header bg-warning text-white text-center">
                        <form action="searchDorm" method="post" class="d-flex mt-3">
                            <input type="text" name="keyword" class="form-control me-2" placeholder="Enter room number or building" required>
                            <button type="submit" class="btn btn-custom">Search</button>
                        </form>
                    </div>

                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Room Number</th>
                                <th>Capacity</th>
                                <th>Available Capacity</th>
                                <th>Building</th>
                                <th>Room Type</th>
                                <th>Price</th> 
                                <th>Detail</th> 
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                           <%
                        String keyword = request.getParameter("keyword");
                        if (keyword == null ) {
                            DormRoomsDAO dao = new DormRoomsDAO();
                            List<DormRooms> dormRooms = dao.getAllDormRooms();
                            request.setAttribute("dormRooms", dormRooms);
                        }
                            %>


                            <c:forEach var="dormRoom" items="${dormRooms}">
                                <tr>
                                    <td>${dormRoom.roomNumber}</td>
                                    <td>${dormRoom.capacity}</td>
                                    <td>${dormRoom.availableCapacity}</td>
                                    <td>${dormRoom.building}</td>
                                    <td>${dormRoom.roomType}</td>
                                    <td>${dormRoom.price}</td> 
                                    <td>${dormRoom.detail}</td> 
                                    <td>
                                        <a href="editdorm?id=${dormRoom.id}" class="btn btn-primary btn-sm">Edit</a>
                                        <a href="javascript:void(0)" class="btn btn-danger btn-sm" onclick="return confirmDelete(${dormRoom.id})">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS (Optional if you need JS for dropdowns, modals, etc.) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
