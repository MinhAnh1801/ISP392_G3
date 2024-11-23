<%-- 
    Document   : tuitionFeeCourse
    Created on : Nov 4, 2024, 10:39:14 AM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tuition Fee for Subjects</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

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
    </style>
</head>
<body>

    <!-- Navigation bar -->
    <nav class="navbar navbar-light bg-warning mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">University Academic Portal</a>
            <div class="d-flex">
                <img src="path_to_avatar_image" alt="Avatar" class="rounded-circle" width="40" height="40">
                <span class="navbar-text mx-3">Student Name</span>
                <a href="logout" class="btn btn-outline-dark">Log out</a>
            </div>
        </div>
    </nav>

    <!-- Main content -->
    <div class="container">
        <div class="card">
            <div class="card-header bg-warning text-white text-center">
                <h4>Tuition Fee for Subjects</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Subject Code</th>
                            <th>Subject Name</th>
                            <th>Description</th>
                            <th>Lecturer ID</th>
                            <th>Tuition Fee</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Hardcoded data for subjects and tuition fees -->
                        <tr>
                            <td>CS101</td>
                            <td>Computer Science I</td>
                            <td>Introduction to computer science concepts.</td>
                            <td>1</td>
                            <td>$500</td>
                        </tr>
                        <tr>
                            <td>MATH201</td>
                            <td>Advanced Mathematics</td>
                            <td>In-depth study of calculus and linear algebra.</td>
                            <td>2</td>
                            <td>$600</td>
                        </tr>
                        <tr>
                            <td>ENG301</td>
                            <td>English Literature</td>
                            <td>Exploration of English literature.</td>
                            <td>3</td>
                            <td>$550</td>
                        </tr>
                        <tr>
                            <td>PHYS101</td>
                            <td>Physics I</td>
                            <td>Basic concepts of physics.</td>
                            <td>4</td>
                            <td>$520</td>
                        </tr>
                        <tr>
                            <td>CHEM101</td>
                            <td>General Chemistry</td>
                            <td>Introduction to chemical principles.</td>
                            <td>5</td>
                            <td>$530</td>
                        </tr>
                        <!-- Add more rows as needed -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (Optional if you need JS for dropdowns, modals, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
