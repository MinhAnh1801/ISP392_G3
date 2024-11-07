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
        <title>Assignment Grade</title>
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
                max-width: 900px;
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
            /* Input and Button Styling */
            input[type="text"] {
                width: 80%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
                text-align: center;
            }
            button[type="submit"] {
                background-color: #FF6F00;
                color: #fff;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }
            button[type="submit"]:hover {
                background-color: #e65c00;
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
        <a href="/ISP392/classgrade" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container1 mt-20">
            <h1 class="text-3xl mb-1">Assignment Grade</h1>

            <!-- Thông báo giảng viên đang ở lớp nào -->
            <p>Giảng viên đang xem bài làm của sinh viên ở lớp: <strong>${className}</strong></p>
            Mã môn: <strong>${subject_name}</strong>
            <form action="assignment?class_name=${className}" method="post">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Assignment ID</th>
                        <th>Student Name</th>
                        <th>Submission Content</th>
                        <th>Submission Date</th>
                        <th>Grade</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${data}" var="as">
                        <tr>
                            <td>${as.getId()}</td>
                            <td>${as.getAssignmentId()}</td>
                            <td>${as.getStudentName()}</td>

                            <!-- Hiển thị link tải file hoặc thông báo lỗi -->

                            <td>
                                <c:choose>                             
                                    <c:when test="${not empty as.getSubmissionContent()}">
                                        <a class="text-blue-500 underline" href="download?file=${as.getSubmissionContent()}">Tải xuống bài làm</a>
                                    </c:when>
                                    <c:otherwise>
                                        Chưa nộp
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${as.getSubmissionDate()}</td>
                            <td>
                                <input type="text" name="grade_${as.getId()}" value="${as.getGrade()}" />
                            </td>
                            <td>
                                <button type="submit" name="save" value="${as.getId()}">Save</button>
                            </td>
                        </tr

                    </c:forEach>

                </table>
            </form>

        </div>
    </body>
</html>
