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
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount, purpleCount, blackCount, orangeCount;
	private Player player1 = new Player("player1");
	private Player player2 = new Player("player2");
	private Player player3 = new Player("player3");
	private Player player4 = new Player("player4");
	
	public GameEngine(int numOfPlayers){

		
		this.numOfPlayer = numOfPlayers;
		
		
		this.lanternCards = new LanternCards(numOfPlayers);
		this.dedicationTokens = new DedicationTokens(numOfPlayers);
		this.favorTokens = new FavorTokens(20);
		if(this.numOfPlayer == 2){
			PlayerList.add(player1);
			PlayerList.add(player2);
			
		}else if(this.numOfPlayer == 3){
			PlayerList.add(player1);
			PlayerList.add(player2);
			PlayerList.add(player3);
			
			
		}else if(this.numOfPlayer == 4){
			PlayerList.add(player1);
			PlayerList.add(player2);
			PlayerList.add(player3);
			PlayerList.add(player4);
		}
		
		
	}

	public GameEngine(int numOfPlayers,int playerTurn,int favorTokensCount,int redCount,int blueCount,int greenCount,int whiteCount,int purpleCount,int blackCount,int orangeCount){
		
		this.numOfPlayer = numOfPlayers;
		this.playerTurn = playerTurn;
		this.favorTokensCount = favorTokensCount;
		
		this.lanternCards = new LanternCards(numOfPlayers, redCount, blueCount, greenCount, whiteCount, purpleCount, blackCount, orangeCount);
		this.dedicationTokens = new DedicationTokens(numOfPlayers,8,9,10,4);
		this.favorTokens = new FavorTokens(favorTokensCount);
	}
	
	public void start(){
		this.load();
	}
	
	public void update(){
		
	}
	
	public void load(){
		
	}
	
	public void gameloop(){
		
	}
	
	public void unload(){
		
	}
	public void startNewGame()
	{
		
	}
}
