<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Guide Line</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

        <!-- jQuery (required for DataTables) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

        <style>
            .alert-container {
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 1050;
                width: 300px;
            }
        </style>
        <style>
            body {
                background-color: #ffffff; /* Màu nền trắng */
                font-family: Arial, sans-serif; /* Font chữ */
            }
            h1 {
                color: #ff5722; /* Màu cam cho tiêu đề */
                text-align: center; /* Căn giữa tiêu đề */
                margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
            }
            .table {
                width: 100%; /* Chiều rộng 100% */
                margin-top: 20px; /* Khoảng cách trên bảng */
                border-collapse: collapse; /* Kết hợp đường biên */
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho bảng */
            }
            .table th, .table td {
                padding: 10px; /* Khoảng cách trong ô */
                border: 1px solid #dee2e6; /* Đường biên cho ô */
                text-align: center; /* Căn giữa nội dung */
            }
            .table th {
                background-color: #ff9800; /* Màu cam cho tiêu đề bảng */
                color: #ffffff; /* Màu chữ trắng cho tiêu đề bảng */
            }
            .table tbody tr:hover {
                background-color: #ffe0b2; /* Màu nền sáng khi di chuột qua hàng */
            }
        </style>
    </head>
    <body>

        <c:if test="${sessionScope.role == 0}">
            <div>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createGuidelineModal">
                    Tạo mới hướng dẫn
                </button>
            </div>
        </c:if>
        <!-- Button to trigger the modal -->


        <!-- Modal Structure -->
        <div class="modal fade" id="createGuidelineModal" tabindex="-1" aria-labelledby="createGuidelineLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="createGuidelineLabel">Tạo hưỡng dẫn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Form inside the modal -->
                        <form action="guideline" method="post" id="createGuidelineForm">
                            <input name="action" value="createGuideline" hidden="">
                            <div class="mb-3">
                                <label for="guidelineTitle" class="form-label">Tiêu đề </label>
                                <input name="title" type="text" class="form-control" id="guidelineTitle" placeholder="Nhập tiêu đề hướng dẫn">
                            </div>
                            <div class="mb-3">
                                <label for="guidelineCategory" class="form-label">Thể loại</label>
                                <select name="category" class="form-control" id="guidelineCategory">
                                    <option value="" disabled selected>Chọn danh mục</option>
                                    <option value="category1">Danh mục 1</option>
                                    <option value="category2">Danh mục 2</option>
                                    <option value="category3">Danh mục 3</option>
                                </select>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary" onclick="submitForm()">Tạo </button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function submitForm() {
                var title = $('#guidelineTitle').val();
                var category = $('#guidelineCategory').val();

                if (title && category) {
                    console.log('Title:', title);
                    console.log('Category:', category);
                    $('#createGuidelineForm').submit();

                    $('#createGuidelineModal').modal('hide');
                } else {
                    alert("Vui lòng điền vào cả tiêu đề và danh mục.");
                }
            }
        </script>




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



        <!-- in ra table -->
        <div class="container mt-5">
            <h2 class="text-center">Danh sách hướng dẫn </h2>

            <table id="guideTable" class="table table-bordered mt-4">
                <thead class="table-primary">
                    <tr>
                        <th>ID</th>
                        <th>Tiêu đề</th>
                        <th>Ngày tạo</th>
                        <th>Thể loại</th>
                        <th>Hoạt động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listGuideline}" var="listGuideline">
                        <tr>
                            <td>${listGuideline.id}</td>
                            <td>${listGuideline.title}</td>
                            <td>${listGuideline.create_date}</td>
                            <td>${listGuideline.category}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#guideModalGuideDetail${listGuideline.id}">
                                    Xem Chi tiết
                                </button>

                                <c:if test="${sessionScope.role == 0}">


                                    <!-- button edit s -->
                                    <button class="btn btn-info"
                                            data-bs-toggle="modal"
                                            data-bs-target="#editModalGuideline"
                                            data-id="${listGuideline.id}"
                                            data-title="${listGuideline.title}"
                                            data-create-date="${listGuideline.create_date}"
                                            data-category="${listGuideline.category}">
                                        Sửa
                                    </button>
                                    <!-- Form for Deleting a Guideline -->
                                    <form id="deleteForm" action="guideline" method="post" class="d-inline">
                                        <input name="action" value="delete" hidden>
                                        <input name="id" value="${listGuideline.id}" hidden>
                                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalGuideline">
                                            Xóa
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- kết thúc in ra table -->

        <!-- Phân trang bằng javasctip -->
        <script>
            $(document).ready(function () {
                $('#guideTable').DataTable({
                    "pageLength": 10, // Set maximum rows per page
                    "lengthMenu": [5, 10, 20], // Options for page length
                    "order": [[1, "asc"]], // Default sorting by the second column (Title)
                    "searching": true, // Enable search functionality
                    "paging": true, // Enable pagination
                    "info": true, // Show table information (like showing x of y entries)
                    "autoWidth": false // Disable auto column width adjustments
                });
            });
        </script>




        <!-- Hiển thị popup cho detail  -->

        <c:forEach items="${listGuideline}" var="listGuideline">

            <!-- hiển thị ra detail  -->
            <div class="modal fade" id="guideModalGuideDetail${listGuideline.id}" tabindex="-1" aria-labelledby="guideModalLabel1" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div>

                                <h5 class="modal-title" id="guideModalLabel1">Hưỡng dẫn chi tiết:</h5>


                               


                                <c:if test="${sessionScope.role == 0}">


                                    <button type="button" class="btn btn-primary" id="openModal1${listGuideline.id}">
                                        Tạo thêm bước 
                                    </button>
                                </c:if>
                                <!-- The Modal -->
                                <div class="modal fade" id="CreatelGuidelineDetailStep${listGuideline.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Tạo bước trong hưỡng dẫn</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="guideline" method="post" id="detailForm${listGuideline.id}">

                                                    <input value="${listGuideline.id}" name="guideline_id" hidden="">
                                                    <input value="createDetail" name="action" hidden="">
                                                    <div class="mb-3">
                                                        <label for="step-number${listGuideline.id}" class="form-label">Bước số:</label>
                                                        <input type="number" class="form-control" id="step-number${listGuideline.id}" name="stepNumber" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="detailTitle${listGuideline.id}" class="form-label">Tiêu đề</label>
                                                        <input type="text" class="form-control" id="detailTitle${listGuideline.id}" name="detailTitle" required>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label for="detailDescription${listGuideline.id}" class="form-label">Miêu tả</label>
                                                        <textarea class="form-control" id="detailDescription${listGuideline.id}" name="detailDescription" rows="3" required></textarea>
                                                    </div>

                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary" form="detailForm${listGuideline.id}">Create</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(document).ready(function () {
                                        // Show the modal when the button is clicked
                                        $(`#openModal1${listGuideline.id}`).on('click', function () {
                                            $(`#CreatelGuidelineDetailStep${listGuideline.id}`).modal('show');
                                        });
                                    });
                                </script>


                            </div>


                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                        </div>
                        <div class="modal-body">

                            <div>
    <c:set var="hasData" value="false" />

    <c:forEach items="${listGuideDetail}" var="listGuideDetail">
        <c:if test="${listGuideDetail.guideline_id.id == listGuideline.id}">
            <h3>${listGuideDetail.step_title}</h3>
            <p>${listGuideDetail.description}</p>
            
            <c:set var="hasData" value="true" /> <!-- Đặt cờ hasData là true nếu có dữ liệu -->

            <c:if test="${sessionScope.role == 0}">
                <div class="d-flex align-items-center">
                    <button type="button" 
                            class="btn btn-info me-2"  
                            data-bs-toggle="modal" 
                            data-bs-target="#editDetailModal"
                            data-id="${listGuideDetail.id}"
                            data-title="${listGuideDetail.step_title}"
                            data-description="${listGuideDetail.description}">
                        Edit
                    </button>

                    <form action="guideline" method="post" onsubmit="return confirmDelete()">
                        <input value="deleteDetail" name="action" type="hidden">
                        <input value="${listGuideDetail.id}" name="id" type="hidden">
                        <button type="submit" class="btn btn-danger">
                            Delete
                        </button>
                    </form>
                </div>
            </c:if>

            <script>
                // Function to display a confirmation dialog
                function confirmDelete() {
                    return confirm("Are you sure you want to delete this step?");
                }
            </script>
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <h3>Không có dữ liệu hướng dẫn</h3>
        <p>Vui lòng kiểm tra lại sau.</p>
    </c:if>
