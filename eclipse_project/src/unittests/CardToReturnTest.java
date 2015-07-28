package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import eclipse_project.CardToReturn;
import eclipse_project.LanternCards;

public class CardToReturnTest {

	@Test
	public void testReturnSeveUnique() {
		CardToReturn card=new CardToReturn();
		LanternCards lc=card.returnSeveUnique();
		assertTrue(lc.getCard("blackCard"));
		assertTrue(lc.getCard("blueCard"));
		assertTrue(lc.getCard("greenCard"));
		assertTrue(lc.getCard("orangeCard"));
		assertTrue(lc.getCard("redCard"));
		assertTrue(lc.getCard("purpleCard"));
		assertTrue(lc.getCard("whiteCard"));
	}
	
	@Test
	public void testReturnStackThreeOfKind(){
		CardToReturn card=new CardToReturn(1,2,7);
		LanternCards lc=card.returnStackThreeOfKind();
		assertTrue(lc.getCard("redCard"));
		assertTrue(lc.getCard("blueCard"));
		assertTrue(lc.getCard("orangeCard"));		
	}
	
	@Test
	public void testReturnStacFourOfKind(){
		CardToReturn card=new CardToReturn(1);
		LanternCards lc=card.returnStackFourOfKind();
		assertTrue(lc.getCard("redCard"));	
	}
	
	
}