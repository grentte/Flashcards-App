package org.example.flashcardsapp.controllers.mainPages;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.flashcardsapp.controllers.modalWindows.CardViewController;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;
import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Session;
import org.example.flashcardsapp.utils.ErrorUtils;

import java.io.IOException;
import java.util.List;

public class ChosenDeckController {

    @FXML
    private Button accountButton;

    @FXML
    private Button cardCreationButton;

    @FXML
    private ListView<Card> cardsListView;

    @FXML
    private TextField chosenDeckTitle;

    @FXML
    private Button deckReviewButton;

    @FXML
    private Button decksButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button webSiteUrlButton;

    @FXML
    void initialize() {

        homeButton.setOnAction(event -> {
            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        decksButton.setOnAction(event -> {
            // Переходим на страницу с колодами
            Stage currentStage = (Stage) decksButton.getScene().getWindow();
            NavigationManager.goToDecksPage(currentStage);
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

        if (cardCreationButton == null) {
            throw new NullPointerException("cardCreationButton is not injected.");
        }

        cardCreationButton.setOnAction(event -> openCardCreationDialog());
        deckReviewButton.setOnAction(event -> reviewDeck());

        // Добавление слушателя для списка карточек
        cardsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showChosenCard(newValue);
            }
        });

        loadCards();
    }

    public void setDeckName(String deckName) {
        chosenDeckTitle.setText(deckName);
        chosenDeckTitle.setEditable(false);
    }

    private void loadCards() {
        DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();
        Session session = Session.getInstance();
        int currentDeck = session.getCurrentDeckId();
        if (currentDeck != 0) {
            int deckId = currentDeck;
            List<Card> cards = cardDAO.getCardsByDeckId(deckId);
            cardsListView.getItems().setAll(cards);
        } else {
            System.out.println("Не найдена текущая колода.");
        }
    }

    private void openCardCreationDialog() {
        Stage currentStage = (Stage) cardCreationButton.getScene().getWindow();
        NavigationManager.showCardCreationDialog(currentStage);

        // После закрытия диалога обновляем список карточек
        loadCards();
    }

    private void showChosenCard(Card card) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/flashcardsapp/CardView.fxml"));
            Parent parent = loader.load();

            CardViewController controller = loader.getController();
            controller.setCard(card);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reviewDeck() {
        Stage currentStage = (Stage) deckReviewButton.getScene().getWindow();

        // Загрузка карточек из базы данных
        DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();
        Session session = Session.getInstance();
        int currentDeck = session.getCurrentDeckId();
        if (currentDeck != 0) {
            int deckId = currentDeck;
            List<Card> cards = cardDAO.getCardsByDeckId(deckId);

            if (!cards.isEmpty()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/flashcardsapp/CardView.fxml"));
                    Parent parent = loader.load();

                    CardViewController controller = loader.getController();
                    controller.setCards(cards, 0); // Передаем карточки и начальный индекс

                    Stage stage = new Stage();
                    stage.setTitle("Просмотр колоды");
                    stage.setScene(new Scene(parent));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(currentStage);
                    stage.show();
                } catch (IOException e) {
                    // Используем ErrorUtils для отображения сообщения об ошибке
                    ErrorUtils.showError("Ошибка загрузки", "Не удалось загрузить представление карточек", e.getMessage());
                }
            } else {
                // Используем ErrorUtils для отображения сообщения об ошибке
                ErrorUtils.showError("Пустая колода", "Колода пуста", "В текущей колоде нет карточек.");
            }
        } else {
            // Используем ErrorUtils для отображения сообщения об ошибке
            ErrorUtils.showError("Не найдена колода", "Ошибка текущей колоды", "Не найдена текущая колода.");
        }
    }

}
