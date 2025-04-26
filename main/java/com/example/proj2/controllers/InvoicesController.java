package com.example.proj2.controllers;

import com.example.proj2.models.Invoices;
import com.example.proj2.models.InvoicesStore;
//import eu.hansolo.toolbox.observables.ObservableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class InvoicesController {
    //NOTE THAT ALL OF THOSE ARE THE IDS IN SCENE BUILDER
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private TableView<Invoices> invoicesTable;
    @FXML
    private TableColumn<Invoices, Integer> idCol;
    @FXML
    private TableColumn<Invoices, String> cnameCol;
    @FXML
    private TableColumn<Invoices, Integer> orderideCol;
    @FXML
    private TableColumn<Invoices, Integer> totamntCol;
    @FXML
    private TableColumn<Invoices, String> dateissuedCol;
    @FXML
    private TableColumn<Invoices, String> statuscCol;
    @FXML
    private TextField idFld;
    @FXML
    private TextField cnameFld;
    @FXML
    private TextField orderideFld;
    @FXML
    private TextField totamntFld;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton statusRadio;
    @FXML private RadioButton paidRadio;
    @FXML private RadioButton unpaidRadio;
    @FXML private ToggleGroup statusGroup ;
    @FXML
    private Label salesTotLbl;




    @FXML
    private Alert alert;

    private final InvoicesStore invoicesStore = new InvoicesStore();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().iidProperty().asObject());
        cnameCol.setCellValueFactory(cellData -> cellData.getValue().clientNameProperty());
        orderideCol.setCellValueFactory(cellData -> cellData.getValue().orderidProperty().asObject());
        totamntCol.setCellValueFactory(cellData -> cellData.getValue().totalamntProperty().asObject());
        dateissuedCol.setCellValueFactory(cellData -> cellData.getValue().dateissuedProperty());
        statuscCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());


        ObservableList<Invoices> invoice = invoicesStore.getInvoicesList();
        invoicesTable.setItems(invoice);
        invoicesTable.refresh();
        invoicesStore.loadInvoicesFromDatabase(); // TO GET A FILLED TABLE FROM MY DBMS
        invoicesTable.setItems(invoicesStore.getInvoicesList());



        invoicesTable.getSelectionModel().selectedItemProperty().addListener(evt->{
            Invoices selectedInvoice = invoicesTable.getSelectionModel().getSelectedItem();
            if(selectedInvoice != null) {
                idFld.setText(Integer.toString(selectedInvoice.getIid()));
                cnameFld.setText(selectedInvoice.getClientName());
                orderideFld.setText(Integer.toString(selectedInvoice.getOrderid()));
                totamntFld.setText(Integer.toString(selectedInvoice.getTotalamnt()));
                datePicker.setValue(selectedInvoice.getDateissued());
                if (selectedInvoice.getStatus().equals("Paid")) paidRadio.setSelected(true);
                else unpaidRadio.setSelected(true);



            }
        });
    }
    @FXML
    void addInvoice(ActionEvent event) {
        Integer iid = null;
        Integer orderid = null;
        Integer totalamnt = null;
        LocalDate date = null;
        String status = "";

        boolean isValid = true;
        String error = "";


        if (idFld.getText().isEmpty()) {
            error += "ID field is required.\n";
            isValid = false;
        } else {
            try {
                iid = Integer.parseInt(idFld.getText());
            } catch (NumberFormatException e) {
                error += "ID must be an integer.\n";
                isValid = false;
            }
        }
        String cname = cnameFld.getText();
        if (cname.isEmpty()) {
            error += "Client name is required.\n";
            isValid = false;
        }


        if (orderideFld.getText().isEmpty()) {
            error += "Order ID is required.\n";
            isValid = false;
        } else {
            try {
                orderid = Integer.parseInt(orderideFld.getText());
            } catch (NumberFormatException e) {
                error += "Order ID must be an integer.\n";
                isValid = false;
            }
        }


        if (totamntFld.getText().isEmpty()) {
            error += "Total Amount is required.\n";
            isValid = false;
        } else {
            try {
                totalamnt = Integer.parseInt(totamntFld.getText());
            } catch (NumberFormatException e) {
                error += "Total Amount must be an integer.\n";
                isValid = false;
            }
        }


        date = datePicker.getValue();
        if (date == null) {
            error += "Date is required.\n";
            isValid = false;
        }


        Toggle selectedToggle = statusGroup.getSelectedToggle();
        if (selectedToggle == null) {
            error += "Status must be selected.\n";
            isValid = false;
        } else {
            RadioButton selectedRadio = (RadioButton) selectedToggle;
            status = selectedRadio.getText();
        }


        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please correct the following:");
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }


        Invoices newInvoice = new Invoices(iid, cnameFld.getText(), orderid, totalamnt, date, status);
        invoicesStore.addInvoice(newInvoice);
        clearFields();
        updateSalesTotal();


    }
    @FXML
    void deleteInvoice(ActionEvent event) {
        Invoices selectedInvoice = invoicesTable.getSelectionModel().getSelectedItem();
        if(selectedInvoice != null) {
            invoicesStore.deleteInvoice(selectedInvoice);
        }
        clearFields();
        updateSalesTotal();



    }
    @FXML
    public void updateInvoice(ActionEvent event) {
        Invoices selectedInvoice = invoicesTable.getSelectionModel().getSelectedItem();

        if (selectedInvoice == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Invoice Selected");
            alert.setContentText("Please select an invoice to update.");
            alert.showAndWait();
            return;
        }

        Integer iid = null;
        Integer orderid = null;
        Integer totalamnt = null;
        LocalDate date = null;
        String status = "";
        boolean isValid = true;
        String error = "";


        if (idFld.getText().isEmpty()) {
            error += "ID field is required.\n";
            isValid = false;
        } else {
            try {
                iid = Integer.parseInt(idFld.getText());
            } catch (NumberFormatException e) {
                error += "ID must be an integer.\n";
                isValid = false;
            }
        }
        String cname = cnameFld.getText();
        if (cname.isEmpty()) {
            error += "Client name is required.\n";
            isValid = false;
        }


        if (orderideFld.getText().isEmpty()) {
            error += "Order ID is required.\n";
            isValid = false;
        } else {
            try {
                orderid = Integer.parseInt(orderideFld.getText());
            } catch (NumberFormatException e) {
                error += "Order ID must be an integer.\n";
                isValid = false;
            }
        }


        if (totamntFld.getText().isEmpty()) {
            error += "Total Amount is required.\n";
            isValid = false;
        } else {
            try {
                totalamnt = Integer.parseInt(totamntFld.getText());
            } catch (NumberFormatException e) {
                error += "Total Amount must be an integer.\n";
                isValid = false;
            }
        }


        date = datePicker.getValue();
        if (date == null) {
            error += "Date is required.\n";
            isValid = false;
        }


        Toggle selectedToggle = statusGroup.getSelectedToggle();
        if (selectedToggle == null) {
            error += "Status must be selected.\n";
            isValid = false;
        } else {
            RadioButton selectedRadio = (RadioButton) selectedToggle;
            status = selectedRadio.getText();
        }


        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please fix the following:");
            alert.setContentText(error);
            alert.showAndWait();
            return;
        }
        else
            invoicesStore.updateInvoice(selectedInvoice, iid, cname, orderid, totalamnt, String.valueOf(date), status);
        clearFields();
        updateSalesTotal();


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
    private void updateSalesTotal() {
        int total = 0;
        for (Invoices inv : invoicesTable.getItems()) {
            if (inv.getStatus().equalsIgnoreCase("Paid")) {
                total += inv.getTotalamnt();
            }
        }
        salesTotLbl.setText("Sales Total: $" + total);
    }
    private void clearFields() {
        idFld.clear();
        cnameFld.clear();
        orderideFld.clear();
        totamntFld.clear();
        datePicker.setValue(null);
        statusGroup.selectToggle(null);
    }





}
