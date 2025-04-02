package com.example.proj2.controllers;

import javafx.scene.Node;
import javafx.scene.Parent;
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
    void openClientBtn(ActionEvent event) throws Exception{
        Parent clientRoot =  FXMLLoader.load(getClass().getResource("/com/example/proj2/views/client-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(clientRoot);
        stage.setScene(homeScene);
        stage.show();
    }
}
