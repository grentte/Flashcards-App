package org.example.flashcardsapp.controllers.mainPages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.DatabaseHandler;

public class CardController {

    @FXML
    public TextField frontSideField;

    @FXML
    public TextField backSideField;

    @FXML
    public Button addCardButton;

    private int currentDeckId;

    @FXML
    void initialize() {
        addCardButton.setOnAction(event -> addNewCard());
    }

    public void setCurrentDeckId(int deckId) {
        this.currentDeckId = deckId;
    }

    public void addNewCard() {
        String frontSide = frontSideField.getText();
        String backSide = backSideField.getText();

        Card card = new Card(frontSide, backSide);
        DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();

        int deckId = 1; // Здесь можно получить ID колоды из контекста
        cardDAO.addCard(card, deckId);

        // Закрываем текущее окно
        Stage currentStage = (Stage) addCardButton.getScene().getWindow();
        currentStage.close();
    }
}
