<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Grades" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mark Report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Định dạng màu sắc giống như Curriculum Overview */
        .table {
            background-color: #f8f9fa; /* Màu nền bảng */
        }
        .thead-dark th {
            background-color: #343a40; /* Màu nền tiêu đề */
            color: white; /* Màu chữ tiêu đề */
        }
        .btn-primary {
            background-color: #007bff; /* Màu nút */
            border-color: #007bff; /* Đường viền nút */
        }
        
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
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Mark Report</h2>
    
    <table class="table table-bordered mt-3">
        <thead class="">
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Grade</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty listGrade}">
                    <c:forEach var="grade" items="${listGrade}">
                        <tr>
                            <td>${grade.getSubjectId().getCode()}</td>
                            <td>${grade.getSubjectId().getName()}</td>
                            <td>${grade.grade}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3" class="text-center">No grades available.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    
    <div class="text-center">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
        </c:if>
        <c:if test="${currentPage < totalPages}">
            <a href="?page=${currentPage + 1}" class="btn btn-primary">Next</a>
        </c:if>
    </div>
    
    <div class="text-center mt-3">
        <a href="home" class="btn btn-primary">Back to home</a>
    </div>
</div>
</body>
</html>
