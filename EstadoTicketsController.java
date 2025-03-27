/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class EstadoTicketsController extends Application {

    
    @FXML
    private TextField nombreEstado;
    
    @FXML
    private TextArea descripcionEstado;
    
    @FXML
    private CheckBox estadoFinal;
    
    @FXML
    private ListView<String> listaEstadosSiguientes;
    
    @FXML
    private Button btnGuardar, btnCancelar, btnEliminar, btnVolver;

    // Acción para guardar un estado
    @FXML
    private void guardarEstado() {
        String nombre = nombreEstado.getText().trim();
        String descripcion = descripcionEstado.getText().trim();
        boolean esFinal = estadoFinal.isSelected();

        // Validaciones
        if (nombre.isEmpty() || nombre.length() < 3 || nombre.length() > 50) {
            mostrarAlerta("Error", "El nombre del estado debe tener entre 3 y 50 caracteres.");
            return;
        }

        if (!esFinal && listaEstadosSiguientes.getSelectionModel().isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar al menos un estado siguiente.");
            return;
        }

      
        mostrarAlerta("Éxito", "Estado guardado correctamente.");
    }

    // Acción para cancelar el proceso
    @FXML
    private void cancelar() {
        nombreEstado.clear();
        descripcionEstado.clear();
        estadoFinal.setSelected(false);
        listaEstadosSiguientes.getSelectionModel().clearSelection();
    }

    // Acción para eliminar un estado
    @FXML
    private void eliminarEstado() {
        String nombre = nombreEstado.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un estado para eliminar.");
            return;
        }
        mostrarAlerta("Información", "Estado eliminado correctamente.");
    }
   private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait(); 
     }
    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    
}

    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
