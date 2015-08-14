package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;

public interface PlayerStrategy 
{
	/**This method playes one move for player 
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	public void play(GameEngine gameEngine, Player player) ;
}
