package clanavard.commands

import clanavard.commands.birthday.*
import clanavard.commands.common.HelpCommand
import clanavard.commands.moderation.*
import clanavard.commands.fun.*

class GetAllCommands {
	static List<Command> getAll(){
		def commands
		def moderation = [
		  new MuteCommand(),
			new KickCommand(),
			new UnmuteCommand()
		]

		def birthday = [
		  new SetBirthdayCommand(),
			new UnsetBirthdayCommand(),
			new SetBirthdayChannelCommand(),
			new BirthdaysListCommand()
		]

		def fun = [
			new YesNoCommand()
		]

		commands = [*moderation, *birthday, *fun]

		commands.add(new HelpCommand(commands))

		return commands
	}
}
