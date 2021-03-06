package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.crypto.Data;

import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;

public class LanternCardsTest {

	//Black Cards Start
	@Test
	public void testBlackColorCardsWhenAvailableForTwoPlayers() {
		
		LanternCards lc = new LanternCards(2);
			
		assertTrue(lc.getBlackCard());
	}
	
	@Test
	public void testBlackColorCardsWhenAvailableForThreePlayers() {
		
		LanternCards lc = new LanternCards(3);
			
		assertTrue(lc.getBlackCard());
	}
	
	@Test
	public void testBlackColorCardsWhenAvailableForFourPlayers() {
		
		LanternCards lc = new LanternCards(4);
			
		assertTrue(lc.getBlackCard());
	}

	@Test
	public void testBlackColorCardsWhenNotAvailableForThreePlayers() {
		
		LanternCards lc = new LanternCards(3);
			
		for(int i=0;i<10;i++)
			lc.getBlackCard();
		
		assertFalse(lc.getBlackCard());
	}
	
	@Test
	public void testAddBlackColorCards() {
		
		LanternCards lc = new LanternCards(3);
		
		for(int i=0;i<10;i++)
			lc.getBlackCard();
		
		lc.addBlackcard();
				
		assertTrue(lc.getBlackCard());
	}
	
	@Test
	public void testHasCards() {
		//
		LanternCards lc = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		
		// 
		assertTrue(lc.hasCard("redCard"));
		assertTrue(lc.hasCard("greenCard"));
		assertTrue(lc.hasCard("blueCard"));
		assertTrue(lc.hasCard("whiteCard"));
		assertTrue(lc.hasCard("orangeCard"));
		assertTrue(lc.hasCard("purpleCard"));
		assertTrue(lc.hasCard("blackCard"));
		assertFalse(lc.hasCard("unknownCard"));
	}
	
	@Test
	public void testHasCardsFalse() {
		//
		LanternCards lc = new LanternCards(0, 0, 0, 0, 0, 0, 0, 0);
		
		// 
		assertFalse(lc.hasCard("redCard"));
		assertFalse(lc.hasCard("greenCard"));
		assertFalse(lc.hasCard("blueCard"));
		assertFalse(lc.hasCard("whiteCard"));
		assertFalse(lc.hasCard("orangeCard"));
		assertFalse(lc.hasCard("purpleCard"));
		assertFalse(lc.hasCard("blackCard"));
	}
	
	@Test
	public void testAddCard() {
		//
		LanternCards lc = new LanternCards(0, 0, 0, 0, 0, 0, 0, 0);
		
		// 
		lc.addCard("redCard");
		lc.addCard("greenCard");
		lc.addCard("blueCard");
		lc.addCard("whiteCard");
		lc.addCard("orangeCard");
		lc.addCard("purpleCard");
		lc.addCard("blackCard");
		
		//
		assertTrue(lc.getCard("redCard"));
		assertTrue(lc.getCard("greenCard"));
		assertTrue(lc.getCard("blueCard"));
		assertTrue(lc.getCard("whiteCard"));
		assertTrue(lc.getCard("orangeCard"));
		assertTrue(lc.getCard("purpleCard"));
		assertTrue(lc.getCard("blackCard"));
	}
	
	@Test
	public void testGetCard() {
		//
		LanternCards lc = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		
		// 
		assertTrue(lc.getCard("redCard"));
		assertTrue(lc.getCard("greenCard"));
		assertTrue(lc.getCard("blueCard"));
		assertTrue(lc.getCard("whiteCard"));
		assertTrue(lc.getCard("orangeCard"));
		assertTrue(lc.getCard("purpleCard"));
		assertTrue(lc.getCard("blackCard"));
		assertFalse(lc.hasCard("unknownCard"));
		
		//
		assertFalse(lc.getCard("redCard"));
		assertFalse(lc.getCard("greenCard"));
		assertFalse(lc.getCard("blueCard"));
		assertFalse(lc.getCard("whiteCard"));
		assertFalse(lc.getCard("orangeCard"));
		assertFalse(lc.getCard("purpleCard"));
		assertFalse(lc.getCard("blackCard"));
	}
	
	@Test
	public void testNonZeroColors() {
		//
		LanternCards lc = new LanternCards(0, 1, 1, 0, 1, 0, 1, 1);
		
		// 
		assertTrue(lc.nonZeroColors() == 5);
	}
	
	@Test
	public void testColorsWithQuantity() {
		//
		LanternCards lc = new LanternCards(0, 3, 1, 0, 1, 0, 3, 1);
		
		// 
		assertTrue(lc.numColorsWithQuantity(3) == 2);
	}
	
