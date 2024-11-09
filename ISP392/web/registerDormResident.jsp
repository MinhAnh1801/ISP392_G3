<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register Dorm Resident</title>
        <!-- Kết nối đến Bootstrap CSS từ CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #ffffff; /* Màu nền trắng cho toàn bộ trang */
            }
            .form-container {
                background-color: #f8f9fa; /* Màu nền nhạt cho container */
                border-radius: 8px; /* Bo góc cho container */
                padding: 20px; /* Padding cho container */
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho container */
            }
            h1 {
                color: #ff4500; /* Màu cam cho tiêu đề */
                text-align: center;
            }
            .btn-custom {
                background-color: #ff4500; /* Màu cam cho nút */
                color: white; /* Màu chữ trắng */
            }
            .btn-custom:hover {
                background-color: #e63946; /* Màu cam đậm hơn khi hover */
            }
            .error-message {
                color: red;
                text-align: center;
            }
            .success-message {
                color: green;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="form-container">
                <h1>Register Dorm Resident</h1>
                <form action="registerDormResident" method="post">
                    <div class="form-group">
                        <label for="dormRoomId">Dorm Room:</label>
                        <select name="dormRoomId" id="dormRoomId" class="form-control" required>
                            <c:forEach var="room" items="${dormRooms}">
                                <option value="${room.id}">${room.roomNumber} - ${room.building}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="checkInDate">Check-in Date:</label>
                        <input type="date" id="checkInDate" name="checkInDate" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label for="checkOutDate">Check-out Date:</label>
                        <input type="date" id="checkOutDate" name="checkOutDate" class="form-control" required />
                    </div>

                    <c:if test="${not empty errorMessage}">
                        <div class="error-message">${errorMessage}</div>
                    </c:if>
                    <c:if test="${not empty successMessage}">
                        <div class="success-message">${successMessage}</div>
                    </c:if>
                    <c:if test="${empty redirectmsg}">
                        <button type="submit" class="btn btn-custom">Submit</button>
                    </c:if>
                    <c:if test="${not empty redirectmsg}">
                        <div class="success-message">${redirectmsg}</div>
                        <a href="dashboardPayments" class="btn-primary p-2">Go to payment</a>
                        <a href="home" class="btn-danger p-2 ml-2">Go back homepage</a>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Kết nối đến Bootstrap JS và jQuery từ CDN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
