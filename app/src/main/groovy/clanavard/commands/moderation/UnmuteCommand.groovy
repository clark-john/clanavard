package clanavard.commands.moderation

import clanavard.Utils
import clanavard.commands.Category
import clanavard.commands.CommandInfo
import clanavard.commands.PermissionsCommand
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(
	name = "unmute", 
	description = "Unmute a member (sometimes used when you accidentally mute a member in a long period of time)", 
	category = Category.MODERATION,
	args = "[MEMBER]"
)
class UnmuteCommand extends PermissionsCommand {
	UnmuteCommand(){
		super(Permission.MODERATE_MEMBERS)
	}

	@Override
	void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args)
		if (isAllowed) {
			if (args == null) {
				sendMessage(event, "Provide a value for member id")
				return
			}
			
			String member = args.get(0)

			Member mem

			try {
				mem = Utils.validUserIdElseError(event, member)	
			} catch (Exception e) {
				sendMessage(event, e.getMessage())
				return
			}

			if (mem.isTimedOut()) {
				mem.removeTimeout().queue()
				sendMessage(event, String.format("Successfully unmuted **%s**", mem.getEffectiveName()))
			} else {
				sendMessage(event, "This member isn't in a timeout period")
			}
		}
	}
}
