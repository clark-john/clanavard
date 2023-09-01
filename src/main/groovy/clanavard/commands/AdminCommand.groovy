package clanavard.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandSubclass
abstract class AdminCommand extends Command {
	boolean isAdmin = false
	void handle(MessageReceivedEvent event, List<String> args) {
		if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
			sendMessage(event, "You need to be an admin to use this command.")
			return
		}
		isAdmin = true
	}
}
