package clanavard.commands;

import java.util.ArrayList;
import java.util.List;

import clanavard.commands.common.HelpCommand;

public class GetAllCommands {
	private static ArrayList<Command> commands = new ArrayList<>();

	public static List<Command> getAll(){
		// common
		addAll(commands, 
			new HelpCommand()
		);

		return commands;
	}

	private static void addAll(ArrayList<Command> a, Command... coms){
		for (Command com : coms){
			a.add(com);
		}
	}
}
