<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Materials</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <style>
        body {
            background-color: #ffffff;
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #ff5722;
            text-align: center;
            margin-bottom: 20px;
        }
        .table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .table th, .table td {
            padding: 10px;
            border: 1px solid #dee2e6;
            text-align: center;
        }
        .table th {
            background-color: #ff9800;
            color: #ffffff;
        }
        .table tbody tr:hover {
            background-color: #ffe0b2;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">List of Materials</h2>

        <div>
            <c:if test="${not empty mess}">
                <div class="alert alert-info" role="alert">
                    ${mess}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
        </div>

        <table class="table table-bordered mt-3" id="materialsTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>File URL</th>
                    <th>Uploaded By</th>
                    <th>Uploaded At</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listMaterials}">
                        <c:forEach var="material" items="${listMaterials}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${material.material_name}</td>
                                <td><a href="${material.material_file}" target="_blank">Download</a></td>
                                <td>
                                    <c:if test="${not empty material.uploaded_by}">
                                        ${material.uploaded_by.fullName}
                                    </c:if>
                                    <c:if test="${empty material.uploaded_by}">
                                        Unknown
                                    </c:if>
                                </td>
                                <td>${material.uploaded_at}</td>
                                <td>${material.description}</td>
                                <td>
                                   <c:if test="${not empty material.material_file}">
    <a href="download?filePath=${fn:replace(fn:escapeXml(material.material_file), ' ', '%20')}" class="btn btn-primary btn-sm">Download</a>
</c:if>

                                    <c:if test="${empty material.material_file}">
                                        <button class="btn btn-secondary btn-sm" disabled>No File</button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7" class="text-center">No materials available.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <div class="text-center">
            <a href="home" class="btn btn-primary">Back to Dashboard</a>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $('#materialsTable').DataTable({
                "pageLength": 10,
                "lengthMenu": [5, 10, 20],
                "order": [[1, "asc"]],
                "searching": true,
                "paging": true,
                "info": true,
                "autoWidth": false
            });
        });
    </script>
</body>
</html>
