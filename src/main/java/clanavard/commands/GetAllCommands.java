package clanavard.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clanavard.commands.birthday.*;
import clanavard.commands.common.HelpCommand;
import clanavard.commands.moderation.*;
import clanavard.commands.fun.*;

public class GetAllCommands {
	public static List<Command> getAll(){
		List<Command> commands = new ArrayList<>();

		List<Command> moderation = Arrays.asList(
			new MuteCommand(),
			new KickCommand(),
			new UnmuteCommand()
		);

		List<Command> birthday = Arrays.asList(
			new SetBirthdayCommand(),
			new UnsetBirthdayCommand(),
			new SetBirthdayChannelCommand(),
			new BirthdaysListCommand()
		);

		List<Command> fun = Arrays.asList(
			new YesNoCommand(),
			new TicTacToeCommand()
		);

		commands.addAll(moderation);
		commands.addAll(birthday);
		commands.addAll(fun);

		commands.add(new HelpCommand(commands));

		return commands;
	}
}
