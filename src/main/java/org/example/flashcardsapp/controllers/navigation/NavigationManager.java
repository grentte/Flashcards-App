package org.example.flashcardsapp.controllers.navigation;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.mainPages.ChosenDeckController;
import org.example.flashcardsapp.controllers.modalWindows.AccountDeletionController;
import org.example.flashcardsapp.controllers.modalWindows.LogoutController;
import org.example.flashcardsapp.controllers.navigation.FxmlLoader.LoaderResult;

public class NavigationManager {

    private static final FxmlLoader fxmlLoader = new FxmlLoader();

    private static void navigateToPage(Stage currentStage, String fxmlFileName, String title) {
        Parent root = fxmlLoader.loadFxml(fxmlFileName);
        if (root != null) {
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();
            currentStage.close();
        }
    }

    public static void goToDecksPage(Stage currentStage) {
        navigateToPage(currentStage, "/org/example/flashcardsapp/decks.fxml", "Колоды");
    }

    public static void goToAccountPage(Stage currentStage) {
        navigateToPage(currentStage, "/org/example/flashcardsapp/account.fxml", "Аккаунт");
    }

    public static void goToHomePage(Stage currentStage) {
        navigateToPage(currentStage, "/org/example/flashcardsapp/home.fxml", "Домашняя страница");
    }

    public static void goToRegisterPage(Stage currentStage) {
        navigateToPage(currentStage, "/org/example/flashcardsapp/signUp.fxml", "Регистрация");
    }

    public static void goToLoginPage(Stage currentStage) {
        navigateToPage(currentStage, "/org/example/flashcardsapp/login.fxml", "Авторизация");
    }

    public static void openWebPage() {
        try {
            String url = "https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s";
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showDeckCreationDialog(Stage ownerStage) {
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/deckCreationDialog.fxml", "Создание новой колоды", ownerStage);
    }

    public static void showCardCreationDialog(Stage ownerStage) {
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/cardCreationDialog.fxml", "Создание новой карточки", ownerStage);
    }

    public static void showPasswordChangeDialog(Stage ownerStage) {
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/passwordChange.fxml", "Смена пароля", ownerStage);
    }

    public static void showLoginChangeDialog(Stage ownerStage) {
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/loginChange.fxml", "Изменение логина", ownerStage);
    }

    public static void showNameChangeDialog(Stage ownerStage) {
        fxmlLoader.loadFxmlAsDialog("/org/example/flashcardsapp/nameChange.fxml", "Изменение имени", ownerStage);
    }

    public static void showLogoutDialog(Stage ownerStage) {
        showModalDialog(ownerStage, "/org/example/flashcardsapp/logout.fxml", "Выход из аккаунта", LogoutController.class);
    }

    public static void showAccountDeletionDialog(Stage ownerStage) {
        showModalDialog(ownerStage, "/org/example/flashcardsapp/accountDeletion.fxml", "Удаление аккаунта", AccountDeletionController.class);
    }

    public static void showChosenDeck(Stage currentStage, String deckName) {
        LoaderResult result = fxmlLoader.loadFxmlWithController("/org/example/flashcardsapp/chosenDeck.fxml");
        if (result.getRoot() != null) {
            ChosenDeckController controller = (ChosenDeckController) result.getController();
            controller.setDeckName(deckName);

            Stage newStage = new Stage();
            newStage.setTitle("Информация о колоде");
            newStage.setScene(new Scene(result.getRoot()));
            newStage.show();

            currentStage.close();
        }
    }

    private static <T> void showModalDialog(Stage ownerStage, String fxmlFileName, String title, Class<T> controllerClass) {
        FXMLLoader loader = new FXMLLoader(NavigationManager.class.getResource(fxmlFileName));
        try {
            Parent root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ownerStage);
            dialogStage.setScene(new Scene(root));

            T controller = loader.getController();
            if (controllerClass.isInstance(controller)) {
                controllerClass.getMethod("setAccountPageStage", Stage.class).invoke(controller, ownerStage);
            }

            dialogStage.showAndWait();
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
