<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Assign Lecturer to Subject</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
        <script>
            function fetchGroupedSchedules() {
                const subjectId = document.getElementById("subjectId").value;
                if (subjectId) {
                    fetch(`assignLecturer?action=fetchGroupedSchedules&subjectId=` + subjectId)
                            .then(response => response.json())
                            .then(data => {
                                let groupedSchedulesHTML = '';
                                for (const [class_name, schedules] of Object.entries(data)) {
                                    if (Array.isArray(schedules)) {  // Ensure schedules is an array
                                        groupedSchedulesHTML += `<h3 class="text-lg font-semibold mt-6">Class: ` + class_name + `</h3>`;
                                        groupedSchedulesHTML += `<table class="w-full text-left table-auto mt-2">
                                <thead>
                                    <tr class="bg-blue-500 text-white">
                                        <th class="px-4 py-2">Day</th>
                                        <th class="px-4 py-2">Start Time</th>
                                        <th class="px-4 py-2">End Time</th>
                                        <th class="px-4 py-2 text-center">
                                                <input type="checkbox" id="selectAll_` + class_name + `" onclick="toggleClassCheckboxes('` + class_name + `')">
                                                Select
                                            </th>
                                    </tr>
                                </thead>
                                <tbody>`;

                                        schedules.forEach(schedule => {
                                            groupedSchedulesHTML += `<tr>
                                    <td class="px-4 py-2">` + schedule.day_of_week + `</td>
                                    <td class="px-4 py-2">` + schedule.start_time + `</td>
                                    <td class="px-4 py-2">` + schedule.end_time + `</td>
                                    <td class="px-4 py-2 text-center">
                                        <input type="checkbox"  name="scheduleIds" value="` + schedule.id + `" class ="schedule-checkbox-` + class_name + `">
                                    </td>
                                </tr>`;
                                        });

                                        groupedSchedulesHTML += `</tbody></table>`;
                                    } else {
                                        console.error(`Expected an array but received:`, schedules);
                                    }
                                }
                                document.getElementById("groupedSchedulesContainer").innerHTML = groupedSchedulesHTML;
                            })
                            .catch(error => {
                                console.error("Error fetching grouped schedules:", error);
                            });
                }
            }
            // JavaScript function to toggle all checkboxes within a class
            function toggleClassCheckboxes(class_name) {
                const selectAllCheckbox = document.getElementById(`selectAll_` + class_name + ``);
                const checkboxes = document.querySelectorAll(`.schedule-checkbox-` + class_name + ``);

                checkboxes.forEach(checkbox => {
                    checkbox.checked = selectAllCheckbox.checked;
                });
            }
            function showAlert(message) {
                if (message) {
                    alert(message);
                    window.location.href = "lecturers"; // Replace with the actual URL of available subjects
                    return;
                }
            }
            function showAlertError(error) {
                if (error) {
                    alert(error);
                    window.history.back();
                    return;
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
    <body class="bg-gray-100">
        <c:if test="${not empty msg}">
            <script>
                // Call showAlert with the error message
                showAlert("${msg}");
            </script>
        </c:if>
        <c:if test="${not empty error}">
            <script>
                // Call showAlert with the error message
                showAlertError("${error}");
            </script>
        </c:if>
        <div class="login-child">
        </div>
        <a href="/ISP392/home" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mx-auto py-8 mt-12">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Assign Lecturer - ID: ${lecturerId}</h2>

            <!-- Assignment Form -->
            <form action="finalizeAssignment" method="post" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <input type="hidden" name="lecturerId" id="lecturerId" value="${lecturerId}" />

                <!-- Subject Selection -->
                <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="subjectId">Select Subject</label>
                    <select name="subjectId" id="subjectId" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" onchange="fetchGroupedSchedules()" required>
                        <option value="">Choose Subject</option>
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.id}">${subject.code}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Container for Grouped Schedules by Class -->
                <div id="groupedSchedulesContainer" class="mt-6">
                    <!-- Grouped schedules will appear here after fetching -->
                </div>

                <!-- Submit Button -->
                <div class="flex items-center justify-between mt-6">
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                        Assign Lecturer
                    </button>
                </div>
            </form>
        </div>

    </body>
</html>