package clanavard.commands.birthday;

import java.util.List;

import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
	name = "setbirthday", 
	description = "Set your birthday and the bot will greet a happy birthday for you." + 
	" Date argument should be in this format DD-MM-YYYY", 
	category = "Birthday"
)
public class SetBirthdayCommand extends Command {
	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
	}
}
