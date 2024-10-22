
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                margin-top:20px;
            }
            .icon-box.medium {
                font-size: 20px;
                width: 50px;
                height: 50px;
                line-height: 50px;
            }
            .icon-box {
                font-size: 30px;
                margin-bottom: 33px;
                display: inline-block;
                color: #ffffff;
                height: 65px;
                width: 65px;
                line-height: 65px;
                background-color: #59b73f;
                text-align: center;
                border-radius: 0.3rem;
            }
            .social-icon-style2 li a {
                display: inline-block;
                font-size: 14px;
                text-align: center;
                color: #ffffff;
                background: #59b73f;
                height: 41px;
                line-height: 41px;
                width: 41px;
            }
            .rounded-3 {
                border-radius: 0.3rem !important;
            }

            .social-icon-style2 {
                margin-bottom: 0;
                display: inline-block;
                padding-left: 10px;
                list-style: none;
            }

            .social-icon-style2 li {
                vertical-align: middle;
                display: inline-block;
                margin-right: 5px;
            }

            a, a:active, a:focus {
                color: #616161;
                text-decoration: none;
                transition-timing-function: ease-in-out;
                -ms-transition-timing-function: ease-in-out;
                -moz-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                transition-duration: .2s;
                -ms-transition-duration: .2s;
                -moz-transition-duration: .2s;
                -webkit-transition-duration: .2s;
                -o-transition-duration: .2s;
            }

            .text-secondary, .text-secondary-hover:hover {
                color: #59b73f !important;
            }
            .display-25 {
                font-size: 1.4rem;
            }

            .text-primary, .text-primary-hover:hover {
                color: #ff712a !important;
            }

            p {
                margin: 0 0 20px;
            }

            .mb-1-6, .my-1-6 {
                margin-bottom: 1.6rem;
            }

        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>



        <div class="position-fixed top-0 end-0 p-3" style="z-index: 11;">
            <div id="toastMessage" class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true" style="display: none;">
                <div class="d-flex">
                    <div class="toast-body">
                        ${mess}
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>

            <div id="toastError" class="toast align-items-center text-white bg-danger border-0" role="alert" aria-live="assertive" aria-atomic="true" style="display: none;">
                <div class="d-flex">
                    <div class="toast-body">
                        ${error}
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>

        <script>
            // Lấy các biến mess và error (thay đổi tùy theo cách bạn lấy giá trị thực tế)
            var mess = "${mess}"; // Giả định có giá trị nào đó
            var error = "${error}"; // Giả định có giá trị nào đó

            // Lấy các phần tử toast
            var toastMessage = document.getElementById('toastMessage');
            var toastError = document.getElementById('toastError');

            if (mess) {
                // Hiển thị thông báo thành công nếu có mess
                toastMessage.querySelector('.toast-body').textContent = mess; // Cập nhật nội dung
                toastMessage.style.display = 'block'; // Hiện toast
                var bootstrapToastMessage = new bootstrap.Toast(toastMessage);
                bootstrapToastMessage.show();

                // Thiết lập ẩn toast sau 5 giây
                setTimeout(function () {
                    bootstrapToastMessage.hide();
                }, 5000);
            }

            if (error) {
                // Hiển thị thông báo lỗi nếu có error
                toastError.querySelector('.toast-body').textContent = error; // Cập nhật nội dung
                toastError.style.display = 'block'; // Hiện toast
                var bootstrapToastError = new bootstrap.Toast(toastError);
                bootstrapToastError.show();

                // Thiết lập ẩn toast sau 5 giây
                setTimeout(function () {
                    bootstrapToastError.hide();
                }, 5000);
            }
        </script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/ti-icons@0.1.2/css/themify-icons.css">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-7 col-lg-4 mb-5 mb-lg-0 wow fadeIn">
                    <div class="card border-0 shadow">
                        <div class="photo-container">
                            <img src="${studentProfile.photo}" alt="${studentProfile.photo}" class="profile-photo">
                        </div>
                        <style>
                            .photo-container {
                                text-align: center; /* Căn giữa theo chiều ngang */
                            }

                            .profile-photo {
                                width: 200px; /* Chiều rộng của ảnh */
                                height: 200px; /* Chiều cao của ảnh */
                                border-radius: 50%; /* Để tạo hình tròn */
                                object-fit: cover; /* Để đảm bảo ảnh được cắt theo hình tròn mà không bị méo */
                                border: 2px solid #ccc; /* Thêm viền nếu cần */
                            }


                        </style>
                        <div class="card-body p-1-9 p-xl-5">
                            <div class="mb-4">
                                <h3 class="h4 mb-0">${studentProfile.full_name}</h3>
                                <span class="text-primary">Student</span>
                            </div>
                            <ul class="list-unstyled mb-4">
                                <li class="mb-3"><a href="#!"><i class="far fa-envelope display-25 me-3 text-secondary"></i><span class="__cf_email__" data-cfemail="0165606a6e756041666c60686d2f626e6c">[email&#160;protected]</span></a></li>
                                <li class="mb-3"><a href="#!"><i class="fas fa-mobile-alt display-25 me-3 text-secondary"></i>${studentProfile.phone_number}</a></li>
                                <li><a href="#!"><i class="fas fa-map-marker-alt display-25 me-3 text-secondary"></i>${studentProfile.address}</a></li>
                            </ul>




                            <div>
                                <!-- Change Password Button -->
                                <ul class="social-icon-style2 ps-0">
                                    <li>
                                        <a style="   text-decoration: none;" href="changepassword" class="change-password-btn" id="change-password-btn">
                                            Đổi mật khẩu
                                        </a>


                                    </li>
                                </ul>

                                <!-- Update Profile Button -->
                                <ul class="social-icon-style2 ps-0">
                                    <li>
                                        <button id="update-profile-btn" data-bs-toggle="modal" data-bs-target="#updateProfileModal">
                                            Update profile
                                        </button>
                                    </li>
                                </ul>
                            </div>
                            <style>
                                /* Reset basic styles */
                                ul {
                                    list-style-type: none;
                                    padding: 0;
                                    margin: 0;
                                    display: inline-block; /* Giữ các ul nằm ngang */
                                }

                                li {
                                    margin-bottom: 0; /* Bỏ khoảng cách dọc giữa các item */
                                }

                                /* Button container: Dùng flexbox để căn chỉnh các nút nằm ngang */
                                .button-container {
                                    display: flex;
                                    justify-content: space-between; /* Tạo khoảng cách đều giữa các nút */
                                    gap: 15px; /* Khoảng cách giữa các nút */
                                    flex-wrap: wrap; /* Đảm bảo các nút sẽ xuống dòng khi không đủ chỗ */
                                }

                                /* Base button styles */
                                button, .btn {
                                    padding: 12px 20px;
                                    font-size: 16px;
                                    font-weight: 600;
                                    color: #fff;
                                    background-color: #007bff;
                                    border: none;
                                    border-radius: 8px;
                                    cursor: pointer;
                                    display: inline-block;
                                    text-align: center;
                                    transition: background-color 0.3s ease, transform 0.2s ease;
                                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                                }

                                /* Hover effect for buttons */
                                button:hover, .btn:hover {
                                    transform: scale(1.05);
                                }

                                /* Specific styles for buttons */
                                #change-password-btn {
                                    background-color: #28a745;
                                    width: 120px;
                                }

                                #change-password-btn:hover {
                                    background-color: #218838;
                                    text-decoration: none;
                                }

                                #update-profile-btn {
                                    background-color: #6c757d;
                                }

                                #update-profile-btn:hover {
                                    background-color: #5a6268;
                                }

                                /* Align text inside buttons to the center */
                                button, .btn {
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                    text-align: center;
                                }

                                /* Full width buttons on mobile */
                                @media (max-width: 576px) {
                                    button, .btn {
                                        width: 100%;
                                    }
                                }


                            </style>


                            <!-- Modal Update Profile -->
                            <div class="modal fade" id="updateProfileModal" tabindex="-1" aria-labelledby="updateProfileModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content border-0 shadow-lg rounded">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="updateProfileModalLabel">Cập nhật hồ sơ</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="updateProfileForm" action="profile" method="post">
                                                <input type="text" value="student" name="action" hidden="">

                                                <div class="mb-3">
                                                    <label for="phoneNumber" class="form-label">Số điện thoại</label>
                                                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${studentProfile.phone_number}" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="address" class="form-label">Địa chỉ</label>
                                                    <input type="text" class="form-control" id="address" name="address" value="${studentProfile.address}" required>
                                                </div>

                                                <div class="mb-3">
                                                    <label for="parentName" class="form-label">Tên phụ huynh</label>
                                                    <input type="text" class="form-control" id="parentName" name="parentName" value="${studentProfile.ten_phu_huynh}" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="parentPhone" class="form-label">Số điện thoại phụ huynh</label>
                                                    <input type="tel" class="form-control" id="parentPhone" name="parentPhone" value="${studentProfile.so_dien_thoai_phu_huynh}" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="parentAddress" class="form-label">Địa chỉ phụ huynh</label>
                                                    <input type="text" class="form-control" id="parentAddress" name="parentAddress" value="${studentProfile.dia_chi_phu_huynh}" required>
                                                </div>

                                                <div class="mb-3">
                                                    <label for="parentOccupation" class="form-label">Nghề nghiệp phụ huynh</label>
                                                    <input type="text" class="form-control" id="parentOccupation" name="parentOccupation" value="${studentProfile.nghe_nghiep_phu_huynh}" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="parentWorkplace" class="form-label">Nơi làm việc phụ huynh</label>
                                                    <input type="text" class="form-control" id="parentWorkplace" name="parentWorkplace" value="${studentProfile.noi_lam_viec_phu_huynh}" required>
                                                </div>

                                                <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <style>
                                .modal-content {
                                    border-radius: 15px; /* Thay đổi độ tròn của góc */
                                    transition: transform 0.3s ease; /* Hiệu ứng khi mở modal */
                                }

                                .modal-header {
                                    background-color: #007bff; /* Màu nền cho header */
                                    color: #fff; /* Màu chữ trong header */
                                    border-top-left-radius: 15px; /* Bo góc trái trên */
                                    border-top-right-radius: 15px; /* Bo góc phải trên */
                                }

                                .modal-body {
                                    background-color: #fdfdfd; /* Màu nền cho body */
                                    border: 1px solid #ddd; /* Đường viền nhẹ */
                                }

                                .form-label {
                                    font-weight: bold; /* Làm chữ đậm cho nhãn */
                                }

                                .btn-primary {
                                    background-color: #0056b3; /* Màu nền của nút */
                                    border: none; /* Bỏ viền */
                                }

                                .btn-primary:hover {
                                    background-color: #004494; /* Màu nền khi hover */
                                }
                            </style>



                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="ps-lg-1-6 ps-xl-5">

                        <div class="mb-5 wow fadeIn">
                            <div class="text-start mb-1-6 wow fadeIn">
                                <h2 class="mb-0 text-primary">#Education</h2>
                            </div>
                            <div class="row mt-n4">
                                <div class="col-sm-6 col-xl-4 mt-4">
                                    <div class="card text-center border-0 rounded-3">
                                        <div class="card-body">
                                            <i class="ti-bookmark-alt icon-box medium rounded-3 mb-4"></i>
                                            <h3 class="h5 mb-3">Education</h3>
                                            <p class="mb-0">${studentProfile.major_id.name}</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-xl-4 mt-4">
                                    <div class="card text-center border-0 rounded-3">
                                        <div class="card-body">
                                            <i class="ti-medall-alt icon-box medium rounded-3 mb-4"></i>
                                            <h3 class="h5 mb-3">Experience</h3>
                                            <p class="mb-0">${studentProfile.year_of_study}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="profile-container mb-5 wow fadeIn">
                            <div class="profile-header text-start mb-1-6 wow fadeIn">
                                <h2 class="profile-title mb-0 text-primary">#Profile Details</h2>
                            </div>

                            <div class="profile-content">
                                <div class="profile-section">
                                    <h3 class="section-title">Student Information</h3>
                                    <div class="profile-info">
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="far fa-calendar-alt display-25 me-3 text-secondary"></i> 
                                                <strong>Date of Birth:</strong> ${studentProfile.date_of_birth}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-id-card-alt display-25 me-3 text-secondary"></i> 
                                                <strong>Student Code:</strong> ${studentProfile.student_code}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-envelope display-25 me-3 text-secondary"></i> 
                                                <strong>Email:</strong> ${studentProfile.email}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-phone display-25 me-3 text-secondary"></i> 
                                                <strong>Phone Number:</strong> ${studentProfile.phone_number}
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="fas fa-map-marker-alt display-25 me-3 text-secondary"></i> 
                                                <strong>Address:</strong> ${studentProfile.address}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-book display-25 me-3 text-secondary"></i> 
                                                <strong>Major:</strong> ${studentProfile.nganh_hoc}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-university display-25 me-3 text-secondary"></i> 
                                                <strong>Program:</strong> ${studentProfile.chuong_trinh_hoc}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-calendar-check display-25 me-3 text-secondary"></i> 
                                                <strong>Enrollment Year:</strong> ${studentProfile.enrollment_year}
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="fas fa-calendar-check display-25 me-3 text-secondary"></i> 
                                                <strong>Graduation Year:</strong> ${studentProfile.graduation_year}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-star display-25 me-3 text-secondary"></i> 
                                                <strong>GPA:</strong> ${studentProfile.gpa}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-award display-25 me-3 text-secondary"></i> 
                                                <strong>Scholarship Status:</strong> 
                                                <span class="status ${studentProfile.scholarship_status ? 'status-yes' : 'status-no'}">
                                                    ${studentProfile.scholarship_status ? 'Yes' : 'No'}
                                                </span>
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-notes-medical display-25 me-3 text-secondary"></i> 
                                                <strong>Medical Conditions:</strong> ${studentProfile.medical_conditions}
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="profile-section">
                                    <h3 class="section-title">Parent Information</h3>
                                    <div class="profile-info">
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="fas fa-user-friends display-25 me-3 text-secondary"></i> 
                                                <strong>Parent's Name:</strong> ${studentProfile.ten_phu_huynh}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-phone-square-alt display-25 me-3 text-secondary"></i> 
                                                <strong>Parent's Phone:</strong> ${studentProfile.so_dien_thoai_phu_huynh}
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="fas fa-home display-25 me-3 text-secondary"></i> 
                                                <strong>Parent's Address:</strong> ${studentProfile.dia_chi_phu_huynh}
                                            </li>
                                            <li class="mb-3">
                                                <i class="fas fa-briefcase display-25 me-3 text-secondary"></i> 
                                                <strong>Parent's Job:</strong> ${studentProfile.nghe_nghiep_phu_huynh}
                                            </li>
                                        </ul>
                                        <ul class="list-unstyled profile-list mb-4">
                                            <li class="mb-3">
                                                <i class="fas fa-building display-25 me-3 text-secondary"></i> 
                                                <strong>Parent's Workplace:</strong> ${studentProfile.noi_lam_viec_phu_huynh}
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <!-- Thông tin bổ sung -->
                            <div class="profile-section">
                                <h3 class="section-title">Additional Information</h3>
                                <ul class="list-unstyled profile-list mb-4">
                                    <li class="mb-3">
                                        <i class="fas fa-id-badge display-25 me-3 text-secondary"></i> 
                                        <strong>National ID:</strong> ${studentProfile.national_id}
                                    </li>
                                    <li class="mb-3">
                                        <i class="fas fa-file-alt display-25 me-3 text-secondary"></i> 
                                        <strong>Graduation Thesis:</strong> ${studentProfile.de_tai_tot_nghiep}
                                    </li>
                                    <li class="mb-3">
                                        <i class="fas fa-piggy-bank display-25 me-3 text-secondary"></i> 
                                        <strong>Account Balance:</strong> ${studentProfile.so_du_tai_khoan}
                                    </li>
                                </ul>
                            </div>
                        </div>


                        <style>
                            .profile-content {
                                display: flex;
                                justify-content: space-between;
                                flex-wrap: wrap; /* Cho phép phần tử xuống dòng nếu không đủ chỗ */
                            }

                            .profile-section {
                                flex: 1 1 45%; /* Chiếm khoảng 45% chiều rộng, có thể điều chỉnh tùy thuộc vào độ rộng màn hình */
                                margin: 15px; /* Khoảng cách giữa các phần */
                                padding: 15px;
                                background-color: #ffffff; /* Màu nền phần thông tin */
                                border-radius: 8px; /* Bo tròn góc */
                                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng */
                            }


                        </style>






                    </div>
                </div>
            </div>
        </div>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>