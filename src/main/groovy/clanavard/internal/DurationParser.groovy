package clanavard.internal

import java.time.Duration
import java.util.stream.Stream

import com.google.common.primitives.Ints

import clanavard.structures.Pair

class DurationParser {
	public Duration parse(String humanReadableDate){
		Duration dur = Duration.ZERO
		List<String> tokens = Arrays.asList(humanReadableDate.split("\\s+"))

		if (!isValidInput(humanReadableDate)) {
			return null;
		}

		for (String token : tokens) {
			dur = dur.plus(toDuration(parseTime(token)))
		}

		return dur;
	}

	private Pair<String, Integer> parseTime(String time){
		Stream<String> str = Arrays.asList("s", "m", "h").stream()

		String unit = str
			.filter { time.endsWith(it) || time.endsWith(it.toUpperCase()) }
			.findFirst()
			.orElse(null)
		
		if (unit == null) {
			throw new RuntimeException("Invalid time format")
		}

		String stringInt = time.toLowerCase().replace(unit, "")
		return new Pair<>(unit, Ints.tryParse(stringInt))
	}

	private boolean isValidInput(String date){
		def m = date =~ /\b\d{1,4}[smhSMH]\b/
		
		int count = (int) m.results().count()

		if (count != date.split("\\s+").length){
			return false
		}
		return true
	}

	private Duration toDuration(Pair<String, Integer> pair){
		String unit = pair.getFirst()
		Integer value = pair.getSecond()

		switch (unit) {
			case 's': return Duration.ofSeconds(value)
			case 'm': return Duration.ofMinutes(value)
			case 'h': return Duration.ofHours(value)
			default: throw new RuntimeException("Invalid time unit")
		}
	}

	public String toWord(Duration dur){
		String s = ""

		int hours = (int) dur.toHoursPart()
		int minutes = (int) dur.toMinutesPart()
		int seconds = (int) dur.toSecondsPart()

		if (hours != 0) {
			s = s.concat(hours + " hours ")
		}
		if (minutes != 0) {
			s = s.concat(minutes + " minutes ")
		}
		if (seconds != 0) {
			s = s.concat(seconds + " seconds ")
		}

		return s.stripTrailing()
	}
}
