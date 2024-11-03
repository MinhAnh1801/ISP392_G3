<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Attendance Check</title>
    <style>
        body {
            background-color: #ffffff;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: space-between;
            width: 100%;
        }
        .form-container {
            width: 30%;
            padding: 20px;
        }
        .attendance-table {
            width: 65%;
            margin-left: 20px;
            padding: 20px;
            border: 1px solid #dee2e6;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #ff5722;
            text-align: center;
            margin: 20px 0;
        }
        select, input[type="date"] {
            margin: 10px 0;
            padding: 10px;
            font-size: 16px;
            width: 100%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #dee2e6;
            text-align: left;
        }
        th {
            background-color: #ff9800;
            color: #ffffff;
        }
        .no-records {
            color: #ff5722;
        }
    </style>
    <script>
        function setCurrentDate() {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var yyyy = today.getFullYear();
            today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("attendanceDate").value = today;
        }
        window.onload = setCurrentDate;
    </script>
</head>
<body>
    <div class="form-container">
        <form action="checkAttendance" method="post">
            <input name="action" value="chooseSubject" type="hidden">
            <select name="subjects" id="subjects" onchange="this.form.submit()">
                <option value="">Select Subject</option>
                <c:forEach items="${listSubjectBylecture}" var="subject">
                    <option value="${subject.id}" <c:if test="${subject.id == subjectId}">selected</c:if>>${subject.name} - ${subject.id}</option>
                </c:forEach>
            </select>
        </form>

        <form action="checkAttendance" method="post">
            <input name="action" value="chooseClass" type="hidden">
            <select name="classes" id="classes" onchange="this.form.submit()">
                <option value="">Select Class</option>
                <c:forEach items="${listClassBySubjectId}" var="classItem">
                    <option value="${classItem.class_id}" <c:if test="${classItem.class_id == classId}">selected</c:if>>${classItem.class_id} - ${classItem.class_name}</option>
                </c:forEach>
            </select>

            <input type="date" name="attendanceDate" id="attendanceDate" onchange="this.form.submit()" value="${attendanceDate}">
            <input type="hidden" value="${subjectId}" name="subjectId">
        </form>
    </div>

    <div class="attendance-table">
        <c:if test="${not empty listAttendance}">
            <h2>Attendance List</h2>
            <table>
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Status</th>
                        <th>Attendance Date</th>
                        <th>Reason</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listAttendance}" var="attendance">
                        <tr>
                            <td>${attendance.fullName}</td>
                            <td>
                                <form action="checkAttendance" method="post">
                                    <input name="action" value="updateAttendance" type="hidden">
                                    <input name="studentId" value="${attendance.studentId.id}" type="hidden">
                                    <input name="attendanceId" value="${attendance.id}" type="hidden">

                                    <input name="attendanceStatus_${attendance.studentId.id}" onchange="this.form.submit()" value="Present" type="radio" <c:if test="${attendance.status == 'Present'}">checked</c:if>> Present
                                    <input name="attendanceStatus_${attendance.studentId.id}" onchange="this.form.submit()" value="Absent" type="radio" <c:if test="${attendance.status == 'Absent'}">checked</c:if>> Absent
                                </form>
                            </td>
                            <td>${attendance.attendance_date}</td>
                            <td>${attendance.reason}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listAttendance}">
            <p class="no-records">No attendance records found.</p>
        </c:if>
    </div>
</body>
</html>