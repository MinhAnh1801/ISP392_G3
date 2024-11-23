<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Curriculum Overview</title>

        <!-- Custom CSS -->
        <style>
            body {
                background-color: #ffffff; /* Màu nền trắng */
                font-family: Arial, sans-serif; /* Font chữ */
            }
            h1 {
                color: #ff5722; /* Màu cam cho tiêu đề */
                text-align: center; /* Căn giữa tiêu đề */
                margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
            }
            .form-group {
                margin-bottom: 15px;
            }
            .hidden {
                display: none; /* Ẩn các phần tử */
            }
        </style>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container" style="margin: auto; padding: 20px;">
            <h1>Create Curriculum</h1>

            <c:if test="${not empty mess}">
                <div class="alert alert-info" role="alert">
                    ${mess}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>

            <form id="updateForm" action="createCurriculum" method="post">
                <div class="form-group">
                    <label for="major">Chuyên ngành</label>
                    <select class="form-control" id="major" name="major" onchange="updateSubjectOptions()">
                        <c:forEach items="${listMajor}" var="major">
                            <option value="${major.id}" <c:if test="${major.id == major_id}">selected</c:if>>${major.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="subject">Điều kiện môn học 1</label>
                    <select class="form-control" id="subject" name="subject">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>                
                    </select>
                </div>
                <div class="form-group">
                    <label for="conditionSubject1">Điều kiện môn học 1</label>
                    <select class="form-control" id="conditionSubject1" name="conditionSubject1">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>                
                    </select>
                </div>

                <div class="form-group">
                    <label for="conditionSubject2">Điều kiện môn học 2</label>
                    <select class="form-control" id="conditionSubject2" name="conditionSubject2">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>  
                    </select>
                </div>

                <div class="form-group">
                    <label for="semester">Học kỳ</label>
                    <select class="form-control" id="semester" name="semester">
                        <c:forEach var="sem" begin="1" end="8">
                            <option value="${sem}">Học kỳ ${sem}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="credits">Số tín chỉ</label>
                    <select class="form-control" id="credits" name="credits">
                        <c:forEach var="credit" begin="1" end="10">
                            <option value="${credit}">Credits: ${credit}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form>


        </div>
    </body>
</html>
