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
        body {
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #ff5722;
            text-align: center;
            margin-bottom: 20px;
        }
        .table {
            margin-top: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .table th {
            background-color: #ff5722;
            color: #ffffff;
        }
        .table tbody tr:hover {
            background-color: #ffe0b2;
        }
        .navbar {
            background-color: #ff5722;
        }
        .navbar-brand {
            color: #ffffff;
            font-size: 24px;
        }
    </style>

    <!-- JavaScript -->
    <script>
        function filterSemester() {
            const selectedSemester = document.getElementById("semesterSelect").value;
            const rows = document.querySelectorAll(".table tbody tr");

            rows.forEach(row => {
                const semesterCell = row.cells[7].textContent.trim();
                row.style.display = (selectedSemester === "all" || semesterCell === selectedSemester) ? "" : "none";
            });
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

    <!-- Content Container -->
    <div class="container my-5">
        <h1>Curriculum Overview</h1>

        <!-- Dropdown for Semester Selection -->
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

        <!-- Table -->
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="text-center">
                    <tr>
                        <th>No.</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Credits</th>
                        <th>Description</th>
                        <th>Condition Subject 1</th>
                        <th>Condition Subject 2</th>
                        <th>Semester</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="index" value="1" />
                    <c:forEach items="${listSubject}" var="subject">
                        <tr>
                            <td class="text-center">${index}</td>
                            <td>${subject.code}</td>
                            <td>${subject.name}</td>
                            <td class="text-center">${subject.credits}</td>
                            <td>${subject.description}</td>
                            <td>${subject.conditionSubject1.getName()}</td>
                            <td>${subject.conditionSubject2.getName()}</td>
                            <td class="text-center">${subject.semester}</td>
                        </tr>
                        <c:set var="index" value="${index + 1}" />
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
