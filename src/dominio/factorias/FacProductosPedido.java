package dominio.factorias;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.factorias.interfaces.IFacProductosPedido;
import dominio.model.Producto;
import dominio.model.ProductosPedido;
import infrastrutura.interfaces.IRepoProducto;

/**
 * Factoria de creacion de productos pedido
 */
public class FacProductosPedido implements IFacProductosPedido {

	private IRepoProducto rProducto;
	
	public FacProductosPedido(IRepoProducto rProducto) {
		this.rProducto = rProducto;
	}
	
	
	@Override
	public ProductosPedido crearDesdeBD(ResultSet result) throws SQLException {
		Producto producto = rProducto.get(result.getInt("idProducto"));
		return crear(
				result.getInt("id"),
				result.getInt("idPedido"),
				result.getDouble("precioFinal"),
				result.getDouble("dctoPorcen"),
				result.getInt("cantidad"),
				producto
				);
	}

	@Override
	public ProductosPedido crear(
		int id, 
		int idPedido, 
		double precioFinal, 
		double dctoPorcen, 
		int cantidad,
		Producto producto
	) {
		return new ProductosPedido(id, idPedido, precioFinal, dctoPorcen, cantidad, producto);
	}



}
