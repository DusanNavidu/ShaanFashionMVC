package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnOrderReport;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colReturnOrderId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private ComboBox<?> comCustemerId;

    @FXML
    private ComboBox<?> comReturnOrderId;

    @FXML
    private Label lblCustoemerId;

    @FXML
    private Label lblNotify;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblReturnOrderId;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtTotalAmount;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
