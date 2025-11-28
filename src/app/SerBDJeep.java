package app;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.model.PedidosVenta;
import infrastrutura.interfaces.IRepoPedidosVenta;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.IRepoProductosPedido;

public class SerBDJeep implements ISerBDJeep {

	private IRepoPedidosVenta rPedidosVenta;
	private IRepoProducto rProducto;
	private IRepoProductosPedido rProductosPedido;
	
	public SerBDJeep(IRepoPedidosVenta rPedidosVenta, IRepoProducto rProducto, IRepoProductosPedido rProductosPedido) {
		this.rPedidosVenta = rPedidosVenta;
		this.rProducto = rProducto;
		this.rProductosPedido = rProductosPedido;
	}
	
	
	@Override
	public void verDatosDeBackUp() throws Exception {
		for(PedidosVenta pedido: getBackUp()) {
			
			System.out.println(pedido);
		}
	}


	@Override
	public ArrayList<PedidosVenta> getBackUp() throws Exception {
		return rPedidosVenta.getAll();
	}

}
