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
import org.example.flashcardsapp.controllers.modalWindows.CardCreationDialogController;
import org.example.flashcardsapp.controllers.navigation.NavigationManager;
import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.DatabaseHandler;

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

    private int deckId;

    @FXML
    void initialize() {

        homeButton.setOnAction(event -> {
            System.out.println("переход на домашнюю страницу");

            // Переходим на домашнюю страницу
            Stage currentStage = (Stage) homeButton.getScene().getWindow();
            NavigationManager.goToHomePage(currentStage);
        });

        decksButton.setOnAction(event -> {
            System.out.println("переход на страницу с колодами");

            // Переходим на страницу с колодами
            Stage currentStage = (Stage) decksButton.getScene().getWindow();
            NavigationManager.goToDecksPage(currentStage);
        });

        accountButton.setOnAction(event -> {
            System.out.println("переход на страницу аккаунта");

            // Переходим на страницу с аккаунтом
            Stage currentStage = (Stage) accountButton.getScene().getWindow();
            NavigationManager.goToAccountPage(currentStage);
        });

        webSiteUrlButton.setOnAction(event -> {
            System.out.println("переходим по ссылке на сайт");

            // Переходим по ссылке на сайт
            NavigationManager.openWebPage("https://youtu.be/dQw4w9WgXcQ?si=Bkh6Wxm9IHRRA16s");
        });

        if (cardCreationButton == null) {
            throw new NullPointerException("cardCreationButton is not injected.");
        }

        cardCreationButton.setOnAction(event -> openCardCreationDialog());
        deckReviewButton.setOnAction(event -> reviewDeck());
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
        loadCards();
    }

    public void setDeckName(String deckName) {
        chosenDeckTitle.setText(deckName);
        chosenDeckTitle.setEditable(false);
    }

    private void loadCards() {
        DatabaseHandler.CardDAO cardDAO = new DatabaseHandler.CardDAO();
        List<Card> cards = cardDAO.getCardsByDeckId(deckId);
        cardsListView.getItems().setAll(cards);
    }

    private void openCardCreationDialog() {
        System.out.println("id: " + deckId);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/flashcardsapp/cardCreationDialog.fxml"));
            Parent parent = loader.load();

            CardCreationDialogController controller = loader.getController();
            controller.setDeckId(deckId); // Передаем идентификатор колоды

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Обновляем список карточек после закрытия окна
            loadCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reviewDeck() {
        // Ваш код для просмотра колоды
    }
}
