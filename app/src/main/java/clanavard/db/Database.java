package clanavard.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import clanavard.Utils;

public class Database {
	private static boolean isInitialized = false;
	private Connection conn;
	private String connUri = "jdbc:sqlite:app.db";
	
	private Database(){}

	public static Database getInstance(){
		return new Database();
	}

	public void initialize(){
		try {
			if (!isInitialized) {
				conn = DriverManager.getConnection(connUri);
				Statement stmt = conn.createStatement();

				String birthdayTable = Utils.getResource("db/tables.sql");
				stmt.execute(birthdayTable);
				stmt.close();
				isInitialized = true;				
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}

	public boolean isInitialized(){
		return isInitialized;
	}

	public Connection getConnection(){
		return conn;
	}
}
