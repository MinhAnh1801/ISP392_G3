package Controller;

import DAO.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/sendCaptcha")
public class SendCaptcha extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve email from the request
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        // Check if the email exists in the database
        DAO dao = new DAO();
        boolean emailExists = dao.checkEmailExists(email);

        if (emailExists) {
            // Email exists, proceed to generate and send the CAPTCHA

            // Store email in session for future use (like resend)
            session.setAttribute("resetEmail", email);

            // Generate a CAPTCHA code
            String captcha = generateCaptcha();
            session.setAttribute("captcha", captcha);  // Store CAPTCHA in session

            // Send the CAPTCHA via email
            try {
                sendEmail(email, "Your CAPTCHA Code to reset password", captcha);
                response.sendRedirect("verifycaptcha.jsp");
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to send email. Please try again.");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            }

        } else {
            // If the email does not exist, show an error message
            request.setAttribute("error", "This email is not registered.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request by calling the POST method
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");  // Retrieve email from session

        // Check if the email is available in the session
        if (email != null && !email.isEmpty()) {
            // Generate a new CAPTCHA code
            String captcha = generateCaptcha();
            session.setAttribute("captcha", captcha);  // Store the new CAPTCHA

            // Send the CAPTCHA via email
            try {
                sendEmail(email, "Your CAPTCHA Code to reset password", captcha);
                response.sendRedirect("verifycaptcha.jsp");
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to resend email. Please try again.");
                request.getRequestDispatcher("verifycaptcha.jsp").forward(request, response);
            }
        } else {
            // If the email is not in the session, redirect to forgot password page
            request.setAttribute("error", "Session expired or invalid email. Please enter your email again.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }

    // Method to generate a 6-digit CAPTCHA code
    private String generateCaptcha() {
        Random rand = new Random();
        int captcha = 1000 + rand.nextInt(9000);  // Generate 6 digit code
        return String.valueOf(captcha);
    }

    // Method to send an email (simplified)
    private void sendEmail(String toEmail, String subject, String captchaCode) throws MessagingException {
        final String fromEmail = "khucxuanhoa6a@gmail.com"; // Your Gmail address
        final String password = "qymnaiapswmxnoem"; // Your Gmail password or App Password

        // Set up email properties (using Gmail SMTP)
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your CAPTCHA Code");

            // HTML content for the email body
            String htmlMessage = "<html>"
                    + "<body style='font-family: Arial, sans-serif; font-size: 14px;'>"
                    + "<h2 style='color: #2e6c80;'>Your CAPTCHA Code</h2>"
                    + "<p>Hello,</p>"
                    + "<p>To complete your password reset process, please use the following CAPTCHA code:</p>"
                    + "<p style='font-size: 40px; font-weight: bold; color: #4CAF50;'>" + captchaCode + "</p>"
                    + "<p>This code will expire in 10 minutes. If you did not request this, please ignore this email.</p>"
                    + "<br>"
                    + "<p>Best regards,</p>"
                    + "<p>Uni Portal</p>"
                    + "<br>"
                    + "<img src='https://your-website.com/logo.png' alt='Website Logo' style='width:100px;'>"
                    + "</body>"
                    + "</html>";

            // Set the content of the email as HTML
            message.setContent(htmlMessage, "text/html; charset=utf-8");

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            // Suppress the exception for non-existent email
            System.err.println("Failed to deliver email to " + toEmail + ": " + e.getMessage());
            // Log this failure (could be to a file or a monitoring system)
            // But don't let the user know about it to keep the flow intact
        }
    }
}
