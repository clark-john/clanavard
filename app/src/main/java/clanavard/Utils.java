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

	public static String getResource(String file){
		try {
			return new String(Utils.class.getClassLoader().getResourceAsStream(file).readAllBytes());
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
