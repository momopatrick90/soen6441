package eclipse_project;

import java.util.ArrayList;

public class Player {

	private String name;
	private LanternCards playerLCStack;
	private int favorTokenScore;
	private ArrayList<LakeTiles> playerLTStack;

	private int playerScore_fourKind;
	private int playerScore_threePair;
	private int playerScore_sevenUnique;

	public Player(String name) {

		this.name = name;
		this.playerLCStack = new LanternCards();
		this.playerLTStack = new ArrayList<LakeTiles>();

	}
	
	public Player(String name, LanternCards lanternCards, LakeTiles lakeTiles, int favorTokenScore, int playerScore_fourKind, int playerScore_threePair, int playerScore_sevenUnique) {

		this.name = name;
		this.favorTokenScore = favorTokenScore;
		this.playerScore_fourKind = playerScore_fourKind;
		this.playerScore_threePair = playerScore_threePair;
		this.playerScore_sevenUnique = playerScore_sevenUnique;
		this.playerLCStack = lanternCards;
		this.playerLTStack = null;

	}

	/**
	 * This method will be called once the user has placed a lake tile
	 * 
	 * @param card
	 *            contains what color of the lantern card the user will be
	 *            adding to his stack.
	 */
	public void pickCard(String card, LanternCards lanternCardsAvailable) {
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
	 * This method will be called when the user is exchanging lantern cards for
	 * a dedication token
	 * 
	 * @param move
	 *            The move that has been made by the player(three pair, four of
	 *            a king, seven unique)
	 * @param dedicationToken
	 *            The object containing the current stack of dedication tokens
	 *            on the table
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
	 *            - The object containing the current stack of favor tokens
	 *            available.
	 */
	public void pickFavorTokens(FavorTokens favorToken) {
		favorToken.decrementToken();
		this.favorTokenScore++;

	}

	/**
	 * This method will be called to store lake tiles of the player
	 * 
	 * @param playerLT
	 */

	public void setLakeTiles(LakeTiles playerLT) {
		this.playerLTStack.add(playerLT);
	}

	/**
	 * This method will be called when the player places his/her lake tile on
	 * the board
	 * 
	 * @param index
	 *            - identifies which lake tile the user is placing on the board.
	 * @return return the lake tile the player wants to place on the board.
	 */
	public LakeTiles placeLakeTile(int index) {
		LakeTiles placeLakeT = this.playerLTStack.get(index);
		this.playerLTStack.remove(index);
		return placeLakeT;
	}

	/**
	 * This method will be called when the user spend token to exchange the
	 * lantern card
	 * 
	 * @param favorToken
	 *            The object containing the current stack of favor tokens on the
	 *            table
	 */
	public void spendFavorTokens(FavorTokens favorToken) {
		if ((this.favorTokenScore > 0) && (this.favorTokenScore % 2 == 0)) {
			favorToken.incrementToken();
			favorToken.incrementToken();
			this.favorTokenScore -= 2;
		}

	}

}