	@Test
	public void testGetAll() {
		//
		LanternCards lc = new 		 LanternCards(0, 3, 1, 0, 1, 0, 3, 1);
		LanternCards target_lc = new LanternCards(0, 1, 2, 3, 4, 1, 1, 3);
		
		// 
		lc.getAll(target_lc);
		
		//
		assertTrue(lc.redCardCount() == 4);
		assertTrue(lc.greenCardCount() == 3);
		assertTrue(lc.blackCardCount() == 4);
		assertTrue(lc.whiteCardCount() == 5);
		assertTrue(lc.orangeCardCount() == 4);
		assertTrue(lc.purpleCardCount() == 1);
		assertTrue(lc.blackCardCount() == 4);
	}
	
	@Test
	public void testWithdrawAll() {
		//
		LanternCards lc = new 		 LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		LanternCards target_lc = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		
		// 
		lc.withdrawAll(target_lc);
		
		//
		assertTrue(lc.nonZeroColors() == 0);
	}
	
	@Test
	public void testDuplicate() {
		//
		LanternCards lc = new LanternCards(0, 3, 1, 0, 1, 0, 3, 1);

		
		// 
		LanternCards lcCopy =  lc.duplicate();
		
		//
		assertTrue(lcCopy.redCardCount() == 3);
		assertTrue(lcCopy.blueCardCount() == 1);
		assertTrue(lcCopy.greenCardCount() == 0);
		assertTrue(lcCopy.whiteCardCount() == 1);
		assertTrue(lcCopy.purpleCardCount() == 0);
		assertTrue(lcCopy.blackCardCount() == 3);
		assertTrue(lcCopy.orangeCardCount() == 1);
	}
	
	@Test
	public void testGetSevenUnique() {
		//
		LanternCards lc1 = new LanternCards(0, 3, 1, 1, 1, 1, 3, 1);
		LanternCards lc2 = new LanternCards(0, 3, 0, 1, 1, 1, 3, 1);

		
		// 
		LanternCards sevenUniques1 =  lc1.getSevenUniques();
		LanternCards sevenUniques2 =  lc2.getSevenUniques();
		
		//
		assertTrue(sevenUniques1.numColorsWithQuantity(1) == 7);
		//
		assertTrue(sevenUniques2 == null);
	}
	
	@Test
	public void testGetThreePair() {
		//
		LanternCards lc1 = new LanternCards(0, 3, 3, 1, 1, 1, 3, 1);
		LanternCards lc2 = new LanternCards(0, 3, 1, 1, 1, 1, 3, 1);

		
		// 
		LanternCards threePair1 =  lc1.getThreePairs();
		LanternCards threePair2 =  lc2.getThreePairs();
		
		//
		assertTrue(threePair1.numColorsWithQuantity(2) == 3);
		assertTrue(threePair1.redCardCount() == 2);
		assertTrue(threePair1.blueCardCount() == 2);
		assertTrue(threePair1.blackCardCount() == 2);
		//
		assertTrue(threePair2 == null);
	}
	
	@Test
	public void testGetFourOfKinds() {
		//
		LanternCards lc1 = new LanternCards(0, 4, 3, 1, 1, 1, 3, 1);
		LanternCards lc2 = new LanternCards(0, 3, 1, 1, 1, 1, 3, 1);

		
		// 
		LanternCards threePair1 =  lc1.getFourOfKinds();
		LanternCards threePair2 =  lc2.getFourOfKinds();
		
		//
		assertTrue(threePair1.numColorsWithQuantity(4) == 1);
		assertTrue(threePair1.redCardCount() == 4);
		//
		assertTrue(threePair2 == null);
	}
	
	@Test
	public void testPossibleDedications() {
		//
		DedicationTokens dedicationTokens = new DedicationTokens(3);
		
		// all types
		assertTrue(new LanternCards(0, 4, 3, 1, 1, 1, 3, 1).possibleDedicationsCount(dedicationTokens) == 3);
		// 4 kind
		assertTrue(new LanternCards(0, 4, 0, 1, 1, 1, 3, 1).possibleDedicationsCount(dedicationTokens) == 1);
		// seven unique
		assertTrue(new LanternCards(0, 2, 1, 1, 1, 1, 1, 1).possibleDedicationsCount(dedicationTokens) == 1);
		// three pair
		assertTrue(new LanternCards(0, 3, 3, 0, 0, 1, 3, 1).possibleDedicationsCount(dedicationTokens) == 1);
	}
	
}
