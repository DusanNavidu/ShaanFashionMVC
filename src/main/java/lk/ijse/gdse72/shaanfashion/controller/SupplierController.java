package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse72.shaanfashion.dto.SupplierDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.SupplierTM;
import lk.ijse.gdse72.shaanfashion.model.SupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnGenerateReport;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblNotify;

    @FXML
    private TableColumn<SupplierTM , String> colSupplierAddress;

    @FXML
    private TableColumn<SupplierTM , String> colSupplierContactNo;

    @FXML
    private TableColumn<SupplierTM , String> colSupplierId;

    @FXML
    private TableColumn<SupplierTM , String> colSupplierName;

    @FXML
    private TableColumn<SupplierTM , String> colSupplyItem;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierContactNo;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplyItem;

    SupplierModel supplierModel = new SupplierModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupplyItem.setCellValueFactory(new PropertyValueFactory<>("supplyItem"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupplierContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        String nextCustomerID = supplierModel.getNextSupplierId();
        lblSupplierId.setText(nextCustomerID);

        txtSupplierName.setText("");
        txtSupplyItem.setText("");
        txtSupplierAddress.setText("");
        txtSupplierContactNo.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnGenerateReport.setDisable(true);
    }

    private void refreshTable() throws SQLException {
        ArrayList<SupplierDTO> supplierDTOS = supplierModel.getAllSuppliers();
        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

        for (SupplierDTO supplierDTO : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getSupplierName(),
                    supplierDTO.getSupplyItem(),
                    supplierDTO.getSupplierAddress(),
                    supplierDTO.getContactNo()
            );
            supplierTMS.add(supplierTM);
        }
        tblSupplier.setItems(supplierTMS);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String supplierId = lblSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierItem = txtSupplyItem.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierContactNo = txtSupplierContactNo.getText();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^^[a-zA-Z0-9\\s,.'-]{3,}$";
        //String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = supplierName.matches(namePattern);
        boolean isValidAddress = supplierAddress.matches(addressPattern);
        boolean isValidPhone = supplierContactNo.matches(phonePattern);

        // Reset input field styles
        txtSupplierName.setStyle(txtSupplierName.getStyle() + ";-fx-border-color: #7367F0;");
        txtSupplyItem.setStyle(txtSupplyItem.getStyle() + ";-fx-border-color: #7367F0;");
        txtSupplierAddress.setStyle(txtSupplierAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtSupplierContactNo.setStyle(txtSupplierContactNo.getStyle() + ";-fx-border-color: #7367F0;");

        // Highlight invalid fields in red
        if (!isValidName) {
            txtSupplierName.setStyle(txtSupplierName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAddress) {
            txtSupplierAddress.setStyle(txtSupplierAddress.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            txtSupplierContactNo.setStyle(txtSupplierContactNo.getStyle() + ";-fx-border-color: red;");
        }


        // Save customer if all fields are valid
        if (isValidName && isValidAddress && isValidPhone) {
            SupplierDTO supplierDTO = new SupplierDTO(supplierId, supplierName, supplierItem, supplierAddress, supplierContactNo);

            boolean isSaved = supplierModel.saveSupplier(supplierDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "supplier save...!").show();
                lblNotify.setText("supplier successfully saved!");
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save supplier...!").show();
                lblNotify.setText("Failed to save supplier.");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

}
