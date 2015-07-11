package eclipse_project;
import java.util.Stack;

/**
 *
 */
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
			putToken();
		}
	}
	
	/**
	 * Adds a token to the stack of tokens
	 */
	public void putToken()
	{
		this.tokens.push("favor_token");
	}
	
	/**
	 * @return null if there are no available tokens, else returns an instance of FavorTokens
	 */
	public String getToken()
	{
		if (this.tokens.isEmpty())
			return null;
		return this.tokens.pop();
	}

}
