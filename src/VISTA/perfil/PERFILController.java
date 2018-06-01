/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.perfil;

import DATOS.ConexionBD;
import VISTA.menuprincipal.MenuPrincipalController;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kokek
 */
public class PERFILController implements Initializable {

    @FXML
    private JFXButton btAtras;
    @FXML
    private JFXButton btModificar;
    private ConexionBD conn;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellidos;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbDireccion;
    @FXML
    private TextField txNombre;
    @FXML
    private TextField txDireccion;
    @FXML
    private TextField txApellidos;
    @FXML
    private TextField txCorreo;
    @FXML
    private RadioButton rbEfectivo;
    @FXML
    private ToggleGroup formaPago;
    @FXML
    private RadioButton rbVisa;
    @FXML
    private RadioButton rbPaypal;
    @FXML
    private RadioButton rbBitcoin;
    private Alert alerta = new Alert(AlertType.WARNING);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarDatos() {
        lbNombre.setText(conn.getUser().getNombre());
        lbApellidos.setText(conn.getUser().getApellidos());
        lbDireccion.setText(conn.getUser().getDireccion());
        lbCorreo.setText(conn.getUser().getCorreo());
        rbBitcoin.setUserData("Bitcoin");
        rbEfectivo.setUserData("Efectivo");
        rbPaypal.setUserData("Paypal");
        rbVisa.setUserData("Visa");
        if(conn.getUser().getFormaPago().equalsIgnoreCase("Bitcoin")){
        rbBitcoin.setSelected(true);
        }
        if(conn.getUser().getFormaPago().equalsIgnoreCase("Efectivo")){
        rbEfectivo.setSelected(true);
        }
        if(conn.getUser().getFormaPago().equalsIgnoreCase("Paypal")){
        rbPaypal.setSelected(true);
        }
        if(conn.getUser().getFormaPago().equalsIgnoreCase("Visa")){
        rbVisa.setSelected(true);
        }
    }

    @FXML
    private void irAtras(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/menuprincipal/MenuPrincipal.fxml"));
        root = loader.load();
        MenuPrincipalController datosLogin = loader.getController();
        datosLogin.setConexion(conn);
        Stage escenarioVentana = (Stage) btAtras.getScene().getWindow();
        escenarioVentana.setTitle("Ventana de elección");
        escenarioVentana.setScene(new Scene(root));
    }

    @FXML
    private void modificarPerfil(ActionEvent event) throws SQLException {
        try {
            conn.actualizarUsuario(txNombre.getText(), txApellidos.getText(), formaPago.getSelectedToggle().getUserData().toString(),txDireccion.getText(),txCorreo.getText() ,conn.getUser().getIdUsuario());
            conn.loginUsuario(conn.getUser().getNombreUsuario(), conn.getUser().getContrasenya());
            cargarDatos();
        } catch (NullPointerException e) {
           alerta.setTitle("Debe seleccionar una forma de pago por favor");
           alerta.setHeaderText("Pulse una de las opciones de abajo");
        }
    }

    public ConexionBD getConn() {
        return conn;
    }

    public void setConn(ConexionBD conn) {
        this.conn = conn;
    }

}
