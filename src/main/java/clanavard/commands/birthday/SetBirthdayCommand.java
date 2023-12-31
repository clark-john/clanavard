package clanavard.commands.birthday;

import java.util.List;

import clanavard.commands.Category;
import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import clanavard.db.BirthdayManager;
import clanavard.internal.DateParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
	name = "setbirthday", 
	description = "Set your birthday and the bot will greet a happy birthday for you." +
    "Date argument should be in this format DD-MM-YYYY",
	category = Category.BIRTHDAY,
	args = "[DATE]"
)
public class SetBirthdayCommand extends Command {
	private final BirthdayManager bdayman;
	private DateParser parser;
	
	public SetBirthdayCommand(){
		super();
		parser = new DateParser();
		bdayman = new BirthdayManager(db);
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		if (args == null) {
			sendMessage(event, "Provide a date of your birthday");
			return;
		}

		String date = args.get(0);

		event.getMessage().getChannel().sendTyping().queue();

		if (parser.validate(date)) {
			bdayman.setBirthday(date, event.getAuthor().getId());
			sendMessage(event, "Birthday successfully set.");
		} else {
			sendMessage(event, "Invalid date. It should be in this format: DD-MM-YYYY");
		}
	}
}
