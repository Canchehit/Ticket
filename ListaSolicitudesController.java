/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.net.URL;import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaSolicitudesController extends Application {

   private TableView<Ticket> ticketTable;
    private ObservableList<Ticket> ticketList;
  private Button btnVolver;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Tickets");
        
        // Tabla de tickets
        ticketTable = new TableView<>();
        TableColumn<Ticket, String> numberColumn = new TableColumn<>("Número de Ticket");
        TableColumn<Ticket, String> statusColumn = new TableColumn<>("Estado");
        TableColumn<Ticket, String> dateColumn = new TableColumn<>("Fecha de Creación");
        TableColumn<Ticket, String> deptColumn = new TableColumn<>("Departamento");
        TableColumn<Ticket, String> priorityColumn = new TableColumn<>("Prioridad");
        TableColumn<Ticket, String> summaryColumn = new TableColumn<>("Resumen");
        
        ticketTable.getColumns().addAll(numberColumn, statusColumn, dateColumn, deptColumn, priorityColumn, summaryColumn);
        
        // Lista de tickets (datos de prueba)
        ticketList = FXCollections.observableArrayList(
            new Ticket("1", "Pendiente", "2025-03-26", "Soporte", "Alta", "Problema con la red"),
            new Ticket("2", "En proceso", "2025-03-25", "IT", "Media", "Error en el sistema")
        );
        ticketTable.setItems(ticketList);
        
        // Filtros
        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por número de ticket");
        ComboBox<String> statusFilter = new ComboBox<>(FXCollections.observableArrayList("Pendiente", "En proceso", "Escalado", "Cerrado"));
        statusFilter.setPromptText("Filtrar por estado");
        
        Button filterButton = new Button("Aplicar Filtro");
        filterButton.setOnAction(e -> applyFilters(searchField, statusFilter));
        
         btnVolver = new Button("Volver al Menú");
        btnVolver.setOnAction(e -> volverAlMenu(primaryStage));
        
        VBox layout = new VBox(10, searchField, statusFilter, filterButton, ticketTable);
        Scene scene = new Scene(layout, 600, 400);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait(); 
     }

    
    private void applyFilters(TextField searchField, ComboBox<String> statusFilter) {
        String searchText = searchField.getText();
        String selectedStatus = statusFilter.getValue();
        
        ObservableList<Ticket> filteredList = FXCollections.observableArrayList();
        for (Ticket ticket : ticketList) {
            boolean matchesSearch = searchText.isEmpty() || ticket.getNumber().contains(searchText);
            boolean matchesStatus = selectedStatus == null || ticket.getStatus().equals(selectedStatus);
            
            if (matchesSearch && matchesStatus) {
                filteredList.add(ticket);
            }
        }
        
        ticketTable.setItems(filteredList);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

class Tickets1 {
    private String number;
    private String status;
    private String date;
    private String department;
    private String priority;
    private String summary;
    
    public Tickets1(String number, String status, String date, String department, String priority, String summary) {
        this.number = number;
        this.status = status;
        this.date = date;
        this.department = department;
        this.priority = priority;
        this.summary = summary;
    }
    
    public String getNumber() { return number; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public String getDepartment() { return department; }
    public String getPriority() { return priority; }
    public String getSummary() { return summary; }

    void ticketNumberProperty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addComment(String comment) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}

