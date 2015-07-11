package eclipse_project;
import java.util.Stack;

public class FavorTokens {
	/**
	 * Stack of favor tokens
	 */
	Stack<String> tokens = new Stack<String>();
	
	public FavorTokens() {
	
	}
	
	/**
	 * Initialize the favor tokens for the start of the game
	 */
	public void initialiseFavorTokens()
	{
		for(int i=0; i<20; i++)
		{
			tokens.push("favor_token");
		}
	}
	
	/**
	 */
	public Stack<String> getTokensStack()
	{
		return this.tokens;
	}

}
