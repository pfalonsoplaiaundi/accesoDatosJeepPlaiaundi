package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EFactory<T extends EModel> {
	// Etiqueta de marcado de factoria
	public T crearDesdeBD(ResultSet result) throws SQLException;
	
}
