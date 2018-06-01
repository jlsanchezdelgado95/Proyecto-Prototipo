/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.calendario;

import DATOS.ConexionBD;
import MODELO.Evento;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CALENDARIOController implements Initializable {

    @FXML
    private ListView<Evento> lvEventos;
    private ObservableList<Evento> eventos = FXCollections.observableArrayList();
    @FXML
    private DatePicker date;
    private ConexionBD conn;
    @FXML
    private JFXButton btAtras;
    private Alert alerta = new Alert(AlertType.CONFIRMATION);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cargarDatos() {

        eventos = conn.cargarEventos();

        lvEventos.setItems(eventos);

    }

    @FXML
    private void seleccionCalendario(ActionEvent event) {

        ObservableList<Evento> eventosFiltrados = FXCollections.observableArrayList();

        if (conn.filtrarEventosFecha(date.getValue()).isEmpty() == false) {
            eventosFiltrados= conn.filtrarEventosFecha(date.getValue());

           
            lvEventos.setItems(eventosFiltrados);
        } else {
            alerta.setTitle("No hay eventos disponibles");
            alerta.setHeaderText("Prueba otro dia");
            alerta.setContentText("Lamentamos que no haya eventos este dia: " + date.getValue());
            alerta.showAndWait();

        }

    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/menuprincipal/MenuPrincipal.fxml"));
        root = loader.load();
        MenuPrincipalController datosMenu = loader.getController();
        datosMenu.setConexion(conn);
        Stage escenarioVentana = (Stage) btAtras.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }

    //GETS Y SETS
    public ConexionBD getConn() {
        return conn;
    }

    public void setConn(ConexionBD conn) {
        this.conn = conn;
    }

}
