/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Actividad;
import MODELO.Experiencia;
import MODELO.ExperienciaActividad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kokekui
 */
public class ExperienciaActividadDAO {

    private Connection conn;

    public ExperienciaActividadDAO(Connection conn) {
        this.conn = conn;
    }
//public ExperienciaActividad(int idExperiencia, int idActividad, LocalDate fechaComienzo, LocalDate fechaFin, int numPlazas, double preciol, LocalTime duracion) {
//CRUD
    public int insertarExpActividad(int idExperiencia, int idActividad, LocalDate fechaComienzo, LocalDate fechaFinal, int numeroPlazas,
            double precio, LocalTime duracion) throws SQLException {
        int filas = 0;
        String sql = ("insert into " + " experiencias_actividades(idExperiencia,numOrden,idActividad, fechaComienzo,fechaFinal,numeroPlazas,precio,duracion) "
                + " values(?,?,?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idExperiencia);
        ps.setInt(2, idActividad);
        ps.setDate(3, Date.valueOf(fechaComienzo));
        ps.setDate(4, Date.valueOf(fechaFinal));
        ps.setInt(5, numeroPlazas);
        ps.setDouble(6, precio);
        ps.setTime(7, Time.valueOf(duracion));
        filas = ps.executeUpdate();
        Actividad act = new Actividad();
        Experiencia exp = new Experiencia();
        ExperienciaActividad expAct = new ExperienciaActividad(idExperiencia, idActividad, fechaComienzo, fechaFinal, numeroPlazas, precio, duracion);
        exp.añadirListaExpAct(expAct);//LOS AÑADO A LAS LISTAS DE LAS CLASES
        act.añadirExpAct(expAct);
        return filas;
    }

    public int borrarExpActividad(int idExperiencia) throws SQLException {
        int filas = 0;
        String sql = ("delete from experiencias_actividades where idExperiencia = ?");
        PreparedStatement ps = conn.prepareCall(sql);
        ps.setInt(1, idExperiencia);
        filas = ps.executeUpdate();//Se ejecuta el delete
        return filas;
    }

    public int modificarExpActividad(ExperienciaActividad ea) {
        int filas = 0;
        String sql = ("update experiencias_actividades set numeroOrden=?,idActividad=?,fechaComienzo=?,fechaFinal=?,numeroPlazas=?,"
                + "precio=?, duracion = ? where idExperiencia=?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ea.getNumOrden());
            ps.setInt(2, ea.getIdActividad());
            ps.setDate(3, Date.valueOf(ea.getFechaComienzo()));
            ps.setDate(4, Date.valueOf(ea.getFechaFin()));
            ps.setInt(5, ea.getNumPlazas());
            ps.setDouble(6, ea.getPreciol());
            ps.setTime(7, Time.valueOf(ea.getDuracion()));
            ps.setInt(8, ea.getIdExperiencia());
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filas;
    }

    public ObservableList<ExperienciaActividad> listarTodasExpAct() throws SQLException {//idActividad, tipo, subtipo, descripcion y observacion
        ObservableList<ExperienciaActividad> lista = FXCollections.observableArrayList();
        String descripcion, observacion, url;
        Integer idExperiencia, tipoActividad, idActividad;
        String sql = ("select *" + " from experiencias_actividades");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {//Siguiente fila
            idExperiencia = rs.getInt("idExperiencia");
            tipoActividad = rs.getInt("numeroOrden");
            idActividad = rs.getInt("idActividad");
            ExperienciaActividad expAct = new ExperienciaActividad(idExperiencia, tipoActividad, idActividad);
            lista.add(expAct);
        }
        return lista;
    }

    public ObservableList filtrarExperienciaActividadPorId(int idExperienciaActividad) throws SQLException {
        ObservableList<ExperienciaActividad> lista = FXCollections.observableArrayList();
        int idExperiencia = 0, numOrden, idActividad, numPlazas;
        LocalDate fechaComienzo, fechaFin;
        LocalTime duracion;

        double preciol;

        String consulta = ("SELECT * FROM experiencias_actividades WHERE idExperiencia = ?");
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, idExperienciaActividad);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            idExperiencia = rs.getInt("idExperiencia");
            numOrden = rs.getInt("numeroOrden");
            idActividad = rs.getInt("idActividad");
            Date fechabda = rs.getDate("fechaCominezo");
            fechaComienzo = fechabda.toLocalDate();
            Date fechabda2 = rs.getDate("fechaFinal");
            fechaFin = fechabda2.toLocalDate();
            numPlazas = rs.getInt("numeroPlazas");
            preciol = rs.getDouble("precio");
            Time fechabda3 = rs.getTime("duracion");
            duracion = fechabda3.toLocalTime();
            ExperienciaActividad expeAct = new ExperienciaActividad(idExperiencia, idActividad, fechaComienzo, fechaFin, numPlazas, preciol, duracion);
            expeAct.setNumOrden(numOrden);
            System.out.println(expeAct);
            lista.add(expeAct);
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
