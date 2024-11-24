<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Curriculum</title>

        <!-- Custom CSS -->
        <style>
            body {
                background-color: #fdf8f0;
                font-family: 'Roboto', Arial, sans-serif;
                color: #333;
                margin: 0;
                padding: 0;
            }
            h1 {
                color: #FE8B00;
                text-align: center;
                margin-bottom: 20px;
                font-weight: bold;
                font-size: 28px;
            }
            .navbar {
                background-color: #FE8B00;
                padding: 15px 20px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .navbar-brand {
                color: white;
                font-size: 24px;
                font-weight: bold;
                text-decoration: none;
            }
            .navbar-brand:hover {
                color: #ffe0b3;
            }
            .btn-back-to-home {
                color: white;
                background-color: #FE8B00;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                font-size: 16px;
                display: inline-block;
                position: absolute;
                top: -20px;
                left: 20px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .btn-back-to-home:hover {
                background-color: #ff9f33;
            }
            .container {
                position: relative;
                background-color: white;
                padding: 30px 20px;
                margin: 50px auto;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                max-width: 600px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-control {
                border-radius: 5px;
                border: 1px solid #ccc;
                padding: 10px;
            }
            label {
                font-weight: bold;
                margin-bottom: 5px;
                display: block;
                color: #333;
            }
            .btn-primary {
                background-color: #FE8B00;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                color: white;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
            }
            .btn-primary:hover {
                background-color: #ff9f33;
            }

            /* Thông báo */
            .alert-container {
                max-width: 600px;
                margin: 20px auto;
            }
            .alert {
                border-radius: 8px;
                padding: 15px 20px;
                font-size: 16px;
                font-weight: bold;
                position: relative;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            .alert-success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .alert-danger {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
            .btn-close {
                background: none;
                border: none;
                font-size: 18px;
                font-weight: bold;
                color: #333;
                position: absolute;
                top: 10px;
                right: 15px;
                cursor: pointer;
                transition: color 0.2s ease;
            }
            .btn-close:hover {
                color: #000;
            }
            .alert-dismissible.fade.show {
                animation: fadeIn 0.5s ease-in-out;
            }
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
            .alert.fade-out {
                animation: fadeOut 0.3s forwards;
            }
            @keyframes fadeOut {
                from {
                    opacity: 1;
                    transform: translateY(0);
                }
                to {
                    opacity: 0;
                    transform: translateY(-10px);
                }
            }
        </style>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>

        <!-- Navigation bar -->
        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand" href="home">University Academic Portal</a>
        </nav>


        <style>
            .alert-container {
                position: fixed;
                top: 20px; /* Cách mép trên */
                right: 20px; /* Cách mép phải */
                width: 300px; /* Tự động điều chỉnh kích thước theo nội dung */
                z-index: 1050; /* Đảm bảo hiển thị trên cùng */
            }

            .alert {
                border-radius: 8px;
                padding: 15px 20px;
                font-size: 16px;
                font-weight: bold;
                position: relative;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 10px; /* Khoảng cách giữa các thông báo */
            }

            .alert-success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }

            .alert-danger {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

            .btn-close {
                background: none;
                border: none;
                font-size: 18px;
                font-weight: bold;
                color: #333;
                position: absolute;
                top: 10px;
                right: 15px;
                cursor: pointer;
                transition: color 0.2s ease;
            }

            .btn-close:hover {
                color: #000;
            }

            .alert-dismissible.fade.show {
                animation: fadeIn 0.5s ease-in-out;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            .alert.fade-out {
                animation: fadeOut 0.3s forwards;
            }

            @keyframes fadeOut {
                from {
                    opacity: 1;
                    transform: translateY(0);
                }
                to {
                    opacity: 0;
                    transform: translateY(-10px);
                }
            }
        </style>


        <div class="alert-container">
            <!-- Thông báo thành công -->
            <c:if test="${not empty mess}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" id="successAlert">
                    ${mess}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Thông báo lỗi -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" id="errorAlert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
        </div>


        <!-- Main content -->
        <div class="container">
            <!-- Back button -->
            <a href="managerCurriculum" class="btn-back-to-home">Back</a>

            <h1>Create Curriculum</h1>

            <!-- Hiển thị thông báo -->


            <!-- Form -->
            <form id="updateForm" action="createCurriculum" method="post">
                <div class="form-group">
                    <label for="major">Major</label>
                    <select class="form-control" id="major" name="major" onchange="updateSubjectOptions()">
                        <c:forEach items="${listMajor}" var="major">
                            <option value="${major.id}" <c:if test="${major.id == major_id}">selected</c:if>>${major.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="subject">Course Condition 1</label>
                    <select class="form-control" id="subject" name="subject">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>                
                    </select>
                </div>

                <div class="form-group">
                    <label for="conditionSubject1">Course Condition 2</label>
                    <select class="form-control" id="conditionSubject1" name="conditionSubject1">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>                
                    </select>
                </div>

                <div class="form-group">
                    <label for="conditionSubject2">Course Condition 3</label>
                    <select class="form-control" id="conditionSubject2" name="conditionSubject2">
                        <c:forEach items="${listAllSubject1}" var="subject">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>  
                    </select>
                </div>

                <div class="form-group">
                    <label for="semester">Semester</label>
                    <select class="form-control" id="semester" name="semester">
                        <c:forEach var="sem" begin="1" end="8">
                            <option value="${sem}">Semester ${sem}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="credits">Credits</label>
                    <select class="form-control" id="credits" name="credits">
                        <c:forEach var="credit" begin="1" end="10">
                            <option value="${credit}">Credits: ${credit}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>

        <!-- JavaScript -->
        <script>
            function autoDismissAlert(alertId, timeout, redirectUrl) {
                setTimeout(function () {
                    var alert = document.getElementById(alertId);
                    if (alert) {
                        alert.classList.add('fade-out'); // Thêm hiệu ứng fade-out
                        setTimeout(() => {
                            alert.remove(); // Xóa thông báo khỏi DOM
                            if (redirectUrl) {
                                window.location.href = redirectUrl; // Chuyển hướng sau khi ẩn thông báo
                            }
                        }, 300); // Đợi hiệu ứng fade-out hoàn tất
                    }
                }, timeout);
            }

            // Chỉ tự động chuyển hướng khi có thông báo thành công
            var successAlert = document.getElementById('successAlert');
            if (successAlert) {
                autoDismissAlert('successAlert', 5000, 'managerCurriculum');
            }

            // Xử lý thông báo lỗi, không chuyển hướng
            var errorAlert = document.getElementById('errorAlert');
            if (errorAlert) {
                autoDismissAlert('errorAlert', 5000, null); // Không chuyển hướng khi có lỗi
            }
        </script>

    </body>
</html>
