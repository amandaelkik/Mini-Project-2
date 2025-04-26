package com.example.proj2.models;

//CREATE THE OBSERVABLE ARRAYList, PREFORM CRUD OPERATIONS

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class InvoicesStore {

    private final ObservableList<Invoices> invoicesList = FXCollections.observableArrayList();

    public ObservableList<Invoices> getInvoicesList() {
        return invoicesList;
    }
    public void addInvoice(Invoices invoice){
        if(invoice !=null)
            this.invoicesList.add(invoice);
    }
    public void deleteInvoice(Invoices invoice){
        if(invoice !=null)
            this.invoicesList.remove(invoice);
    }
    public void updateInvoice(Invoices invoice, int iid, String clientName, int orderid,int totalamnt,String dateissued, String status){
        if(invoice !=null) {
            invoice.setIid(iid);
            invoice.setclientName(clientName);
            invoice.setorderid(orderid);
            invoice.settotalamnt(totalamnt);
            invoice.setdateissued(LocalDate.parse(dateissued));
            invoice.setstatus(status);


        }
    }


}
