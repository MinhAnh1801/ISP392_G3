<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curriculum Overview</title>
    
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #ffffff; /* Màu nền trắng */
            font-family: Arial, sans-serif; /* Font chữ */
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
    </style>
    <script>
        function filterSemester() {
            const selectedSemester = document.getElementById("semesterSelect").value;
            const rows = document.querySelectorAll(".table tbody tr");

            rows.forEach(row => {
                const semesterCell = row.cells[5].textContent;
                if (selectedSemester === "all" || semesterCell === selectedSemester) {
                    row.style.display = ""; 
                } else {
                    row.style.display = "none"; 
                }
            });
        }
    </script>
</head>
<body>
    <div class="container" style="max-width: 800px; margin: auto; padding: 20px;">
        <h1>Curriculum Overview</h1>

        <!-- Dropdown để chọn học kỳ -->
        <div class="mb-3">
            <label for="semesterSelect" class="form-label">Select Semester:</label>
            <select id="semesterSelect" class="form-select" onchange="filterSemester()">
                <option value="all">All Semesters</option>
                <c:set var="lastSemester" value="" />
                <c:forEach items="${listSubject}" var="subject">
                    <c:if test="${subject.semester != lastSemester}">
                        <option value="${subject.semester}">Semester ${subject.semester}</option>
                        <c:set var="lastSemester" value="${subject.semester}" />
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Credits</th>
                        <th>Description</th>
                        <th>Condition subject 1</th>
                        <th>Condition subject 2</th>
                        <th>Semester</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="1"/> <!-- Khởi tạo biến đếm -->
                    <c:forEach items="${listSubject}" var="subject">
                        <tr>
                            <td>${index}</td> <!-- Hiển thị giá trị của biến đếm -->
                            <td>${subject.code}</td>
                            <td>${subject.name}</td>
                            <td>${subject.credits}</td>
                            <td>${subject.description}</td>
                            <td>${subject.conditionSubject1.getName()}</td>
                            <td>${subject.conditionSubject2.getName()}</td>
                            <td>${subject.semester}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}"/> <!-- Tăng biến đếm -->
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
