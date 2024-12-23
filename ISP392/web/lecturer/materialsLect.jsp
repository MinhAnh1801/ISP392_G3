<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Materials</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <!--<script src="https://code.jquery.com/jquery-3.5.1.js"></script>-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />

    </head>
    <body>
        <script>
            $(document).ready(function () {
                $('#mytable').DataTable();
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
                width: 100%;
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
            .notification {
                display: none;
                padding: 10px;
                margin-top: 20px;
                border-radius: 5px;
                font-weight: bold;
            }
            .notification.success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .notification.error {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
            .dataTables_wrapper{
                position: absolute !important;
                top: 100px;
                left: 500px;
                width: 1100px;
            }
            .dataTables_length{
                left: -800px;
            }
            body{
                height: 1000px;
            }
        </style>
        <c:if test="${sessionScope.role==2}">
            <div class="login-child">
            </div>
            <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
                <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
                <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <div class="back m-auto">Return</div>
            </a>
            <button id="openModalBtn" class="flex absolute w-[135px] h-[36px] shadow-md bg-grey top-[100px] left-[300px] rounded-full bg-[#029F31]">
                <p class="pl-2 m-auto font-semibold text-white leading-none">ADD NEW</p>
                <div class="m-auto pr-1">
                    <svg width="24" height="23" viewBox="0 0 24 23" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M19 12.3158H13V18H11V12.3158H5V10.421H11V4.73684H13V10.421H19V12.3158Z" fill="white"/>
                    </svg>
                </div>
            </button>
            <table id="mytable" class="left-[-180px] top-[100px] absolute table-fixed border-collapse border-2 shadow-md ">
                <thead>
                    <tr>
                        <th class="border-y-2 w-[160px] h-[50px]">Subject</th>
                        <th class="border-y-2 w-[200px] h-[50px]">Material Name</th>
                        <th class="border-y-2 w-[240px] h-[50px] truncate">Material File</th>
                        <th class="border-y-2 w-[130px] h-[50px]">Upload Time</th>
                        <th class="border-y-2 w-[300px] h-[50px]">Description</th>
                        <th class="border-y-2 w-[116px] h-[50px]">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="material" items="${materials}">
                        <tr class="text-center">
                            <td class="border-y-2 w-[160px] h-[50px]"><div class="w-[160px]">${material.subjectCode}</div></td>
                            <td class="border-y-2 h-[50px]"><div class="w-[200px] mx-2 truncate">${material.materialName}</div></td>
                            <td class="border-y-2 w-[240px] h-[50px] text-[#0c59ff]"><div class="w-[240px] mx-2 truncate"><a class="hover:underline" href="download?file=${material.materialFile}">${material.materialFile}</a></div></td>
                            <td class="border-y-2 w-[130px] h-[50px]">${material.uploadedAt}</td>
                            <td class="border-y-2 w-[300px] h-[50px] truncate"><div class="w-[300px] mx-2 truncate">${material.description}</div></td>
                            <th class="border-y-2 w-[116px] h-[50px]">Action</th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="hidden absolute w-[800px] top-[200px] left-[600px] bg-white shadow-md rounded-lg" id="modal">
                <div class="h-[74px] w-full bg-slate-100 rounded-t-lg flex justify-items-center">
                    <p class="w-full text-center text-[26px] font-semibold m-auto leading-none">Upload new Material</p>
                    <button id="close" class="absolute w-[32px] h-[32px] left-[740px] top-[24px]"><svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <circle cx="12" cy="12" r="10" stroke="#ff5900" stroke-width="1.5"></circle> <path d="M14.5 9.50002L9.5 14.5M9.49998 9.5L14.5 14.5" stroke="#ff5900" stroke-width="1.5" stroke-linecap="round"></path> </g></svg></button>
                </div>     
                <form id="materialForm" method="post" enctype="multipart/form-data">
                    <div class="pl-[21px] pt-8 h-[74px] w-full max-w-sm min-w-[200px]">      
                        <div class="relative">
                            <select id="subject_id" name="subject_id" required
                                    class="w-full bg-transparent placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded pl-3 pr-8 py-2 transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md appearance-none cursor-pointer">
                                <option value="none">Choose Subject</option>
                                <c:forEach var="subject" items="${subjectList}">
                                    <option value="${subject.id}"> ${subject.code}</option>
                                </c:forEach>
                            </select>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.2" stroke="currentColor" class="h-5 w-5 ml-1 absolute top-2.5 right-2.5 text-slate-700">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 15 12 18.75 15.75 15m-7.5-6L12 5.25 15.75 9" />
                            </svg>
                        </div>
                    </div>
                    <div class=" flex h-[74px] w-full m-auto flex ">
                        <p class="m-auto w-[50px]">Title<span class="text-red-500"> *</span></p>
                        <input type="text" id="title" name="title" required class="w-[700px] h-[60px] outline-none border-b-2 mr-2 mt-2">
                    </div>
                    <button class="ml-[21px] mt-5 mb-2" id="content_btn"> <p>Click to add Content</p></button>
                    <div class="hidden flex h-[74px] w-full m-auto flex " id="content_input">
                        <p class="m-auto w-[50px]">Content</p>
                        <textarea id="description" name="description" rows="3" class="w-[700px] h-[60px] outline-none border-b-2 mr-2 mt-2"></textarea>
                    </div>
                    <div class="ml-[21px] mt-5 mb-2">
                        <p class="">Upload file<span class="text-red-500"> *</span></p>
                    </div>
                    <div class="mt-4 flex items-center space-x-6 ml-[32px] pb-6 border-b-2">
                        <label class="block">
                            <input type="file" id="material_file" name="material_file" required class="block w-full text-sm text-slate-500
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
        </c:if>
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
        <script>
            document.getElementById('materialForm').addEventListener('submit', function (event) {
                event.preventDefault(); // Prevent default form submission

                const form = document.getElementById('materialForm');
                const formData = new FormData(form); // Create FormData object for file upload

                const xhr = new XMLHttpRequest();
                xhr.open('POST', 'addMaterial', true);

                xhr.onload = function () {
                    const response = JSON.parse(xhr.responseText);

                    // Show an alert message based on the response
                    if (xhr.status === 200 && response.status === 'success') {
                        alert(response.message); // Show success message
                        // Refresh the page after the alert is closed
                        window.location.reload();
                    } else {
                        alert(response.message); // Show error message
                    }
                };

                xhr.onerror = function () {
                    alert('An unexpected error occurred. Please try again.'); // Show error message on network failure
                };

                // Send the form data
                xhr.send(formData);
            });
        </script>

    </body>
</html>
