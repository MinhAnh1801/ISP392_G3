package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/verifyCaptcha")
public class VerifyCaptcha extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the entered CAPTCHA from the form
        String enteredCaptcha = request.getParameter("captcha");

        // Retrieve the stored CAPTCHA from the session
        HttpSession session = request.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");

        if (sessionCaptcha != null && sessionCaptcha.equals(enteredCaptcha)) {
            // CAPTCHA is correct, redirect to reset-password.jsp
            response.sendRedirect("resetpassword.jsp");
        } else {
            // CAPTCHA is incorrect, set an error message and reload the verification page
            request.setAttribute("error", "Invalid CAPTCHA. Please try again.");
            request.getRequestDispatcher("verifycaptcha.jsp").forward(request, response);
        }
    }
}
