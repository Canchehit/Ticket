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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import static javafx.application.Application.launch;
public class ParametrosController extends Application {
   private TextField nombreEmpresa;
    private ImageView logoPreview;
    private ComboBox<String> idiomaComboBox;
    private ComboBox<String> zonaHorariaComboBox;
    private Spinner<Integer> tiempoVencimientoSpinner;
    private ListView<String> listaPrioridades;
    private Button btnGuardar, btnCancelar, btnCargarLogo,btnVolver;

    @Override
    public void start(Stage primaryStage) {
        nombreEmpresa = new TextField();
        nombreEmpresa.setPromptText("Nombre de la Empresa");

        logoPreview = new ImageView();
        logoPreview.setFitHeight(100);
        logoPreview.setFitWidth(100);
        btnCargarLogo = new Button("Cargar Logo");
        btnCargarLogo.setOnAction(e -> cargarLogo());

        idiomaComboBox = new ComboBox<>();
        idiomaComboBox.getItems().addAll("Español", "Inglés");
        idiomaComboBox.setValue("Español");

        zonaHorariaComboBox = new ComboBox<>();
        zonaHorariaComboBox.getItems().addAll("UTC-5", "UTC-6", "UTC-7", "UTC-8");
        zonaHorariaComboBox.setValue("UTC-5");

        tiempoVencimientoSpinner = new Spinner<>(1, 365, 30);

        listaPrioridades = new ListView<>();
        listaPrioridades.getItems().addAll("Alta", "Media", "Baja");
        listaPrioridades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarConfiguracion());
        
        btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelarConfiguracion());
        
       Button btnVolver = new Button("Volver al Menú");
        btnVolver.setOnAction(e -> volverAlMenu(primaryStage));
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Nombre de la Empresa"), nombreEmpresa,
                new Label("Logo"), btnCargarLogo, logoPreview,
                new Label("Idioma"), idiomaComboBox,
                new Label("Zona Horaria"), zonaHorariaComboBox,
                new Label("Tiempo de Vencimiento (días)"), tiempoVencimientoSpinner,
                new Label("Niveles de Prioridad"), listaPrioridades,
                new HBox(10, btnGuardar, btnCancelar)
        );
        
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setTitle("Configuración del Sistema");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cargarLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null && file.length() <= 2 * 1024 * 1024) {
            logoPreview.setImage(new Image(file.toURI().toString()));
        } else {
            mostrarAlerta("Error", "El archivo debe ser JPG o PNG y no superar los 2MB.");
        }
    }

    private void guardarConfiguracion() {
        if (nombreEmpresa.getText().length() < 3 || nombreEmpresa.getText().length() > 100) {
            mostrarAlerta("Error", "El nombre de la empresa debe tener entre 3 y 100 caracteres.");
            return;
        }
        mostrarAlerta("Éxito", "Configuración guardada correctamente.");
    }

    private void cancelarConfiguracion() {
        nombreEmpresa.clear();
        idiomaComboBox.setValue("Español");
        zonaHorariaComboBox.setValue("UTC-5");
        tiempoVencimientoSpinner.getValueFactory().setValue(30);
        listaPrioridades.getSelectionModel().clearSelection();
        logoPreview.setImage(null);
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

   private void volverAlMenu(Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Volver al Menú");
        alerta.setContentText("Funcionalidad para cambiar de escena aún no implementada.");
        alerta.showAndWait();
    }
}
