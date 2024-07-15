package org.example.flashcardsapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private Button accountButton;

    @FXML
    private Button deckCreationButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    public TextField deckNameField;

    @FXML
    public TextField deckDescriptionField;

    @FXML
    void initialize() {

        homeButton.setOnAction(event -> {
            System.out.println("переход на домашнюю страницу");

            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        accountButton.setOnAction(event -> {
            System.out.println("переход на страницу аккаунта");

            // Переходим на страницу с аккаунтом
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            NavigationManager.goToAccountPage(currentStage);

        });

        webSiteUrlButton.setOnAction(event -> {
            System.out.println("переходим по ссылке на сайт");

            // Открываем ссылку на сайт
            NavigationManager.openWebPage("https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s");
        });

        deckCreationButton.setOnAction(event -> {
            System.out.println("создание колоды");
            addNewDeck();
        });
    }

    public void addNewDeck() {
        String name = "Test1";
        String description = "physics";

        Deck deck = new Deck(name, description);
        DatabaseHandler.DeckDAO deckDAO = new DatabaseHandler.DeckDAO();

        User currentUser = Session.getInstance().getCurrentUser();

        if (currentUser != null) {
            int userId = currentUser.getId();
            deckDAO.addDeck(deck, userId);
            System.out.println("Deck added for user ID: " + userId);
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}
