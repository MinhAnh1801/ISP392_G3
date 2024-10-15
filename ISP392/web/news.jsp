<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>News</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
    </head>
    <body>
        <script>
            $(document).ready(function () {
                $('#table').DataTable();
            });
        </script>
        <style>
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
            .forgot-password-container {
                background-color: #fff;
                display: flex;
                flex-direction: column;
                overflow: hidden;
            }


            .login-button {
                border-radius: 17px;
                background-color: #fff;
                display: flex;
                gap: 13px;
                padding: 14px;
            }

            .login-icon {
                aspect-ratio: 1;
                object-fit: contain;
                object-position: center;
                width: 24px;
            }

            .password-reset-form {
                border-radius: 8px;
                background-color: #fff;
                align-self: center;
                display: flex;
                margin-top: 91px;
                flex-direction: column;
                align-items: center;
                font-family: Open Sans, sans-serif;
                justify-content: start;
                padding: 65px 49px;
            }

            .form-title {
                color: #153060;
                font-size: 37px;
                font-weight: 400;
                line-height: 1;
            }

            .form-description {
                color: #828282;
                font-size: 13px;
                font-weight: 400;
                line-height: 20px;
                letter-spacing: 0.12px;
                margin-top: 26px;
            }

            .form-content {
                display: flex;
                margin-top: 26px;
                width: 100%;
                max-width: 437px;
                flex-direction: column;
            }

            .email-input-container {
                border-radius: 2px;
                display: flex;
                width: 100%;
                flex-direction: column;
                font-weight: 400;
            }

            .email-label {
                color: #153060;
                font-size: 11px;
                line-height: 1;
                letter-spacing: 0.2px;
            }

            .email-input {
                align-self: stretch;
                flex: 1;
                border-radius: 4.068px;
                border: 0.814px solid #9badca;
                width: 100%;
                font-size: 13px;
                color: #1e4178;
                letter-spacing: 0.12px;
                padding: 13px 9px;
            }

            .submit-button {
                align-self: stretch;
                width: 100%;
                border-radius: 4.068px;
                background-color: #1e4178;
                margin-top: 20px;
                font-size: 13px;
                color: #fff;
                font-weight: 600;
                text-align: center;
                text-transform: uppercase;
                letter-spacing: 0.12px;
                padding: 15px 26px;
                border: none;
                cursor: pointer;
            }

            @media (max-width: 991px) {
                .forgot-password-container {
                    padding-bottom: 100px;
                }

                .header {
                    max-width: 100%;
                    padding: 0 20px;
                }

                .password-reset-form {
                    max-width: 100%;
                    margin-top: 40px;
                    padding: 0 20px;
                }

                .form-title,
                .form-description,
                .form-content,
                .email-input-container,
                .email-input,
                .submit-button {
                    max-width: 100%;
                }
            }

            .visually-hidden {
                position: absolute;
                width: 1px;
                height: 1px;
                padding: 0;
                margin: -1px;
                overflow: hidden;
                clip: rect(0, 0, 0, 0);
                white-space: nowrap;
                border: 0;
            }
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 1920px;
                height: 72px;
            }
            .login-item {
                position: absolute;
                top: 10px;
                left: 47px;
                border-radius: 17px;
                width: 128px;
                height: 52px;
            }
            .dataTables_wrapper{
                position: absolute !important;
                top: 100px;
                left: 450px;
                width: 1100px;
            }
            .dataTables_length{
                left: -800px;
            }
        </style>
        <c:if test="${sessionScope.role==0}">
            <div class="login-child">
            </div>
            <a href="home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
                <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
                <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <div class="back m-auto">Return</div>
            </a>
            <button id="openModalBtn" class="flex absolute w-[135px] h-[36px] shadow-md bg-grey top-[100px] left-[250px] rounded-full bg-[#029F31]">
                <p class="pl-2 m-auto font-semibold text-white leading-none">ADD NEW</p>
                <div class="m-auto pr-1">
                    <svg width="24" height="23" viewBox="0 0 24 23" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M19 12.3158H13V18H11V12.3158H5V10.421H11V4.73684H13V10.421H19V12.3158Z" fill="white"/>
                    </svg>
                </div>
            </button>
            <table id="table" class="pb-8 absolute left-[-200px] top-[100px] table-fixed border-collapse border-2 shadow-md">
                <thead>
                    <tr>
                        <th class="border-y-2 w-[45px] h-[50px]">ID</th>
                        <th class="border-y-2 w-[236px] h-[50px]">Title</th>
                        <th class="border-y-2 w-[204px] h-[50px]">Image</th>
                        <th class="border-y-2 w-[306px] h-[50px]">Content</th>
                        <th class="border-y-2 w-[306px] h-[50px]">Upload Time</th>
                        <th class="border-y-2 w-[116px] h-[50px]">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="news" items="${newsList}">
                        <tr>
                            <th class="border-b-2 w-[45px] h-[50px] font-normal">${news.id}</th>
                            <th class="border-b-2 w-[236px] h-[50px] font-normal">${news.title}</th>
                            <th class="border-b-2 w-[204px] h-[50px] font-normal"><img src="image?name=${news.img}"></th>
                            <th class="border-b-2 w-[306px] h-[50px] font-normal trumcate">${news.content}</th>
                            <th class="border-b-2 w-[306px] h-[50px] font-normal">${news.uploadDate}</th>
                            <th class="border-b-2 w-[116px] h-[50px] font-normal"><button>Action</button></th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="hidden absolute w-[800px] top-[200px] left-[600px] bg-white shadow-md rounded-lg" id="modal">
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
        </c:if>
    </body>
</html>