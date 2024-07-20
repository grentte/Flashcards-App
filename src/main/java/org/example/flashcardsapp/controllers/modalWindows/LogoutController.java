package org.example.flashcardsapp.controllers.modalWindows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;

public class LogoutController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button logoutButton;

    private Stage accountPageStage; // Ссылка на основное окно

    public void setAccountPageStage(Stage accountPageStage) {
        this.accountPageStage = accountPageStage;
    }

    @FXML
    void initialize() {
        logoutButton.setOnAction(event -> {
            System.out.println("подтверждение выхода из аккаунта");

            // Закрываем всплывающее окно
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();

            // Закрываем основное окно
            if (accountPageStage != null) {
                accountPageStage.close();
            }

            // Перенаправляем на страницу входа
            NavigationManager.goToLoginPage(stage);
            // + еще нужно "обнулить" id пользователя
        });

        cancelButton.setOnAction(event -> {
            System.out.println("отмена выхода из аккаунта");

            // Закрываем всплывающее окно
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
