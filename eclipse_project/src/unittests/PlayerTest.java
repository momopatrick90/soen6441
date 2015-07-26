package unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	public void testSpendFavorTokens(){
		// 
		FavorTokens favorToken=new FavorTokens(5);
		LanternCards lanternCardsAvailable=new LanternCards(4, 5, 5, 6, 5, 4, 5, 1);
		//
		player.favorTokenScore=10;
		player.playerLCStack = new LanternCards(4, 1, 5, 6, 5, 4, 5, 2); 

		
		// 
		boolean result = player.spendFavorTokens(favorToken, lanternCardsAvailable, "redCard", "orangeCard");
		
		
		//
		assertTrue(result);
		// 
		assertEquals(player.favorTokenScore, 8);
		assertEquals(player.playerLCStack.redCardCount(), 0);
		assertEquals(player.playerLCStack.orangeCardCount(), 3);
		//
		assertEquals(favorToken.tokensCount(), 7);
		assertEquals(lanternCardsAvailable.redCardCount(), 6);
		assertEquals(lanternCardsAvailable.orangeCardCount(), 0);
	}
	
	@Test
	public void testSpendFavorTokensNotEnoughCards(){
		// 
		FavorTokens favorToken=new FavorTokens(5);
		LanternCards lanternCardsAvailable=new LanternCards(4, 5, 5, 6, 5, 4, 0, 1);
		//
		player.favorTokenScore=10;
		player.playerLCStack = new LanternCards(4, 0, 5, 6, 5, 4, 5, 2); 

		//  not enough red cards
		assertFalse(player.spendFavorTokens(favorToken, lanternCardsAvailable, "redCard", "orangeCard"));
		//  not enough black cards
		assertFalse(player.spendFavorTokens(favorToken, lanternCardsAvailable, "blueCard", "blackCard"));
	}

	@Test
	public void testSpendFavorTokensNotEnoughToken(){
		// 
		FavorTokens favorToken=new FavorTokens(5);
		LanternCards lanternCardsAvailable=new LanternCards(4, 5, 5, 6, 5, 4, 2, 1);
		//
		player.favorTokenScore=1;
		player.playerLCStack = new LanternCards(4, 2, 5, 6, 5, 4, 5, 2); 

		//  not enough tokens
		assertFalse(player.spendFavorTokens(favorToken, lanternCardsAvailable, "redCard", "orangeCard"));
	}

	@Test
	public void testCheckFavorTokens(){
		player.setFavorToken(20);
		assertTrue(player.getFavorToken()==20);
	}

	@Test
	public void testAssignBoardPosition(){
		ArrayList<Player> playersList=new ArrayList<>();
		
		Player p1=new Player("player1");
		Player p2=new Player("player2");
		Player p3=new Player("player3");
		Player p4=new Player("player4");
		
		p1.boardPosition="left";
		p2.boardPosition="up";
		p3.boardPosition="right";
		p4.boardPosition="down";
		
		playersList.add(p1);
		playersList.add(p2);
		playersList.add(p3);
		playersList.add(p4);
		
		LakeTiles lTiles=new LakeTiles(); 
		
		lTiles.downColor="red";
		lTiles.leftColor="blue";
		lTiles.upColor="white";
		lTiles.rightColor="green";
		lTiles.left=1;
		lTiles.right=2;
		lTiles.down=3;
		lTiles.up=4;
		lTiles.id=1;
		lTiles.platform=false;
		//For four players
		playersList=player.assignBoardPosition(4, playersList, lTiles);
		assertNotNull(playersList);
		assertTrue(playersList.get(0).boardPosition=="left");
		assertTrue(playersList.get(1).boardPosition=="up");
		assertTrue(playersList.get(2).boardPosition=="right");
		assertTrue(playersList.get(3).boardPosition=="down");
		
	}
	
	public void testPickDedicationTokenThreePair()
	{
		
	}
	
	public void testPickDedicationTokenThreePairFail()
	{
		
	}
	
	public void testPickDedicationTokenFourOfKind()
	{
		
	}
	
	public void testPickDedicationTokenFourOfKindFail()
	{
		
	}
	
	public void testPickDedicationTokenSevenUnique()
	{
		
	}
	
	public void testPickDedicationTokenSevenUniqueFail()
	{
		
	}
}