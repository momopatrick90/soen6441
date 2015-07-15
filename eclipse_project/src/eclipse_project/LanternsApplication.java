package eclipse_project;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

/**
 * Main application container - loads the new game or existing game based on the
 * user input
 */
public class LanternsApplication {

	private static GameEngine game;
	private static int numOfPlayers;
	private static int playerTurn;
	private static int tokensCount;
	private static int redCount, blueCount, greenCount, whiteCount,
			purpleCount, blackCount, orangeCount;

	private static boolean isLoad = false;

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerException {

		int resp = '\0';

		System.out.println("Choose an option:");
		System.out.println("Press 1 for New Game");
		System.out.println("Press 2 for Existing Game");
		System.out.println("Press 3 for Text Mode of the Game");
		System.out.println("Press ZERO to Exit Game");
		Scanner in = new Scanner(System.in);
		resp = in.nextInt();

		do {

			if (resp == 1) {
				System.out.println("Enter number of players");
				in = new Scanner(System.in);
				numOfPlayers = in.nextInt();
				isLoad = true;
				loadNewGame();

			} else if (resp == 2) {

				System.out.println("Enter name of file");
				in = new Scanner(System.in);
				String fileName = in.nextLine();
				isLoad = true;
				loadExistingGame(fileName);

			}
			else if (resp == 3) {
					
				if (!isLoad) {
					System.out.println("Kindly load new/existing game before viewing it in text mode.");
				}
				else
				{
					displayTextMode();
				}
			}
			if (resp == 0) {
				if (isLoad) {
					System.out
							.println("Enter the name of file to save the game & quit.");
					in = new Scanner(System.in);
					String fileName = in.nextLine();
					fileName = fileName.trim();
					saveTheGame(fileName,game);
					System.out.println("Game saved successfully!");
				}
				break;
			}
			
			System.out.println("Press ZERO to quit!");
			in = new Scanner(System.in);
			resp = in.nextInt();


		} while (true);

		System.out.println("Game is ended!");
	}

	/**
	 * load the new based on the user input
	 */
	public static void loadNewGame() {

		game = new GameEngine(numOfPlayers);
		game.startNewGame();
	}

	/**
	 * load the existing game from file name provided by the user
	 * @param fileName
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void loadExistingGame(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		game = new GameEngine();
		
		File file = new File(fileName);
		if(!file.exists()){
			isLoad = false;
			System.out.println("file: "+fileName+" doesn't exist.");
		}
		else
			game.loadExistingGame(fileName);
	}

	/**
	 * save the state of the game to the file
	 * @param fileName
	 * @param game - Current Game Instance
	 * @throws ParserConfigurationException
	 * @throws TransformerException 
	 */
	public static void saveTheGame(String fileName,GameEngine game) throws ParserConfigurationException, TransformerException {
		game.saveGame(fileName, game);
	}
	
	/**
	 * Display the current state of the game in text mode
	 */
	public static void displayTextMode(){
		game.displayTextMode();
	}

}
