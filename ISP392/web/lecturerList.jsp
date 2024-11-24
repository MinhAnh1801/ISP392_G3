<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lecturer List</title>
        <link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <style>
            /* Additional custom styling for a cleaner look */
            .dataTables_wrapper .dataTables_filter input {
                border-radius: 4px;
                border: 1px solid #ddd;
                padding: 5px;
                margin-left: 10px;
            }
            .dataTables_wrapper .dataTables_length select {
                border-radius: 4px;
                border: 1px solid #ddd;
                padding: 5px;
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
    </head>
    <body class="bg-gray-100">
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mx-auto py-8 mt-12">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Lecturer List</h2>

            <table id="lecturerTable" class="display table-auto border-collapse w-full">
                <thead>
                    <tr class="bg-blue-500 text-white">
                        <th class="px-4 py-2">Lecturer ID</th>
                        <th class="px-4 py-2">Department</th>
                        <th class="px-4 py-2">Full Name</th>
                        <th class="px-4 py-2">Email</th>
                        <th class="px-4 py-2">Office</th>
                        <th class="px-4 py-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="lecturer" items="${lecturers}">
                        <tr class="bg-white border-b">
                            <td class="px-4 py-2 text-center">${lecturer.lecturerId}</td>
                            <td class="px-4 py-2 text-center">${lecturer.department}</td>
                            <td class="px-4 py-2 text-center">${lecturer.fullName}</td>
                            <td class="px-4 py-2 text-center">${lecturer.email}</td>
                            <td class="px-4 py-2 text-center">${lecturer.office}</td>
                            <td class="px-4 py-2 text-center ">
                                <!-- Button to navigate to the assign screen -->
                                <a href="assignLecturer?lecturerId=${lecturer.lecturerId}" class="assign-button bg-blue-700 p-2 rounded-xl text-white hover:bg-blue-500 hover:text-slate-100 font-semibold">Assign</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#lecturerTable').DataTable({
                    paging: true,
                    searching: true,
                    ordering: true,
                    pageLength: 5
                });
            });
        </script>
    </body>
</html>
