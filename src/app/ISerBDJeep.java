package app;

import java.util.ArrayList;

import dominio.model.PedidosVenta;

public interface ISerBDJeep {

	void verDatosDeBackUp() throws Exception;
	ArrayList<PedidosVenta> getBackUp() throws Exception;

}
