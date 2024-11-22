package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.shaanfashion.db.DBConnection;
import lk.ijse.gdse72.shaanfashion.util.PasswordUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class LoginController {

    @FXML
    private Button btnBack;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXButton btnSignup;

    @FXML
    private JFXButton btnForgot;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUsername;

    @FXML
    private AnchorPane ownerLoginPage;

    @FXML
    private JFXPasswordField txtFxPassword;

    @FXML
    private JFXTextField txtFxUsername;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        ownerLoginPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        ownerLoginPage.getChildren().add(load);
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        String username = txtFxUsername.getText();
        String password = PasswordUtil.hashPassword(txtFxPassword.getText());

        String storedUsername1 = "dusan";
        String storedUsername2 = "navidu";
        String storedHashedPassword1 = PasswordUtil.hashPassword("74747474");
        String storedHashedPassword2 = PasswordUtil.hashPassword("12345678");

        String usernamePattern = "^(?=.*[A-Z])(?=(.*\\\\d){2,})[A-Za-z0-9]{5,20}$";
        String passwordPattern = "\\\\d{8}";

        boolean isValidUsername1 = storedUsername1.matches(usernamePattern);
        boolean isValidUsername2 = storedUsername2.matches(usernamePattern);

        boolean isValidName1 = storedHashedPassword1.matches(passwordPattern);
        boolean isValidName2 = storedHashedPassword2.matches(passwordPattern);

        txtFxUsername.setStyle(txtFxUsername.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidUsername1) {
            txtFxUsername.setStyle(txtFxUsername.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidUsername2) {
            txtFxUsername.setStyle(txtFxUsername.getStyle() + ";-fx-border-color: red;");
        }

        txtFxPassword.setStyle(txtFxPassword.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidName1) {
            txtFxPassword.setStyle(txtFxPassword.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidName2) {
            txtFxPassword.setStyle(txtFxPassword.getStyle() + ";-fx-border-color: red;");
        }

        if ((username.equals(storedUsername1) && password.equals(storedHashedPassword1)) || (username.equals(storedUsername2) && password.equals(storedHashedPassword2))) {
            ownerLoginPage.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardForm.fxml"));
            AnchorPane load = loader.load();
            ownerLoginPage.getChildren().add(load);

            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ForgotForm.fxml"));
            //Parent loading = loader.load();

            //ForgotController forgotController = loader.getController();

        }else {
            new Alert(Alert.AlertType.ERROR, "Wrong !!").show();
            System.out.println("Wrong");
        }
    }

    @FXML
    void btnForgotOnAction(ActionEvent event) throws IOException {
        String u001Email = getEmailForUsername("U001");
        if (u001Email != null && !u001Email.isEmpty()) {
            Random random = new Random();
            int verificationCode = random.nextInt(100);
            ForgotController.verificationCode = verificationCode;
            sendEmail(u001Email, "Password Recovery", "Your verification code is: " + verificationCode);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username").show();
            return;
        }

        AnchorPane forgotForm = FXMLLoader.load(getClass().getResource("/view/ForgotForm.fxml"));
        ownerLoginPage.getChildren().setAll(forgotForm);
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        ownerLoginPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerRegisterForm.fxml"));
        ownerLoginPage.getChildren().add(load);
    }

    private void sendEmail(String to, String subject, String body) {
        final String username = "navidu200210@gmail.com";
        final String password = "yjwj rhdp lkrj mygs";

        // Setting up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject(subject);

            message.setText(body);

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getEmailForUsername(String username) {
        String email = null;
        String query = "SELECT userEmail FROM user WHERE username = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    email = resultSet.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }


    private boolean isValidUser(String username, String password) {
        String query = "SELECT password FROM user WHERE username = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String dbPassword = resultSet.getString("password");
                    return dbPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}