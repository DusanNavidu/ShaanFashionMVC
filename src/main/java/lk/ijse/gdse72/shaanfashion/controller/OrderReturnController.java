package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse72.shaanfashion.dto.OrderReturnDTO;
import lk.ijse.gdse72.shaanfashion.model.OrderReturnModel;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderReturnController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<OrderReturnDTO , String > colItemName;

    @FXML
    private TableColumn<OrderReturnDTO , String> colOID;

    @FXML
    private TableColumn<OrderReturnDTO , BigDecimal> colPrice;

    @FXML
    private TableColumn<OrderReturnDTO , String> colRID;

    @FXML
    private TableColumn<OrderReturnDTO , String> colReason;

    @FXML
    private ComboBox<String > comROname;

    @FXML
    private Label lblRO;

    @FXML
    private Label lblROId;

    @FXML
    private TableView<OrderReturnDTO> tblOR;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtROReason;

    @FXML
    private JFXTextField txttPrice;

    OrderReturnModel orderReturnModel = new OrderReturnModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRID.setCellValueFactory(new PropertyValueFactory<>("returnOrderId"));
        colOID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();
    }

    private void refreshTable() {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

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

    @FXML
    void onClickTable(MouseEvent event) {

    }

}
