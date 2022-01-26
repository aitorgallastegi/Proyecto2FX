package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private Connection conexion;
	
	public ConexionBD() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/libros?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String username = "admin";
	    String password = "dm2";
	    conexion = DriverManager.getConnection(url, username, password);
	    conexion.setAutoCommit(true);
	}

	public Connection getConexion() {
		return conexion;
	}
	
}