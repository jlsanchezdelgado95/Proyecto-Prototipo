package DATOS;

import MODELO.Usuario;
import VISTA.perfil.PERFILController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.omg.PortableServer.IdAssignmentPolicyValue;

public class UsuariosDAO {

    private PreparedStatement ps;
    private Connection conn;
    private ResultSet rs;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario user;

    public UsuariosDAO(Connection conn) {
        this.conn = conn;
        listarUsuarios();
    }

    public void listarUsuarios() {
        try {
            ps = conn.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenya = rs.getString("contraseña");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String formaDePago = rs.getString("formaDePago");
                int administrador = rs.getInt("administrador");

                Usuario fullUser = new Usuario(nombreUsuario, contrasenya, nombre, apellidos, formaDePago, administrador);
                listaUsuarios.add(fullUser);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public boolean insertarUsuario(String nombreUsuario, String contraseña, String nombre, String apellidos, String formaDePago) throws SQLException {
        boolean insercion = false;
        String consulta = "INSERT INTO "
                + "usuarios (nombreUsuario, contraseña, nombre, apellidos, formaDePago)"
                + "VALUES (?,?,?,?,?)";

        ps = conn.prepareStatement(consulta);
        ps.setString(1, nombreUsuario);
        ps.setString(2, contraseña);
        ps.setString(3, nombre);
        ps.setString(4, apellidos);
        ps.setString(5, formaDePago);

        int numFilas = ps.executeUpdate();
        if (numFilas == 1 || numFilas > 1) {
            insercion = true;
        }
        return insercion;
    }

    public boolean eliminarUsuario() throws SQLException {
        boolean eliminacion = false;
        String consulta = "DELETE FROM usuarios"
                + " WHERE idUsuario = ?";

        ps = conn.prepareStatement(consulta);

        ps.setInt(1, Integer.parseInt(user.getIdUsuario()));

        int numFilas = ps.executeUpdate();
        if (numFilas == 1 || numFilas > 1) {
            eliminacion = true;
        }
        return eliminacion;

    }

    public boolean actualizarUsuario(String nombreUsuario, String contraseña, String nombre, String apellidos, String formaDePago, int idUsuario) throws SQLException {
        boolean actualizacion = false;
        String consulta = "UPDATE usuarios"
                + " SET nombreUsuario = ?"
                + " contraseña = ?"
                + " nombre = ?"
                + " apellidos = ?"
                + " formaDePago = ?"
                + "WHERE idUsuario = ?";

        ps = conn.prepareStatement(consulta);

        ps.setString(1, nombreUsuario);
        ps.setString(2, contraseña);
        ps.setString(3, nombre);
        ps.setString(4, apellidos);
        ps.setString(5, formaDePago);
        ps.setInt(6, idUsuario);
        int numFilas = ps.executeUpdate();
        if (numFilas == 1 || numFilas > 1) {
            actualizacion = true;
        }
        return actualizacion;
    }

    public boolean buscarUsuario(String nombreUsuario, String contraseña,Connection con) {
        boolean esta= false;
        String nombre;
        String apellidos;
        String formaPago;
        int administrador;
        try {
               
            String consulta = "select * from usuarios where nombreUsuario=? and contraseña=?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            rs.first();
            System.out.println(rs.getRow());
            if(rs.wasNull()==false){
            nombreUsuario = rs.getString("nombreUsuario");
            contraseña = rs.getString("contraseña");
            nombre = rs.getString("nombre");
            apellidos = rs.getString("apellidos");
            formaPago = rs.getString("formaDePago");
            administrador = rs.getInt("administrador");

            user = new Usuario(nombreUsuario, contraseña, nombre, apellidos, formaPago, administrador);
            
            esta = true;
            }
           
          

            

//            String NombreUsuario, String Contrasenya, String Nombre, String Apellidos, String FormaPago
        } catch (Exception e) {
            System.out.println(e);
        }
       
        return esta;
    }
    public boolean esAdministrador(){
    boolean esAdministrador=false;
    if(user.getAdministrador()==1){
    esAdministrador=true;
    }
    return esAdministrador;
    
    }
}