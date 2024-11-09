<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Attendance Report</title>
    <style>
        body {
            background-color: #ffffff; /* Màu nền trắng */
            font-family: Arial, sans-serif; /* Font chữ */
            display: flex; /* Sử dụng flexbox để tạo layout */
        }
        .sidebar {
            width: 25%; /* Chiều rộng của sidebar */
            padding: 20px;
            border-right: 1px solid #dee2e6; /* Đường biên bên phải */
        }
        .content {
            width: 75%; /* Chiều rộng của phần nội dung */
            padding: 20px;
        }
        h1 {
            color: #ff5722; /* Màu cam cho tiêu đề */
            text-align: center; /* Căn giữa tiêu đề */
            margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
        }
        .table {
            width: 100%; /* Chiều rộng 100% */
            margin-top: 20px; /* Khoảng cách trên bảng */
            border-collapse: collapse; /* Kết hợp đường biên */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho bảng */
        }
        .table th, .table td {
            padding: 10px; /* Khoảng cách trong ô */
            border: 1px solid #dee2e6; /* Đường biên cho ô */
            text-align: center; /* Căn giữa nội dung */
        }
        .table th {
            background-color: #ff9800; /* Màu cam cho tiêu đề bảng */
            color: #ffffff; /* Màu chữ trắng cho tiêu đề bảng */
        }
        .table tbody tr:hover {
            background-color: #ffe0b2; /* Màu nền sáng khi di chuột qua hàng */
        }
        .button-edit {
            padding: 8px 12px; /* Khoảng cách trong nút */
            background-color: #ff9800; /* Màu nền xanh lá */
            color: white; /* Màu chữ trắng */
            border: none; /* Không có đường biên */
            border-radius: 5px; /* Bo góc */
            cursor: pointer; /* Con trỏ khi di chuột */
            transition: background-color 0.3s; /* Hiệu ứng chuyển đổi màu nền */
        }
        .button-edit:hover {
            background-color: #45a049; /* Màu nền khi di chuột qua nút */
        }
        .filter-container {
            text-align: center; /* Căn giữa nội dung trong div */
            margin-top: 20px; /* Khoảng cách trên div */
        }
        .subject-list {
            list-style-type: none; /* Bỏ dấu chấm đầu dòng */
            padding: 0;
        }
        .subject-list li {
            padding: 5px;
            cursor: pointer; /* Con trỏ khi di chuột */
        }
        .subject-list li:hover {
            background-color: #f0f0f0; /* Hiệu ứng hover */
        }
        .selected {
            background-color: #ff9800; /* Màu nền cam cho mục đã chọn */
            color: white; /* Màu chữ trắng cho mục đã chọn */
        }
    </style>
    <script>
        function filterAttendance(subject, selectedElement) {
            const rows = document.querySelectorAll('#attendanceTable tbody tr');
            rows.forEach(row => {
                const subjectCell = row.cells[1].textContent; // Assuming the subject is in the second cell
                if (subjectCell === subject) {
                    row.style.display = ''; // Show row
                } else {
                    row.style.display = 'none'; // Hide row
                }
            });

            // Remove 'selected' class from all list items
            const items = document.querySelectorAll('.subject-list li');
            items.forEach(item => {
                item.classList.remove('selected');
            });

            // Add 'selected' class to the clicked item
            selectedElement.classList.add('selected');
        }

        document.addEventListener('DOMContentLoaded', () => {
            const subjects = [];
            <c:forEach var="attendance" items="${listAttendance}">
                subjects.push('${attendance.subject.name}');
            </c:forEach>

            const uniqueSubjects = [...new Set(subjects)];
            const subjectList = document.querySelector('.subject-list');

            uniqueSubjects.forEach((subject, index) => {
                const li = document.createElement('li');
                li.textContent = subject;
                li.onclick = () => filterAttendance(subject, li);
                subjectList.appendChild(li);

                // If it's the first subject, filter attendance for it
                if (index === 0) {
                    filterAttendance(subject, li); // Filter for the first subject by default
                    li.classList.add('selected'); // Add 'selected' class to the first subject
                }
            });
        });
    </script>
</head>
<body>
    <div class="sidebar">
        <h2>Subjects</h2>
        <ul class="subject-list">
            <!-- Subjects will be populated here by JavaScript -->
        </ul>
    </div>
    
    <div class="content">
        <h1>Attendance Report</h1>
        <table class="table" id="attendanceTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Subject</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Reason</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="attendance" items="${listAttendance}">
                    <tr>
                        <td>${attendance.id}</td>
                        <td>${attendance.subject.name}</td> 
                        <td>${attendance.attendance_date}</td>
                        <td>${attendance.status}</td>
                        <td>${attendance.reason}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Call to Action Button -->
        <div class="cta-container">
            <button class="button-edit" onclick="alert('Action triggered!')">Take Action</button>
        </div>
    </div>
</body>
</html>
