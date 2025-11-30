package app;

import java.util.ArrayList;

import app.interfaces.ISerBDJeep;
import dominio.model.PedidosVenta;
import infrastrutura.interfaces.IRepoPedidosVenta;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.IRepoProductosPedido;

/**
 * Servicio de conexion de la base de datos
 */
public class SerBDJeep implements ISerBDJeep {

	private IRepoPedidosVenta rPedidosVenta;
	private IRepoProducto rProducto;
	private IRepoProductosPedido rProductosPedido;
	
	public SerBDJeep(IRepoPedidosVenta rPedidosVenta, IRepoProducto rProducto, IRepoProductosPedido rProductosPedido) {
		this.rPedidosVenta = rPedidosVenta;
		this.rProducto = rProducto;
		this.rProductosPedido = rProductosPedido;
	}
	
	/**
	 * Funcion para mostrar los datos de backup
	 */
	@Override
	public void verDatosDeBackUp() throws Exception {
		for(PedidosVenta pedido: getBackUp()) {
			
			System.out.println(pedido);
		}
	}

	/**
	 * Funcion para pedir el backup a la base de datos
	 * como cada pedido contiene los demas objetos asociados
	 * solo pidiendo los pedidos la infrastructura genera los
	 * objetos necesarios para hacer el backup.
	 */
	@Override
	public ArrayList<PedidosVenta> getBackUp() throws Exception {
		return rPedidosVenta.getAll();
	}

}
