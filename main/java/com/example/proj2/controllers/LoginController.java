package com.example.proj2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class LoginController{

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    Text errorMsg=new Text();

    @FXML
    void loginHandler(ActionEvent event) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();

        if(username.equals("") || password.equals("")) {
            errorMsg.setText("Username and password are required!");
        }
        else if((!username.equals("amandaelkik") || !password.equals("a12345"))&&(!username.equals("mazenmorched") || !password.equals("m12345"))) {
            errorMsg.setText("Incorrect username and/or password");
        }
        else
            login(event);
    }


    void login(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/proj2/views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();




    }

}
