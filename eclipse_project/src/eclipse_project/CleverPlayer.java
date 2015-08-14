package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements multiple strategies, in round robbing manner
 *
 */
public class CleverPlayer implements PlayerStrategy {
	ArrayList<PlayerStrategy> strategies = null;
	int currentStrategy = 0;
	
	public CleverPlayer(String name) {
		//
		strategies = new ArrayList<PlayerStrategy>();
		//
		strategies.add(new RandomPlayer(name));
		strategies.add(new UnfriendlyPlayer(name));
		strategies.add(new GreedyPlayer(name));
		//
		currentStrategy = 0;
	}


	/**
	 * @see eclipse_project.PlayerStrategy#play(eclipse_project.GameEngine, eclipse_project.Player)
	 */
	@Override
	public void play(GameEngine gameEngine, Player player) {
		// Play as one of the strategies
		System.out.println("clever_player: playing as: "+strategies.get(currentStrategy).getClass().getName());
		strategies.get(currentStrategy).play(gameEngine, player);
		
		// move to next strategy
		currentStrategy += 1;
		if(currentStrategy>=this.strategies.size())
		{
			currentStrategy = 0;
		}
	}


}
