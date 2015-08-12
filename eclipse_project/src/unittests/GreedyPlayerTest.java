package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.FavorTokens;
import eclipse_project.GameEngine;
import eclipse_project.GreedyPlayer;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;

public class GreedyPlayerTest {

	GreedyPlayer gPlayer;
	@Before
	public void testGreedyPlayer(){
		gPlayer=new GreedyPlayer("Player1");
	}
	@Test
	public void testExchangeLanternCardforSevenUnique() {
		
		LanternCards lanternCards=new LanternCards(2, 0, 1, 1, 2, 2, 3, 2);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		
		gameEngine.lanternCards=new LanternCards(2,1, 1, 1, 2, 2, 3, 2);
		boolean result= gPlayer.checkSevenUnique(player, gameEngine);
		assertTrue(result);
		assertEquals(1,player.getLanternCards().redCardCount());
		assertEquals(2,player.getLanternCards().blackCardCount());
		
	}
	
	@Test
	public void testExchangeLanternCardForFourOfKind(){
		LanternCards lanternCards=new LanternCards(2, 3, 3, 1, 2, 2, 3, 2);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		
		gameEngine.lanternCards=new LanternCards(2,1, 1, 1, 2, 2, 3, 2);
		boolean result= gPlayer.checkFourOfKind(player, gameEngine);
		
		assertTrue(result);
		assertEquals(3,player.getLanternCards().redCardCount());
		assertEquals(1,player.getLanternCards().whiteCardCount());
		assertTrue(player.getLanternCards().whiteCardCount()>0);
		assertTrue(player.getLanternCards().whiteCardCount()<3);
		
	}

}
