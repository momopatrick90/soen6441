package eclipse_project;

public class Player {

	public String name;
	public LanternCards playerLCStack;
	public int playerScore;
	public int favorTokenScore;
	public FavorTokens playerFTStack;
	public LakeTiles playerLTStack;

	public Player(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;

	}
	

	/**
	 * This method will be called once the user has placed a lake tile
	 * @param card
	 *  contains what color of the lantern card the user will be adding to his stack.
	 */
	public void pickCard(String card) {
		if (card.equals("redCard")) {

			playerLCStack.addRedcard();

		} else if (card.equals("blueCard")) {

			playerLCStack.addBluecard();

		} else if (card.equals("greenCard")) {

			playerLCStack.addGreencard();

		} else if (card.equals("whiteCard")) {

			playerLCStack.addWhitecard();

		} else if (card.equals("purpleCard")) {

			playerLCStack.addPurplecard();

		} else if (card.equals("blackCard")) {

			playerLCStack.addBlackcard();

		} else {
			playerLCStack.addOrangecard();
		}
	}

	/**
	 * This method will be called when the user is exchanging lattern cards 
	 * for a dedication token
	 * 
	 * @param move 
	 * The move that has been made by the player(three pair, 
	 * four of a king, seven unique)
	 * @param dedicationToken 
	 * The object containing the current stack of dedication tokens on the table
	 */
	public void pickDedicationToken(String move,
			DedicationTokens dedicationToken) {

		if (move.equals("threePair")) {
			
			playerScore += dedicationToken.getThreePair();
			
		} else if (move.equals("FourOfKind")) {
			
			playerScore += dedicationToken.getFourOfKind();
			
		} else if (move.equals("sevenUnique")) {
			
			playerScore += dedicationToken.getSevenUnique();
		}

	}

	
	/**
	 * This method will be called when the lake tile contains a platform in it  
	 * 
	 * @param favorToken 
	 * The object containing the current stack of favor tokens on the table
	 */
	public void pickFavorTokens(FavorTokens favorToken){
		favorToken.decrementToken();
		favorTokenScore++;
	
	}
	
	
	
}
