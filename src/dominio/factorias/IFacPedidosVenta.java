package dominio.factorias;

import java.sql.Date;
import java.util.ArrayList;

import dominio.EFactory;
import dominio.model.PedidosVenta;
import dominio.model.ProductosPedido;

public interface IFacPedidosVenta extends EFactory<PedidosVenta> {

	public PedidosVenta crear(
		int id, 
		Date fecha, 
		boolean bRecoger, 
		Date fecEntrega, 
		String direccion, 
		int idFactura,
		int idCliente, 
		ArrayList<ProductosPedido> productos
	);
	
}