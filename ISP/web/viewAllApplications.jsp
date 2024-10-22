<%-- 
    Document   : viewAllApplication
    Created on : Oct 22, 2024, 10:09:54 PM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - View All Applications</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .table th {
                background-color: #ff9800;
                color: white;
            }
            .btn-custom {
                background-color: #ff9800;
                color: white;
            }
            .status-pending {
                background-color: #f9c74f;
                color: white;
                padding: 3px 10px;
                border-radius: 5px;
            }
            .status-approved {
                background-color: #4caf50;
                color: white;
                padding: 3px 10px;
                border-radius: 5px;
            }
            .status-rejected {
                background-color: #f44336;
                color: white;
                padding: 3px 10px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4>All Applications</h4>
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Student ID</th>
                                <th>Application Type</th>
                                <th>Content</th>
                                <th>Status</th>
                                <th>Attached File</th>
                                <th>Response</th>
                                <th>Created At</th>
                                <th>Last Updated</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="application" items="${applications}" varStatus="index">
                                <tr>
                                    <td>${index.index + 1}</td>
                                    <td>${application.studentId}</td>
                                    <td>${application.type.typeName}</td>
                                    <td>${application.content}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${application.status == 'Pending'}">
                                                <span class="status-pending">Pending</span>
                                            </c:when>
                                            <c:when test="${application.status == 'Approved'}">
                                                <span class="status-approved">Approved</span>
                                            </c:when>
                                            <c:when test="${application.status == 'Rejected'}">
                                                <span class="status-rejected">Rejected</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>${application.status}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${application.attachedFile != null}">
                                                <a href="uploads/${application.attachedFile}" download>${application.attachedFile}</a>
                                            </c:when>
                                            <c:otherwise>
                                                No file attached
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${application.response == null ? "No response" : application.response}</td>
                                    <td>${application.createdAt}</td>
                                    <td>${application.lastUpdated}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
