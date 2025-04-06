package com.example.proj2.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty phone;
    private final SimpleStringProperty address;

    public Client(String name, Integer id, String email, Integer phone, String address) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleIntegerProperty(phone);
        this.address = new SimpleStringProperty(address);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return this.name;
    }
    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return this.id;
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public StringProperty emailProperty(){
        return this.email;
    }
    public int getPhone() {
        return phone.get();
    }
    public void setPhone(int phone) {
        this.phone.set(phone);
    }
    public IntegerProperty phoneProperty(){
        return this.phone;
    }
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public StringProperty addressProperty(){
        return this.address;
    }
}

