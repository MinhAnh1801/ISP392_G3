<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Feedback Management</title>
    
    <!-- Link to Bootstrap 4 CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

    <style>
        /* Custom main color */
        :root {
            --main-color: #D76325;
        }

        .navbar {
            background-color: var(--main-color);
        }

        .navbar-brand {
            color: white !important;
        }

        .table th, .table td {
            vertical-align: middle;
        }

        .btn-warning {
            background-color: var(--main-color);
            border-color: var(--main-color);
        }

        .btn-warning:hover {
            background-color: darken(var(--main-color), 10%);
            border-color: darken(var(--main-color), 10%);
        }

        .btn-info {
            background-color: var(--main-color);
            border-color: var(--main-color);
        }

        .btn-info:hover {
            background-color: darken(var(--main-color), 10%);
            border-color: darken(var(--main-color), 10%);
        }

        .alert-info {
            background-color: var(--main-color);
            color: white;
        }

        .action-buttons button {
            margin-right: 10px;
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

    <div class="container mt-5">
        <h1>Admin Feedback Management</h1>

        <!-- Check if feedbackForms is not null -->
        <c:if test="${not empty listFeedbackForms}">
            <div class="table-container">
                <table id="feedbackTable" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Created At</th>
                            <th>Lecturer</th>
                            <th>Subject</th>
                            <th>Stauts</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through feedbackForms list and display each feedback -->
                        <c:forEach var="feedback" items="${listFeedbackForms}">
                            <tr>
                                <td>${feedback.id}</td>
                                <td>${feedback.startDate}</td>
                                <td>${feedback.endDate}</td>
                                <td>${feedback.createdAt}</td>
                                <td>${feedback.lecturerId.fullName} - ${feedback.lecturerId.email}</td>
                                <td>${feedback.subjectId.code}</td>
                                <td>${feedback.subjectId}</td>
                                <td class="action-buttons">
                                    <button class="btn btn-warning btn-sm">Edit</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <!-- Message when no feedback forms are available -->
        <c:if test="${empty listFeedbackForms}">
            <p class="alert alert-info">No feedback forms available.</p>
        </c:if>

    </div>

    <!-- Link to Bootstrap 4 JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- jQuery (required for DataTables) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- DataTables JS -->
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

    <!-- Initialize DataTables -->
    <script>
        $(document).ready(function() {
            $('#feedbackTable').DataTable({
                "paging": true,
                "lengthChange": true,
                "searching": true,
                "ordering": true,
                "info": true,
                "autoWidth": true
            });
        });
    </script>

</body>
</html>
