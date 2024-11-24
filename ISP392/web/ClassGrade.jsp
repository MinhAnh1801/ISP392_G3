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
            .container1 {
                padding: 20px;
                max-width: 800px;
                margin: 30px auto;
                text-align: center;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
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
                margin-top: 10px;
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
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
        <div class="container1 mt-20">
            <h1 class="text-3xl font-semibold">Class Assignments</h1>

            <!-- Outer loop: List subjects -->
            <c:forEach items="${data}" var="subject">
                <h2 class="text-left ml-1" style=" margin-top: 20px;">Subject: ${subject.code}</h2>

                <!-- Inner loop: List classes under each subject -->
                <table>
                    <tr>
                        <th>Classes for ${subject.code}</th>
                    </tr>
                    <c:forEach items="${subject.classes}" var="classItem">
                        <tr>
                            <!-- Link to AssignmentStudentController with class_name as a parameter -->
                            <td><a href="assignment?class_name=${classItem.class_name}&subject_name=${subject.code}">${classItem.class_name}</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </body>
</html>
