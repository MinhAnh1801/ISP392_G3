<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Applications</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <!-- Custom styles for this page -->
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
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 100%;
                height: 72px;
            }
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
            .login-item {
                position: absolute;
                top: 10px;
                left: 47px;
                border-radius: 17px;
                width: 128px;
                height: 52px;
            }
        </style>

        <!-- JavaScript -->
        <script type="text/javascript">
            // Custom scripts if needed
        </script>
    </head>

    <body>
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <!-- Main content -->
        <div class="container w-[70%] mt-24 ">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4 class="text-3xl">Your Applications</h4>
                </div>
                <div class="card-body">
                    <a href="ApplicationController?action=showForm" class="btn btn-custom mb-3">Send Application</a>
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>No.</th>
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
                                    <td>
                                        <p>
                                            <c:if  test="${application.response == null}">
                                                No response
                                            </c:if>
                                            <c:if  test="${application.response != null}">
                                                ${application.response}
                                            </c:if>
                                        </p>
                                    </td>
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