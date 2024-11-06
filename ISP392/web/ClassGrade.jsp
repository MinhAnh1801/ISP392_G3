<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Class Assignment</title>
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
            max-width: 600px;
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
        tr:hover {
            background-color: #ffe5cc;
        }
        td a {
            color: #FF6F00;
            text-decoration: none;
            font-weight: bold;
        }
        td a:hover {
            text-decoration: underline;
            color: #e65c00;
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
        <h1>Class Assignment</h1>
    
        <table>
            <tr>
                <th>Class Assignment</th>
            </tr>
            <c:forEach items="${data}" var="c">
                <tr>
                    <!-- Link to AssignmentStudentController with class_name as a parameter -->
                    <td><a href="assignment?class_name=${c.class_name}">${c.class_name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
