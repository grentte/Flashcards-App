package org.example.flashcardsapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerSignInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        registerSignInButton.setOnAction(event -> {
            System.out.println("переход на страницу авторизации");

            // закрываем окно с регистрацией
            Stage currentStage = (Stage) registerSignInButton.getScene().getWindow();
            currentStage.close();

            // переходим на страницу авторизации
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signUpButton.setOnAction(event -> {
            // регистрация
            System.out.println("регистрация");
        });
    }
}
