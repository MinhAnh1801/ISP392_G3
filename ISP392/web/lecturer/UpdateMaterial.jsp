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
        <title>Update Material</title>
        <script src="https://cdn.tailwindcss.com"></script>      
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
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
        <a href="/ISP392/lecturer/materials" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <!-- Nội dung chính -->
        <div class="container mt-20">
            <h2 class="text-center font-bold text-xl">Update Material</h2>
            <form action="material" method="post" enctype="multipart/form-data">
                <input type="text" hidden id="id" name="id" value="${m.getId()}" readonly>

                <label for="materialName">Material Name:</label>
                <input type="text" id="materialName" name="materialName" value="${m.getMaterialName()}">

                <label for="materialFile">Material File:</label>
                <input type="file" id="materialFile" name="materialFile" value="${m.getMaterialFile()}" required>

                <label for="uploadAt">Upload At:</label>
                <input type="text" id="uploadAt" name="uploadAt" value="${m.getUploadedAt()}" readonly=>

                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="${m.getDescription()}">
                <label for="subject_id">Subject:</label>
                <select id="subject_id" name="subject_id" required
                        class="w-full h-[40px] mb-2 bg-transparent placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded pl-3 pr-8 py-2 transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md appearance-none cursor-pointer">
                    <option value="${sid}">${m.getSubjectCode()}</option>
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.subjectid}"> ${subject.getCode()}</option>
                    </c:forEach>
                </select>

                    <input class="mt-2" type="submit" name="value" value="UPDATE">
            </form>
        </div>
    </body>
</html>
