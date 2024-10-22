<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Curriculum Overview</title>

        <!-- Custom CSS -->
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
            .pagination {
                text-align: center;
                margin-top: 20px;
            }
            .pagination button {
                margin: 0 5px;
                padding: 5px 10px;
                border: none;
                background-color: #ff9800; /* Màu nền cho nút phân trang */
                color: white; /* Màu chữ trắng */
                cursor: pointer; /* Con trỏ khi di chuột */
                border-radius: 5px; /* Bo góc cho nút */
            }
            .pagination button:hover {
                background-color: #ff5722; /* Màu nền khi di chuột qua nút */
            }
            .button-edit {
                padding: 8px 12px; /* Khoảng cách trong nút */
                background-color: #ff9800; /* Màu nền xanh lá */
                color: white; /* Màu chữ trắng */
                border: none; /* Không có đường biên */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ khi di chuột */
                transition: background-color 0.3s; /* Hiệu ứng chuyển đổi màu nền */
            }

            .button-edit:hover {
                background-color: #45a049; /* Màu nền khi di chuột qua nút */
            }

            .button-edit:focus {
                outline: none; /* Xóa viền khi nút được nhấn */
            }

        </style>

    </head>
    <body>
        <div class="container" style=" margin: auto; padding: 20px;">
            <h1>Curriculum Overview</h1>
            <div>
                <a href="createCurriculum">
                    <button class="button-edit">
                        Create new curiculum
                    </button>
                </a>

                <br>
                <br>
                <br>
            </div>
            <c:if test="${not empty mess}">
                <div class="alert alert-info" role="alert">
                    ${mess}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>



            <select id="majorSelect" class="form-select" onchange="filterMajor()">
                <option value="all">All Major</option>
                <c:set var="lastmajor" value="" />
                <c:forEach items="${listCurriculum}" var="curriculum">
                    <c:if test="${curriculum.major_id.name != lastmajor}">
                        <option value="${curriculum.major_id.name}">${curriculum.major_id.name}</option>
                        <c:set var="lastmajor" value="${curriculum.major_id.name}" />
                    </c:if>
                </c:forEach>
            </select>

            <div class="table-responsive">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" id="curriculumTable">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Major</th>
                                <th>Code Subject</th>
                                <th>Name Subject</th>
                                <th>Credits Subject</th>
                                <th>Description Subject</th>
                                <th>Condition subject 1</th>
                                <th>Condition subject 2</th>
                                <th>Semester</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="index" value="1"/> <!-- Khởi tạo biến đếm -->
                            <c:forEach items="${listCurriculum}" var="curriculum">
                                <tr>
                                    <td>${index}</td>
                                    <td>${curriculum.major_id.name}</td>
                                    <td>${curriculum.subject_id.code}</td>
                                    <td>${curriculum.subject_id.name}</td>
                                    <td>${curriculum.credits}</td>
                                    <td>${curriculum.subject_id.description}</td>
                                    <td>${curriculum.condition_subject_1.getName()}</td>
                                    <td>${curriculum.condition_subject_2.getName()}</td>
                                    <td>${curriculum.semester}</td>
                                    <td>
                                        <button class="button-edit" 
                                                data-toggle="modal" 
                                                data-target="#updateModal${curriculum.subject_id.code}" 
                                                >
                                            Update
                                        </button>

                                    </td>

                                </tr>
                                <c:set var="index" value="${index + 1}"/> <!-- Tăng biến đếm -->

                                <!-- Modal -->
                            <div class="modal fade" id="updateModal${curriculum.subject_id.code}" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="updateModalLabel">Cập nhật thông tin</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateForm" action="managerCurriculum" method="post">
                                                <div class="form-group">
                                                    <label>Tên môn học: ${curriculum.subject_id.name}( ${curriculum.subject_id.code} )</label>
                                                    <label>Tên chuyên nghành học: ${curriculum.major_id.name}</label>

                                                    <input hidden="" name="majorId" value="${curriculum.major_id.id}" >
                                                    <input hidden="" name="subjectId" value="${curriculum.subject_id.id}" >

                                                </div>
                                                <div class="form-group">
                                                    <label for="conditionSubject1">Điều kiện môn học 1</label>
                                                    <select class="form-control" id="conditionSubject1" name="conditionSubject1">
                                                        <option value="${curriculum.condition_subject_1.id}">${curriculum.condition_subject_1.name}</option>

                                                        <c:choose>
                                                            <c:when test="${curriculum.major_id.id == 1}">
                                                                <c:forEach items="${listAllSubject1}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:when test="${curriculum.major_id.id == 2}">
                                                                <c:forEach items="${listAllSubject2}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:when test="${curriculum.major_id.id == 3}">
                                                                <c:forEach items="${listAllSubject3}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                        </c:choose>
                                                    </select>

                                                </div>
                                                <div class="form-group">
                                                    <label for="conditionSubject2">Điều kiện môn học 2</label>
                                                    <select class="form-control" id="conditionSubject2" name="conditionSubject2">
                                                        <option value="${curriculum.condition_subject_2.id}">${curriculum.condition_subject_2.name}</option>


                                                        <c:choose>
                                                            <c:when test="${curriculum.major_id.id == 1}">
                                                                <c:forEach items="${listAllSubject1}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:when test="${curriculum.major_id.id == 2}">
                                                                <c:forEach items="${listAllSubject2}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:when test="${curriculum.major_id.id == 3}">
                                                                <c:forEach items="${listAllSubject3}" var="subject">
                                                                    <option value="${subject.id}">${subject.name}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                        </c:choose>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="semester">Học kỳ</label>
                                                    <select class="form-control" id="semester" name="semester">
                                                        <option value="${curriculum.semester}">Học Kỳ ${curriculum.semester}</option>
                                                        <option value="1">Học kỳ 1</option>
                                                        <option value="2">Học kỳ 2</option>
                                                        <option value="3">Học kỳ 3</option>
                                                        <option value="3">Học kỳ 4</option>
                                                        <option value="3">Học kỳ 5</option>
                                                        <option value="3">Học kỳ 6</option>
                                                        <option value="3">Học kỳ 7</option>
                                                        <option value="3">Học kỳ 8</option>
                                                        <option value="3">Học kỳ 9</option>
                                                        <!-- Thêm các học kỳ khác nếu cần -->
                                                    </select>
                                                </div>


                                                <div class="form-group">
                                                    <label for="credits">Học kỳ</label>
                                                    <select class="form-control" id="credits" name="credits">
                                                        <option value="${curriculum.credits}">Credits : ${curriculum.credits}</option>
                                                        <option value="1">Credits: 1</option>
                                                        <option value="2">Credits: 2</option>
                                                        <option value="3">Credits: 3</option>
                                                        <option value="4">Credits: 4</option>
                                                        <option value="5">Credits: 5</option>
                                                        <option value="6">Credits: 6</option>
                                                        <option value="7">Credits: 7</option>
                                                        <option value="8">Credits: 8</option>
                                                        <option value="9">Credits: 9</option>
                                                        <option value="10">Credits: 10</option>

                                                    </select>
                                                </div>
                                                <button type="submit" class="btn btn-primary">Cập nhật</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>



                <div id="pagination" class="pagination"></div>
            </div>










            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>




            <script>
                const rowsPerPage = 5; // Số hàng hiển thị trên mỗi trang
                let currentPage = 1; // Trang hiện tại

                function filterMajor() {
                    const selectedMajor = document.getElementById("majorSelect").value; // Lấy giá trị đã chọn
                    const rows = Array.from(document.querySelectorAll(".table tbody tr")); // Lấy tất cả hàng
                    let filteredRows = rows.filter(row => {
                        const majorCell = row.cells[1].textContent; // Cột chuyên ngành ở chỉ số 1
                        return selectedMajor === "all" || majorCell === selectedMajor;
                    });
                    // Hiển thị hàng theo trang
                    paginate(filteredRows);
                }

                function paginate(rows) {
                    // Ẩn tất cả hàng
                    const allRows = document.querySelectorAll(".table tbody tr");
                    allRows.forEach(row => row.style.display = "none");
                    // Tính toán số trang
                    const totalPages = Math.ceil(rows.length / rowsPerPage);
                    const startRow = (currentPage - 1) * rowsPerPage;
                    const endRow = startRow + rowsPerPage;
                    // Hiển thị hàng trong khoảng startRow đến endRow
                    rows.slice(startRow, endRow).forEach(row => row.style.display = "");
                    // Tạo phân trang
                    createPagination(totalPages);
                }

                function createPagination(totalPages) {
                    const paginationDiv = document.getElementById("pagination");
                    paginationDiv.innerHTML = ""; // Xóa nội dung cũ

                    // Nút Previous
                    const prevButton = document.createElement("button");
                    prevButton.textContent = "Previous";
                    prevButton.disabled = currentPage === 1;
                    prevButton.onclick = () => {
                        if (currentPage > 1) {
                            currentPage--;
                            filterMajor();
                        }
                    };
                    paginationDiv.appendChild(prevButton);
                    // Nút số trang
                    for (let i = 1; i <= totalPages; i++) {
                        const pageButton = document.createElement("button");
                        pageButton.textContent = i;
                        pageButton.className = (i === currentPage) ? "active" : "";
                        pageButton.onclick = () => {
                            currentPage = i;
                            filterMajor();
                        };
                        paginationDiv.appendChild(pageButton);
                    }

                    // Nút Next
                    const nextButton = document.createElement("button");
                    nextButton.textContent = "Next";
                    nextButton.disabled = currentPage === totalPages;
                    nextButton.onclick = () => {
                        if (currentPage < totalPages) {
                            currentPage++;
                            filterMajor();
                        }
                    };
                    paginationDiv.appendChild(nextButton);
                }

                // Gọi hàm filterMajor để hiển thị hàng đầu tiên
                filterMajor();
            </script>

        </div>
    </body>
</html>
