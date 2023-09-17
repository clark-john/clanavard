package clanavard.commands;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandSubclass()
public abstract class PermissionsCommand extends Command {
	protected boolean isAllowed = false;
	private Permission[] requiredPermissions;

	protected PermissionsCommand(Permission... permissions){
		requiredPermissions = permissions;
	}
	
	public void handle(MessageReceivedEvent event, List<String> args) {
		if (!event.getMessage().getMember().hasPermission(requiredPermissions)) {
			sendMessage(event, "You're not eligible to run this command.");
			return;
		}
		isAllowed = true;
	}
}
