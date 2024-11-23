<%-- 
    Document   : EditApplicationType
    Created on : Sep 28, 2024, 2:53:13 AM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Application Type</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- Custom styles -->
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .form-label {
                font-weight: bold;
            }
            .btn-custom {
                background-color: #ff9800;
                color: white;
            }
        </style>
    </head>
    <body>

        <!-- Navigation bar -->
        <nav class="navbar navbar-light bg-warning mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">University Academic Portal</a>
                <div class="d-flex">
                    <img src="path_to_avatar_image" alt="Avatar" class="rounded-circle" width="40" height="40">
                    <span class="navbar-text mx-3">Khúc Xuân Hào</span>
                    <a href="logout" class="btn btn-outline-dark">Log out</a>
                </div>
            </div>
        </nav>

        <!-- Main content -->
        <div class="container">
            <div class="card">
                <div class="card-header bg-warning text-white text-center">
                    <h4>Edit Application Type</h4>
                </div>
                <div class="card-body">
                    <form action="editapplicationtype" method="post" class="needs-validation" novalidate enctype="multipart/form-data">
                        <!-- Hidden input for Application Type ID -->
                        <input type="hidden" name="id" value="${applicationType.id}">

                        <!-- Type Name -->
                        <div class="mb-3">
                            <label for="type_name" class="form-label">Application Type Name</label>
                            <input type="text" class="form-control" id="type_name" name="type_name" value="${applicationType.typeName}" required>
                            <label for="template" class="form-label">Template</label>
                            <input type="file" class="form-control" id="template" name="template">
                            <div class="invalid-feedback">Please provide the application type name.</div>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-custom">Update Application Type</button>
                        <a href="ViewListApplicationType.jsp" class="btn btn-secondary">Cancel</a>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS for form validation -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            // Bootstrap custom form validation
            (function () {
                'use strict'
                var forms = document.querySelectorAll('.needs-validation')
                Array.prototype.slice.call(forms).forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }
                        form.classList.add('was-validated')
                    }, false)
                })
            })()
        </script>
    </body>
</html>
