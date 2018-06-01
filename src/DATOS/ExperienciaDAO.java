/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Experiencia;
import MODELO.tipoOrigen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kokekui
 */
public class ExperienciaDAO {

    private Connection conn;

    public ExperienciaDAO(Connection conn) {
        this.conn = conn;
    }

    //CRUD
    public int insertarExperiencia(int idExperiencia, int idUsuario, double presupuesto, LocalDate fechaContratacion, LocalDate fechaFin, tipoOrigen origen) throws SQLException {
        int filas = 0;
        String sql = ("insert into " + " experiencias(idExperiencia,idUsuario,presupuesto, fechaContratacion,fechaFin,origen) "
                + " values(?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idExperiencia);
        ps.setInt(2, idUsuario);
        ps.setDouble(3, presupuesto);
        ps.setDate(4, Date.valueOf(fechaContratacion));
        ps.setDate(5, Date.valueOf(fechaFin));
        ps.setString(6, String.valueOf(origen));
        filas = ps.executeUpdate();
        return filas;
    }

    public int borrarExperiencia(int idExperiencia) throws SQLException {
        int filas = 0;
        String sql = ("delete from experiencias where idExperiencia = ?");
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setInt(1, idExperiencia);
        filas = ps.executeUpdate();//Se ejecuta el delete
        return filas;
    }

    public int modificarExpActividad(Experiencia e) {
        int filas = 0;
        String sql = ("update experiencias set idUsuario=?,presupuesto=?,fechaContratacion=?,fechaFin=?,origen=?"
                + " where idExperiencia=?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getIdUsuario());
            ps.setDouble(2, e.getPresupuesto());
            ps.setDate(3, Date.valueOf(e.getFechaContratacion()));
            ps.setDate(4, Date.valueOf(e.getFechaFin()));
            ps.setString(5, String.valueOf(e.getOrigen()));
            ps.setInt(6, e.getIdExperiencia());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filas;
    }

    public int maximoIdExp() throws SQLException {
        int maximoId = 0;
        String sql = ("select max(idExperiencia) from experiencias");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.first();
        maximoId = rs.getInt("idExperiencia");
        return maximoId;
    }

    public ObservableList<Experiencia> listarTodasExpAct() throws SQLException {//idActividad, tipo, subtipo, descripcion y observacion
        ObservableList<Experiencia> lista = FXCollections.observableArrayList();
        Integer idExperiencia, idUsuario;
        double presupuesto;
        LocalDate fechaContratacion, fechaFin;
        tipoOrigen origen;
        String sql = ("select *" + " from experiencias");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {//Siguiente fila
            idExperiencia = rs.getInt("idExperiencia");
            idUsuario = rs.getInt("idUsuario");
            presupuesto = rs.getDouble("presupuesto");
            fechaContratacion = rs.getDate("fechaContratacion").toLocalDate();
            fechaFin = rs.getDate("fechaFin").toLocalDate();
            origen = tipoOrigen.valueOf(rs.getString("origen"));
            Experiencia exp = new Experiencia(idUsuario, presupuesto, fechaContratacion, fechaFin, origen);
            exp.setIdExperiencia(idExperiencia);
            lista.add(exp);
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
