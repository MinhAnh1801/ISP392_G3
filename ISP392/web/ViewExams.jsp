<%-- 
    Document   : ViewExams
    Created on : Oct 12, 2024, 2:54:42 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <title>Exam Schedule</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #ff8c00; /* Màu cam đậm */
                color: white;
                padding: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                height: auto; /* Đảm bảo chiều cao tự động mở rộng theo nội dung */
                box-sizing: border-box; /* Đảm bảo kích thước tính cả padding */
                overflow: hidden; /* Ngăn việc tràn nội dung */
            }
            .taskbar h1 {
                margin: 0;
                font-size: 24px; /* Kích thước chữ lớn để dễ thấy */
            }
            .logout-button {
                color: white;
                text-decoration: none;
                display: flex;
                align-items: center;
            }
            .logout-button img {
                margin-right: 5px;
            }
            .container {
                padding: 20px;
            }
            h1 {
                color: #ff8c00; /* Màu cam đậm */
            }
            .button {
                background-color: #ffa500; /* Màu cam sáng */
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                display: inline-block;
                margin-bottom: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid #ffa500; /* Đường viền màu cam */
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #ff8c00; /* Màu cam đậm cho header */
                color: white;
            }
            tr:nth-child(even) {
                background-color: #fff2e0; /* Màu cam nhạt cho các hàng chẵn */
            }
            tr:nth-child(odd) {
                background-color: #ffe5cc; /* Màu cam rất nhạt cho các hàng lẻ */
            }
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 100%;
                height: 72px;
            }
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
            .login-item {
                position: absolute;
                top: 10px;
                left: 47px;
                border-radius: 17px;
                width: 128px;
                height: 52px;
            }
        </style>
    </head>
    <body>
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mt-24 m-auto">
            <h1 class="text-3xl">Exam Schedule</h1>
            <c:if test="${sessionScope.role==0}">
                <a href="exam?mod=1" class="button">Upload Schedule</a>
            </c:if>
            <table>
                <tr>
                    <th>Subject</th>
                    <th>Exam Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Exam Room</th>
                </tr>
                <c:forEach items="${data}" var="e">
                    <tr>
                        <td>${e.getSubject_name()}</td>
                        <td>${e.getExam_date()}</td>
                        <td>${e.getStart_time()}</td>
                        <td>${e.getEnd_time()}</td>
                        <td>${e.getExam_room()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>  
    </body>
</html>
