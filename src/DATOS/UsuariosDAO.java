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
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                int administrador = rs.getInt("administrador");

                Usuario fullUser = new Usuario(idUsuario, nombreUsuario, contrasenya, nombre, apellidos, formaDePago, direccion, correo, administrador);
                listaUsuarios.add(fullUser);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

  
    public boolean insertarUsuario(String nombreUsuario, String contraseña, String nombre, String apellidos,  String direccion,String correo,String formaDePago) throws SQLException {
        boolean insercion = false;
        String consulta = "INSERT INTO usuarios (nombreUsuario, contraseña, nombre, apellidos,direccion,correo,formaDePago) VALUES (?,?,?,?,?,?,?)";

        ps = conn.prepareStatement(consulta);
        ps.setString(1, nombreUsuario);
        ps.setString(2, contraseña);
        ps.setString(3, nombre);
        ps.setString(4, apellidos);
        ps.setString(5, direccion);
        ps.setString(6, correo);
        ps.setString(7, formaDePago);
        
       
        

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

        ps.setInt(1, user.getIdUsuario());

        int numFilas = ps.executeUpdate();
        if (numFilas == 1 || numFilas > 1) {
            eliminacion = true;
        }
        return eliminacion;

    }

    public boolean actualizarUsuario(String nombre, String apellidos, String formaDePago,String direccion,String correo, int idUsuario) throws SQLException {
        boolean actualizacion = false;
        String consulta = "UPDATE usuarios SET nombre = ?, apellidos = ?, formaDePago = ?,direccion=?,correo=? WHERE idUsuario = ?";

        ps = conn.prepareStatement(consulta);

        ps.setString(1, nombre);
        ps.setString(2, apellidos);
        ps.setString(3, formaDePago);
        ps.setInt(4, idUsuario);
        ps.setString(5, direccion);
        ps.setString(5, correo);
        int numFilas = ps.executeUpdate();
        if (numFilas == 1 || numFilas > 1) {
            actualizacion = true;
        }
        return actualizacion;
    }

    public boolean buscarUsuario(String nombreUsuario, String contraseña, Connection con) {
        boolean esta = false;
        String nombre;
        String apellidos;
        String formaPago;
        String direccion;
        String correo;
        int administrador;
        int idUsuario;
        try {

            String consulta = "select * from usuarios where nombreUsuario=? and contraseña=?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            rs.first();
            System.out.println(rs.getRow());
            if (rs.wasNull() == false) {
                idUsuario = rs.getInt("idUsuario");
                nombreUsuario = rs.getString("nombreUsuario");
                contraseña = rs.getString("contraseña");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                formaPago = rs.getString("formaDePago");
                direccion = rs.getString("direccion");
                correo = rs.getString("correo");
                administrador = rs.getInt("administrador");

                user = new Usuario(idUsuario, nombreUsuario, contraseña, nombre, apellidos, formaPago, direccion, correo, administrador);

                esta = true;
            }

//            String NombreUsuario, String Contrasenya, String Nombre, String Apellidos, String FormaPago
        } catch (Exception e) {
            System.out.println(e);
        }

        return esta;
    }

    public boolean esAdministrador() {
        boolean esAdministrador = false;
        if (user.getAdministrador() == 1) {
            esAdministrador = true;
        }
        return esAdministrador;

    }

    public Usuario getUser() {
        return user;
    }
}
