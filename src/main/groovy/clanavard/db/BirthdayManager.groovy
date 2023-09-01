package clanavard.db

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class BirthdayManager {
	private Connection conn
	private PreparedStatement stmt

	BirthdayManager(Database db) {
		conn = db.getConnection()
	}

	void setBirthdayChannel(String channelId, String guildId){
		try {
			stmt = conn.prepareStatement("update config set birthdayChannel = ? where guildId = ?")
			stmt.setString(1, channelId)
			stmt.setString(2, guildId)
			stmt.execute()
			stmt.close()
		} catch (SQLException e){
			e.printStackTrace()
		}
	}

	void setBirthday(String birthday, String userId){
		try {
			stmt = conn.prepareStatement("select * from birthdays where userId = ?")
			stmt.setString(1, userId)
			def set = stmt.executeQuery()
			set.next()

			String sql

			if (!set.isFirst()) {
				sql = "insert into birthdays (birthday, userId) values (?, ?)"
			} else {
				sql = "update birthdays set birthday = ? where userId = ?"
			}

			stmt = conn.prepareStatement(sql)

			stmt.setString(1, birthday)
			stmt.setString(2, userId)
			stmt.execute()
			stmt.close()
		} catch (SQLException e){
			e.printStackTrace()
		}
	}

	// true if it is successfully deleted otherwise false if it doesn't exist/unsuccess
	boolean unsetBirthday(String userId){
		try {
			stmt = conn.prepareStatement("select * from birthdays where userId = ?")
			stmt.setString(1, userId)
			def set = stmt.executeQuery()
			set.next()

			if (!set.isFirst()) {
				return false
			}

			stmt = conn.prepareStatement("delete from birthdays where userId = ?")
			stmt.setString(1, userId)
			stmt.execute()
			return true

		} catch (SQLException e){
			e.printStackTrace()
			return false
		}
	}
}
