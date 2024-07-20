package org.example.flashcardsapp.controllers.modalWindows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
            // "достаём" данные, введенные пользователем
            String currentPasswordText = currentPassword.getText().trim();
            String newLoginText = newLogin.getText().trim();

            // Проверяем, заполнил ли пользователь все поля
            if (currentPasswordText.isEmpty() || newLoginText.isEmpty()) {
                System.out.println("Пустые поля");
                // Если поля не пустые (случай else), то меняем логин
            } else {
                System.out.println("подтверждение смены логина");
                // меняем логин, а потом закрываем окошко

                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
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
