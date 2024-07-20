package org.example.flashcardsapp.controllers.modalWindows;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Deck;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.database.User;

public class DeckCreationDialogController {

    @FXML
    private TextField deckNameField;

    @FXML
    private TextField deckDescriptionField;

    @FXML
    private void handleCreateDeck() {
        // записываем данные, которые ввел пользователь
        String deckName = deckNameField.getText().trim();
        String deckDescription = deckDescriptionField.getText().trim();

        // добавляем колоду в базу данных
        Deck deck = new Deck(deckName, deckDescription);
        DatabaseHandler.DeckDAO deckDAO = new DatabaseHandler.DeckDAO();

        User currentUser = Session.getInstance().getCurrentUser();

        if (currentUser != null) {
            int userId = currentUser.getId();
            deckDAO.addDeck(deck, userId);
            System.out.println("Колода добавлена для пользователя с ID: " + userId);
        } else {
            System.out.println("Не найдено ID пользователя.");
        }

        System.out.println("Создана колода с названием: " + deckName + " и описанием: " + deckDescription);

        // закрываем окно после "создания колоды"
        Stage stage = (Stage) deckNameField.getScene().getWindow();
        stage.close();
    }
}
