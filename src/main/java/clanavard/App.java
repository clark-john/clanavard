package clanavard;

import clanavard.db.Database;
import clanavard.listeners.BirthdayListener;
import clanavard.listeners.MessageListener;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

public class App implements Runnable {
	private JDA jda;
	private ServerRuntime cayenne;
	private ObjectContext ctx;
	
  public static void main(String[] args) {
		new Thread(new App()).start();
	}
	
	@Override
	public void run() {
		cayenne = ServerRuntime.builder()
			.addConfig("cayenne-project.xml")
			.build();
		
		ctx = cayenne.newContext();
				
		Dotenv dotenv = Dotenv.load();
		String token;
		String env = System.getenv("JAVA_ENV");
		
		if (env != null && env.toLowerCase().equals("prod")) {
			token = dotenv.get("BOT_TOKEN");
		} else {
			token = System.getenv("BOTTEST_TOKEN");
		}
		
		if (token == null) {
			throw new RuntimeException("No token provided");
		}

		var db = Database.getInstance();
		db.setObjectContext(ctx);
		
		jda = JDABuilder.create(token, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
			.addEventListeners(
				new MessageListener(),
				new BirthdayListener()
			)
			.setActivity(Activity.playing("c?help"))
			.build();
			
		try {
			jda.awaitReady();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
