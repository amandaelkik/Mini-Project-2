package com.example.proj2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;



import java.time.LocalDate;

public class InvoicesStore {

    private final ObservableList<Invoices> invoicesList = FXCollections.observableArrayList();

    public ObservableList<Invoices> getInvoicesList() {
        return invoicesList;
    }
    // OPENING A CONNECTION TO A DATABASE

    String URL = "jdbc:mysql://localhost:3306/invoicing_system";
    String USER = "root";
    String PASSWORD = "00000000";
    public void addInvoice(Invoices invoice){

        //before jdbc: this.invoicesList.add(invoice);

        /*
         * This method connects to the database and inserts a new invoice into the Invoices table.
         * It uses a PreparedStatement to safely set the invoice data, avoiding SQL injection.
         * The inserted invoice is also added to the local ObservableList used for UI updates.
         */

        String query = "INSERT INTO Invoices(iid,clientName,orderid,totalamnt,dateissued,status) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, invoice.getIid()); //EACH NB AT FIRST IS THE ? IN VALUES UP THERE^
            stmt.setString(2, invoice.getClientName());
            stmt.setInt(3, invoice.getOrderid());
            stmt.setInt(4, invoice.getTotalamnt());
            stmt.setDate(5, java.sql.Date.valueOf(invoice.getDateissued()));
            stmt.setString(6, invoice.getStatus());

            stmt.executeUpdate();

            this.invoicesList.add(invoice); // UPDATE LOCAL Observable List
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("INVOICES ADDED");
            alert.setContentText("INVOICE SUCCESSFULLY ADDED \n\nNOTE: CHANGES HAVE BEEN MADE IN YOUR DATABASE");
            alert.showAndWait();


        }catch (SQLException e) {
            e.printStackTrace(); // This is what handles the exception
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("INVOICE ID ALREADY EXISTS");
            alert.showAndWait();
        }


    }
    public void deleteInvoice(Invoices invoice){
        //if(invoice !=null)
        // this.invoicesList.remove(invoice);
        String query = "DELETE FROM Invoices WHERE iid = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, invoice.getIid());
            stmt.executeUpdate();

            invoicesList.remove(invoice); // Remove from local list
            System.out.println("Invoice deleted.");
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("INVOICES DELETED");
            alert.setContentText("INVOICE SUCCESSFULLY DELETED \n\nNOTE: CHANGES HAVE BEEN MADE IN YOUR DATABASE");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("INVOICE ID ALREADY EXISTS");
            alert.showAndWait();
        }

    }
    public void updateInvoice(Invoices invoice, int iid, String clientName, int orderid,int totalamnt,String dateissued, String status){
        /*if(invoice !=null) {
            invoice.setIid(iid);
            invoice.setclientName(clientName);
            invoice.setorderid(orderid);
            invoice.settotalamnt(totalamnt);
            invoice.setdateissued(LocalDate.parse(dateissued));
            invoice.setstatus(status);
        }*/
        String query = "UPDATE Invoices SET clientName = ?, orderid=?,totalamnt = ?, dateissued = ?,status= ? WHERE iid = ?";
        try (Connection conn = DriverManager.getConnection( URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clientName);
            stmt.setInt(2, orderid);
            stmt.setInt(3, totalamnt);
            stmt.setString(4, dateissued);
            stmt.setString(5, status);
            stmt.setInt(6, iid);

            stmt.executeUpdate();

            invoice.setIid(iid);
            invoice.setclientName(clientName);
            invoice.setorderid(orderid);
            invoice.settotalamnt(totalamnt);
            invoice.setdateissued(LocalDate.parse(dateissued));
            invoice.setstatus(status);

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("INVOICES UPDATED");
            alert.setContentText("INVOICE SUCCESSFULLY UPDATED \n\nNOTE: CHANGES HAVE BEEN MADE IN YOUR DATABASE");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("INVOICE ID ALREADY EXISTS");
            alert.showAndWait();


        }
    }
    public void loadInvoicesFromDatabase() {
        String query = "SELECT * FROM Invoices";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int iid = rs.getInt("iid");
                String clientName = rs.getString("clientName");
                int orderid = rs.getInt("orderid");
                int totalamnt = rs.getInt("totalamnt");
                LocalDate dateissued = rs.getDate("dateissued").toLocalDate();
                String status = rs.getString("status");

                invoicesList.add(new Invoices(iid, clientName, orderid, totalamnt, dateissued, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}






