
public class Ans {
	private String tag;
	private String name;
	
	public Ans(String name, String tag) {
		
		this.tag = tag;
		this.name = name;
	
	}
	public Ans(String input) {
		//This is just an overloaded constructor, identical to the first one
		if(input.split(":").length < 2){
			System.out.println("Invalid input: " + input);
		}
		else{
			this.tag = input.split(":")[1];
			this.name = input.split(":")[0];
		}
	}
	public String getFullTag(){
		return tag;
	}
	public String[] getTag() {
		return this.tag.split(",");
	}
	public String getName() {
		return this.name;
	}
	public boolean tagMatch(String inputTag){
		if(tag.indexOf(inputTag) != -1){
			return true;
		}
		return false;
	}
	public String toString(){
		return name;
	}
	public String fullToString(){
		return name +":" +  tag;
	}
	public Ans clone(){
		return new Ans(name, tag);
	}
	
}