package eclipse_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.ArrayList;

/**
 * Game Engine - Responsible for loading, updating the state of the game.
 */
public class GameEngine {

	private ArrayList<Player> PlayerList = new ArrayList();
	private int numOfPlayer;
	private int playerTurn;
	private final int numberOfCards = 3;
	private int favorTokensCount;
	private LanternCards lanternCards;
	private LakeTiles lakeTiles;
	private LakeTiles startTile;
	private ArrayList<LakeTiles> lTiles;
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount,
			purpleCount, blackCount, orangeCount;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");

	/**
	 * This method is called when the user starts a new game.
	 * 
	 * @param numOfPlayers
	 *            - number of players that will be playing the game
	 */
	public GameEngine(int numOfPlayer) {

		this.numOfPlayer = numOfPlayer;
	}

	public GameEngine(int numOfPlayers, int playerTurn, int favorTokensCount,
			DedicationTokens dedicationObj, LanternCards lanternCardObj) {

		this.numOfPlayer = numOfPlayers;

		this.lanternCards = lanternCardObj;
		this.dedicationTokens = dedicationObj;
		this.favorTokens = new FavorTokens(favorTokensCount);
	}

	public void startNewGame() {
		// Set the initial variables for the lantern cards and the various
		// tokens
		// according to the number of players.

		this.lanternCards = new LanternCards(this.numOfPlayer);
		this.dedicationTokens = new DedicationTokens(numOfPlayer);
		this.favorTokens = new FavorTokens(20);// For new game there will be 20
												// favor tokens by default

		lakeTiles = new LakeTiles();

		// generate a random lake tile stack
		lakeTiles.initializeLakeTiles(this.numOfPlayer);

		// get the lake tiles that each user will have at the start of the game
		// if 2 players it return 6 lake tiles, else if 3 -> 9 else 4 ->12
		lTiles = lakeTiles.assignLakeTiles(this.numOfPlayer);

		// get the starting lake tile
		startTile = lakeTiles.lakeTiles[0];
		Board board = new Board();

		// place the starting lake tile on the board
		board.intializeGameBoard(startTile);

		System.out.println("Starting the game...");
		System.out.println("StartTile is placed on board");
		System.out.println();
		System.out.println("Details of the startTile:");
		System.out.println("id" + startTile.id + " " + "leftColor " + " "
				+ startTile.leftColor + " " + "rightColor" + " "
				+ startTile.rightColor + " " + "upColor" + " "
				+ startTile.upColor + " " + "downColor" + " "
				+ startTile.downColor + " " + "platform" + " "
				+ startTile.platform);
		System.out.println();

		// give each player three lake tiles to start with.
		if (this.numOfPlayer == 2) {

			for (int i = 0; i < numOfPlayer * numberOfCards; i++) {
				if (i < numOfPlayer + 1)
					player1.setLakeTiles(lTiles.get(i));
				else
					player2.setLakeTiles(lTiles.get(i));
			}
			PlayerList.add(player1);
			PlayerList.add(player2);

			for (int x = 0; x < PlayerList.size(); x++) {

				System.out
						.println("Details of the LakeTiles assigned to Player"
								+ (x + 1));
				for (int i = 0; i < PlayerList.get(x).getLakeTiles().size(); i++) {
					System.out
							.println("id"
									+ PlayerList.get(x).getLakeTiles().get(i).id
									+ " "
									+ "leftColor "
									+ PlayerList.get(x).getLakeTiles().get(i).leftColor
									+ " "
									+ "rightColor "
									+ PlayerList.get(x).getLakeTiles().get(i).rightColor
									+ " "
									+ "upColor "
									+ PlayerList.get(x).getLakeTiles().get(i).upColor
									+ " "
									+ "downColor "
									+ PlayerList.get(x).getLakeTiles().get(i).downColor
									+ " "
									+ "platForm "
									+ PlayerList.get(x).getLakeTiles().get(i).platform);
				}
				System.out.println();
			}
		} else if (this.numOfPlayer == 3) {
			for (int i = 0; i < numOfPlayer * numberOfCards; i++) {
				if (i < numberOfCards)
					player1.setLakeTiles(lTiles.get(i));
				else if (i < numberOfCards * 2)
					player2.setLakeTiles(lTiles.get(i));
				else if (i < numberOfCards * 3)
					player3.setLakeTiles(lTiles.get(i));
			}
			PlayerList.add(player1);
			PlayerList.add(player2);
			PlayerList.add(player3);
			for (int x = 0; x < PlayerList.size(); x++) {

				System.out
						.println("Details of the LakeTiles assigned to Player"
								+ (x + 1));
				for (int i = 0; i < PlayerList.get(x).getLakeTiles().size(); i++) {
					System.out
							.println("id"
									+ PlayerList.get(x).getLakeTiles().get(i).id
									+ " "
									+ "leftColor "
									+ PlayerList.get(x).getLakeTiles().get(i).leftColor
									+ " "
									+ "rightColor "
									+ PlayerList.get(x).getLakeTiles().get(i).rightColor
									+ " "
									+ "upColor "
									+ PlayerList.get(x).getLakeTiles().get(i).upColor
									+ " "
									+ "downColor "
									+ PlayerList.get(x).getLakeTiles().get(i).downColor
									+ " "
									+ "platForm "
									+ PlayerList.get(x).getLakeTiles().get(i).platform);
				}
				System.out.println();
			}

		} else if (this.numOfPlayer == 4) {
			for (int i = 0; i < numOfPlayer * numberOfCards; i++) {
				if (i < numberOfCards)
					player1.setLakeTiles(lTiles.get(i));
				else if (i < numberOfCards * 2)
					player2.setLakeTiles(lTiles.get(i));
				else if (i < numberOfCards * 3)
					player3.setLakeTiles(lTiles.get(i));
				else if (i < numberOfCards * 4)
					player4.setLakeTiles(lTiles.get(i));
			}
			PlayerList.add(player1);
			PlayerList.add(player2);
			PlayerList.add(player3);
			PlayerList.add(player4);
			
			for (int x = 0; x < PlayerList.size(); x++) {

				System.out
						.println("Details of the LakeTiles assigned to Player"
								+ (x + 1));			
				for (int i = 0; i < PlayerList.get(x).getLakeTiles().size(); i++) {
					System.out
							.println("id"
									+ PlayerList.get(x).getLakeTiles().get(i).id
									+ " "
									+ "leftColor "
									+ PlayerList.get(x).getLakeTiles().get(i).leftColor
									+ " "
									+ "rightColor "
									+ PlayerList.get(x).getLakeTiles().get(i).rightColor
									+ " "
									+ "upColor "
									+ PlayerList.get(x).getLakeTiles().get(i).upColor
									+ " "
									+ "downColor "
									+ PlayerList.get(x).getLakeTiles().get(i).downColor
									+ " "
									+ "platForm "
									+ PlayerList.get(x).getLakeTiles().get(i).platform);
				}
				System.out.println();
			}
		}
		System.out.println();

		
		
		
	}

