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
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@300;400;500;600;700&display=swap" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" rel="stylesheet">

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
                font-weight: 600;
            }

            .main-content {
                margin: 50px auto;
                width: 85%;
            }

            h2 {
                margin-bottom: 30px;
                color: #333;
                font-weight: 700;
                text-align: center;
            }

            table {
                font-size: 14px;
                color: #555;
            }

            table.dataTable thead th {
                background-color: #007bff;
                color: white;
            }

            table.dataTable tbody tr:hover {
                background-color: #f1f1f1;
            }

            .btn-view {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 6px 12px;
                border-radius: 3px;
                font-size: 12px;
                cursor: pointer;
            }

            .btn-view:hover {
                background-color: #0056b3;
            }

            .alert-success {
                font-size: 14px;
                margin-bottom: 20px;
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
                <div class="alert alert-success" role="alert" id="messageAlert">
                    ${sessionScope.message}
                </div>
                <c:set var="sessionScope.message" value="" scope="session"/>
            </c:if>

            <!-- Assignments Table -->
            <div class="table-responsive">
                <table id="assignmentsTable" class="table table-bordered table-striped">
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

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

        <!-- jQuery and DataTables JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script>
            // Hide alert after 5 seconds
            setTimeout(function () {
                var messageAlert = document.getElementById("messageAlert");
                if (messageAlert) {
                    messageAlert.style.display = "none";
                }
            }, 5000);

            // Initialize DataTables
            $(document).ready(function () {
                $('#assignmentsTable').DataTable({
                    "lengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                    "language": {
                        "search": "Search:",
                        "lengthMenu": "Display _MENU_ records per page",
                        "info": "Showing _START_ to _END_ of _TOTAL_ assignments",
                        "paginate": {
                            "first": "First",
                            "last": "Last",
                            "next": "Next",
                            "previous": "Previous"
                        }
                    },
                    "order": [[3, "asc"]] // Sort by Due Date by default
                });
            });
        </script>
    </body>
</html>
