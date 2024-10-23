<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Application</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'DownloadTemplateController', true);
            xhr.responseType = 'blob'; 

            xhr.onload = function () {
                if (xhr.status === 200) {
                    var link = document.createElement('a');
                    link.href = window.URL.createObjectURL(xhr.response);
                    link.download = 'template.zip'; 
                    link.click();
                } else {
                    alert('Error: Unable to download template.');
                }
            };

            xhr.send();
        }
    </script>
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
    <div class="container">
        <h2 class="text-center">Send an Application to Academic Administration Dept</h2>
        <p class="text-center">Bộ phận xử lý đơn/email sẽ trả lời trong vòng 48h.</p>
        <form action="ApplicationController?action=submitApplication" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="applicationType">Application Type:</label>
                <select class="form-control" id="applicationType" name="applicationType" required>
                    <c:forEach var="applicationType" items="${applicationTypes}">
                        <option value="${applicationType.id}">${applicationType.typeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
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
