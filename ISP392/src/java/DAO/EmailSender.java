import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String toEmail, String subject, String messageContent) throws MessagingException {
        // Sender's email credentials
        final String fromEmail = "khucxuanhoa6a@gmail.com"; // Your Gmail address
        final String password = "qymnaiapswmxnoem"; // Your Gmail password or App Password

        // Set up email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
        properties.put("mail.smtp.port", "587"); // TLS port
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        // Create a session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Create the email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail)); // Set the sender
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Set recipient
        message.setSubject(subject); // Set the subject
        message.setText(messageContent); // Set the message content

        // Send the email
        Transport.send(message);
        System.out.println("Email sent successfully to " + toEmail);
    }

    public static void main(String[] args) {
        try {
            // Example usage
            sendEmail("khucxuanhoa111@gmail.com", "Test Subject", "This is a test email.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
