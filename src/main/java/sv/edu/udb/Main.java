package sv.edu.udb;

import sv.edu.udb.dao.Conexion;
import sv.edu.udb.dao.RevistaDAO;
import sv.edu.udb.entidades.Revista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {


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

        /*    METODO PARA MODIFICAR LOS REGISTROS

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

        /*     ELIMINAR REGISTRO DE LA TABLA
        try {
            int result = revistaDAO.eliminar(codigoID);
            if (result > 0) {
                System.out.println("Eliminación completada.");
            } else {
                System.out.println("No se encontró el registro o no se pudo eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la revista.");
            e.printStackTrace();
        } */

        /*        CREAR REGISTRO EN LA TABLA

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        } */
    }
}