	/**
	 * @param fileName
	 *            , filename with xml structure of the form:
	 * 
	 *            <players> <player ... /> </players>
	 * 
	 *            <dedication_tokens ... />
	 * 
	 *            <favor_tokens ... />
	 * 
	 *            <available_lantern_cards ... />
	 * 
	 *            <available_lake_tiles ... />
	 * 
	 *            <game_board ... />
	 */
	public static GameEngine loadExistingGame(String fileName)
			throws ParserConfigurationException, SAXException, IOException {
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

			//
			if (node.getNodeName().equals("dedication_tokens")) {
				dedicationTokens = loadDedicationTokens((Element) node);
			} else if (node.getNodeName().equals("available_lake_tiles")) {
				availableLakeTiles = loadLakeTiles((Element) node);
			} else if (node.getNodeName().equals("available_lantern_cards")) {
				availableLanternCards = loadLanternCards((Element) node);
			} else if (node.getNodeName().equals("favor_tokens")) {
				favorTokens = loadFavorTokens((Element) node);
			} else if (node.getNodeName().equals("players")) {
				// list of players
				NodeList playersElementList = ((Element) node)
						.getElementsByTagName("player");

				//
				for (int j = 0; j < playersElementList.getLength(); j++) {
					// should add to true to this if its the current players
					// turn
					LinkedList<Boolean> isCurrent = new LinkedList<Boolean>();
					//
					Player player = loadPlayer((Element) nodeList.item(i),
							isCurrent);
					//
					players.add(player);
					//
					if (isCurrent.contains(true)) {
						currentPlayer = player;
					}
				}
			} else if (node.getNodeName().equals("game_board")) {

			}

		}

