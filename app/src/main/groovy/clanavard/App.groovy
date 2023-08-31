/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package clanavard

import clanavard.db.Database
import clanavard.listeners.MessageListener
import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

class App implements Runnable {
  static void main(String[] args) {
		new Thread(new App()).start()
	}
	
	@Override
	void run() {
		Dotenv dotenv = Dotenv.load()
		def token
		String env = System.getenv("JAVA_ENV");
		
		if (env && env.toLowerCase() == "prod") {
			token = dotenv.get("BOT_TOKEN")
		} else {
			token = System.getenv("BOTTEST_TOKEN")
		}
		
		if (!token) {
			throw new RuntimeException("No token provided")
		}

		Database db = Database.getInstance();
		db.initialize();
		
		def intents = [GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES]
		
		JDA jda = JDABuilder.create(token, intents)
			.addEventListeners(
				new MessageListener(),
				// new BirthdayListener()
			)
			.setActivity(Activity.playing("c?help"))
			.build()
			
		try {
			jda.awaitReady()
		} catch (Exception e) {
			throw new RuntimeException(e)
		}
	}
}
