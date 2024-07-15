package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
            FxmlLoader fxmlLoader = new FxmlLoader();
            fxmlLoader.loadFxml("/org/example/flashcardsapp/login.fxml");

            // Закрываем текущее окно
            Stage currentStage = (Stage) registerSignInButton.getScene().getWindow();
            currentStage.close();
        });

        signUpButton.setOnAction(event -> {
            signUpNewUser();
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
