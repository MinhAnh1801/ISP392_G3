<%-- 
    Document   : AddApplicationType
    Created on : Sep 28, 2024, 2:34:13 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Application Type</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
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
        <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

</head>
<body>

    <!-- Navigation bar -->
    <div class="login-child">
    </div>
    <a href="/ISP392/ViewListApplicationType.jsp" class="login-item bg-white flex hover:bg-slate-200 duration-200">
        <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
        <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
        <div class="back m-auto">Return</div>
    </a>

    <!-- Main content -->
    <div class="container">
        <div class="card">
            <div class="card-header bg-warning text-white text-center">
                <h4>Add New Application Type</h4>
            </div>
            <div class="card-body">
                <form action="addapplicationtype" method="post" class="needs-validation" enctype="multipart/form-data" novalidate >
                    <!-- Type Name -->
                    <div class="mb-3">
                        <label for="type_name" class="form-label">Application Type Name</label>                 
                        <input type="text" class="form-control" id="type_name" name="type_name" required>
                        <label for="template" class="form-label">Template </label>
                        <input class="mt-2 ml-2" type="file" class="form-control" id="template" name="template" required>
                        <div class="invalid-feedback">Please provide a name for the application type.</div>
                    </div>
                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-custom">Add Application Type</button>
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
