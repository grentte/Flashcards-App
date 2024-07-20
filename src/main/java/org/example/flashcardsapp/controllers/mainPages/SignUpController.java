package org.example.flashcardsapp.controllers.mainPages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.database.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerSignInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        registerSignInButton.setOnAction(event -> {
            System.out.println("переход на страницу авторизации");

            // Переходим на страницу авторизации
            Stage currentStage = (Stage) registerSignInButton.getScene().getWindow();
            NavigationManager.goToLoginPage(currentStage);
        });

        signUpButton.setOnAction(event -> {
            signUpNewUser();

            // После регистрации переходим на страницу с авторизацией
            Stage currentStage = (Stage) registerSignInButton.getScene().getWindow();
            NavigationManager.goToLoginPage(currentStage);
            System.out.println("регистрация");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String login = loginField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();

        User user = new User(login, name, password);

        dbHandler.signUpUser(user);

        Session.getInstance().setCurrentUser(user);
    }
}