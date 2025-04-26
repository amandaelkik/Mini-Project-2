package com.example.proj2.models;

import javafx.beans.property.*;

import java.time.LocalDate;

//TO ADD VARIABLES;CONSTRUCTOR;GETTERS AND SETTERS; AND THE SPECIAL JAVAFX PROPERTY GETTER

public class Invoices {
    private final SimpleIntegerProperty iid ;
    private final SimpleStringProperty clientName;
    private final SimpleIntegerProperty orderid;
    private final SimpleIntegerProperty totalamnt;
    private final SimpleObjectProperty<LocalDate> dateissued;
    private final SimpleStringProperty status;

    public Invoices(int iid, String clientName,int orderid,int totalamnt,LocalDate dateissued,String status){
        this.iid = new SimpleIntegerProperty(iid);
        this.clientName = new SimpleStringProperty(clientName);
        this.orderid = new SimpleIntegerProperty(orderid);
        this.totalamnt = new SimpleIntegerProperty(totalamnt);
        this.dateissued = new SimpleObjectProperty<>(dateissued);
        this.status = new SimpleStringProperty(status);
    }
    //iid
    public int getIid() {
        return iid.get();
    }
    public void setIid(int iid) {
        this.iid.set(iid);
    }
    public IntegerProperty iidProperty(){
        return this.iid;
    }
    //clientName
    public String getClientName() {
        return clientName.get();
    }
    public void setclientName(String clientName) {
        this.clientName.set(clientName);
    }
    public StringProperty clientNameProperty(){
        return this.clientName;
    }
    //orderid
    public int getOrderid() {
        return orderid.get();
    }
    public void setorderid(int orderid) {
        this.orderid.set(orderid);
    }
    public IntegerProperty orderidProperty(){
        return this.orderid;
    }
    //totalamnt
    public int getTotalamnt(){
        return totalamnt.get();
    }
    public void settotalamnt(int totalamnt){
        this.totalamnt.set(totalamnt);
    }
    public IntegerProperty totalamntProperty(){
        return this.totalamnt;
    }
    //dateissued
    public LocalDate getDateissued(){
        return dateissued.get();
    }
    public void setdateissued(LocalDate dateissued){
        this.dateissued.set(dateissued);
    }
    public ObjectProperty dateissuedProperty(){
        return this.dateissued;
    }
    //status
    public String getStatus(){
        return status.get();
    }
    public void setstatus(String status){
        this.status.set(status);
    }
    public StringProperty statusProperty(){
        return this.status;
    }

}
