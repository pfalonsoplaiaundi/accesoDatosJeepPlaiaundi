package util;

// Config de bd
public class DBUtils {

	// La URL donde esta la Base de Datos. Se descompone en:
	// driver : bbd : // IP : Puerto / Schema
	private static final String SERVER = "localhost";
	private static final String PORT = "3306";
	private static final String DATABASE = "JeeP";
	
	// public static final String URL = "jdbc:mysql://localhost:3306/empresadamtarde";
	public static final String URL = "jdbc:mysql://" + SERVER + ":" + PORT +"/" + DATABASE;

	// El Driver que vamos a usar
	// siempre ponemos el mismo
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	// Nombre y Pass de acceso a la Base de Datos
	public static final String USER = "JeeP";
	public static final String PASS = "jip";
		
}
	

