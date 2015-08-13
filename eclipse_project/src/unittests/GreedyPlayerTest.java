package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.DedicationTokens;
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
	public void testExchangeLanternCardforSevenUniqueForGreedyPlayer() {
		
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
	public void testExchangeLanternCardForFourOfKindForGreedyPlayer(){
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

	@Test
	public void testEnchangeLanternCardForThreePairForGreedyPlayer(){
		LanternCards lanternCards=new LanternCards(2, 1, 3, 1, 2, 2, 3, 1);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		
		gameEngine.lanternCards=new LanternCards(2,1, 3, 1, 2, 2, 3, 2);
		boolean result= gPlayer.checkThreePair(player, gameEngine);
		
		assertTrue(result);
		assertEquals(2,player.getLanternCards().redCardCount());
		assertEquals(2,player.getLanternCards().blackCardCount());
		assertTrue(player.getLanternCards().blackCardCount()>1);
		
	}
	
	@Test
	public void testFourOfKindPossibleAndDoesIfPossibleForGreedyPlayer(){
		LanternCards lanternCards=new LanternCards(2, 4, 3, 1, 2, 2, 3, 2);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		gameEngine.lanternCards=new LanternCards(2,1, 3, 1, 2, 2, 2, 2);
		gameEngine.dedicationTokens=new DedicationTokens(2, 2, 4, 7, 4);
		boolean result= gPlayer.checkFourOfKindDedication(player, gameEngine, true);
		int simfourOfKind=gameEngine.dedicationTokens.peekFourOfKind();
		
		assertTrue(result);
		assertEquals(2, simfourOfKind);
		assertFalse(player.playerScore_fourKind==0);
		assertEquals(5, gameEngine.lanternCards.redCardCount());
		assertEquals(4, player.getLanternCards().redCardCount());
		assertEquals(3, gameEngine.lanternCards.blueCardCount());
		assertEquals(1, gameEngine.lanternCards.greenCardCount());
		assertEquals(2, gameEngine.lanternCards.whiteCardCount());
		assertEquals(2, gameEngine.lanternCards.purpleCardCount());
		assertEquals(2, gameEngine.lanternCards.blackCardCount());
		assertEquals(2, gameEngine.lanternCards.orangeCardCount());
		
		
	}
	
	@Test
	public void testThreePairDedeicationIsPossibleAndDoesIfPossibleForGreedyPlayer(){
		LanternCards lanternCards=new LanternCards(2, 2, 3, 1, 1, 1, 3, 1);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		gameEngine.lanternCards=new LanternCards(2,1, 3, 1, 2, 2, 3, 2);
		gameEngine.dedicationTokens=new DedicationTokens(2, 2, 4, 7, 4);
		boolean result= gPlayer.checkThreePairDedication(player, gameEngine, false);
		
		assertTrue(result);
		assertEquals(3, gameEngine.lanternCards.redCardCount());
		assertEquals(0, player.getLanternCards().redCardCount());
		assertEquals(5, gameEngine.lanternCards.blueCardCount());
		assertEquals(1, gameEngine.lanternCards.greenCardCount());
		assertEquals(2, gameEngine.lanternCards.whiteCardCount());
		assertEquals(2, gameEngine.lanternCards.purpleCardCount());
		assertEquals(5, gameEngine.lanternCards.blackCardCount());
		assertEquals(2, gameEngine.lanternCards.orangeCardCount());
	}	
}
