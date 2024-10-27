package sv.edu.udb.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JUtil {
    public static Logger getLogger(Class<?> clase) {
        return LogManager.getLogger(clase);
    }
}
