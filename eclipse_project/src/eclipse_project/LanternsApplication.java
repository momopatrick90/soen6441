package eclipse_project;

import com.sun.tools.javac.parser.Scanner;

/**
 *	Main application container - loads the new game or existing game based on the user input
 */

public class LanternsApplication {

	private static GameEngine game;
	private static int numOfPlayers;
	private static int playerTurn;
	private static int tokensCount;
	
	public static void main(String[] args){

		int resp = '\0';

		do{
			
			System.out.println("Choose an option:");
			System.out.println("1.New Game");
			System.out.println("2.Existing Game");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			if (choice == 1) {
				System.out.println("Enter number of players");
				in = new Scanner(System.in);
				numOfPlayers = in.nextInt();
				playerTurn = 1;
				tokensCount = 20;
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
		
		game = new GameEngine(numOfPlayers, playerTurn, tokensCount);
		game.start();
	}
	
	public static void loadExistingGame(){
		
		readGameState();
		game = new GameEngine(numOfPlayers, playerTurn, tokensCount);
		game.start();
	}
	
	public static void readGameState(){
		numOfPlayers = 1;
		playerTurn = 1;
		tokensCount = 20;
	}
}
