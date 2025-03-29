package com.example.proj2.models;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientStore{
    private final ObservableList<Client> clients=FXCollections.observableArrayList();

    public ObservableList<Client> getClients(){
        return clients;
    }

    public void addClient(Client client){
        if(client!=null)
            clients.add(client);
    }
    public void removeClient(Client client){
        if(client!=null)
            clients.remove(client);
    }
    public void updateClient(Client client, String name, Integer id,String email, Integer phone, String address){
        if(client!=null){
            client.setName(name);
            client.setId(id);
            client.setEmail(email);
            client.setPhone(phone);
            client.setAddress(address);
        }
    }


}
