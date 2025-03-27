/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GestionarTicketsController extends Application{
     @FXML
    private Button  btnVolver;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Tickets");
        
        // Elementos de la UI
        Label titleLabel = new Label("Título:");
        TextField titleField = new TextField();
        
        Label descLabel = new Label("Descripción:");
        TextArea descArea = new TextArea();
        
        Label deptLabel = new Label("Departamento:");
        ComboBox<String> deptCombo = new ComboBox<>();
        deptCombo.getItems().addAll("Soporte", "IT", "Mantenimiento");
        
        Label priorityLabel = new Label("Prioridad:");
        ComboBox<String> priorityCombo = new ComboBox<>();
        priorityCombo.getItems().addAll("Baja", "Media", "Alta");
        
        Button submitButton = new Button("Crear Ticket");
        submitButton.setOnAction(e -> createTicket(titleField, descArea, deptCombo, priorityCombo));
        
        VBox layout = new VBox(10, titleLabel, titleField, descLabel, descArea, deptLabel, deptCombo, priorityLabel, priorityCombo, submitButton);
        Scene scene = new Scene(layout, 400, 400);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void createTicket(TextField titleField, TextArea descArea, ComboBox<String> deptCombo, ComboBox<String> priorityCombo) {
        String title = titleField.getText();
        String description = descArea.getText();
        String department = deptCombo.getValue();
        String priority = priorityCombo.getValue();
        
        if (title.isEmpty() || description.isEmpty() || department == null || priority == null) {
            showAlert("Error", "Todos los campos son obligatorios");
            return;
        }
        
        System.out.println("Ticket creado: " + title + " - " + department + " - " + priority);
        showAlert("Éxito", "Ticket creado exitosamente");
        
        titleField.clear();
        descArea.clear();
        deptCombo.setValue(null);
        priorityCombo.setValue(null);
    }
    
     private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait(); }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
