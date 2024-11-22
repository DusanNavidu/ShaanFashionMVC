package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.shaanfashion.dto.tm.UserDTO;
import lk.ijse.gdse72.shaanfashion.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OwnerRegisterController implements Initializable {

    @FXML
    private AnchorPane OwnerRegisterPage;


    @FXML
    private Label lblUserId;

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

    UserModel userModel = new UserModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        String nextUserId = userModel.getNextUserId();
        lblUserId.setText(nextUserId);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        OwnerRegisterPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        OwnerRegisterPage.getChildren().add(load);
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException, SQLException {

        String userId = lblUserId.getText();
        String userFullName = txtFullName.getText();
        String username = txtUserName.getText();
        String userEmail = txtEmail.getText();
        String password = txtConfirmPassword.getText();

        String usernamePattern = "^[a-zA-Z0-9]{5,20}$";
        String passwordPattern = "^[0-9]{4,10}$";
        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean isValidName = userFullName.matches(namePattern);
        boolean isValidUsername = username.matches(usernamePattern);
        boolean isValidEmail = userEmail.matches(emailPattern);
        boolean isValidPassword = password.matches(passwordPattern);

        txtFullName.setStyle(txtFullName.getStyle() + ";-fx-border-color: #7367F0;");
        txtUserName.setStyle(txtUserName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtConfirmPassword.setStyle(txtConfirmPassword.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidName) {
            txtFullName.setStyle(txtFullName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidUsername) {
            txtUserName.setStyle(txtUserName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPassword) {
            txtConfirmPassword.setStyle(txtConfirmPassword.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidUsername && isValidEmail && isValidPassword) {
            UserDTO userDTO = new UserDTO(userId, userFullName, username, userEmail, password);

            boolean isSaved = userModel.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save user...!\nTry again please...😊").show();
            }
        }
    }

    @FXML
    void fxBtnSigninOnAction(ActionEvent event) throws IOException, SQLException {
        OwnerRegisterPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        OwnerRegisterPage.getChildren().add(load);
    }
}

