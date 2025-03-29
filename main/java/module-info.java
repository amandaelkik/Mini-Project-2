module com.example.proj2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.proj2 to javafx.fxml;
    exports com.example.proj2;
    exports com.example.proj2.models;
    opens com.example.proj2.models to javafx.fxml;
    exports com.example.proj2.controllers;
    opens com.example.proj2.controllers to javafx.fxml;
}