<%-- 
    Document   : ViewGrades
    Created on : Oct 30, 2024, 3:08:42 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Grade</title>
        <style>
            /* Tổng thể */
            body {
                font-family: Arial, sans-serif;
                background-color: #fff5e6;
                color: #333;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #ff6600;
                color: white;
                padding: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                overflow: visible; /* Đảm bảo nội dung không bị ẩn */
            }

            .taskbar h1 {
                margin: 0;
                font-size: 24px;
                white-space: nowrap; /* Ngăn chặn việc xuống dòng nếu thanh taskbar quá nhỏ */
            }

            .logout-button {
                color: white;
                text-decoration: none;
                display: flex;
                align-items: center;
            }

            .container {
                padding: 20px;
                max-width: 800px;
                margin: auto;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                color: #ff6600;
                text-align: center;
            }
            form {
                margin-bottom: 20px;
                text-align: center;
            }
            form input[type="text"] {
                padding: 8px;
                width: 200px;
                border: 1px solid #ff6600;
                border-radius: 4px;
            }
            form input[type="submit"] {
                padding: 8px 16px;
                background-color: #ff6600;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            form input[type="submit"]:hover {
                background-color: #e65c00;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ff6600;
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #ff6600;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #fff2e6;
            }
            tr:hover {
                background-color: #ffdab3;
            }
        </style>
    </head>
    <body>
        <!-- Taskbar -->
        <div class="taskbar">
            <h1>University Academic Portal</h1>
            <a href="#" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/000000/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>

        <div class="container">
            <h1>View Grade</h1>

            <form action="grade" method="get">
                <br>Enter Student_ID: <input type="text" name="student_id">
                <input type="submit" value="SEARCH" name="search">
            </form>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Student_ID</th>
                    <th>Subject_ID</th>
                    <th>Grade</th>
                    <th>Upload_Date</th>
                    <th>Comments</th>
                </tr>
                <c:forEach items="${data}" var="g">
                    <tr>
                        <td>${g.getId()}</td>
                        <td>${g.getStudent_id()}</td>
                        <td>${g.getSubject_id()}</td>
                        <td>${g.getGrade()}</td>
                        <td>${g.getUpload_date()}</td>
                        <td>${g.getComments()}</td>                      
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
