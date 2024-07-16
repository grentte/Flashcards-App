package org.example.flashcardsapp.controllers;

import java.awt.Desktop;
import java.net.URI;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationManager {

    public static void goToDecksPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Parent root = fxmlLoader.loadFxml("/org/example/flashcardsapp/decks.fxml");
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle("Decks Page");
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void goToAccountPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Parent root = fxmlLoader.loadFxml("/org/example/flashcardsapp/account.fxml");
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle("Account Page");
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void goToHomePage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Parent root = fxmlLoader.loadFxml("/org/example/flashcardsapp/home.fxml");
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle("Home Page");
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void goToRegisterPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Parent root = fxmlLoader.loadFxml("/org/example/flashcardsapp/signUp.fxml");
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle("Register Page");
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void goToLoginPage(Stage currentStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        Parent root = fxmlLoader.loadFxml("/org/example/flashcardsapp/login.fxml");
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle("Login Page");
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок при открытии ссылки
        }
    }

    public static void showDeckCreationDialog(Stage ownerStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/deckCreationDialog.fxml", "Создание новой колоды", ownerStage);
    }
}
