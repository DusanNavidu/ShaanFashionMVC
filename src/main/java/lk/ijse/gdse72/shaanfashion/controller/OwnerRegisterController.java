package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OwnerRegisterController {

    @FXML
    private AnchorPane OwnerRegisterPage;

    @FXML
    private Button btnBack;

    @FXML
    private JFXButton btnSignup;

    @FXML
    private JFXButton fxBtnSignin;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtEnterPassword;

    @FXML
    private JFXTextField txtFullName;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        OwnerRegisterPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerLoginForm.fxml"));
        OwnerRegisterPage.getChildren().add(load);
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        OwnerRegisterPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerLoginForm.fxml"));
        OwnerRegisterPage.getChildren().add(load);
    }

    @FXML
    void fxBtnSigninOnAction(ActionEvent event) throws IOException {
        OwnerRegisterPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerLoginForm.fxml"));
        OwnerRegisterPage.getChildren().add(load);
    }

}
