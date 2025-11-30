package infrastrutura;

import dominio.EFactory;
import dominio.model.Producto;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.ISerBD;

/**
 * Repositorio de productos
 */
public class RepoProducto extends AbsRepo<Producto> implements IRepoProducto {

	public RepoProducto(ISerBD bd, EFactory<Producto> fac) {
		super(bd, fac, "productos");
	}
	
}
