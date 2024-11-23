<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <title>Upload Grades</title>
        <style>
            body {
                background-color: #ffffff;
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
            }
            .upload-container {
                width: 50%;
                padding: 20px;
                border: 1px solid #dee2e6;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            h1 {
                color: #ff5722;
                text-align: center;
                margin-bottom: 20px;
            }
            label {
                font-size: 16px;
                margin-bottom: 10px;
                display: block;
            }
            input[type="file"] {
                margin: 10px 0;
                padding: 10px;
                font-size: 16px;
                width: 100%;
                border: 1px solid #dee2e6;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #ff9800;
                color: #ffffff;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                font-size: 16px;
                border-radius: 4px;
                margin-top: 20px;
                width: 100%;
            }
            input[type="submit"]:hover {
                background-color: #e68900;
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
        <div class="upload-container">
            <h1 class="text-3xl">Upload Grade File</h1>
            <form action="uploadGrade" method="post" enctype="multipart/form-data">
                <label for="file">Select Excel file:</label>
                <input type="file" name="file" id="file" accept=".xls,.xlsx" required>
                <input type="submit" value="Upload">
            </form>
        </div>
    </body>
</html>
