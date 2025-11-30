package infrastrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import infrastrutura.interfaces.ISerBD;

/**
 * Servicio para obtener la conexion a la bd
 */
public class SerMySql implements ISerBD{

	private final Connection conn;
	
	/** 
	 * Carga del driver y establece conexion con al base de datos
	 * @param driver Nombre del driver a usar
	 * @param bdUrl URL de la base de datos
	 * @param user Usuario de la base de datos
	 * @param pass
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public SerMySql(String driver, String bdUrl, String user, String pass) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(bdUrl, user, pass);
	}

	public Connection getConn() {
		return conn;
	}
	
}
