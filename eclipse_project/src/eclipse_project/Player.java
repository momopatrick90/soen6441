package eclipse_project;

public class Player {

	private String name;
	private LanternCards playerLCStack;
	private int playerScore_fourKind;
	private int playerScore_threePair;
	private int playerScore_sevenUnique;
	private int favorTokenScore;
	private LakeTiles playerLTStack;

	public Player(String name) {

		this.name = name;
		this.playerLCStack = new LanternCards();
		this.playerLTStack = new LakeTiles(); //Don't know why do we need it.
		
	}	

	/**
	 * This method will be called once the user has placed a lake tile
	 * @param card
	 *  contains what color of the lantern card the user will be adding to his stack.
	 */
	public void pickCard(String card,LanternCards lanternCardsAvailable) {
		if (card.equals("redCard")) {
			lanternCardsAvailable.getRedCard();
			playerLCStack.addRedcard();

		} else if (card.equals("blueCard")) {
			lanternCardsAvailable.getBlueCard();
			playerLCStack.addBluecard();

		} else if (card.equals("greenCard")) {
			lanternCardsAvailable.getGreenCard();
			playerLCStack.addGreencard();

		} else if (card.equals("whiteCard")) {
			lanternCardsAvailable.getWhiteCard();
			playerLCStack.addWhitecard();

		} else if (card.equals("purpleCard")) {
			lanternCardsAvailable.getPurpleCard();
			playerLCStack.addPurplecard();

		} else if (card.equals("blackCard")) {
			lanternCardsAvailable.getBlackCard();
			playerLCStack.addBlackcard();

		} else {
			lanternCardsAvailable.getOrangeCard();
			playerLCStack.addOrangecard();
		}
	}

	/**
	 * This method will be called when the user is exchanging lantern cards 
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
			
			playerScore_threePair += dedicationToken.getThreePair();
			
		} else if (move.equals("FourOfKind")) {
			
			playerScore_fourKind += dedicationToken.getFourOfKind();
			
		} else if (move.equals("sevenUnique")) {
			
			playerScore_sevenUnique += dedicationToken.getSevenUnique();
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
