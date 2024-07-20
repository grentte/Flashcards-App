package org.example.flashcardsapp.controllers.navigation;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.modalWindows.AccountDeletionController;
import org.example.flashcardsapp.controllers.modalWindows.LogoutController;

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

    public static void showPasswordChangeDialog(Stage ownerStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/passwordChange.fxml", "Смена пароля", ownerStage);
    }

    public static void showLoginChangeDialog(Stage ownerStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/loginChange.fxml", "Смена логина", ownerStage);
    }

    public static void showNameChangeDialog(Stage ownerStage) {
        FxmlLoader fxmlLoader = new FxmlLoader();
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/nameChange.fxml", "Смена имени", ownerStage);
    }

    public static void showLogoutDialog(Stage ownerStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NavigationManager.class.getResource("/org/example/flashcardsapp/logout.fxml"));

        try {
            Parent root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Выход из аккаунта");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ownerStage);
            dialogStage.setScene(new Scene(root));

            // Устанавливаем ссылку на основное окно в контроллер
            LogoutController controller = loader.getController();
            controller.setAccountPageStage(ownerStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAccountDeletionDialog(Stage ownerStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NavigationManager.class.getResource("/org/example/flashcardsapp/accountDeletion.fxml"));

        try {
            Parent root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Удаление аккаунта");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ownerStage);
            dialogStage.setScene(new Scene(root));

            // Устанавливаем ссылку на основное окно в контроллер
            AccountDeletionController controller = loader.getController();
            controller.setAccountPageStage(ownerStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
