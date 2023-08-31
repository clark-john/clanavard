package clanavard.commands

import clanavard.commands.birthday.SetBirthdayChannelCommand
import clanavard.commands.birthday.SetBirthdayCommand
import clanavard.commands.birthday.UnsetBirthdayCommand
import clanavard.commands.common.HelpCommand
import clanavard.commands.moderation.KickCommand
import clanavard.commands.moderation.MuteCommand
import clanavard.commands.moderation.UnmuteCommand

class GetAllCommands {
	private static List<Command> commands = []

	static List<Command> getAll(){
		// moderation
		addAll(commands,
			new MuteCommand(),
			new KickCommand(),
			new UnmuteCommand()
		)

		// birthday
		addAll(commands,
			new SetBirthdayCommand(),
			new UnsetBirthdayCommand(),
			new SetBirthdayChannelCommand()
		)

		// common
		addAll(commands,
			new HelpCommand(commands)
		)

		return commands
	}

	private static void addAll(List<Command> a, Command... coms){
		for (Command com : coms){
			a.add(com)
		}
	}
}
