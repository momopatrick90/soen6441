package eclipse_project;

/**
 * This class is a concrete implementation of the ending strategy with option of all player ending with N lake tiles 
 *
 */
public class AllPlayerEnd implements GameEndStrategy {

	public AllPlayerEnd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean gameIsOver(GameEngine gameEngine) {

		return (gameEngine.getRound() > 0);

	}

}
