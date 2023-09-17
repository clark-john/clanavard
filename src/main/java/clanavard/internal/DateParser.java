package clanavard.internal;

import java.util.Calendar;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.primitives.Ints;

public class DateParser {
	public boolean validate(String date){
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Supplier<Stream<String>> streamSup = () -> Stream.of(date.split("-"));

		var parts = streamSup.get();

		if (streamSup.get().count() != 3) {
			return false;
		}
		
		boolean isAllValidNums = parts.allMatch(x -> Ints.tryParse(x) != null);
		
		if (!isAllValidNums) {
			return false;
		}

		var ls = streamSup.get().map(Integer::parseInt).toList();

		int first = ls.get(0);
		int second = ls.get(1);
		int third = ls.get(2);

		if (first > 31 || first < 1) {
			return false;
		}
		if (second > 12 || second < 1) {
			return false;
		}
		if (third > cal.get(Calendar.YEAR) || third < 1970) {
			return false;
		}

		cal2.set(third, second - 1, first, 0, 0);

		if ((cal2.getTimeInMillis() / 1000) >= (System.currentTimeMillis() / 1000)) {
			return false;
		}
		
		return true;
	}
}
