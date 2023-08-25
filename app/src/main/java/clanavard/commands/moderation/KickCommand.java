package clanavard.commands.moderation;

import java.util.Arrays;
import java.util.List;

import clanavard.Utils;
import clanavard.commands.CommandInfo;
import clanavard.commands.PermissionsCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(name = "kick", description = "Kick a member", category = "Moderation")
public class KickCommand extends PermissionsCommand {
	public KickCommand(){
		super(Arrays.asList(Permission.KICK_MEMBERS));
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args);
		if (isAllowed) {
			if (args == null) {
				sendMessage(event, "Ping a member to kick");
				return;
			}
			// String member = args.get(0);
			// String reason = Utils.getOrNull(args, 1);
			// event.getGuild().kick(event.getGuild().getMe)
			// System.out.println();
		}
	}
}
