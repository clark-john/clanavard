package clanavard;

import java.util.List;

public class Utils {
	public static Object getOrNull(List<?> list, int index){
		try {
			return list.get(index);
		} catch (IndexOutOfBoundsException e){
			return null;
		}
	}	
}
