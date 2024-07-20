package org.example.flashcardsapp.controllers.modalWindows;

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
import org.example.flashcardsapp.utils.ErrorUtils;

public class LoginChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField currentPassword;

    @FXML
    private TextField newLogin;

    @FXML
    void initialize() {
        confirmButton.setOnAction(event -> {
            // "Достаём" данные, введенные пользователем
            User currentUser = Session.getInstance().getCurrentUser();
            int id = currentUser.getId();
            String currentPasswordText = currentPassword.getText().trim();
            String newLoginText = newLogin.getText().trim();

            // Проверяем, заполнил ли пользователь все поля
            if (currentPasswordText.isEmpty() || newLoginText.isEmpty()) {
                ErrorUtils.showError("Ошибка смены логина", "Пустые поля", "Пожалуйста, заполните все поля.");
                return;
            }

            // Проверяем текущий пароль
            if(currentUser.getPassword().equals(currentPasswordText)) {
                System.out.println("подтверждение смены логина");
                DatabaseHandler.updateLogin(id, newLoginText);
                currentUser.setLogin(newLoginText);
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            } else {
                ErrorUtils.showError("Ошибка смены логина", "Неверный пароль", "Введен неверный пароль от аккаунта.");
            }
        });

        cancelButton.setOnAction(event -> {
            System.out.println("отмена смены логина");

            // Закрываем всплывающее окно
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
