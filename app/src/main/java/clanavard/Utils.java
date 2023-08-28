package clanavard;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

public class Utils {
	public static Object getOrNull(List<?> list, int index){
		try {
			return list.get(index);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}

	public static String getResource(String file){
		try {
			return new String(Utils.class.getClassLoader().getResourceAsStream(file).readAllBytes());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public static Member validUserIdElseErrorMessage(MessageReceivedEvent event, String userId) throws Exception {
		try {
			return event.getGuild().retrieveMemberById(userId).complete();
		} catch (NumberFormatException e) {
			throw new Exception("Member must be a valid user id");
		} catch (ErrorResponseException e){
			if (e.getMessage().contains("Unknown Member")) {
				throw new Exception("Unknown member");
			} else {
				throw new Exception(e);
			}
		}
	} 
}
