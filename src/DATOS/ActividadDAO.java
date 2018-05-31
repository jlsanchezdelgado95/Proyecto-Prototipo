/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Actividad;
import MODELO.TipoActividades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ActividadDAO {

    private Connection conn;

    public ActividadDAO(Connection conn) {
        this.conn = conn;
    }

    //INSERTAR, MODIFICAR, BORRAR, FILTRAR, LISTAR...W
    public int insertarActividad(String tipoActividad, String subtipo, String descripcion, String observacion, String url, String rutaImagen, String direccion) throws SQLException {
        int filas = 0;
        String sql = ("insert into " + " actividades(tipoActividad,subtipo,descripcion, observacion,URL,rutaImagen,direccion) "
                + " values(?,?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tipoActividad);
        ps.setString(2, subtipo);
        ps.setString(3, descripcion);
        ps.setString(4, observacion);
        ps.setString(5, url);
        ps.setString(6, rutaImagen);
        ps.setString(7, direccion);
        filas = ps.executeUpdate();//Se ejecuta el insert
        return filas;
    }

    public int borrarActividad(int idActividad) throws SQLException {
        int filas = 0;
        String sql = ("delete from actividades where idActividad = ?");
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setInt(1, idActividad);
        filas = ps.executeUpdate();//Se ejecuta el delete
        return filas;
    }

    public int modificarActividad(Actividad a) {
        int filas = 0;
        String sql = ("update actividades set tipoActividad=?,subtipo=?,direccion=?,rutaImagen=?,URL=?,descripcion=?,observacion=? where idActividad=?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTipoActividad().toString());
            ps.setString(2, a.getSubtipo());
            ps.setString(3, a.getDireccion());
            ps.setString(4, a.getImagen());
            ps.setString(5, a.getURL());
            ps.setString(6, a.getDescripcion());
            ps.setString(7, a.getObservacion());
            ps.setInt(8, a.getIdActividad());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filas;
    }

    public ObservableList<Actividad> listarTodasActividades() throws SQLException {//idActividad, tipo, subtipo, descripcion y observacion
        ObservableList<Actividad> lista = FXCollections.observableArrayList();
        String tipoActividad, subTipo, descripcion, observacion, url;
        Integer idActividad;
        String sql = ("select *" + " from actividades");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {//Siguiente fila
            idActividad = rs.getInt("idActividad");
            tipoActividad = rs.getString("tipoActividad");
            subTipo = rs.getString("subtipo");
            descripcion = rs.getString("descripcion");
            observacion = rs.getString("observacion");
            url = rs.getString("URL");
            TipoActividades tipo = TipoActividades.valueOf(tipoActividad.toUpperCase());
            Actividad act = new Actividad(idActividad, tipo, subTipo, descripcion, observacion, url);
            lista.add(act);
        }
        return lista;
    }

    public Actividad filtrarActividadesPorId(int idActividad) throws SQLException {
        Actividad act = null;
        String tipoActividad, subTipo, descripcion, observacion, url;
        Integer idActividadMetodo;
        String sql = ("select *" + " from actividades"
                + " where idActividad = ?");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idActividad);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {//Siguiente fila
            idActividadMetodo = rs.getInt("idActividad");
            tipoActividad = rs.getString("tipoActividad");
            subTipo = rs.getString("subtipo");
            descripcion = rs.getString("descripcion");
            observacion = rs.getString("observacion");
            url = rs.getString("URL");
            TipoActividades tipo = TipoActividades.valueOf(tipoActividad.toUpperCase());//tengo que devolver solo un objeto Actividad
             act = new Actividad(idActividadMetodo, tipo, subTipo, descripcion, observacion, url);
        }
        return act;
    }

    public ObservableList filtrarActividadesPorTipo(String tipoMetodo) throws SQLException {
        ObservableList<Actividad> lista = FXCollections.observableArrayList();
        String tipoActividad, subTipo, descripcion, observacion, url;
        Integer idActividadMetodo;
        String sql = ("select *" + " from actividades"
                + " where tipoActividad = ? order by subtipo");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tipoMetodo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {//Siguiente fila
            idActividadMetodo = rs.getInt("idActividad");
            tipoActividad = rs.getString("tipoActividad");
            subTipo = rs.getString("subtipo");
            descripcion = rs.getString("descripcion");
            observacion = rs.getString("observacion");
            url = rs.getString("URL");
            TipoActividades tipo = TipoActividades.valueOf(tipoActividad.toUpperCase());
            Actividad act = new Actividad(idActividadMetodo, tipo, subTipo, descripcion, observacion, url);
            lista.add(act);
        }
        return lista;
    }

    //GETS Y SETS
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
