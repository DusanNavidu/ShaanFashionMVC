package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

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
    private AnchorPane dashBoardLoadingTablePage;

    @FXML
    private AnchorPane dashboardPage;

    @FXML
    private Label lblDateTime;

    @FXML
    private Button btnCal;

    @FXML
    private JFXButton btnItemAvailability;

    @FXML
    void NavigateTOItemPage(ActionEvent event) {
        navigateTo ("/view/ItemForm.fxml");
    }

    @FXML
    void NavigateToBrandPage(ActionEvent event) {

    }

    @FXML
    void NavigateToCategory(ActionEvent event) {
        navigateTo ("/view/CategoryForm.fxml");
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
        navigateTo ("/view/SupplierForm.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        navigateTo ("/view/CustomerForm.fxml");
    }


    @FXML
    void NavigateToItemAvailabilityPage(ActionEvent event) {

    }

    private void navigateTo(String fxmlPath) {
        try {
            dashBoardLoadingTablePage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(dashBoardLoadingTablePage.widthProperty());
            load.prefHeightProperty().bind(dashBoardLoadingTablePage.heightProperty());
            dashBoardLoadingTablePage.getChildren().add(load);
        }catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startClock();
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

    @FXML
    private void btnCalOnAction(ActionEvent event) {
        Stage stage = new Stage();
        Calculator calculatorApp = new Calculator();
        try {
            calculatorApp.start(stage);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error while opening Calculator!").show();
        }
    }

    @FXML
    void btnLogOutOnAcion(ActionEvent event) throws IOException {
        dashboardPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        dashboardPage.getChildren().add(load);
    }
}
