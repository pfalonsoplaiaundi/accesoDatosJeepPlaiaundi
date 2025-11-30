package app.interfaces;

import java.util.ArrayList;

import dominio.model.PedidosVenta;

/**
 * Contrato de servicio de base de datos de la app
 */
public interface ISerBDJeep {

	void verDatosDeBackUp() throws Exception;
	ArrayList<PedidosVenta> getBackUp() throws Exception;

}
