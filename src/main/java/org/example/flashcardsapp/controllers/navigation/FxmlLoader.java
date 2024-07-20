package org.example.flashcardsapp.controllers.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlLoader {

    public Parent loadFxml(String fxmlFileName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));

        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoaderResult loadFxmlWithController(String fxmlFileName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));

        try {
            Parent root = loader.load();
            return new LoaderResult(root, loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
            return new LoaderResult(null, null);
        }
    }

    public void loadFxmlAsDialog(String fxmlFileName, String title, Stage ownerStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));

        try {
            Parent root = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ownerStage);
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class LoaderResult {
        private final Parent root;
        private final Object controller;

        public LoaderResult(Parent root, Object controller) {
            this.root = root;
            this.controller = controller;
        }

        public Parent getRoot() {
            return root;
        }

        public Object getController() {
            return controller;
        }
    }
}
