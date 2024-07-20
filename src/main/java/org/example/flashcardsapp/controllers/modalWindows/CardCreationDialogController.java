package org.example.flashcardsapp.controllers.modalWindows;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.utils.ErrorUtils;

public class CardCreationDialogController {

    @FXML
    private TextField frontSideField;

    @FXML
    private TextField backSideField;

    private int deckId;

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    @FXML
    private void handleCreateCard() {
        // Записываем данные, которые ввел пользователь
        String frontSide = frontSideField.getText().trim();
        String backSide = backSideField.getText().trim();

        // Проверяем пустые ли поля
        if (frontSide.isEmpty() || backSide.isEmpty()) {
            ErrorUtils.showError("Ошибка создания карточки", "Пустые поля", "Пожалуйста, заполните оба поля.");
            return;
        }

        // Добавляем карточку в базу данных
        Card card = new Card(frontSide, backSide);
        DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();
        cardDAO.addCard(card, deckId);
        System.out.println("Создана карточка с фронтальной стороной: " + frontSide + " и обратной стороной: " + backSide + " для " + deckId);

        // Закрываем окно после создания карточки и добавления ее в БД
        Stage stage = (Stage) frontSideField.getScene().getWindow();
        stage.close();
    }
}
