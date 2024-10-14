package Controller;

import DAO.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/resetpwd")
public class ResetPassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the new password and the confirmed password from the request
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if the passwords match and meet the 8-character length requirement
        if (newPassword != null && newPassword.equals(confirmPassword) && newPassword.length() >= 8) {

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("resetEmail"); // Retrieve the email from the session

            if (email != null) {
                // Update the password in the database
                DAO dao = new DAO();
                boolean success = dao.resetPassword(email, newPassword);

                if (success) {
                    // Set a success flag in the request or session
                    request.setAttribute("success", true); // For request-scoped
                    request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                } else {
                    // Handle the case where the password could not be updated
                    request.setAttribute("error", "Failed to reset password. Please try again.");
                    request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                }
            } else {
                // If email is not found in session, redirect to forgot-password.jsp
                response.sendRedirect("forgotpassword.jsp");
            }

        } else {
            // If passwords do not match or do not meet the length requirement, show an error
            request.setAttribute("error", "Passwords must match and be at least 8 characters long.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
    }
}
