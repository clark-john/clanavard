package clanavard

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.ErrorResponseException

class Utils {
	static def getOrNull(List<?> list, int index){
		try {
			return list.get(index);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}
	
	static String getResource(String file){
		try {
			return new String(Utils.class.getClassLoader().getResourceAsStream(file).readAllBytes());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	static Member validUserIdElseError(MessageReceivedEvent event, String userId) throws Exception {
		try {
			return event.getGuild().retrieveMemberById(userId).complete();
		} catch (NumberFormatException | ErrorResponseException e) {
			if (e instanceof NumberFormatException) {				
				throw new Exception("Member must be a valid user id");
			} else {
				if (e.getMessage().contains("Unknown Member")) {
					throw new Exception("Unknown member");
				} else {
					throw new Exception(e);
				}
			}
		}
	}
}
