<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${material != null ? 'Update Material' : 'Create Material'}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f7f7f7;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 600px;
                margin-top: 50px;
                background-color: #ffffff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            h2 {
                text-align: center;
                color: #007bff;
                margin-bottom: 20px;
            }
            .form-group label {
                font-weight: bold;
            }
            .btn-primary {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>${material != null ? 'Update Material' : 'Create New Material'}</h2>
            <form action="${material != null ? 'updateMaterial' : 'createMaterial'}" method="post" enctype="multipart/form-data">
                <input type="hidden" name="userId" value="${userId}">
                <input type="text" value="${material.getId()}" hidden="" name="material_id">
                <div class="form-group">
                    <label for="material_name">Material Name</label>
                    <input value="${material != null ? material.getMaterialName() : ''}" type="text" class="form-control" id="material_name" name="material_name" required>
                </div>

                <div class="form-group">
                    <label for="material_file">File</label>
                    <input value="${material != null ? material.getMaterial_file() : ''}" type="file" class="form-control-file" id="material_file" name="material_file" ${material != null ? '' : 'required'}>
                </div>

                <div class="form-group">
                    <label for="subject">Subject</label>
                    <select class="form-control" id="subject" name="subject_id" required>
                        <option value="" disabled ${material == null ? 'selected' : ''}>Select a subject</option>

                        <c:forEach var="subject" items="${listSubject}">
                            <option value="${subject.id}" ${material != null && subject.id == material.getSubjectId().getId() ? 'selected' : ''}>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="3">${material != null ? material.getDescription() : ''}</textarea>
                </div>

                <button type="submit" class="btn btn-primary">${material != null ? 'Update Material' : 'Upload Material'}</button>
            </form>

            <div class="text-center mt-3">
                <a href="home" class="btn btn-secondary">Back to Dashboard</a>
            </div>
        </div>
    </body>
</html>
