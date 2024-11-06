<%-- 
    Document   : viewMyDormRoom.jsp
    Created on : Nov 5, 2024, 7:48:19 PM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Dorm Room</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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

    <div class="container mt-5">
        <h1 class="text-center">Your Dorm Room Information</h1>
        <c:choose>
            <c:when test="${not empty dormResident}">
                <table class="table table-bordered mt-4">
                    <tr>
                        <th>Room ID</th>
                        <td>${dormResident.dormRoomId}</td>
                    </tr>
                    <tr>
                        <th>Check-in Date</th>
                        <td>${dormResident.checkInDate}</td>
                    </tr>
                    <tr>
                        <th>Check-out Date</th>
                        <td>${dormResident.checkOutDate}</td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td>${dormResident.status}</td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-center">You have not registered for a dorm room yet.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
