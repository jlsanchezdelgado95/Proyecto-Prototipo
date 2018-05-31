package INICIO;

import DATOS.ConexionBD;
import VISTA.login.LOGINController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class INICIO extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/VISTA/login/LOGIN.fxml"));
        root = loader.load(); 
        ConexionBD conn = new ConexionBD();
        
        LOGINController datosLogin = loader.getController();
        datosLogin.setConn(conn);
        Image icono =  new Image(this.getClass().getResource("/IMAGENES/icono.png").toString());
        stage.getIcons().add(icono);
        stage.resizableProperty().setValue(Boolean.FALSE);
        
//        int verdadero = WindowEvent.WINDOW_STATE_CHANGED;
//        if (verdadero == 1) {
//            stage.setFullScreen(true);
//        } else if (verdadero == 0) {
//            stage.setMaxHeight(verdadero);
//        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
