<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Curriculum Overview</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <style>
            :root {
                --primary-color: #FF8C00; /* Màu cam chủ đạo */
                --primary-hover-color: #e07a00; /* Màu cam khi hover */
                --primary-text-color: #ffffff; /* Màu chữ trên nền cam */
                --secondary-color: #FF8C00; /* Màu xanh phụ */
                --secondary-hover-color: #0056b3; /* Màu xanh khi hover */
                --background-color: #f9f9f9; /* Nền xám nhạt */
                --table-hover-color: #e07a00; /* Màu khi hover trên bảng */
                --table-header-color: var(--secondary-color); /* Màu nền header bảng */
                --table-header-text-color: var(--primary-text-color); /* Màu chữ header bảng */
            }

            body {
                background-color: var(--background-color); /* Nền tổng thể */
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .navbar {
                background-color: var(--primary-color); /* Navbar với màu cam */
                padding: 10px 20px;
            }

            .navbar-brand {
                color: var(--primary-text-color); /* Logo chữ màu trắng */
                font-size: 24px;
                text-decoration: none;
            }

            .navbar-brand:hover {
                color: var(--primary-hover-color); /* Đổi màu cam nhạt khi hover */
            }

            h1 {
                color: var(--secondary-color); /* Tiêu đề màu xanh */
                text-align: center;
                margin-bottom: 20px;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .table th {
                background-color: var(--table-header-color); /* Header bảng màu xanh */
                color: var(--table-header-text-color); /* Chữ trắng */
                text-align: center;
                padding: 10px;
            }

            .table td {
                text-align: center;
                padding: 10px;
                border: 1px solid #ddd; /* Đường viền nhạt */
            }

            .table tbody tr:hover {
                background-color: var(--table-hover-color); /* Màu nền hover */
            }

            .btn-return {
                display: inline-block;
                background-color: var(--secondary-color); /* Nút màu xanh */
                color: var(--primary-text-color); /* Chữ trắng */
                padding: 10px 20px;
                border-radius: 8px;
                text-decoration: none;
                font-size: 16px;
                margin-bottom: 20px;
            }

            .btn-return:hover {
                background-color: var(--secondary-hover-color); /* Hover màu xanh đậm */
            }

            .card {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng cho thẻ */
            }

        </style>

        <!-- JavaScript -->
        <script>
            function filterSemester() {
                const selectedSemester = document.getElementById("semesterSelect").value;
                const rows = document.querySelectorAll(".table tbody tr");
                let hasVisibleRow = false;

                rows.forEach(row => {
                    const semesterCell = row.cells[5].textContent.trim();
                    if (selectedSemester === "all" || semesterCell === selectedSemester) {
                        row.style.display = "";
                        hasVisibleRow = true;
                    } else {
                        row.style.display = "none";
                    }
                });

                // Hiển thị thông báo nếu không có hàng nào hiển thị
                const noDataMessage = document.getElementById("noDataMessage");
                noDataMessage.style.display = hasVisibleRow ? "none" : "block";
            }
        </script>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">University Academic Portal</a>
            </div>
        </nav>

        <!-- Return Button -->
        <div class="text-start p-3">
            <a href="home" class="btn-return">Return</a>
        </div>

        <!-- Main Content -->
        <div class="container-fluid mt-5">
            <div class="card shadow p-4">
                <h1>Curriculum Overview</h1>

                <!-- Dropdown -->
                <div class="mb-4">
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

                <!-- No Data Message -->
                <div id="noDataMessage" class="text-center text-danger" style="display: none;">
                    No subjects found for the selected semester.
                </div>

                <!-- Table -->
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Credits</th>
                                <th>Description</th>
                                <th>Semester</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="index" value="1" />
                            <c:forEach items="${listSubject}" var="subject">
                                <tr>
                                    <td>${index}</td>
                                    <td>${subject.code}</td>
                                    <td>${subject.name}</td>
                                    <td>${subject.credits}</td>
                                    <td>${subject.description}</td>
                                    <td>${subject.semester}</td>
                                </tr>
                                <c:set var="index" value="${index + 1}" />
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
