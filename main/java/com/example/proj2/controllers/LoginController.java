package com.example.proj2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import java.sql.*;



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
        else  try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/workshopidb";
            String dbUsername = "root";
            String dbPassword = "123456-";
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println(conn);

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Accounts WHERE username=\"" + username
                    + "\" AND password=\"" + password + "\"";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);

            if(rs.next()) {
                errorMsg.setFill(Color.GREEN);
                errorMsg.setText("Success! You have been signed in!");
                login(event);
            }
            else {
                errorMsg.setText("Error: Invalid Username and/or Password!");
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
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
