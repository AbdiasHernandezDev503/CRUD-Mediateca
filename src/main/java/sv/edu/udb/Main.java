package sv.edu.udb;

import sv.edu.udb.dao.CdAudioDAO;
import sv.edu.udb.dao.Conexion;
import sv.edu.udb.dao.LibroDAO;
import sv.edu.udb.dao.RevistaDAO;
import sv.edu.udb.entidades.CdAudio;
import sv.edu.udb.entidades.Libro;
import sv.edu.udb.entidades.Revista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
  final  Logger log = LogManager.getLogger(Main.class);
        log.info("Prueba en consola");

        RevistaDAO revistaDAO = new RevistaDAO();

        /*     METODO DE LISTAR LOS RESULTADOS OBTENIDOS
        try {
            List<Revista> revistas = revistaDAO.listar();
            for (Revista revista : revistas) {
                System.out.println("Código: " + revista.getCodigoId());
                System.out.println("Editorial: " + revista.getEditorial());
                System.out.println("Periodicidad: " + revista.getPeriodicidad());
                System.out.println("Fecha de Publicación: " + revista.getFechaPublicacion());
                System.out.println("Stock: " + revista.getStock());
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

      //      METODO PARA MODIFICAR LOS REGISTROS

     /*   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaPublicacion = null;
        try {
            fechaPublicacion = sdf.parse("2023-01-01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Revista revista = new Revista("Nuevo Título REV2", "Editorial Z", "Mensual", fechaPublicacion, 30);
        revista.setCodigoId("REV00001");

        try {
            int result = revistaDAO.modificar(revista);
            if (result > 0) {
                System.out.println("Actualización completada en la base de datos.");
            } else {
                System.out.println("No se encontró el registro o no se pudo actualizar.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la revista.");
            e.printStackTrace();
        }
*/

            // ELIMINAR REGISTRO DE LA TABLA
   /*     try {
            int result = revistaDAO.eliminar(codigoID);
            if (result > 0) {
                System.out.println("Eliminación completada.");
            } else {
                System.out.println("No se encontró el registro o no se pudo eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la revista.");
            e.printStackTrace();
        }*/

        //CREAR REGISTRO EN LA TABLA

      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaPublicacion = null;
        try {
            fechaPublicacion = sdf.parse("2023-01-01");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Revista revista = new Revista("Revista La casa de los famosos", "Editorial Z", "Mensual", fechaPublicacion, 20);

        try {
            revistaDAO.crear(revista);
            System.out.println("Inserción completada en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al insertar la revista.");
            e.printStackTrace();
        }*/

        //CREAR REGISTRO EN LA TABLA

/*
        LibroDAO libroDAO = new LibroDAO();

        Libro libro = new Libro("LIB00001", "El Quijote", "Libro", "Miguel de Cervantes", 500, "Editorial X", "1234567890", 1605, 10);

        try {
            libroDAO.crear(libro);
            System.out.println("Inserción completada en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al insertar el libro.");
            e.printStackTrace();
        }*/

        //   METODO PARA MODIFICAR LOS REGISTROS
/*
LibroDAO libroDAO = new LibroDAO();

Libro libro = new Libro("LIB00001", "EL principito", "Libro", "Miguel de Cervantes", 500, "Editorial X", "1234567890", 1605, 10);

try {
    int result = libroDAO.modificar(libro);
    if (result > 0) {
        System.out.println("Actualización completada en la base de datos.");
    } else {
        System.out.println("No se encontró el registro o no se pudo actualizar.");
    }
} catch (Exception e) {
    System.out.println("Error al actualizar el libro.");
    e.printStackTrace();
}*/


       // ELIMINAR REGISTRO DE LA TABLA
      /*  LibroDAO libroDAO = new LibroDAO();
        String codigoID = "LIB00001"; // Replace with the actual ID of the book you want to delete

        try {
            int result = libroDAO.eliminar(codigoID);
            if (result > 0) {
                System.out.println("Eliminación completada.");
            } else {
                System.out.println("No se encontró el registro o no se pudo eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el libro.");
            e.printStackTrace();
        }*/
         //CREAR REGISTRO EN LA TABLA
       /* CdAudioDAO cdAudioDAO = new CdAudioDAO();

       CdAudio cdAudio = new CdAudio( "Cd testing 2", "CD_Audio", "Various Artists", LocalTime.of(1, 20, 0), 23, 50);

        try {
            cdAudioDAO.crear(cdAudio);
            System.out.println("Inserción completada en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al insertar el CD_Audio.");
            e.printStackTrace();
        }*/


    }
}