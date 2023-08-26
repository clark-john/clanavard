package clanavard.internal;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.primitives.Ints;
import org.javatuples.Pair;

public class DurationParser {
	public Duration parse(String humanReadableDate){
		Duration dur = Duration.ZERO;
		List<String> tokens = Arrays.asList(humanReadableDate.split(" "));
		for (String token : tokens) {
			dur = dur.plus(toDuration(parseTime(token)));
		}
		return dur;
	}

	private Pair<String, Integer> parseTime(String time){
		Stream<String> str = Arrays.asList("s", "m", "h", "d").stream();
		String unit = str.filter(x -> time.endsWith(x)).findFirst().orElse(null);
		if (unit == null) {
			throw new RuntimeException("Invalid time unit");
		}
		String stringInt = time.replace(unit, "");
		return new Pair<>(unit, Ints.tryParse(stringInt));
	}

	private Duration toDuration(Pair<String, Integer> pair){
		Duration dur = Duration.ZERO;
		
		String unit = pair.getValue0();
		Integer value = pair.getValue1();

		if (unit.equals("s")) {
			dur = dur.plusSeconds(value);
		} else if (unit.equals("m")) {
			dur = dur.plusMinutes(value);
		} else if (unit.equals("h")) {
			dur = dur.plusHours(value);
		} else if (unit.equals("d")) {
			dur = dur.plusDays(value);
		}

		return dur;
	}

	public int removeUnit(String time, String unit){
		String t = time.replace(unit, "");
		try {
			return Integer.parseInt(t, 10);
		} catch (NumberFormatException e){
			throw new RuntimeException(e);
		}
	}
}
