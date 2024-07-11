module org.example.flashcardsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.flashcardsapp to javafx.fxml;
    exports org.example.flashcardsapp;
}
