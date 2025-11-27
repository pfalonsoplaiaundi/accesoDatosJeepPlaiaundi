package dominio.factorias;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.model.Producto;
import dominio.model.Producto.Categoria;

public class FacProducto implements IFacProducto {

	@Override
	public Producto crearDesdeBD(ResultSet result) throws SQLException {
		return crear(
			result.getInt("id"),
			result.getString("alias"),
			result.getString("descripcion"),
			result.getDouble("precioVenta"),
			result.getDouble("precioCompra"),
			result.getInt("stock"),
			Producto.Categoria.valueOf(result.getString("categoria")),
			result.getInt("tipoIva")
			);
	}

	@Override
	public Producto crear(
		int id, 
		String alias, 
		String descripcion, 
		double precioVenta, 
		double precioCompra, 
		int stock,
		Categoria categoria, 
		int tipoIva
	) {
		return new Producto(id, alias, descripcion, precioVenta, precioCompra, stock, categoria, tipoIva);
	}

}
