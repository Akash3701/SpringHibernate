package com.revshop.rev.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String USERNAME = "puchakayalayogananda22@gmail.com"; // Replace with your email
    private static final String PASSWORD = "jkuobiftuhsnztvk"; // Replace with your email password
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    // General method to send an email with a subject and body
    public void sendEmail(String recipientEmail, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Specific method for sending confirmation email after registration
    public void sendRegistrationEmail(String recipientEmail) {
        String subject = "Thank you for your Registration";
        String body = "Dear Retailer,\n\nThank you for registering with RevShop.\n\n"
                    + "We have received your registration request and will contact you soon.\n\n"
                    + "Best regards,\nRevShop Team";
        sendEmail(recipientEmail, subject, body);
    }

    // Method to send an order status update email
    public void sendOrderStatusUpdateEmail(String recipientEmail, Long orderId, String status) {
        String subject = "Order Status Update for Order #" + orderId;
        String body = "Dear Customer,\n\nYour order with ID " + orderId 
                    + " has been updated to the following status: " + status + ".\n\n"
                    + "Thank you for shopping with us!\n\nBest regards,\nRevShop Team";
        sendEmail(recipientEmail, subject, body);
    }
}
