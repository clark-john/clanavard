package clanavard.commands.birthday

import clanavard.commands.Category
import clanavard.commands.CommandInfo
import clanavard.commands.PermissionsCommand
import clanavard.db.BirthdayManager
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@CommandInfo(
	name = "setbdaychannel",
	description = "Set the text channel where the bot will greet a happy birthday. If no channel argument, current channel will be set.", 
	category = Category.BIRTHDAY,
	args = "(CHANNEL)"
)
class SetBirthdayChannelCommand extends PermissionsCommand {
	private final BirthdayManager bdayman

	public SetBirthdayChannelCommand(){
		super(Permission.MANAGE_CHANNEL)
		bdayman = new BirthdayManager(db)
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		super.handle(event, args)

		String guildId = event.getGuild().getId()

		if (isAllowed) {
			if (args == null){
				String channelId = event.getMessage().getChannel().asTextChannel().getId()
				bdayman.setBirthdayChannel(channelId, guildId)
				sendMessage(event, String.format("Successfully set the birthday channel to <#%s>", channelId))
				return
			}

			String channel = args.get(0)

			TextChannel chan = event.getGuild().getTextChannelsByName(channel.replaceAll("#", ""), true)
				.stream().findFirst().orElse(null)
			
			if (chan == null) {
				sendMessage(event, "Text channel not found")
				return
			}

			bdayman.setBirthdayChannel(chan.getId(), guildId)
			sendMessage(event, String.format("Successfully set the birthday channel to <#%s>", chan.getId()))
		}
	}
}
