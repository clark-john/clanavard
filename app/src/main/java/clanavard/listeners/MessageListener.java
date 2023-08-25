package clanavard.listeners;

import clanavard.commands.GetAllCommands;
import clanavard.internal.CommandProcessor;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	private CommandProcessor cmd;
	public MessageListener(){
		cmd = new CommandProcessor("c?");
		cmd.setCommands(GetAllCommands.getAll());
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (!event.getAuthor().isBot()) {
			cmd.process(event);
		}
	}

	@Override
	public void onGatewayPing(GatewayPingEvent event) {
    super.onGatewayPing(event);
    // new MessageReceivedEvent(event.getJDA(), event.getResponseNumber());
    // System.out.println(event);
	}
}