</div>




                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <!-- kết thúc Hiển thị popup cho detail  -->



        <!--Edit cho guideline-->
        <!-- Edit Modal -->
        <div class="modal fade" id="editModalGuideline" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Item</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <form action="guideline" method="post" id="editForm">
                            <input hidden="" type="text" id="editId" name="id">
                            <input hidden="" type="text" value="editGuideline" name="action">

                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title</label>
                                <input type="text" class="form-control" id="editTitle" name="title">
                            </div>                   
                            <div class="mb-3">
                                <label for="editCategory" class="form-label">Category</label>
                                <input type="text" class="form-control" id="editCategory" name="category">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="saveChangesBtn">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $('#editModalGuideline').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal
                var title = button.data('title'); // Extract info from data-* attributes
                var createDate = button.data('create-date');
                var category = button.data('category');
                var id = button.data('id'); // Get the ID from data attributes

                // Update the modal's content
                var modal = $(this);
                modal.find('#editTitle').val(title);
                modal.find('#editCreateDate').val(createDate);
                modal.find('#editCategory').val(category);
                modal.find('#editId').val(id); // Set the ID in the hidden input
            });

            // Save changes button action
            $('#saveChangesBtn').on('click', function () {
                // Confirm the action
                if (confirm('Are you sure you want to save the changes?')) {
                    $('#editForm').submit(); // Submit the form if confirmed
                }
            });


            $(document).ready(function () {
                // Store the form reference
                let form;

                // When the Delete button is clicked, store the form
                $('[data-bs-target="#deleteModalGuideline"]').on('click', function () {
                    form = $(this).closest('form');
                });

                // When the confirm delete button is clicked
                $('#confirmDelete').on('click', function () {
                    form.submit(); // Submit the form to delete the guideline
                });
            });
        </script>
        <!-- kết thúc Edit cho guideline-->

        <!-- comment  form xác nhận xóa  -->
        <div class="modal fade" id="deleteModalGuideline" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete this guideline?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger" id="confirmDelete">Delete</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- kết thúc   form xác nhận xóa  -->


        <!--Edit step in guideline detail-->
        <!-- Edit Detail Modal -->
        <div class="modal fade" id="editDetailModal" tabindex="-1" aria-labelledby="editDetailModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editDetailModalLabel">Edit Step</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="guideline" method="post" id="editGuideForm">
                            <input value="editDetail" name="action" hidden=""> 
                            <div class="mb-3">
                                <label for="stepTitle" class="form-label">Step Title</label>
                                <input type="text" class="form-control" id="stepTitle" name="stepTitle" required>
                            </div>
                            <div class="mb-3">
                                <label for="stepDescription" class="form-label">Description</label>
                                <textarea class="form-control" id="stepDescription" name="stepDescription" required></textarea>
                            </div>
                            <input hidden="" type="text" id="guideDetailId" name="guideDetailId">
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            // Add an event listener for when the modal is shown
            var editDetailModal = document.getElementById('editDetailModal');
            editDetailModal.addEventListener('show.bs.modal', function (event) {
                // Get the button that triggered the modal
                var button = event.relatedTarget;

                // Extract the data-* attributes from the button
                var title = button.getAttribute('data-title');
                var description = button.getAttribute('data-description');
                var id = button.getAttribute('data-id');

                // Update the modal's content
                var modalTitle = editDetailModal.querySelector('#editDetailModalLabel');
                var stepTitleInput = editDetailModal.querySelector('#stepTitle');
                var stepDescriptionInput = editDetailModal.querySelector('#stepDescription');
                var guideDetailIdInput = editDetailModal.querySelector('#guideDetailId');

                modalTitle.textContent = 'Edit Step: ' + title; // Set modal title
                stepTitleInput.value = title; // Set input value
                stepDescriptionInput.value = description; // Set textarea value
                guideDetailIdInput.value = id; // Set hidden input value
            });
        </script>
        <!-- kết thúc Edit step in guideline detail-->




        <!-- Bootstrap JS and dependencies (Popper.js) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
