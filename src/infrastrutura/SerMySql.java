package infrastrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SerMySql implements ISerBD{

	private final Connection conn;
	
	public SerMySql(String driver, String bdUrl, String user, String pass) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(bdUrl, user, pass);
	}

	public Connection getConn() {
		return conn;
	}
	
}
