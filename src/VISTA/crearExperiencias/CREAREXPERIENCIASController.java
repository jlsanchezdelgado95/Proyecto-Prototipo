/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.crearExperiencias;

import DATOS.ConexionBD;
import MODELO.Actividad;
import MODELO.Experiencia;
import MODELO.tipoOrigen;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kokek
 */
public class CREAREXPERIENCIASController implements Initializable {

    @FXML
    private JFXButton btAtras;
    @FXML
    private Pane PanelLateral;
    @FXML
    private JFXComboBox<String> cbTipoActividad;
    @FXML
    private TableColumn<?, ?> tcSubtipo;
    @FXML
    private TableColumn<?, ?> tcNombre;
    @FXML
    private TableColumn<?, ?> tcUrl;
    @FXML
    private ImageView imgView;
    @FXML
    private TextArea txaDescripcion;
    @FXML
    private JFXButton btInsertar;
    @FXML
    private JFXButton btBorrar;
    @FXML
    private JFXButton btModificar;
    private ConexionBD conexion;
    private ObservableList<Actividad> actividades = FXCollections.observableArrayList();
    @FXML
    private TableView<Actividad> tvActividades;
    @FXML
    private TableView<Actividad> tvExperiencia;
    @FXML
    private TableColumn<?, ?> tcExpTipo;
    @FXML
    private TableColumn<?, ?> tcExpNombre;
    @FXML
    private TableColumn<?, ?> tcExpURL;
    private ObservableList<Actividad> listaExpAct = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> tcExpSubtipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarDatos() {
        String lugar = "Lugares";
        String restaurantes = "Restaurantes";
        String transporte = "Transportes";
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.add(lugar);
        tipo.add(restaurantes);
        tipo.add(transporte);
        cbTipoActividad.setItems(tipo);
        if (conexion.esAdministrador() == true) {
            btInsertar.setVisible(true);
            btModificar.setVisible(true);
            btBorrar.setVisible(true);
        }
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/menuprincipal/MenuPrincipal.fxml"));
        root = loader.load();
        MenuPrincipalController datosLogin = loader.getController();
        datosLogin.setConexion(conexion);
        Stage escenarioVentana = (Stage) btAtras.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }//BOTON ATRAS

    @FXML
    private void InsertarActividad(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/crearpaquetes/insertActividad.fxml"));
        root = loader.load();
        InsertActividadController datosInsertar = loader.getController();
        datosInsertar.setConexion(conexion);
        datosInsertar.cargarDatos();
        Stage escenarioVentana = (Stage) btInsertar.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }//VENTANA INSERTAR ACTIVIDAD

    @FXML
    private void ModificarActividad(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/crearpaquetes/ActualizarActividad.fxml"));
        root = loader.load();
        ActualizarActividadController datosActividad = loader.getController();
        datosActividad.setConexion(conexion);
        datosActividad.cargarDatos();
        Stage escenarioVentana = (Stage) btModificar.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }//VENTANA MODIFICAR ACTIVIDAD

    @FXML
    private void borrarActividad(ActionEvent event) {
        conexion.borrarActividad(tvActividades.getSelectionModel().getSelectedItem().getIdActividad());
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void mostrarActividades(ActionEvent event) {
        actividades = conexion.filtrarActividadPorTipo(cbTipoActividad.getSelectionModel().getSelectedItem());
        tvActividades.setItems(actividades);
        tcSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcUrl.setCellValueFactory(new PropertyValueFactory<>("URL"));
    }

    @FXML
    private void mostrarDescripcion(MouseEvent event) {
        txaDescripcion.clear();
        String mensaje = tvActividades.getSelectionModel().getSelectedItem().getObservacion();
        if (mensaje != null) {
            txaDescripcion.appendText(mensaje);
        }
    }

    @FXML
    private void anyadirActividadEnExperiencia(ActionEvent event) throws SQLException {
        listaExpAct.add(conexion.filtrarActividadPorId(tvActividades.getSelectionModel().getSelectedItem().getIdActividad()));
        tvExperiencia.setItems(listaExpAct);
        tcExpNombre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcExpTipo.setCellValueFactory(new PropertyValueFactory<>("tipoActividad"));
        tcExpSubtipo.setCellValueFactory(new PropertyValueFactory<>("Subtipo"));
        tcExpURL.setCellValueFactory(new PropertyValueFactory<>("URL"));
    }

    @FXML
    private void eliminarActividadDeExperiencia(ActionEvent event) {
        listaExpAct.remove(tvExperiencia.getSelectionModel().getSelectedItem());
        tvActividades.setItems(listaExpAct);
    }
//    public Experiencia(int idUsuario, double presupuesto, LocalDate fechaContratacion, LocalDate fechaFin, tipoOrigen origen) {
    @FXML
    private void guardarExperiencia(ActionEvent event) throws SQLException {
        conexion.insertarExperiencia(conexion.getUser().getUser().getIdUsuario(), 0, 0, LocalDate.MIN, LocalDate.MIN, tipoOrigen.USUARIO);
    }

}
