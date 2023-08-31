package clanavard.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

import clanavard.Utils

class Database {
	private static boolean isInitialized = false;
	private Connection conn;
	private String connUri = "jdbc:sqlite:app.db";
	
	private Database(){
		Runtime.getRuntime().addShutdownHook(new Thread({ ->
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}));
	}
	
	private static Database db = new Database();
	
	static Database getInstance(){
		return db;
	}
	
	void initialize(){
		try {
			if (!isInitialized) {
				conn = DriverManager.getConnection(connUri);
				Statement stmt = conn.createStatement();

				String tables = Utils.getResource("db/tables.sql");
				for (String st : tables.split(";")) {
					stmt.execute(st);
				}
				stmt.close();
				isInitialized = true;
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	void initializeConfig(String guildId){
		PreparedStatement insert;
		PreparedStatement st;
		try {
			st = conn.prepareStatement("select * from config where guildId = ?");
			st.setString(1, guildId);
			def set = st.executeQuery();
			set.next();

			if (!set.isFirst()) {
				insert = conn.prepareStatement("insert into config (guildId) values (?)");
				insert.setString(1, guildId);
				insert.execute();
				insert.close();
			}
			
			set.close();
			st.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	boolean isInitialized(){
		return isInitialized;
	}

	Connection getConnection(){
		return conn;
	}
}
