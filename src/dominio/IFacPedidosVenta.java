package dominio;

import model.PedidosVenta;
import model.Producto;

public interface IFacPedidosVenta extends EFactory<PedidosVenta> {

	public PedidosVenta crear(int id, int idPedido, double precioFinal, double dctoPorcen, int cantidad, Producto producto);
	
}