package dominio.factorias;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.factorias.interfaces.IFacPedidosVenta;
import dominio.model.PedidosVenta;
import dominio.model.Producto;
import dominio.model.ProductosPedido;
import infrastrutura.interfaces.IRepoProductosPedido;

/**
 * Factoria de creacion de pedidos de venta
 */
public class FacPedidosVenta implements IFacPedidosVenta {

	private IRepoProductosPedido rProductosPedido;
	
	public FacPedidosVenta(
		IRepoProductosPedido rProductosPedido
	) {
		this.rProductosPedido = rProductosPedido;
	}
	
	@Override
	public PedidosVenta crearDesdeBD(ResultSet result) throws SQLException {
		ArrayList<ProductosPedido> productos = rProductosPedido.getAllFromPedidoVenta(result.getInt("id"));		
		return crear(
			result.getInt("id"),
			result.getDate("fecha"),
			result.getBoolean("bRecoger"),
			result.getDate("fecEntrega"),
			result.getString("direccion"),
			result.getInt("idFactura"),
			result.getInt("idCliente"),
			productos
		);
	}

	@Override
	public PedidosVenta crear(
		int id, 
		Date fecha, 
		boolean bRecoger, 
		Date fecEntrega, 
		String direccion, 
		int idFactura,
		int idCliente, 
		ArrayList<ProductosPedido> productos
	) {
		return new PedidosVenta(
			id,
			fecha,
			bRecoger,
			fecEntrega,
			direccion,
			idFactura,
			idCliente,
			productos
		);
	}

}
