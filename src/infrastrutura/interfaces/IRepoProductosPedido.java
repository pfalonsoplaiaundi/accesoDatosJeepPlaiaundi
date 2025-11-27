package infrastrutura.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import dominio.model.ProductosPedido;

public interface IRepoProductosPedido extends IAbstracRepo<ProductosPedido> {

	ArrayList<ProductosPedido> getAllFromPedidoVenta(int idPedidoVenta) throws SQLException;

}
