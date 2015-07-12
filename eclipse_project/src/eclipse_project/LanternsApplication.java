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


/**
 *	Main application container - loads the new game or existing game based on the user input
 */

public class LanternsApplication {

	private static GameEngine game;
	private static int numOfPlayers;
	private static int playerTurn;
	private static int tokensCount;
	private static int redCount, blueCount, greenCount, whiteCount, purpleCount, blackCount, orangeCount;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{

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
				loadNewGame();
			
			} else if (choice == 2) {
				
				System.out.println("Enter number of players");
				in = new Scanner(System.in);
				String fileName = in.nextLine();
				loadExistingGame(fileName);

			}

			System.out.println("press 9 to continue and ZERO to quit!");
			in = new Scanner(System.in);
			resp = in.nextInt();
		
		}while(resp != 0); 

	}
	
	public static void loadNewGame(){
		
		game = new GameEngine(numOfPlayers);
		game.startNewGame();
	}
	
	/**
	 * @param fileName, filename with xml structure of the form:
	 * 
	 * <players>
	 * 		<player ... />
	 * </players>
	 * 
	 * <dedication_tokens ... />
	 * 
	 * <favor_tokens ... />
	 * 
	 * <available_lantern_cards ... />
	 * 
	 * <available_lake_tiles ... />
	 * 
	 * <game_board ... />
	 */
	public static GameEngine loadExistingGame(String fileName) throws ParserConfigurationException, SAXException, IOException{
		// 
		File fXmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		//
		Document doc = dBuilder.parse(fXmlFile);
		
		//
		NodeList nodeList = doc.getChildNodes();
		
		//
		DedicationTokens dedicationTokens = null;
		LakeTiles availableLakeTiles = null;
		LanternCards availableLanternCards = null;
		int favorTokens = 0;
		LinkedList<Player> players = new LinkedList<Player>();
		Player currentPlayer = null;
		
		//
		for (int i = 0; i < nodeList.getLength(); i++) {
			//
			Node node = nodeList.item(i);
			
			
			if(node.getNodeName().equals("players"))
			{
				// list of players
				NodeList playersElementList = ((Element) node).getElementsByTagName("player");
				
				//
				for (int j = 0; j < playersElementList.getLength(); j++) {
					// should add to true to this if its the current players turn
					LinkedList<Boolean> isCurrent = new LinkedList<Boolean>();
					//
					Player player = loadPlayer((Element) nodeList.item(i), isCurrent);
					//
					players.add(player);
					//
					if(isCurrent.contains(true))
					{
						currentPlayer = player;
					}
				}
			}
			
		}
		
		//
		for (int i = 0; i < nodeList.getLength(); i++) {
			//
			Node node = nodeList.item(i);
			
			//
			if(node.getNodeName().equals("dedication_tokens"))
			{
				dedicationTokens = loadDedicationTokens(players.size(),(Element) node);
			}else if(node.getNodeName().equals("available_lake_tiles"))
			{
				availableLakeTiles = loadLakeTiles((Element) node);
			}else if(node.getNodeName().equals("available_lantern_cards"))
			{
				availableLanternCards = loadLanternCards((Element) node);
			}else if(node.getNodeName().equals("favor_tokens"))
			{
				favorTokens = loadFavorTokens((Element) node);
			}else if(node.getNodeName().equals("game_board"))
			{
				
			}
			
		}
		
		game = new GameEngine(players.size(), 1, favorTokens, dedicationTokens,availableLanternCards);

		return null;
	}
	
	// Load a player from xml
	/**
	 * @param playerElement
	 * example: 
	 * 	<player current="true" four_kind_score="2" three_pair_score="2" seven_unique_score="1" total_score="5"  >
	 * 		<lake_tiles ... />
	 * 		<favor_tokens ... />
	 * 		<lantern_cards ... />
	 * 	</player>
	 * */
	public static Player loadPlayer(Element playerElement, LinkedList<Boolean> isCurrent)
	{
		//
		int favorTokens = loadFavorTokens((Element) playerElement.getElementsByTagName("favor_tokens").item(0));
	
		//
		//DedicationTokens dedicationTokens = this.loadDedicationTokens((Element) playerElement.getElementsByTagName("dedication_tokens"));
		
		//
		LanternCards loadLanternCards = loadLanternCards((Element) playerElement.getElementsByTagName("lantern_cards"));
		
		//
		LakeTiles lakeTiles = loadLakeTiles((Element) playerElement.getElementsByTagName("lake_tiles")); 
		
		//four
		int fourKindScore = Integer.parseInt(playerElement.getAttribute("four_kind_score"));
		int threePairScore = Integer.parseInt(playerElement.getAttribute("three_pair_score"));
		int sevenUniqueScore = Integer.parseInt(playerElement.getAttribute("seven_unique_score"));
		int totalScore = Integer.parseInt(playerElement.getAttribute("total_score"));
		isCurrent.add(Boolean.parseBoolean(playerElement.getAttribute("current")));
		
		//
		return new Player("", loadLanternCards, lakeTiles, favorTokens, fourKindScore, threePairScore, sevenUniqueScore);
	}
	
	/**
	 * 
	 * @param favorTokensElement
	 * @return
	 */
	public static int loadFavorTokens(Element favorTokensElement)
	{
		return Integer.parseInt(favorTokensElement.getAttribute("value"));
	}
	
	// Load dedication token
	/**
	 * @param dedicationTokenElement example
	 * 		<dedication_tokens four_kind="2" three_pair="1" seven_unique="2" generic_four="4" />
	 * @return a dedication token
	 */
	public static DedicationTokens loadDedicationTokens(int numOfPlayer, Element dedicationTokenElement)
	{
		//
		int fourKind = Integer.parseInt(dedicationTokenElement.getAttribute("four_kind"));;
		int threePair = Integer.parseInt(dedicationTokenElement.getAttribute("three_pair"));;
		int sevenUnique = Integer.parseInt(dedicationTokenElement.getAttribute("seven_unique"));;
		int genericFour = Integer.parseInt(dedicationTokenElement.getAttribute("generic_four"));;
		

		//
		return new DedicationTokens(numOfPlayer, fourKind, threePair, sevenUnique, genericFour);
	}
	
	
	/**
	 * @param lanternCardsElement 
	 * 		example: <lantern_cards red="1"  blue="2"  green="1" white="3"  purple="2" black="0" "orange"=1 />
	 */
	public static LanternCards loadLanternCards(Element lanternCardsElement)
	{
		//
		int red = Integer.parseInt(lanternCardsElement.getAttribute("red"));
		int blue = Integer.parseInt(lanternCardsElement.getAttribute("blue"));
		int green = Integer.parseInt(lanternCardsElement.getAttribute("green"));
		int white = Integer.parseInt(lanternCardsElement.getAttribute("white"));
		int purple = Integer.parseInt(lanternCardsElement.getAttribute("purple"));
		int orange = Integer.parseInt(lanternCardsElement.getAttribute("orange"));
		int black = Integer.parseInt(lanternCardsElement.getAttribute("black"));
		
		//
		return new LanternCards(0, red, blue, green, white, purple, black, orange);
	}
	
	/**
	 * @param  lakeTilesElement
	 * example
	 * <lake_tiles 
	 * 		id="lake_tile_id"
	 * 		left_color="red" right_color="blue" up_color="red"  down_color="green"
	 * 		left="lake_tile_id" right="lake_tile_id"  up="lake_tile_id" down="lake_tile_id"
	 * 		lake_tiles="lake_tile_id1,lake_tile_id2, ..."
	 * 		plaform="false"
	 *  />
	 * 	
	 */
	public static LakeTiles loadLakeTiles(Element lakeTilesElement)
	{
		//
		int id = Integer.parseInt(lakeTilesElement.getAttribute("id"));
		//
		String leftColor = lakeTilesElement.getAttribute("left_color");
		String rightColor = lakeTilesElement.getAttribute("right_color");
		String upColor = lakeTilesElement.getAttribute("up_color");
		String downColor = lakeTilesElement.getAttribute("down_color");
		//
		int left = Integer.parseInt(lakeTilesElement.getAttribute("left"));
		int right = Integer.parseInt(lakeTilesElement.getAttribute("right"));
		int up = Integer.parseInt(lakeTilesElement.getAttribute("up"));
		int down = Integer.parseInt(lakeTilesElement.getAttribute("down"));
		//
		String[] lake_tiles_str = lakeTilesElement.getAttribute("lake_tiles").split(",");
		int[] lakeTiles = new int[lake_tiles_str.length];
		for(int i=0; i<lake_tiles_str.length; i++)
		{
			lakeTiles[i] = Integer.parseInt(lake_tiles_str[i]);
		}
		//
		boolean platform = Boolean.parseBoolean(lakeTilesElement.getAttribute("platform"));
		
		return null;
	}

}
