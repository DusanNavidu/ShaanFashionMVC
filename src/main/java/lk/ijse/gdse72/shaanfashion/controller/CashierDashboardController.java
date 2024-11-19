package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CashierDashboardController implements Initializable {

    @FXML
    private JFXButton btnBrand;

    @FXML
    private JFXButton btnCategory;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnItem;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnOrderReturn;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnItemAvailability;

    @FXML
    private AnchorPane cashierDashboardPage;

    @FXML
    private AnchorPane dashBoardCashierLoadingTablePage;

    @FXML
    private Label lblDateTime;

    @FXML
    void NavigateTOItemPage(ActionEvent event) {

    }

    @FXML
    void NavigateToBrandPage(ActionEvent event) {

    }

    @FXML
    void NavigateToCategory(ActionEvent event) {

    }

    @FXML
    void NavigateToOrderPage(ActionEvent event) {
        navigateTo("/view/OrderForm.fxml");
    }

    @FXML
    void NavigateToOrderReturnPage(ActionEvent event) {

    }

    @FXML
    void NavigateToSupplierPage(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        navigateTo("/view/CustomerForm.fxml");
    }

    @FXML
    void NavigateToItemAvailabilityPage(ActionEvent event) {

    }

    private void navigateTo(String fxmlPath) {
        try {
            dashBoardCashierLoadingTablePage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(dashBoardCashierLoadingTablePage.widthProperty());
            load.prefHeightProperty().bind(dashBoardCashierLoadingTablePage.heightProperty());
            dashBoardCashierLoadingTablePage.getChildren().add(load);
        }catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startClock();
        disableButtons();
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateDateTime();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm:ss");
        lblDateTime.setText(now.format(formatter));
    }

    private void disableButtons() {
        btnBrand.setDisable(true);
        btnCategory.setDisable(true);
        btnItem.setDisable(true);
        btnOrder.setDisable(true);
        btnOrderReturn.setDisable(true);
        btnSupplier.setDisable(true);

        btnLogOut.setDisable(false);
        btnCustomer.setDisable(false);
        btnItemAvailability.setDisable(false);
    }

    @FXML
    void btnLogOutOnAcion(ActionEvent event) throws IOException {
        cashierDashboardPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        cashierDashboardPage.getChildren().add(load);
    }
}