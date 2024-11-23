<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script src="https://cdn.tailwindcss.com"></script>
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


            .container1 {
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
            function showAlertError(error) {
                if (error) {
                    alert(error);
                    window.history.back();
                    return;
                }
            }
        </script>
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
            <h1 class="text-3xl">List Class</h1>

            <a href="class?mod=1" class="button">Create new Class</a>
            <c:if test="${not empty msg}">
                <script>
                    // Call showAlert with the error message
                    showAlertError("${msg}");
                </script>
            </c:if>
            <form action="class" method="get">
                <br>Enter name:<input class="border-[1px] p-1 rounded-lg" type="text" name="class_name">
                <input class="bg-green-400 p-1 rounded-xl w-[100px] cursor-pointer shadow-lg hover:bg-green-300" type="submit" value="SEARCH" name="search">
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
