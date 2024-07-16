package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
    private ListView<String> decksListView;

    @FXML
    private Label errorMessageLabel;

    private ObservableList<String> deckList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        homeButton.setOnAction(event -> {
            System.out.println("переход на домашнюю страницу");
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        if (deckCreationButton == null) {
            throw new NullPointerException("deckCreationButton is not injected.");
        }

        deckCreationButton.setOnAction(event -> {
            System.out.println("Создание новой колоды");
            // Открытие окна создания новой колоды
            Stage currentStage = (Stage) deckCreationButton.getScene().getWindow();
            NavigationManager.showDeckCreationDialog(currentStage);
            // Перезагрузка колод после создания новой
            loadDecks();
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
                deckList.add(deck.getName() + ": " + deck.getDescription());
            }

            decksListView.setItems(deckList);
        } else {
            System.out.println("Не найдено ID пользователя.");
        }
    }
}
