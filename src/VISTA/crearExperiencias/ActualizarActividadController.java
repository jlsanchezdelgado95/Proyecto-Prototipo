/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.crearExperiencias;

import DATOS.ConexionBD;
import MODELO.Actividad;
import MODELO.TipoActividades;
import com.jfoenix.controls.JFXComboBox;
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
public class ActualizarActividadController implements Initializable {

    private TextField txTipoActividad;
    @FXML
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
    private JFXComboBox<Actividad> cbActividades;
    @FXML
    private Button btActualizar;
    @FXML
    private Button btAtrás;
    private ConexionBD conexion;
    private ObservableList<Actividad> actividades = FXCollections.observableArrayList();
    private Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ComboBox<String> cbTipoActividad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarDatos() {
        actividades = conexion.rellenarActividades();
        cbActividades.setItems(actividades);
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.add("Lugares");
        tipo.add("Restaurantes");
        tipo.add("Transportes");
        cbTipoActividad.setItems(tipo);
    }

    @FXML
    private void actualizarActividad(ActionEvent event) {
        boolean actualizado;
        TipoActividades tipo = TipoActividades.valueOf(cbTipoActividad.getSelectionModel().getSelectedItem().toUpperCase());
        cbActividades.getSelectionModel().getSelectedItem().setDescripcion(txDescripcion.getText());
        cbActividades.getSelectionModel().getSelectedItem().setDireccion(txDireccion.getText());
        cbActividades.getSelectionModel().getSelectedItem().setImagen(txRutaImagen.getText());
        cbActividades.getSelectionModel().getSelectedItem().setObservacion(txaObservacion.getText());
        cbActividades.getSelectionModel().getSelectedItem().setSubtipo(txSubTipo.getText());
        cbActividades.getSelectionModel().getSelectedItem().setTipoActividad(tipo);
        cbActividades.getSelectionModel().getSelectedItem().setURL(txURL.getText());
        actualizado = conexion.modificarActividad(cbActividades.getSelectionModel().getSelectedItem());
        if (actualizado == true) {
            alerta.setTitle("Modificacion correcta");
            alerta.setContentText("La actividad se ha modificado satisfactoriamente");
            alerta.showAndWait();
        } else {
            alerta.setTitle("Modificacion incorrecta");
            alerta.setContentText("No hemos podido modificar su actividad");
            alerta.showAndWait();
        }
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/crearpaquetes/CREARPAQUETES.fxml"));
        root = loader.load();
        CREAREXPERIENCIASController datosCrearPaquetes = loader.getController();
        datosCrearPaquetes.setConexion(conexion);
        datosCrearPaquetes.cargarDatos();
        Stage escenarioVentana = (Stage) btAtrás.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void rellenarTextFild(ActionEvent event) {
        for (Actividad actividade : actividades) {
            if (actividade.getIdActividad() == cbActividades.getSelectionModel().getSelectedItem().getIdActividad()) {
                txDescripcion.setText(cbActividades.getSelectionModel().getSelectedItem().getDescripcion());
                txDireccion.setText(cbActividades.getSelectionModel().getSelectedItem().getDireccion());
                txRutaImagen.setText(cbActividades.getSelectionModel().getSelectedItem().getImagen());
                txSubTipo.setText(cbActividades.getSelectionModel().getSelectedItem().getSubtipo());
                cbTipoActividad.getSelectionModel().select(cbActividades.getSelectionModel().getSelectedItem().getTipoActividad().toString());
                txURL.setText(cbActividades.getSelectionModel().getSelectedItem().getURL());
                txaObservacion.setText(cbActividades.getSelectionModel().getSelectedItem().getObservacion());
            }
        }
    }
}
