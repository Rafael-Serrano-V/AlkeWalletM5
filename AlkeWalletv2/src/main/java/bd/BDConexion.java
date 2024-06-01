package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
	private static final String URL = "jdbc:mysql://localhost:3306/alke_wallet";
	private static final String USER = "root";
	private static final String PASSWORD = "amorcito";
	
	public static Connection getConexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
