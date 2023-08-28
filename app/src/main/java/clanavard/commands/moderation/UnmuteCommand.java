package clanavard.commands.moderation;

import java.util.List;

import clanavard.Utils;
import clanavard.commands.AdminCommand;
import clanavard.commands.Category;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
	name = "unmute", 
	description = "Unmute a member (sometimes used when you accidentally mute a member in a long period of time)", 
	category = Category.MODERATION,
	args = "[MEMBER]"
)
public class UnmuteCommand extends AdminCommand {
	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args);
		if (isAdmin) {
			if (args == null) {
				sendMessage(event, "Provide a value for member id");
				return;
			}
			
			String member = args.get(0);

			Member mem;

			try {
				mem = Utils.validUserIdElseErrorMessage(event, member);	
			} catch (Exception e) {
				sendMessage(event, e.getMessage());
				return;
			}

			if (mem.isTimedOut()) {
				mem.removeTimeout().queue();
				sendMessage(event, String.format("Successfully unmuted **%s**", mem.getEffectiveName()));
			} else {
				sendMessage(event, "This member isn't in a timeout period");
			}
		}
	}
}
