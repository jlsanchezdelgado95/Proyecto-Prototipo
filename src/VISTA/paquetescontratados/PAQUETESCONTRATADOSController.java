package VISTA.paquetescontratados;

import DATOS.ConexionBD;
import MODELO.Experiencia;
import MODELO.ExperienciaActividad;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PAQUETESCONTRATADOSController implements Initializable {

    @FXML
    private JFXButton btAtras;
    private ConexionBD conexion;
    @FXML
    private JFXComboBox<ExperienciaActividad> cbIdExperiencia;
    private ObservableList<ExperienciaActividad> experienciasActividades = FXCollections.observableArrayList();
    @FXML
    private Label lbPagina;
    @FXML
    private TableColumn<?, ?> tcIdActividad;
    @FXML
    private TableColumn<?, ?> tcFechaComienzo;
    @FXML
    private TableColumn<?, ?> tcFechaFin;
    @FXML
    private TableColumn<?, ?> tcNumeroPlazas;
    @FXML
    private TableColumn<?, ?> tcPrecio;
    @FXML
    private TableColumn<?, ?> tcDuracion;
    @FXML
    private TableView<ExperienciaActividad> tvExperienciaActividad;
    @FXML
    private JFXButton btBorrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cargarDatos() {
        experienciasActividades = conexion.rellenarExperienciasActividades(conexion.getUser().getIdUsuario());
        cbIdExperiencia.setItems(experienciasActividades);
        System.out.println(experienciasActividades);
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
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void mostarExperienciasActividad(ActionEvent event) {
        int idExperiencia;
        idExperiencia = cbIdExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia();
        experienciasActividades = conexion.filtrarExperienciaActividadPorId(idExperiencia);
        tvExperienciaActividad.setItems(experienciasActividades);
        tcIdActividad.setCellValueFactory(new PropertyValueFactory<>("idActividad"));
        tcFechaComienzo.setCellValueFactory(new PropertyValueFactory<>("fechaComienzo"));
        tcFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        tcNumeroPlazas.setCellValueFactory(new PropertyValueFactory<>("numPlazas"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("preciol"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

    }

    @FXML
    private void borrarExperiencia(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        boolean borrar = false;
        borrar = conexion.borrarExperienciaActividad(cbIdExperiencia.getSelectionModel().getSelectedItem().getIdExperiencia());

        if (borrar == true) {
            alerta.setTitle("Borrado correcta");
            alerta.setContentText("La Experiencia se ha borrado satisfactoriamente");
            alerta.showAndWait();
        }else{
            alerta.setTitle("Borrado Incorrecto");
            alerta.setContentText("No hemos podido borrar la Experiencia");
            alerta.showAndWait();
        }
    }

}
