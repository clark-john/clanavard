package clanavard.commands.birthday

import clanavard.Utils
import clanavard.commands.Category
import clanavard.commands.Command
import clanavard.commands.CommandInfo
import clanavard.db.BirthdayManager
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(name = "unsetbirthday", description = "Unset your birthday", category = Category.BIRTHDAY)
class UnsetBirthdayCommand extends Command {
	private final BirthdayManager bdayman
	UnsetBirthdayCommand(){
		super()
		bdayman = new BirthdayManager(db)
	}
	@Override
	void handle(MessageReceivedEvent event, List<String> args) {
		def id = event.getAuthor().getId()
		try {
			def mem = Utils.validUserIdElseError(event, id)
			bdayman.unsetBirthday(mem.getId())
			sendMessage(event, "Successfully unset your birthday")
		} catch (Exception e){
			sendMessage(event, e.getMessage())
		}
	}
}
