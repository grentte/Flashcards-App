package org.example.flashcardsapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorUtils {

    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
