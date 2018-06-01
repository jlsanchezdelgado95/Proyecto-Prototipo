package VISTA.menuprincipal;

import DATOS.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import VISTA.calendario.CALENDARIOController;
import VISTA.crearExperiencias.CREAREXPERIENCIASController;
import VISTA.login.LOGINController;
import VISTA.paquetescontratados.PAQUETESCONTRATADOSController;
import VISTA.perfil.PERFILController;
import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btPaquetesContratado;
    @FXML
    private Button btPaquetes;
    @FXML
    private Button btCalendario;
    @FXML
    private Button btPerfil;
    private ConexionBD conexion;
    @FXML
    private JFXButton btDesconectar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void paquetesContratados(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/paquetescontratados/PAQUETESCONTRATADOS.fxml"));
            root = loader.load();
            PAQUETESCONTRATADOSController datosCrearPaquetes = loader.getController();
            datosCrearPaquetes.setConexion(conexion);
            Stage escenarioVentana = (Stage) btPaquetesContratado.getScene().getWindow();
            escenarioVentana.setTitle("Paquetes contratados");
            escenarioVentana.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

    @FXML
    private void mostrarPaquetes(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/crearExperiencias/CREAREXPERIENCIAS.fxml"));
            root = loader.load();
            CREAREXPERIENCIASController datosCrearPaquetes = loader.getController();
            datosCrearPaquetes.setConexion(conexion);
            datosCrearPaquetes.cargarDatos();
            Stage escenarioVentana = (Stage) btPaquetesContratado.getScene().getWindow();
            escenarioVentana.setTitle("Creacion de Paquetes");
            escenarioVentana.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

    @FXML
    private void mostrarCalendario(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/calendario/CALENDARIO.fxml"));
            root = loader.load();
            CALENDARIOController datosCalendario = loader.getController();
            datosCalendario.setConn(conexion);
            datosCalendario.cargarDatos();
            Stage escenarioVentana = (Stage) btPaquetesContratado.getScene().getWindow();
            escenarioVentana.setTitle("Calendario Eventos");
            escenarioVentana.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

@FXML
    private void mostrarPerfil(ActionEvent event) {
     Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/perfil/PERFIL.fxml"));
            root = loader.load();
            PERFILController datosLogin = loader.getController();
            datosLogin.setConn(conexion);
            datosLogin.cargarDatos();
            Stage escenarioVentana = (Stage) btPerfil.getScene().getWindow();
            escenarioVentana.setTitle("Perfil");
            escenarioVentana.resizableProperty().setValue(Boolean.FALSE);
            escenarioVentana.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void desconectarse(ActionEvent event) {
        Parent root;
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/login/LOGIN.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            LOGINController datosLogin = loader.getController();
            datosLogin.setConn(this.conexion);
            Stage escenarioVentana = (Stage) btDesconectar.getScene().getWindow();
            escenarioVentana.hide();
            Image icono = new Image(this.getClass().getResource("/IMAGENES/icono.png").toString());
            escenario.getIcons().add(icono);
            escenario.setTitle("Login");
            escenario.setScene(scene);
            escenario.show();
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }
}
