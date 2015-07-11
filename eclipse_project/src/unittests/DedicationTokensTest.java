package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import eclipse_project.DedicationTokens;

public class DedicationTokensTest {

	@Test
	public void testFourOfKindWhenCardsAreAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens();
		
		dedicationTokens.initializeDedicationTokens(4);
			
		assertTrue(dedicationTokens.getFourOfKind() > 0);
	}
	
	@Test
	public void testFourOfKindWhenCardsAreNotAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens();
		
		dedicationTokens.initializeDedicationTokens(4);
			
		for(int i=0;i<100;i++)
			dedicationTokens.getFourOfKind();
			
		assertFalse(dedicationTokens.getFourOfKind() > 0);
	}
	
	@Test
	public void testThreePairWhenCardsAreNotAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens();
		
		dedicationTokens.initializeDedicationTokens(4);
		
		for(int i=0;i<100;i++)
			dedicationTokens.getThreePair();
			
		assertFalse(dedicationTokens.getThreePair() > 0);
	}
	
	@Test
	public void testThreePairWhenCardsAreAvailable() {
		
		DedicationTokens dedicationTokens = new DedicationTokens();
		
		dedicationTokens.initializeDedicationTokens(4);
		
		assertTrue(dedicationTokens.getThreePair() > 0);
	}

}
