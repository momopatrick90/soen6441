package eclipse_project;

import java.util.Stack;

/**
 * Favor Tokens - Tokens can be replaced with Lanterns card during the game
 * play.
 *
 */
public class FavorTokens {

	private final int MAX_TOKENS = 20;
	private Stack<String> tokens = new Stack<String>();

	/**
	 * initializing the favor tokens
	 */
	public FavorTokens(int tokenCount) {

		if (tokenCount < 0 || tokenCount > MAX_TOKENS)
			tokenCount = 0;

		for (int i = 0; i < tokenCount; i++) {
			tokens.push("favor_token");
		}
	}

	/**
	 * Number of favor tokens
	 * 
	 * @return number of favor tokens available
	 */
	public int getTokens() {
		return tokens.size();
	}

	/**
	 * Decrement the favor token
	 */
	public void decrementToken() {
		if (!this.tokens.isEmpty())
			this.tokens.pop();
	}

	/**
	 * Increment the favor token - Not exceeding the MAX Count
	 */
	public void incrementToken() {
		if (this.tokens.size() < MAX_TOKENS)
			this.tokens.push("favor_token");
	}

}
