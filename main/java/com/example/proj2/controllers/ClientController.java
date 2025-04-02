package com.example.proj2.controllers;

import com.example.proj2.models.Client;
import com.example.proj2.models.ClientStore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController {

    @FXML
    private TableColumn<Client, SimpleStringProperty>nameCol;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<Client, SimpleIntegerProperty> idCol;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Client, SimpleStringProperty> emailCol;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<Client, SimpleIntegerProperty> phoneCol;

    @FXML
    private TextField phoneField;

    @FXML
    private TableColumn<Client, SimpleStringProperty> addressCol;

    @FXML
    private TextField addressField;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private Button addBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Text errorMsg;

    private final ClientStore clientStore = new ClientStore();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<Client> persons = clientStore.getClients();
        clientTable.setItems(persons);

        clientTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                nameField.setText(selectedClient.getName());
                idField.setText(String.valueOf(selectedClient.getId()));
                emailField.setText(selectedClient.getEmail());
                phoneField.setText(Integer.toString(selectedClient.getPhone()));
                addressField.setText(selectedClient.getAddress());
            }
        });
    }
        @FXML
        void addClient(ActionEvent event) {
            String error = "";
            boolean isValid = true;

            String name = nameField.getText();
            if(name.isEmpty()) {
                error += "Error: Name is required\n";
                isValid = false;
            }

            Integer id = null;
            if(idField.getText().isEmpty()) {
                error += "Error: ID is required\n";
                isValid = false;
            }
            else try{
                id = Integer.parseInt(idField.getText());
            }
            catch(NumberFormatException e) {
                error += "Error: ID must be an integer\n";
                isValid = false;
            }

            String email = emailField.getText();
            if(email.isEmpty()) {
                error += "Error: Email is required\n";
                isValid = false;
            }
            else {
                email= emailField.getText()+"@gmail.com";
            }

            Integer phone = null;
            if(phoneField.getText().isEmpty()) {
                error += "Error: Phone is required\n";
                isValid = false;
            }
            else try {
                phone = Integer.parseInt(phoneField.getText());
            } catch(NumberFormatException ex){
                error += "Error: Invalid phone value!\n";
                isValid = false;
            }

            String address = addressField.getText();
            if(address.isEmpty()) {
                error += "Error: Address is required\n";
                isValid = false;
            }

            if(isValid) {
                clientStore.addClient(new Client(name,id,email ,phone, address));

                nameField.setText("");
                idField.setText("");
                emailField.setText("");
                phoneField.setText("");
                addressField.setText("");
                errorMsg.setText("");
            }
            else
                errorMsg.setText(error);

        }
    @FXML
    void returnHome (ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/com/example/proj2/views/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(homeRoot);
        stage.setScene(homeScene);
        stage.show();
    }
    @FXML
    void deleteClient(ActionEvent event) {
        Client selectedPerson = clientTable.getSelectionModel().getSelectedItem();

        if(selectedPerson != null) {
            clientStore.removeClient(selectedPerson);

        }
    }
    @FXML
    void updateClient(ActionEvent event) {

        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            String error = "";
            boolean isValid = true;

            String name = nameField.getText();
            if (name.isEmpty()) {
                error += "Error: Name is required!\n";
                isValid = false;
            }
            Integer id = null;
            if (idField.getText().isEmpty()) {
                error += "Error: ID is required\n";
                isValid = false;
            } else try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                error += "Error: ID must be an integer\n";
                isValid = false;
            }

            String email = emailField.getText();
            if (email.isEmpty()) {
                error += "Error: Email is required\n";
                isValid = false;
            }

            Integer phone = null;
            if (phoneField.getText().isEmpty()) {
                error += "Error: Phone is required\n";
                isValid = false;
            } else try {
                phone = Integer.parseInt(phoneField.getText());
            } catch (NumberFormatException ex) {
                error += "Error: Invalid phone value!\n";
                isValid = false;
            }

            String address = addressField.getText();
            if (address.isEmpty()) {
                error += "Error: Address is required!";
            }

            if (isValid) {
                clientStore.updateClient(selectedClient, name, id, email, phone, address);
                errorMsg.setText("");
            } else
                errorMsg.setText(error);

        }



    }}







