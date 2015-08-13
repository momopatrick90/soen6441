package eclipse_project;

public class GameEndContext {

	private GameEndStrategy strategy;

	   public GameEndContext(GameEndStrategy strategy){
	      this.strategy = strategy;
	   }

	   public boolean executeStrategy(GameEngine gameEngine){
	      return strategy.gameIsOver(gameEngine);
	   }

}
