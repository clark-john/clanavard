package clanavard.internal

import java.util.stream.Stream

import com.google.common.primitives.Ints

class DateParser {
	@SuppressWarnings('GrMethodMayBeStatic')
	boolean validate(String date){
		def cal = Calendar.getInstance()
		def cal2 = Calendar.getInstance()
		def streamSup = { -> Stream.of(date.split("-")) }

		def parts = streamSup()

		if (streamSup().count() != 3) {
			return false
		}
		
		boolean isAllValidNums = parts.allMatch { Ints.tryParse(it) != null }
		
		if (!isAllValidNums) {
			return false
		}

		def (
			int first,
			int second,
			int third
		) = streamSup()
			.map { Integer.parseInt(it) }
			.toList()

		if (first > 31 || first < 1) {
			return false
		}
		if (second > 12 || second < 1) {
			return false
		}
		if (third > cal.get(Calendar.YEAR) || third < 1970) {
			return false
		}

		cal2.set(third, second - 1, first, 0, 0)

		def toInt = { BigDecimal x -> x.intValue() }

		if (toInt(cal2.getTimeInMillis() / 1000) >= toInt(System.currentTimeMillis() / 1000)) {
			return false
		}
		
		return true
	}
}
