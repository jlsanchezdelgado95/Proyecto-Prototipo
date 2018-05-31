/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.registro;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author 53752269g
 */
public class REGISTROController implements Initializable {
    
    private Label label;
    @FXML
    private Button btRegistrarse1;
    @FXML
    private Button btRegistrarse4;
    @FXML
    private Button btRegistrarse41;
    @FXML
    private Button btRegistrarse411;
    @FXML
    private Button btRegistrarse4111;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
