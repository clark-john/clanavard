package clanavard;

import java.util.Arrays;
import java.util.List;

import clanavard.db.Database;
// import clanavard.internal.DurationParser;
import clanavard.listeners.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App implements Runnable {
	public static void main(String[] args) {
		// var parser = new DurationParser();
		// System.out.println(parser.parse("3m 45s 5h"));
		new Thread(new App()).start();
	}

	@Override
	public void run() {
		Dotenv dotenv = Dotenv.load();
		String token;

		String env = dotenv.get("JAVA_ENV");

		if (env != null && env.equals("PROD")) {
			token = dotenv.get("BOT_TOKEN");
		} else {
			token = System.getenv("BOTTEST_TOKEN");
		}

		if (token == null) {
			throw new RuntimeException("No token provided.");
		}

		Database db = Database.getInstance();
		db.initialize();

		List<GatewayIntent> intents = Arrays.asList(
			GatewayIntent.MESSAGE_CONTENT, 
			GatewayIntent.GUILD_MESSAGES
		);

		JDA jda = JDABuilder.create(token, intents)
			.addEventListeners(new MessageListener())
			.setActivity(Activity.playing("c?help"))
			.build();

		try {
			jda.awaitReady();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
