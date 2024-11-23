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
        <title>List Subject</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f5;
                margin: 0;
                padding: 0;
            }

            /* Taskbar */
            .taskbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #ff6600;
                padding: 10px 20px;
                width: 100%;
                box-sizing: border-box;
                height: 45px;
            }

            .taskbar h1 {
                margin: 0;
                font-size: 18px;
                color: white;
                text-shadow: 1px 1px 2px black;
            }

            .logout-button {
                background-color: white;
                border: 2px solid white;
                padding: 5px 15px;
                border-radius: 20px;
                font-size: 14px;
                color: #333;
                text-decoration: none;
                display: flex;
                align-items: center;
            }

            .logout-button:hover {
                background-color: #e6e6e6;
                border-color: #e6e6e6;
            }

            .container1 {
                width: 80%;
                margin: 50px auto;
                padding: 20px;
                background-color: white;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            a.button {
                display: inline-block;
                padding: 10px 20px;
                margin-bottom: 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            a.button:hover {
                background-color: #0056b3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            table, th, td {
                border: 1px solid #ccc;
            }

            th, td {
                padding: 12px 15px;
                text-align: left;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #f1f1f1;
            }

            td a {
                color: #007bff;
                text-decoration: none;
                transition: color 0.2s ease;
            }

            td a:hover {
                color: #0056b3;
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
        <script>
            // JavaScript function to show an alert if there's a duplicate message
            function showAlert(message) {
                alert(message);
                window.location.href="subject";
            }
        </script>
    </head>
    <body>
        <c:if test="${not empty duplicateMessage}">
            <script>
                // Call the showAlert function with the duplicate message
                showAlert("${duplicateMessage}");
            </script>
        </c:if>
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
            <h1 class="text-3xl">List Subject</h1>

            <br><a href="subject?mod=1" class="button">Create new Subject</a>

            <form  action="subject" method="get">
                <br>Search by subject code:<input  class="border-[1px] mb-2"type="text" name="name">
                <input class="bg-green-400 p-1 rounded-xl w-[100px] cursor-pointer shadow-lg hover:bg-green-300" type="submit" value="SEARCH" name="search">
            </form>

            <table>
                <tr>
                    <th>Major</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Credits</th>
                    <th>Description</th>
                    <th>Semester</th>
                    <th>Tuition</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${data}" var="s">
                    <tr>
                        <td>${s.major_name}</td>
                        <td>${s.getCode()}</td>
                        <td>${s.getName()}</td>
                        <td>${s.getCredits()}</td>
                        <td>${s.getDescription()}</td>
                        <td>${s.getSemester()}</td>
                        <td>${s.getTuition()}</td>
                        <td class="flex justify-center"><a href="subject?mod=2&id=${s.getId()}" onclick="return confirm('Are you sure you want to delete this subject?');">Delete</a>
                            <a class="ml-2" href="subject?mod=3&id=${s.getId()}">Update</a></td>

                    </tr>
                </c:forEach>
            </table>
        </div>   
    </body>
</html>
