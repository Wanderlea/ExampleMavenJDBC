//Wanderleá lodi
//15/06/2017

package connectionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/javajdbc";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	static{
		 connect();
	}
	
	private SingleConnection() {
		 connect();
	}
	
	private static void connect(){
		try {
			
			if(connection == null){
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Connect OK!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return connection;
	}

}
