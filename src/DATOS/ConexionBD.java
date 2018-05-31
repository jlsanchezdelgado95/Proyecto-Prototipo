/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Actividad;
import VISTA.login.LOGINController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kokekui
 */
public class ConexionBD {

    private Connection conn;
    private final String usuario = "root";
    private final String password = "root";
    private ActividadDAO act;
    private EventoDAO event;
    private UsuariosDAO user;
    private ExperienciaActividadDAO expAct;

// RESTO DE METODOS CUANDO SE ACLARE LA BD
    public ConexionBD() throws SQLException {
        conexion();
        user = new UsuariosDAO(conn);
        act = new ActividadDAO(conn);
        expAct = new ExperienciaActividadDAO(conn);
    }

    public void conexion() {
        String urlJDBC = "jdbc:mysql://localhost:3306/bdatenas";
        try {
            conn = DriverManager.getConnection(urlJDBC, usuario, password);
            if (conn != null) {
                System.out.println("esta conectado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LOGINController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// GETS Y SETS

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean loginUsuario(String usuario, String contraseña) {
        boolean esta;
        esta = user.buscarUsuario(usuario, contraseña, this.conn);
        return esta;
    }

    public boolean insertarActividad(String tipoActividad, String subtipo, String descripcion, String observacion, String url, String rutaImagen, String direccion) {
        boolean ok = false;
        int filas = 0;
        try {
            filas = act.insertarActividad(tipoActividad, subtipo, descripcion, observacion, url, rutaImagen, direccion);
            if (filas > 0) {
                ok = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public boolean borrarActividad(int idActividad) {
        boolean ok = false;
        int filas = 0;
        try {
            filas = act.borrarActividad(idActividad);
            if (filas > 0) {
                ok = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public boolean modificarActividad(Actividad a) {
        boolean ok = false;
        int filas = 0;
        filas = act.modificarActividad(a);
        if (filas > 0) {
            ok = true;
        }
        return ok;
    }

    public ObservableList rellenarActividades() {
        ObservableList<Actividad> lista = FXCollections.observableArrayList();
        try {
            lista = act.listarTodasActividades();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Actividad filtrarActividadPorId(int idActividad) throws SQLException {
        return act.filtrarActividadesPorId(idActividad);
    }

    public ObservableList filtrarActividadPorTipo(String tipo) {
        ObservableList<Actividad> lista = FXCollections.observableArrayList();
        try {
            lista = act.filtrarActividadesPorTipo(tipo);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean esAdministrador() {
        boolean esAdministrador;

        esAdministrador = user.esAdministrador();
        return esAdministrador;

    }
}
