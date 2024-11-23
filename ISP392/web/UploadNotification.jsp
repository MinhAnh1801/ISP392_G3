<%-- 
    Document   : UploadNotification
    Created on : Oct 8, 2024, 9:31:03 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <title>Upload Notification</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #FF6F00;
                color: white;
                padding: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .taskbar h1 {
                margin: 0;
            }
            .logout-button {
                color: white;
                text-decoration: none;
                display: flex;
                align-items: center;
            }
            .logout-button img {
                margin-right: 5px;
            }
            .container1 {
                max-width: 800px;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border: 2px solid #FF6F00;
            }
            .container1 h2 {
                color: #FF6F00;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
                color: #FF6F00;
            }
            input[type="text"], input[type="submit"] {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border-radius: 4px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            select{
                height: 42px;
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border-radius: 4px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #FF6F00;
                color: white;
                cursor: pointer;
                border: none;
            }
            input[type="submit"]:hover {
                background-color: #e65c00;
            }
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
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container1 mt-20">
            <h2 class="text-3xl">Upload Notification</h2>
            <form action="notice" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="content">Content:</label>
                    <input type="text" id="content" name="content" required>
                </div>
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select name="role" id="role" required>
                        <option value="Student">Student</option>
                        <option value="Lecturer">Lecturer</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" name="upload" value="Upload">
                </div>
            </form>
        </div>
    </body>
</html>
