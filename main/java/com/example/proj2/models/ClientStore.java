package com.example.proj2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ClientStore {
    private final ObservableList<Client> clients = FXCollections.observableArrayList();

    public ObservableList<Client> getClients() {
        return clients;
    }

    String dbURL = "jdbc:mysql://localhost:3306/workshopidb";
    String dbUsername = "root";
    String dbPassword = "123456-";


    public void addClient(Client client) {
        String query = "INSERT INTO Client (id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, client.getId());
            pstmt.setString(2, client.getName());
            pstmt.setString(3, client.getEmail());
            pstmt.setInt(4, client.getPhone());
            pstmt.setString(5, client.getAddress());

            pstmt.executeUpdate();

            clients.add(client); // Update local ObservableList
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(Client client) {
        String query = "DELETE FROM Client WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, client.getId());
            pstmt.executeUpdate();

            clients.remove(client); // Remove from local list
            System.out.println("Client deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadClients() {
        String query = "SELECT * FROM Client";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");

                Client client = new Client(name, id, email, phone, address);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateClient(Client client, String name, Integer id, String email, Integer phone, String address) {
        String query = "UPDATE Client SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, phone);
            pstmt.setString(4, address);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();

            client.setName(name);
            client.setId(id);
            client.setEmail(email);
            client.setPhone(phone);
            client.setAddress(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



