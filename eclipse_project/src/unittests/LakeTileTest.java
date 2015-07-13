package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		lT.initializeLakeTiles(3);
		ArrayList<LakeTiles> listTiles =lT.assignLakeTiles(3);
		LakeTiles tile = new LakeTiles();
		assertNotNull(listTiles);
		
		for(int i = 0; i<3 ; i++){
			tile = listTiles.get(i);
			assertNotNull(tile);
			
		}
		
		//System.out.println(lT.globalLakeTiles.size());
	}
	
	@Test
	public void testPlaceTile(){
		lT.initializeLakeTiles(4);
		//lT.placeTile();
		//assertFalse(lT.placeTile(26, 30, gameBoard, tileToPlace));
	}
	
	@Test
	public void testRandomValuesAreBetweenFiveAndEleven(){
		
		for(int i=0;i<8;i++){
			
		}
	}
	
	
}
