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
            body {
                margin-top: 20px;
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
                transition-duration: .2s;
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
    </head>
    <body>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/ti-icons@0.1.2/css/themify-icons.css">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-7 col-lg-4 mb-5 mb-lg-0 wow fadeIn">
                    <div class="card border-0 shadow">
                        <img src="${lecturerProfile.photoUrl}" alt="${lecturerProfile.fullName}">
                        <div class="card-body p-1-9 p-xl-5">
                            <div class="mb-4">
                                <h3 class="h4 mb-0">${lecturerProfile.fullName}</h3>
                                <span class="text-primary">Lecturer</span>
                            </div>

                            <ul class="list-unstyled mb-4">
                                <li class="mb-3"><a href="mailto:${lecturerProfile.email}"><i class="far fa-envelope display-25 me-3 text-secondary"></i>${lecturerProfile.email}</a></li>
                                <li class="mb-3"><a href="tel:${lecturerProfile.phoneNumber}"><i class="fas fa-mobile-alt display-25 me-3 text-secondary"></i>${lecturerProfile.phoneNumber}</a></li>
                                <li><a href="#!"><i class="fas fa-map-marker-alt display-25 me-3 text-secondary"></i>${lecturerProfile.department}</a></li>
                            </ul>
                            <ul class="social-icon-style2 ps-0">
                                <li><a href="#!" class="rounded-3"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="#!" class="rounded-3"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="#!" class="rounded-3"><i class="fab fa-youtube"></i></a></li>
                                <li><a href="#!" class="rounded-3"><i class="fab fa-linkedin-in"></i></a></li>
                            </ul>
                            <ul class="social-icon-style2 ps-0">
                                <a href="changepassword">
                                    <button>Change password</button>
                                </a>
                            </ul>
                            <ul class="social-icon-style2 ps-0">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editProfileModal">Cập nhật hồ sơ</button>
                            </ul>

                            <script>
                                function openUpdateProfileModal() {
                                    // Mở modal hoặc chuyển hướng đến trang cập nhật hồ sơ
                                    // Ví dụ: Nếu bạn sử dụng Bootstrap cho modal
                                    $('#updateProfileModal').modal('show');
                                }



                            </script>
                            <!-- Modal -->
                            <div class="modal fade" id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editProfileModalLabel">Cập nhật Hồ sơ Giảng viên</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="lecturerProfileForm">
                                                <input type="hidden" id="lecturerId" value="${lecturerProfile.lecturerId}" />
                                                <div class="mb-3">
                                                    <label for="fullName" class="form-label">Full Name</label>
                                                    <input type="text" class="form-control" id="fullName" value="${lecturerProfile.fullName}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="email" class="form-label">Email</label>
                                                    <input type="email" class="form-control" id="email" value="${lecturerProfile.email}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="phoneNumber" class="form-label">Phone Number</label>
                                                    <input type="text" class="form-control" id="phoneNumber" value="${lecturerProfile.phoneNumber}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="department" class="form-label">Department</label>
                                                    <input type="text" class="form-control" id="department" value="${lecturerProfile.department}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="expertise" class="form-label">Expertise</label>
                                                    <input type="text" class="form-control" id="expertise" value="${lecturerProfile.expertise}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="researchSkill" class="form-label">Research Skill</label>
                                                    <input type="number" class="form-control" id="researchSkill" value="${lecturerProfile.researchSkill}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="teachingSkill" class="form-label">Teaching Skill</label>
                                                    <input type="number" class="form-control" id="teachingSkill" value="${lecturerProfile.teachingSkill}" required />
                                                </div>
                                                <div class="mb-3">
                                                    <label for="mentoringSkill" class="form-label">Mentoring Skill</label>
                                                    <input type="number" class="form-control" id="mentoringSkill" value="${lecturerProfile.mentoringSkill}" required />
                                                </div>
                                                <!-- Add other fields if needed -->
                                            </form>
                                        </div>


                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                            <button type="button" class="btn btn-primary" id="saveChanges">Lưu thay đổi</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="ps-lg-1-6 ps-xl-5">
                        <div class="mb-5 wow fadeIn">
                            <div class="text-start mb-1-6 wow fadeIn">
                                <h2 class="h1 mb-0 text-primary">#About Me</h2>
                            </div>
                            <p>${lecturerProfile.bio}</p>
                        </div>
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
                                            <p class="mb-0">${lecturerProfile.publications}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-xl-4 mt-4">
                                    <div class="card text-center border-0 rounded-3">
                                        <div class="card-body">
                                            <i class="ti-pencil-alt icon-box medium rounded-3 mb-4"></i>
                                            <h3 class="h5 mb-3">Career Start</h3>
                                            <p class="mb-0">After completing my education, I joined the workforce.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-xl-4 mt-4">
                                    <div class="card text-center border-0 rounded-3">
                                        <div class="card-body">
                                            <i class="ti-medall-alt icon-box medium rounded-3 mb-4"></i>
                                            <h3 class="h5 mb-3">Experience</h3>
                                            <p class="mb-0">Over 20 years of professional experience in academia.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wow fadeIn">
                            <div class="text-start mb-1-6 wow fadeIn">
                                <h2 class="mb-0 text-primary">#Kỹ năng &amp; Kinh nghiệm</h2>
                            </div>
                            <p class="mb-4">Tôi có chuyên môn trong nhiều lĩnh vực, bao gồm:</p>
                            <div class="progress-style1">
                                <div class="progress-text">
                                    <div class="row">
                                        <div class="col-6 fw-bold">Nghiên cứu</div>
                                        <div class="col-6 text-end">${lecturerProfile.researchSkill}%</div> <!-- Kỹ năng nghiên cứu -->
                                    </div>
                                </div>
                                <div class="custom-progress progress rounded-3 mb-4">
                                    <div class="animated custom-bar progress-bar slideInLeft" style="width:${lecturerProfile.researchSkill}%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="${lecturerProfile.researchSkill}" role="progressbar"></div>
                                </div>
                                <div class="progress-text">
                                    <div class="row">
                                        <div class="col-6 fw-bold">Giảng dạy</div>
                                        <div class="col-6 text-end">${lecturerProfile.teachingSkill}%</div> <!-- Kỹ năng giảng dạy -->
                                    </div>
                                </div>
                                <div class="custom-progress progress rounded-3 mb-4">
                                    <div class="animated custom-bar progress-bar bg-secondary slideInLeft" style="width:${lecturerProfile.teachingSkill}%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="${lecturerProfile.teachingSkill}" role="progressbar"></div>
                                </div>
                                <div class="progress-text">
                                    <div class="row">
                                        <div class="col-6 fw-bold">Hướng dẫn</div>
                                        <div class="col-6 text-end">${lecturerProfile.mentoringSkill}%</div> <!-- Kỹ năng hướng dẫn -->
                                    </div>
                                </div>
                                <div class="custom-progress progress rounded-3 mb-4">
                                    <div class="animated custom-bar progress-bar bg-danger slideInLeft" style="width:${lecturerProfile.mentoringSkill}%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="${lecturerProfile.mentoringSkill}" role="progressbar"></div>
                                </div>
                            </div>
                        </div>


                        <footer class="footer border-top mt-5 pt-4">
                            <div class="text-center">
                                <p class="mb-0">© 2024 Your Website. All rights reserved.</p>
                            </div>
                        </footer>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
