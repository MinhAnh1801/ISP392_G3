<%-- 
    Document   : editFeedback
    Created on : Nov 4, 2024, 11:33:34 AM
    Author     : Dell
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Feedback</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Edit Feedback</h2>
        <form action="updateFeedBack" method="post">
            <!-- Hidden field for feedback ID -->
            <input type="text" name="id" value="${feedback.feedbackId}" />

            <div class="form-group">
                <label for="rating">Rating:</label>
                <input type="text" class="form-control" name="rating" id="rating" value="${feedback.rating}" required>
            </div>

            <div class="form-group">
                <label for="studentId">Student ID:</label>
                <input type="number" class="form-control" name="studentId" id="studentId" value="${feedback.studentId}" required>
            </div>

            <div class="form-group">
                <label for="lecturerId">Lecturer ID:</label>
                <input type="number" class="form-control" name="lecturerId" id="lecturerId" value="${feedback.lecturerId}" required>
            </div>

            <div class="form-group">
                <label for="feedbackQuestionId">Feedback Question ID:</label>
                <input type="number" class="form-control" name="feedbackQuestionId" id="feedbackQuestionId" value="${feedback.feedbackQuestionId}" required>
            </div>

            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" name="status" id="status" ${feedback.status ? "checked" : ""}>
                <label class="form-check-label" for="status">Status</label>
            </div>

            <button type="submit" class="btn btn-primary">Update Feedback</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
