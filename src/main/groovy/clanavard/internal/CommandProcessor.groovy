package clanavard.internal

import clanavard.commands.Command
import clanavard.commands.CommandInfo
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class CommandProcessor {
	private String prefix
	private List<Command> commands
	
	CommandProcessor(String pref){
		prefix = pref
	}

	void setCommands(List<Command> commands){
		this.commands = commands
	}

	void process(MessageReceivedEvent event){
		String content = event.getMessage().getContentStripped()

		if (!content.startsWith(prefix)) {
			return
		}

		List<String> args = null
		List<String> rawArgs = Arrays.asList(content.split("\\s+"))

		if (rawArgs.size() > 1){
			args = rawArgs.subList(1, rawArgs.size())
		}

		Command comm = null

		if (content.startsWith(prefix)) {
			comm = commands.stream().filter({ x -> 
				String name = x.getClass().getDeclaredAnnotation(CommandInfo.class).name()
				return rawArgs.get(0) == prefix + name
			}).findFirst().orElse(null)

			if (!comm) {
				event.getMessage().getChannel().sendMessage(
					"Unknown command \"${content.replace(rawArgs.get(0), "").strip()}\"".toString()
				).queue()
				return
			}
		}

		if (comm) {
			comm.handle(event, args)
		}
	}
}
