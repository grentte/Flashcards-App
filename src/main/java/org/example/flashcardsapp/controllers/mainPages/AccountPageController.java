package org.example.flashcardsapp.controllers.mainPages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;


public class AccountPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decksButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    private Button loginChangeButton;

    @FXML
    private Button nameChangeButton;

    @FXML
    private Button passwordChangeButton;

    @FXML
    private Button accountDeletionButton;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() {

        homeButton.setOnAction(event -> {
            System.out.println("переход на домашнюю страницу");

            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        decksButton.setOnAction(event -> {
            System.out.println("переход на страницу с колодами");

            // Переходим на страницу с колодами
            Stage currentStage = (Stage) decksButton.getScene().getWindow();
            NavigationManager.goToDecksPage(currentStage);
        });

        webSiteUrlButton.setOnAction(event -> {
            System.out.println("переходим по ссылке на сайт");

            // Открываем ссылку на сайт
            NavigationManager.openWebPage("https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s");
        });


        loginChangeButton.setOnAction(event -> {
           System.out.println("смена логина");

           // Открываем всплывающее окно для смены логина
           Stage currentStage = (Stage) loginChangeButton.getScene().getWindow();
            NavigationManager.showLoginChangeDialog(currentStage);
        });

        nameChangeButton.setOnAction(event -> {
            System.out.println("смена имени пользователя");

            // Открываем всплывающее окно для смены имени пользователя
            Stage currentStage = (Stage) nameChangeButton.getScene().getWindow();
            NavigationManager.showNameChangeDialog(currentStage);
        });

        passwordChangeButton.setOnAction(event -> {
            System.out.println("смена пароля");

            // Открываем всплывающее окно для смены пароля
            Stage currentStage = (Stage) passwordChangeButton.getScene().getWindow();
            NavigationManager.showPasswordChangeDialog(currentStage);
        });

        accountDeletionButton.setOnAction(event -> {
            System.out.println("удаление аккаунта");

            // Открываем всплывающее окно для удаления аккаунта
            Stage currentStage = (Stage) accountDeletionButton.getScene().getWindow();
            NavigationManager.showAccountDeletionDialog(currentStage);
        });

        logoutButton.setOnAction(event -> {
            System.out.println("выход из аккаунта");

            // Открываем всплывающее окно для выходя из аккаунта
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            NavigationManager.showLogoutDialog(currentStage);
        });
    }
}
