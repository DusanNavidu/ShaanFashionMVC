package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse72.shaanfashion.dto.CustomerDTO;
import lk.ijse.gdse72.shaanfashion.dto.ItemDTO;
import lk.ijse.gdse72.shaanfashion.dto.OrderDTO;
import lk.ijse.gdse72.shaanfashion.dto.OrderDetailsDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.CartTM;
import lk.ijse.gdse72.shaanfashion.model.CustomerModel;
import lk.ijse.gdse72.shaanfashion.model.ItemModel;
import lk.ijse.gdse72.shaanfashion.model.OrderModel;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPay;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<CartTM, String> colItemId;

    @FXML
    private TableColumn<CartTM, String> colName;

    @FXML
    private TableColumn<CartTM, BigDecimal> colPrice;

    @FXML
    private TableColumn<CartTM, Integer> colQuantity;

    @FXML
    private TableColumn<CartTM, BigDecimal> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label orderDate;

    @FXML
    private TableView<CartTM> tblCart;

    private final OrderModel orderModel = new OrderModel();
    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();

    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();

        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }

    private void refreshPage() throws SQLException {
        lblOrderId.setText(orderModel.getNextOrderId());

        orderDate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadItemId();

        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblItemName.setText("");
        lblItemQty.setText("");
        lblItemPrice.setText("");
        txtAddToCartQty.setText("");
        lblCustomerName.setText("");

        cartTMS.clear();

        tblCart.refresh();
    }

    private void loadItemId() throws SQLException {
        ArrayList<String> itemIds = itemModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemId.setItems(observableList);
    }

    private void loadCustomerIds() throws SQLException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

    private void setCellValues() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));

        tblCart.setItems(cartTMS);
    }

    @FXML
    private JFXTextField txtAddToCartQty;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String selectedItemId = cmbItemId.getValue();

        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
            return;
        }

        String quantityPattern = "^[0-9]+$";

        boolean isValidQty = txtAddToCartQty.getText().matches(quantityPattern);

        if (!isValidQty) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            return;
        }

        String itemName = lblItemName.getText();
        int cartQty = Integer.parseInt(txtAddToCartQty.getText());
        int qtyOnHand = Integer.parseInt(lblItemQty.getText());

        if (qtyOnHand < cartQty) {
            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
            return;
        }

        txtAddToCartQty.setText("");

        double unitPrice = Double.parseDouble(lblItemPrice.getText());
        double total = unitPrice * cartQty;

        // Loop through each item in cart's observable list.
        for (CartTM cartTM : cartTMS) {

            // Check if the item is already in the cart
            if (cartTM.getItemId().equals(selectedItemId)) {
                // Update the existing CartTM object in the cart's observable list with the new quantity and total.
                int newQty = cartTM.getCartQuantity() + cartQty;
                cartTM.setCartQuantity(newQty); // Add the new quantity to the existing quantity in the cart.
                cartTM.setTotal(unitPrice * newQty); // Recalculate the total price based on the updated quantity

                // Refresh the table to display the updated information.
                tblCart.refresh();
                return; // Exit the method as the cart item has been updated.
            }
        }


        Button btn = new Button("Remove");

        CartTM newCartTM = new CartTM(
                selectedItemId,
                itemName,
                cartQty,
                unitPrice,
                total,
                btn
        );

        btn.setOnAction(actionEvent -> {

            cartTMS.remove(newCartTM);

            tblCart.refresh();
        });

        cartTMS.add(newCartTM);
    }

    @FXML
    void btnPayOnAction(ActionEvent event) throws SQLException {
        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
            return;
        }
        if (cmbCustomerId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select customer for place order..!").show();
            return;
        }

        String orderId = lblOrderId.getText();
        Date dateOfOrder = Date.valueOf(orderDate.getText());
        String customerId = cmbCustomerId.getValue();

        // List to hold order details
        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

        // Collect data for each item in the cart and add to order details array
        for (CartTM cartTM : cartTMS) {

            // Create order details for each cart item
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderId,
                    cartTM.getItemId(),
                    cartTM.getCartQuantity(),
                    cartTM.getUnitPrice()
            );

            // Add to order details array
            orderDetailsDTOS.add(orderDetailsDTO);
        }

        // Create an OrderDTO with relevant order data
        OrderDTO orderDTO = new OrderDTO(
                orderId,
                customerId,
                dateOfOrder,
                orderDetailsDTOS
        );

        boolean isSaved = orderModel.saveOrder(orderDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Order saved..!").show();

            // Reset the page after placing the order
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order fail..!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDTO customerDTO = customerModel.findById(selectedCustomerId);

        // If customer found (customerDTO not null)
        if (customerDTO != null) {

            // FIll customer related labels
            lblCustomerName.setText(customerDTO.getCustomerName());
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        ItemDTO itemDTO = itemModel.findById(selectedItemId);

        // If item found (itemDTO not null)
        if (itemDTO != null) {

            // FIll item related labels
            lblItemName.setText(itemDTO.getItemName());
            lblItemQty.setText(String.valueOf(itemDTO.getItemQuantityOnHand()));
            lblItemPrice.setText(String.valueOf(itemDTO.getPrice()));
        }
    }
}