package clanavard.commands;

import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
	private CommandName name;
	protected Command(){
		name = getClass().getDeclaredAnnotation(CommandName.class);
		if (
			(getClass().getDeclaredAnnotation(CommandSubclass.class) == null && name == null) ||
			name == null
		) {
			throw new NullPointerException("Command name annotation is required!");
		}
	}

	public void sendMessage(MessageReceivedEvent event, CharSequence content){
		event.getMessage().getChannel().sendMessage(content).queue();
	}

	public abstract void handle(MessageReceivedEvent event, List<String> args);
}
