
public class Ans {
	private String tag;
	private String name;
	
	
	public Ans(String input) {
		//This is just an overloaded constructor, identical to the first one
		this.tag = input.split(":")[1];
		this.name = input.split(":")[0];
	}
	
	public String[] getTag() {
		return this.tag.split(",");
	}
	public String getName() {
		return this.name;
	}
	
}