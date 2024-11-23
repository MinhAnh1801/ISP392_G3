<%-- 
    Document   : SubjectGrade
    Created on : Nov 8, 2024, 6:44:32 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject Grade</title>
        <style>
            /* Định dạng chung */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            
            /* Header */
            .header {
                background-color: #ffcc00; /* Màu cam đậm cho header */
                padding: 10px 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .header h1 {
                color: white;
                margin: 0;
            }
            .header .user-info {
                display: flex;
                align-items: center;
            }
            .header .user-info span {
                margin-right: 10px;
                color: white;
                font-weight: bold;
            }
            .header .logout-btn {
                background-color: white;
                color: #ff6600;
                border: 1px solid #ff6600;
                padding: 5px 10px;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
            }
            .logout-btn:hover {
                background-color: #ff6600;
                color: white;
            }

            /* Container và tiêu đề */
            .container {
                margin: 20px;
                text-align: center;
            }
            .container h2 {
                margin: auto;
                width: 50%;
                background-color: #ffcc00; /* Màu nền cam cho tiêu đề */
                color: white;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }

            /* Bảng */
            table {
                width: 40%;
                margin: 0 auto;
                border-collapse: collapse;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            th, td {
                padding: 12px;
                text-align: center;
                border: 1px solid #ddd;
            }
            th {
                background-color: #ff6600; /* Màu cam cho header của bảng */
                color: white;
            }
            tr:nth-child(even) {
                background-color: #ffe6cc; /* Màu nền cho các dòng chẵn */
            }
            tr:hover {
                background-color: #ffcc99; /* Màu nền khi hover */
            }
            a {
                color: #ff6600;
                text-decoration: none;
                font-weight: bold;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        
        <!-- Container và tiêu đề -->
        <div class="container">
            <h2>Click subject name to see grade report</h2>
            
            <!-- Bảng hiển thị các môn học -->
            <table>
                <tr>
                    <th>Subject Name</th>
                </tr>
                <c:forEach items="${data}" var="sg">
                    <tr>
                        <td><a href="getgrade?subject_id=${sg.getSubjectid()}">${sg.getCode()}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
