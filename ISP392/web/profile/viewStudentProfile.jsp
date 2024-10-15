
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
    </head>
    <body>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/ti-icons@0.1.2/css/themify-icons.css">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-7 col-lg-4 mb-5 mb-lg-0 wow fadeIn">
                    <div class="card border-0 shadow">
                        <img src="${profile.profile_picture}" alt="${profile.profile_picture}">
                        <div class="card-body p-1-9 p-xl-5">
                            <div class="mb-4">
                                <h3 class="h4 mb-0">${profile.full_name}</h3>
                                <span class="text-primary">${profile.user_id.role}</span>
                            </div>
                            <ul class="list-unstyled mb-4">
                                <li class="mb-3"><a href="#!"><i class="far fa-envelope display-25 me-3 text-secondary"></i><span class="__cf_email__" data-cfemail="0165606a6e756041666c60686d2f626e6c">[email&#160;protected]</span></a></li>
                                <li class="mb-3"><a href="#!"><i class="fas fa-mobile-alt display-25 me-3 text-secondary"></i>${profile.phone_number}</a></li>
                                <li><a href="#!"><i class="fas fa-map-marker-alt display-25 me-3 text-secondary"></i>${profile.address}</a></li>
                            </ul>
                            <ul class="social-icon-style2 ps-0">
                                <a href="changepassword">
                                    <button>Change password</button>
                                </a>
                            </ul>

                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="ps-lg-1-6 ps-xl-5">
                        <div class="mb-5 wow fadeIn">
                            <div class="text-start mb-1-6 wow fadeIn">
                                <h2 class="h1 mb-0 text-primary">#About Me</h2>
                            </div>
                            <p>${profile.bio}</p>
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
                        <div class="mb-5 wow fadeIn">
                            <div class="text-start mb-1-6 wow fadeIn">
                                <h2 class="mb-0 text-primary">#Profile Details</h2>
                            </div>
                            <ul class="list-unstyled mb-4">
                                <li class="mb-3"><i class="far fa-calendar-alt display-25 me-3 text-secondary"></i> Date of Birth: ${studentProfile.date_of_birth}</li>
                                <li class="mb-3"><i class="fas fa-id-card-alt display-25 me-3 text-secondary"></i> Student Code: ${studentProfile.student_code}</li>
                                <li class="mb-3"><i class="fas fa-graduation-cap display-25 me-3 text-secondary"></i> Enrollment Year: ${studentProfile.enrollment_year}</li>
                                <li class="mb-3"><i class="fas fa-calendar-check display-25 me-3 text-secondary"></i> Graduation Year: ${studentProfile.graduation_year}</li>
                                <li class="mb-3"><i class="fas fa-star display-25 me-3 text-secondary"></i> GPA: ${studentProfile.gpa}</li>
                                <li class="mb-3"><i class="fas fa-award display-25 me-3 text-secondary"></i> Scholarship Status: 
                                    <c:choose>
                                        <c:when test="${studentProfile.scholarship_status}">
                                            Yes
                                        </c:when>
                                        <c:otherwise>
                                            No
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                                <li class="mb-3"><i class="fas fa-notes-medical display-25 me-3 text-secondary"></i> Medical Conditions: ${studentProfile.medical_conditions}</li>
                            </ul>
                        </div>

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