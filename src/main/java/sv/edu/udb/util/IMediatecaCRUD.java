package sv.edu.udb.util;

import java.util.List;

public interface IMediatecaCRUD <T> {
    int crear(T entidad) throws Exception;
    int modificar(T entidad) throws Exception;
    int eliminar(String codigoId) throws Exception;
    List<T> listar() throws Exception;

}
