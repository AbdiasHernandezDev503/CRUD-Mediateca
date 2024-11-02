package sv.edu.udb.dao;

import org.apache.logging.log4j.Logger;
import sv.edu.udb.entidades.Libro;
import sv.edu.udb.util.IMediatecaCRUD;
import sv.edu.udb.util.Log4JUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements IMediatecaCRUD<Libro> {

    private Logger log = Log4JUtil.getLogger(Conexion.class);

    private String generarCodigoID(String tipo) throws SQLException {
        String codigoID = null;
        String prefijo = tipo.equals("Libro") ? "LIB"
                : tipo.equals("Revista") ? "REV"
                : tipo.equals("CD_Audio") ? "CDA"
                : tipo.equals("DVD") ? "DVD"
                : null;

        String sql = "SELECT IFNULL(MAX(CAST(SUBSTRING(codigo_ID, 4) AS UNSIGNED)), 0) + 1 AS nuevo_id "
                + "FROM Material WHERE tipo = ?";

        try (Connection conexion = Conexion.obtenerConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int nuevoID = rs.getInt("nuevo_id");
                    codigoID = prefijo + String.format("%05d", nuevoID);
                }
            }
        } catch (SQLException e) {
            log.error("Error al generar el código ID: ", e);
            throw e;
        }
        return codigoID;
    }

    public String insertarMaterial(String titulo, String tipo) throws SQLException {
        String codigoID = generarCodigoID(tipo);

        String sqlMaterial = "INSERT INTO Material (codigo_ID, titulo, tipo) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.obtenerConexion(); PreparedStatement psMaterial = connection.prepareStatement(sqlMaterial)) {

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

    public int crear(Libro libro) throws Exception {
        int result = 0;
        String codigoID = insertarMaterial(libro.getTitulo(), "Libro");

        String sql = "INSERT INTO Libro(codigo_ID, autor, paginas, editorial, isbn, anio_publicacion, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigoID);
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getPaginas());
            ps.setString(4, libro.getEditorial());
            ps.setString(5, libro.getIsbn());
            ps.setInt(6, libro.getAnioPublicacion());
            ps.setInt(7, libro.getStock());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Ocurrió un error al crear el libro: ", e);
            throw e;
        }

        return result;
    }

    public int modificar(Libro libro) throws Exception {
        int result = 0;
        String sqlUpdateLibro;
        String sqlUpdateMaterial;

        try (Connection conexion = Conexion.obtenerConexion()) {
            sqlUpdateLibro = "UPDATE Libro SET autor = ?, paginas = ?, editorial = ?, isbn = ?, anio_publicacion = ?, stock = ? "
                    + "WHERE codigo_ID = ?";

            sqlUpdateMaterial = "UPDATE Material SET titulo = ? WHERE codigo_ID = ?";

            try (PreparedStatement psMaterial = Conexion.createPreparedStatement(conexion, sqlUpdateMaterial)) {
                psMaterial.setString(1, libro.getTitulo());
                psMaterial.setString(2, libro.getCodigoId());
                psMaterial.executeUpdate();
            }

            try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sqlUpdateLibro)) {
                ps.setString(1, libro.getAutor());
                ps.setInt(2, libro.getPaginas());
                ps.setString(3, libro.getEditorial());
                ps.setString(4, libro.getIsbn());
                ps.setInt(5, libro.getAnioPublicacion());
                ps.setInt(6, libro.getStock());
                ps.setString(7, libro.getCodigoId());

                result = ps.executeUpdate();

            } catch (SQLException e) {
                log.error("Ocurrió un error al modificar los datos: ", e);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error inesperado: ", e);
        }

        return result;
    }

    public int eliminar(String codigoId) throws Exception {
        int result = 0;
        String sql;
        log.info("Iniciando eliminación del libro con codigo_ID: {}", codigoId);

        try (Connection conexion = Conexion.obtenerConexion()) {
            conexion.setAutoCommit(false);  // Inicia la transacción
            log.debug("Conexión obtenida y auto-commit desactivado");

            sql = "DELETE FROM Libro WHERE codigo_ID = ?";
            try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sql)) {
                ps.setString(1, codigoId);
                result = ps.executeUpdate();
                log.info("Resultado de eliminación en Libro: {}", result);

                if (result > 0) {
                    String sqlDeleteMaterial = "DELETE FROM Material WHERE codigo_ID = ?";
                    try (PreparedStatement psMaterial = conexion.prepareStatement(sqlDeleteMaterial)) {
                        psMaterial.setString(1, codigoId);
                        int resultMaterial = psMaterial.executeUpdate();
                        log.info("Resultado de eliminación en Material: {}", resultMaterial);

                        if (resultMaterial > 0) {
                            conexion.commit();  // Confirma la transacción solo si ambas eliminaciones son exitosas
                            log.info("Eliminación completada en ambas tablas y commit realizado");
                        } else {
                            conexion.rollback();  // Revierte si la segunda eliminación falla
                            log.warn("No se pudo eliminar el registro en Material. Transacción revertida");
                        }
                    }
                } else {
                    log.warn("No se encontró el registro en Libro. Transacción revertida");
                    conexion.rollback();  // Revierte si no existe el registro en Libro
                }
            } catch (SQLException e) {
                conexion.rollback();  // Revierte la transacción en caso de error
                log.error("Ocurrió un error al borrar los datos en Libro: ", e);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error inesperado al conectar a la base de datos: ", e);
        }

        return result;
    }

    public List<Libro> listar() throws Exception {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT l.codigo_ID, m.titulo, l.autor, l.paginas, l.editorial, l.isbn, l.anio_publicacion, l.stock"
                + " FROM Libro l "
                + "JOIN Material m ON l.codigo_ID = m.codigo_ID";

        try (Connection connection = Conexion.obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setCodigoId(rs.getString("codigo_ID"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setPaginas(rs.getInt("paginas"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
                libro.setStock(rs.getInt("stock"));

                libros.add(libro);

            }
        } catch (SQLException e) {
            log.error("Ocurrió un error al listar los libros: ", e);
        }

        return libros;
    }

    public List<Libro> buscar(Libro libro) throws SQLException {
        List<Libro> libros = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT l.codigo_ID, m.titulo, l.autor, l.editorial, l.isbn, l.anio_publicacion, l.paginas, l.stock "
                + "FROM Libro l "
                + "JOIN Material m ON l.codigo_ID = m.codigo_ID "
                + "WHERE 1=1");

        List<Object> parametros = new ArrayList<>();

        if (libro.getCodigoId() != null && !libro.getCodigoId().isEmpty()) {
            sql.append(" AND l.codigo_ID LIKE ?");
            parametros.add("%" + libro.getCodigoId() + "%");
        }
        if (libro.getTitulo() != null && !libro.getTitulo().isEmpty()) {
            sql.append(" AND m.titulo LIKE ?");
            parametros.add("%" + libro.getTitulo() + "%");
        }
        if (libro.getAutor() != null && !libro.getAutor().isEmpty()) {
            sql.append(" AND l.autor LIKE ?");
            parametros.add("%" + libro.getAutor() + "%");
        }
        if (libro.getEditorial() != null && !libro.getEditorial().isEmpty()) {
            sql.append(" AND l.editorial LIKE ?");
            parametros.add("%" + libro.getEditorial() + "%");
        }
        if (libro.getIsbn() != null && !libro.getIsbn().isEmpty()) {
            sql.append(" AND l.isbn LIKE ?");
            parametros.add("%" + libro.getIsbn() + "%");
        }
        if (libro.getAnioPublicacion() != 0) {
            sql.append(" AND l.anio_publicacion = ?");
            parametros.add(libro.getAnioPublicacion());
        }
        if (libro.getPaginas() != 0) {
            sql.append(" AND l.paginas = ?");
            parametros.add(libro.getPaginas());
        }
        if (libro.getStock() != 0) {
            sql.append(" AND l.stock = ?");
            parametros.add(libro.getStock());
        }

        try (Connection connection = Conexion.obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Libro resultado = new Libro();
                    resultado.setCodigoId(rs.getString("codigo_ID"));
                    resultado.setTitulo(rs.getString("titulo")); // Obtener el título desde Material
                    resultado.setAutor(rs.getString("autor"));
                    resultado.setEditorial(rs.getString("editorial"));
                    resultado.setIsbn(rs.getString("isbn"));
                    resultado.setAnioPublicacion(rs.getInt("anio_publicacion"));
                    resultado.setPaginas(rs.getInt("paginas"));
                    resultado.setStock(rs.getInt("stock"));

                    libros.add(resultado);
                }
            }
        } catch (SQLException e) {
            log.error("Ocurrio un error inesperado: ", e);
        }

        return libros;
    }

}
