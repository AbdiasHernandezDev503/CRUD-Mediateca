package sv.edu.udb.dao;

import org.apache.logging.log4j.Logger;
import sv.edu.udb.entidades.Dvd;

import sv.edu.udb.util.IMediatecaCRUD;
import sv.edu.udb.util.Log4JUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DvdDAO implements IMediatecaCRUD<Dvd> {
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

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

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

    public int crear(Dvd dvd) throws Exception {
        int result = 0;
        String codigoID = insertarMaterial(dvd.getTitulo(), "DVD");

        String sql = "INSERT INTO DVD(codigo_ID, director, duracion, genero, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigoID);
            ps.setString(2, dvd.getDirector());
            ps.setTime(3, Time.valueOf(dvd.getDuracion()));
            ps.setString(4, dvd.getGenero());
            ps.setInt(5, dvd.getStock());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Ocurrió un error al crear el DVD: ", e);
            throw e;
        }

        return result;
    }

    public int modificar(Dvd dvd) throws Exception {
        int result = 0;
        String sqlUpdateDvd;
        String sqlUpdateMaterial;

        try (Connection conexion = Conexion.obtenerConexion()) {
            sqlUpdateDvd = "UPDATE DVD SET director = ?, duracion = ?, genero = ?, stock = ? " +
                    "WHERE codigo_ID = ?";

            sqlUpdateMaterial = "UPDATE Material SET titulo = ? WHERE codigo_ID = ?";

            try (PreparedStatement psMaterial = Conexion.createPreparedStatement(conexion, sqlUpdateMaterial)) {
                psMaterial.setString(1, dvd.getTitulo());
                psMaterial.setString(2, dvd.getCodigoId());
                psMaterial.executeUpdate();
            }

            try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sqlUpdateDvd)) {
                ps.setString(1, dvd.getDirector());
                ps.setTime(2, Time.valueOf(dvd.getDuracion()));
                ps.setString(3, dvd.getGenero());
                ps.setInt(4, dvd.getStock());
                ps.setString(5, dvd.getCodigoId());

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

        try (Connection conexion = Conexion.obtenerConexion()) {
            sql = "DELETE FROM DVD WHERE codigo_ID = ?";

            try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sql)) {
                ps.setString(1, codigoId);
                result = ps.executeUpdate();

                if (result > 0) {
                    String sqlDeleteMaterial = "DELETE FROM Material WHERE codigo_ID = ?";
                    try (PreparedStatement psMaterial = conexion.prepareStatement(sqlDeleteMaterial)) {
                        psMaterial.setString(1, codigoId);
                        result = psMaterial.executeUpdate();
                    }
                }

            } catch (SQLException e) {
                log.error("Ocurrió un error al borrar los datos: ", e);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error inesperado: ", e);
        }

        return result;
    }

    public List<Dvd> listar() throws Exception {
        List<Dvd> dvds = new ArrayList<>();

        String sql = "SELECT codigo_ID, director, duracion, genero, stock FROM DVD";

        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dvd dvd = new Dvd();
                dvd.setCodigoId(rs.getString("codigo_ID"));
                dvd.setDirector(rs.getString("director"));
                dvd.setDuracion(rs.getTime("duracion").toLocalTime());
                dvd.setGenero(rs.getString("genero"));
                dvd.setStock(rs.getInt("stock"));

                dvds.add(dvd);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error al listar los DVDs: ", e);
        }

        return dvds;
    }

    public List<Dvd> buscar(Dvd dvd) throws SQLException {
        List<Dvd> dvds = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT d.codigo_ID, m.titulo, d.director, d.genero, d.duracion, d.stock "
                + "FROM DvD d "
                + "JOIN Material m ON d.codigo_ID = m.codigo_ID "
                + "WHERE 1=1");

        List<Object> parametros = new ArrayList<>();

        if (dvd.getTitulo() != null && !dvd.getTitulo().isEmpty()) {
            sql.append(" AND m.titulo LIKE ?");
            parametros.add("%" + dvd.getTitulo() + "%");
        }
        if (dvd.getDirector() != null && !dvd.getDirector().isEmpty()) {
            sql.append(" AND d.director LIKE ?");
            parametros.add("%" + dvd.getDirector() + "%");
        }
        if (dvd.getGenero() != null && !dvd.getGenero().isEmpty()) {
            sql.append(" AND d.genero LIKE ?");
            parametros.add("%" + dvd.getGenero() + "%");
        }

        try (Connection connection = Conexion.obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dvd resultado = new Dvd();
                    resultado.setCodigoId(rs.getString("codigo_ID"));
                    resultado.setTitulo(rs.getString("titulo"));
                    resultado.setDirector(rs.getString("director"));
                    resultado.setGenero(rs.getString("genero"));
                    resultado.setDuracion(rs.getTime("duracion").toLocalTime());
                    resultado.setStock(rs.getInt("stock"));

                    dvds.add(resultado);
                }
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error inesperado al buscar.", e.getMessage());
        }

        return dvds;
    }
}