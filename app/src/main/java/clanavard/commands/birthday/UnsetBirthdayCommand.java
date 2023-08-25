package clanavard.commands.birthday;

import java.util.List;

import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(name = "unsetbirthday", description = "Unset your birthday", category = "Birthday")
public class UnsetBirthdayCommand extends Command {
	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
	}
}
