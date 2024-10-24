<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Avenir:wght@400&display=swap" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap" />
    </head>
    <body>
        <style>
            .back {
                font-weight: 600;
                font-family: Inter;
                font-size: 18px;
            }
            .forgot-password-container {
                background-color: #fff;
                display: flex;
                flex-direction: column;
                overflow: hidden;
            }


            .login-button {
                border-radius: 17px;
                background-color: #fff;
                display: flex;
                gap: 13px;
                padding: 14px;
            }

            .login-icon {
                aspect-ratio: 1;
                object-fit: contain;
                object-position: center;
                width: 24px;
            }

            .password-reset-form {
                border-radius: 8px;
                background-color: #fff;
                align-self: center;
                display: flex;
                margin-top: 91px;
                flex-direction: column;
                align-items: center;
                font-family: Open Sans, sans-serif;
                justify-content: start;
                padding: 65px 49px;
            }

            .form-title {
                color: #153060;
                font-size: 37px;
                font-weight: 400;
                line-height: 1;
            }

            .form-description {
                color: #828282;
                font-size: 13px;
                font-weight: 400;
                line-height: 20px;
                letter-spacing: 0.12px;
                margin-top: 26px;
            }

            .form-content {
                display: flex;
                margin-top: 26px;
                width: 100%;
                max-width: 437px;
                flex-direction: column;
            }

            .email-input-container {
                border-radius: 2px;
                display: flex;
                width: 100%;
                flex-direction: column;
                font-weight: 400;
            }

            .email-label {
                color: #153060;
                font-size: 11px;
                line-height: 1;
                letter-spacing: 0.2px;
            }

            .email-input {
                align-self: stretch;
                flex: 1;
                border-radius: 4.068px;
                border: 0.814px solid #9badca;
                width: 100%;
                font-size: 13px;
                color: #1e4178;
                letter-spacing: 0.12px;
                padding: 13px 9px;
            }

            .submit-button {
                align-self: stretch;
                width: 100%;
                border-radius: 4.068px;
                background-color: #1e4178;
                margin-top: 20px;
                font-size: 13px;
                color: #fff;
                font-weight: 600;
                text-align: center;
                text-transform: uppercase;
                letter-spacing: 0.12px;
                padding: 15px 26px;
                border: none;
                cursor: pointer;
            }

            @media (max-width: 991px) {
                .forgot-password-container {
                    padding-bottom: 100px;
                }

                .header {
                    max-width: 100%;
                    padding: 0 20px;
                }

                .password-reset-form {
                    max-width: 100%;
                    margin-top: 40px;
                    padding: 0 20px;
                }

                .form-title,
                .form-description,
                .form-content,
                .email-input-container,
                .email-input,
                .submit-button {
                    max-width: 100%;
                }
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
            .login-child {
                position: absolute;
                top: 0px;
                left: 0px;
                background-color: #d76325;
                width: 1920px;
                height: 72px;
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
        <div class="login-child">
        </div>
        <a href="login.jsp" class="login-item bg-white flex hover:bg-slate-200 duration-200">
            <svg class="ml-[14px] mt-[14px]" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M4 12H20" stroke="black" stroke-width="1.5" stroke-miterlimit="10" stroke-linecap="round"/>
            <path d="M11.0325 4.33936L4.45961 10.9122C4.31606 11.0546 4.20206 11.224 4.12432 11.4108C4.04646 11.5975 4.00641 11.7977 4.00641 12C4.00641 12.2023 4.04646 12.4025 4.12432 12.5892C4.20206 12.776 4.31606 12.9454 4.45961 13.0877L11.0325 19.6606" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            <div class="back m-auto">Return</div>
        </a> 
        <main class="password-reset-form">
            <h1 class="form-title">Forgot password</h1>
            <p class="form-description">
                Enter your email for the verification process, we will send 4 digits code to your email.
            </p>
            <form class="form-content" action="sendCaptcha" method="post">
                <div class="email-input-container">
                    <label for="email-input" class="email-label mb-1">E-mail</label>
                    <input
                        type="email"
                        id="email"
                        name ="email"
                        class="email-input"
                        aria-label="Enter your email"
                        required
                        />
                </div>
                <button type="submit" class="submit-button">Continue</button>
            </form>
        </main>
        <% 
    String error = (String) request.getAttribute("error");
    if (error != null) {
        %>
        <p id="errormessage" style="color: red;text-align: center;"><%= error %></p>
        <% } %>
        <script>
            window.onload = function () {
                if (performance.navigation.type === 1) {  // If it's a page reload
                    document.getElementById("errormessage").innerHTML = "";  // Clear the message
                }
            }
        </script>
    </body>
</html>