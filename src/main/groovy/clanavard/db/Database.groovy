package clanavard.db

import clanavard.db.models.Config
import org.apache.cayenne.ObjectContext
import org.apache.cayenne.query.ObjectSelect

class Database {
	private ObjectContext ctx

	private static Database db = new Database();
	
	static Database getInstance(){
		return db
	}
	
	void setObjectContext(ObjectContext ctx) {
		this.ctx = ctx
	}
	
	ObjectContext getObjectContext() {
		return ctx
	}
		
	void initializeConfig(String guildId){
		def c = ObjectSelect
			.query(Config.class)
			.where(Config.GUILD_ID.eq(guildId))
			.selectFirst(ctx)
		if (!c) {
			def config = ctx.newObject(Config.class)
			config.setGuildId(guildId)
			ctx.commitChanges()
		}
	}
}
