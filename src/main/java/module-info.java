module org.example.flashcardsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.flashcardsapp to javafx.fxml;
    exports org.example.flashcardsapp;
    exports org.example.flashcardsapp.controllers;
    opens org.example.flashcardsapp.controllers to javafx.fxml;
    exports org.example.flashcardsapp.database;
    opens org.example.flashcardsapp.database to javafx.fxml;
}
