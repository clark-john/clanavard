package clanavard.commands.birthday;

import java.util.List;

import clanavard.commands.AdminCommand;
import clanavard.commands.Category;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
	name = "setbdaychannel", 
	description = "Set the text channel where the bot will greet a happy birthday. If no channel argument, current channel will be set.", 
	category = Category.BIRTHDAY,
	args = "(CHANNEL)"
)
public class SetBirthdayChannelCommand extends AdminCommand {
	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args);
		if (isAdmin) {
			
		}
	}
}
