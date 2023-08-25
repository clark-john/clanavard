package clanavard.commands;

import java.util.ArrayList;
import java.util.List;

import clanavard.commands.birthday.SetBirthdayCommand;
import clanavard.commands.birthday.UnsetBirthdayCommand;
import clanavard.commands.common.HelpCommand;
import clanavard.commands.moderation.KickCommand;
import clanavard.commands.moderation.MuteCommand;

public class GetAllCommands {
	private static ArrayList<Command> commands = new ArrayList<>();

	public static List<Command> getAll(){
		// moderation
		addAll(commands,
			new MuteCommand(),
			new KickCommand()
		);

		// birthday
		addAll(commands,
			new SetBirthdayCommand(),
			new UnsetBirthdayCommand()
		);

		// common
		addAll(commands,
			new HelpCommand(commands)
		);

		return commands;
	}

	private static void addAll(ArrayList<Command> a, Command... coms){
		for (Command com : coms){
			a.add(com);
		}
	}
}
