package clanavard;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

public class Utils {
	public static <T> T getOrNull(List<T> list, int index){
		try {
			return list.get(index);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}
	
	public static String getResource(String file){
		try {
			return new String(Utils.class
				.getClassLoader()
				.getResourceAsStream(file)
				.readAllBytes());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static Member validUserIdElseError(MessageReceivedEvent event, String userId) throws Exception {
		try {
			return event.getGuild().retrieveMemberById(userId).complete();
		} catch (NumberFormatException | ErrorResponseException e) {
			if (e instanceof NumberFormatException) {				
				throw new Exception("Member must be a valid user id");
			} else {
				if (e.getMessage().toLowerCase().contains("unknown member")) {
					throw new Exception("Unknown member");
				} else {
					throw new Exception(e);
				}
			}
		}
	}

	public static Gson getGson(){
		return new GsonBuilder()
			.disableInnerClassSerialization()
			.setPrettyPrinting()
			.serializeNulls()
			.create();
	}
}
