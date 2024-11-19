package lk.ijse.gdse72.shaanfashion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnChashier;

    @FXML
    private Button btnManager;

    @FXML
    private Button btnOwner;

    @FXML
    private AnchorPane mainPage;

    @FXML
    void btnChashierOnAction(ActionEvent event) throws IOException {
        mainPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CasheirDashboard.fxml"));
        mainPage.getChildren().add(load);
    }

    @FXML
    void btnManagerOnAction(ActionEvent event) {

    }

    @FXML
    void btnOwnerOnAction(ActionEvent event) throws IOException {
        mainPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OwnerLoginForm.fxml"));
        mainPage.getChildren().add(load);
    }

    @FXML
    void mainPageOnAction(MouseEvent event) {

    }

}
