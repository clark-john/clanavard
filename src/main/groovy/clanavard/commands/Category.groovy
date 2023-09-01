package clanavard.commands

enum Category {
	BIRTHDAY ("Birthday"),
	MODERATION ("Moderation"),
	NONE (null)

	private String name

	Category(String name){
		this.name = name
	}

	String getValue(){
		return name
	}
}
