package infrastrutura;

import dominio.EFactory;
import dominio.model.PedidosVenta;
import infrastrutura.interfaces.IRepoPedidosVenta;
import infrastrutura.interfaces.ISerBD;

public class RepoPedidosVenta extends AbsRepo<PedidosVenta> implements IRepoPedidosVenta {
	
	public RepoPedidosVenta(ISerBD bd, EFactory<PedidosVenta> fac) {
		super(bd, fac, "PedidosVenta");
	}
	
}
