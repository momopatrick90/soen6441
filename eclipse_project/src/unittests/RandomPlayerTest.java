package unittests;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import eclipse_project.GameEngine;
import eclipse_project.LakeTiles;
import eclipse_project.RandomPlayer;

public class RandomPlayerTest {

	@Test
	public void testRandomNumberGenerator() {
		RandomPlayer rp=new RandomPlayer("abc");
		int randomNumber=rp.RandomNumberGenerator(2, 5);
		assertNotNull(randomNumber);
		assertTrue(randomNumber>=2);
		assertTrue(randomNumber<=5);
	}
	@Test 
	public void test(){
	}

}
