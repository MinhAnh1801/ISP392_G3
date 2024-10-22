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
        .pagination {
            text-align: center; 
            margin-top: 20px;
        }
        .pagination button {
            margin: 0 5px;
            padding: 5px 10px;
            border: none;
            background-color: #ff9800; /* Màu nền cho nút phân trang */
            color: white; /* Màu chữ trắng */
            cursor: pointer; /* Con trỏ khi di chuột */
            border-radius: 5px; /* Bo góc cho nút */
        }
        .pagination button:hover {
            background-color: #ff5722; /* Màu nền khi di chuột qua nút */
        }
    </style>
    
</head>
<body>
    <div class="container" style="max-width: 800px; margin: auto; padding: 20px;">
        <h1>Curriculum Overview</h1>

        <select id="majorSelect" class="form-select" onchange="filterMajor()">
            <option value="all">All Major</option>
            <c:set var="lastmajor" value="" />
            <c:forEach items="${listCurriculum}" var="curriculum">
                <c:if test="${curriculum.major_id.name != lastmajor}">
                    <option value="${curriculum.major_id.name}">${curriculum.major_id.name}</option>
                    <c:set var="lastmajor" value="${curriculum.major_id.name}" />
                </c:if>
            </c:forEach>
        </select>

        <div class="table-responsive">
            <table class="table" id="curriculumTable">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Major</th>
                        <th>Code Subject</th>
                        <th>Name Subject</th>
                        <th>Credits Subject</th>
                        <th>Description Subject</th>
                        <th>Condition subject 1</th>
                        <th>Condition subject 2</th>
                        <th>Semester</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="1"/> <!-- Khởi tạo biến đếm -->
                    <c:forEach items="${listCurriculum}" var="curriculum">
                        <tr>
                            <td>${index}</td>
                            <td>${curriculum.major_id.name}</td>
                            <td>${curriculum.subject_id.code}</td>
                            <td>${curriculum.subject_id.name}</td>
                            <td>${curriculum.subject_id.credits}</td>
                            <td>${curriculum.subject_id.description}</td>
                            <td>${curriculum.condition_subject_1.getName()}</td>
                            <td>${curriculum.condition_subject_2.getName()}</td>
                            <td>${curriculum.subject_id.semester}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}"/> <!-- Tăng biến đếm -->
                    </c:forEach>
                </tbody>
            </table>
                    
            <div id="pagination" class="pagination"></div>
        </div>

        <script>
            function filterMajor() {
                const selectedMajor = document.getElementById("majorSelect").value; // Lấy giá trị đã chọn
                const rows = document.querySelectorAll(".table tbody tr");

                rows.forEach(row => {
                    const majorCell = row.cells[1].textContent; // Cột chuyên ngành ở chỉ số 1
                    if (selectedMajor === "all" || majorCell === selectedMajor) {
                        row.style.display = ""; // Hiển thị hàng
                    } else {
                        row.style.display = "none"; // Ẩn hàng
                    }
                });
            }
        </script>
                    
    </div>
</body>
</html>
