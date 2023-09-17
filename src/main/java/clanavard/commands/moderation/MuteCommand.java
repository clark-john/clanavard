package clanavard.commands.moderation;

import java.time.Duration;
import java.util.List;

import clanavard.Utils;
import clanavard.commands.Category;
import clanavard.commands.CommandInfo;
import clanavard.commands.PermissionsCommand;
import clanavard.internal.DurationParser;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
	name = "mute", 
	description = "Mute a member", 
	category = Category.MODERATION,
	args = "[MEMBER] [DURATION]"
)
public class MuteCommand extends PermissionsCommand {
	DurationParser durParser;

	public MuteCommand(){
		super(Permission.MODERATE_MEMBERS);
		durParser = new DurationParser();
	}

	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args);

		if (isAllowed) {
			if (args == null) {
				sendMessage(event, "Please provide arguments for member, duration, and reason (optional)");
				return;
			}

			String memberId = args.get(0);
			String duration = (String) Utils.getOrNull(args, 1);

			Member mem;

			try {
				mem = Utils.validUserIdElseError(event, memberId);
			} catch (Exception e) {
				sendMessage(event, e.getMessage());
				return;
			}

			if (mem.getUser().isBot()) {
				sendMessage(event, "You can't mute a bot");
				return;
			}

			if (mem.getId().equals(event.getAuthor().getId())) {
				sendMessage(event, "You can't mute yourself");
				return;
			}

			if (duration == null) {
				sendMessage(event, "Duration is required");
				return;
			}

			Duration dur;

			try {
				dur = durParser.parse(duration);
			} catch (Exception e){
				sendMessage(event, String.format("**%s** isn't a valid duration.", duration));
				return;
			}

			mem.timeoutFor(dur).queue();
			
			sendMessage(event, String.format(
				"Successfully muted **%s** in %s".toString(),
				mem.getEffectiveName(),
				durParser.toWord(dur))
			);
		}
	}
}
