package eclipse_project;

import java.util.ArrayList;

public class Player {

	public String name;
	public LanternCards playerLCStack;
	public int favorTokenScore;
	public ArrayList<LakeTiles> playerLTStack;

	public int playerScore_fourKind;
	public int playerScore_threePair;
	public int playerScore_sevenUnique;

	public Player(String name) {

		this.name = name;
		this.playerLCStack = new LanternCards();
		this.playerLTStack = new ArrayList<LakeTiles>();

	}

	public Player(String name, LanternCards lanternCards, ArrayList<LakeTiles> playerLTStack,
			int favorTokenScore, int playerScore_fourKind,
			int playerScore_threePair, int playerScore_sevenUnique) {

		this.name = name;
		this.favorTokenScore = favorTokenScore;
		this.playerScore_fourKind = playerScore_fourKind;
		this.playerScore_threePair = playerScore_threePair;
		this.playerScore_sevenUnique = playerScore_sevenUnique;
		this.playerLCStack = lanternCards;
		this.playerLTStack = playerLTStack;
	}

	/**
	 * This method will be called once the user has placed a lake tile
	 * 
	 * @param card
	 *            contains what color of the lantern card the user will be
	 *            adding to his stack.
	 */
	public boolean pickLanternCard(String card,
			LanternCards lanternCardsAvailable) {
		if (card.equals("redCard")) {
			if (lanternCardsAvailable.getRedCard()) {
				playerLCStack.addRedcard();
				return true;
			} else
				return false;
		} else if (card.equals("blueCard")) {
			if (lanternCardsAvailable.getBlueCard()) {
				playerLCStack.addBluecard();
				return true;
			} else
				return false;
		} else if (card.equals("greenCard")) {
			if (lanternCardsAvailable.getGreenCard()) {
				playerLCStack.addGreencard();
				return true;
			} else
				return false;
		} else if (card.equals("whiteCard")) {
			if (lanternCardsAvailable.getWhiteCard()) {
				playerLCStack.addWhitecard();
				return true;
			} else
				return false;
		} else if (card.equals("purpleCard")) {
			if (lanternCardsAvailable.getPurpleCard()) {
				playerLCStack.addPurplecard();
				return true;
			} else
				return false;
		} else if (card.equals("blackCard")) {
			if (lanternCardsAvailable.getBlackCard()) {
				playerLCStack.addBlackcard();
				return true;
			} else
				return false;
		} else {
			if (lanternCardsAvailable.getOrangeCard()) {
				playerLCStack.addOrangecard();
				return true;
			} else
				return false;
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
	 * This method will be called to store lake tiles of the player return
	 * PlayerLTStack
	 */
	public ArrayList<LakeTiles> getLakeTiles() {
		return this.playerLTStack;
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
	 * @param lanternCards
	 *            The object contains the current stack of Lantern Cards
	 * @param returnLCard
	 *            The lantern card return by user
	 * @param pickLCard
	 *            The lantern card pick by user
	 * 
	 */
	public boolean spendFavorTokens(FavorTokens favorToken,
			LanternCards lanternCards, String returnLCard, String pickLCard) {

		if (this.favorTokenScore > 1
				&& returnLanternCards(returnLCard, pickLCard, lanternCards)) {
			favorToken.incrementToken();
			favorToken.incrementToken();
			this.favorTokenScore -= 2;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method will be called from spendFavorTokens method to exchange
	 * lantern cards between player stack and general stack
	 * 
	 * @param returnLCard
	 *            The lantern card return by user
	 * @param pickLCard
	 *            The lantern card pick by user
	 * @param lanternCardsAvailable
	 *            The object contains the current stack of Lantern Cards
	 */
	public boolean returnLanternCards(String returnLCard, String pickLCard,
			LanternCards lanternCardsAvailable) {
		if ((returnLCard.equals("redCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addRedcard();
			playerLCStack.getRedCard();
			return true;

		} else if ((returnLCard.equals("blueCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addBluecard();
			playerLCStack.getBlueCard();
			return true;

		} else if ((returnLCard.equals("greenCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addGreencard();
			playerLCStack.getGreenCard();
			return true;

		} else if ((returnLCard.equals("whiteCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addWhitecard();
			playerLCStack.getWhiteCard();
			return true;

		} else if ((returnLCard.equals("purpleCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addPurplecard();
			playerLCStack.getPurpleCard();
			return true;

		} else if ((returnLCard.equals("blackCard"))
				&& (pickLanternCard(pickLCard, lanternCardsAvailable))) {
			lanternCardsAvailable.addBlackcard();
			playerLCStack.getBlackCard();
			return true;

		} else if (pickLanternCard(pickLCard, lanternCardsAvailable)) {
			lanternCardsAvailable.addOrangecard();
			playerLCStack.getOrangeCard();
			return true;
		}
		return false;
	}

	/*
	 * set the favor token on start or load
	 */
	public void setFavorToken(int favorToken) {
		this.favorTokenScore = favorToken;
	}

	/*
	 * get the favor token
	 */
	public int getFavorToken() {
		return this.favorTokenScore;
	}
}
