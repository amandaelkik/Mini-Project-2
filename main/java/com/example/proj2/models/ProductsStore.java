package com.example.proj2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsStore {

    private ObservableList<Product> products = FXCollections.observableArrayList();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public void updateProduct(int index, Product updatedProduct) {
        products.set(index, updatedProduct);
    }

    public ObservableList<Product> getProducts() {
        return products;
    }
}
