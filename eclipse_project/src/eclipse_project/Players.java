package eclipse_project;

public class Players {

	public String name;
	public LatternCards playerLCStack;
	public int playerScore;
	public FavorTokens playerFTStack;
	public LakeTiles playerLTStack;

	public Players(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;

	}
	

	/**
	 * This method will be called once the user has placed a lake tile
	 * @param card
	 *  contains what color of the lattern card the user will be adding to his stack.
	 */
	public void pickCard(String card) {
		if (card.equals("redCard")) {

			playerLCStack.addRedcard(card);

		} else if (card.equals("blueCard")) {

			playerLCStack.addBluecard(card);

		} else if (card.equals("greenCard")) {

			playerLCStack.addGreencard(card);

		} else if (card.equals("whiteCard")) {

			playerLCStack.addWhitecard(card);

		} else if (card.equals("purpleCard")) {

			playerLCStack.addPurplecard(card);

		} else if (card.equals("blackCard")) {

			playerLCStack.addBlackcard(card);

		} else {
			playerLCStack.addOrangecard(card);
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

}
