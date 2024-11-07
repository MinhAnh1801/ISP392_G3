<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>List Class</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: white;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                height: 52vh;
            }

            .taskbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #ff6600;
                padding: 10px 20px;
                width: 100%;
                box-sizing: border-box;
                height: 45px; /* Set the height to 45px */
            }

            .taskbar h1 {
                margin: 0;
                font-size: 18px;
                color: white;
                text-shadow: 1px 1px 2px black;
            }

            .logout-button {
                padding: 5px 15px;
                border-radius: 20px;
                font-size: 14px;
                color: #333;
                text-decoration: none;
                display: flex;
                align-items: center;
            }


            .container {
                margin-top: 20px;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                width: 600px;
            }

            h1 {
                text-align: center;
                color: #333;
                
            }

            .button {
                display: inline-block;
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                margin-bottom: 20px;
                font-size: 14px;
            }

            .button:hover {
                background-color: #0056b3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }

            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:hover {
                background-color: #f1f1f1;
            }

            td a {
                color: #007bff;
                text-decoration: none;
            }

            td a:hover {
                text-decoration: underline;
            }
        </style>
        
    </head>
    <body>
        <!-- Taskbar -->
        <div class="taskbar">
            <a href="home" class="logout-button">
                <h1>University Academic Portal</h1>
            </a>
        </div>

        <div class="container">
            <h1>List Class</h1>

            <a href="class?mod=1" class="button">Create new Class</a>
            
            <form action="class" method="get">
                <br>Enter name:<input type="text" name="class_name">
                <input type="submit" value="SEARCH" name="search">
            </form>

            <table>
                <tr>
                    <th>Class ID</th>
                    <th>Class Name</th>
                    <th>Class Capacity</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${data}" var="c">
                    <tr>
                        <td><a href="class?mod=3&id=${c.getClass_id()}">${c.getClass_id()}</a></td>
                        <td>${c.getClass_name()}</td>
                        <td>${c.getCapacity()}</td>
                        <td><a href="class?mod=2&id=${c.getClass_id()}" style="color: red;" onclick="return confirm('Are you sure you want to delete this subject?');">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
