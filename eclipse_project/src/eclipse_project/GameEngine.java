package eclipse_project;

import java.util.ArrayList;

/**
 * Game Engine - Responsible for loading, updating the state of the game.
 */
public class GameEngine {
	
	private ArrayList<Player> PlayerList = new ArrayList();
	private int numOfPlayer;
	private int playerTurn;
	private int favorTokensCount;
	private LanternCards lanternCards ;
	private LakeTiles lakeTiles;
	private LakeTiles startTile;
	private ArrayList<LakeTiles> lTiles;
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount, purpleCount, blackCount, orangeCount;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");
	
	/**
	 * This method is called when the user starts a new game.
	 * 
	 * @param numOfPlayers - number of players that will be playing the game
	 */
	public GameEngine(int numOfPlayer){

		this.numOfPlayer = numOfPlayer;		
	}

	public GameEngine(int numOfPlayers, int playerTurn, int favorTokensCount, DedicationTokens dedicationObj, LanternCards lanternCardObj){
		
		this.numOfPlayer = numOfPlayers;
		
		this.lanternCards = lanternCardObj;
		this.dedicationTokens = dedicationObj;
		this.favorTokens = new FavorTokens(favorTokensCount);
	}	

	public void startNewGame()
	{
		//Set the initial variables for the lantern cards and the various tokens
				// according to the number of players.
				
				this.lanternCards = new LanternCards(this.numOfPlayer);
				this.dedicationTokens = new DedicationTokens(numOfPlayer);
				this.favorTokens = new FavorTokens(20);//For new game there will be 20 favor tokens by default
				
				
				lakeTiles = new LakeTiles();
				
				//generate a random lake tile stack
				lakeTiles.initializeLakeTiles(this.numOfPlayer);
				
				//get the lake tiles that each user will have at the start of the game
				// if 2 players it return 6 lake tiles, else if 3 -> 9  else 4 ->12
				lTiles = lakeTiles.assignLakeTiles(this.numOfPlayer);
				
				//get the starting lake tile
				startTile = lakeTiles.lakeTiles[0];
				Board board = new Board();
				
				//place the starting lake tile on the board
				board.intializeGameBoard(startTile);
				
				
				
				// give each player three lake tiles to start with.
				if(this.numOfPlayer == 2){
					
					player1.setLakeTiles(lTiles.get(0));
					player1.setLakeTiles(lTiles.get(1));
					player1.setLakeTiles(lTiles.get(2));
					PlayerList.add(player1);
					
					player2.setLakeTiles(lTiles.get(3));
					player2.setLakeTiles(lTiles.get(4));
					player2.setLakeTiles(lTiles.get(5));
					PlayerList.add(player2);
					
				}else if(this.numOfPlayer == 3){
					
					player1.setLakeTiles(lTiles.get(0));
					player1.setLakeTiles(lTiles.get(1));
					player1.setLakeTiles(lTiles.get(2));
					PlayerList.add(player1);
					
					player2.setLakeTiles(lTiles.get(3));
					player2.setLakeTiles(lTiles.get(4));
					player2.setLakeTiles(lTiles.get(5));
					PlayerList.add(player2);
					
					player3.setLakeTiles(lTiles.get(6));
					player3.setLakeTiles(lTiles.get(7));
					player3.setLakeTiles(lTiles.get(8));
					PlayerList.add(player3);

					
					
				}else if(this.numOfPlayer == 4){
					player1.setLakeTiles(lTiles.get(0));
					player1.setLakeTiles(lTiles.get(1));
					player1.setLakeTiles(lTiles.get(2));
					PlayerList.add(player1);
					
					player2.setLakeTiles(lTiles.get(3));
					player2.setLakeTiles(lTiles.get(4));
					player2.setLakeTiles(lTiles.get(5));
					PlayerList.add(player2);
					
					player3.setLakeTiles(lTiles.get(6));
					player3.setLakeTiles(lTiles.get(7));
					player3.setLakeTiles(lTiles.get(8));
					PlayerList.add(player3);
					
					player4.setLakeTiles(lTiles.get(9));
					player4.setLakeTiles(lTiles.get(10));
					player4.setLakeTiles(lTiles.get(11));
					PlayerList.add(player4);
					
				}
	}
}
