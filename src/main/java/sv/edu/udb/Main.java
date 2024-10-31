package sv.edu.udb;

import sv.edu.udb.dao.Conexion;
import sv.edu.udb.dao.DvdDAO;
import sv.edu.udb.dao.LibroDAO;
import sv.edu.udb.dao.RevistaDAO;
import sv.edu.udb.entidades.Dvd;
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

        DvdDAO dvdDAO = new DvdDAO();
        /*Dvd dvd = new Dvd("Anaconda 4: Rastro de Sangre", "Stephen Spilberg", LocalTime.of(1, 30), "Suspenso", 20);

        try {
            dvdDAO.crear(dvd);
            System.out.println("Inserción completada en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al insertar la revista.");
            e.printStackTrace();
        }
        log.info("Prueba en consola");*/

        /*RevistaDAO revistaDAO = new RevistaDAO();
        LibroDAO libroDAO = new LibroDAO();*/
        //DvdDAO dvdDAO = new DvdDAO();

        try {
            List<Dvd> dvds = dvdDAO.listar();
            for (Dvd dvd : dvds) {
                System.out.println("Código: " + dvd.getCodigoId());
                System.out.println("titulo: " + dvd.getTitulo());
                System.out.println("Director: " + dvd.getDirector());
                System.out.println("Genero: " + dvd.getGenero());
                System.out.println("Duracion: " + dvd.getDuracion());
                System.out.println("Stock: " + dvd.getStock());
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*String codigoID = "LIB00002";

        try {
            int result = libroDAO.eliminar(codigoID);
            if (result > 0) {
                System.out.println("Eliminación completada.");
            } else {
                System.out.println("No se encontró el registro o no se pudo eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la revista.");
            e.printStackTrace();
        }*/

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
    }
}