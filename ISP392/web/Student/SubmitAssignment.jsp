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
                <a class="navbar-brand" href="#">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="main-content">
            <h2>Assignment Details</h2>

            <form action="assignments" method="post" enctype="multipart/form-data">
                <!-- Assignment Name -->
                <div class="form-group mb-3">
                    <label>Assignment Name</label>
                    <div>${assignment.getAssignmentName()}</div>
                </div>

                <!-- Subject Code -->
                <div class="form-group mb-3">
                    <label>Subject Code</label>
                    <div>
                        <c:forEach items="${listSubjects}" var="subjectItem">
                            <c:if test="${subjectItem.getId() == a.getSubjectID()}">
                                ${subjectItem.getCode()}
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- Class -->
                <div class="form-group mb-3">
                    <label>Class</label>
                    <div>
                        <c:forEach items="${listClass}" var="classItem"> 
                            <c:if test="${classItem.ID == a.getClassID()}">
                                ${classItem.getClassName()}
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- Due Date -->
                <div class="form-group mb-3">
                    <label>Due Date</label>
                    <div>${assignment.getDueDate()}</div>
                </div>

                <!-- Assignment Description -->
                <div class="form-group mb-3">
                    <label>Assignment Description</label>
                    <div>${assignment.getAssignmentDecription()}</div>
                </div>

                <!-- Download File -->
                <div class="form-group mb-3">
                    <label>Download File</label>
                    <div>
                        <a href="${assignment.getFilePath()}" class="btn-download" download>Download</a>
                    </div>
                </div>

                <!-- File Upload for Submission -->
                <div class="form-group mb-3">
                    <label for="fileUpload">Upload Assignment File</label>
                    <input type="file" class="form-control" id="fileUpload" name="fileUpload" required>
                </div>

                <!-- Hidden fields for assignment ID and action -->
                <input type="hidden" name="assignmentId" value="${assignment.ID}">
                <input type="hidden" name="action" value="submit">

                <!-- Submit Button -->
                <button type="submit" class="btn-submit">Submit Assignment</button>
            </form>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
