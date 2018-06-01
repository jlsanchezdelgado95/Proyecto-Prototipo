package DATOS;

import MODELO.Actividad;
import MODELO.Evento;
import MODELO.Experiencia;
import MODELO.ExperienciaActividad;
import MODELO.Usuario;
import MODELO.tipoOrigen;
import VISTA.login.LOGINController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConexionBD {

    private Connection conn;
    private final String usuario = "root";
    private final String password = "root";
    private ActividadDAO act;
    private EventoDAO event;
    private UsuariosDAO user;
    private ExperienciaActividadDAO expAct;
    private ExperienciaDAO exp;

// RESTO DE METODOS CUANDO SE ACLARE LA BD
    public ConexionBD() throws SQLException {
        conexion();
        user = new UsuariosDAO(conn);
        act = new ActividadDAO(conn);
        event = new EventoDAO(conn);
        exp = new ExperienciaDAO(conn);
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

    //CRUD ACTIVIDADES
    public boolean insertarActividad(String tipoActividad, String subtipo, String descripcion, String observacion, String url, String rutaImagen, String direccion, double precio) {
        boolean ok = false;
        int filas = 0;
        try {
            filas = act.insertarActividad(tipoActividad, subtipo, descripcion, observacion, url, rutaImagen, direccion, precio);
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
        return act.filtrarActividadPorId(idActividad);
    }

    public ObservableList filtrarExperienciaActividadPorId(int idExperienciaActividad) {
        ObservableList<ExperienciaActividad> lista = FXCollections.observableArrayList();
        try {
            lista = expAct.filtrarExperienciaActividadPorId(idExperienciaActividad);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
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
    //EXPACTIVIDAD
    //    public int insertarExpActividad(int idExperiencia, int numOrden, int idActividad, LocalDate fechaComienzo, LocalDate fechaFinal, int numeroPlazas,
    //double precio, LocalTime duracion) throws SQLException {
    public boolean insertaExpActividad(int idExperiencia, int idActividad, LocalDate fechaComienzo, LocalDate fechaFinal, int numeroPlazas, double precio, LocalTime duracion) throws SQLException {
        boolean ok = false;
        expAct.insertarExpActividad(idExperiencia, idActividad, fechaComienzo, fechaFinal, numeroPlazas, precio, duracion);
        return ok;
    }

    public ObservableList rellenarExperienciasActividades(int idUsuario) {
        ObservableList<ExperienciaActividad> lista = FXCollections.observableArrayList();
        try {
            lista = expAct.listarTodasExpAct();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
       public boolean borrarExperienciaActividad(int idExperiencia) {
        boolean correcto = false;
        int filas = 0;
        try {
            filas = expAct.borrarExpActividad(idExperiencia);
            if (filas > 0) {
                correcto = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;

    }

    //EXPERIENCIA
    // public int insertarExperiencia(int idExperiencia, int idUsuario, double presupuesto, LocalDate fechaContratacion, LocalDate fechaFin, tipoOrigen origen) throws SQLException {
    public boolean insertarExperiencia(int idUsuario, double presupuesto, LocalDate fechaContratacion, LocalDate fechaFin, tipoOrigen origen) throws SQLException {
        boolean ok = false;
        exp.insertarExperiencia(idUsuario, presupuesto, fechaContratacion, fechaFin, origen);
        return ok;
    }

    public boolean esAdministrador() {
        boolean esAdministrador;

        esAdministrador = user.esAdministrador();
        return esAdministrador;

    }

    public ObservableList filtrarEventosFecha(LocalDate fecha) {
        ObservableList<Evento> lista = event.filtrarEvento(fecha);
        return lista;
    }

    public ObservableList cargarEventos() {
        ObservableList<Evento> lista = FXCollections.observableArrayList();
        lista = event.getListaeventos();
        return lista;
    }

    public boolean insertarUsuario(String nombreUsuario, String contraseña, String nombre, String apellidos, String direccion, String correo, String formaDePago) throws SQLException {
        return user.insertarUsuario(nombreUsuario, contraseña, nombre, apellidos, direccion, correo, formaDePago);
    }

    public boolean actualizarUsuario(String nombre, String apellidos, String formaDePago, String direccion, String correo, int idUsuario) throws SQLException {

        return user.actualizarUsuario(nombre, apellidos, formaDePago, direccion, correo, idUsuario);
    }

    public Usuario getUser() {
        return user.getUser();
    }

    public int getExperienciaId() throws SQLException {
        return exp.maximoIdExp();
    }

    public ExperienciaActividadDAO getExpAct() {
        return expAct;
    }

    public void setExpAct(ExperienciaActividadDAO expAct) {
        this.expAct = expAct;
    }

    public ExperienciaDAO getExp() {
        return exp;
    }

    public void setExp(ExperienciaDAO exp) {
        this.exp = exp;
    }
    

}
