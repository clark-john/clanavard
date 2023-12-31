package clanavard.commands;

import java.util.List;

import clanavard.db.Database;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
	private CommandInfo info;
	public Database db;
	
	protected Command(){
		info = getClass().getDeclaredAnnotation(CommandInfo.class);
		if (
			(getClass().getDeclaredAnnotation(CommandSubclass.class) == null && info == null) ||
			info == null
		) {
			throw new NullPointerException("Command info annotation is required!");
		}
		db = Database.getInstance();
	}
	
	public void sendMessage(MessageReceivedEvent event, CharSequence content){
		event.getMessage().getChannel().sendMessage(content).queue();
	}

	public abstract void handle(MessageReceivedEvent event, List<String> args);

	public CommandInfo getCommandInfo(){
		return info;
	}
}
