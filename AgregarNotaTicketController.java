/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import static javafx.application.Application.launch;

public class AgregarNotaTicketController extends Application  {

    
    private TableView<Ticket> ticketTable;
    private TextArea noteTextArea;
    private Button addNoteButton, attachButton,btnVolver;
    private File attachedFile;
    private ObservableList<Ticket> ticketList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agregar Nota al Ticket");

        // Lista de Tickets (ejemplo)
        ticketList = FXCollections.observableArrayList(
                new Ticket("001", "Pendiente"),
                new Ticket("002", "En proceso")
        );

        // Tabla de Tickets
        ticketTable = new TableView<>();
        TableColumn<Ticket, String> ticketNumberColumn = new TableColumn<>("Número de Ticket");
        ticketNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTicketNumber()));

        TableColumn<Ticket, String> ticketStatusColumn = new TableColumn<>("Estado");
        ticketStatusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        ticketTable.getColumns().addAll(ticketNumberColumn, ticketStatusColumn);
        ticketTable.setItems(ticketList);

        // Área de texto para agregar la nota
        noteTextArea = new TextArea();
        noteTextArea.setPromptText("Escribe tu nota aquí...");

        // Botón para agregar la nota
        addNoteButton = new Button("Agregar Nota");
        addNoteButton.setOnAction(event -> addNoteToTicket());

        // Botón para adjuntar archivo
        attachButton = new Button("Adjuntar Archivo");
        attachButton.setOnAction(event -> attachFile());
        
        btnVolver = new Button("Volver al Menú");
        btnVolver.setOnAction(e -> volverAlMenu(primaryStage));

        // Layout
        VBox layout = new VBox(10, ticketTable, noteTextArea, attachButton, addNoteButton);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait(); 
     }
    

    // Lógica para agregar una nota al ticket seleccionado
    private void addNoteToTicket() {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            String noteContent = noteTextArea.getText();
            if (!noteContent.isEmpty()) {
                // Aquí deberías agregar la nota al ticket, guardándola en un historial de notas (no implementado en este ejemplo)
                selectedTicket.addNote(noteContent, attachedFile);

                // Limpiar campos después de agregar la nota
                noteTextArea.clear();
                attachedFile = null;

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nota agregada con éxito", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "La nota no puede estar vacía.", ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un ticket primero.", ButtonType.OK);
            alert.show();
        }
    }

    // Lógica para adjuntar un archivo a la nota
    private void attachFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Todos los Archivos", "*.*"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            attachedFile = file;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Archivo adjunto: " + file.getName(), ButtonType.OK);
            alert.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Clase Ticket con la lógica para agregar notas
class Ticket {
    private String ticketNumber;
    private String status;
    private String notesHistory = "";

    public Ticket(String ticketNumber, String status) {
        this.ticketNumber = ticketNumber;
        this.status = status;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void addNote(String noteContent, File attachedFile) {
        notesHistory += "Nota: " + noteContent + "\n";
        if (attachedFile != null) {
            notesHistory += "Archivo adjunto: " + attachedFile.getName() + "\n";
        }
        // Aquí podrías agregar más lógica para almacenar las notas de manera más compleja
    }

    public String getNotesHistory() {
        return notesHistory;
    }
}
