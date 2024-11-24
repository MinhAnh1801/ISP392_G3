<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verification Form</title>
        <style>
            body {
                font-family: 'Open Sans', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #fff;
            }
            .verification-container {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            .header {
                background-color: #d76325;
                padding: 10px 47px;
                display: flex;
                align-items: center;
            }
            .login-button {
                background-color: #fff;
                border-radius: 17px;
                padding: 14px;
                display: flex;
                align-items: center;
                gap: 13px;
                font-weight: 600;
                font-size: 18px;
                cursor: pointer;
            }
            .login-icon {
                width: 24px;
                height: 24px;
            }
            .verification-form {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                align-items: center;
                padding-top: 60px;
            }
            .verification-title {
                color: #153060;
                font-size: 32px;
                margin-bottom: 23px;
            }
            .verification-instruction {
                color: #828282;
                font-size: 14px;
                letter-spacing: 0.11px;
                margin-bottom: 23px;
                text-align: center;
            }
            .code-input-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 100%;
                max-width: 382px;
                padding-bottom: 20px;
            }
            .code-inputs {
                display: flex;
                gap: 17px;
                margin-bottom: 17px;
            }
            .code-input {
                width: 60px;
                height: 54px;
                border: 1px solid #9badca;
                border-radius: 3.554px;
                text-align: center;
                font-size: 24px;
            }
            .timer {
                color: #828282;
                font-size: 13px;
                letter-spacing: 0.11px;
            }
            .continue-button {
                width: 100%;
                padding: 13px 23px;
                background-color: #1e4178;
                color: #fff;
                border: none;
                border-radius: 3.554px;
                font-size: 11px;
                font-weight: 600;
                text-transform: uppercase;
                letter-spacing: 0.11px;
                cursor: pointer;
            }
            .resend-text {
                color: #828282;
                font-size: 10px;
                letter-spacing: 0.18px;
                margin-top: 23px;
            }
            .resend-link {
                font-size: 14px;
                color: #f2451c;
                cursor: pointer;
                background: none;
                border: none;
            }
            .visually-hidden {
                position: absolute;
                width: 1px;
                height: 1px;
                padding: 0;
                margin: -1px;
                overflow: hidden;
                clip: rect(0, 0, 0, 0);
                white-space: nowrap;
                border: 0;
            }
            @media (max-width: 991px) {
                .header {
                    padding: 10px 20px;
                }
                .verification-form {
                    padding: 40px 20px;
                }
            }
        </style>
        <script>
            // Function to automatically move focus to the next input field
            function moveToNext(current, nextFieldID) {
                if (current.value.length >= 1) {
                    document.getElementById(nextFieldID).focus();
                }
            }

            // Function to collect and submit the full CAPTCHA code
            function submitCaptcha() {
                const code1 = document.getElementById('code1').value;
                const code2 = document.getElementById('code2').value;
                const code3 = document.getElementById('code3').value;
                const code4 = document.getElementById('code4').value;
                const fullCaptcha = code1 + code2 + code3 + code4;

                // Set the hidden input field to the concatenated captcha
                document.getElementById('captcha').value = fullCaptcha;
                document.getElementById('captchaForm').submit();
            }
            // Countdown timer for the Resend button
            let countdown = 30; // 30 seconds countdown

            // Start the countdown after the page loads
            window.onload = function () {
                const resendButton = document.getElementById("resendButton");
                const timerDisplay = document.getElementById("timer");

                resendButton.disabled = true; // Disable resend button initially
                const interval = setInterval(function () {
                    countdown--;
                    timerDisplay.innerText = countdown;

                    if (countdown <= 0) {
                        clearInterval(interval);
                        timerDisplay.innerText = "";
                        resendButton.disabled = false; // Enable the resend button after countdown
                    }
                }, 1000); // Update every second
            }
        </script>
    </head>
    <body>
        <div class="verification-container">
            <header class="header">
                <a style="text-decoration: none;" href="forgotpassword.jsp" class="login-button">
                    <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/fb3f9c5b5375d8123a287241342aa1e68a8f98d7070998dedb1b5f1efe044541?placeholderIfAbsent=true&apiKey=3405f136f7584360bc9d450c0a79d7b9" alt="" class="login-icon">
                    <span style="color:black;text-decoration: none;">Return</span>
                </a>
            </header>
            <div class="verification-form">
                <h1 class="verification-title">Verification</h1>
                <p class="verification-instruction">Enter your 4 digits code that you received on your email.</p>
                <form class="code-input-container" action="verifyCaptcha" method="post">
                    <div class="code-inputs">
                        <label for="code1" class="visually-hidden">First digit</label>
                        <input type="text" id="code1" class="code-input" maxlength="1" pattern="\d" required oninput="moveToNext(this, 'code2')">
                        <label for="code2" class="visually-hidden">Second digit</label>
                        <input type="text" id="code2" class="code-input" maxlength="1" pattern="\d" required oninput="moveToNext(this, 'code3')">
                        <label for="code3" class="visually-hidden">Third digit</label>
                        <input type="text" id="code3" class="code-input" maxlength="1" pattern="\d" required oninput="moveToNext(this, 'code4')">
                        <label for="code4" class="visually-hidden">Fourth digit</label>
                        <input type="text" id="code4" class="code-input" maxlength="1" pattern="\d" required>
                    </div>
                    <!-- Hidden field to store full captcha -->
                    <input type="hidden" id="captcha" name="captcha">
                    <button type="submit" class="continue-button" onclick="submitCaptcha()">Continue</button>
                </form>
                <div style="display: flex;">
                    <p style="color: #828282;
                       font-size: 14px;">If you didn't receive a code!</p>
                    <button id="resendButton" onclick="location.href = 'sendCaptcha';" class="resend-link" tabindex="0" role="button">Resend</button>
                </div>
                <span class="timer">You can request to resend after <span style="color:red" id="timer" aria-live="polite">30</span><span class="timer"> seconds</span></span>               
                <% 
    String error = (String) request.getAttribute("error");
    if (error != null) {
                %>
                <p style="color: red;"><%= error %></p>
                <% } %>

            </div>
        </div>

    </body>
</html>