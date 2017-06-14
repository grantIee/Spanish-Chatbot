import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*Precode:
 * Ask a random question from the question ArrayList
 * If a positive answer, remove answers that do not match the question's tag
 * If a negative answer, do nothing.  
 * When the answer array's size is 1, state the answer
 * We will implement question limiting later, once we get the main part of the code working.
 * 
 * 
 */
public class Chatbot {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hola!  Yo soy un proyecto de Grant y Everett");
		System.out.println("Please think of either something that has to do with disasters or food.");
		System.out.println("I will ask you questions, please answer to the best of your ability.");
		Scanner input = new Scanner(System.in);
		Scanner getQ;
		File f = new File("Objects.txt");
		File q = new File("Questions.txt");
		Scanner file;
		
		if(f.exists()&& q.exists()){
			file = new Scanner(f);
			getQ = new Scanner(q);
			ArrayList<Ans> objects = new ArrayList<Ans>(100);
			ArrayList<String> tags = new ArrayList<String>();
			ArrayList<Qes> questions = new ArrayList<Qes>(100);
			while(file.hasNextLine()){
				objects.add(new Ans(file.nextLine()));
			}
			while(getQ.hasNextLine()){
				questions.add(new Qes(getQ.nextLine()));
			}
			System.out.println("File load successful!  ");
			System.out.println("Answer these questions:");
			//Preliminary questions, to split between food and disasters.
			System.out.println("Is it a food or a disaster?");
			String choice = input.nextLine().toLowerCase();
			boolean valid = false;
			while(!valid){
				if(choice.indexOf("food") != -1|| choice.indexOf("comida") != -1){
					valid = true;
					for(int i = 0; i < objects.size(); i ++){
						if(!objects.get(i).tagMatch("food")){
							//System.out.println(objects.get(i).toString());
							objects.remove(i);
							i --;
						}
					}
					for(int i = 0; i < questions.size(); i ++){
						if(!questions.get(i).getSuperTag().equals("food")){
							questions.remove(i);
							i --;
						}
					}
				}
				else if(choice.indexOf("disaster") != -1|| choice.indexOf("desastre") != -1){
					valid = true;
					for(int i = 0; i < objects.size(); i ++){
						if(!objects.get(i).tagMatch("disaster")){
							objects.remove(i);
							i --;
						}
					}
					for(int i = 0; i < questions.size(); i ++){
						if(!questions.get(i).getSuperTag().equals("disaster")){
							questions.remove(i);
							i --;
							
						}
					}
				}
				else{
					System.out.println("Invalid input!  Enter either food/comida or disaster/desastre!");
					choice = input.nextLine().toLowerCase();
				}
			}
			while(objects.size() > 5){
				
				ArrayList<Ans> backupArray= new ArrayList<Ans>(objects.size());
				for(int i = 0; i < objects.size(); i ++){
					backupArray.add(i,objects.get(i).clone());
				}
				
				Qes qes = questions.get((int)(Math.random() * questions.size()));
				System.out.println(qes.getName());
				String filterKeyword = input.nextLine();
				if(filterKeyword.toLowerCase().indexOf("yes") != -1 || filterKeyword.toLowerCase().indexOf("si") != -1){
					for(int i = 0; i < objects.size(); i ++){
						if(!objects.get(i).tagMatch(qes.getTag())){
							objects.remove(i);
							i --;
						}
						else{
							//System.out.println(objects.get(i).fullToString());
						}
					}
					if(objects.size() < 1){
						System.out.println("No answers fit your answer!  Loading last state...");
						objects = backupArray;
						//System.out.println(objects.size());
					}
					//trimming quesetion array;
					String taglist = ""; //first we find all active tags
					for(int i = 0; i < objects.size(); i ++){
						for(int j = 0; j < objects.get(i).getTag().length; j ++){
							if(taglist.indexOf(objects.get(i).getTag()[j]) == -1){
								taglist += objects.get(i).getTag()[j];
								//System.out.print(objects.get(i).getTag()[j] + ", ");
							}
						}
							
					}
					// then we remove questions corresponding to inactive tags.
					for(int k = 0; k < questions.size(); k ++){
						if(taglist.indexOf(questions.get(k).getTag()) == -1){
							//System.out.print(questions.get(k).getName() + ", ");
							questions.remove(k);
							k --;
							
						}
					}
				}
				//Trimming question array
				
				
			}	
			int reps = 0;
			while(objects.size() > 0){
				System.out.println("Es " + objects.get(reps) + "?");
				String filterKeyword = input.nextLine().toLowerCase();
				
				if(filterKeyword.equals("si") || filterKeyword.equals("yes")){
					System.out.println("I win!");
					break;
					
				}
				if(objects.size() <= 1){
					System.out.println("I give up!  You win!");
					break;
				}
			
				
			}
			
		}
		else{
			System.out.println("Error! File status: "+ f.getName() + " exists: " + f.exists() + " " + q.getName() + " exists: " + q.exists());
		}	
	}
}
