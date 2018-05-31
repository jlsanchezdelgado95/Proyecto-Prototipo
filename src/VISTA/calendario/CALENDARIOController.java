/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.calendario;

import DATOS.ConexionBD;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CALENDARIOController implements Initializable {

    @FXML
    private ListView<String> lvEventos;
    private ObservableList<String> eventos = FXCollections.observableArrayList();
    private DatePicker date;
    private ConexionBD conn;
    @FXML
    private JFXButton btAtras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cargarDatos() {
        String evento;
        for (int i = 1; i <= 20; i++) {
            evento = "nombre evento" + i + "    " + "Direccion: " + "          " + "Descripción: ";
            eventos.add(evento);
        }
        lvEventos.setItems(eventos);

    }

    private void seleccionCalendario(ActionEvent event) {
        LocalDate dateHoy = LocalDate.now();
        if (dateHoy.equals(date.getValue())) {
            cargarDatos();
        } else {
            lvEventos.getItems().clear();
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
