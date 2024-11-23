<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Assignment Submission</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;600;700&display=swap" rel="stylesheet">

        <style>
            body {
                font-family: 'Be Vietnam Pro', sans-serif;
                background-color: #f8f9fa;
            }
            .navbar {
                background-color: #FF8C00;
            }
            .navbar-brand {
                color: white;
                font-size: 1.5rem;
            }
            .main-content {
                margin: 50px auto;
                width: 60%;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            h2 {
                font-weight: 700;
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }
            .form-group label {
                font-weight: 600;
            }
            .btn-download, .btn-submit {
                display: inline-block;
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                margin-top: 15px;
                border: none;
                font-size: 1rem;
            }
            .btn-download:hover {
                background-color: #0056b3;
            }
            .btn-submit:hover {
                background-color: #0069d9;
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="main-content">
            <h2>Assignment Details</h2>


            <form action="${pageContext.request.contextPath}/dashboardAssignments" method="post" enctype="multipart/form-data" onsubmit="return validateDueDate()">
                <!-- Assignment Name -->
                <div class="form-group mb-3">
                    <label for="assignmentName">Assignment Name</label>
                    <input type="text" class="form-control" id="assignmentName" name="assignmentName" value="${assignment.getAssignmentName()}" required>
                </div>

                <!-- Assignment Description -->
                <div class="form-group mb-3">
                    <label for="assignmentDescription">Assignment Description</label>
                    <textarea class="form-control" id="assignmentDescription" name="assignmentDescription" required>${assignment.getAssignmentDecription()}</textarea>
                </div>

                <!-- Due Date -->
                <div class="form-group mb-3">
                    <label for="dueDate">Due Date</label>
                    <input type="date" class="form-control" id="dueDate" name="dueDate" value="${assignment.getDueDate()}" required>
                </div>

                <!-- File Upload -->
                <div class="form-group mb-3">
                    <label for="fileUpload">Upload Assignment File</label>
                    <input type="file" class="form-control" id="fileUpload" name="fileUpload">
                </div>

                <!-- Hidden fields for assignment ID and action -->
                <input type="hidden" name="assignmentId" value="${assignment.getID()}">
                <input type="hidden" name="action" value="update">

                <!-- Submit Button -->
                <button type="submit" class="btn-submit">Update Assignment</button>
            </form>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script>
                       function validateDueDate() {
                           const dueDate = document.getElementById("dueDate").value;
                           const today = new Date().toISOString().split("T")[0];

                           if (dueDate < today) {
                               alert("Due Date cannot be in the past. Please select a valid date.");
                               return false;
                           }
                           return true;
                       }
        </script>
    </body>
</html>
