package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import eclipse_project.DedicationTokens;

public class DedicationTokensTest {

	@Test
	public void testFourOfKindWhenCardsAreAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
			
		assertTrue(dedicationTokens.getFourOfKind() > 0);
	}
	
	@Test
	public void testFourOfKindWhenCardsAreNotAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
			
		for(int i=0;i<100;i++)
			dedicationTokens.getFourOfKind();
			
		assertFalse(dedicationTokens.getFourOfKind() > 0);
	}
	
	@Test
	public void testThreePairWhenCardsAreNotAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		for(int i=0;i<100;i++)
			dedicationTokens.getThreePair();
			
		assertFalse(dedicationTokens.getThreePair() > 0);
	}
	
	@Test
	public void testThreePairWhenCardsAreAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		assertTrue(dedicationTokens.getThreePair() > 0);
	}

	@Test
	public void testFourOfKindCount() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		int tokenExpected=dedicationTokens.fourOfKindCount();
		assertEquals(9, tokenExpected);
	
	}
	
	@Test
	public void testThreePairCount() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		int tokenExpected=dedicationTokens.threePairCount();
		assertEquals(9, tokenExpected);
	
	}
	
	@Test
	public void testSevenOfUniqueWhenCardsAreAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
			
		assertTrue(dedicationTokens.getSevenUnique() > 0);
	}
	
	@Test
	public void testSevenOfUniqueWhenCardsAreNotAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
			
		for(int i=0;i<100;i++)
			dedicationTokens.getSevenUnique();
			
		assertFalse(dedicationTokens.getSevenUnique() > 0);
	}
	
	@Test
	public void testSevenUniqueCount() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		int tokenExpected=dedicationTokens.getSevenUnique();
		assertEquals(10, tokenExpected);
	
	}	
	@Test
	public void testGenericFourCount() {
		
		DedicationTokens dedicationTokens = new DedicationTokens(4);
		
		int tokenExpected=dedicationTokens.genericFourCount();
		assertEquals(3, tokenExpected);
	
	}	
}
