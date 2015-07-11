package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import eclipse_project.LanternCards;;

public class LatternCardsTest {

	//Black Cards Start
	@Test
	public void testBlackColorCardsWhenAvailableForTwoPlayers() {
		
		LanternCards lc = new LanternCards();
		
		lc.initializeLatternCards(2);
			
		assertTrue(lc.getBlackCard());
	}
	
	@Test
	public void testBlackColorCardsWhenAvailableForThreePlayers() {
		
		LanternCards lc = new LanternCards();
		
		lc.initializeLatternCards(3);
			
		assertTrue(lc.getBlackCard());
	}
	
	@Test
	public void testBlackColorCardsWhenAvailableForFourPlayers() {
		
		LanternCards lc = new LanternCards();
		
		lc.initializeLatternCards(4);
			
		assertTrue(lc.getBlackCard());
	}

	@Test
	public void testBlackColorCardsWhenNotAvailableForThreePlayers() {
		
		LanternCards lc = new LanternCards();
		
		lc.initializeLatternCards(3);
			
		for(int i=0;i<10;i++)
			lc.getBlackCard();
		
		assertFalse(lc.getBlackCard());
	}
	
	@Test
	public void testAddBlackColorCards() {
		
		LanternCards lc = new LanternCards();
		
		lc.initializeLatternCards(3);
		
		for(int i=0;i<10;i++)
			lc.getBlackCard();
		
		lc.addBlackcard("Black Card");
				
		assertTrue(lc.getBlackCard());
	}
}
