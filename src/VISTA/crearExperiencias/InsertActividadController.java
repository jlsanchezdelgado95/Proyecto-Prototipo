/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.crearExperiencias;

import DATOS.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 53752269g
 */
public class InsertActividadController implements Initializable {

    private TextField txTipoActividad;
    private TextField txSubTipo;
    @FXML
    private TextField txRutaImagen;
    @FXML
    private TextField txDireccion;
    @FXML
    private TextField txDescripcion;
    @FXML
    private TextField txURL;
    @FXML
    private TextArea txaObservacion;
    @FXML
    private Button btInsertar;
    @FXML
    private Button btAtras;
    private ConexionBD conexion;
    private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ComboBox<String> cbTipoActividad;
    @FXML
    private ComboBox<String> cbSubtipo;
    @FXML
    private TextField txPrecio;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarDatos() {
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.add("Lugares");
        tipo.add("Restaurantes");
        tipo.add("Transportes");
        tipo.add("Otros");
        cbTipoActividad.setItems(tipo);
        cbTipoActividad.getSelectionModel().selectFirst();
        ObservableList<String> Subtipo = FXCollections.observableArrayList();

        Subtipo.add("Museo");
        Subtipo.add("Plaza");
        Subtipo.add("Hoteles");
        cbSubtipo.setItems(Subtipo);
        cbSubtipo.getSelectionModel().selectFirst();

    }

    @FXML
    private void insertarActividad(ActionEvent event) {
        boolean insertado;
        try {
            insertado = conexion.insertarActividad(cbTipoActividad.getSelectionModel().getSelectedItem(), cbSubtipo.getSelectionModel().getSelectedItem(), txDescripcion.getText(), txaObservacion.getText(), txURL.getText(), txRutaImagen.getText(), txDireccion.getText(),Double.parseDouble(txPrecio.getText()));
            if (insertado == true) {
                alerta.setTitle("Insercion correcta");
                alerta.setContentText("La actividad se ha registrado satisfactoriamente");
                alerta.showAndWait();
            } else {
                alerta.setTitle("Insercion incorrecta");
                alerta.setContentText("No hemos podido isertar su actividad");
                alerta.showAndWait();
            }
        } catch (NullPointerException e) {
            alerta.setTitle("Insercion incorrecta");
            alerta.setHeaderText("No hemos podido isertar su actividad");
            alerta.setContentText("No se olvide de seleccionar un tipo y un subtipo por favor y poner una descripcion");
            alerta.showAndWait();
        }
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/crearExperiencias/CREAREXPERIENCIAS.fxml"));
        root = loader.load();
        CREAREXPERIENCIASController datosCrearPaquetes = loader.getController();
        datosCrearPaquetes.setConexion(conexion);
        datosCrearPaquetes.cargarDatos();
        Stage escenarioVentana = (Stage) btAtras.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void mostrarSubtipo(ActionEvent event) {
        ObservableList<String> Subtipo = FXCollections.observableArrayList();
        if (cbTipoActividad.getSelectionModel().getSelectedItem().equalsIgnoreCase("lugares")) {
            Subtipo.add("Museo");
            Subtipo.add("Plaza");
            Subtipo.add("Hoteles");
        }
        if (cbTipoActividad.getSelectionModel().getSelectedItem().equalsIgnoreCase("restaurantes")) {
            Subtipo.add("Local");
            Subtipo.add("Nacional");
            Subtipo.add("Internacional");
        }
        if (cbTipoActividad.getSelectionModel().getSelectedItem().equalsIgnoreCase("Transportes")) {
            Subtipo.add("Taxi");
            Subtipo.add("Metro");
        }
        if (cbTipoActividad.getSelectionModel().getSelectedItem().equalsIgnoreCase("otros")) {
            Subtipo.add("otros");

        }
        cbSubtipo.setItems(Subtipo);
        cbSubtipo.getSelectionModel().selectFirst();
    }
}
