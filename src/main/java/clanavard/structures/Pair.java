package clanavard.structures;

public class Pair<T, U> {
	private T firstValue;
	private U secondValue;
	
	public Pair(T firstVal, U secondVal){
		firstValue = firstVal;
		secondValue = secondVal;
	}

	public Pair(){
		firstValue = null;
		secondValue = null;
	}

	public void setFirst(T value){
		firstValue = value;
	}

	public void setSecond(U value){
		secondValue = value;
	}

	public T getFirst(){
		return firstValue;
	}

	public U getSecond(){
		return secondValue;
	}
}
