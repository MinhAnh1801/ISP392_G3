<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Submit Application</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script>
            function toggleFileUpload() {
                var checkBox = document.getElementById("attachFile");
                var fileUploadSection = document.getElementById("fileUploadSection");
                if (checkBox.checked) {
                    fileUploadSection.style.display = "block";
                } else {
                    fileUploadSection.style.display = "none";
                }
            }

            function downloadTemplate() {
                // Get the selected option
                const select = document.getElementById("applicationType");
                const selectedOption = select.options[select.selectedIndex];

                // Get the template file name from the data-template attribute
                const templateFile = selectedOption.getAttribute("data-template");

                // Check if a template is available
                if (templateFile) {
                    // Construct the download URL
                    const downloadUrl = "downloadfile?file=" + templateFile;

                    // Redirect to the download URL to initiate the download
                    window.location.href = downloadUrl;
                } else {
                    alert("No template available for the selected application type.");
                }
            }
        </script>
        <style>
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 100%;
                height: 72px;
            }
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
            .login-item {
                position: absolute;
                top: 10px;
                left: 47px;
                border-radius: 17px;
                width: 128px;
                height: 52px;
            }
        </style>
    </head>
    <body>
        <!-- Navigation bar -->
<div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container w-[60%] mt-20">
            <h2 class="text-center text-3xl">Send an Application to Academic Administration Dept</h2>
            <p class="text-center text-xl mb-2">Bộ phận xử lý đơn/email sẽ trả lời trong vòng 48h.</p>
            <form action="ApplicationController?action=submitApplication" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="applicationType" class="text-xl">Application Type:</label>
                    <select class="form-control" id="applicationType" name="applicationType" required>
                        <c:forEach var="applicationType" items="${applicationTypes}">
                            <option value="${applicationType.id}" data-template="${applicationType.template}">${applicationType.typeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="content" class="text-xl">Content:</label>
                    <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
                </div>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="attachFile" name="attachFile" onclick="toggleFileUpload()">
                    <label class="form-check-label" for="attachFile">Attach File</label>
                </div>
                <div class="form-group" id="fileUploadSection" style="display:none;">
                    <label for="file">Choose file to upload:</label>
                    <input type="file" class="form-control" id="file" name="file">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <div class="mt-4">
                <button type="button" class="btn btn-info" onclick="downloadTemplate()">Download Application Templates (ZIP)</button>
            </div>
        </div>
    </body>
</html>
