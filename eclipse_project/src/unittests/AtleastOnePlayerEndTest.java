package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.AtleastOnePlayerEnd;
import eclipse_project.DedicationTokens;
import eclipse_project.FavorTokens;
import eclipse_project.GameEngine;
import eclipse_project.LanternCards;
import eclipse_project.ProperEnd;

public class AtleastOnePlayerEndTest {

	AtleastOnePlayerEnd gPlayer;
	@Before
	public void testProperEnd(){
		gPlayer=new AtleastOnePlayerEnd();
	}
	@Test
	public void testGameIsOver() {
		GameEngine gameEngine=new GameEngine(2);
		gameEngine.favorTokens=new FavorTokens(5);
		gameEngine.lanternCards=new LanternCards(2,1, 3, 1, 2, 2, 3, 2);
		gameEngine.dedicationTokens=new DedicationTokens(2, 2, 4, 7, 4);
		boolean result= gPlayer.gameIsOver(gameEngine);
		assertTrue(result);
	}

}
