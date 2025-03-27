/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RegistrarUsuariosController  extends Application {
    
  private TextField nombreCompleto, correoElectronico, nombreUsuario;
    private PasswordField contrasena;
    private ComboBox<String> rolAsignado, departamento;
    private Button btnGuardar, btnCancelar,btnVolver;

    @Override
    public void start(Stage primaryStage) {
        nombreCompleto = new TextField();
        nombreCompleto.setPromptText("Nombre Completo");

        correoElectronico = new TextField();
        correoElectronico.setPromptText("Correo Electrónico");

        nombreUsuario = new TextField();
        nombreUsuario.setPromptText("Nombre de Usuario");

        contrasena = new PasswordField();
        contrasena.setPromptText("Contraseña");

        rolAsignado = new ComboBox<>();
        rolAsignado.getItems().addAll("Administrador", "Técnico", "Usuario");
        rolAsignado.setValue("Usuario");
        rolAsignado.setOnAction(e -> toggleDepartamento());

        departamento = new ComboBox<>();
        departamento.getItems().addAll("Soporte", "Desarrollo", "Infraestructura");
        departamento.setDisable(true);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarUsuario());
        
        btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelarRegistro());
        
        btnVolver = new Button("Volver al Menú");
        btnVolver.setOnAction(e -> volverAlMenu(primaryStage));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Nombre Completo"), nombreCompleto,
                new Label("Correo Electrónico"), correoElectronico,
                new Label("Nombre de Usuario"), nombreUsuario,
                new Label("Contraseña"), contrasena,
                new Label("Rol Asignado"), rolAsignado,
                new Label("Departamento"), departamento,
                new HBox(10, btnGuardar, btnCancelar)
        );

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Gestión de Usuarios");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleDepartamento() {
        departamento.setDisable(!"Técnico".equals(rolAsignado.getValue()));
    }

    private void guardarUsuario() {
        if (nombreCompleto.getText().length() < 3 || nombreCompleto.getText().length() > 100) {
            mostrarAlerta("Error", "El nombre debe tener entre 3 y 100 caracteres.");
            return;
        }
        if (!correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            mostrarAlerta("Error", "El correo electrónico no es válido.");
            return;
        }
        if (nombreUsuario.getText().length() < 5 || nombreUsuario.getText().length() > 30) {
            mostrarAlerta("Error", "El nombre de usuario debe tener entre 5 y 30 caracteres.");
            return;
        }
        if (!contrasena.getText().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            mostrarAlerta("Error", "La contraseña debe tener mínimo 8 caracteres, una mayúscula, un número y un carácter especial.");
            return;
        }
        mostrarAlerta("Éxito", "Usuario registrado correctamente.");
    }

    private void cancelarRegistro() {
        nombreCompleto.clear();
        correoElectronico.clear();
        nombreUsuario.clear();
        contrasena.clear();
        rolAsignado.setValue("Usuario");
        departamento.setValue(null);
    }
    
     private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait(); 
     }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

