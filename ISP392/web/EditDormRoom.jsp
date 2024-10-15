<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Dorm Room</title>
        
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
                    <h4>Edit Dorm Room</h4>
                </div>
                <div class="card-body">
                    <form action="editdorm" method="post" class="needs-validation" novalidate>
                        <!-- Hidden input for room ID -->
                        <input type="hidden" name="id" value="${dormRoom.id}">

                        <!-- Room Number -->
                        <div class="mb-3">
                            <label for="roomNumber" class="form-label">Room Number</label>
                            <input type="text" class="form-control" id="roomNumber" name="roomNumber" value="${dormRoom.roomNumber}" required>
                            <div class="invalid-feedback">Please provide a room number.</div>
                        </div>

                        <!-- Capacity -->
                        <div class="mb-3">
                            <label for="capacity" class="form-label">Capacity</label>
                            <input type="number" class="form-control" id="capacity" name="capacity" value="${dormRoom.capacity}" required>
                            <div class="invalid-feedback">Please provide the capacity.</div>
                        </div>

                        <!-- Available Capacity -->
                        <div class="mb-3">
                            <label for="availableCapacity" class="form-label">Available Capacity</label>
                            <input type="number" class="form-control" id="availableCapacity" name="availableCapacity" value="${dormRoom.availableCapacity}" required>
                            <div class="invalid-feedback">Please provide the available capacity.</div>
                        </div>

                        <!-- Building -->
                        <div class="mb-3">
                            <label for="building" class="form-label">Building</label>
                            <input type="text" class="form-control" id="building" name="building" value="${dormRoom.building}" required>
                            <div class="invalid-feedback">Please provide the building.</div>
                        </div>

                        <!-- Room Type -->
                        <div class="mb-3">
                            <label for="roomType" class="form-label">Room Type</label>
                            <input type="text" class="form-control" id="roomType" name="roomType" value="${dormRoom.roomType}" required>
                            <div class="invalid-feedback">Please provide the room type.</div>
                        </div>

                        <!-- Price -->
                        <div class="mb-3">
                            <label for="price" class="form-label">Price</label>
                            <input type="number" class="form-control" id="price" name="price" value="${dormRoom.price}" required>
                            <div class="invalid-feedback">Please provide the price.</div>
                        </div>

                        <!-- Detail -->
                        <div class="mb-3">
                            <label for="detail" class="form-label">Detail</label>
                            <textarea class="form-control" id="detail" name="detail" rows="3" required>${dormRoom.detail}</textarea>
                            <div class="invalid-feedback">Please provide details.</div>
                        </div>

                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-custom">Update Room</button>
                        <a href="ViewListDormRoom.jsp" class="btn btn-secondary">Cancel</a>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS (Optional for form validation) -->
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

