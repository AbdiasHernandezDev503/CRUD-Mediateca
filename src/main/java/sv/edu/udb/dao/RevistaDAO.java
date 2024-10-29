package sv.edu.udb.dao;

import org.apache.logging.log4j.Logger;
import sv.edu.udb.entidades.Revista;
import sv.edu.udb.util.IMediatecaCRUD;
import sv.edu.udb.util.Log4JUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevistaDAO implements IMediatecaCRUD<Revista> {
    private Logger log = Log4JUtil.getLogger(Conexion.class);

    private String generarCodigoID(String tipo) throws SQLException {
        String codigoID = null;
        String prefijo = tipo.equals("Libro") ? "LIB"
                : tipo.equals("Revista") ? "REV"
                : tipo.equals("CD_Audio") ? "CDA"
                : tipo.equals("DVD") ? "DVD"
                : null;

        String sql = "SELECT IFNULL(MAX(CAST(SUBSTRING(codigo_ID, 4) AS UNSIGNED)), 0) + 1 AS nuevo_id " +
                "FROM Material WHERE tipo = ?";

        // Usa try-with-resources para la conexión y el PreparedStatement
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int nuevoID = rs.getInt("nuevo_id");
                    codigoID = prefijo + String.format("%05d", nuevoID); // Formato de cinco dígitos
                }
            }
        } catch (SQLException e) {
            log.error("Error al generar el código ID: ", e);
            throw e;
        }
        return codigoID;
    }

    public String insertarMaterial(String titulo, String tipo) throws SQLException {
        String codigoID = generarCodigoID(tipo); // Generar el código ID antes de abrir la conexión

        String sqlMaterial = "INSERT INTO Material (codigo_ID, titulo, tipo) VALUES (?, ?, ?)";

        // Abre la conexión y realiza el insert
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement psMaterial = connection.prepareStatement(sqlMaterial)) {

            psMaterial.setString(1, codigoID);
            psMaterial.setString(2, titulo);
            psMaterial.setString(3, tipo);
            psMaterial.executeUpdate();

        } catch (SQLException e) {
            log.error("Error al insertar material: ", e);
            throw e;
        }
        return codigoID;
    }

    public int crear(Revista revista) throws Exception {
        int result = 0;
        String codigoID = insertarMaterial(revista.getTitulo(), "Revista");

        String sql = "INSERT INTO Revista(codigo_ID, editorial, periodicidad, fecha_publicacion, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigoID);
            ps.setString(2, revista.getEditorial());
            ps.setString(3, revista.getPeriodicidad());

            // Convertir java.util.Date a java.sql.Date para la fecha de publicación
            Date fechaPublicacion = new Date(revista.getFechaPublicacion().getTime());
            ps.setDate(4, fechaPublicacion);
            ps.setInt(5, revista.getStock());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Ocurrió un error al crear la revista: ", e);
            throw e;
        }

        return result;
    }


    public int modificar(Revista revista) throws Exception {
        int result = 0;
        String sqlUpdateRevista;
        String sqlUpdateMaterial;

        try(Connection conexion = Conexion.obtenerConexion()) {
           sqlUpdateRevista = "UPDATE Revista SET editorial = ?, periodicidad = ?, fecha_publicacion = ?, stock = ? " +
                   "WHERE codigo_ID = ?";

           sqlUpdateMaterial = "UPDATE Material SET titulo = ? WHERE codigo_ID = ?";

           try (PreparedStatement psMaterial = Conexion.createPreparedStatement(conexion, sqlUpdateMaterial)) {
                psMaterial.setString(1, revista.getTitulo());
                psMaterial.setString(2, revista.getCodigoId());
                psMaterial.executeUpdate();
           }

           try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sqlUpdateRevista)){
               ps.setString(1, revista.getEditorial());
               ps.setString(2, revista.getPeriodicidad());

               // Convertir java.util.Date a java.sql.Date para la fecha de publicación
               Date fechaPublicacion = new Date(revista.getFechaPublicacion().getTime());
               ps.setDate(3, fechaPublicacion);
               ps.setInt(4, revista.getStock());
               ps.setString(5, revista.getCodigoId());

               result = ps.executeUpdate();

           } catch (SQLException e) {
               log.error("Ocurrio un error al modificar los datos: ", e);
           }
        } catch (SQLException e) {
            log.error("Ocurrio un error inesperado: ", e);
        }

        return result;
    }

    public int eliminar(String codigoId) throws Exception {
        int result = 0;
        String sql;

        try(Connection conexion = Conexion.obtenerConexion()) {
            sql = "DELETE FROM Revista WHERE codigo_ID = ?";

            try(PreparedStatement ps = Conexion.createPreparedStatement(conexion, sql)) {
                ps.setString(1, codigoId);
                result = ps.executeUpdate();

                if (result > 0) {

                    String sqlDeleteMaterial = "DELETE FROM Material WHERE codigo_ID = ?";
                    try (PreparedStatement psMaterial = conexion.prepareStatement(sqlDeleteMaterial)) {
                        psMaterial.setString(1, codigoId);
                        result = psMaterial.executeUpdate(); // Ejecuta la eliminación en Material
                    }
                }

            } catch (SQLException e) {
                log.error("Ocurrio un error al borrar los datos: ", e);
            }
        } catch (SQLException e) {
            log.error("Ocurrio un error inesperado: ", e);
        }

        return result;
    }

    public List<Revista> listar() throws Exception {
        List<Revista> revistas = new ArrayList<>();

        String sql = "SELECT codigo_ID, editorial, periodicidad, fecha_publicacion, stock FROM Revista";

        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Revista revista = new Revista();
                revista.setCodigoId(rs.getString("codigo_ID"));
                revista.setEditorial(rs.getString("editorial"));
                revista.setPeriodicidad(rs.getString("periodicidad"));
                revista.setFechaPublicacion(rs.getDate("fecha_publicacion"));
                revista.setStock(rs.getInt("stock"));

                revistas.add(revista);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error al listar las revistas: ", e);
        }

        return revistas;
    }
}
