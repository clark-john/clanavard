package clanavard.embed;

import net.dv8tion.jda.api.entities.MessageEmbed.Field;

public class CommandsFieldBuilder {
	private String gName;
	private String content = "";
	public CommandsFieldBuilder(String groupName){
		gName = groupName;
	}
	public CommandsFieldBuilder addCommand(String name, String description){
		content = content.concat(String.format("`%s`  -  %s", name, description));
		return this;
	}
	public Field build(){
		return new Field(gName, content.stripTrailing(), false);
	}	
}
