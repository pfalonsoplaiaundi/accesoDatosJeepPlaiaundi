package dominio.factorias;

import dominio.EFactory;
import dominio.model.Producto;

public interface IFacProducto extends EFactory<Producto> {

	public Producto crear(
			int id, 
			String alias, 
			String descripcion,
			double precioVenta,
			double precioCompra,
			int stock,
			Producto.Categoria categoria,
			int tipoIva
			);
		
}
