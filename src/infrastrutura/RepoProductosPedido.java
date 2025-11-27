package infrastrutura;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.EFactory;
import dominio.model.ProductosPedido;
import infrastrutura.interfaces.IRepoProductosPedido;
import infrastrutura.interfaces.ISerBD;

public class RepoProductosPedido extends AbsRepo<ProductosPedido> implements IRepoProductosPedido {
	
	public RepoProductosPedido(ISerBD bd, EFactory<ProductosPedido> fac) {
		super(bd, fac, "PedidosVenta");
	}

	@Override
	public ArrayList<ProductosPedido> getAllFromPedidoVenta(int idPedidoVenta) throws SQLException {
		String query = "SELECT * FROM " + tabla + " WHERE idPedido = ?;";
		ResultSet rS = queryReturnParam(query, idPedidoVenta);
		ArrayList<ProductosPedido> result = new ArrayList<>();
		while (rS.next()) {
			result.add(fac.crearDesdeBD(rS));
		}
		return result;
	}
	
}
