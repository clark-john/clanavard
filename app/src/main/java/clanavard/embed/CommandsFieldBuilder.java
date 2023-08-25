package clanavard.embed;

import net.dv8tion.jda.api.entities.MessageEmbed.Field;

public class CommandsFieldBuilder {
	private String groupName;
	private String content = "";
	
	public CommandsFieldBuilder(String groupName){
		this.groupName = groupName;
	}

	public CommandsFieldBuilder addCommand(String name, String description){
		content = content.concat(String.format("`%s`  -  %s\n", name, description));
		return this;
	}

	public Field build(){
		return new Field(groupName, content.stripTrailing(), false);
	}	
}
