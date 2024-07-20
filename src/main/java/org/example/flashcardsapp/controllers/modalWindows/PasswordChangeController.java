package org.example.flashcardsapp.controllers.modalWindows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            // "достаём" данные, введенные пользователем
            String currentPasswordText = currentPassword.getText().trim();
            String newPasswordText = newPassword.getText().trim();

            // Проверяем, заполнил ли пользователь все поля
            if (currentPasswordText.isEmpty() || newPasswordText.isEmpty()) {
                System.out.println("Пустые поля");
            // Если поля не пустые (случай else), то меняем пароль
            } else {
                System.out.println("подтверждение смены пароля");
                // меняем пароль, а потом закрываем окошко

                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
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
