package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import eclipse_project.FavorTokens;

public class FavorTokensTest {

	@Test
	public void testFavorTokensOnNewGame() {
		
		FavorTokens favorTokens = new FavorTokens(20);
		
		assertTrue(favorTokens.getTokens()==20);
		
	}
	
	@Test
	public void testFavorTokensOnLoadExistingGame() {
		
		FavorTokens favorTokens = new FavorTokens(10);
		
		assertTrue(favorTokens.getTokens()==10);
		
	}
	
	@Test
	public void testFavorTokensOnWrongValues() {
		
		FavorTokens favorTokens = new FavorTokens(30);
		
		assertFalse(favorTokens.getTokens()==30);
		
	}
	
	@Test
	public void testFavorTokensOnIncrementToken() {
		
		FavorTokens favorTokens = new FavorTokens(19);
		
		favorTokens.incrementToken();
		
		assertTrue(favorTokens.getTokens()==20);
		
	}
	
	@Test
	public void testFavorTokensOnDecrementToken() {
		
		FavorTokens favorTokens = new FavorTokens(20);
		
		favorTokens.decrementToken();
		
		assertTrue(favorTokens.getTokens()==19);
		
	}

}
