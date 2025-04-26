package com.example.proj2.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Accounts {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public Accounts(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() {
        return username.get();
    }
    public String getPassword() {
        return password.get();
    }
    public void setUsername(String username) {
        this.username.set(username);
    }
    public void setPassword(String password) {
        this.password.set(password);
    }
    public StringProperty getUsernameProperty() {
        return username;
    }
    public StringProperty getPasswordProperty() {
        return password;
    }




    }

