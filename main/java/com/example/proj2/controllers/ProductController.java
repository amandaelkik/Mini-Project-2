package com.example.proj2.controllers;

import com.example.proj2.models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class ProductController {

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private void addProduct(ActionEvent event) {
        try {
            String productId = productIdField.getText();
            String productName = productNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Product newProduct = new Product(productId, productName, price, quantity);
            productsTableView.getItems().add(newProduct);
            clearFields();

        } catch (NumberFormatException e) {
            // Show an error dialog if there's an issue converting the fields to numbers
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter valid numeric values for price and quantity.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productsTableView.getItems().remove(selectedProduct);
        }
    }

    @FXML
    private void updateProduct(ActionEvent event) {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setProductName(productNameField.getText());
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));

            productsTableView.refresh();
        }
    }

    private void clearFields() {
        productIdField.clear();
        productNameField.clear();
        priceField.clear();
        quantityField.clear();
    }
}
