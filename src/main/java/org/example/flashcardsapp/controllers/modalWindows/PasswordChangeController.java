package org.example.flashcardsapp.controllers.modalWindows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.database.User;
import org.example.flashcardsapp.utils.ErrorUtils;

public class PasswordChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField currentPassword;

    @FXML
    private TextField newPassword;

    @FXML
    void initialize() {
        confirmButton.setOnAction(event -> {
            // "Достаём" данные, введенные пользователем
            User currentUser = Session.getInstance().getCurrentUser();
            int id = currentUser.getId();
            String currentPasswordText = currentPassword.getText().trim();
            String newPasswordText = newPassword.getText().trim();

            // Проверяем, заполнил ли пользователь все поля
            if (currentPasswordText.isEmpty() || newPasswordText.isEmpty()) {
                ErrorUtils.showError("Ошибка смены пароля", "Пустые поля", "Пожалуйста, заполните все поля.");
                return;
            }

            // Проверяем текущий пароль
            if(currentUser.getPassword().equals(currentPasswordText)) {
                System.out.println("подтверждение смены пароля");
                DatabaseHandler.updateUserPassword(id, newPasswordText);
                currentUser.setPassword(newPasswordText);
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            } else {
                ErrorUtils.showError("Ошибка смены пароля", "Неверный пароль", "Введен неверный пароль от аккаунта.");
            }
        });

        cancelButton.setOnAction(event -> {
            System.out.println("отмена смены пароля");

            // Закрываем всплывающее окно
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
