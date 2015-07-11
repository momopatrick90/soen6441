package eclipse_project;

/**
 * Game Engine - Responsible for loading, updating the state of the game.
 */
public class GameEngine {
	
	private int numOfPlayer;
	private int playerTurn;
	private int tokensCount;
	private StartTile startTile;
	private Player player;
	private DedicationTokens dedicationTokens;
	private FavorTokens favorTokens;
	
	public GameEngine(int numOfPlayers,int playerTurn,int tokensCount){
		
		this.numOfPlayer = numOfPlayers;
		this.playerTurn = playerTurn;
		this.tokensCount = tokensCount;
		
		this.startTile = new StartTile();
		this.dedicationTokens = new DedicationTokens(numOfPlayers);
		this.favorTokens = new FavorTokens(tokensCount);
	}
	
	public void start(){
		this.load();
	}
	
	public void update(){
		
	}
	
	public void load(){
		
		startTile.randomizeTheTile();
	}
	
	public void gameloop(){
		
	}
	
	public void unload(){
		
	}
}
