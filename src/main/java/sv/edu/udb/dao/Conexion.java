package sv.edu.udb.dao;

import java.sql.*;

import org.apache.logging.log4j.Logger;
import sv.edu.udb.util.Log4JUtil;

public class Conexion {
    private static Logger log = Log4JUtil.getLogger(Conexion.class);
    private static Connection connection;

    // TODO Ojo, cambiar al usuario correspondiente de Base de Datos (en caso de ser necesario)
    private static String usuario = "root";

    // TODO Ojo, cambiar la clave correspondiente al usuario de Base de datos (en caso de ser necesario)
    private static String clave = "PA$$123";
    private static String servidor = "localhost";
    private static String puerto = "3306";
    private static String nombreBaseDeDatos = "mediateca";

    private static String timeZone = "?serverTimeZone=UTC";    // Se pone formato de TimeZone en caso de dar error la conexion de la base de datos

    private static String url = "jdbc:mysql://" +servidor + ":" + puerto + "/" + nombreBaseDeDatos + timeZone;
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection obtenerConexion() throws SQLException {
        if(connection == null || connection.isClosed()) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, usuario, clave);
            } catch (ClassNotFoundException cnfe) {
                log.error("Ocurrio un error: ", cnfe);
            }
        }
        return connection;
    }

    public static Statement createStatement(Connection pConn) throws SQLException {
        Statement statement = pConn.createStatement();
        return statement;
    }

    public static PreparedStatement createPreparedStatement(Connection pConn, String pSql) throws SQLException {
        PreparedStatement statement = pConn.prepareStatement(pSql);
        return statement;
    }

    public static ResultSet obtenerResultSet(Statement pStatement, String pSql) throws SQLException {
        ResultSet resultSet = pStatement.executeQuery(pSql);
        return resultSet;
    }

    public static ResultSet obtenerResultSet(PreparedStatement pPreparedStatement) throws SQLException {
        ResultSet resultSet = pPreparedStatement.executeQuery();
        return resultSet;
    }

    public static void cerrar(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static void cerrar(Statement stmt) throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    }

    public static void cerrar(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
}
