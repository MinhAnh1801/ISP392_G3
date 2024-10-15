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
            .container {
                max-width: 800px;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border: 2px solid #FF6F00;
            }
            .container h2 {
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
            input[type="submit"] {
                background-color: #FF6F00;
                color: white;
                cursor: pointer;
                border: none;
            }
            input[type="submit"]:hover {
                background-color: #e65c00;
            }
        </style>
    </head>
    <body>
        <div class="taskbar">
            <h1>University Academic Portal</h1>
            <a href="home" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/FFFFFF/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>
        <div class="container">
            <h2>Upload Notification</h2>
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
