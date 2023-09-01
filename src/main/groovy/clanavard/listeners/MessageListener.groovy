package clanavard.listeners;

import clanavard.commands.GetAllCommands
import clanavard.db.Database
import clanavard.internal.CommandProcessor
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener extends ListenerAdapter {
	private CommandProcessor cmd;
	private Database db;

	MessageListener(){
		cmd = new CommandProcessor("c?");
		cmd.setCommands(GetAllCommands.getAll());
		db = Database.getInstance();
	}

	@Override
	void onMessageReceived(MessageReceivedEvent event) {
		db.initializeConfig(event.getGuild().getId());
		if (!event.getAuthor().isBot()) {
			cmd.process(event);
		}
	}
}
