package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Etiqueta de marcado de factoria
 * @param <T> Usa el etiquetado de modelo de dominio para evitar errores
 */
public interface EFactory<T extends EModel> {
	// Etiqueta de marcado de factoria
	public T crearDesdeBD(ResultSet result) throws SQLException;
	
}
