package com.example.proj2.controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class HomeController{
    @FXML
    private Button clientBtn;

    @FXML
    private Button productBtn;

    @FXML
    void openClientBtn(ActionEvent event) throws Exception{
        Parent clientRoot =  FXMLLoader.load(getClass().getResource("/com/example/proj2/views/client-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(clientRoot);
        stage.setScene(homeScene);
        stage.setTitle("Client Management");
        stage.show();
    }
   @FXML
    void openProductBtn(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/proj2/views/product-view.fxml"));

        AnchorPane root = loader.load();

        root.setStyle("-fx-background-color: #0078d4;");
        Stage stage = new Stage() ;
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Product Management");
        stage.show();
    }
}
