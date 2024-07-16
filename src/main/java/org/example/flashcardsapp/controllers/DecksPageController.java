package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class DecksPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private Button deckCreationButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    void initialize() {

        homeButton.setOnAction(event -> {
            System.out.println("переход на домашнюю страницу");

            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        accountButton.setOnAction(event -> {
            System.out.println("переход на страницу аккаунта");

            // Переходим на страницу с аккаунтом
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            NavigationManager.goToAccountPage(currentStage);

        });

        webSiteUrlButton.setOnAction(event -> {
            System.out.println("переходим по ссылке на сайт");

            // Открываем ссылку на сайт
            NavigationManager.openWebPage("https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s");
        });

        deckCreationButton.setOnAction(event -> {
            System.out.println("создание колоды");

            // Выводим всплывающее окно для создание колоды
            Stage currentStage = (Stage) deckCreationButton.getScene().getWindow();
            NavigationManager.showDeckCreationDialog(currentStage);
        });
    }
}
