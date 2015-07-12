package unittests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import eclipse_project.LakeTiles;

public class LakeTileTest {

	LakeTiles lT;
	@Before
	public void lakeTileTestClass(){
		lT=new LakeTiles();
	}
	@Test
	public void lakeTilesForTwoPlayersShouldBeTweentyThree() {
		lT.initializeLakeTiles(2);
		assertEquals(23,lT.lakeTiles.length);
	}
	@Test
	public void lakeTilesForThreePlayersShouldBeTweentyEight() {
		lT.initializeLakeTiles(3);
		assertEquals(28,lT.lakeTiles.length);
	}
	@Test
	public void lakeTilesForFourPlayersShouldBeThirtyThree() {
		lT.initializeLakeTiles(4);
		assertEquals(33,lT.lakeTiles.length);
	}
	@Test
	public void testThreeTilesAreAssignedForEachPlayer(){
		
	}
	
	@Test
	public void testPlaceTile(){
		//assertFalse(lT.placeTile(26, 30, gameBoard, tileToPlace));
	}
	
	@Test
	public void testRandomValuesAreBetweenFiveAndEleven(){
		
		for(int i=0;i<8;i++){
			
		}
	}
	
	
}
