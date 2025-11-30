package dominio.factorias.interfaces;

import dominio.EFactory;
import dominio.model.Producto;

/**
 * Contrato de factoria para la creacion de productos
 */
public interface IFacProducto extends EFactory<Producto> {

	/**
	 * Constructor de productos
	 * @param id
	 * @param alias
	 * @param descripcion
	 * @param precioVenta
	 * @param precioCompra
	 * @param stock
	 * @param categoria
	 * @param tipoIva
	 * @return
	 */
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
