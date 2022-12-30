package sample.todoapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.model.User;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainLoginPageController<event> {

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    private TextField loginUsername;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(this::handle2);


    }

  private void handle(ActionEvent event ){
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLLoginController.class.getResource("/sample/todoapp/NewUserAdd.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void showAddItemScreen() {
        loginButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/todoapp/AddItem.fxml"));
        try {
            loader.load();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }


    private void handle2(ActionEvent event) {
        String loginText = loginUsername.getText().trim();
        String loginPwd = loginPassword.getText().trim();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPwd);


        try (ResultSet userRow = databaseHandler.getUser(user)) {
            int counter = 0;
            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    System.out.println("Welcome! " + loginUsername);
                }
                if (counter == 1) {
                    showAddItemScreen();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
