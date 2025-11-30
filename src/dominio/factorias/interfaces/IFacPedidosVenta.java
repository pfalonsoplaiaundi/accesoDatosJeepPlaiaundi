package dominio.factorias.interfaces;

import java.sql.Date;
import java.util.ArrayList;

import dominio.EFactory;
import dominio.model.PedidosVenta;
import dominio.model.ProductosPedido;

/**
 * Contrato de factoria de creacion de pedidosVenta 
 */
public interface IFacPedidosVenta extends EFactory<PedidosVenta> {

	/**
	 * Basicamente el constructor de pedidosVenta
	 * @param id
	 * @param fecha
	 * @param bRecoger
	 * @param fecEntrega
	 * @param direccion
	 * @param idFactura
	 * @param idCliente
	 * @param productos
	 * @return
	 */
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