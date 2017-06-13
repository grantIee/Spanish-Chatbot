
public class Ans {
	private String tag;
	private String name;
	
	public Ans(String name, String tag) {
		this.tag = tag;
		this.name = name;
	}
	
	public String[] getTag() {
		return this.tag.split(",");
	}
	public String getName() {
		return this.name;
	}
	
}