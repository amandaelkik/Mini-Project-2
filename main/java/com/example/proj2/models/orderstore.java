package com.example.proj2.models;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class orderstore {
    private ObservableList<order> orders = FXCollections.observableArrayList();
    
    // Methods similar to ClientsStore

    public ObservableList<order> getOrders() {
        return orders;
    }

    public void setOrders(ObservableList<order> orders) {
        this.orders = orders;
    }

    public ObservableList<order> getorders() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getorders'");
    }



}
