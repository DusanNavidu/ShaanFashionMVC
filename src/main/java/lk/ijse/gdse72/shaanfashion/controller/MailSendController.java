package lk.ijse.gdse72.shaanfashion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSendController {

    @FXML
    private TextArea txtBody;

    @FXML
    private TextField txtSubject;

    @Setter
    private String customerEmail;

    @FXML
    public void sendUsingSendgridOnAction(ActionEvent actionEvent) {
        if (customerEmail == null) {
            new Alert(Alert.AlertType.WARNING, "Customer email is not set.").show();
            return;
        }

        // The sender's email address
        final String FROM = "replace-your-email";

        // Get the subject and body from the text fields
        String subject = txtSubject.getText();
        String body = txtBody.getText();

        // Check if subject or body is empty; show a warning if they are
        if (subject.isEmpty() || body.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Subject and body must not be empty!").show();
            return;
        }

        // Call the method to send an email via SendGrid
        sendEmailWithSendgrid(FROM, customerEmail, subject, body);
    }

    private void sendEmailWithSendgrid(String from, String to, String subject, String body) {
        String USER_NAME = "apikey";
        String PASSWORD = "navidu200210@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.sendgrid.net");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }

    @FXML
    public void sendUsingGmailOnAction(ActionEvent event) {
        if (customerEmail == null) {
            new Alert(Alert.AlertType.WARNING, "Customer email is not set.").show();
            return;
        }

        // The sender's email address
        final String FROM = "navidu200210@gmail.com";

        // Get the subject and body from the text fields
        String subject = txtSubject.getText();
        String body = txtBody.getText();

        // Check if subject or body is empty; show a warning if they are
        if (subject.isEmpty() || body.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Subject and body must not be empty!").show();
            return;
        }

        // Call the method to send an email via Gmail
        sendEmailWithGmail(FROM, customerEmail, subject, body);
    }

    private void sendEmailWithGmail(String from, String to, String subject, String messageBody) {
        String PASSWORD = "yjwj rhdp lkrj mygs";  // Replace with your Gmail app-specific password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }
}