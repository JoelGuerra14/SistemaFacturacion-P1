package gestion.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/sistemafacturacionbd";
	private static final String USER = "root";
	private static final String PASSWORD = "j16o14s12e";
	private static Connection connection = null;
	
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
            throw new RuntimeException("Error al cargar el driver JDBC", e);
        }
    }
	
	public static Connection getConnection(){
		if (connection == null ) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}catch(SQLException e){
				System.err.println("Error SQL: " + e.getMessage());
			}
		}
		return connection;
	}
	
	public static void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			}catch(SQLException e){
				System.err.println("Error SQL: " + e.getMessage());
			}
		}
	}
	
}