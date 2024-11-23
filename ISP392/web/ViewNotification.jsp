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
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
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
            .container1 {
                max-width: 800px;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border: 2px solid #FF6F00;
            }
            .container1 h1 {
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
            <h1 class="text-3xl">Notification</h1>
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
