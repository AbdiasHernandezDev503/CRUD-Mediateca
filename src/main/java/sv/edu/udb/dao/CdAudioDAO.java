package sv.edu.udb.dao;

import org.apache.logging.log4j.Logger;
import sv.edu.udb.entidades.CdAudio;
import sv.edu.udb.util.IMediatecaCRUD;
import sv.edu.udb.util.Log4JUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CdAudioDAO implements IMediatecaCRUD<CdAudio> {
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

    public int crear(CdAudio cdAudio) throws Exception {
        int result = 0;
        String codigoID = insertarMaterial(cdAudio.getTitulo(), "CD_Audio");

        String sql = "INSERT INTO CD_Audio(codigo_ID, artista, genero,  duracion, canciones,  stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigoID);
            ps.setString(2, cdAudio.getArtista());
            ps.setString(3, cdAudio.getGenero());
            ps.setTime(4, Time.valueOf(cdAudio.getDuracion()));
             ps.setInt(5, cdAudio.getCanciones());
            ps.setInt(6, cdAudio.getStock());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Ocurrió un error al crear el CD_Audio: ", e);
            throw e;
        }

        return result;
    }

    public int modificar(CdAudio cdAudio) throws Exception {
        int result = 0;
        String sqlUpdateCdAudio;
        String sqlUpdateMaterial;

        try (Connection conexion = Conexion.obtenerConexion()) {
            sqlUpdateCdAudio = "UPDATE CD_Audio SET artista = ?, duracion = ?, genero = ?, stock = ? " +
                    "WHERE codigo_ID = ?";

            sqlUpdateMaterial = "UPDATE Material SET titulo = ? WHERE codigo_ID = ?";

            try (PreparedStatement psMaterial = Conexion.createPreparedStatement(conexion, sqlUpdateMaterial)) {
                psMaterial.setString(1, cdAudio.getTitulo());
                psMaterial.setString(2, cdAudio.getCodigoId());
                psMaterial.executeUpdate();
            }

            try (PreparedStatement ps = Conexion.createPreparedStatement(conexion, sqlUpdateCdAudio)) {
                ps.setString(1, cdAudio.getArtista());
                ps.setTime(2, Time.valueOf(cdAudio.getDuracion()));
                ps.setString(3, cdAudio.getGenero());
                ps.setInt(4, cdAudio.getStock());
                ps.setString(5, cdAudio.getCodigoId());

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
            sql = "DELETE FROM CD_Audio WHERE codigo_ID = ?";

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

    public List<CdAudio> listar() throws Exception {
        List<CdAudio> cdAudios = new ArrayList<>();

        String sql = "SELECT cd.codigo_ID, m.titulo, cd.artista, cd.duracion, cd.genero, cd.canciones, cd.stock " +
             "FROM CD_Audio cd " +
             "JOIN Material m ON cd.codigo_ID = m.codigo_ID";

        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CdAudio cdAudio = new CdAudio();
                cdAudio.setCodigoId(rs.getString("codigo_ID"));
                cdAudio.setArtista(rs.getString("artista"));
                cdAudio.setDuracion(rs.getTime("duracion").toLocalTime());
                cdAudio.setGenero(rs.getString("genero"));
                cdAudio.setCanciones(rs.getInt("canciones"));
                cdAudio.setStock(rs.getInt("stock"));

                cdAudios.add(cdAudio);
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error al listar los CD_Audio: ", e);
        }

        return cdAudios;
    }
    public List<CdAudio> buscar(CdAudio cdAudio) throws SQLException {
        List<CdAudio> cdAudios = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT c.codigo_ID, m.titulo, c.artista, c.genero, c.duracion, c.canciones, c.stock "
                + "FROM CD_Audio c "
                + "JOIN Material m ON c.codigo_ID = m.codigo_ID "
                + "WHERE 1=1");

        List<Object> parametros = new ArrayList<>();

        if (cdAudio.getTitulo() != null && !cdAudio.getTitulo().isEmpty()) {
            sql.append(" AND m.titulo LIKE ?");
            parametros.add("%" + cdAudio.getTitulo() + "%");
        }
        if (cdAudio.getArtista() != null && !cdAudio.getArtista().isEmpty()) {
            sql.append(" AND c.artista LIKE ?");
            parametros.add("%" + cdAudio.getArtista() + "%");
        }
        if (cdAudio.getGenero() != null && !cdAudio.getGenero().isEmpty()) {
            sql.append(" AND c.genero LIKE ?");
            parametros.add("%" + cdAudio.getGenero() + "%");
        }

        try (Connection connection = Conexion.obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CdAudio resultado = new CdAudio();
                    resultado.setCodigoId(rs.getString("codigo_ID"));
                    resultado.setTitulo(rs.getString("titulo"));
                    resultado.setArtista(rs.getString("artista"));
                    resultado.setGenero(rs.getString("genero"));
                    resultado.setDuracion(rs.getTime("duracion").toLocalTime());
                    resultado.setCanciones(rs.getInt("canciones"));
                    resultado.setStock(rs.getInt("stock"));

                    cdAudios.add(resultado);
                }
            }
        } catch (SQLException e) {
            log.error("Ocurrió un error inesperado al buscar.", e.getMessage());
        }

        return cdAudios;
    }
}