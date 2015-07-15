package unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.FavorTokens;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;
public class PlayerTest {

	Player player;
	
	@Before 
	public void testPlayer(){
		player=new Player("DemoPlayer");
	}
	
	@Test
	public void testPickLanternCard() {
		LanternCards lanternCardsAvailable=new LanternCards(4, 8, 7, 6, 5, 4, 3, 2);
		assertTrue(player.pickLanternCard("redCard", lanternCardsAvailable));
	}

		
	@Test
	public void testPickFavorTokens(){
		FavorTokens favorToken=new FavorTokens(20);
		player.pickFavorTokens(favorToken);
		assertEquals(19, favorToken.getTokens());
	}
	
	@Test 
	public void testSetAndGetLakeTiles(){
		ArrayList<LakeTiles> playerLTStack;
		LakeTiles l=new LakeTiles();
		player.setLakeTiles(l);
		playerLTStack=player.getLakeTiles();
		assertEquals(1,playerLTStack.size());
		
	}
	

	@Test
	public void testPlaceLakeTile(){
		//Q: What is index value
		//assertNotNull(player.placeLakeTile(37));
	}

	@Test
	public void testSpendFavorTokens(){
		FavorTokens favorToken=new FavorTokens(10);
		LanternCards lanternCardsAvailable=new LanternCards(4, 5, 5, 6, 5, 4, 5, 2);
		player.favorTokenScore=10;
		assertTrue(player.spendFavorTokens(favorToken, lanternCardsAvailable, "redCard", "blackCard"));
	}

	@Test
	public void testReturnLanternCards(){
		LanternCards lanternCardsAvailable=new LanternCards(4, 5, 7, 6, 5, 4, 5, 2);
		assertTrue(player.returnLanternCards("redCard", "blackCard", lanternCardsAvailable));
		System.out.println(lanternCardsAvailable.redCardCount());
		System.out.println(lanternCardsAvailable.blackCardCount());
	}

	@Test
	public void testCheckFavorTokens(){
		player.setFavorToken(20);
		assertTrue(player.getFavorToken()==20);
	}

	

}
