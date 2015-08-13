package unittests;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.FavorTokens;
import eclipse_project.GameEngine;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;
import eclipse_project.UnfriendlyPlayer;

public class UnfriendlyPlayerTest {

	UnfriendlyPlayer uPlayer;
	@Before
	public void testUnfriendlyPlayer(){
		uPlayer=new UnfriendlyPlayer("Player1");
	}
	@Test
	public void testDedicationGain() {
		
		LanternCards lanternCards=new LanternCards(2, 4, 3, 3, 3, 2, 3, 2);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		int result;
		Player player=new Player("P1", "P1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		gameEngine.dedicationTokens=new DedicationTokens(2);
		
		result=  uPlayer.dedicationGain(gameEngine, player, "redCard", "whiteCard");
		assertTrue(result==0);
		
	}
	
}
