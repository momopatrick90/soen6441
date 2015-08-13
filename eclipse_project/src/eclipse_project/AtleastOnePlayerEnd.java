package eclipse_project;

/**
 * This class is a concrete implementation of the ending strategy with option of enough dedication ending
 *
 */
public class AtleastOnePlayerEnd implements GameEndStrategy {

	public AtleastOnePlayerEnd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean gameIsOver(GameEngine gameEngine) {

		int N_INPUT = gameEngine.userInput_N, count = 0, honorPoints = 0;

		for (int playerIndex = 0; playerIndex < gameEngine.PlayerList.size(); playerIndex++) {

			Player player = gameEngine.PlayerList.get(playerIndex);

			honorPoints = player.playerScore_fourKind
					+ player.playerScore_sevenUnique
					+ player.playerScore_threePair;

			if (honorPoints >= N_INPUT) {
				count++;
				break;
			}
		}

		return (count > 0 || !(gameEngine.getRound() > 0));
	}

}
