package lk.ijse.gdse72.shaanfashion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import lk.ijse.gdse72.shaanfashion.db.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ValidationController {

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Button btnOk;

    @FXML
    void btnOkOnAction(ActionEvent event) {

        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            updatePasswordInDatabase("U001", txtConfirmPassword.getText());
            new Alert(Alert.AlertType.INFORMATION, "Password updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
        }
    }

    private void updatePasswordInDatabase(String username, String newPassword) {
        String query = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}