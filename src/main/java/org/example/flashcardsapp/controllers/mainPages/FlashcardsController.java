package org.example.flashcardsapp.controllers.mainPages;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.example.flashcardsapp.utils.ErrorUtils;

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
                ErrorUtils.showError("Ошибка", "Пустые поля", "Пожалуйста, заполните все поля.");
            } else {
                int counter = loginUser(loginText, passwordText);
                if (counter >= 1) {
                    System.out.println("Вход...");

                    // Открываем окно с домашней страницей
                    Stage currentStage = (Stage) authSignInButton.getScene().getWindow();
                    NavigationManager.goToHomePage(currentStage);

                } else {
                    ErrorUtils.showError("Ошибка входа", "Неверные логин или пароль", "Пожалуйста, проверьте введенные данные.");
                }
            }
        });

        loginSignUpButton.setOnAction(event -> {
            // Открываем окно с регистрацией
            Stage currentStage = (Stage) loginSignUpButton.getScene().getWindow();
            NavigationManager.goToRegisterPage(currentStage);
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
                user.setId(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
            Session.getInstance().setCurrentUser(user);
        }
        return counter;
    }
}
