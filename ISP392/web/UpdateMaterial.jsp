<%-- 
    Document   : UpdateMaterial
    Created on : Nov 3, 2024, 2:31:22 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <title>Update Material</title>
        <style>
            /* CSS cho giao diện với tông màu cam */
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
            }
            .navbar {
                background-color: #FF9800;
                color: white;
                padding: 15px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .navbar h1 {
                margin: 0;
                font-size: 24px;
            }
            .logout-btn {
                background-color: #ff5722;
                color: white;
                padding: 8px 16px;
                text-decoration: none;
                border-radius: 5px;
            }
            .logout-btn:hover {
                background-color: #e64a19;
            }
            .container {
                width: 60%;
                margin: auto;
                margin-top: 20px;
                background-color: white;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            h2 {
                color: #FF9800;
                text-align: center;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label {
                margin-top: 10px;
                font-weight: bold;
            }
            input[type="text"] {
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin-bottom: 10px;
                width: 100%;
            }
            input[type="submit"] {
                background-color: #FF9800;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            input[type="submit"]:hover {
                background-color: #e68a00;
            }
        </style>
    </head>
    <body>
        <!-- Thanh taskbar -->
        <div class="navbar">
            <h1>University Academy Portal</h1>
            <a href="logout" class="logout-btn">Log Out</a>
        </div>

        <!-- Nội dung chính -->
        <div class="container">
            <h2>Update Material</h2>
            <form action="material" method="post" enctype="multipart/form-data">
                <input type="text" id="id" name="id" value="${m.getId()}" readonly>

                <label for="materialName">Material Name:</label>
                <input type="text" id="materialName" name="materialName" value="${m.getMaterialName()}">

                <label for="materialFile">Material File:</label>
                <input type="file" id="materialFile" name="materialFile" value="${m.getMaterialFile()}">

                <label for="uploadAt">Upload At:</label>
                <input type="text" id="uploadAt" name="uploadAt" value="${m.getUploadedAt()}" readonly=>

                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="${m.getDescription()}">

                <select id="subject_id" name="subject_id" required
                        class="w-full h-[40px] mb-2 bg-transparent placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded pl-3 pr-8 py-2 transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md appearance-none cursor-pointer">
                    <option value="${sid}">${m.getSubjectCode()}</option>
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.subjectid}"> ${subject.getCode()}</option>
                    </c:forEach>
                </select>

                    <input type="submit" name="value" value="UPDATE">
            </form>
        </div>
    </body>
</html>
