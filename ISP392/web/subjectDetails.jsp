<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Subject Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container1 {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #FF6F00;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .info {
            margin-bottom: 20px;
        }
        .info label {
            font-weight: bold;
            color: #333;
        }
        .info span {
            display: block;
            margin-top: 5px;
            margin-bottom: 15px;
            color: #666;
        }
        .materials-list {
            list-style: none;
            padding: 0;
        }
        .materials-list li {
            margin: 10px 0;
        }
        .materials-list a {
            text-decoration: none;
            color: #FF6F00;
            font-weight: bold;
        }
        .materials-list a:hover {
            color: #e65c00;
        }
        #email{
            margin-left: 10px;
            text-decoration: underline;
            cursor: pointer;
            color: #0073e6;
        }
    </style>
</head>
<body>

<div class="container1 ">
    <h1 class="text-3xl">Subject Details</h1>

    <!-- Subject Information -->
    <div class="info mt-4">
        <label>Subject Code:</label>
        <span>${subject.subject_code}</span>

        <label>Subject Name:</label>
        <span>${subject.subjectName}</span>

        <label>Lecturer Name:</label>
        <span>${subject.lecturer_name} <a id="email">${subject.lecturer_email}</a> </span> 

        <label>Class Name:</label>
        <a href="viewStudentInClass?class_id=${subject.class_name}" class="text-blue-500 hover:underline">${subject.class_name}</a>
    </div>

    <!-- Materials List -->
    <h2 class="text-xl font-semibold mb-2">Materials</h2>
    <a href="viewAllMaterial?subject_id=${subjectid}" class="mt-2 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Browse</a>
</div>

</body>
</html>
