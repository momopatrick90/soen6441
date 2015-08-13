package eclipse_project;

/**
 * This class is a concrete implementation of the ending strategy with option of proper ending 
 *
 */
public class ProperEnd implements GameEndStrategy {

	public ProperEnd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean gameIsOver(GameEngine gameEngine) {
		
		return !(gameEngine.getRound() > 0);
		
	}

}
