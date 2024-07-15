package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    }
}
