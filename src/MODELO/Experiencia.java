package MODELO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Experiencia {

    private int idExperiencia;

    private int idUsuario;

    private double presupuesto;

    private LocalDate fechaContratacion;

    private LocalDate fechaFin;

    private tipoOrigen origen;
    
    private List<ExperienciaActividad> listado = new ArrayList<>();

    public Experiencia(int idExperiencia, int idUsuario, double presupuesto, LocalDate fechaContratacion, LocalDate fechaFin, tipoOrigen origen) {
        this.idExperiencia = idExperiencia;
        this.idUsuario = idUsuario;
        this.presupuesto = presupuesto;
        this.fechaContratacion = fechaContratacion;
        this.fechaFin = fechaFin;
        this.origen = origen;
    }
    public Experiencia(){
        
    }
    
    public void añadirListaExpAct(ExperienciaActividad expAct){
        listado.add(expAct);
    }

//GETS Y SETS
    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public tipoOrigen getOrigen() {
        return origen;
    }

    public void setOrigen(tipoOrigen origen) {
        this.origen = origen;
    }

    public List<ExperienciaActividad> getListado() {
        return listado;
    }

    public void setListado(List<ExperienciaActividad> listado) {
        this.listado = listado;
    }

}
