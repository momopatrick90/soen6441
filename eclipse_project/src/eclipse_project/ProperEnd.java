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
	/**This method decides when the game will end 
	 * @param gameEngine contains the entire state of the game
	 */
	public boolean gameIsOver(GameEngine gameEngine) {
		
		return !(gameEngine.getRound() > 0);
		
	}

}
