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
import org.example.flashcardsapp.utils.ErrorUtils;

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
        });
    }

    private void signUpNewUser() {
        String login = loginField.getText().trim();
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();

        if (login.isEmpty() || name.isEmpty() || password.isEmpty()) {
            ErrorUtils.showError("Ошибка регистрации", "Пустые поля", "Пожалуйста, заполните все поля.");
            return;
        }

        DatabaseHandler dbHandler = new DatabaseHandler();

        if (dbHandler.isUserExists(login)) {
            ErrorUtils.showError("Ошибка регистрации", "Логин занят", "Логин уже занят. Пожалуйста, выберите другой логин.");
            return;
        }

        User user = new User(login, name, password);

        dbHandler.signUpUser(user);
        Session.getInstance().setCurrentUser(user);

        // После регистрации переходим на страницу с авторизацией
        Stage currentStage = (Stage) registerSignInButton.getScene().getWindow();
        NavigationManager.goToLoginPage(currentStage);
        System.out.println("регистрация");
    }
}
