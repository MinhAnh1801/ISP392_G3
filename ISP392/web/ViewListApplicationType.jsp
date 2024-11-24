<%-- 
    Document   : ViewListApplicationType
    Created on : Sep 28, 2024, 2:27:27 AM
    Author     : Dell
--%>
<%@ page import="java.util.List" %>
<%@ page import="Model.ApplicationType" %>
<%@ page import="DAL.ApplicationTypeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List of Application Types</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script src="https://cdn.tailwindcss.com"></script>
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
        <script type="text/javascript">
    function confirmDelete(id) {
        var result = confirm("Are you sure you want to delete this application type?");
        if (result) {
            window.location.href = 'deleteapplicationtype?id=' + id;
        } else {
            return false;
        }
    }
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
        <div class="container w-[60%] mt-20">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4 class="text-3xl">List of Application Types</h4>
                </div>
                <div class="card-body">
                    <a href="AddApplicationType.jsp" class="btn btn-custom mb-3">Add New Application Type</a>
                    
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <!-- <th>ID</th> -->
                                <th>Type Name</th>
                                <th>Template file</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ApplicationTypeDAO dao = new ApplicationTypeDAO();
                                List<ApplicationType> applicationTypes = dao.getAllApplicationTypes();
                                request.setAttribute("applicationTypes", applicationTypes);
                            %>

                            <c:forEach var="applicationType" items="${applicationTypes}">
                                <tr>
                                 <!--   <td>${applicationType.id}</td>-->
                                    <td>${applicationType.typeName}</td>
                                    <td><div class="text-blue-500 underline"><a href="downloadfile?file=${applicationType.template}">${applicationType.template}</a></div></td>
                                    <td>
                                        <a href="editapplicationtype?id=${applicationType.id}" class="btn btn-primary btn-sm">Edit</a>
                                        <a href="deleteapplicationtype?id=${applicationType.id}" class="btn btn-danger btn-sm" onclick="confirmDelete(${applicationType.id})">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS (Optional if you need JS for dropdowns, modals, etc.) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
