package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse72.shaanfashion.dto.CustomerDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.CustomerTM;
import lk.ijse.gdse72.shaanfashion.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEMailSendToCustomer;

    @FXML
    private JFXButton btnGenerateReport;

    @FXML
    private JFXButton btnPreOrderCustomerReport;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblNotify;

    @FXML
    private TableColumn<CustomerTM , String> colCustomerAddress;

    @FXML
    private TableColumn<CustomerTM , String> colCustomerEmail;

    @FXML
    private TableColumn<CustomerTM , String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM , String> colCustomerName;

    @FXML
    private TableColumn<CustomerTM , String> colUserId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblUserId;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerEmail;

    @FXML
    private JFXTextField txtCustomerName;

    CustomerModel customerModel = new CustomerModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        String nextCustomerID = customerModel.getNextCustomerId();
        lblCustomerId.setText(nextCustomerID);

        lblUserId.setText("U001");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerEmail.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnGenerateReport.setDisable(true);
        btnEMailSendToCustomer.setDisable(true);
    }

    private void refreshTable() throws SQLException {
        ArrayList<CustomerDTO> customerDTOS = customerModel.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getUserId(),
                    customerDTO.getCustomerName(),
                    customerDTO.getCustomerAddress(),
                    customerDTO.getCustomerEmail()
            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);
    }

    @FXML
    void btnEMailSendToCustomerOnAction(ActionEvent event) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING, "Please select customer..!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MailSendForm.fxml"));
            Parent load = loader.load();

            MailSendController sendMailController = loader.getController();

            String email = selectedItem.getCustomerEmail();
            sendMailController.setCustomerEmail(email);

            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Send email");

            stage.initModality(Modality.APPLICATION_MODAL);

            Window underWindow = btnUpdate.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnPreOrderCustomerReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();
        String userId = lblUserId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerEmail = txtCustomerEmail.getText();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^^[a-zA-Z0-9\\s,.'-]{3,}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        //Validate each field using regex patterns
        boolean isValidName = customerName.matches(namePattern);
        boolean isValidAddress = customerAddress.matches(addressPattern);
        boolean isValidEmail = customerEmail.matches(emailPattern);

        txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: #7367F0;");
        txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: #7367F0;");

        // Highlight invalid fields in red

        if (!isValidName) {
            txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAddress) {
            txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidAddress && isValidEmail) {
            CustomerDTO customerDTO = new CustomerDTO(customerId, userId, customerName, customerAddress, customerEmail);

            boolean isSaved = customerModel.saveCustomer(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "customer save...!").show();
                lblNotify.setText("customer successfully saved!");
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
                lblNotify.setText("Failed to save customer.");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();
        String userId = lblUserId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerEmail = txtCustomerEmail.getText();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^^[a-zA-Z0-9\\s,.'-]{3,}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        //Validate each field using regex patterns
        boolean isValidName = customerName.matches(namePattern);
        boolean isValidAddress = customerAddress.matches(addressPattern);
        boolean isValidEmail = customerEmail.matches(emailPattern);

        txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: #7367F0;");
        txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: #7367F0;");

        // Highlight invalid fields in red

        if (!isValidName) {
            txtCustomerName.setStyle(txtCustomerName.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAddress) {
            txtCustomerAddress.setStyle(txtCustomerAddress.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            txtCustomerEmail.setStyle(txtCustomerEmail.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidAddress && isValidEmail) {
            CustomerDTO customerDTO = new CustomerDTO(customerId, userId, customerName, customerAddress, customerEmail);

            boolean isUpdated = customerModel.updateCustomer(customerDTO);

            if (isUpdated) {
                lblNotify.setText("Customer updated successfully!");
                refreshTable(); // Refresh the table to show updated data
            } else {
                lblNotify.setText("Customer update failed!");
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String customerId = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = customerModel.deleteCustomer(customerId);

            if (isDeleted) {
                lblNotify.setText("Customer deleted successfully!");
                refreshTable(); // Refresh the table to show updated data
                refreshPage();  // Refresh the entire page to clear fields
            } else {
                lblNotify.setText("Customer delete failed!");
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblCustomerId.setText(selectedItem.getCustomerId());
            lblUserId.setText(selectedItem.getUserId());
            txtCustomerName.setText(selectedItem.getCustomerName());
            txtCustomerAddress.setText(selectedItem.getCustomerAddress());
            txtCustomerEmail.setText(selectedItem.getCustomerEmail());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnGenerateReport.setDisable(false);
            btnEMailSendToCustomer.setDisable(false);
        }
    }
}