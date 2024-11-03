<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    </style>
</head>
<body>
    <div class="upload-container">
        <h1>Upload Grade File</h1>
        <form action="uploadGrade" method="post" enctype="multipart/form-data">
            <label for="file">Select Excel file:</label>
            <input type="file" name="file" id="file" accept=".xls,.xlsx" required>
            <input type="submit" value="Upload">
        </form>
    </div>
</body>
</html>
