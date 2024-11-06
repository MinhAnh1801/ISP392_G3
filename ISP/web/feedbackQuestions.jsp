<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback Questions</title>
        <!-- Kết nối đến Bootstrap CSS từ CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Định nghĩa màu cam cho tiêu đề và nút */
            body {
                background-color: #f8f9fa; /* Màu nền nhạt */
            }
            h1 {
                color: #ff4500; /* Màu cam sáng hơn cho tiêu đề */
            }
            .btn-custom {
                background-color: #ff4500; /* Màu cam sáng hơn cho nút */
                color: white; /* Màu chữ trắng */
            }
            .btn-custom:hover {
                background-color: #e63946; /* Màu cam đậm hơn khi hover */
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Feedback Questions</h1>
        <form action="<c:choose>
                          <c:when test="${param.action == 'update'}">updateFeedback</c:when>
                          <c:otherwise>addFeedback</c:otherwise>
                      </c:choose>" method="post">                <table class="table table-bordered mt-4">
                    <thead class="thead-light">
                        <tr>
                            <th>STT</th> <!-- Đổi tiêu đề thành STT -->
                            <th>Name</th>
                            <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="question" items="${feedbackQuestions}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td> <!-- Hiển thị STT -->
                                <td>${question.name}</td>
                                <td>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="feedback_${question.id}" value="Good" id="good_${question.id}" />
                                        <label class="form-check-label" for="good_${question.id}">Good</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="feedback_${question.id}" value="Average" id="average_${question.id}" />
                                        <label class="form-check-label" for="average_${question.id}">Average</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="feedback_${question.id}" value="Bad" id="bad_${question.id}" />
                                        <label class="form-check-label" for="bad_${question.id}">Bad</label>
                                    </div>
                                </td>
                            </tr>
                        <input type="hidden" name="lecturerId" value="${param.lecturerId}" />
                        <input type="hidden" name="feedbackQuestionIds" value="${question.id}" />
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <button type="submit" class="btn btn-custom">Submit Feedback</button>
            </form>
        </div>

        <!-- Kết nối đến Bootstrap JS và jQuery từ CDN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
