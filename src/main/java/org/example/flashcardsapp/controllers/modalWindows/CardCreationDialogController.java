package org.example.flashcardsapp.controllers.modalWindows;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Deck;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.utils.ErrorUtils;

public class CardCreationDialogController {

    @FXML
    private TextField frontSideField;

    @FXML
    private TextField backSideField;

    @FXML
    private void handleCreateCard() {
        // Получение ввода пользователя
        String frontSide = frontSideField.getText().trim();
        String backSide = backSideField.getText().trim();

        // Проверка введённых данных
        if (frontSide.isEmpty() || backSide.isEmpty()) {
            ErrorUtils.showError("Ошибка создания карточки", "Пустое поле", "Пожалуйста, заполните обе стороны карточки.");
            return;
        }

        // Получение текущей колоды из сессии
        Session session = Session.getInstance();
        int currentDeck = session.getCurrentDeckId();

        if (currentDeck != 0) {
            int deckId = currentDeck;
            // Создание новой карточки
            Card card = new Card(frontSide, backSide);
            DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();

            // Добавление карточки в базу данных с использованием deck_id
            cardDAO.addCard(card, deckId);

            System.out.println("Карточка добавлена в колоду с ID: " + deckId);

            // Закрытие окна диалога
            Stage stage = (Stage) frontSideField.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Не найдена текущая колода.");
        }
    }
}
