package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static final String HOSTNAME = "localhost";
	private static final String PORT = "3306";
	private static final String DBNAME = "lab7_jdbc";
	private static final String USER = "root";
	private static final String PASSWORD = "12345678";
	private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
