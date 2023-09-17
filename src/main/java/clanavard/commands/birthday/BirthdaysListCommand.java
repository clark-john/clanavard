package clanavard.commands.birthday;

import java.util.List;

import clanavard.commands.Category;
import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
  name = "birthdays",
  description = "Display a list of incoming birthdays",
  category = Category.BIRTHDAY
)
public class BirthdaysListCommand extends Command {
  @Override
  public void handle(MessageReceivedEvent event, List<String> args) {
    sendMessage(event, "Under construction");
  }
}
