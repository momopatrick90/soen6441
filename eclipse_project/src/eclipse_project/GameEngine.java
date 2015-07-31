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
	private LinkedList<LakeTiles> lakeTilesList;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount,
			purpleCount, blackCount, orangeCount;
	private int round;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");
	private Board board = null;

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

	public void startNewGame() throws Exception, IOException {
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

		//
		round = this.numOfPlayer * 3 + lakeTiles.globalLakeTiles.size()
				+ this.numOfPlayer;

		//
		run();
	}
	
	protected Player getCurrentPlayer()
	{
		for (int playerIndex = 0; playerIndex < PlayerList.size(); playerIndex++) {
			if (PlayerList.get(playerIndex).current) {
				return PlayerList.get(playerIndex);
			}
		}
		return null;
	}
	
	protected void moveToNextPlayer()
	{
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
	
	protected void exchangeLanternCards(Player player, BufferedReader br) throws IOException
	{
		int lanternCard = 0;
		String returnLCard = "";
		String pickLCard = "";
		String regex = "\\d+";
		Scanner in = new Scanner(System.in);
		//
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		
		// Print lanterns
		System.out.println("--Lantern cards you currently have:--");
		System.out.println(player.getLanternCards());
		System.out.println("----------------------------");
		
		//
		System.out.println("Select the lantern card you want to return.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		boolean check = true;
		while (check) {
			in = new Scanner(System.in);
			returnLCard = in.nextLine();
			returnLCard.trim();
			if (!returnLCard.matches(regex)) {
				System.out.println("Invalid lantern card option");
				check = true;
			} else {
				lanternCard = Integer.valueOf(returnLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid lantern card option");
				} else {
					check = false;
				}
			}

		}
		switch(lanternCard)
		{
			case 1: returnLCard = "redCard";break;
			case 2: returnLCard = "greenCard";break;
			case 3: returnLCard = "purpleCard";break;
			case 4: returnLCard = "orangeCard";break;
			case 5: returnLCard = "whiteCard";break;
			case 6: returnLCard = "blackCard";break;
			case 7: returnLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		lanternCard = 0;
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		check = true;
		while (check) {
			in = new Scanner(System.in);
			pickLCard = in.nextLine();
			pickLCard.trim();
			if (!pickLCard.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				lanternCard = Integer.valueOf(pickLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		//
		switch(lanternCard)
		{
			case 1: pickLCard = "redCard";break;
			case 2: pickLCard = "greenCard";break;
			case 3: pickLCard = "purpleCard";break;
			case 4: pickLCard = "orangeCard";break;
			case 5: pickLCard = "whiteCard";break;
			case 6: pickLCard = "blackCard";break;
			case 7: pickLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		boolean moveState = player.spendFavorTokens(favorTokens, lanternCards,
						returnLCard, pickLCard);
		if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");
	}
	
	protected void makeDedication(Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		int move = 0;
		String regex = "\\d+";
		String option = "";
		Scanner in = new Scanner(System.in);	
		//
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		System.out.println("--Lantern cards you currently have:--");

		// Prints the number of black Cards the player has.
		System.out.println(player.getLanternCards());

		System.out.println("----------------------------");
		// TODO needs validation to be done before submitting
		// the code
		System.out.println("What Move do you want to make? Enter its corresponding integer");

		System.out.println("1:Three Pair 2:Four of a kind 3: Seven Unique");
	
		boolean check = true;
	
		while (check) {
			in = new Scanner(System.in);
			option = in.nextLine();
			option.trim();
			if (!option.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				move = Integer.valueOf(option);
				if (move < 1 || move > 3) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		
		String moveString = null;
		LanternCards returnedLanternCards = null;
		boolean state = false;
		if (move == 1) {
			moveString = "threePair";
			System.out.println("What Lantern cards do you want to return? Enter "+ "the three cards.");
			System.out.println("1: redCard 2: blueCard 3: greenCard 4: "+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

			int card = Integer.valueOf(br.readLine());
			int card2 = Integer.valueOf(br.readLine());
			int card3 = Integer.valueOf(br.readLine());

			if (player.getLanternCards().CardCount(card) >= 2
						&& player.getLanternCards().CardCount(card2) >= 2
						&& player.getLanternCards().CardCount(card3) >= 2) {
					
				System.out.println("This means that you succeded to "+ "get through the card check point");

					CardToReturn cardToReturn = new CardToReturn(
							card, card2, card3);

					returnedLanternCards = cardToReturn
							.returnStackThreeOfKind();
					System.out
							.println("these are the cards to be returned 3:"
									+ returnedLanternCards
											.CardCount(card3));
					System.out
							.println("these are the cards to be returned 2:"
									+ returnedLanternCards
											.CardCount(card2));

					System.out
							.println("these are the cards to be returned 1:"
									+ returnedLanternCards
											.CardCount(card));

					// take 2 cards with the first color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());

					// take 2 cards with the second color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor2());
					player.getLanternCards()
							.getCard(cardToReturn.getColor2());

					// take 2 cards with the third color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor3());
					player.getLanternCards()
							.getCard(cardToReturn.getColor3());
				} else {
					System.out
							.println("You do not have enough cards to make 'Three pair' move");
				}

			} else if (move == 2) {
				moveString = "FourOfKind";
				System.out
						.println("What Lantern cards do you want to return?"
								+ " Enter the card");
				System.out
						.println("1: redCard 2: blueCard 3: greenCard 4: "
								+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

				int card = Integer.valueOf(br.readLine());
				if (player.getLanternCards()
						.CardCount(card) >= 4) {

					CardToReturn cardToReturn = new CardToReturn(
							card);
					returnedLanternCards = cardToReturn
							.returnStackFourOfKind();

					// take 4 cards with the third color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
				} else {
					System.out
							.println("You do not have enough cards to make 'Four of a kind' move");
				}

			} else if (move == 3) {
				moveString = "sevenUnique";

				CardToReturn cardToReturn = new CardToReturn();
				if (cardToReturn.SevenUniqueState(player)) {
					returnedLanternCards = cardToReturn
							.returnSeveUnique();

				} else {
					System.out
							.println("You do not have enough cards to make 'Seven Unique' move");
				}
			}
			else if(move >= 4)
			{
				System.out.println("Invalid option");
			}
			if (returnedLanternCards != null) {
				state = player.pickDedicationToken(moveString,
								returnedLanternCards, lanternCards,
								dedicationTokens);
			}
			if (state) {
				System.out.println("Picked!");
				System.out.println("Score: four of a kind "
								+ player.playerScore_fourKind);
				System.out
						.println("Score: three of a kind "
								+ player.playerScore_threePair);
				System.out
						.println("Score: Seven unique "
								+ player.playerScore_sevenUnique);
			} else
				System.out
						.println(" Please revisit the game rules!");
	}
	
	public void placeLakeTile(Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("No lake tile available to play");
			return;
		}
		
		int degreeOfRotation = 0;
		String option = "";
		String regex = "\\d+";
		
		Scanner in = new Scanner(System.in);
		
		// board
		board.displayBoard(board.board, board.tilesOnBoard);
		System.out.println();
		
		// player
		player.displayPlayersLakeTile(player);
		System.out.println();
		
		//
		System.out.println("Enter the index of laketiles you want to put on board:");
		LakeTiles currentTileToPlace = player.placeLakeTile(Integer.parseInt(br.readLine()));

		//
		boolean flag = true;
		boolean placeLakeTile = false;
	
		//
		while (flag) {
			System.out
			.println("Enter the id of the adjacent tile (on board) where you want to place your LakeTile");
			int id = Integer.parseInt(br.readLine());
			
			System.out
			.println("Enter the adjacent position (right, left, up, down)");
			String AdjacentPosition = br.readLine();
			int GetColumn=lakeTiles.getColumn(board,id,AdjacentPosition);
			int GetRow=lakeTiles.getRow(board,id,AdjacentPosition);
			//System.out.println("MyColumn "+GetColumn+" row "+GetRow);
	
			System.out
					.println("Enter the degree of roatation for the tile you want to place on board");
			System.out.println("Available options 0 90 180 270");
			
			boolean check = true;
			while (check) {
				in = new Scanner(System.in);
				option = in.nextLine();
				option.trim();
				if (!option.matches(regex)) {
					System.out.println("Invalid degree. Enter again!");
					check = true;
				} else {
					degreeOfRotation = Integer.valueOf(option);
					if (degreeOfRotation != 0 || degreeOfRotation!=90 || degreeOfRotation!=180|| degreeOfRotation!=270 ) {
						System.out.println("Invalid degree. Enter again!");
					} else {
						check = false;
					}
				}

			}
			
			currentTileToPlace = lakeTiles.rotateLakeTile(
					currentTileToPlace, degreeOfRotation);

			placeLakeTile = lakeTiles.placeTile(GetColumn,
					GetRow, board, currentTileToPlace);
			if (placeLakeTile) {
				lanternCards.assignLanternCardsToPlayers(
						this.numOfPlayer, board,
						GetColumn,GetRow, currentTileToPlace,
						PlayerList, lanternCards, favorTokens);
				board.displayBoard(board.board,
						board.tilesOnBoard);
				player.displayPlayersLakeTile(player);
				System.out.println();
				System.out
						.println("Number of FavorTokens:"
								+ player.favorTokenScore);
				System.out.println();
				System.out
						.println("Details of the LanternCards Assigned to Each Player After Placing the LakeTile");
				for (int i = 0; i < PlayerList.size(); i++) {
					System.out
							.println("Player" + (i + 1) + ":");
					System.out
							.println(PlayerList.get(i).playerLCStack);
					System.out.println();
				}
				
				flag = false;
			} else
				player.pickLakeTileFromStack(currentTileToPlace);
			
			
			System.out.println();
			System.out.println("Player Pick up the new LakeTile from the Stack after placing one");
		}
	}
	

	//
	protected void run() throws NumberFormatException, IOException {
		// was the game exited
		boolean exited = false;
		
		while (round > 0) {
			//
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			//get current
			Player currentPlayer = getCurrentPlayer();
			System.out.println(currentPlayer.name+ "'s turn to play.");
			
			// Exchange lantern cards
			System.out.print("Type 1 if you want to exit the game loop, any other number to continue: ");
			int choice = Integer.parseInt(br.readLine());
			//
			if(choice == 1)
			{
				exited = true;
				break;
			}
						
			// Exchange lantern cards
			System.out.print("Type 1 if you want to exchange lantern cards, any other number to skip: ");
			choice = Integer.parseInt(br.readLine());
			//
			if(choice == 1)
			{
				exchangeLanternCards(currentPlayer, br);
			}
			
			// Make dedication
			System.out.print("Type 1 if you want to make a dedication, any other number to skip: ");
			choice = Integer.parseInt(br.readLine());
			if(choice == 1)
			{
				this.makeDedication(currentPlayer, br);
			}
			

			//place lake tile 
			this.placeLakeTile(currentPlayer, br);
			
			if(lakeTiles.hasLakeTile())
			{// Composury pick lake tile
				currentPlayer.pickLakeTileFromStack(lakeTiles.getLakeTile());
				System.out.println();
				currentPlayer.displayPlayersLakeTile(currentPlayer);
			}else
			{
				System.out.println("No lake tiles left on board to pick");
			}
			
			//
			System.out.println(currentPlayer.name+ "'s turn is over");
			
			// move to next player
			moveToNextPlayer();
			
			//
			round--;
		}
		
		
		if(!exited)
		{
			System.out.println("Game finished, player scores: ");
			//
			for (int playerIndex = 0; playerIndex < PlayerList.size(); playerIndex++) {
				///
				Player player = PlayerList.get(playerIndex);
				System.out.println("name: "+player.name
						+", fourKindScore: "+player.playerScore_fourKind
						+", threePairScore "+player.playerScore_threePair
						+", sevenUniqueScore "+player.playerScore_sevenUnique
						+", total: "+(player.playerScore_fourKind+player.playerScore_threePair+player.playerScore_sevenUnique));
				
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

		//
		displayTextMode();

		//
		run();
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
		//
		saveRound(_round, this.round);
		//
		game.appendChild(_round);

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
}
