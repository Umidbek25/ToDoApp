package sample.todoapp.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import sample.todoapp.Database.DatabaseHandler;
import sample.todoapp.model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewUserAddController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXCheckBox signUpCheckBoxFemale;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        signUpButton.setOnAction(event -> {
            try {
                createUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });


    }

    private void createUser() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String firstName = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();


        String gender = "";
        if (signUpCheckBoxFemale.isSelected()) {
            gender = "Female";
        }else gender = "Male";

        User user = new User(firstName, lastName, userName, password, location, gender);

        databaseHandler.signUpUser(user);
    }

}
