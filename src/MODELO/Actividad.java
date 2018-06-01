package MODELO;

import java.util.ArrayList;
import java.util.List;

public class Actividad {

    public Integer idActividad;

    public TipoActividades tipoActividad;

    public String subtipo;

    public String imagen;

    public String URL;

    public String descripcion;

    public String observacion;

    private String direccion;
    private double precio;

    private List<ExperienciaActividad> listado = new ArrayList<>();

    public Actividad(Integer idActividad, TipoActividades tipoActividad, String subtipo, String imagen, String URL, String descripcion, String observacion, String direccion, double precio) {
        this.idActividad = idActividad;
        this.tipoActividad = tipoActividad;
        this.subtipo = subtipo;
        this.imagen = imagen;
        this.URL = URL;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.direccion = direccion;
        this.precio = precio;
    }

    public Actividad(Integer idActividad, TipoActividades tipoActividad, String subtipo, String descripcion, String observacion, double precio) {
        this.idActividad = idActividad;
        this.tipoActividad = tipoActividad;
        this.subtipo = subtipo;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.precio = precio;
    }

 

   

    public Actividad() {
    }
    
    public void añadirExpAct(ExperienciaActividad expAct){
        listado.add(expAct);
    }

    //GETS Y SETS
    public TipoActividades getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividades tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        String cadena = idActividad + " " + descripcion;
        return cadena;
    }

    public List<ExperienciaActividad> getListado() {
        return listado;
    }

    public void setListado(List<ExperienciaActividad> listado) {
        this.listado = listado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
