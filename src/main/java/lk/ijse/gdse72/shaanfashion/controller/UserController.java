package lk.ijse.gdse72.shaanfashion.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse72.shaanfashion.dto.tm.UserDTO;
import lk.ijse.gdse72.shaanfashion.dto.tm.UserTM;
import javafx.scene.control.TableView;
import lk.ijse.gdse72.shaanfashion.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TableColumn<UserTM,String> colEmail;

    @FXML
    private TableColumn<UserTM,String> colPassword;

    @FXML
    private TableColumn<UserTM,String> colUserId;
    @FXML
    private TableColumn<UserTM,String> colFullName;

    @FXML
    private TableColumn<UserTM,String> colUsername;

    @FXML
    private TableView<UserTM> tblUser;

    UserModel userModel = new UserModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("userFullName"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();

    }

    private void refreshTable() throws SQLException {
        ArrayList<lk.ijse.gdse72.shaanfashion.dto.tm.UserDTO> userDTOS = userModel.getAllUsers();
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();


        for (UserDTO userDTO : userDTOS) {
            UserTM userTM = new UserTM(
                    userDTO.getUserId(),
                    userDTO.getUserFullName(),
                    userDTO.getUsername(),
                    userDTO.getUserEmail(),
                    userDTO.getPassword()
            );
            userTMS.add(userTM);
        }
        tblUser.setItems(userTMS);
    }
    public void userSelect (UserTM userTM) {
        UserTM selectedUser = tblUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null && "C001".equals(selectedUser.getUserId())) {
            System.out.println("User with ID C001 is selected.");
        } else if (selectedUser != null) {
            System.out.println("Selected user ID: " + selectedUser.getUserId());
        } else {
            System.out.println("No user is selected.");
        }
    }
}
