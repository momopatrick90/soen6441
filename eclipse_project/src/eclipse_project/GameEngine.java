package eclipse_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
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
	private int favorTokenAtStart = 0;
	private LanternCards lanternCards;
	private LakeTiles lakeTiles;
	private LakeTiles startTile;
	private ArrayList<LakeTiles> lTiles;
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount,
			purpleCount, blackCount, orangeCount;
	private String playerWhoStartsGame;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");

	/**
	 * Default Constructor
	 */
	public GameEngine() {
		super();
	}

	/**
	 * This method is called when the user starts a new game.
	 * 
	 * @param numOfPlayers
	 *            - number of players that will be playing the game
	 */
	public GameEngine(int numOfPlayer) {

		this.numOfPlayer = numOfPlayer;
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
			player1.setFavorToken(favorTokenAtStart);
			PlayerList.add(player1);
			player2.setFavorToken(favorTokenAtStart);
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
			player1.setFavorToken(favorTokenAtStart);
			player2.setFavorToken(favorTokenAtStart);
			player3.setFavorToken(favorTokenAtStart);
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
			player1.setFavorToken(favorTokenAtStart);
			player2.setFavorToken(favorTokenAtStart);
			player3.setFavorToken(favorTokenAtStart);
			player4.setFavorToken(favorTokenAtStart);
			
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
		for(int i=0;i<lakeTiles.globalLakeTiles.size();i++)
		{
			System.out.println("id "+lakeTiles.globalLakeTiles.get(i).id+" "+ "leftColor " + " "
				+ lakeTiles.globalLakeTiles.get(i).leftColor + " " + "rightColor" + " "
				+ lakeTiles.globalLakeTiles.get(i).rightColor + " " + "upColor" + " "
				+ lakeTiles.globalLakeTiles.get(i).upColor + " " + "downColor" + " "
				+ lakeTiles.globalLakeTiles.get(i).downColor + " " + "platform" + " "
				+ lakeTiles.globalLakeTiles.get(i).platform);
		}
		System.out.println();
		//System.out.println(dedicationTokens.genericFourCount());
		System.out.println("Details of the Dedication Tokens:");
		System.out.println("Four Of Kind Tokens:");
		dedicationTokens.getFourOfKindInfo();
		System.out.println();
		System.out.println("Three Pair Tokens:");
		dedicationTokens.getThreePairInfo();
		System.out.println();
		System.out.println("Seven Unique Tokens:");
		dedicationTokens.getSevenUniqueInfo();
		System.out.println();
		System.out.println("Generic Four Tokens:");
		dedicationTokens.getGenericFourInfo();
		System.out.println();
		System.out.println();
		
		System.out.println("Details of FavorTokens:");
		System.out.println("Number of Favor Tokens present on stack :"+favorTokens.getTokens());
		System.out.println("Number of Favor Tokens present with players: 0");	
		System.out.println();
		
		System.out.println("Details of LanternCards:");
		System.out.println("Number of Black lantern cards available on Stack: "+lanternCards.blackCardCount());
		System.out.println("Number of Red lantern cards available on Stack: "+lanternCards.redCardCount());
		System.out.println("Number of Green lantern cards available on Stack: "+lanternCards.greenCardCount());
		System.out.println("Number of White lantern cards available on Stack: "+lanternCards.whiteCardCount());
		System.out.println("Number of Purple lantern cards available on Stack: "+lanternCards.purpleCardCount());
		System.out.println("Number of Blue lantern cards available on Stack: "+lanternCards.blueCardCount());
		System.out.println("Number of Orange lantern cards available on Stack: "+lanternCards.orangeCardCount());
		
		System.out.println();
		
		PlayerList=player1.assignBoardPosition(this.numOfPlayer,PlayerList,startTile);
		
		for(int i=0;i<PlayerList.size();i++)	
		System.out.println("Position of Player"+(i+1)+": "+PlayerList.get(i).boardPosition);
		System.out.println();
		
		System.out.println("Details of the startTile:");
		System.out.println("id" + startTile.id + " " + "leftColor " + " "
				+ startTile.leftColor + " " + "rightColor" + " "
				+ startTile.rightColor + " " + "upColor" + " "
				+ startTile.upColor + " " + "downColor" + " "
				+ startTile.downColor + " " + "platform" + " "
				+ startTile.platform);
		System.out.println();
		
		playerWhoStartsGame=player1.turnToStartGame(this.numOfPlayer, PlayerList, startTile);
		System.out.println(playerWhoStartsGame +" will start the game as startTile is pointing to it else we follow clockwise rule");
		
		System.out.println();
		lanternCards.assignLanternCards(this.numOfPlayer,PlayerList,startTile,lanternCards);
		System.out.println("Players will get lakeTiles according to startTile");
		for(int i=0;i<PlayerList.size();i++)
		{
			System.out.println("Details of the lanternCards available to Player"+(i+1)+":");
			System.out.println("Number of Black LanternCards:\t"+PlayerList.get(i).playerLCStack.blackCardCount());
			System.out.println("Number of Blue LanternCards:\t"+PlayerList.get(i).playerLCStack.blueCardCount());
			System.out.println("Number of Green LanternCards:\t"+PlayerList.get(i).playerLCStack.greenCardCount());
			System.out.println("Number of Orange LanternCards:\t"+PlayerList.get(i).playerLCStack.orangeCardCount());
			System.out.println("Number of Purple LanternCards:\t"+PlayerList.get(i).playerLCStack.purpleCardCount());
			System.out.println("Number of White LanternCards: \t"+PlayerList.get(i).playerLCStack.whiteCardCount());
			System.out.println("Number of Red LanternCards:\t"+PlayerList.get(i).playerLCStack.redCardCount());
			System.out.println();
		}
		
		System.out.println("One example of laketile being added to GameBoard");
		lakeTiles.placeTile(36, 37,board,PlayerList.get(0).getLakeTiles().get(0));
		
		System.out.println("left"+board.tilesOnBoard.get(0).left+"right"+board.tilesOnBoard.get(0).right+"down"+board.tilesOnBoard.get(0).down+"up"+board.tilesOnBoard.get(0).up+"id"+board.tilesOnBoard.get(0).id);
		System.out.println("left"+board.tilesOnBoard.get(1).left+"right"+board.tilesOnBoard.get(1).right+"down"+board.tilesOnBoard.get(1).down+"up"+board.tilesOnBoard.get(1).up+"id"+board.tilesOnBoard.get(1).id);
		

	
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
	public void loadExistingGame(String fileName)
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

			if (node.getNodeName().equals("players")) {
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
			}
		}

		//
		for (int i = 0; i < nodeList.getLength(); i++) {
			//
			Node node = nodeList.item(i);

			//
			if (node.getNodeName().equals("dedication_tokens")) {
			} else if (node.getNodeName().equals("available_lake_tiles")) {
				availableLakeTiles = loadLakeTiles((Element) node);
			} else if (node.getNodeName().equals("available_lantern_cards")) {
				availableLanternCards = loadLanternCards((Element) node);
			} else if (node.getNodeName().equals("favor_tokens")) {
				favorTokens = loadFavorTokens((Element) node);
			} else if (node.getNodeName().equals("game_board")) {

			}

		}

		this.numOfPlayer = players.size();

		this.lanternCards = availableLanternCards;
		this.dedicationTokens = dedicationTokens;
		this.favorTokens = new FavorTokens(favorTokens);
	}

	/**
	 * @param fileName
	 *            file name to save the game
	 * @param gameEngine
	 *            gameEngine to save
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void saveGame(String fileName, GameEngine gameEngine)
			throws ParserConfigurationException, TransformerException {
		//
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// core
		Document gameDoc = docBuilder.newDocument();
		Element game = gameDoc.createElement("game");
		gameDoc.appendChild(game);

		saveGamePlayers(gameDoc, game);

		// add Lake tiles tag
		Element _lakeTiles = gameDoc.createElement("gloabol_lake_tiles_stack");

		//
		for (int j = 0; j < this.lakeTiles.globalLakeTiles.size(); j++) {
			//
			Element _lakeTilesInner = gameDoc.createElement("lake_tiles");
			//
			System.out.println("Check what is being printed here"
					+ this.lakeTiles.globalLakeTiles.get(j).id);

			// id
			_lakeTilesInner.setAttribute("id",
					Integer.toString(this.lakeTiles.globalLakeTiles.get(j).id));
			//
			_lakeTilesInner.setAttribute("left_color",
					this.lakeTiles.globalLakeTiles.get(j).leftColor);
			_lakeTilesInner.setAttribute("right_color",
					this.lakeTiles.globalLakeTiles.get(j).rightColor);
			_lakeTilesInner.setAttribute("up_color",
					this.lakeTiles.globalLakeTiles.get(j).upColor);
			_lakeTilesInner.setAttribute("down_color",
					this.lakeTiles.globalLakeTiles.get(j).downColor);
			//
			_lakeTilesInner.setAttribute("left", Integer
					.toString(this.lakeTiles.globalLakeTiles.get(j).left));
			_lakeTilesInner.setAttribute("right", Integer
					.toString(this.lakeTiles.globalLakeTiles.get(j).right));
			_lakeTilesInner.setAttribute("up",
					Integer.toString(this.lakeTiles.globalLakeTiles.get(j).up));
			_lakeTilesInner.setAttribute("down", Integer
					.toString(this.lakeTiles.globalLakeTiles.get(j).down));
			_lakeTilesInner.setAttribute("platform", Boolean
					.toString(this.lakeTiles.globalLakeTiles.get(j).platform));
			//
			_lakeTiles.appendChild(_lakeTilesInner);
		}
		game.appendChild(_lakeTiles);

		// lattern card
		Element _lanternCards = gameDoc.createElement("lantern_cards");
		//
		saveLanternCards(_lanternCards, lanternCards);
		//
		game.appendChild(_lanternCards);

		// Dedication card
		Element _dedicationTokens = gameDoc.createElement("dedication_tokens");
		//
		saveDedicationTokens(_dedicationTokens, dedicationTokens);
		//
		game.appendChild(_dedicationTokens);
		
		// saveFavorTokens
		Element _favorTokens = gameDoc.createElement("favor_tokens");
		//
		saveFavorTokens(_favorTokens, favorTokens);
		//
		game.appendChild(_favorTokens);
		

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(gameDoc);
		StreamResult result = new StreamResult(new File(fileName+".xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

		// TODO continue, save players, cards, board etc..
	}

	/**
	 * @param playerElement
	 *            example: <player current="true" four_kind_score="2"
	 *            three_pair_score="2" seven_unique_score="1"
	 *            favor_token_score="5" name="palyer_name" > <lake_tiles_stack >
	 *            <lake_tiles ... /> </lake_tiles_stack > <favor_tokens ... />
	 *            <lantern_cards ... /> </player>
	 * */
	public Player loadPlayer(Element playerElement,
			LinkedList<Boolean> isCurrent) {
		//
		// int favorTokens = loadFavorTokens((Element) playerElement
		// .getElementsByTagName("favor_tokens").item(0));

		//
		DedicationTokens dedicationTokens = loadDedicationTokens((Element) playerElement
				.getElementsByTagName("dedication_tokens"));

		//
		LanternCards loadLanternCards = loadLanternCards((Element) playerElement
				.getElementsByTagName("lantern_cards"));

		//
		ArrayList<LakeTiles> lakeTilesStack = new ArrayList<LakeTiles>();
		//
		LinkedList<LakeTiles> lakeTilesList = loadMultipleLakeTiles((Element) playerElement
				.getElementsByTagName("lake_tiles_stack"));
		//
		for (int i = 0; i < lakeTilesList.size(); i++) {
			lakeTilesStack.add(lakeTilesList.get(i));
		}

		// four
		int fourKindScore = Integer.parseInt(playerElement
				.getAttribute("four_kind_score"));
		int threePairScore = Integer.parseInt(playerElement
				.getAttribute("three_pair_score"));
		int sevenUniqueScore = Integer.parseInt(playerElement
				.getAttribute("seven_unique_score"));
		int favorTokenScore = Integer.parseInt(playerElement
				.getAttribute("favor_token_score"));

		// name
		String name = playerElement.getAttribute("name");

		//
		isCurrent.add(Boolean.parseBoolean(playerElement
				.getAttribute("current")));

		//
		return new Player(name, loadLanternCards, lakeTilesStack,
				favorTokenScore, fourKindScore, threePairScore,
				sevenUniqueScore);
	}

	/**
	 * 
	 * @param docBuilder
	 * @param gameDoc
	 * @param players
	 * @param gameEngine
	 */
	public void saveGamePlayers(Document gameDoc, Element players) {
		// add players tag

		//
		for (int i = 0; i < this.PlayerList.size(); i++) {
			//
			Element player = gameDoc.createElement("player");
			// Am I the current?
			if(this.PlayerList.get(i).name.equalsIgnoreCase(playerWhoStartsGame)){
				player.setAttribute("current",
						Boolean.toString(true));
			}
			else
			{
				player.setAttribute("current",
						Boolean.toString(false));
			}
			
			// TODO player attributes
			players.appendChild(player);

			//
			Element lakeTiles = gameDoc.createElement("lake_tiles_stack");
			//
			for (int j = 0; j < this.PlayerList.get(i).playerLTStack.size(); j++) {
				//
				Element lakeTilesInner = gameDoc.createElement("lake_tiles");
				//
				System.out.println("Check what is being printed here"
						+ this.PlayerList.get(i).playerLTStack.get(j).id);

				saveLakeTiles(lakeTilesInner,
						this.PlayerList.get(i).playerLTStack, j, gameDoc);
				//
				lakeTiles.appendChild(lakeTilesInner);
			}
			player.appendChild(lakeTiles);

			//

			//
			player.setAttribute("four_kind_score", ""
					+ this.PlayerList.get(i).playerScore_fourKind);
			player.setAttribute("three_pair_score", ""
					+ this.PlayerList.get(i).playerScore_threePair);
			player.setAttribute("seven_unique_score",
					"" + this.PlayerList.get(i).playerScore_sevenUnique);
			player.setAttribute("favor_token_score",
					"" + this.PlayerList.get(i).favorTokenScore);
			//
			player.setAttribute("name", "" + this.PlayerList.get(i).name);

			// lattern card
			Element _lanternCards = gameDoc.createElement("lantern_cards");
			//
			this.saveLanternCards(_lanternCards,
					this.PlayerList.get(i).playerLCStack);
			//
			player.appendChild(_lanternCards);
		}

	}

	public LinkedList<LakeTiles> loadMultipleLakeTiles(Element element) {
		//
		LinkedList<LakeTiles> lakeTiles = new LinkedList<LakeTiles>();
		//
		NodeList lakeTilesStackNL = element
				.getElementsByTagName("lake_tiles_stack");
		//
		for (int j = 0; j < lakeTilesStackNL.getLength(); j++) {
			// should add to true to this if its the current players
			//
			LakeTiles lakeTile = loadLakeTiles((Element) lakeTilesStackNL
					.item(j));

			//
			lakeTiles.push(lakeTile);
		}

		return lakeTiles;
	}

	/**
	 * @param lakeTilesElement
	 *            example <lake_tiles id="lake_tile_id" left_color="red"
	 *            right_color="blue" up_color="red" down_color="green"
	 *            left="lake_tile_id" right="lake_tile_id" up="lake_tile_id"
	 *            down="lake_tile_id"
	 *            lake_tiles="lake_tile_id1,lake_tile_id2, ..."
	 *            platform="false",
	 *            global_lake_tiles="lake_tile_id1,lake_tile_id2, ..." >
	 *            <lake_tiles> <lake_tiles> .... </lake_tiles> </lake_tiles>
	 * 
	 *            <global_lake_tiles> <lake_tiles> .... </lake_tiles>
	 *            </global_lake_tiles>
	 * 
	 *            </lake_tiles>
	 */
	public LakeTiles loadLakeTiles(Element lakeTilesElement) {
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
		LinkedList<LakeTiles> lakeTilesList = loadMultipleLakeTiles((Element) lakeTilesElement
				.getElementsByTagName("lake_tiles"));
		//
		LakeTiles[] lakeTiles = new LakeTiles[lakeTilesList.size()];
		//
		for (int i = 0; i < lakeTilesList.size(); i++) {
			lakeTiles[i] = lakeTilesList.get(i);
		}

		//
		lakeTilesList = loadMultipleLakeTiles((Element) lakeTilesElement
				.getElementsByTagName("lake_tiles_global"));
		//
		Stack<LakeTiles> globalLakeTiles = new Stack<LakeTiles>();
		//
		for (int i = 0; i < lakeTilesList.size(); i++) {
			globalLakeTiles.push(lakeTilesList.get(i));
		}
		//
		boolean platform = Boolean.parseBoolean(lakeTilesElement
				.getAttribute("platform"));

		//
		return new LakeTiles(leftColor, rightColor, upColor, downColor, left,
				right, up, down, id, platform, lakeTiles, globalLakeTiles);
	}

	/**
	 * 
	 * @param element
	 *            this element will contain the lake tile info
	 * @param playerLTStack
	 * @param j
	 * @param gameDoc
	 */
	public void saveLakeTiles(Element element,
			ArrayList<LakeTiles> playerLTStack, int i, Document gameDoc) {

		// id
		element.setAttribute("id", Integer.toString(playerLTStack.get(i).id));
		//
		element.setAttribute("left_color", playerLTStack.get(i).leftColor);
		element.setAttribute("right_color", playerLTStack.get(i).rightColor);
		element.setAttribute("up_color", playerLTStack.get(i).upColor);
		element.setAttribute("down_color", playerLTStack.get(i).downColor);
		//
		element.setAttribute("left",
				Integer.toString(playerLTStack.get(i).left));
		element.setAttribute("right",
				Integer.toString(playerLTStack.get(i).right));
		element.setAttribute("up", Integer.toString(playerLTStack.get(i).up));
		element.setAttribute("down",
				Integer.toString(playerLTStack.get(i).down));
		element.setAttribute("platform",
				Boolean.toString(playerLTStack.get(i).platform));

	}

	/**
	 * @param lanternCardsElement
	 *            example: <lantern_cards red="1" blue="2" green="1" white="3"
	 *            purple="2" black="0" "orange"=1 />
	 */
	public LanternCards loadLanternCards(Element lanternCardsElement) {
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
	public DedicationTokens loadDedicationTokens(Element dedicationTokenElement) {
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
	public int loadFavorTokens(Element favorTokensElement) {
		return Integer.parseInt(favorTokensElement.getAttribute("value"));
	}

	public void saveFavorTokens(Element element, FavorTokens favorTokens) {
		element.setAttribute("value", Integer.toString(favorTokens.getTokens()));
	}

	public void displayTextMode() {
		System.out.println("UnImplemented");
	}
}
