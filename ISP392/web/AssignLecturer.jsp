<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Assign Lecturer to Class and Subject</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

<div class="container mx-auto py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Assign Lecturer to Class and Subject</h2>

    <form action="AssignLecturerServlet" method="post" class="bg-white p-6 rounded-lg shadow-md">
        <!-- Lecturer Dropdown -->
        <div class="mb-4">
            <label for="lecturer" class="block text-gray-700 font-bold mb-2">Select Lecturer</label>
            <select id="lecturer" name="lecturerId" class="w-full border rounded p-2">
                <c:forEach var="lecturer" items="${lecturers}">
                    <option value="${lecturer.id}">${lecturer.fullName}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Subject Dropdown -->
        <div class="mb-4">
            <label for="subject" class="block text-gray-700 font-bold mb-2">Select Subject</label>
            <select id="subject" name="subjectId" class="w-full border rounded p-2">
               
            </select>
        </div>
        <!-- Class Dropdown -->
        <div class="mb-4">
            <label for="class" class="block text-gray-700 font-bold mb-2">Select Class</label>
            <select id="class" name="classId" class="w-full border rounded p-2">
                
            </select>
        </div>

        

        <!-- Submit Button -->
        <button type="submit" class="bg-blue-500 text-white font-bold py-2 px-4 rounded">
            Assign Lecturer
        </button>
    </form>
</div>

</body>
</html>
