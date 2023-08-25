package clanavard.commands.common;

import java.awt.Color;
import java.util.List;

import clanavard.commands.Command;
import clanavard.commands.CommandInfo;
import clanavard.embed.CommandsFieldBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(name = "help", description = "", category = "")
public class HelpCommand extends Command {
	private MessageEmbed embed;

	public HelpCommand(List<Command> commands){
		EmbedBuilder builder = new EmbedBuilder()
			.setAuthor("Clanavard")
			.setTitle("Commands List - Prefix `c?`")
			.setColor(Color.decode("#88AA9E"))
			.setFooter("Website: https://clark.surge.sh")
			.addField("Arguments",
				"**[ARG]** - required argument\n" + 
				"**(ARG)** - optional argument", false
			);

		String category = null;
		String newCategory;
		CommandsFieldBuilder b = null;
		boolean isLast;

		int index = 0;
				
		for (Command comm : commands) {

			CommandInfo info = comm.getCommandInfo();
			newCategory = info.category();
			
			if (category == null){
				category = newCategory;
				b = new CommandsFieldBuilder(category);
			}

			b.addCommand(info.name(), info.description());

			index++;

			isLast = commands.size() == index;
			
			if (!category.equals(newCategory)) {
				builder.addField(b.build());
				category = newCategory;
				b = new CommandsFieldBuilder(category);
				b.addCommand(info.name(), info.description());
			}

			if (isLast) {
				builder.addField(b.build());
				builder.addField(
					new CommandsFieldBuilder("Help").addCommand("help", "Display this help").build()
				);
			}
		}

		embed = builder.build();
	}

	@Override
	public void handle(MessageReceivedEvent event, List<String> args) {
		event
			.getMessage()
			.getChannel()
			.sendMessageEmbeds(embed)
			.queue();
	}
}
