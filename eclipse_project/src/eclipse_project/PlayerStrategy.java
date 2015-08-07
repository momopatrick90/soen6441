package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;

public interface PlayerStrategy 
{
	public void play(GameEngine gameEngine, Player player, BufferedReader br) throws NumberFormatException, IOException;
}
