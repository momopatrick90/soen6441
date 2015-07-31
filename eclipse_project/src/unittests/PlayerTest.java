package unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.FavorTokens;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;
import eclipse_project.Player;
public class PlayerTest {

	Player player;
	ArrayList<Player> playersList;
	LakeTiles lTiles;
	@Before 
	public void testPlayer(){
		player=new Player("DemoPlayer");
	}
	
	@Test
	public void testPickLanternCard() {
		LanternCards lanternCardsAvailable=new LanternCards(4, 3, 7, 6, 5, 4, 3, 2);
		boolean result=player.pickLanternCard("redCard", lanternCardsAvailable);
		assertTrue(result);
		assertEquals(lanternCardsAvailable.redCardCount(), 2);
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
		lTiles=new LakeTiles();
		player.setLakeTiles(lTiles);
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

	@Before
	public void testAssignBoardPositionInitialization(){
		playersList=new ArrayList<Player>();
		
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
		
		lTiles=new LakeTiles(); 
		
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

	}
	@Test
	public void testForFourPlayersAssignBoardPosition(){

		//For four players
		playersList=player.assignBoardPosition(4, playersList, lTiles);
		assertNotNull(playersList);
		assertTrue(playersList.get(0).boardPosition=="left");
		assertTrue(playersList.get(1).boardPosition=="up");
		assertTrue(playersList.get(2).boardPosition=="right");
		assertTrue(playersList.get(3).boardPosition=="down");
		
	}

	@Test
	public void testForTwoPlayersAssignBoardPosition(){

		//For four players
		playersList=player.assignBoardPosition(2, playersList, lTiles);
		assertNotNull(playersList);
		assertTrue(playersList.get(0).boardPosition=="left");
		assertTrue(playersList.get(1).boardPosition=="right");
		
	}
	
	@Test
	public void testReturnLanternCards(){
		LanternCards lanternCardsAvailable=new LanternCards(4, 1, 7, 6, 5, 4, 3, 2);
		boolean result=player.returnLanternCards("redCard" , "orangeCard", lanternCardsAvailable);
		assertTrue(result);
		assertEquals(lanternCardsAvailable.redCardCount(),2);
		assertEquals(lanternCardsAvailable.orangeCardCount(),1);
	}

	@Test
	public void testPickDedicationTokenThreePair()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		LanternCards returnedLanternCards = new LanternCards(0, 2, 0, 2, 0, 2, 0, 0);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		//
		boolean result = p1.pickDedicationToken("threePair", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertTrue(result);
		assertFalse(p1.playerScore_threePair == 0);
		assertTrue(globalLanternCards.redCardCount() == 3);
		assertTrue(globalLanternCards.greenCardCount() == 3);
		assertTrue(globalLanternCards.purpleCardCount() == 3);
	}
	
	@Test
	public void testPickDedicationTokenThreePairFail()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		// not enough lanterns
		LanternCards returnedLanternCards = new LanternCards(0, 1, 0, 2, 0, 2, 0, 0);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		// 
		boolean result = p1.pickDedicationToken("threePair", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertFalse(result);
		
		//to many latterns
		returnedLanternCards = new LanternCards(0, 2, 0, 2, 0, 2, 0, 1);
		
		//
		result = p1.pickDedicationToken("threePair", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertFalse(result);
	}
	
	@Test
	public void testPickDedicationTokenFourOfKind()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		LanternCards returnedLanternCards = new LanternCards(0, 4, 0, 0, 0, 0, 0, 0);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		//
		boolean result = p1.pickDedicationToken("FourOfKind", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertTrue(result);
		assertFalse(p1.playerScore_fourKind == 0);
		assertTrue(globalLanternCards.redCardCount() == 5);
	}
	
	@Test
	public void testPickDedicationTokenFourOfKindFail()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		// not enough lantern
		LanternCards returnedLanternCards = new LanternCards(0, 0, 3, 0, 0, 0, 0, 0);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		// 
		boolean result = p1.pickDedicationToken("FourOfKind", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertFalse(result);
		
		//to many latterns
		returnedLanternCards = new LanternCards(0, 4, 0, 0, 0, 0, 0, 1);
		
		//
		result = p1.pickDedicationToken("FourOfKind", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertFalse(result);
	}
	
	@Test
	public void testPickDedicationTokenSevenUnique()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		LanternCards returnedLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		//
		boolean result = p1.pickDedicationToken("sevenUnique", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertTrue(result);
		assertFalse(p1.playerScore_sevenUnique == 0);
		assertTrue(globalLanternCards.redCardCount() == 2);
		assertTrue(globalLanternCards.greenCardCount() == 2);
		assertTrue(globalLanternCards.blackCardCount() == 2);
		assertTrue(globalLanternCards.orangeCardCount() == 2);
		assertTrue(globalLanternCards.blueCardCount() == 2);
		assertTrue(globalLanternCards.purpleCardCount() == 2);
		assertTrue(globalLanternCards.whiteCardCount() == 2);
	}
	
	@Test
	public void testPickDedicationTokenSevenUniqueFail()
	{
		//
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		// not enough lantern
		LanternCards returnedLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		LanternCards globalLanternCards = new LanternCards(0, 1, 1, 1, 1, 1, 1, 1);
		Player p1=new Player("player1");
		
		// 
		boolean result = p1.pickDedicationToken("FourOfKind", returnedLanternCards, globalLanternCards, dedicationTokens);
		
		//
		assertFalse(result);
	}
}