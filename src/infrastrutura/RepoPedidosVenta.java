package infrastrutura;

import dominio.EFactory;
import model.PedidosVenta;

public class RepoPedidosVenta extends AbsRepo<PedidosVenta> {
	
	public RepoPedidosVenta(ISerBD bd, EFactory<PedidosVenta> fac) {
		super(bd, fac, "PedidosVenta");
	}
	
}
