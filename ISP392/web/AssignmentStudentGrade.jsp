<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment Grade</title>
        <style>
            /* Reset default styling */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }
            body {
                background-color: #f9f9f9;
            }
            /* Taskbar Styling */
            .taskbar {
                background-color: #FF6F00;
                color: #fff;
                padding: 15px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .taskbar h1 {
                margin: 0;
                font-size: 24px;
            }
            .logout-button {
                color: #fff;
                text-decoration: none;
                font-size: 14px;
                display: flex;
                align-items: center;
            }
            .logout-button img {
                margin-right: 5px;
            }
            /* Container Styling */
            .container {
                padding: 20px;
                max-width: 900px;
                margin: 30px auto;
                text-align: center;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                color: #FF6F00;
                margin-bottom: 20px;
                font-size: 26px;
                border-bottom: 2px solid #FF6F00;
                display: inline-block;
                padding-bottom: 5px;
            }
            /* Table Styling */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: center;
            }
            th {
                background-color: #FF6F00;
                color: #fff;
                font-weight: bold;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            /* Input and Button Styling */
            input[type="text"] {
                width: 80%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                text-align: center;
            }
            button[type="submit"] {
                background-color: #FF6F00;
                color: #fff;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }
            button[type="submit"]:hover {
                background-color: #e65c00;
            }
        </style>
    </head>
    <body>
        <div class="taskbar">
            <h1>University Academic Portal</h1>
            <a href="#" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/ffffff/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>

        <div class="container">
            <h1>Assignment Grade</h1>

            <!-- Thông báo giảng viên đang ở lớp nào -->
            <p>Giảng viên đang ở lớp: <strong>${className}</strong></p>
            <form action="assignment?class_name=${className}" method="post">
                <table>
                <tr>
                    <th>ID</th>
                    <th>Assignment ID</th>
                    <th>Student Name</th>
                    <th>Submission Content</th>
                    <th>Submission Date</th>
                    <th>Grade</th>
                    <th>Comment</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${data}" var="as">
                    <tr>
                        <td>${as.getId()}</td>
                        <td>${as.getAssignmentId()}</td>
                        <td>${as.getStudentName()}</td>
           
                        <!-- Hiển thị link tải file hoặc thông báo lỗi -->
                        
                        <td>
                            <c:choose>                             
                                <c:when test="${not empty as.getSubmissionContent()}">
                                    <a  href="download?file=${as.getSubmissionContent()}">Tải bài tập</a>
                                </c:when>
                                <c:otherwise>
                                    Chưa nộp
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${as.getSubmissionDate()}</td>
                        <td>
                            <input type="text" name="grade_${as.getId()}" value="${as.getGrade()}" />
                        </td>
                        <td>${as.getComment()}</td>
                        <td>
                            <button type="submit" name="save" value="${as.getId()}">Save</button>
                        </td>
                    </tr

                </c:forEach>

            </table>
            </form>
            
        </div>
    </body>
</html>
