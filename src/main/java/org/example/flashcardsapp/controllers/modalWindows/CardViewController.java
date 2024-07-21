package org.example.flashcardsapp.controllers.modalWindows;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.example.flashcardsapp.database.Card;

import java.util.List;

public class CardViewController {

    @FXML
    private StackPane frontSide;

    @FXML
    private StackPane backSide;

    @FXML
    private AnchorPane cardPane;

    @FXML
    private Label frontLabel;

    @FXML
    private Label backLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    private boolean isFront = true;
    private List<Card> cards;
    private int currentIndex = 0;

    @FXML
    void initialize() {
        cardPane.setOnMouseClicked(event -> flipCard());

        nextButton.setOnAction(event -> showNextCard());
        prevButton.setOnAction(event -> showPreviousCard());
    }

    public void setCard(Card card) {
        frontLabel.setText(card.getFrontSide());
        backLabel.setText(card.getBackSide());
    }

    public void setCards(List<Card> cards, int startIndex) {
        this.cards = cards;
        this.currentIndex = startIndex;
        showCard(currentIndex);
    }

    private void showCard(int index) {
        if (cards == null || index < 0 || index >= cards.size()) {
            return;
        }
        Card card = cards.get(index);
        frontLabel.setText(card.getFrontSide());
        backLabel.setText(card.getBackSide());
        isFront = true;
        frontSide.setVisible(true);
        backSide.setVisible(false);
        cardPane.setRotate(0);
    }

    private void showNextCard() {
        if (cards != null && currentIndex < cards.size() - 1) {
            currentIndex++;
            showCard(currentIndex);
        }
    }

    private void showPreviousCard() {
        if (cards != null && currentIndex > 0) {
            currentIndex--;
            showCard(currentIndex);
        }
    }

    private void flipCard() {
        RotateTransition rotateOut = new RotateTransition(Duration.seconds(0.25), cardPane);
        rotateOut.setAxis(new Point3D(0, 1, 0)); // Вращение по вертикальной оси
        rotateOut.setFromAngle(0);
        rotateOut.setToAngle(90);

        rotateOut.setOnFinished(e -> {
            // Переключение видимости панелей на середине поворота
            frontSide.setVisible(!isFront);
            backSide.setVisible(isFront);

            // Второй поворот (от 90 до 180 градусов)
            RotateTransition rotateIn = new RotateTransition(Duration.seconds(0.25), cardPane);
            rotateIn.setAxis(new Point3D(0, 1, 0));
            rotateIn.setFromAngle(90);
            rotateIn.setToAngle(180);

            rotateIn.setOnFinished(event2 -> {
                // Сброс угла поворота и переключение видимости панелей
                cardPane.setRotate(0);
                isFront = !isFront;
                frontSide.setVisible(isFront);
                backSide.setVisible(!isFront);
            });

            rotateIn.play();
        });

        rotateOut.play();
    }
}
