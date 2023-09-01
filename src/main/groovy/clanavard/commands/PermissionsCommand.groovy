package clanavard.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandSubclass()
abstract class PermissionsCommand extends Command {
	boolean isAllowed = false
	private Permission[] requiredPermissions

	protected PermissionsCommand(Permission... permissions){
		requiredPermissions = permissions
	}
	
	void handle(MessageReceivedEvent event, List<String> args) {
		if (!event.getMessage().getMember().hasPermission(requiredPermissions)) {
			sendMessage(event, "You're not eligible to run this command.")
			return
		}
		isAllowed = true
	}
}
