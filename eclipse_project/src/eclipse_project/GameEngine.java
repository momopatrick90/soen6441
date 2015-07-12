package eclipse_project;

/**
 * Game Engine - Responsible for loading, updating the state of the game.
 */
public class GameEngine {
	
	private int numOfPlayer;
	private int playerTurn;
	private int favorTokensCount;
	private LanternCards lanternCards ;
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	private static int redCount, blueCount, greenCount, whiteCount, purpleCount, blackCount, orangeCount;
	
	public GameEngine(int numOfPlayers){

		
		this.numOfPlayer = numOfPlayers;
		
		this.lanternCards = new LanternCards(numOfPlayers);
		this.dedicationTokens = new DedicationTokens(numOfPlayers);
		this.favorTokens = new FavorTokens(20);
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
}
