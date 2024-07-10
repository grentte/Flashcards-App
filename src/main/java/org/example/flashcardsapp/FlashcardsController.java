package org.example.flashcardsapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FlashcardsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Flashcards Application!");
    }
}