		// game = new GameEngine(numOfPlayers, playerTurn, tokensCount);
		// game.start();
		return null;
	}

	/**
	 * @param fileName
	 *            file name to save the game
	 * @param gameEngine
	 *            gameEngine to save
	 * @throws ParserConfigurationException
	 */
	public void saveGame(String fileName, GameEngine gameEngine)
			throws ParserConfigurationException {
		//
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// core
		Document gameDoc = docBuilder.newDocument();

		// TODO continue, save players, cards, board etc..
	}

	/**
	 * @param playerElement
	 *            example: <player current="true" four_kind_score="2"
	 *            three_pair_score="2" seven_unique_score="1" total_score="5" >
	 *            <lake_tiles ... /> <favor_tokens ... /> <lantern_cards ... />
	 *            </player>
	 * */
	public static Player loadPlayer(Element playerElement,
			LinkedList<Boolean> isCurrent) {
		//
		int favorTokens = loadFavorTokens((Element) playerElement
				.getElementsByTagName("favor_tokens").item(0));

		//
		DedicationTokens dedicationTokens = loadDedicationTokens((Element) playerElement
				.getElementsByTagName("dedication_tokens"));

		//
		LanternCards loadLanternCards = loadLanternCards((Element) playerElement
				.getElementsByTagName("lantern_cards"));

		//
		// TODO changed to a list of lake tiles, (Player.playerLTStack),
		// update the xml, and this cariable to an array instead
		LakeTiles lakeTiles = loadLakeTiles((Element) playerElement
				.getElementsByTagName("lake_tiles"));

		// four
		int fourKindScore = Integer.parseInt(playerElement
				.getAttribute("four_kind_score"));
		int threePairScore = Integer.parseInt(playerElement
				.getAttribute("three_pair_score"));
		int sevenUniqueScore = Integer.parseInt(playerElement
				.getAttribute("seven_unique_score"));
		int totalScore = Integer.parseInt(playerElement
				.getAttribute("total_score"));

		//
		isCurrent.add(Boolean.parseBoolean(playerElement
				.getAttribute("current")));

		// TODO add a player constructor
		// TODO favor tokens score pay attention
		return null;
	}

	/**
	 * 
	 * @param docBuilder
	 * @param gameDoc
	 * @param gameEngine
	 */
	public void saveGamePlayers(Document gameDoc, GameEngine gameEngine) {
		// add players tag
		Element players = gameDoc.createElement("players");
		gameDoc.appendChild(players);

		//
		for (int i = 0; i < this.PlayerList.size(); i++) {
			//
			Element player = gameDoc.createElement("player");
			// Am I the current?
			player.setAttribute("current",
					Boolean.toString(this.PlayerList.get(i) == this.player));

			// TODO player attributes

		}

	}

	/**
	 * @param lakeTilesElement
	 *            example <lake_tiles id="lake_tile_id" left_color="red"
	 *            right_color="blue" up_color="red" down_color="green"
	 *            left="lake_tile_id" right="lake_tile_id" up="lake_tile_id"
	 *            down="lake_tile_id"
	 *            lake_tiles="lake_tile_id1,lake_tile_id2, ..." plaform="false"
	 *            />
	 * 
	 */
	public static LakeTiles loadLakeTiles(Element lakeTilesElement) {
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
		String[] lake_tiles_str = lakeTilesElement.getAttribute("lake_tiles")
				.split(",");
		int[] lakeTiles = new int[lake_tiles_str.length];
		for (int i = 0; i < lake_tiles_str.length; i++) {
			lakeTiles[i] = Integer.parseInt(lake_tiles_str[i]);
		}
		//
		boolean platform = Boolean.parseBoolean(lakeTilesElement
				.getAttribute("platform"));

		// TODO add LakeTiles constructor
		return null;
	}

	/**
	 * 
	 * @param element
	 *            this element will contain the lake tile info
	 * @param lakeTile
	 */
	public void saveLakeTiles(Element element, LakeTiles lakeTile) {
		// id
		element.setAttribute("id", Integer.toString(lakeTile.id));
		//
		element.setAttribute("left_color", lakeTile.leftColor);
		element.setAttribute("right_color", lakeTile.rightColor);
		element.setAttribute("up_color", lakeTile.upColor);
		element.setAttribute("down_color", lakeTile.downColor);
		//
		element.setAttribute("left", Integer.toString(lakeTile.left));
		element.setAttribute("right", Integer.toString(lakeTile.right));
		element.setAttribute("up", Integer.toString(lakeTile.up));
		element.setAttribute("down", Integer.toString(lakeTile.down));
		//
		String lakeTilesString = "";
		//
		for (int i = 0; i < lakeTile.lakeTiles.length; i++) {
			lakeTilesString += Integer.toString(lakeTile.lakeTiles[i].id);
			if (i != lakeTile.lakeTiles.length - 1) {
				lakeTilesString += ",";
			}
		}
		element.setAttribute("lake_tiles", lakeTilesString);
		//
		element.setAttribute("plaform", Boolean.toString(lakeTile.platform));
	}

	/**
	 * @param lanternCardsElement
	 *            example: <lantern_cards red="1" blue="2" green="1" white="3"
	 *            purple="2" black="0" "orange"=1 />
	 */
	public static LanternCards loadLanternCards(Element lanternCardsElement) {
		//
		int red = Integer.parseInt(lanternCardsElement.getAttribute("red"));
		int blue = Integer.parseInt(lanternCardsElement.getAttribute("blue"));
		int green = Integer.parseInt(lanternCardsElement.getAttribute("green"));
		int white = Integer.parseInt(lanternCardsElement.getAttribute("white"));
		int purple = Integer.parseInt(lanternCardsElement
				.getAttribute("purple"));
		int orange = Integer.parseInt(lanternCardsElement
				.getAttribute("orange"));
		int black = Integer.parseInt(lanternCardsElement.getAttribute("black"));

		// TODO remove numerOfPlayers from constuctor?
		return new LanternCards(0, red, blue, green, white, purple, black,
				orange);
	}

	/**
	 * 
	 * @param element
	 *            this element will contain the lantern cards info
	 * @param lanternCards
	 */
	public void saveLanternCards(Element element, LanternCards lanternCards) {
		//
		element.setAttribute("red",
				Integer.toString(lanternCards.redCardCount()));
		element.setAttribute("blue",
				Integer.toString(lanternCards.blueCardCount()));
		element.setAttribute("green",
				Integer.toString(lanternCards.greenCardCount()));
		element.setAttribute("white",
				Integer.toString(lanternCards.whiteCardCount()));
		element.setAttribute("purple",
				Integer.toString(lanternCards.purpleCardCount()));
		element.setAttribute("orange",
				Integer.toString(lanternCards.orangeCardCount()));
		element.setAttribute("black",
				Integer.toString(lanternCards.blackCardCount()));
	}

	/**
	 * @param dedicationTokenElement
	 *            example <dedication_tokens four_kind="2" three_pair="1"
	 *            seven_unique="2" generic_four="4" />
	 * @return a dedication token
	 */
	public static DedicationTokens loadDedicationTokens(
			Element dedicationTokenElement) {
		//
		int fourKind = Integer.parseInt(dedicationTokenElement
				.getAttribute("four_kind"));
		;
		int threePair = Integer.parseInt(dedicationTokenElement
				.getAttribute("three_pair"));
		;
		int sevenUnique = Integer.parseInt(dedicationTokenElement
				.getAttribute("seven_unique"));
		;
		int genericFour = Integer.parseInt(dedicationTokenElement
				.getAttribute("generic_four"));
		;

		// TODO remove numberOfPlayers from constructor?
		return new DedicationTokens(0, fourKind, threePair, sevenUnique,
				genericFour);
	}

	/**
	 * 
	 * @param element
	 *            , this element will contain the dedication tokens
	 * @param dedicationTokens
	 */
	public void saveDedicationTokens(Element element,
			DedicationTokens dedicationTokens) {
		//
		element.setAttribute("four_kind",
				Integer.toString(dedicationTokens.fourOfKindCount()));
		element.setAttribute("three_pair",
				Integer.toString(dedicationTokens.threePairCount()));
		element.setAttribute("seven_unique",
				Integer.toString(dedicationTokens.sevenUniqueCount()));
		element.setAttribute("generic_four",
				Integer.toString(dedicationTokens.genericFourCount()));
	}

	/**
	 * 
	 * @param favorTokensElement
	 *            example: <favor_tokens value="2" />
	 * @return
	 */
	public static int loadFavorTokens(Element favorTokensElement) {
		return Integer.parseInt(favorTokensElement.getAttribute("value"));
	}

	public void saveFavorTokens(Element element, FavorTokens favorTokens) {
		element.setAttribute("value", Integer.toString(favorTokens.getTokens()));
	}
}
