package org.example.flashcardsapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.User;

public class FlashcardsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();
            if (loginText.isEmpty() || passwordText.isEmpty()) {
                System.out.println("Пустые поля");
            } else {
                int counter = loginUser(loginText, passwordText);
                if (counter >= 1) {
                    System.out.println("Вход...");

                    // Открываем окно с домашней страницей
                    FxmlLoader fxmlLoader = new FxmlLoader();
                    fxmlLoader.loadFxml("/org/example/flashcardsapp/home.fxml");

                    // Закрываем окно с авторизацией
                    Stage currentStage = (Stage) authSignInButton.getScene().getWindow();
                    currentStage.close();
                } else {
                    System.out.println("Неверные логин или пароль");
                }
            }
        });

        loginSignUpButton.setOnAction(event -> {
            System.out.println("переход на страницу регистрации");

            // Открываем окно с регистрацией
            FxmlLoader fxmlLoader = new FxmlLoader();
            fxmlLoader.loadFxml("/org/example/flashcardsapp/signUp.fxml");

            // Закрываем окно с авторизацией
            Stage currentStage = (Stage) loginSignUpButton.getScene().getWindow();
            currentStage.close();
        });
    }

    private int loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
