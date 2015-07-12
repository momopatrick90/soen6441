package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import eclipse_project.LakeTiles;

public class LakeTileTest {

	@Test
	public void lakeTilesForTwoPlayersShouldBeSixteen() {
		LakeTiles lakeTiles=new LakeTiles();
		//lakeTiles.initializeLakeTiles(2);
		assertTrue(lakeTiles.lakeTiles.length==16);
		
	}

}
