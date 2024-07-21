package org.example.flashcardsapp.controllers.modalWindows;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.example.flashcardsapp.database.Card;

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

    private boolean isFront = true;

    @FXML
    void initialize() {
        cardPane.setOnMouseClicked(event -> flipCard());
    }

    public void setCard(Card card) {
        frontLabel.setText(card.getFrontSide());
        backLabel.setText(card.getBackSide());
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
