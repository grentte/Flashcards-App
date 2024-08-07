package org.example.flashcardsapp.controllers.mainPages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Deck;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.database.User;

public class DecksPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button homeButton;

    @FXML
    private Button deckCreationButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    private ListView<String> decksListView;

    @FXML
    private Label errorMessageLabel;

    private final ObservableList<String> deckList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        homeButton.setOnAction(event -> {
            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
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

        if (deckCreationButton == null) {
            throw new NullPointerException("deckCreationButton is not injected.");
        }

        deckCreationButton.setOnAction(event -> {
            // Открытие окна создания новой колоды
            Stage currentStage = (Stage) deckCreationButton.getScene().getWindow();
            NavigationManager.showDeckCreationDialog(currentStage);

            // Перезагрузка колод после создания новой
            loadDecks();
        });

        // Добавляем слушатель для выбора элемента в ListView
        decksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showChosenDeck(newValue);
            }
        });

        loadDecks();
    }

    private void loadDecks() {
        deckList.clear();
        User currentUser = Session.getInstance().getCurrentUser();

        if (currentUser != null) {
            int userId = currentUser.getId();
            DatabaseHandler.DeckDAO deckDAO = new DatabaseHandler.DeckDAO();
            var decks = deckDAO.getDecksByUserId(userId);

            for (Deck deck : decks) {
                deckList.add(deck.getName());
            }

            decksListView.setItems(deckList);
        } else {
            System.out.println("Не найдено ID пользователя.");
        }
    }

    private void showChosenDeck(String deckName) {
        DatabaseHandler.DeckDAO deckDAO = new DatabaseHandler.DeckDAO();
        Deck deck = deckDAO.getDeckByName(deckName);

        if (deck != null) {
            // Устанавливаем текущую колоду в сессию
            int deckId = deck.getDeck_id();
            Session.getInstance().setCurrentDeckId(deckId);

            // Переход к странице выбранной колоды
            Stage currentStage = (Stage) decksListView.getScene().getWindow();
            NavigationManager.showChosenDeck(currentStage, deckName);
        } else {
            System.out.println("Deck not found with name: " + deckName);
        }
    }
}

