package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import eclipse_project.Board;
import eclipse_project.LakeTiles;
public class BoardTest {

	Board board;
	@Before
	public void boardTestClass(){
		board=new Board();
	}
	@Test
	public void testIntializeGameBoard() {
		LakeTiles l=new LakeTiles();
		board.intializeGameBoard(l);
		assertNotNull(l);		
	}
}
