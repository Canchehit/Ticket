/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemastickets;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class RolesyPermisosController extends Application {

     private ObservableList<String> roles = FXCollections.observableArrayList("Administrador", "Técnico", "Usuario");
    private ObservableList<String> permisos = FXCollections.observableArrayList(
            "Crear tickets", "Ver tickets", "Editar tickets", "Eliminar tickets",
            "Asignar tickets", "Cambiar estado de tickets", "Agregar notas a tickets",
            "Gestionar usuarios", "Gestionar departamentos", "Gestionar flujos de trabajo",
            "Configurar parámetros del sistema");

    private ListView<String> listViewRoles;
    private TextField nombreRol;
    private TextArea descripcionRol;
    private ListView<String> listViewPermisos;
    private Button btnGuardar, btnEliminar, btnVolver;

    @Override
    public void start(Stage primaryStage) {
        listViewRoles = new ListView<>(roles);
        listViewPermisos = new ListView<>(permisos);
        listViewPermisos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        nombreRol = new TextField();
        nombreRol.setPromptText("Nombre del Rol");
        descripcionRol = new TextArea();
        descripcionRol.setPromptText("Descripción del Rol");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarRol());
        
        btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> eliminarRol());
        
        btnVolver = new Button("Volver al Menú");
        btnVolver.setOnAction(e -> volverAlMenu(primaryStage));
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Roles"), listViewRoles, 
                new Label("Nombre"), nombreRol, 
                new Label("Descripción"), descripcionRol, 
                new Label("Permisos"), listViewPermisos, 
                btnGuardar, btnEliminar, btnVolver);
        
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Gestión de Roles y Permisos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void guardarRol() {
        String nombre = nombreRol.getText().trim();
        if (nombre.length() < 3 || nombre.length() > 50) {
            mostrarAlerta("Error", "El nombre del rol debe contener entre 3 y 50 caracteres.");
            return;
        }
        if (!roles.contains(nombre)) {
            roles.add(nombre);
            nombreRol.clear();
            descripcionRol.clear();
            listViewPermisos.getSelectionModel().clearSelection();
        } else {
            mostrarAlerta("Error", "El rol ya existe.");
        }
    }

    private void eliminarRol() {
        String seleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            roles.remove(seleccionado);
        } else {
            mostrarAlerta("Error", "Seleccione un rol para eliminar.");
        }
    }

    private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
