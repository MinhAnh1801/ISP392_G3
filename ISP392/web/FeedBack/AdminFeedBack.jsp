<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Feedback Management</title>

        <!-- Link to Bootstrap 4 CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

        <style>
            /* Custom main color */
            :root {
                --main-color: #D76325;
            }

            .navbar {
                background-color: var(--main-color);
            }

            .navbar-brand {
                color: white !important;
            }

            .table th, .table td {
                vertical-align: middle;
            }

            .btn-warning {
                background-color: var(--main-color);
                border-color: var(--main-color);
            }

            .btn-warning:hover {
                background-color: darken(var(--main-color), 10%);
                border-color: darken(var(--main-color), 10%);
            }

            .btn-info {
                background-color: var(--main-color);
                border-color: var(--main-color);
            }

            .btn-info:hover {
                background-color: darken(var(--main-color), 10%);
                border-color: darken(var(--main-color), 10%);
            }

            .alert-info {
                background-color: var(--main-color);
                color: white;
            }

            .action-buttons button {
                margin-right: 10px;
            }
        </style>

    </head>
    <body>
        
        
          <style>
            .alert-container {
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 1050;
                width: 300px;
            }
        </style>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">University Academic Portal</a>
            </div>
        </nav>
        
          <!--Hiển thị thông báo lỗi hoặc mess -->
        <div class="container mt-5 alert-container">
            <!-- Success Message -->
            <c:if test="${not empty mess}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" id="successAlert">
                    ${mess}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" id="errorAlert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
        </div>

        <script>
            function autoDismissAlert(alertId, timeout) {
                setTimeout(function () {
                    var alert = document.getElementById(alertId);
                    if (alert) {
                        var bsAlert = new bootstrap.Alert(alert);
                        bsAlert.close();
                    }
                }, timeout);
            }

            // Dismiss success alert after 5 seconds (5000 ms)
            var successAlert = document.getElementById('successAlert');
            if (successAlert) {
                autoDismissAlert('successAlert', 5000);
            }

            // Dismiss error alert after 5 seconds (5000 ms)
            var errorAlert = document.getElementById('errorAlert');
            if (errorAlert) {
                autoDismissAlert('errorAlert', 5000);
            }
        </script>
        <!-- kết thúc Hiển thị thông báo lỗi hoặc mess -->

        <div class="container mt-5">
            <h1>Admin Feedback Management</h1>

            <!-- Check if feedbackForms is not null -->
            <c:if test="${not empty listFeedbackForms}">
                <div class="table-container">
                    <table id="feedbackTable" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Created At</th>
                                <th>Lecturer</th>
                                <th>Subject</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Loop through feedbackForms list and display each feedback -->
                            <c:forEach var="feedback" items="${listFeedbackForms}">
                                <tr>
                                    <td>${feedback.id}</td>
                                    <td>${feedback.startDate}</td>
                                    <td>${feedback.endDate}</td>
                                    <td>${feedback.createdAt}</td>
                                    <td>${feedback.lecturerId.fullName} - ${feedback.lecturerId.email}</td>
                                    <td>${feedback.subjectId.code}</td>
                                    <td class="action-buttons">
                                        <!-- Trigger modal on Edit button click -->
                                        <button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editModal${feedback.id}" data-id="${feedback.id}" data-enddate="${feedback.endDate}">
                                            Edit extend date
                                        </button>
                                    </td>
                                </tr>






                            <div class="modal fade" id="editModal${feedback.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editModalLabel">Edit End Date</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form id="editForm" action="adminFeedBack" method="post">

                                            <input hidden="" name="action" value="edit">
                                            <input type="date" hidden="" name="startDate" value="${feedback.startDate}">
                                            <div class="modal-body">
                                                <!-- Form to edit end date -->
                                                <div class="form-group">
                                                    <label for="endDate">End Date</label>
                                                    <input  value="${feedback.endDate}" type="date" class="form-control" id="endDate" name="endDate" required>
                                                </div>
                                                <input type="" hidden="" value="${feedback.id}" name="feedbackId">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </div>        
                                        </form>


                                    </div>
                                </div>
                            </div>





                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <!-- Message when no feedback forms are available -->
            <c:if test="${empty listFeedbackForms}">
                <p class="alert alert-info">No feedback forms available.</p>
            </c:if>

        </div>

        <!-- Modal for Editing End Date -->


        <!-- Link to Bootstrap 4 JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- jQuery (required for DataTables) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

        <!-- Initialize DataTables -->
        <script>
            $(document).ready(function () {
                $('#feedbackTable').DataTable({
                    "paging": true,
                    "lengthChange": true,
                    "searching": true,
                    "ordering": true,
                    "info": true,
                    "autoWidth": true
                });

                // When edit button is clicked, load data into the modal form
                $('#editModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var feedbackId = button.data('id');  // Extract feedback ID
                    var endDate = button.data('enddate'); // Extract current end date

                    // Set the values in the modal form
                    var modal = $(this);
                    modal.find('#endDate').val(endDate);
                    modal.find('#feedbackId').val(feedbackId);
                });

                // Save changes when Save button is clicked
                $('#saveChangesBtn').click(function () {
                    var feedbackId = $('#feedbackId').val();
                    var endDate = $('#endDate').val();

                    // Perform an AJAX request to save the changes (replace URL with your endpoint)
                    $.ajax({
                        url: '/your-api-endpoint-to-update-enddate', // Replace with your API URL
                        method: 'POST',
                        data: {
                            feedbackId: feedbackId,
                            endDate: endDate
                        },
                        success: function (response) {
                            // Close the modal and reload the table if necessary
                            $('#editModal').modal('hide');
                            $('#feedbackTable').DataTable().ajax.reload();  // Reload DataTable
                        },
                        error: function () {
                            alert('Error updating the end date.');
                        }
                    });
                });
            });
        </script>

    </body>
</html>
