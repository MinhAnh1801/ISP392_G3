<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Available Schedules</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
    </head>
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
    <script>
        // Function to confirm registration and submit form
        function confirmRegistration(event) {
            event.preventDefault(); // Prevent default form submission

            // Ask for confirmation
            const confirmed = confirm("Are you sure you want to register for all schedules of the selected class?");
            if (confirmed) {
                // If confirmed, submit the form
                event.target.submit();
                // After form submission, display success message and redirect
                setTimeout(() => {
                    alert("Registered successfully! Redirecting to available subjects...");
                    window.location.href = "availableSubjects"; // Replace with the actual URL of available subjects
                }, 100); // Small delay to ensure form submission completes before redirect
            } else {
                // Alert cancellation
                alert("Registration cancelled.");
            }
        }
    </script>
    <body class="bg-gray-100">
        <div class="login-child">
        </div>
        <a href="/ISP392/availableSubjects" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a>
        <div class="container mx-auto py-8 mt-12">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Select a Class to Register</h2>

            <!-- Loop through each class and its schedules -->
            <c:forEach var="entry" items="${classSchedulesMap}">
                <c:set var="classId" value="${entry.key}" />
                <c:set var="schedules" value="${entry.value}" />

                <div class="bg-white p-4 rounded-lg shadow-md mb-6">
                    <!-- Class Header -->
                    <h3 class="text-xl font-semibold text-gray-700 mb-4">
                        Class: ${schedules[0].class_name}
                    </h3>
                    <!-- Table for each class's schedules -->
                    <table class="min-w-full bg-white border rounded">
                        <thead>
                            <tr>
                                <th class="border px-4 py-2">Subject</th>
                                <th class="border px-4 py-2">Day of the Week</th>
                                <th class="border px-4 py-2">Start Time</th>
                                <th class="border px-4 py-2">End Time</th>                       
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Loop through schedules for this class -->
                            <c:forEach var="schedule" items="${schedules}">
                                <tr>
                                    <td class="border px-4 py-2">${schedule.subject_code}</td>
                                    <td class="border px-4 py-2">${schedule.day_of_week}</td>
                                    <td class="border px-4 py-2">${schedule.start_time}</td>
                                    <td class="border px-4 py-2">${schedule.end_time}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <form action="registerSchedule?subject_id=${param.subject_id}" method="post" onsubmit="confirmRegistration(event)">
                        <!-- Register button for the class -->
                        <input type="hidden" name="classId" value="${classId}">
                        <button type="submit" class="mt-4 bg-blue-500 text-white py-2 px-4 rounded">Register This Class</button>
                    </form>
                </div>
            </c:forEach>
        </div>

    </body>
</html>
