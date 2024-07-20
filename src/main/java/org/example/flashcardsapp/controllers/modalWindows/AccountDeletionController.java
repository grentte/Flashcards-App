package org.example.flashcardsapp.controllers.modalWindows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.database.User;

public class AccountDeletionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    private Stage accountPageStage; // Ссылка на основное окно

    public void setAccountPageStage(Stage accountPageStage) {
        this.accountPageStage = accountPageStage;
    }

    @FXML
    void initialize() {
        deleteButton.setOnAction(event -> {
            System.out.println("подтверждение удаление аккаунта");

            User currentUser = Session.getInstance().getCurrentUser();
            int userId = currentUser.getId();

            // Удаляем пользователя и его колоды из базы данных
            DatabaseHandler.deleteUser(userId);
            DatabaseHandler.deleteUserDecks(userId);

            // Обнуляем ID текущего пользователя в сессии
            Session.getInstance().getCurrentUser().setId(0);

            // Закрываем всплывающее окно
            Stage stage = (Stage) deleteButton.getScene().getWindow();
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
            System.out.println("отмена удаления аккаунта");

            // Закрываем всплывающее окно
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
