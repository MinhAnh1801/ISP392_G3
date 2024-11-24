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

        <!-- DataTables Bootstrap CSS -->
        <link href="https://cdn.datatables.net/1.13.3/css/dataTables.bootstrap5.min.css" rel="stylesheet">

        <!-- Font Preconnect -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Custom styles -->
        <style>
            body {
                font-family: 'Be Vietnam Pro', sans-serif;
            }
             body {
                background-color: #ffffff; /* Màu nền trắng */
                font-family: Arial, sans-serif; /* Font chữ */
            }
            h1 {
                color: #ff5722; /* Màu cam cho tiêu đề */
                text-align: center; /* Căn giữa tiêu đề */
                margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
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

            .btn-delete {
                background-color: #dc3545;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 3px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-delete:hover {
                background-color: #c82333;
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="main-content">
            <h2>List Assignments</h2>

            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-success" id="successMessage" role="alert">
                    ${sessionScope.message}
                </div>
                <c:remove var="message" scope="session" />
            </c:if>

            <!-- Assignments Table -->
            <div class="table-responsive">
                <table id="assignmentsTable" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Subject Code</th>
                            <th>Class</th>
                            <th>Due Date</th>
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
                                <td>
                                    <form action="dashboardAssignments" method="post" style="display: inline;">
                                        <input type="hidden" name="assignmentId" value="${a.getID()}">
                                        <input type="hidden" name="action" value="view">
                                        <button type="submit" class="btn-view">Update</button>
                                    </form>
                                    <form action="dashboardAssignments" method="post" style="display: inline;">
                                        <input type="hidden" name="assignmentId" value="${a.getID()}">
                                        <input type="hidden" name="action" value="delete">
                                        <button type="submit" class="btn-delete" onclick="return confirm('Are you sure you want to delete this assignment?');">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            window.addEventListener('DOMContentLoaded', (event) => {
                const successMessage = document.getElementById("successMessage");
                if (successMessage) {
                    setTimeout(() => {
                        successMessage.style.display = "none";
                    }, 3500);
                }
            });
        </script>

        <!-- jQuery and DataTables JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.3/js/dataTables.bootstrap5.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#assignmentsTable').DataTable({
                    "paging": true,
                    "searching": true,
                    "ordering": true,
                    "info": true,
                    "pageLength": 5,
                    "lengthMenu": [5, 10, 15],
                    "language": {
                        "search": "Search assignments:",
                        "lengthMenu": "Show _MENU_ assignments",
                        "info": "Showing _START_ to _END_ of _TOTAL_ assignments",
                        "infoFiltered": "(filtered from _MAX_ total assignments)"
                    }
                });
            });
        </script>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
