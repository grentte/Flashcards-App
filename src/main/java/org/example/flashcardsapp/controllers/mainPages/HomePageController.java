package org.example.flashcardsapp.controllers.mainPages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;

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
            // Переходим на страницу с колодами
            Stage currentStage = (Stage) decksButton.getScene().getWindow();
            NavigationManager.goToDecksPage(currentStage);

        });

        accountButton.setOnAction(event -> {
            // Переходим на страницу с аккаунтом
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            NavigationManager.goToAccountPage(currentStage);

        });

        webSiteUrlButton.setOnAction(event -> {
            // Переходим по ссылке на сайт
            NavigationManager.openWebPage();
        });
    }
}