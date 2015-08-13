package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AtleastOnePlayerEndTest.class, BoardTest.class,
		CardToReturnTest.class, DedicationTokensTest.class,
		FavorTokensTest.class, GreedyPlayerTest.class, LakeTileTest.class,
		LanternCardsTest.class, PlayerTest.class, ProperEndTest.class,
		RandomPlayerTest.class, UnfriendlyPlayerTest.class })
public class AllTests {

}
