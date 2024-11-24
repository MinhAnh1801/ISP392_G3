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


        <style>
            .alert-container {
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 1050;
                width: 300px;
            }
        </style>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">University Academic Portal</a>
            </div>
        </nav>

        <!--Hiển thị thông báo lỗi hoặc mess -->
        <div class="container mt-5 alert-container">
            <!-- Success Message -->
            <c:if test="${not empty mess}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" id="successAlert">
                    ${mess}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" id="errorAlert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
        </div>

        <script>
            function autoDismissAlert(alertId, timeout) {
                setTimeout(function () {
                    var alert = document.getElementById(alertId);
                    if (alert) {
                        var bsAlert = new bootstrap.Alert(alert);
                        bsAlert.close();
                    }
                }, timeout);
            }

            // Dismiss success alert after 5 seconds (5000 ms)
            var successAlert = document.getElementById('successAlert');
            if (successAlert) {
                autoDismissAlert('successAlert', 5000);
            }

            // Dismiss error alert after 5 seconds (5000 ms)
            var errorAlert = document.getElementById('errorAlert');
            if (errorAlert) {
                autoDismissAlert('errorAlert', 5000);
            }
        </script>
        <!-- kết thúc Hiển thị thông báo lỗi hoặc mess -->

        <div class="container mt-5">
            <form action="CreateFeedback" method="POST">
                
                
                
                  <div class="form-group">
                    <label for="subject_id">Subject:</label>
                    <select id="subject_id" name="subject_id" class="form-control" required onchange="redirectToCreateFeedback()">
                        <option value="">Select Subject</option>
                        <c:forEach var="subject" items="${listSubject}">
                            <option value="${subject.id}">${subject.code} - ${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <script>
                    function redirectToCreateFeedback() {
                        var subjectId = document.getElementById('subject_id').value;
                        if (subjectId) {
                            // Chuyển hướng đến trang CreateFeedback với phương thức GET
                            window.location.href = 'createFeedback?subject_id=' + subjectId;
                        }
                    }
                </script>

                <!-- Lecturer ID -->
                <div class="form-group">
                    <label for="lecturer_id">Lecturer:</label>
                    <select id="lecturer_id" name="lecturer_id" class="form-control" required>
                        <option value="">Select Lecturer</option>
                        <c:forEach var="lecturer" items="${lecturers}">
                            <option value="${lecturer.id}">${lecturer.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Start Date -->
                <div class="form-group">
                    <label for="start_date">Start Date:</label>
                    <input type="date" id="start_date" name="start_date" class="form-control" required>
                </div>

                <!-- End Date -->
                <div class="form-group">
                    <label for="end_date">End Date:</label>
                    <input type="date" id="end_date" name="end_date" class="form-control" required>
                </div>

              


                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary">Submit Feedback</button>
            </form>
        </div>


        <!-- Modal for Editing End Date -->


        <!-- Link to Bootstrap 4 JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>





    </body>
</html>
