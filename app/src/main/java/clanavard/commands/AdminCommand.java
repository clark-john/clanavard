package clanavard.commands;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandSubclass()
public class AdminCommand extends Command {
	public boolean isAdmin = false;
	public void handle(MessageReceivedEvent event, List<String> args) {
		if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
			sendMessage(event, "You need to be an admin to use this command.");
			return;
		}
		isAdmin = true;
	}
}
