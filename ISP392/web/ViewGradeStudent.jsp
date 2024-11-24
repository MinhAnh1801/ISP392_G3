<%-- 
    Document   : ViewGradeStudent
    Created on : Nov 5, 2024, 10:34:55 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Grade</title>
        <style>
            /* Taskbar styling */
            .taskbar {
                background-color: #FF8C00;
                color: white;
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
                color: white;
                text-decoration: none;
                font-size: 16px;
            }
            .logout-button img {
                vertical-align: middle;
                margin-right: 5px;
            }
            
            /* Table styling */
            .container {
                padding: 20px;
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }
            th {
                background-color: #FFA500;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #FFE5B4;
            }
            h1 {
                color: #FF8C00;
                text-align: center;
                font-size: 28px;
            }
        </style>
    </head>
    <body>
        <div class="taskbar">
            <h1>University Academy Portal</h1>
            <a href="#" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/ffffff/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>

        <div class="container">
            <h1>Student Grade</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Grade</th>                   
                </tr>
                <c:forEach items="${data}" var="gs">
                    <tr>
                        <td>${gs.getId()}</td>
                        <td>${gs.getFull_name()}</td>
                        <td>${gs.getGrade()}</td>                      
                    </tr>
                </c:forEach>
            </table>  
        </div>
    </body>
</html>
