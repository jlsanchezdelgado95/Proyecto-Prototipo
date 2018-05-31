/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Evento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 53253095e
 */
public class EventoDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private List<Evento> Listaeventos = new ArrayList<>();

    public EventoDAO(Connection conn) throws SQLException {
        this.conn = conn;
        RellenarListaEventos();
    }

    public void RellenarListaEventos() throws SQLException {
        String instruccionSQL = "SELECT * FROM eventos";
        LocalDate fechajava;
        ps = conn.prepareStatement(instruccionSQL);
        rs = ps.executeQuery();
        java.sql.Date fechabda = rs.getDate("fecha");
        fechajava = fechabda.toLocalDate();
        Evento evento = new Evento(fechajava, rs.getString("Nombre"));
        Listaeventos.add(evento);

    }

    public List filtrarEvento(LocalDate fecha) {
        List<Evento> listaFiltrada = new ArrayList<>();
        for (Evento Listaevento : Listaeventos) {
            if (Listaevento.getFecha().equals(fecha)) {
                listaFiltrada.add(Listaevento);
            }
        }

        return listaFiltrada;

    }

    public boolean UpdateEventos(LocalDate fecha, String nombre, String descripcion) throws SQLException {
        boolean confirmado = false;
        String instruccionSQL = "UPDATE eventos SET fecha=? nombre=? descripcion=?";

        ps = conn.prepareStatement(instruccionSQL);
        ps.setDate(1, Date.valueOf(fecha));
        ps.setString(2, nombre);
        ps.setString(3, descripcion);
        if (ps.executeUpdate() > 1) {
            confirmado = true;
        }
        return confirmado;
    }

    public boolean InsertarEventos(LocalDate fecha, String nombre, String descripcion) throws SQLException {
        boolean confirmado = false;
        String instruccionSQL = "INSERT INTO eventos VALUES(?,?,?)";

        ps = conn.prepareStatement(instruccionSQL);
        ps.setDate(1, Date.valueOf(fecha));
        ps.setString(2, nombre);
        ps.setString(3, descripcion);
        if (ps.executeUpdate() > 1) {
            confirmado = true;
        }
        return confirmado;
    }

    public boolean BorrarEventos(LocalDate fecha, String nombre) throws SQLException {
        boolean confirmado = false;
        String instruccionSQL = "DELETE FROM EVENTOS WHERE fecha=? and nombre=?";

        ps = conn.prepareStatement(instruccionSQL);
        ps.setDate(1, Date.valueOf(fecha));
        ps.setString(2, nombre);
        
        if (ps.executeUpdate() > 1) {
            confirmado = true;
        }
        return confirmado;
    }

   
}
