module org.example.flashcardsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.example.flashcardsapp to javafx.fxml;
    exports org.example.flashcardsapp;
    exports org.example.flashcardsapp.database;
    opens org.example.flashcardsapp.database to javafx.fxml;
    exports org.example.flashcardsapp.controllers.modalWindows;
    opens org.example.flashcardsapp.controllers.modalWindows to javafx.fxml;
    exports org.example.flashcardsapp.controllers.navigation;
    opens org.example.flashcardsapp.controllers.navigation to javafx.fxml;
    exports org.example.flashcardsapp.controllers.mainPages;
    opens org.example.flashcardsapp.controllers.mainPages to javafx.fxml;
}
