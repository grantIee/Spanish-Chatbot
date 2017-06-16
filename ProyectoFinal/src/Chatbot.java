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
		System.out.println("¡Hola!  Yo soy un proyecto de Grant y Everett");
		System.out.println("Piensa en un objeto, por favor.");
		System.out.println("Yo hago preguntas de tu objeto.  Repuesta si o no.");
		Scanner input = new Scanner(System.in);
		Scanner getQ;
		File f = new File("Objects.txt");
		File q = new File("Questions.txt");
		Scanner file;
		boolean playing = true;
		while(playing){
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
				/*
				while(getQ.hasNextLine()){
					questions.add(new Qes(getQ.nextLine()));
				}
				System.out.println("File load successful!  ");
				ArrayList<Ans> lvl1 = new ArrayList<Ans>(100);
				for(int i = 0; i < objects.size(); i ++){
					if()
				}
				*/
				
				
				//Preliminary questions, to split between food and disasters.
				System.out.println("¿Es una comida o un desastre?");
				String choice = input.nextLine().toLowerCase();
				boolean valid = false;
				while(!valid){
					while(file.hasNextLine()){
						objects.add(new Ans(file.nextLine()));
					}
					while(getQ.hasNextLine()){
						questions.add(new Qes(getQ.nextLine()));
					}
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
						System.out.println("Un entrada no valido (Invalid Input)! Quiere endrada otra vez!");
						choice = input.nextLine().toLowerCase();
					}
				}
				while(objects.size() > 5){
					
					ArrayList<Ans> backupArray= new ArrayList<Ans>(objects.size());
					for(int i = 0; i < objects.size(); i ++){
						backupArray.add(i,objects.get(i).clone());
					}
					int qchoice = (int)(Math.random() * questions.size());
					Qes qes = questions.get(qchoice);
					System.out.println(qes.getName());
					String filterKeyword = input.nextLine();
					if(filterKeyword.toLowerCase().indexOf("yes") != -1 || filterKeyword.toLowerCase().indexOf("si") != -1){
						for(int i = 0; i < objects.size(); i ++){
							if(!objects.get(i).tagMatch(qes.getTag())){
								objects.remove(i);
								i --;
								//questions.remove(qchoice);
							}
							else{
								//questions.remove(qchoice);
								//System.out.println(objects.get(i).fullToString());
							}
						}
						if(objects.size() < 1){
							System.out.println("¡No tengo repuestas (No availiable answers)!  Creo repuestas valido (loading last state)...");
							objects = backupArray;
							//System.out.println(objects.size());
						}
						
						questions.remove(qchoice);
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
					else{
						questions.remove(qchoice);
					}
					
					//Trimming question array
					
					
				}	
				int reps = 0;
				while(objects.size() > reps){
					System.out.println("¿Es " + objects.get(reps) + "?");
					String filterKeyword = input.nextLine().toLowerCase();
					
					if(filterKeyword.equals("si") || filterKeyword.equals("yes")){
						System.out.println("¡Yo gano!");
						System.out.println("¿Tu queres jugar otra vez?");
						filterKeyword = input.nextLine().toLowerCase();
						if(filterKeyword.equals("si")){
							valid = true;
						}
						else{
							valid = false;
						}
						
						
					}
					else{
						//System.out.println("¡Tu gane!");
					
						if(objects.size() <= 1){
							System.out.println("¡Yo dejo de ganar!  !Tu gane!");
							System.out.println("¿Tu queres jugar otra vez?");
							filterKeyword = input.nextLine().toLowerCase();
							if(filterKeyword.equals("si")){
								valid = true;
							}
							else{
								valid = false;
							}
							
						}
						
					}
					reps ++;					
				}
				
				
			}
			else{
				System.out.println("Error! File status: "+ f.getName() + " exists: " + f.exists() + " " + q.getName() + " exists: " + q.exists());
			}
		}
	}
}
