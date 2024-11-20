package lk.ijse.gdse72.shaanfashion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderController {

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemQuantity;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private ListView<?> lstItems;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private TableView<?> tblOrders;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQuantity;

    @FXML
    void addItem(ActionEvent event) {

    }

    @FXML
    void deleteOrder(ActionEvent event) {

    }

    @FXML
    void resetForm(ActionEvent event) {

    }

    @FXML
    void saveOrder(ActionEvent event) {

    }

}
