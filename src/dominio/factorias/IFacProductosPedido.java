package dominio.factorias;

import dominio.EFactory;
import dominio.model.Producto;
import dominio.model.ProductosPedido;

public interface IFacProductosPedido extends EFactory<ProductosPedido> {

	public ProductosPedido crear(
		int id, 
		int idPedido, 
		double precioFinal, 
		double dctoPorcen, 
		int cantidad, 
		Producto producto
	);
	
	
	
}
