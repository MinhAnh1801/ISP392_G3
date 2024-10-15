<%-- 
    Document   : homepage
    Created on : Sep 24, 2024, 12:25:09 AM
    Author     : khucx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/style.css" />
        <link rel="stylesheet" href="./index.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    </head>
    <body>
        <div class="homepage-student">
            <div class="homepage-student-child">
            </div>
            <div class="university-academic-portal1 mt-1">University Academic Portal</div>
            <a href="logout" class="homepage-student-item bg-white flex hover:bg-slate-200 duration-200">
                <div class=" text-[18px] ml-[20px] mt-[12px]"> Log out </div>
                <svg class="mt-[14px] ml-[8px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M13.4766 21.2448H8.34056C7.04866 21.3045 5.78525 20.8527 4.82396 19.9876C3.86267 19.1224 3.28088 17.9134 3.20459 16.6224V7.37762C3.28088 6.08659 3.86267 4.87757 4.82396 4.01241C5.78525 3.14724 7.04866 2.69559 8.34056 2.75524H13.4766" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                <path d="M20.7952 12H7.44165" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
                <path d="M16.0833 17.136L20.4874 12.7319C20.6802 12.5371 20.7884 12.2742 20.7884 12C20.7884 11.7259 20.6802 11.4629 20.4874 11.2681L16.0833 6.86401" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
            </a>           
            <div class="homepage-student-inner">
            </div>
            <div class="rectangle-div">
            </div>
            <div class="homepage-student-child1">
            </div>
            <c:if test="${sessionScope.role != 0}">
                <div class="notificationsthng-bo">Notifications/Thông báo</div>
            </c:if>
            <c:if test="${sessionScope.role == 0}">
                <div class="notificationsthng-bo w-[500px]">Notifications/News Settings</div>
            </c:if>
            <c:if test="${sessionScope.role != 0}">
                <div class="profile-brief">
                    <div class="profile-brief-child">
                    </div>
                    <div class="name"></div>
                    <c:if test="${sessionScope.role == 1}">
                        <div class="roll-number">SV${sessionScope.user}</div>
                    </c:if>
                    <c:if test="${sessionScope.role == 2}">
                        <div class="roll-number">GV${sessionScope.user}</div>
                    </c:if>
                    <img class="photo-4-1682302453807184190466-icon" alt="" src="photo-4-1682302453807184190466 1.png">
                </div>
                <a href="profile" class="profile-detail">
                    <div class="profile-detail-child">
                    </div>
                    <svg class="hs-icon" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M19.6177 21.25C19.6177 17.6479 15.6021 14.7206 12 14.7206C8.39791 14.7206 4.38232 17.6479 4.38232 21.25" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
                    <path d="M11.9999 11.4559C14.4039 11.4559 16.3528 9.50701 16.3528 7.10294C16.3528 4.69888 14.4039 2.75 11.9999 2.75C9.59585 2.75 7.64697 4.69888 7.64697 7.10294C7.64697 9.50701 9.59585 11.4559 11.9999 11.4559Z" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>

                    <div class="xem-h-s">Xem hồ sơ</div>
                </a>
            </c:if>
            <c:if test="${sessionScope.role == 0}">
                <div class="absolute top-[100px] left-[20px] text-sm italic">logged in as admin</div>
            </c:if>
            <c:if test="${sessionScope.role != 0}">
                <div class="group-parent">
                    <div class="rectangle-parent">
                        <div class="group-child">
                        </div>
                        <div class="noti-time">20/09/24 10:59</div>
                        <div class="thng-bo-ti-sinh-vin-v-lc-wrapper">
                            <a href="" class="thng-bo-ti hover:underline">Thông báo tới sinh viên về lịch học ngày 21/09, vì thời tiết mưa lớn khiến nhiều nơi ngập lụt </a>
                        </div>
                    </div>                  
                </div> 
            </c:if>
            <c:if test="${sessionScope.role == 0}">
                <div class="view-notifications-upload-container">
                    <ul class="view-notifications-upload-noti list-disc text-left">
                        <li class="view-notifications hover:underline">
                            <a href="">View notifications</a></li>
                        <li class="hover:underline">
                            <a href="">Upload notifications</a></li>
                    </ul>
                </div>
                <div class="view-news-upload-container">
                    <ul class="view-notifications-upload-noti list-disc text-left">
                        <li class="view-notifications hover:underline">
                            <a href="newsAdmin">View news</a> 
                        </li>
                        <li class="hover:underline"> 
                            <button id="openModalBtn" class="cursor-pointer hover:underline">Upload news</button> 
                        </li>
                        <div class="hidden absolute w-[800px] top-[-100px] left-[100px] bg-white shadow-md rounded-lg z-10" id="modal">
                            <div class="h-[74px] w-full bg-slate-100 rounded-t-lg flex justify-items-center">
                                <p class="w-full text-center text-[26px] font-semibold m-auto leading-none">Upload new news</p>
                                <button id="close" class="absolute w-[32px] h-[32px] left-[740px] top-[24px]"><svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <circle cx="12" cy="12" r="10" stroke="#ff5900" stroke-width="1.5"></circle> <path d="M14.5 9.50002L9.5 14.5M9.49998 9.5L14.5 14.5" stroke="#ff5900" stroke-width="1.5" stroke-linecap="round"></path> </g></svg></button>
                            </div>     
                            <form action="insertNews" method="post" enctype="multipart/form-data">
                                <div class=" flex h-[74px] w-full m-auto flex ">
                                    <p class="m-auto w-[50px]">Title<span class="text-red-500"> *</span></p>
                                    <input type="text" id="title" name="title" required class="w-[700px] h-[60px] outline-none border-b-2 mr-2 mt-2">
                                </div>
                                <button class="ml-[21px] mt-5 mb-2" id="content_btn"> <p>Click to add Content</p></button>
                                <div class="hidden flex h-[74px] w-full m-auto flex " id="content_input">
                                    <p class="m-auto w-[50px]">Content</p>
                                    <textarea id="content" name="content" rows="6" class="w-[700px] h-[60px] outline-none border-b-2 mr-2 mt-2"></textarea>
                                </div>
                                <div class="ml-[21px] mt-5 mb-2">
                                    <p class="">Upload file<span class="text-red-500"> *</span></p>
                                </div>
                                <div class="mt-4 flex items-center space-x-6 ml-[32px] pb-6 border-b-2">
                                    <label class="block">
                                        <input type="file" id="img" name="img" accept="image/*" required class="block w-full text-sm text-slate-500
                                               file:mr-4 file:py-2 file:px-4
                                               file:rounded-full file:border-0
                                               file:text-sm file:font-semibold
                                               file:bg-violet-50 file:text-violet-700
                                               hover:file:bg-violet-100
                                               "/>
                                    </label>
                                </div>
                                <div class="flex justify-center">
                                    <button type="submit" class="mt-3 ml-auto text-white bg-green-700 hover:bg-green-800 focus:outline-none font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">
                                        Save
                                    </button>
                                </div>
                            </form>
                        </div>
                    </ul>
                </div>
            </c:if>        
            <div class="rectangle-parent6">
                <div class="group-child7">
                </div>
                <c:if test="${sessionScope.role != 0}">
                    <div class="academic-information">Academic Information/Thông tin</div>
                </c:if>
                <c:if test="${sessionScope.role == 0}">
                    <div class="academic-information">System Settings</div>                   
                </c:if>
            </div>
            <c:if test="${sessionScope.role == 0}">
                <div class="registraion1">
                    <div class="subject-settings">Subject settings</div>
                    <div class="view-list-of-container">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="view-notifications hover:underline">
                                <a href="">View list of subjects</a></li>
                            <li class="hover:underline"><a href="">Add new subject</a></li>
                        </ul>
                    </div>
                </div>
                <div class="registraion">
                    <div class="subject-settings">Students management</div>
                    <div class="import-list-of-container">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="hover:underline">
                                <a href="">Import list of student</a></li>
                        </ul>
                    </div>
                </div>
                <div class="lecturer-management-parent">
                    <div class="subject-settings">Lecturer management</div>
                    <div class="assign-lecturer">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="hover:underline"> <a href="">Assign lecturer</a></li>
                        </ul>
                    </div>
                </div>
                <div class="application3">
                    <div class="subject-settings">Class settings</div>
                    <div class="view-list-of-container">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="view-notifications hover:underline">
                                <a href="">View list of classes</a></li>
                            <li class="hover:underline"><a href="">Add new class</a></li>
                        </ul>
                    </div>
                </div>
                <div class="application1">
                    <div class="subject-settings">Classroom settings</div>
                    <div class="view-list-of-container2">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="view-notifications hover:underline">
                                <a href="">View list of classrooms</a></li>
                            <li class="hover:underline"><a href="">Add new classroom</a></li>
                        </ul>
                    </div>
                </div>
                <div class="report3">
                    <div class="subject-settings">Dorm settings</div>
                    <div class="view-list-of-container3">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="view-notifications hover:underline"><a href="">View list of rooms</a></li>
                            <li class="hover:underline"><a href="">Add new room</a></li>
                        </ul>
                    </div>
                </div>
                <div class="report1">
                    <div class="subject-settings">Application settings</div>
                    <div class="view-list-of-container3">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="view-notifications hover:underline"><a href="">View list of types of application</a></li>
                            <li class="view-notifications hover:underline"><a href="">Add new type of application</a></li>
                            <li class="hover:underline"><a href="">View list of applications</a></li>
                        </ul>
                    </div>
                </div>
                <div class="report2">
                    <div class="subject-settings">Guide settings</div>
                    <div class="view-list-of-container3">
                        <ul class="view-notifications-upload-noti list-disc text-left">
                            <li class="hover:underline"><a href="guideline">Manager guide</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--student-->
            <c:if test="${sessionScope.role == 1}">
                <div class="registraion">
                    <div class="registrationng-k">Registration/Đăng ký</div>
                    <div class="register-courses-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses">
                                <a href="" class="hover:underline">Register courses / Đăng ký môn học</a>
                            </li>
                            <li>
                                <a href="" class="hover:underline">Register dorm room / Đăng ký phòng ký túc xá</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--lecturer-->
            <c:if test="${sessionScope.role == 2}">
                <div class="registraion w-[430px]">
                    <div class="registrationng-k">Information access/Tra cứu thông tin</div>
                    <div class="register-courses-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses">
                                <a href="" class="hover:underline">Weekly time table / Thời khóa biểu hàng tuần</a>
                            </li>
                            <li>
                                <a href="" class="hover:underline">Check attendance / Điểm danh</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--student-->
            <c:if test="${sessionScope.role == 1}">
                <div class="informaion">
                    <div class="registrationng-k">Information access/Tra cứu thông tin</div>
                    <div class="weekly-timetable-container">
                        <ul class="register-courses-ng-k-mn list-disc ">
                            <li class="register-courses hover:underline"><a href="">Weekly timetable / Thời khóa biểu hàng tuần</a></li>
                            <li class="register-courses hover:underline"><a href="">Assignment / Bài tập</a></li>
                            <li class="register-courses hover:underline"><a href="">View exam schedule / Xem lịch thi</a></li>
                            <li><a class="hover:underline" href="guideline">Student guide / Hướng dẫn sinh viên</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--lecturer-->
            <c:if test="${sessionScope.role == 2}">
                <div class="informaion">
                    <div class="registrationng-k">Assignment/Bài tập</div>
                    <div class="weekly-timetable-container">
                        <ul class="register-courses-ng-k-mn list-disc ">
                            <li class="register-courses hover:underline"><a href="">Upload assignment / Giao bài tập</a></li>
                            <li class="register-courses hover:underline"><a href="">Grade assignments / Chấm điểm</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--student-->
            <c:if test="${sessionScope.role == 1}">
                <div class="application">
                    <div class="registrationng-k">Application/Đơn từ</div>
                    <div class="register-courses-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses hover:underline"><a href="">Send application / Gửi đơn</a></li>
                            <li><a class="hover:underline" href="">View application / Xem đơn đã gửi</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--lecturer-->
            <c:if test="${sessionScope.role == 2}">
                <div class="application w-[400px]">
                    <div class="registrationng-k">Subject materials/Tài liệu môn học</div>
                    <div class="register-courses-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses hover:underline"><a href="">Upload materials / Tải lên tài liệu</a></li>
                            <li><a class="hover:underline" href="lecturer/materials">View materials / Xem tài liệu</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--student-->
            <c:if test="${sessionScope.role == 1}">
                <div class="report">
                    <div class="registrationng-k">Report/Báo cáo</div>
                    <div class="attendance-report-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses hover:underline"><a href="">Attendance report / Báo cáo điểm danh</a></li>
                            <li class="register-courses hover:underline"><a href="">Mark report / Báo cáo điểm</a></li>
                            <li><a href="viewCurriculum" class="hover:underline">Curriculum / Khung chương trình</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <!--lecturer-->
            <c:if test="${sessionScope.role == 2}">
                <div class="report">
                    <div class="registrationng-k">Report/Báo cáo</div>
                    <div class="attendance-report-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses hover:underline"><a href="">Upload grades / Cập nhật điểm</a></li>
                            <li class="register-courses hover:underline"><a href="">Check grades / Xem điểm sinh viên</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.role == 1}">
                <div class="finance">
                    <div class="registrationng-k">Finance/Tài chính</div>
                    <div class="pay-paid-items-container">
                        <ul class="register-courses-ng-k-mn list-disc">
                            <li class="register-courses hover:underline"><a href="">Pay paid items / Thanh toán các khoản nộp</a></li>
                            <li><a href="" class="hover:underline">Transaction history / Lịch sử giao dịch</a></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <img class="line-icon" alt="" src="Line 2.svg">
            <c:if test="${sessionScope.role != 0}">
                <div class="mi-thc-mc-kin-vui-lng-parent">
                    <div class="mi-thc-mc">Mọi thắc mắc, ý kiến vui lòng liên hệ:</div>
                    <div class="phng-dch-v-container">
                        <p class="phng-dch-v">Phòng dịch vụ sinh viên: </p>
                        <p class="phng-dch-v">SĐT: 0123456789</p>
                        <p class="phng-dch-v">Email: abcdef@edu.com</p>
                    </div>
                    <div class="phng-o-to-container">
                        <p class="phng-dch-v">Phòng đào tạo: </p>
                        <p class="phng-dch-v">SĐT: 0123456789</p>
                        <p class="phng-dch-v">Email: abcdef@edu.com</p>
                    </div>
                </div>
            </c:if>
        </div>
        <script>
            // Get modal element
            var modal = document.getElementById("modal");

            // Get button that opens the modal
            var btn = document.getElementById("openModalBtn");

            // Get the <span> element that closes the modal
            var close = document.getElementById("close");

            var content = document.getElementById("content_btn");
            var content_input = document.getElementById("content_input");
            // When the user clicks the button, open the modal

            btn.onclick = function () {
                modal.classList.remove("hidden");
            }

            // When the user clicks on <span> (x), close the modal
            close.onclick = function () {
                modal.classList.add("hidden");
            }
            content.onclick = function () {
                content_input.classList.remove("hidden");
                content.classList.add("hidden");
            }
        </script>
    </body>
</html>
