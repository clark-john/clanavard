package clanavard.commands.birthday

import clanavard.commands.Category
import clanavard.commands.Command
import clanavard.commands.CommandInfo
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(
  name = "birthdays",
  description = "Display a list of incoming birthdays",
  category = Category.BIRTHDAY
)
class BirthdaysListCommand extends Command {
  @Override
  void handle(MessageReceivedEvent event, List<String> args) {
    sendMessage(event, "Under construction")
  }
}
