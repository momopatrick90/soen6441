package unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import eclipse_project.DedicationTokens;
import eclipse_project.GameEngine;
import eclipse_project.LakeTiles;
import eclipse_project.LanternCards;

public class GameEngineTest {

	GameEngine gEngine;
	ArrayList<LakeTiles> lTiles;
	LakeTiles l;
	
	@Test
	public void testGameIsProperlyStarted() {
		gEngine = new GameEngine(4);
		gEngine.startNewGame();
		l=new LakeTiles();
		l.initializeLakeTiles(4);
		lTiles =l.assignLakeTiles(4);
		assertNotNull(lTiles);
			
	}
	
	@Test 
	public void lakeTilesShouldBeTwelveForFourPlayers(){
		gEngine = new GameEngine(4);
		gEngine.startNewGame();
		l=new LakeTiles();
		l.initializeLakeTiles(4);
		lTiles =l.assignLakeTiles(4);
		
		assertEquals(12, lTiles.size());
	}
	
	@Test 
	public void lakeTilesShouldBeNineForThreePlayers(){
		gEngine = new GameEngine(3);
		gEngine.startNewGame();
		l=new LakeTiles();
		l.initializeLakeTiles(3);
		lTiles =l.assignLakeTiles(3);
		assertEquals(9, lTiles.size());
	}
	
	@Test 
	public void lakeTilesShouldBeSixForTwoPlayers(){
		gEngine = new GameEngine(2);
		gEngine.startNewGame();
		l=new LakeTiles();
		l.initializeLakeTiles(2);
		lTiles =l.assignLakeTiles(2);
		assertEquals(6, lTiles.size());
	}
	
	
	
}
