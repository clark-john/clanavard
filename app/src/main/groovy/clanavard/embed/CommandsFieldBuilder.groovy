package clanavard.embed

import net.dv8tion.jda.api.entities.MessageEmbed.Field

class CommandsFieldBuilder {
	private String groupName
	private String content = ""
	
	CommandsFieldBuilder(String groupName){
		this.groupName = groupName
	}

	CommandsFieldBuilder addCommand(String name, String description){
		content += "$name  -  $description\n".toString()
		this
	}

	Field build(){
		new Field(groupName, content.stripTrailing(), false)
	}	
}
