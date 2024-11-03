<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Timetable</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <style>
        /* Additional custom styling to match the look in the image */
        .timetable-table {
            width: 100%;
            border-collapse: collapse;
            text-align: center;
        }
        .timetable-table th, .timetable-table td {
            border: 1px solid #ccc;
            padding: 10px;
        }
        .timetable-table th {
            background-color: #0073e6;
            color: white;
        }
        .slot-cell {
            min-height: 60px;
            vertical-align: top;
        }
        .subject-code {
            font-weight: bold;
        }
        .material-link, .attendance-status, .edu-link {
            display: inline-block;
            margin: 5px 0;
        }
        .material-link {
            color: #f0a500;
        }
        .attendance-status {
            color: green;
            font-weight: bold;
        }
        .attendance-status.absent {
            color: red;
        }
        .edu-link {
            color: #0073e6;
        }
    </style>
</head>
<body class="bg-gray-100">

<div class="container mx-auto py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Your Weekly Timetable</h2>

    <!-- Controls for Week Selection -->
    <div class="mb-4 flex items-center">
        <label for="year" class="mr-2 font-semibold">Year</label>
        <select id="year" name="year" class="border rounded p-1">
            <option>2024</option>
            <option>2023</option>
            <!-- Add more years as needed -->
        </select>

        <label for="week" class="mx-2 font-semibold">Week</label>
        <select id="week" name="week" class="border rounded p-1">
            <option>28/10 To 03/11</option>
            <!-- Add more weeks dynamically if possible -->
        </select>
    </div>

    <table class="timetable-table">
        <!-- Header Row with Days -->
        <thead>
            <tr>
                <th>Time</th>
                <th>MON<br>28/10</th>
                <th>TUE<br>29/10</th>
                <th>WED<br>30/10</th>
                <th>THU<br>31/10</th>
                <th>FRI<br>01/11</th>
                <th>SAT<br>02/11</th>
                <th>SUN<br>03/11</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through time slots and days -->
                <tr>
                    <td>7:30 - 9:50</td>
                    <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                        <td class="slot-cell">
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>10:00 - 12:20</td>
                    <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                        <td class="slot-cell">
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>12:50 - 15:10</td>
                    <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                        <td class="slot-cell">
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>15:20 - 17:40</td>
                    <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                        <td class="slot-cell">
                        </td>
                    </c:forEach>
                </tr>
    </table>
</div>

</body>
</html>
