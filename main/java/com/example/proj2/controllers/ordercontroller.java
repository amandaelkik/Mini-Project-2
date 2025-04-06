package com.example.proj2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.order;
import models.orderstore;

public class ordercontroller {
    @FXML private TableView<order> ordersTable;
    @FXML private TextField orderIdField;
    @FXML private TextField clientIdField;
    @FXML private TextField productIdField;
    @FXML private TextField quantityField;
    @FXML private DatePicker orderDateField;
    @FXML private ComboBox<String> statusField;
    
    private orderstore orderstore = new orderstore();
    
    @FXML
    public void initialize() {
        ordersTable.setItems(orderstore.getOrders());
        statusField.getItems().addAll("Pending", "Processing", "Shipped", "Delivered", "Cancelled");
    }
    
    @FXML
    @SuppressWarnings("unused")
    private void handleAddOrder() {
        // Implementation similar to ClientController
    }
    
    @FXML
    @SuppressWarnings("unused")
    private void handleUpdateOrder() {
        // Implementation similar to ClientController
    }
    
    @FXML
    @SuppressWarnings("unused")
    private void handleDeleteOrder() {
        // Implementation similar to ClientController
    }
    
    @SuppressWarnings("unused")
    private void clearFields() {
        orderIdField.clear();
        clientIdField.clear();
        productIdField.clear();
        quantityField.clear();
        orderDateField.setValue(null);
        statusField.setValue(null);
    }
    
    @SuppressWarnings("unused")
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public orderstore getOrderstore() {
        return orderstore;
    }

    public void setOrderstore(orderstore orderstore) {
        this.orderstore = orderstore;
    }
}
