<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecturers List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
        }
        .table-header {
            background-color: #ff9800; /* Orange background for header */
            color: white; /* White text color */
        }
        .feedback-yes {
            color: red; /* Red text for feedback */
        }
        .btn-orange {
            background-color: #ff9800; /* Orange button */
            border: none; /* Remove border */
            color: white; /* White text color */
        }
        .btn-orange:hover {
            background-color: #e68a00; /* Darker orange on hover */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lecturers List</h1>
        <table class="table table-bordered">
            <thead class="table-header">
                <tr>
                    <th>Lecturer ID</th>
                    <th>Full Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="lecturer" items="${lecturers}">
                    <tr>
                        <td>${lecturer.lecturerId}</td>
                        <td>${lecturer.fullName}</td>
                        <td>
                            <c:choose>
                               <c:when test="${lecturer.hasFeedback}">
                                    <span class="feedback-yes">Đã phản hồi</span>
<!--<a href="${pageContext.request.contextPath}/editFeedback?lecturerId=${lecturer.lecturerId}&action=update" class="btn btn-orange btn-sm ml-2">Update</a>-->

                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/viewFeedback" method="get">
                                        <input type="hidden" name="lecturerId" value="${lecturer.lecturerId}" />
                                        <input type="submit" value="Feedback" class="btn btn-orange" />
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS and dependencies (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
