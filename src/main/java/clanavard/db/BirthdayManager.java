package clanavard.db;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import clanavard.db.models.Birthday;
import clanavard.db.models.Config;

public class BirthdayManager {
	private ObjectContext ctx;

	public BirthdayManager(Database db) {
		ctx = db.getObjectContext();
	}

	public void setBirthdayChannel(String channelId, String guildId){		
		Config config = ObjectSelect
			.query(Config.class)
			.where(Config.GUILD_ID.eq(guildId))
			.selectFirst(ctx);
			
		if (config.getBirthdayChannel() == null) {
			config.setBirthdayChannel(channelId);
			ctx.commitChanges();
		}			
	}

	public void setBirthday(String birthday, String userId){
		Birthday bday = ObjectSelect
			.query(Birthday.class)
			.where(Birthday.USER_ID.eq(userId))
			.selectFirst(ctx);

		if (bday == null) {
			var b = ctx.newObject(Birthday.class);
			b.setUserId(userId);
			b.setBirthday(birthday);
		} else {
			bday.setBirthday(birthday);
		}
		ctx.commitChanges();
	}

	public boolean unsetBirthday(String userId){
		var bday = ObjectSelect.query(Birthday.class)
			.where(Birthday.USER_ID.eq(userId))
			.selectFirst(ctx);
			
		if (bday == null) {
			return false;
		}
		ctx.deleteObject(bday);
		return true;
	}
}
