package lk.ijse.gdse72.shaanfashion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotController {

    @FXML
    private TextField txtPin;

    @FXML
    private Button btnOk;

    public static int verificationCode;

    @FXML
    void btnOkOnAction(ActionEvent event) throws IOException {
        if (Integer.parseInt(txtPin.getText()) == verificationCode) {
            // Load ValidationForm.fxml
            AnchorPane validationForm = FXMLLoader.load(getClass().getResource("/view/ValidationForm.fxml"));
            ((Stage) btnOk.getScene().getWindow()).setScene(new Scene(validationForm));
        } else {
            // Handle PIN mismatch error
        }
    }
}