package sv.edu.udb.util;

import java.util.List;

public interface IMediatecaCRUD <T> {
    int crear(T entidad) throws Exception;      // Cambiar a T en lugar de Class<?>
    int modificar(T entidad) throws Exception;  // Cambiar a T en lugar de Class<?>
    int eliminar(String codigoId) throws Exception;   // Cambiar a T en lugar de Class<?>
    List<T> listar() throws Exception;

}
