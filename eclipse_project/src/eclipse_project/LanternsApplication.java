package eclipse_project;

import com.sun.tools.javac.parser.Scanner;

public class LanternsApplication {

	
	public static void main(String[] args){
		

		int resp = '\0';

		do{
			
			System.out.println("Choose an option:");
			System.out.println("1.New Game");
			System.out.println("2.Existing Game");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			if (choice == 1) {
				loadNewGame();
			
			} else if (choice == 2) {
				
				loadExistingGame();

			}

			System.out.println("press 9 to continue and ZERO to quit!");
			in = new Scanner(System.in);
			resp = in.nextInt();
		
		}while(resp != 0); 

	}
	
	public static void loadNewGame(){
		
	}
	
	public static void loadExistingGame(){
		
	}
}
