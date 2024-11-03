<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Available Subjects for Registration</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
    </head>
    <style>
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
    <body class="bg-gray-100">
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mx-auto py-8 mt-12">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Available Subjects for Registration</h2>

            <table class="min-w-full bg-white border rounded">
                <thead>
                    <tr>
                        <th class="border px-4 py-2">Subject Code</th>
                        <th class="border px-4 py-2">Subject Name</th>
                        <th class="border px-4 py-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through each eligible subject -->
                    <c:forEach var="subject" items="${eligibleSubjects}">
                        <tr>
                            <td class="border px-4 py-2 text-center">${subject.code}</td>
                            <td class="border px-4 py-2 text-center">${subject.name}</td>
                            <td class="border px-4 py-2 text-center">
                                <a href="viewAvailableSchedules?subject_id=${subject.id}">
                                    <button class="bg-blue-500 text-white py-1 px-3 rounded">Register</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
