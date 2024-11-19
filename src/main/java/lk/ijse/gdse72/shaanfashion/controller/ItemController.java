package lk.ijse.gdse72.shaanfashion.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse72.shaanfashion.dto.ItemDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.ItemTM;
import lk.ijse.gdse72.shaanfashion.model.BrandModel;
import lk.ijse.gdse72.shaanfashion.model.CategoryModel;
import lk.ijse.gdse72.shaanfashion.model.ItemModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnItemReport;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<ItemTM , String> colBatchNumber;

    @FXML
    private TableColumn<ItemTM , String> colBrandId;

    @FXML
    private TableColumn<ItemTM , String> colCategoryId;

    @FXML
    private TableColumn<ItemTM , String> colDescription;

    @FXML
    private TableColumn<ItemTM , String> colItemId;

    @FXML
    private TableColumn<ItemTM , String> colItemName;

    @FXML
    private TableColumn<ItemTM , String> colItemQuantity;

    @FXML
    private TableColumn<ItemTM , BigDecimal> colPrice;

    @FXML
    private TableColumn<ItemTM , BigDecimal> colProfit;

    @FXML
    private ComboBox<String> comBrandId;

    @FXML
    private ComboBox<String> comCategoryId;

    @FXML
    private Label lblBatchNumber;

    @FXML
    private Label lblBrandId;

    @FXML
    private Label lblCategoryId;

    @FXML
    private Label lblItemId;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtItemQuantity;

    @FXML
    private JFXTextField txtPeice;

    @FXML
    private JFXTextField txtProfit;

    private final CategoryModel categoryModel = new CategoryModel();
    private final BrandModel brandModel = new BrandModel();

    ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colBrandId.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        colItemQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantityOnHand"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        comCategoryId.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    lblCategoryId.setText(categoryModel.getCategoryNameById(newValue));
                } else {
                    lblCategoryId.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        comBrandId.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    lblBrandId.setText(brandModel.getBrandNameById(newValue));
                } else {
                    lblBrandId.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        String nextItemID = itemModel.getNextItemId();
        lblItemId.setText(nextItemID);

        txtItemName.setText("");

        loadCategoryIds();
        loadBrandIds();

        comCategoryId.getSelectionModel().clearSelection();
        comBrandId.getSelectionModel().clearSelection();

        txtItemQuantity.setText("");

        String nextBatchNumber = itemModel.getNexBatchNumber();
        lblBatchNumber.setText(nextBatchNumber);

        txtDescription.setText("");
        txtPeice.setText("");
        txtProfit.setText("");

        btnResert.setDisable(false);
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadCategoryIds() throws SQLException {
        ArrayList<String> categoryIds = categoryModel.getAllCategoryIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(categoryIds);
        comCategoryId.setItems(observableList);
    }

    private void loadBrandIds() throws SQLException {
        ArrayList<String> categoryIds = brandModel.getAllBrandIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(categoryIds);
        comBrandId.setItems(observableList);
    }


    private void refreshTable() throws SQLException {
        ArrayList<ItemDTO> items = itemModel.getAllItems();
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

        for (ItemDTO itemDTO : items) {
            ItemTM itemTM = new ItemTM(
                    itemDTO.getItemId(),
                    itemDTO.getItemName(),
                    itemDTO.getCategoryId(),
                    itemDTO.getBrandId(),
                    itemDTO.getItemQuantityOnHand(),
                    itemDTO.getBatchNumber(),
                    itemDTO.getDescription(),
                    itemDTO.getPrice(),
                    itemDTO.getProfit()
            );
            itemTMS.add(itemTM);
        }

        tblItem.setItems(itemTMS);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    private void resetStyles() {
        txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: #7367F0;");
        txtItemQuantity.setStyle(txtItemQuantity.getStyle() + "; -fx-border-color: #7367F0;");
        txtPeice.setStyle(txtPeice.getStyle() + "; -fx-border-color: #7367F0;");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String itemId = lblItemId.getText();
        String itemName = txtItemName.getText();
        String categoryId = comCategoryId.getSelectionModel().getSelectedItem();
        String brandId = comBrandId.getSelectionModel().getSelectedItem();
        String itemQuantityOnHand = txtItemQuantity.getText();
        String batchNumber = lblBatchNumber.getText();
        String description = txtDescription.getText();
        String priceStr = txtPeice.getText();
        String profitStr = txtProfit.getText();

        String quantityPattern = "^[0-9]+$";
        String pricePattern = "^\\d+(\\.\\d{1,2})?$";
        String namePattern = "^[A-Za-z ]+$";

        boolean isValidName = itemName != null && itemName.matches(namePattern);
        boolean isValidQuantity = itemQuantityOnHand.matches(quantityPattern);
        boolean isValidPrice = priceStr.matches(pricePattern);

        System.out.println(isValidQuantity + " / " + itemQuantityOnHand);

        resetStyles();

        if (!isValidName) {
            txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtItemQuantity.setStyle(txtItemQuantity.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtPeice.setStyle(txtPeice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(itemQuantityOnHand);
            BigDecimal price = new BigDecimal(priceStr);
            BigDecimal profit = new BigDecimal(profitStr);

            ItemDTO itemDTO = new ItemDTO(itemId, itemName, categoryId, brandId, itemQuantityOnHand, batchNumber, description, price, profit);

            try {
                boolean isSaved = itemModel.saveItem(itemDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!").show();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save item!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to save item due to an SQL error!").show();
            }
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String itemName = txtItemName.getText();
        String categoryId = comCategoryId.getSelectionModel().getSelectedItem();
        String brandId = comBrandId.getSelectionModel().getSelectedItem();
        String itemQuantityOnHand = txtItemQuantity.getText();
        String batchNumber = lblBatchNumber.getText();
        String description = txtDescription.getText();
        String priceStr = txtPeice.getText();
        String profitStr = txtProfit.getText();

        String quantityPattern = "^[0-9]+$";
        String pricePattern = "^\\d+(\\.\\d{1,2})?$";
        String namePattern = "^[A-Za-z ]+$";

        boolean isValidName = itemName != null && itemName.matches(namePattern);
        boolean isValidQuantity = itemQuantityOnHand.matches(quantityPattern);
        boolean isValidPrice = priceStr.matches(pricePattern);

        resetStyles();

        if (!isValidName) {
            txtItemName.setStyle(txtItemName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtItemQuantity.setStyle(txtItemQuantity.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtPeice.setStyle(txtPeice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(itemQuantityOnHand);
            BigDecimal price = new BigDecimal(priceStr);
            BigDecimal profit = new BigDecimal(profitStr);

            ItemDTO itemDTO = new ItemDTO(itemId, itemName, categoryId, brandId, itemQuantityOnHand, batchNumber, description, price, profit);

            try {
                boolean isUpdated = itemModel.updateItem(itemDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
                    refreshPage();

                    btnUpdate.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update item!").show();
                    btnUpdate.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                }
            } catch (SQLException e) {
                e.printStackTrace();

                new Alert(Alert.AlertType.ERROR, "Failed to update item due to an SQL error!").show();
                btnUpdate.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

}
