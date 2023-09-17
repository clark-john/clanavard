package clanavard.commands;

public enum Category {
	BIRTHDAY ("Birthday"),
	MODERATION ("Moderation"),
	FUN ("Fun"),
	NONE (null);

	private String name;

	Category(String name){
		this.name = name;
	}

	public String getValue(){
		return name;
	}
}
