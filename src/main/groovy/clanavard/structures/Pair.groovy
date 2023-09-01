package clanavard.structures

public class Pair<T, U> {
	private T firstValue
	private U secondValue
	
	Pair(T firstVal, U secondVal){
		firstValue = firstVal
		secondValue = secondVal
	}

	Pair(){
		firstValue = null
		secondValue = null
	}

	void setFirst(T value){
		firstValue = value
	}

	void setSecond(U value){
		secondValue = value
	}

	T getFirst(){
		return firstValue
	}

	U getSecond(){
		return secondValue
	}
}
