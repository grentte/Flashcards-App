package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private Button decksButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    void initialize() {
        decksButton.setOnAction(event -> {
            System.out.println("переход на страницу с колодами");

            // Переходим на страницу с колодами
            Stage currentStage = (Stage) decksButton.getScene().getWindow();
            NavigationManager.goToDecksPage(currentStage);

        });

        accountButton.setOnAction(event -> {
            System.out.println("переход на страницу аккаунта");

            // Переходим на страницу с аккаунтом
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            NavigationManager.goToAccountPage(currentStage);

        });

        webSiteUrlButton.setOnAction(event -> {
            System.out.println("переходим по ссылке на сайт");

            // Переходим по ссылке на сайт
            NavigationManager.openWebPage("https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s");
        });
    }
}