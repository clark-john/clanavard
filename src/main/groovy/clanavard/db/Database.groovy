package clanavard.db

import org.apache.cayenne.ObjectContext
import clanavard.db.models.Config

class Database {
	private static boolean isInitialized = false
	private ObjectContext ctx
	
	private Database(){
	}
	
	private static Database db = new Database();
	
	static Database getInstance(){
		return db
	}
	
	void setObjectContext(ObjectContext ctx) {
		this.ctx = ctx
	}
		
	void initializeConfig(String guildId){
		def config = ctx.newObject(Config.class)
		config.setGuildId(guildId)
		ctx.commitChanges()
	}
	
	boolean isInitialized(){
		return isInitialized
	}
}
