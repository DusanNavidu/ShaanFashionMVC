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
import lk.ijse.gdse72.shaanfashion.dto.CategoryDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.CategoryTM;
import lk.ijse.gdse72.shaanfashion.model.CategoryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private JFXButton btnCategoryReport;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<CategoryTM , String> colCategoryId;

    @FXML
    private TableColumn<CategoryTM , String> colCategoryName;

    @FXML
    private TableColumn<CategoryTM , String> colDescprition;

    @FXML
    private Label lblCategoryId;

    @FXML
    private Label lblNotify;

    @FXML
    private TableView<CategoryTM> tbtCategory;

    @FXML
    private JFXTextField txtCategoryName;

    @FXML
    private JFXTextField txtDescription;

    CategoryModel categoryModel = new CategoryModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colDescprition.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        ArrayList<CategoryDTO> categoryDTOS = categoryModel.getAllCategoryS();
        ObservableList<CategoryTM> customerTMS = FXCollections.observableArrayList();

        for (CategoryDTO categoryDTO : categoryDTOS) {
            CategoryTM customerTM = new CategoryTM(
                    categoryDTO.getCategoryId(),
                    categoryDTO.getCategoryName(),
                    categoryDTO.getDescription()
            );
            customerTMS.add(customerTM);
        }
        tbtCategory.setItems(customerTMS);
    }

    @FXML
    void btnCategoryReportOnAcrion(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String categoryId = lblCategoryId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this category?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = categoryModel.deleteCategory(categoryId);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Category delete...!").show();
                lblNotify.setText("Category successfully delete!");
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Category...!").show();
                lblNotify.setText("Failed to delete category.");
            }
        }
    }

    @FXML
    void btnResertOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String categoryId = lblCategoryId.getText();
        String categoryName = txtCategoryName.getText();
        String description = txtDescription.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = categoryName.matches(namePattern);

        txtCategoryName.setStyle(txtCategoryName.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");


        if (!isValidName) {
            txtCategoryName.setStyle(txtCategoryName.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName ) {
            CategoryDTO customerDTO = new CategoryDTO(categoryId , categoryName , description);

            boolean isSaved = categoryModel.saveCategory(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Category saved...!").show();
                lblNotify.setText("Category successfully saved!");
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Category...!").show();
                lblNotify.setText("Failed to save category.");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String categoryId = lblCategoryId.getText();
        String categoryName = txtCategoryName.getText();
        String description = txtDescription.getText();


        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";


        boolean isValidName = categoryName.matches(namePattern);


        txtCategoryName.setStyle(txtCategoryName.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidName) {
            txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName) {

            CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryName, description);

            boolean isUpdate = categoryModel.updateCategory(categoryDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Category update...!").show();
                lblNotify.setText("Category successfully update!");
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update Category...!").show();
                lblNotify.setText("Failed to update category.");
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CategoryTM selectedItem = tbtCategory.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblCategoryId.setText(selectedItem.getCategoryId());
            txtCategoryName.setText(selectedItem.getCategoryName());
            txtDescription.setText(selectedItem.getDescription());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

        }
    }
}
