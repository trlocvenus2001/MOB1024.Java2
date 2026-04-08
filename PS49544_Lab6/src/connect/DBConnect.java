package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static final String HOSTNAME = "localhost";
	public static final String PORT = "3306";
	public static final String DBNAME = "lab6_jdbc";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "12345678";

	public static Connection getConnection() {
		String connectionUrl = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME
				+ "?useSSL=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
}