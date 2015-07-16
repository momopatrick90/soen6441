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
	}

	@Test
	public void testCheckFavorTokens(){
		player.setFavorToken(20);
		assertTrue(player.getFavorToken()==20);
	}

	@Test
	public void testAssignBoardPosition(){
		ArrayList<Player> playersList=new ArrayList<>();
		Player p1=new Player("Player1");
		p1.boardPosition="left";
		Player p2=new Player("Player2");
		p2.boardPosition="up";
		Player p3=new Player("Player3");
		p3.boardPosition="right";
		Player p4=new Player("Player4");
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
		
		//lTiles.globalLakeTiles.push(lTiles.lakeTiles[0]);
		String playersL=player.turnToStartGame(4, playersList, lTiles);
		assertEquals("Player4", playersL);
		
		
	}

}
