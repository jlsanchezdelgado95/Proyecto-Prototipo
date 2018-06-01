package VISTA.login;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import VISTA.registro.REGISTROController;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author 53752269g
 */
public class LOGINController implements Initializable {

    @FXML
    private Pane PanelLateral;
    @FXML
    private Pane login;
    @FXML
    private Button btRegistrarse;
    @FXML
    private Button btEntrar;
    @FXML
    private JFXTextField txNombreUsuario;
    @FXML
    private JFXPasswordField txContraseña;
    private ConexionBD conn;
    Alert alerta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void entrarAplicacion(ActionEvent event) {
        Parent root;
        try {
            if (conn.loginUsuario(txNombreUsuario.getText(), txContraseña.getText()) == true) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/menuprincipal/MenuPrincipal.fxml"));
                root = loader.load();
                MenuPrincipalController datosLogin = loader.getController();
                datosLogin.setConexion(conn);
                Stage escenarioVentana = (Stage) btEntrar.getScene().getWindow();
                escenarioVentana.hide();
                Stage escenario = new Stage();
                Image icono = new Image(this.getClass().getResource("/IMAGENES/icono.png").toString());
                escenario.getIcons().add(icono);
                escenario.setTitle("Ventana de elección");
                escenario.resizableProperty().setValue(Boolean.FALSE);
                escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
                escenario.setScene(new Scene(root));
                escenario.showAndWait();
            } else {
                alerta = new Alert(AlertType.CONFIRMATION);
                alerta.setTitle("Login no valido");
                alerta.setHeaderText("Registrese por favor");
                alerta.setContentText("Para registrase pulse el boton registrar gracias");
                alerta.showAndWait();
            }
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

    @FXML
    private void registrarse(ActionEvent event) {
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/registro/REGISTRO.fxml"));
            root = loader.load();
            REGISTROController datosLogin = loader.getController();
            datosLogin.setConexion(conn);
            datosLogin.cargarDatos();
            Stage escenario = new Stage();
            escenario.setTitle("Ventana de Registro");
            escenario.resizableProperty().setValue(Boolean.FALSE);
            escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
            escenario.setScene(new Scene(root));
            escenario.showAndWait();
        } catch (IOException ex) {
            System.out.println("ERROR IOExcepction:  No se encuentra la ventana de login");
        }
    }

    public void setConn(ConexionBD conn) {
        this.conn = conn;
    }

}
