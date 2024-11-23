<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create New Schedule</title>
        <script src="https://cdn.tailwindcss.com"></script>

        <script>
            // Predefined time slots
            const timeSlots = [
                "07:30 - 09:50",
                "10:00 - 12:20",
                "12:50 - 15:10",
                "15:20 - 17:40"
            ];

            // Populate the time range dropdown on page load
            function populateTimeRangeDropdown() {
                const timeRangeSelect = document.getElementById("timeRange");
                timeSlots.forEach(slot => {
                    const option = document.createElement("option");
                    option.value = slot;
                    option.text = slot;
                    timeRangeSelect.appendChild(option);
                });
            }

            // Split the selected time range and assign to hidden inputs
            function handleTimeRangeChange() {
                const selectedRange = document.getElementById("timeRange").value;
                const [startTime, endTime] = selectedRange.split(" - ");
                document.getElementById("startTime").value = startTime;
                document.getElementById("endTime").value = endTime;
            }

            // Display alert if the operation was successful or failed
            function showAlert(message, isSuccess) {
                const userChoice = confirm(message + "\nDo you want to create more?");
                if (userChoice) {
                    window.location.href = "createSchedule"; // Redirect to home
                } else {
                    window.location.href = "viewSchedules";
                }
            }

            // Check if the form submission was successful or failed
            window.onload = function () {
                populateTimeRangeDropdown();
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.has('success')) {
                    showAlert("Schedule created successfully!", true);
                } else if (urlParams.get('error') === "invalidDueDate") {
                    alert("Unable to save new entry. Please check your inputs.");
                    window.location.href = "createSchedule";
                } else if (urlParams.has('error')) {
                    alert("Failed to create schedule. Please try again.");
                    window.location.href = "createSchedule";
                }

            };
        </script>
    </head>
    <body class="bg-gray-100">
        <c:if test="${sessionScope.role == 0}">

            <div class="container mx-auto py-8">
                <a class="w-[70px] h-[20px] px-4 py-2 bg-blue-700 align-middle rounded-lg text-white" href="viewSchedules">Return</a>
                <h2 class="text-2xl font-bold text-gray-800 mb-6 mt-2">Create New Schedule</h2>
                <form action="createSchedule" method="post" class="bg-white p-6 rounded shadow-md">
                    <!-- Select Subject -->
                    <div class="mb-4">
                        <label for="classId" class="block text-gray-700 font-bold mb-2">Select Subject</label>
                        <select id="subjectId" name="subjectid" class="w-full p-2 border rounded">
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.id}">
                                    ${subject.code}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- Select Class -->
                    <div class="mb-4">
                        <label for="classId" class="block text-gray-700 font-bold mb-2">Select Class</label>
                        <select id="classId" name="classId" class="w-full p-2 border rounded">
                            <c:forEach var="classInfo" items="${classList}">
                                <option value="${classInfo.class_id}">
                                    ${classInfo.class_name} (Capacity: ${classInfo.capacity})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Select Classroom -->
                    <div class="mb-4">
                        <label for="classroomId" class="block text-gray-700 font-bold mb-2">Select Classroom<p class="text-sm font-normal text-red-600"> *Capacity must be > Class capacity</p></label>
                        <select id="classroomId" name="classroomId" class="w-full p-2 border rounded">
                            <c:forEach var="classroom" items="${classroomList}">
                                <option value="${classroom.ID}">
                                    ${classroom.name} - ${classroom.location} (Capacity: ${classroom.capacity})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Select Day of the Week -->
                    <div class="mb-4">
                        <label for="dayOfWeek" class="block text-gray-700 font-bold mb-2">Day of the Week</label>
                        <select id="dayOfWeek" name="dayOfWeek" class="w-full p-2 border rounded" required>
                            <option value="Mon">Monday</option>
                            <option value="Tue">Tuesday</option>
                            <option value="Wed">Wednesday</option>
                            <option value="Thu">Thursday</option>
                            <option value="Fri">Friday</option>
                            <option value="Sat">Saturday</option>
                            <option value="Sun">Sunday</option>
                        </select>
                    </div>

                    <!-- Select Time Range -->
                    <div class="mb-4">
                        <label for="timeRange" class="block text-gray-700 font-bold mb-2">Select Time</label>
                        <select id="timeRange" class="w-[400px] p-2 border rounded" onchange="handleTimeRangeChange()" required>
                            <option value="" selected>Choose time</option>
                            <!-- Options populated by JavaScript -->
                        </select>
                    </div>

                    <!-- Hidden Inputs for Start and End Time -->
                    <input type="hidden" id="startTime" name="startTime">
                    <input type="hidden" id="endTime" name="endTime">
                    <div class="mb-4">
                        <label for="dueDate" class="block text-gray-700 font-bold mb-2">Due Date</label>
                        <input type="date" id="dueDate" name="dueDate" class="w-[300px] p-2 border rounded" required>
                    </div>

                    <div class="mb-4">
                        <label for="dueTime" class="block text-gray-700 font-bold mb-2">Due Time</label>
                        <input type="time" id="dueTime" name="dueTime" class="w-[300px] p-2 border rounded" required>
                    </div>
                    <!-- Submit Button -->
                    <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded">Create Schedule</button>
                </form>
            </div>
        </c:if>
    </body>
</html>
