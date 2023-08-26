package clanavard.commands.moderation;

import java.util.List;

import clanavard.commands.AdminCommand;
import clanavard.commands.CommandInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(name = "mute", description = "Mute a member", category = "Moderation")
public class MuteCommand extends AdminCommand {
	public void handle(MessageReceivedEvent event, List<String> args) {
		// event.getMember().
		super.handle(event, args);
		if (isAdmin) {
			/*
				first arg: "user"
				second arg: "duration"
				third arg: "reason"
			*/
			// event.getGuild().getMemberById("dsfsd").time
			// event.getGuild().getMemberById("1312323123").timeoutFor(Duration.);
			// event.getGuild().getMemberById("1312323123").mute(true).queue();
			// sendMessage(event, "still in construction");
		}
	}
}
