<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List in Class</title>

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
                <h4>Students in Class: ${classId}</h4>
            </div>
            <div class="card-body">

                <c:if test="${empty students}">
                    <p class="text-center">No students found in this class.</p>
                </c:if>

                <c:if test="${not empty students}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Student ID</th>
                                <th>Student Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.studentId}</td>
                                    <td>${student.studentName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
