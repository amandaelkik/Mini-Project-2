package com.example.proj2.models;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountStore extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text title = new Text("All Accounts");
        title.setFont(Font.font("HELVETICA", FontWeight.BOLD, 32));
        title.setFill(Color.WHITE);

        ListView<String> listView = new ListView<>();

        VBox vbox = new VBox(25);
        vbox.setStyle("-fx-background-color: purple;");
        vbox.setPadding(new Insets(25));
        vbox.getChildren().addAll(title, listView);

        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("All Accounts");
        primaryStage.show();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/workshopidb";
            String dbUsername = "root";
            String dbPassword = "123456-";
            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println(conn);

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Accounts";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);

            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                listView.getItems().add("Username: " + username + ", Password: " + password);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
