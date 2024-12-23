<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List Assignments</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Preconnect -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Custom styles -->
        <style>
            body {
                font-family: 'Be Vietnam Pro', sans-serif;
            }

            .navbar {
                background-color: #FF8C00;
            }

            .navbar-brand {
                color: white;
                font-size: 24px;
            }

            .main-content {
                margin: 50px auto;
                width: 70%;
                text-align: center;
            }

            h2 {
                margin-bottom: 20px;
                color: #333;
            }

            .table-responsive {
                margin-top: 20px;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            .btn-view {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 3px;
                cursor: pointer;
            }

            .btn-view:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="main-content">
            <h2>List Assignments</h2>


            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-success" role="alert" id="messageAlert">
                    ${sessionScope.message}
                </div>
                <!-- Xóa thông báo khỏi session sau khi hiển thị -->
                <c:set var="sessionScope.message" value="" scope="session"/>
            </c:if>

            <!-- Assignments Table -->
            <div class="table-responsive">
                <table id="facilitiesTable" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Subject Code</th>
                            <th>Class</th>
                            <th>Due Date</th>
                            <th>Student Progress</th>
                            <th>Instructor Review</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listAssignments}" var="a">
                            <tr>
                                <td>${a.getAssignmentName()}</td>
                                <td>
                                    <c:forEach items="${listSubjects}" var="subjectItem">
                                        <c:if test="${subjectItem.getId() == a.getSubjectID()}">
                                            ${subjectItem.getCode()}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach items="${listClass}" var="classItem"> 
                                        <c:if test="${classItem.ID == a.getClassID()}">
                                            ${classItem.getClassName()}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${a.getDueDate()}</td>
                                <td>${progressMap[a.getID()]}</td>
                                <td>${reviewStatusMap[a.getID()]}</td>
                                <td>
                                    <form action="assignments" method="post" style="display: inline;">
                                        <input type="hidden" name="assignmentId" value="${a.getID()}">
                                        <input type="hidden" name="action" value="do">
                                        <button type="submit" class="btn-view">View</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            // Ẩn phần tử thông báo sau 5 giây
            setTimeout(function () {
                var messageAlert = document.getElementById("messageAlert");
                if (messageAlert) {
                    messageAlert.style.display = "none";
                }
            }, 5000);
        </script>
        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
