package eclipse_project;

/**
 * This class is a concrete implementation of the ending strategy with option of all player ending with N lake tiles 
 *
 */
public class AllPlayerEnd implements GameEndStrategy {

	/**
	 * Constructor for AllPlayerEnd class
	 */
	public AllPlayerEnd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Game is ended when total laketiles placed are N lake tiles
	 */
	public boolean gameIsOver(GameEngine gameEngine) {

		return !(gameEngine.getRound() > 0);

	}

}
