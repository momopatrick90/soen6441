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
		int found=0;
		String token = null;
		do
		{
			token = favorTokens.getToken();
			if(token != null)
			{
				found++;
			}
			
		}while(token != null);
		assertTrue(found == 20);
	}

}
