package clanavard.internal

import java.util.stream.Stream

import com.google.common.primitives.Ints

class DateParser {
	boolean validate(String date){
		def p = date.split("-")
		Stream<String> parts = Stream.of(p)
				
		if (p.length != 3) {
			return false
		}
		
		boolean isAllValidNums = parts.allMatch({ Ints.tryParse(it) != null })
		
		if (!isAllValidNums) {
			return false
		}

		parts = Stream.of(p)
		
		def (
			int first, 
			int second, 
			int third
		) = parts.map({ Integer.parseInt(it) }).toList()

		if (first > 31 || first < 1) {
			return false
		}
		if (second > 12 || second < 1) {
			return false
		}
		if (third > Calendar.getInstance().get(Calendar.YEAR) || third < 1970) {
			return false
		}
		
		return true
	}
}
