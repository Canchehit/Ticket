/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambiarEstadoTicketController extends Application {

 
    private TableView<Ticket> ticketTable;
    private ComboBox<String> statusComboBox;
    private TextArea commentTextArea;
    private Button changeStatusButton, cancelButton;
    private ObservableList<Ticket> ticketList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cambio de Estado de Ticket");

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

        // ComboBox para seleccionar el nuevo estado
        statusComboBox = new ComboBox<>();
        statusComboBox.setItems(FXCollections.observableArrayList("Pendiente", "En proceso", "Escalado", "Cerrado"));

        // Área de texto para el comentario adicional
        commentTextArea = new TextArea();
        commentTextArea.setPromptText("Comentario adicional (opcional)");

        // Botón para cambiar el estado
        changeStatusButton = new Button("Cambiar Estado");
        changeStatusButton.setOnAction(event -> changeTicketStatus());

        // Botón para cancelar el cambio
        cancelButton = new Button("Cancelar");
        cancelButton.setOnAction(event -> cancelChange());

        // Layout
        VBox layout = new VBox(10, ticketTable, statusComboBox, commentTextArea, changeStatusButton, cancelButton);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Lógica para cambiar el estado del ticket
    private void changeTicketStatus() {
        Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        if (selectedTicket != null && statusComboBox.getValue() != null) {
            String newStatus = statusComboBox.getValue();
            String comment = commentTextArea.getText();

            // Validación de cambio de estado (por ejemplo, no permitir que el estado se cambie a 'Cerrado' si ya está 'En proceso')
            if (!isValidStateTransition(selectedTicket.getStatus(), newStatus)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Transición de estado no válida.", ButtonType.OK);
                alert.show();
                return;
            }

            

            // Mostrar confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Estado cambiado con éxito.", ButtonType.OK);
            alert.show();

            // Limpiar campos
            statusComboBox.setValue(null);
            commentTextArea.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Seleccione un ticket y un estado válido.", ButtonType.OK);
            alert.show();
        }
    }

    // Lógica de validación de la transición del estado
    private boolean isValidStateTransition(String currentStatus, String newStatus) {
        // Implementar reglas de flujo de trabajo
        // Ejemplo: No permitir cambiar de "Pendiente" a "Cerrado" directamente
        if (currentStatus.equals("Pendiente") && newStatus.equals("Cerrado")) {
            return false;
        }
        // Puedes agregar más reglas según las necesidades del flujo de trabajo
        return true;
    }

    // Lógica para cancelar el cambio
    private void cancelChange() {
        statusComboBox.setValue(null);
        commentTextArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Clase Ticket para manejar los datos de los tickets
class Tickets {
    private String ticketNumber;
    private String status;
    private String comments = "";

    public Tickets(String ticketNumber, String status) {
        this.ticketNumber = ticketNumber;
        this.status = status;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            this.comments += "Comentario: " + comment + "\n";
        }
    }

    public String getComments() {
        return comments;
    }
}