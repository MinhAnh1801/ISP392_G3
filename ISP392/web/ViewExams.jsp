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
        </style>
    </head>
    <body>
      <div class="taskbar">
            <h1>University Academic Portal</h1> <!-- Đảm bảo không mất dòng chữ -->
            <a href="#" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/FFFFFF/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>
        <div class="container">
            <h1>Exam Schedule</h1>
            <a href="exam?mod=1" class="button">Upload Schedule</a>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Subject_ID</th>
                    <th>Exam_Date</th>
                    <th>Start_Time</th>
                    <th>End_Time</th>
                    <th>Exam_Room</th>
                    <th>Exam_Type</th>
                </tr>
                <c:forEach items="${data}" var="e">
                    <tr>
                        <td>${e.getId()}</td>
                        <td>${e.getSubjectID()}</td>
                        <td>${e.getExam_date()}</td>
                        <td>${e.getStart_time()}</td>
                        <td>${e.getEnd_time()}</td>
                        <td>${e.getExam_room()}</td>
                        <td>${e.getExam_type()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>  
    </body>
</html>
