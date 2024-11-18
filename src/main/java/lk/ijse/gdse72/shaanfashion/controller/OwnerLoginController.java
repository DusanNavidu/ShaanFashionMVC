package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.shaanfashion.util.PasswordUtil;

import java.io.IOException;

public class OwnerLoginController {

    @FXML
    private Button btnBack;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXButton btnSignup;

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

        String storedUsername = "dusan";
        String storedHashedPassword = PasswordUtil.hashPassword("7474");

        if(username.equals(storedUsername) && password.equals(storedHashedPassword)) {
            ownerLoginPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"));
            ownerLoginPage.getChildren().add(load);
        } else {
            new Alert(Alert.AlertType.ERROR, "Wrong !!").show();
            System.out.println("Wrong");
        }
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        ownerLoginPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerRegisterForm.fxml"));
        ownerLoginPage.getChildren().add(load);
    }

}
