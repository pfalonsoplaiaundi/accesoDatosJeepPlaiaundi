package dominio.factorias.interfaces;

import dominio.EFactory;
import dominio.model.Producto;
import dominio.model.ProductosPedido;

/**
 * Contrato de factoria de productosPedido
 */
public interface IFacProductosPedido extends EFactory<ProductosPedido> {

	/**
	 * Constructor de productosPedido
	 * @param id
	 * @param idPedido
	 * @param precioFinal
	 * @param dctoPorcen
	 * @param cantidad
	 * @param producto
	 * @return
	 */
	public ProductosPedido crear(
		int id, 
		int idPedido, 
		double precioFinal, 
		double dctoPorcen, 
		int cantidad, 
		Producto producto
	);
	
	
	
}
