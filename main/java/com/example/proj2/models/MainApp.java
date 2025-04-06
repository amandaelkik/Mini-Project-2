package com.example.proj2.models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/product-view.fxml"));

        AnchorPane root = loader.load();

        root.setStyle("-fx-background-color: #0078d4;");

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
