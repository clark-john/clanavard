package clanavard.commands.common;

import java.awt.Color;
import java.util.List;

import clanavard.commands.Command;
import clanavard.commands.CommandName;
import clanavard.embed.CommandsFieldBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandName("help")
public class HelpCommand extends Command {
	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		event
			.getMessage()
			.getChannel()
			.sendMessageEmbeds(
				new EmbedBuilder()
					.setTitle("Commands List")
					.setColor(Color.decode("#88AA9E"))
					.setFooter("Website: https://clark.surge.sh")
					.addField(new CommandsFieldBuilder("Birthday")
						.addCommand("setbirthday", "Set your birthday and the bot will greet a happy birthday for you.")
						.build()
					)
					.build()
			)
			.queue();
	}
}
