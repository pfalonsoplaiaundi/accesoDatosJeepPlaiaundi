package infrastrutura;

import dominio.EFactory;
import dominio.model.Producto;
import infrastrutura.interfaces.IRepoProducto;
import infrastrutura.interfaces.ISerBD;

public class RepoProducto extends AbsRepo<Producto> implements IRepoProducto {

	public RepoProducto(ISerBD bd, EFactory<Producto> fac) {
		super(bd, fac, "producto");
	}
	
}
