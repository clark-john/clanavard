package clanavard.commands.birthday;

import java.util.List;

import clanavard.Utils;
import clanavard.commands.Category;
import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import clanavard.db.BirthdayManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(name = "unsetbirthday", description = "Unset your birthday", category = Category.BIRTHDAY)
public class UnsetBirthdayCommand extends Command {
	private final BirthdayManager bdayman;
	public UnsetBirthdayCommand(){
		super();
		bdayman = new BirthdayManager(db);
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		String id = event.getAuthor().getId();
		try {
			Member mem = Utils.validUserIdElseError(event, id);
			bdayman.unsetBirthday(mem.getId());
			sendMessage(event, "Successfully unset your birthday");
		} catch (Exception e){
			sendMessage(event, e.getMessage());
		}
	}
}
