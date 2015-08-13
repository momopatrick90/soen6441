package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.FavorTokens;
import eclipse_project.GameEngine;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;
import eclipse_project.RandomPlayer;

public class RandomPlayerTest {
	
	RandomPlayer gPlayer;
	
	@Before
	public void testRandonPlayer(){
		gPlayer=new RandomPlayer("Player1");
	}
	@Test
	public void testRandomNumberGenerator() {
		
		int randomNumber=gPlayer.RandomNumberGenerator(2, 5);
		assertNotNull(randomNumber);
		assertTrue(randomNumber>=2);
		assertTrue(randomNumber<=5);
	}
	
	@Test
	public void testFourOFKindDedeicationIsPossibleAndDoesIfPossibleForRandomPlayer(){
		LanternCards lanternCards=new LanternCards(2, 4, 3, 1, 1, 1, 3, 1);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		
		boolean result= gPlayer.checkFourOfKindDedication(player, gameEngine);
		assertTrue(result);
	}	
	
	@Test
	public void testThreePlayerDedeicationIsPossibleAndDoesIfPossibleForRandomPlayer(){
		LanternCards lanternCards=new LanternCards(2, 2, 3, 1, 1, 1, 3, 1);
		ArrayList<LakeTiles> playerLTStack=new ArrayList<LakeTiles>();
		int favorTokenScore=10;
		Player player=new Player("Player1", "Player1", lanternCards , playerLTStack, favorTokenScore, 2, 2, 2);
		
		GameEngine gameEngine=new GameEngine(2);
		
		boolean result= gPlayer.checkThreePairDedication(player, gameEngine);
		assertTrue(result);
	}

}
