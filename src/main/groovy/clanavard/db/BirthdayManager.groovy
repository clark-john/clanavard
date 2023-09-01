package clanavard.db

import org.apache.cayenne.ObjectContext
import org.apache.cayenne.query.ObjectSelect
import clanavard.db.models.Birthday
import clanavard.db.models.Config

class BirthdayManager {
	private ObjectContext ctx

	BirthdayManager(Database db) {
		ctx = db.getObjectContext()
	}

	void setBirthdayChannel(String channelId, String guildId){		
		def config = ObjectSelect
			.query(Config.class)
			.where(Config.GUILD_ID.eq(guildId))
			.selectFirst(ctx)
			
		if (!config.getBirthdayChannel()) {
			config.setBirthdayChannel(channelId)
			ctx.commitChanges()
		}			
	}

	void setBirthday(String birthday, String userId){
		def bday = ObjectSelect
			.query(Birthday.class)
			.where(Birthday.USER_ID.eq(userId))
			.selectFirst(ctx)

		if (!bday) {
			def b = ctx.newObject(Birthday.class)
			b.setUserId(userId)
			b.setBirthday(birthday)
		} else {
			bday.setBirthday(birthday)
		}
		ctx.commitChanges()
	}

	boolean unsetBirthday(String userId){
		def bday = ObjectSelect.query(Birthday.class)
			.where(Birthday.USER_ID.eq(userId))
			.selectFirst(ctx)
			
		if (!bday) {
			return false
		}
		ctx.deleteObject(bday)
		return true
	}
}
