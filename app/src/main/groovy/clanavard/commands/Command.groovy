package clanavard.commands

import clanavard.db.Database
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class Command {
	private CommandInfo info
	public Database db
	
	protected Command(){
		info = getClass().getDeclaredAnnotation(CommandInfo.class)
		if (
			(getClass().getDeclaredAnnotation(CommandSubclass.class) == null && info == null) ||
			info == null
		) {
			throw new NullPointerException("Command info annotation is required!")
		}
		db = Database.getInstance()
	}
	
	void sendMessage(MessageReceivedEvent event, CharSequence content){
		event.getMessage().getChannel().sendMessage(content).queue()
	}

	abstract void handle(MessageReceivedEvent event, List<String> args)

	CommandInfo getCommandInfo(){
		return info
	}
}
