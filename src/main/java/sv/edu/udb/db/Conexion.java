package sv.edu.udb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.util.Log4JUtil;

public class Conexion {
    private Logger log = Log4JUtil.getLogger(Conexion.class);
    private Connection connection;
    private String usuario = "root";
    private String clave = "admin";
    private String servidor = "localhost";
    private String puerto = "3306";
    private String nombreBaseDeDatos = "mediateca";

    private String timeZone = "?serverTimeZone=UTC";

    private String url = "jdbc:mysql://" +servidor + ":" + puerto + "/" + nombreBaseDeDatos + timeZone;
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Conexion () {
        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(url, usuario, clave);

            if(connection != null)
                System.out.println("Conexión exitosa");

        } catch (Exception e) {
            System.out.println("Ocurrio un error al hacer la conexion" + e.getMessage());

            log.error("Error: ", e);
        }
    }
}
