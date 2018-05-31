/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FICHEROS;

import MODELO.Actividad;
import MODELO.TipoActividades;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 *
 * @author Kokekui
 */
public class ImportacionActividades {

    public List<Actividad> importarActividades(File cargaActividades) {
        List<Actividad> listaActividades = new ArrayList<>();//AL DEVOLVER LA LISTA, Y RECOGERLA EN LA VENTANA QUE TOCA, RECORRER TODA LA LISTA Y HACER INSERT SEGUN LONGITUD
        String direccion = "";
        Integer idActividad = 0;
        String tipoActividad, subtipo = null, descripcion = null, observacion = null;
        TipoActividades tipo = null;
        if (cargaActividades != null) {
            direccion = cargaActividades.getAbsolutePath();
            Path archivo = Paths.get(direccion);
            try (Stream<String> datos = Files.lines(archivo);) {
                Iterator<String> it = datos.iterator();
                while (it.hasNext()) {
                    String linea = it.next();
                    StringTokenizer s = new StringTokenizer(linea, "#");
                    String actividad = s.nextToken();
                    if (actividad.equalsIgnoreCase("Id")) {
                        idActividad = Integer.parseInt(s.nextToken());
                    }
                    actividad = s.nextToken();
                    if (actividad.equalsIgnoreCase("Tipo")) {
                        tipoActividad = s.nextToken();
                        tipo = TipoActividades.valueOf(tipoActividad);
                    }
                    actividad = s.nextToken();
                    if (actividad.equalsIgnoreCase("Subtipo")) {
                        subtipo = s.nextToken();
                    }
                    actividad = s.nextToken();
                    if (actividad.equalsIgnoreCase("Descripcion")) {
                        descripcion = s.nextToken();
                    }
                    actividad = s.nextToken();
                    if (actividad.equalsIgnoreCase("Observacion")) {
                        observacion = s.nextToken();
                    }
                }
                Actividad act = new Actividad(idActividad, tipo, subtipo, descripcion, observacion);//lista actividades, donde?
                listaActividades.add(act);
            } catch (IOException ex) {
                System.out.println("Error en la lectura del archivo");
            }
        }
        return listaActividades;
    }

}
