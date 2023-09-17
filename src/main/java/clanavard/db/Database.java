package clanavard.db;

import clanavard.db.models.Config;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;

public class Database {
	private ObjectContext ctx;

	private static Database db = new Database();
	
	public static Database getInstance(){
		return db;
	}
	
	public void setObjectContext(ObjectContext ctx) {
		this.ctx = ctx;
	}
	
	public ObjectContext getObjectContext() {
		return ctx;
	}
		
	public void initializeConfig(String guildId){
		Config c = ObjectSelect
			.query(Config.class)
			.where(Config.GUILD_ID.eq(guildId))
			.selectFirst(ctx);
		if (c == null) {
			Config config = ctx.newObject(Config.class);
			config.setGuildId(guildId);
			ctx.commitChanges();
		}
	}
}
