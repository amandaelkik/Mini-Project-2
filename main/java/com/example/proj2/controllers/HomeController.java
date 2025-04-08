package com.example.demoo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private Button clientBtn;
    @FXML
    private Button invoicesBtn111;

    @FXML
    void openClientBtn(ActionEvent event) throws Exception{
        Parent clientRoot =  FXMLLoader.load(getClass().getResource("/com/example/demoo/views/client-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(clientRoot);
        stage.setScene(homeScene);
        stage.show();
    }
    @FXML
    void openOrdersBtn(ActionEvent event) throws Exception{
        Parent orderRoot =  FXMLLoader.load(getClass().getResource("/com/example/demoo/views/orders-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(orderRoot);
        stage.setScene(homeScene);
        stage.show();
    }
    @FXML
    void openProductsBtn(ActionEvent event) throws Exception{
        Parent orderRoot =  FXMLLoader.load(getClass().getResource("/com/example/demoo/views/product-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(orderRoot);
        stage.setScene(homeScene);
        stage.show();
    }
    @FXML
    void openInvoicesBtn(ActionEvent event) throws Exception{
        Parent invoicesRoot =  FXMLLoader.load(getClass().getResource("/com/example/demoo/views/invoices-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(invoicesRoot);
        stage.setScene(homeScene);
        stage.setTitle(">INVOICES MANAGEMENT");
        stage.show();
    }
}
