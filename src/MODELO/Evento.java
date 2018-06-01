package MODELO;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Evento {

  private  LocalDate fecha;
  private String Nombre;
   private String Descripcion;

    public Evento(LocalDate fecha, String Nombre, String Descripcion) {
        this.fecha = fecha;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }

    public Evento(LocalDate fecha, String Nombre) {
        this.fecha = fecha;
        this.Nombre = Nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }
 @Override
    public String toString() {
        String cadena = this.fecha + "  "+this.Nombre+"  ";
        if(this.Descripcion!=null){
        cadena+= this.Descripcion;
        }
        return cadena;
    }
 




}