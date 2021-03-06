package MODELO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExperienciaActividad {

    public int idExperiencia;

    public int numOrden;

    public int idActividad;

    public LocalDate fechaComienzo;

    public LocalDate fechaFin;

    public int numPlazas;

    public double precio;

    public LocalTime duracion;

    public ExperienciaActividad(int idExperiencia, int numOrden, int idActividad) {
        this.idExperiencia = idExperiencia;
        this.numOrden = numOrden;
        this.idActividad = idActividad;
    }

    public ExperienciaActividad(int idExperiencia, int idActividad, LocalDate fechaComienzo, LocalDate fechaFin, int numPlazas, double precio, LocalTime duracion) {
        this.idExperiencia = idExperiencia;
        this.idActividad = idActividad;
        this.fechaComienzo = fechaComienzo;
        this.fechaFin = fechaFin;
        this.numPlazas = numPlazas;
        this.precio = precio;
        this.duracion = duracion;
    }
    
    public ExperienciaActividad(int idActividad, LocalDate fechaComienzo, LocalDate fechaFin, int numPlazas){
        
        
    }
    
    

    //GETS Y SETS
    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDate getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(LocalDate fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public double getPreciol() {
        return precio;
    }

    public void setPreciol(double preciol) {
        this.precio = preciol;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public double precioCalculado(double precioMetodo) {
        precio = precioMetodo * numPlazas;
        return precio;
    }

}
