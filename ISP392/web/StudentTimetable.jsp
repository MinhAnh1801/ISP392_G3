<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Student Timetable</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script src="https://cdn.tailwindcss.com"></script>
        <style>
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
            .timetable-table tr {
                height: 70px;
            }
            .slot-cell {
                width: 200px;
                min-height: 60px;
                vertical-align: top;
            }
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 1920px;
                height: 72px;
            }
            .login-item {
                position: absolute;
                top: 10px;
                left: 47px;
                border-radius: 17px;
                width: 128px;
                height: 52px;
            }
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
        </style>
    </head>
    <body class="">
        <div class="login-child">
        </div>
        <a href="home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mx-auto py-8 mt-12">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Your Weekly Timetable</h2>

            <table class="timetable-table">
                <thead>
                    <tr>
                        <th>Time</th>
                        <th>MON</th>
                        <th>TUE</th>
                        <th>WED</th>
                        <th>THU</th>
                        <th>FRI</th>
                        <th>SAT</th>
                        <th>SUN</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Slot 1: 7:30 - 9:50 -->
                    <tr>
                        <td>7:30 - 9:50</td>
                        <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                            <td class="slot-cell">
                                <c:forEach var="schedule" items="${timetable}">
                                    <c:if test="${fn:trim(fn:toUpperCase(schedule.day_of_week)) == day &&
                                                  fn:substring(schedule.start_time, 0, 5) == '07:30' &&
                                                  fn:substring(schedule.end_time, 0, 5) == '09:50'}">
                                          Subject: <a href="viewSubjectDetail?subject_id=${schedule.subject_id}" class="text-blue-500 underline">${schedule.subjectName}</a>
                                          <div>Classroom: ${schedule.classroomName}</div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>

                    <!-- Slot 2: 10:00 - 12:20 -->
                    <tr>
                        <td>10:00 - 12:20</td>
                        <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                            <td class="slot-cell">
                                <c:forEach var="schedule" items="${timetable}">
                                    <c:if test="${fn:trim(fn:toUpperCase(schedule.day_of_week)) == day &&
                                                  fn:substring(schedule.start_time, 0, 5) == '10:00' &&
                                                  fn:substring(schedule.end_time, 0, 5) == '12:20'}">
                                          Subject: <a href="viewSubjectDetail?subject_id=${schedule.subject_id}" class="text-blue-500 underline">${schedule.subjectName}</a>
                                          <div>Classroom: ${schedule.classroomName}</div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>

                    <!-- Slot 3: 12:50 - 15:10 -->
                    <tr>
                        <td>12:50 - 15:10</td>
                        <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                            <td class="slot-cell">
                                <c:forEach var="schedule" items="${timetable}">
                                    <c:if test="${fn:trim(fn:toUpperCase(schedule.day_of_week)) == day &&
                                                  fn:substring(schedule.start_time, 0, 5) == '12:50' &&
                                                  fn:substring(schedule.end_time, 0, 5) == '15:10'}">
                                          Subject: <a href="viewSubjectDetail?subject_id=${schedule.subject_id}" class="text-blue-500 underline">${schedule.subjectName}</a>
                                          <div>Classroom: ${schedule.classroomName}</div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>

                    <!-- Slot 4: 15:20 - 17:40 -->
                    <tr>
                        <td>15:20 - 17:40</td>
                        <c:forEach var="day" items="${['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']}">
                            <td class="slot-cell">
                                <c:forEach var="schedule" items="${timetable}">
                                    <c:if test="${fn:trim(fn:toUpperCase(schedule.day_of_week)) == day &&
                                                  fn:substring(schedule.start_time, 0, 5) == '15:20' &&
                                                  fn:substring(schedule.end_time, 0, 5) == '17:40'}">
                                          Subject: <a href="viewSubjectDetail?subject_id=${schedule.subject_id}" class="text-blue-500 underline">${schedule.subjectName}</a>
                                          <div>Classroom: ${schedule.classroom_id}</div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>

    </body>
</html>