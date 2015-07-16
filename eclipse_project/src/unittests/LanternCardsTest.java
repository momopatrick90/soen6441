package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.crypto.Data;

import org.junit.Test;

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
	
//	@Test
//	public void testAssignsLanternCardsToEachPlayerAccordingToLakeTileAndPlayersPosition(){
//		LanternCards lc=new LanternCards(4,5, 7, 6, 5, 4, 5, 2);
//		Player p1=new Player("Player1");
//		Player p2=new Player("Player2");
//		Player p3=new Player("Player3");
//		ArrayList<Player> playerList=new ArrayList<>();
//		playerList.add(p1);
//		playerList.add(p2);
//		playerList.add(p3);
//		LakeTiles startTile=new LakeTiles();
//		//LanternCards lanternCards=new LanternCards(4, 5, 7, 6, 5, 4, 5, 2);;
//		lc.assignLanternCards(4, playerList, startTile, lc);
//	}
}
