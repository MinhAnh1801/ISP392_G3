<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Attendance Check</title>
    <style>
        body {
            background-color: #ffffff; /* Màu nền trắng */
            font-family: Arial, sans-serif; /* Font chữ */
            display: flex; /* Sử dụng flexbox để tạo layout */
            justify-content: space-between; /* Căn giữa giữa các phần tử */
            width: 100%; /* Chiều rộng của body */
        }
        .form-container {
            width: 30%; /* Chiều rộng của phần chọn lớp và ngày */
            padding: 20px; /* Padding cho phần này */
        }
        .attendance-table {
            width: 65%; /* Chiều rộng của bảng điểm danh */
            margin-left: 20px; /* Khoảng cách bên trái của bảng */
            padding: 20px; /* Padding cho bảng */
            border: 1px solid #dee2e6; /* Đường biên cho bảng */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho bảng */
        }
        h2 {
            color: #ff5722; /* Màu cam cho tiêu đề */
            text-align: center; /* Căn giữa tiêu đề */
            margin: 20px 0; /* Khoảng cách trên và dưới tiêu đề */
        }
        select, input[type="date"] {
            margin: 10px; /* Khoảng cách giữa các phần tử */
            padding: 10px; /* Khoảng cách trong nút */
            font-size: 16px; /* Kích thước chữ */
            width: calc(100% - 22px); /* Chiều rộng của các ô nhập */
        }
        .attendance-list {
            margin-top: 20px; /* Khoảng cách trên danh sách điểm danh */
            text-align: left; /* Căn trái nội dung danh sách */
            width: 100%; /* Chiều rộng của danh sách */
        }
        .no-records {
            color: #ff5722; /* Màu đỏ cho thông báo không có dữ liệu */
        }
        table {
            width: 100%; /* Chiều rộng bảng */
            border-collapse: collapse; /* Kết hợp đường biên */
            margin-top: 10px; /* Khoảng cách trên bảng */
        }
        th, td {
            padding: 10px; /* Khoảng cách trong ô */
            border: 1px solid #dee2e6; /* Đường biên cho ô */
            text-align: left; /* Căn trái nội dung trong ô */
        }
        th {
            background-color: #ff9800; /* Màu cam cho tiêu đề bảng */
            color: #ffffff; /* Màu chữ trắng cho tiêu đề bảng */
        }
    </style>
    <script>
        function setCurrentDate() {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var yyyy = today.getFullYear();
            today = yyyy + '-' + mm + '-' + dd; // Format: YYYY-MM-DD
            document.getElementById("attendanceDate").value = today;
        }

        window.onload = setCurrentDate; // Call the function when the page loads
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

            <input type="date" name="attendanceDate" id="attendanceDate">
            <input type="text" value="${subjectId}" name="subjectId" style="display:none;">
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
                            <td>${attendance.status}</td>
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
