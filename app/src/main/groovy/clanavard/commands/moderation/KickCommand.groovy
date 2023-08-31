package clanavard.commands.moderation

import clanavard.Utils
import clanavard.commands.Category
import clanavard.commands.CommandInfo
import clanavard.commands.PermissionsCommand
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(name = "kick", description = "Kick a member", category = Category.MODERATION, args = "[MEMBER]")
class KickCommand extends PermissionsCommand {
	public KickCommand(){
		super(Permission.KICK_MEMBERS)
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args)
		if (isAllowed) {
			if (args == null) {
				sendMessage(event, "Ping a member to kick")
				return
			}

			String memberId = args.get(0)
			Member mem

			try {
				mem = Utils.validUserIdElseError(event, memberId)
				mem.kick().queue()
				sendMessage(event, "Successfully kicked **${mem.getEffectiveName()}**".toString())
			} catch (Exception e) {
				sendMessage(event, e.getMessage())
				return
			}
		}
	}
}
