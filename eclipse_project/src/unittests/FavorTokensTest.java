package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import eclipse_project.FavorTokens;

public class FavorTokensTest {

	@Test
	public void testInitialise() {
		//
		FavorTokens favorTokens = new FavorTokens();
		
		// 
		favorTokens.initialiseFavorTokens();
		
		//
		assertTrue(favorTokens.getTokensStack().capacity() == 20);
	}

}
