package eclipse_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
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

	final int PROPER_END = 1, N_LAKE_TILES = 2, ENOUGH_DEDICATIONS = 3;

	public ArrayList<Player> PlayerList = new ArrayList();
	public int numOfPlayer;
	private int playerTurn;
	private final int numberOfCards = 3;
	public int favorTokensCount;
	private int favorTokenAtStart = 0;
	public LanternCards lanternCards;
	public LakeTiles lakeTiles;
	private LakeTiles startTile;
	public ArrayList<LakeTiles> lTiles;
	private Player player;
	public LinkedList<LakeTiles> lakeTilesList;
	public DedicationTokens dedicationTokens;
	public FavorTokens favorTokens;
	public static int redCount, blueCount, greenCount, whiteCount, purpleCount,
			blackCount, orangeCount;
	public int round;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");
	public Board board = null;
	public GameEndContext gameEnd = null;
	public int userInput_N, M, endingOption = 0;
	public String regex = "\\d+";
	/**
	 * Default Constructor
	 */
	public GameEngine() {
		super();
	}

	/**
	 * This constructor is called when the user starts a new game.
	 * 
	 * @param numOfPlayers
	 *            - number of players that will be playing the game
	 */
	public GameEngine(int numOfPlayer) {
		this.numOfPlayer = numOfPlayer;
	}

	/**
	 * This constructor is called when the user starts a new game.
	 * 
	 * @param numOfPlayer
	 *            - number of players that will be playing the game
	 * @param N
	 *            - User input for N, where N can lake tiles, dedication tokens
	 * @param M
	 *            - Upper limit of the user input, where M can be lake tiles,
	 *            dedication tokens
	 * @param endingOption
	 *            - Ending strategy selected by the user
	 */
	public GameEngine(int numOfPlayer, int N, int endingOption) {
		this.userInput_N = N;
		this.endingOption = endingOption;
		this.numOfPlayer = numOfPlayer;
	}

	public void startNewGame() throws Exception, IOException {
		// set the strategy of the player.
		selectStrategy();

		// Set the initial variables for the lantern cards and the various
		// tokens
		// according to the number of players.

		setEndingStrategy(this.endingOption);

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
		board = new Board();

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
		for (int i = 0; i < lakeTiles.globalLakeTiles.size(); i++) {
			System.out.println("id " + lakeTiles.globalLakeTiles.get(i).id
					+ " " + "leftColor " + " "
					+ lakeTiles.globalLakeTiles.get(i).leftColor + " "
					+ "rightColor" + " "
					+ lakeTiles.globalLakeTiles.get(i).rightColor + " "
					+ "upColor" + " "
					+ lakeTiles.globalLakeTiles.get(i).upColor + " "
					+ "downColor" + " "
					+ lakeTiles.globalLakeTiles.get(i).downColor + " "
					+ "platform" + " "
					+ lakeTiles.globalLakeTiles.get(i).platform);
		}
		System.out.println();
		// System.out.println(dedicationTokens.genericFourCount());
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
		System.out.println("Number of Favor Tokens present on stack :"
				+ favorTokens.getTokens());
		System.out.println("Number of Favor Tokens present with players: 0");
		System.out.println();

		System.out.println("Details of LanternCards:");
		System.out.println("Number of Black lantern cards available on Stack: "
				+ lanternCards.blackCardCount());
		System.out.println("Number of Red lantern cards available on Stack: "
				+ lanternCards.redCardCount());
		System.out.println("Number of Green lantern cards available on Stack: "
				+ lanternCards.greenCardCount());
		System.out.println("Number of White lantern cards available on Stack: "
				+ lanternCards.whiteCardCount());
		System.out
				.println("Number of Purple lantern cards available on Stack: "
						+ lanternCards.purpleCardCount());
		System.out.println("Number of Blue lantern cards available on Stack: "
				+ lanternCards.blueCardCount());
		System.out
				.println("Number of Orange lantern cards available on Stack: "
						+ lanternCards.orangeCardCount());

		System.out.println();

		PlayerList = player1.assignBoardPosition(this.numOfPlayer, PlayerList,
				startTile);

		for (int i = 0; i < PlayerList.size(); i++)
			System.out.println("Position of Player" + (i + 1) + ": "
					+ PlayerList.get(i).boardPosition);
		System.out.println();

		System.out.println("Details of the startTile:");
		System.out.println("id" + startTile.id + " " + "leftColor " + " "
				+ startTile.leftColor + " " + "rightColor"
				+ startTile.rightColor + " " + "upColor" + startTile.upColor
				+ " " + "downColor" + startTile.downColor + " " + "platform"
				+ startTile.platform);
		System.out.println();

		PlayerList = player1.turnToStartGame(this.numOfPlayer, PlayerList,
				startTile);

		lanternCards.assignLanternCards(this.numOfPlayer, PlayerList,
				startTile, lanternCards);
		System.out
				.println("Players will get lanternCards according to startTile");
		System.out.println();
		for (int i = 0; i < PlayerList.size(); i++) {
			System.out
					.println("Details of the lanternCards available to Player"
							+ (i + 1) + ":");
			System.out.println("Number of Black LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.blackCardCount());
			System.out.println("Number of Blue LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.blueCardCount());
			System.out.println("Number of Green LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.greenCardCount());
			System.out.println("Number of Orange LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.orangeCardCount());
			System.out.println("Number of Purple LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.purpleCardCount());
			System.out.println("Number of White LanternCards: \t"
					+ PlayerList.get(i).playerLCStack.whiteCardCount());
			System.out.println("Number of Red LanternCards:\t"
					+ PlayerList.get(i).playerLCStack.redCardCount());
			System.out.println();
		}

		if (this.endingOption == N_LAKE_TILES)
			round = this.numOfPlayer * 3 + this.userInput_N + this.numOfPlayer;
		else
			round = this.numOfPlayer * 3 + lakeTiles.globalLakeTiles.size()
					+ this.numOfPlayer;
		//
		run();
	}

	/**
	 * Prompt user to select the strategy of the player. (Human Player, Random
	 * Player, Greedy Player, Unfriendly Player, Clever Player)
	 */
	private void selectStrategy() {
		String strategy = "HumanPlayer";
		System.out.println("Available Strategies:"
				+ " 1. Human Player 2. Random Player 3.Greedy Player "
				+ "4.Unfriendly Player 5.Clever Player");
		System.out.println();

		Scanner in = new Scanner(System.in);
		int choice = 0;
		String input="";
		if (this.numOfPlayer == 2) {
			System.out.println("Enter the strategy for player 1");
			boolean check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player1.setStrategy(strategy);
					}
				}

			}//

			System.out.println("Enter the strategy for player 2");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player2.setStrategy(strategy);
					}
				}

			}//
		}
		if (this.numOfPlayer == 3) {

			/*System.out.println("Enter the strategy for player 1");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player1.setStrategy(strategy);

			System.out.println("Enter the strategy for player 2");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player2.setStrategy(strategy);

			System.out.println("Enter the strategy for player 3");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player3.setStrategy(strategy);*/
			System.out.println("Enter the strategy for player 1");
			boolean check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player1.setStrategy(strategy);
					}
				}

			}//
			
			System.out.println("Enter the strategy for player 2");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player2.setStrategy(strategy);
					}
				}

			}//
			
			System.out.println("Enter the strategy for player 3");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player3.setStrategy(strategy);
					}
				}

			}//
		}
		if (this.numOfPlayer == 4) {

			/*System.out.println("Enter the strategy for player 1");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player1.setStrategy(strategy);

			System.out.println("Enter the strategy for player 2");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player2.setStrategy(strategy);

			System.out.println("Enter the strategy for player 3");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player3.setStrategy(strategy);

			System.out.println("Enter the strategy for player 4");

			choice = in.nextInt();
			strategy = checkChoice(choice);

			player4.setStrategy(strategy);*/
			System.out.println("Enter the strategy for player 1");
			boolean check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player1.setStrategy(strategy);
					}
				}

			}//
			System.out.println("Enter the strategy for player 2");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player2.setStrategy(strategy);
					}
				}

			}//
			System.out.println("Enter the strategy for player 3");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player3.setStrategy(strategy);
					}
				}

			}//
			System.out.println("Enter the strategy for player 4");
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("strategy is invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 1 || choice > 5) {
						System.out.println("strategy is invalid input. Enter again!");
					} else {
						check = false;
						strategy = checkChoice(choice);
						player4.setStrategy(strategy);
					}
				}

			}//
		}

	}

	private String checkChoice(int choice) {

		String strategy = "HumanPlayer";
		if (choice == 1)
			strategy = "HumanPlayer";
		else if (choice == 2)
			strategy = "RandomPlayer";
		else if (choice == 3)
			strategy = "GreedyPlayer";
		else if (choice == 4)
			strategy = "UnfriendlyPlayer";
		else if (choice == 5)
			strategy = "CleverPlayer";
		return strategy;

	}

	protected Player getCurrentPlayer() {
		for (int playerIndex = 0; playerIndex < PlayerList.size(); playerIndex++) {
			if (PlayerList.get(playerIndex).current) {
				return PlayerList.get(playerIndex);
			}
		}
		return null;
	}

	protected void moveToNextPlayer() {
		for (int playerIndex = 0; playerIndex < PlayerList.size(); playerIndex++) {
			if (PlayerList.get(playerIndex).current) {
				//
				PlayerList.get(playerIndex).setCurrent(false);
				//
				if (playerIndex == PlayerList.size() - 1) {
					PlayerList.get(0).setCurrent(true);
				} else
					PlayerList.get(++playerIndex).setCurrent(true);
			}
		}
	}

	//
	protected void run() throws NumberFormatException, IOException {
		// was the game exited
		boolean exited = false;
		boolean check = true;
		String input = "";
		Scanner in;
		while (!this.gameEnd.executeStrategy(this)) {
			//
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			//
			System.out
					.println("==========================================================================");

			// get current
			Player currentPlayer = getCurrentPlayer();
			System.out.println(currentPlayer.name + "'s turn to play.");

			// Exit game loop
			System.out.print("Type 1 if you want to exit the game loop, any other number to continue: ");
			
			int choice =0;
			
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 0) {
						System.out.println("invalid input. Enter again!");
					} else {
						check = false;
					}
				}

			}//
			//
			if (choice == 1) {
				exited = true;
				break;
			}

			//
			currentPlayer.playerStrategy.play(this, currentPlayer);

			//
			System.out.println(currentPlayer.name + "'s turn is over");

			// move to next player
			moveToNextPlayer();

			round--;
		}

		if (!exited) {
			System.out.println("Game finished, player scores: ");
			//
			for (int playerIndex = 0; playerIndex < PlayerList.size(); playerIndex++) {
				// /
				Player player = PlayerList.get(playerIndex);
				System.out
						.println("name: "
								+ player.name
								+ ", fourKindScore: "
								+ player.playerScore_fourKind
								+ ", threePairScore "
								+ player.playerScore_threePair
								+ ", sevenUniqueScore "
								+ player.playerScore_sevenUnique
								+ ", total: "
								+ (player.playerScore_fourKind
										+ player.playerScore_threePair + player.playerScore_sevenUnique));

			}
		}
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

		// game node
		NodeList nodeList = doc.getElementsByTagName("game").item(0)
				.getChildNodes();

		//
		DedicationTokens dedicationTokens = null;
		LakeTiles lakeTiles = new LakeTiles();
		LanternCards availableLanternCards = null;
		int favorTokens = 0;
		ArrayList<Player> players = new ArrayList<Player>();
		Board gameBoard = null;
		int _round = 0;

		//
		for (int i = 0; i < nodeList.getLength(); i++) {
			//
			Node node = nodeList.item(i);

			//
			if (node.getNodeName().equals("dedication_tokens")) {
				dedicationTokens = this.loadDedicationTokens((Element) node);
			} else if (node.getNodeName().equals("global_lake_tiles_stack")) {
				//
				lakeTilesList = loadMultipleLakeTiles((Element) node);
				//
				for (int j = 0; j < lakeTilesList.size(); j++) {

					lakeTiles.globalLakeTiles.push(lakeTilesList.get(j));
				}
			} else if (node.getNodeName().equals("lantern_cards")) {
				availableLanternCards = loadLanternCards((Element) node);
			} else if (node.getNodeName().equals("favor_tokens")) {
				favorTokens = loadFavorTokens((Element) node);
			} else if (node.getNodeName().equals("game_board")) {

			} else if (node.getNodeName().equals("player")) {
				// list of players
				Element playersElementList = ((Element) node);
				//
				Player player = loadPlayer(playersElementList);
				//
				players.add(player);
				//

			} else if (node.getNodeName().equals("board")) {
				gameBoard = this.loadBoards((Element) node);
			} else if (node.getNodeName().equals("round")) {
				_round = this.loadRound((Element) node);
			} else if (node.getNodeName().equals("endstrategy")) {
				this.loadEndStrategy((Element) node);
			}

		}

		// populate
		this.PlayerList = players;
		this.numOfPlayer = players.size();
		this.lanternCards = availableLanternCards;
		this.dedicationTokens = dedicationTokens;
		this.favorTokens = new FavorTokens(favorTokens);
		this.lakeTiles = lakeTiles;
		this.board = gameBoard;
		this.round = _round;

		// set ending strategy
		setEndingStrategy(this.endingOption);

		//
		displayTextMode();

		//
		run();
	}

	public void setEndingStrategy(int endOption) {

		if (endingOption == PROPER_END) {
			this.gameEnd = new GameEndContext(new ProperEnd());
		} else if (endingOption == N_LAKE_TILES) {
			this.gameEnd = new GameEndContext(new AllPlayerEnd());
		} else if (endingOption == ENOUGH_DEDICATIONS) {
			this.gameEnd = new GameEndContext(new AtleastOnePlayerEnd());
		}
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
		Element _lakeTiles = gameDoc.createElement("global_lake_tiles_stack");

		//
		for (int j = 0; j < this.lakeTiles.globalLakeTiles.size(); j++) {
			//
			Element _lakeTilesInner = gameDoc.createElement("lake_tiles");
			//

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

		// save round
		Element _round = gameDoc.createElement("round");
		saveRound(_round, this.round);
		game.appendChild(_round);

		// save end-strategy
		Element _endStrategy = gameDoc.createElement("endstrategy");
		saveEndingStrategy(_endStrategy, this.userInput_N, this.endingOption);
		game.appendChild(_endStrategy);

		// game board
		Element board = gameDoc.createElement("board");
		//
		saveBoard(gameDoc, board);
		//
		game.appendChild(board);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(gameDoc);
		StreamResult result = new StreamResult(new File(fileName + ".xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);

		System.out.println("File saved!");
	}

	/**
	 * <board> <board width="2", height="3"> <entry i="0" j="0" id="1" /> <entry
	 * i="1" j="2" id="2 "/> ... </board> <tiles_on_board>...</tiles_on_board>
	 * </board>
	 */
	public Board loadBoards(Element board) {
		//
		Element innerBoardElement = (Element) board.getElementsByTagName(
				"board").item(0);
		int board_matrix[][] = new int[Integer.parseInt(innerBoardElement
				.getAttribute("width"))][Integer.parseInt(innerBoardElement
				.getAttribute("height"))];
		for (int i = 0; i < board_matrix.length; i++) {
			for (int j = 0; j < board_matrix[i].length; j++) {
				board_matrix[i][j] = -1;
			}
		}

		//
		NodeList entries = innerBoardElement.getElementsByTagName("entry");

		//
		for (int k = 0; k < entries.getLength(); k++) {
			//
			Element entry = (Element) entries.item(k);
			//
			int i = Integer.parseInt(entry.getAttribute("i"));
			int j = Integer.parseInt(entry.getAttribute("j"));
			int id = Integer.parseInt(entry.getAttribute("id"));
			//
			board_matrix[i][j] = id;
		}

		//
		ArrayList<LakeTiles> tilesOnBoard = new ArrayList<LakeTiles>();
		//
		LinkedList<LakeTiles> lakeTilesList = loadMultipleLakeTiles((Element) board
				.getElementsByTagName("tiles_on_board").item(0));
		//
		for (int i = 0; i < lakeTilesList.size(); i++) {
			tilesOnBoard.add(lakeTilesList.get(i));
		}

		return new Board(board_matrix, tilesOnBoard);
	}

	public void saveBoard(Document gameDoc, Element board) {
		// add players tag
		Element lakeTilesBoard = gameDoc.createElement("tiles_on_board");
		//
		for (int j = 0; j < this.board.tilesOnBoard.size(); j++) {
			//
			Element lakeTilesInner = gameDoc.createElement("lake_tiles");
			//
			saveLakeTiles(lakeTilesInner, this.board.tilesOnBoard.get(j),
					gameDoc);
			//
			lakeTilesBoard.appendChild(lakeTilesInner);
		}
		//
		board.appendChild(lakeTilesBoard);

		// add board matrix
		Element boardMatrix = gameDoc.createElement("board");
		//
		boardMatrix.setAttribute("width", "" + this.board.board.length);
		boardMatrix.setAttribute("height", "" + this.board.board[0].length);
		//
		for (int i = 0; i < this.board.board.length; i++) {
			for (int j = 0; j < this.board.board[0].length; j++) {
				if (this.board.board[i][j] != -1) {
					//
					Element entry = gameDoc.createElement("entry");
					//
					entry.setAttribute("i", "" + i);
					entry.setAttribute("j", "" + j);
					entry.setAttribute("id", "" + this.board.board[i][j]);
					//
					boardMatrix.appendChild(entry);
				}
			}
		}
		//
		board.appendChild(boardMatrix);
	}

	/**
	 * @param playerElement
	 *            example: <player current="true" four_kind_score="2"
	 *            three_pair_score="2" seven_unique_score="1"
	 *            favor_token_score="5" name="palyer_name" > <lake_tiles_stack>
	 *            .... </lake_tiles_stack > <lantern_cards ... /> </player>
	 * */
	public Player loadPlayer(Element playerElement) {
		//
		LanternCards loadLanternCards = loadLanternCards((Element) playerElement
				.getElementsByTagName("lantern_cards").item(0));

		//
		ArrayList<LakeTiles> lakeTilesStack = new ArrayList<LakeTiles>();
		//
		LinkedList<LakeTiles> lakeTilesList = loadMultipleLakeTiles((Element) playerElement
				.getElementsByTagName("lake_tiles_stack").item(0));

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
		String current = playerElement.getAttribute("current");

		//
		return new Player(name, current, loadLanternCards, lakeTilesStack,
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
			//
			player.setAttribute("current",
					Boolean.toString(this.PlayerList.get(i).current));

			players.appendChild(player);

			//
			Element lakeTiles = gameDoc.createElement("lake_tiles_stack");
			//
			for (int j = 0; j < this.PlayerList.get(i).playerLTStack.size(); j++) {
				//
				Element lakeTilesInner = gameDoc.createElement("lake_tiles");

				saveLakeTiles(lakeTilesInner,
						(this.PlayerList.get(i).playerLTStack).get(j), gameDoc);
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
		NodeList lakeTilesStackNL = element.getElementsByTagName("lake_tiles");
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
		boolean platform = Boolean.parseBoolean(lakeTilesElement
				.getAttribute("platform"));

		//
		return new LakeTiles(leftColor, rightColor, upColor, downColor, left,
				right, up, down, id, platform, new LakeTiles[23],
				new Stack<LakeTiles>());
	}

	/**
	 * 
	 * @param element
	 *            this element will contain the lake tile info
	 * @param playerLTStack
	 * @param j
	 * @param gameDoc
	 */
	public void saveLakeTiles(Element element, LakeTiles lakeTiles,
			Document gameDoc) {

		// id
		element.setAttribute("id", Integer.toString(lakeTiles.id));
		//
		element.setAttribute("left_color", lakeTiles.leftColor);
		element.setAttribute("right_color", lakeTiles.rightColor);
		element.setAttribute("up_color", lakeTiles.upColor);
		element.setAttribute("down_color", lakeTiles.downColor);
		//
		element.setAttribute("left", Integer.toString(lakeTiles.left));
		element.setAttribute("right", Integer.toString(lakeTiles.right));
		element.setAttribute("up", Integer.toString(lakeTiles.up));
		element.setAttribute("down", Integer.toString(lakeTiles.down));
		element.setAttribute("platform", Boolean.toString(lakeTiles.platform));
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

	/**
	 * 
	 * @param roundElement
	 *            example: <round value="2" />
	 * @return
	 */
	public int loadRound(Element roundElement) {
		return Integer.parseInt(roundElement.getAttribute("value"));
	}

	public void saveRound(Element element, int round) {
		element.setAttribute("value", Integer.toString(round));
	}

	/**
	 * 
	 * @param endStrategyElement
	 *            example: <round value="2" />
	 * @return
	 */
	public void loadEndStrategy(Element endStrategyElement) {
		this.endingOption = Integer.parseInt(endStrategyElement
				.getAttribute("endoption"));
		this.userInput_N = Integer.parseInt(endStrategyElement
				.getAttribute("userinput_n"));
	}

	public void saveEndingStrategy(Element element, int N, int endingOption) {
		element.setAttribute("endoption", Integer.toString(endingOption));
		element.setAttribute("userinput_n", Integer.toString(N));
	}

	public void displayTextMode() {
		System.out.println("Round: " + this.round);

		for (int x = 0; x < PlayerList.size(); x++) {

			System.out.println("Details of the LakeTiles assigned to Player"
					+ (x + 1));
			for (int i = 0; i < PlayerList.get(x).getLakeTiles().size(); i++) {
				System.out.println(PlayerList.get(x).getLakeTiles().get(i));
			}
			System.out.println();
		}

		// System.out.println(dedicationTokens.genericFourCount());
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
		System.out.println("Number of Favor Tokens present on stack :"
				+ favorTokens.getTokens());
		System.out.println("Number of Favor Tokens present with players: 0");
		System.out.println();

		System.out.println("Details of LanternCards On Board:");
		System.out.println(lanternCards);

		System.out.println();

		// ////
		System.out.println("Lake tiles on the board.  ");
		for (int i = 0; i < lakeTilesList.size(); i++) {
			System.out.println(lakeTilesList.get(i));
		}
		System.out.println();

		PlayerList = player1.assignBoardPosition(this.numOfPlayer, PlayerList,
				startTile);

		for (int i = 0; i < PlayerList.size(); i++)
			System.out.println("Position of Player" + (i + 1) + ": "
					+ PlayerList.get(i).boardPosition);
		System.out.println();

		for (int x = 0; x < PlayerList.size(); x++) {
			if (PlayerList.get(x).current) {

				System.out.println(PlayerList.get(x).name
						+ "'s turn to play. else we follow clockwise rule");
			}

		}

		System.out.println();

	}

	public int getRound() {
		return this.round;
	}
}
