import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
	public static void main(String[] args) {
		System.out.println("Hola!  Yo soy un proyecto de Grant y Everett");
		System.out.println("Please think of either something that has to do with disasters or food.");
		System.out.println("I will ask you questions, please answer honestly.");
		Scanner input = new Scanner(System.in);
		Scanner getQ = new Scanner(System.in);
		File f = new File("objects.txt");
		File q = new File("")
		Scanner file;
		//Chatbot bot = new 
		if(f.exists()){
			file = new Scanner("objects.txt");
			ArrayList<Ans> objects = new ArrayList<Ans>();
			ArrayList<String> tags = new ArrayList<String>();
			ArrayList<String> questions = new ArrayList<String>();
			while(file.hasNextLine()){
				objects.add(new Ans(file.nextLine()));
			}
		}
		else{
			System.out.println("Error!  File not found!");
		}
		
		
		
		
	}
	
}
