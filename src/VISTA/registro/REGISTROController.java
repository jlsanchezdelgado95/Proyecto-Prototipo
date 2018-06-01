/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.registro;

import DATOS.ConexionBD;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author 53752269g
 */
public class REGISTROController implements Initializable {

    @FXML
    private Button btResgistrarse;
    @FXML
    private PasswordField txContraseña;
    private ConexionBD conexion;
    private Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private RadioButton btEfectivo;
    @FXML
    private RadioButton btVisa;
    @FXML
    private RadioButton btPaypal;
    @FXML
    private RadioButton btBitcoin;
    @FXML
    private ToggleGroup pagar;
    @FXML
    private TextField txNombreUsuario;
    @FXML
    private TextField txNombre;

    @FXML

    private JFXTextField texApellidos;
    @FXML
    private JFXTextField texCorreo;
    @FXML
    private JFXTextField texDireccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarDatos() {
        btBitcoin.setUserData("Bitcoin");
        btEfectivo.setUserData("Efectivo");
        btPaypal.setUserData("Paypal");
        btVisa.setUserData("Visa");

    }

    @FXML
    private void registro(ActionEvent event) throws SQLException {
        boolean registrado = false;
        try {

       

            registrado = conexion.insertarUsuario(txNombreUsuario.getText(), txContraseña.getText(), txNombre.getText(), texApellidos.getText(), texDireccion.getText(),texCorreo.getText(),pagar.getSelectedToggle().getUserData().toString());
            if (registrado == true) {
                alerta.setTitle("Se ha registrado correctamente");
                alerta.setHeaderText("Gracias por registrarse en nuestra aplicaion");
                alerta.setContentText("Le redirigimos al menu principal");
                alerta.showAndWait();
                btResgistrarse.getScene().getWindow().hide();

            } else {

                alerta.setTitle("Lo sentimos no se ha podido registrar");
                alerta.setHeaderText("Intetelo de nuevo");
                alerta.setContentText("Por favor rellene todos los campos que solicitamos");
                alerta.showAndWait();

            }
        } catch (NullPointerException e) {
            alerta.setTitle("Lo sentimos no se ha podido registrar2");
            alerta.setHeaderText("Intetelo de nuevo");
            alerta.setContentText("Por favor rellene todos los campos que solicitamos");
            alerta.showAndWait();

        }
    }

    public void setConexion(ConexionBD conexion) {
        this.conexion = conexion;
    }

}
