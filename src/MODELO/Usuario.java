package MODELO;

public class Usuario {

    private String NombreUsuario;

    private String Contrasenya;

    private String Nombre;

    private String Apellidos;

    private String Correo;

    private String Direccion;

    private String FormaPago;

    private int idUsuario;
    
    private int administrador;

    public Usuario(int idUsuario,String NombreUsuario, String Contrasenya, String Nombre, String Apellidos, String FormaPago,String Direccion,String Correo, int administrador) {
        this.idUsuario = idUsuario;
        this.NombreUsuario = NombreUsuario;
        this.Contrasenya = Contrasenya;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FormaPago = FormaPago;
        this.Direccion= Direccion;
        this.Correo= Correo;
        this.administrador = administrador;
    }

    public Usuario() {
       
    }

    //GETS  Y SETS
    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void setContrasenya(String Contrasenya) {
        this.Contrasenya = Contrasenya;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

 
    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public int getAdministrador() {
        return administrador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
