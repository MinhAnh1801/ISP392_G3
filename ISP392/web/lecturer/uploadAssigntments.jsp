<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>University Academic Portal - Add Assignment</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

        <style>
            .navbar {
                background-color: #FF8C00;
            }

            .navbar-brand {
                color: white;
                font-size: 24px;
                font-weight: bold;
            }

            .main-content {
                margin: 50px auto;
                max-width: 600px;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            h2 {
                font-size: 1.8rem;
                font-weight: bold;
                margin-bottom: 20px;
                color: #333;
                text-align: center;
            }

            .form-group label {
                font-weight: 600;
                color: #555;
            }

            .btn-submit, .btn-back {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                font-weight: bold;
            }

            .btn-submit {
                background-color: #007bff;
                color: white;
                border: none;
                margin-top: 15px;
            }

            .btn-submit:hover {
                background-color: #0056b3;
            }

            .btn-back {
                background-color: #6c757d;
                color: white;
                border: none;
                margin-top: 10px;
            }

            .btn-back:hover {
                background-color: #5a6268;
            }
        </style>
    </head>

    <body>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="main-content">
            <h2>Add New Assignment</h2>

            <form action="uploadAssignment" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="add">

                <div class="form-group mb-3">
                    <label for="subjectID">Subject</label>
                    <select class="form-control" id="subjectID" name="subjectID" required>
                        <c:forEach var="subjectId" items="${servletA.getAllSubjectIds()}">
                            <option value="${subjectId}">
                                ${servletA.getSubjectCode(subjectId)}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label for="classID">Class</label>
                    <select class="form-control" id="classID" name="classID" required>
                        <c:forEach var="classId" items="${servletA.getAllClassIds()}">
                            <option value="${classId}">
                                ${servletA.getClassName(classId)}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group mb-3">
                    <label for="assignmentDescription">Assignment Name</label>
                    <textarea class="form-control" id="assignmentDescription" name="assignmentName" rows="3" placeholder="Enter assignment name" required></textarea>
                </div>

                <div class="mb-3">
                    <label for="assignmentDescription" class="form-label">Assignment Description</label>
                    <textarea class="form-control" id="assignmentDescription" name="assignmentDescription" rows="3" required></textarea>
                </div>

                <!-- Thêm trường để chọn file -->
                <div class="mb-3">
                    <label for="assignmentFile" class="form-label">Attach File</label>
                    <input type="file" class="form-control" id="assignmentFile" name="assignmentFile" required>
                </div>

                <div class="form-group mb-3">
                    <label for="assignedDate">Assigned Date</label>
                    <input type="date" class="form-control" id="assignedDate" name="assignedDate" required>
                </div>

                <div class="form-group mb-3">
                    <label for="dueDate">Due Date</label>
                    <input type="date" class="form-control" id="dueDate" name="dueDate" required>
                </div>

                <button type="submit" class="btn btn-submit">Add Assignment</button>
            </form>
        </div>
    </body>

</html>
