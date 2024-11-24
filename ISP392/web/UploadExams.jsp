<%-- 
    Document   : UploadExams
    Created on : Oct 12, 2024, 2:54:25 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Exam Schedule</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }
            .taskbar {
                background-color: #ff8c00; /* Màu cam đậm */
                color: white;
                padding: 20px;
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
                padding: 20px;
            }
            h2 {
                color: #ff8c00; /* Màu cam đậm */
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                font-weight: bold;
                color: #ff8c00; /* Màu cam cho label */
                display: block;
                margin-bottom: 5px;
            }
            input[type="text"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #ffa500; /* Đường viền màu cam */
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #ffa500; /* Màu cam sáng cho nút */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #ff8c00; /* Thay đổi màu khi hover */
            }
        </style>
    </head>
    <body>
        <div class="taskbar">
            <h1>University Academic Portal</h1>
            <a href="#" class="logout-button">
                <img src="https://img.icons8.com/ios-filled/20/FFFFFF/logout-rounded--v1.png" alt="Logout Icon"> Log out
            </a>
        </div>
        <div class="container">
            <h2>Upload Schedule</h2>
            <form action="exam" method="post">
<!--                <div class="form-group">
                    <label for="id">ID:</label>
                    <input type="text" id="id" name="id" required>
                </div>-->
                <div class="form-group">
                    <label for="subject_id">Subject_ID:</label>
                    <input type="text" id="subject_id" name="subject_id" required>
                </div>
                <div class="form-group">
                    <label for="exam_date">Exam_date:</label>
                    <input type="text" id="exam_date" name="exam_date" required>
                </div>
                <div class="form-group">
                    <label for="start_time">Start_time:</label>
                    <input type="text" id="start_time" name="start_time" required>
                </div>
                <div class="form-group">
                    <label for="end_time">End_time:</label>
                    <input type="text" id="end_time" name="end_time" required>
                </div>
                <div class="form-group">
                    <label for="exam_room">Exam_room:</label>
                    <input type="text" id="exam_room" name="exam_room" required>
                </div>
                <div class="form-group">
                    <label for="exam_type">Exam_type:</label>
                    <input type="text" id="exam_type" name="exam_type" required>
                </div>
                <div class="form-group">
                    <input type="submit" name="upload" value="Upload">
                </div>
            </form>
        </div>
    </body>
</html>
