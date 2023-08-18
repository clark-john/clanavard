package clanavard.internal;

import java.util.Arrays;
import java.util.List;

import clanavard.commands.Command;
import clanavard.commands.CommandName;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandProcessor {
	private String prefix;
	private List<Command> commands;

	public CommandProcessor(String pref){
		prefix = pref;
	}

	public void setCommands(List<Command> commands){
		this.commands = commands;
	}

	public void process(MessageReceivedEvent event){
		String content = event.getMessage().getContentStripped();

		if (!content.startsWith(prefix)) {
			return;
		}

		List<String> args = null;
		List<String> rawArgs = Arrays.asList(content.split(" "));

		if (rawArgs.size() > 1){
			args = rawArgs.subList(1, rawArgs.size());
		}

		Command comm = null;

		if (content.startsWith(prefix)) {
			comm = commands.stream().filter(x -> {
				String name = x.getClass().getDeclaredAnnotation(CommandName.class).value();
				return rawArgs.get(0).equals(prefix + name);
			}).findFirst().orElse(null);

			if (comm == null) {
				event.getMessage().getChannel().sendMessage(
					String.format("Unknown command \"**%s**\"", content.replace(prefix, ""))
				).queue();
				return;
			}
		}

		if (comm != null){
			comm.handle(event, args);
		}
	}
}
