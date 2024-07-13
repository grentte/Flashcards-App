package org.example.flashcardsapp.controllers;

import java.awt.Desktop;
import java.net.URI;
import javafx.stage.Stage;

public class NavigationManager {

    public static void goToDecksPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxml("/org/example/flashcardsapp/decks.fxml");
        currentStage.close();
    }

    public static void goToAccountPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxml("/org/example/flashcardsapp/account.fxml");
        currentStage.close();
    }

    public static void goToHomePage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxml("/org/example/flashcardsapp/home.fxml");
        currentStage.close();
    }

    public static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок при открытии ссылки
        }
    }
}
