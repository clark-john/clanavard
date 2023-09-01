package clanavard.commands.birthday

import clanavard.commands.Category
import clanavard.commands.Command
import clanavard.commands.CommandInfo
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(name = "unsetbirthday", description = "Unset your birthday", category = Category.BIRTHDAY)
class UnsetBirthdayCommand extends Command {
	@Override
	void handle(MessageReceivedEvent event, List<String> args) {
	}
}
