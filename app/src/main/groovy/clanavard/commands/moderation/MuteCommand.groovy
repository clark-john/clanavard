package clanavard.commands.moderation

import java.time.Duration

import clanavard.Utils
import clanavard.commands.Category
import clanavard.commands.CommandInfo
import clanavard.commands.PermissionsCommand
import clanavard.internal.DurationParser
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(
	name = "mute", 
	description = "Mute a member", 
	category = Category.MODERATION,
	args = "[MEMBER] [DURATION]"
)
class MuteCommand extends PermissionsCommand {
	private DurationParser durParser

	MuteCommand(){
		super(Permission.MODERATE_MEMBERS)
		durParser = new DurationParser()
	}

	void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args)

		if (isAllowed) {
			if (args == null) {
				sendMessage(event, "Please provide arguments for member, duration, and reason (optional)")
			}

			String memberId = args.get(0)
			String duration = (String) Utils.getOrNull(args, 1)

			Member mem

			try {
				mem = Utils.validUserIdElseError(event, memberId)
			} catch (Exception e) {
				sendMessage(event, e.getMessage())
				return
			}

			if (mem.getUser().isBot()) {
				sendMessage(event, "You can't mute a bot")
				return
			}

			if (mem.getId().equals(event.getAuthor().getId())) {
				sendMessage(event, "You can't mute yourself")
				return
			}

			if (!duration) {
				sendMessage(event, "Duration is required")
				return
			}

			Duration dur

			try {
				dur = durParser.parse(duration)
			} catch (Exception e){
				sendMessage(event, "**$duration** isn't a valid duration.".toString())
				return
			}

			mem.timeoutFor(dur).queue()
			
			sendMessage(event, String.format(
				"Successfully muted **${mem.getEffectiveName()}** in %s".toString(),
				durParser.toWord(dur))
			)
		}
	}
}
