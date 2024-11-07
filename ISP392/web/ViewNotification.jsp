<%-- 
    Document   : ViewNotification
    Created on : Oct 8, 2024, 9:21:09 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdn.tailwindcss.com"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #FF6F00;
                color: white;
                padding: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .taskbar h1 {
                margin: 0;
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
                max-width: 800px;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border: 2px solid #FF6F00;
            }
            .container h1 {
                color: #FF6F00;
            }
            .button {
                display: inline-block;
                padding: 8px 15px;
                background-color: #FF6F00;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                margin-bottom: 15px;
            }
            .button:hover {
                background-color: #e65c00;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #FF6F00;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="taskbar">
            
            <a href="home"  class="no-underline text-3xl">
               <h1>University Academic Portal</h1>
            </a>
        </div>
        <div class="container">
            <h1>Notification</h1>
            <a href="notice?mod=1" class="button">Upload Notification</a>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Role</th>
                </tr>
                <c:forEach items="${data}" var="n">
                    <tr>
                        <td>${n.getId()}</td>
                        <td>${n.getTitle()}</td>
                        <td>${n.getContent()}</td>
                        <td>${n.getRole()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
