<%-- 
    Document   : ViewAvailableDormRooms
    Created on : Oct 10, 2024, 9:36:00 PM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Available Dorm Rooms</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>

        <!-- Navigation bar -->
        <nav class="navbar navbar-light bg-warning mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">University Academic Portal</a>
                <div class="d-flex">
                    <img src="path_to_avatar_image" alt="Avatar" class="rounded-circle" width="40" height="40">
                    <span class="navbar-text mx-3">Khúc Xuân Hào</span>
                    <a href="logout" class="btn btn-outline-dark">Log out</a>
                </div>
            </div>
        </nav>

        <!-- Main content -->
        <div class="container">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4>Available Dorm Rooms</h4>
                </div>
                <div class="card-body">

                    <c:if test="${empty availableDormRooms}">
                        <p class="text-center">No dorm rooms are currently available.</p>
                    </c:if>

                    <c:if test="${not empty availableDormRooms}">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Room Number</th>
                                    <th>Capacity</th>
                                    <th>Available Capacity</th>
                                    <th>Building</th>
                                    <th>Room Type</th>
                                    <th>Details</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dormRoom" items="${availableDormRooms}">
                                    <tr>
                                        <td>${dormRoom.roomNumber}</td>
                                        <td>${dormRoom.capacity}</td>
                                        <td>${dormRoom.availableCapacity}</td>
                                        <td>${dormRoom.building}</td>
                                        <td>${dormRoom.roomType}</td>
                                        <td>${dormRoom.detail}</td>
                                        <td>${dormRoom.price}</td>
                                        <td><a href="Register.jsp" class="btn btn-primary btn-sm">Đăng kí</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                 <!--   <a href="ViewListDormRoom.jsp" class="btn btn-secondary">Back to All Rooms</a> -->
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

