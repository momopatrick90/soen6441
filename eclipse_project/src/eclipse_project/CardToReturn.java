package eclipse_project;

/**
 * Cards to be returned for makeDedication
 */
public class CardToReturn {
	private String color;
	private String color2;
	private String color3;

	/**
	 * This constructor sets the 3 cards color for 3 pair dedication
	 * @param card card 1's Value
	 * @param card2 card 2's Value
	 * @param card3 card 3's Value
	 */
	public CardToReturn(int card, int card2, int card3) {
		// set the color of the first card
		if (card == 1) {
			color = "redCard";
		} else if (card == 2) {
			color = "blueCard";
		} else if (card == 3) {
			color = "greenCard";
		} else if (card == 4) {
			color = "whiteCard";
		} else if (card == 5) {
			color = "purpleCard";
		} else if (card == 6) {
			color = "blackCard";
		} else if (card == 7) {
			color = "orangeCard";
		}
		// set the color of the second card
		if (card2 == 1) {
			color2 = "redCard";
		} else if (card2 == 2) {
			color2 = "blueCard";
		} else if (card2 == 3) {
			color2 = "greenCard";
		} else if (card2 == 4) {
			color2 = "whiteCard";
		} else if (card2 == 5) {
			color2 = "purpleCard";
		} else if (card2 == 6) {
			color2 = "blackCard";
		} else if (card2 == 7) {
			color2 = "orangeCard";
		}
		// set the color of the third card
		if (card3 == 1) {
			color3 = "redCard";
		} else if (card3 == 2) {
			color3 = "blueCard";
		} else if (card3 == 3) {
			color3 = "greenCard";
		} else if (card3 == 4) {
			color3 = "whiteCard";
		} else if (card3 == 5) {
			color3 = "purpleCard";
		} else if (card3 == 6) {
			color3 = "blackCard";
		} else if (card3 == 7) {
			color3 = "orangeCard";
		}

	}

	/**
	 * This constructor sets the cards color for four of kind dedication
	 * @param card card's Value
	 */
	public CardToReturn(int card) {
		// set the color of the first card
		if (card == 1) {
			color = "redCard";
		} else if (card == 2) {
			color = "blueCard";
		} else if (card == 3) {
			color = "greenCard";
		} else if (card == 4) {
			color = "whiteCard";
		} else if (card == 5) {
			color = "purpleCard";
		} else if (card == 6) {
			color = "blackCard";
		} else if (card == 7) {
			color = "orangeCard";
		}
	}

	/**
	 * constructor for CardToReturn
	 */
	public CardToReturn() {

	}

	/**
	 * This method returns the 6 cards for 3 pair dedication
	 * @return returnCards returns the 6 cards
	 */
	public LanternCards returnStackThreeOfKind() {
		LanternCards returnCards = new LanternCards();
		returnCards.addCard(color);
		returnCards.addCard(color2);
		returnCards.addCard(color3);
		returnCards.addCard(color);
		returnCards.addCard(color2);
		returnCards.addCard(color3);
		

		return returnCards;

	}

	/**
	 * This method returns the 4 cards for four of kind dedication
	 * @return returnCards returns the 4 cards
	 */
	public LanternCards returnStackFourOfKind() {
		LanternCards returnCards = new LanternCards();
		returnCards.addCard(color);
		returnCards.addCard(color);
		returnCards.addCard(color);
		returnCards.addCard(color);
		return returnCards;

	}

	/**
	 * This method returns the 7 cards for seven Unique dedication
	 * @return returnCards returns the 7 cards 
	 */
	public LanternCards returnSeveUnique() {
		LanternCards returnCards = new LanternCards();
		returnCards.addBlackcard();
		returnCards.addBluecard();
		returnCards.addGreencard();
		returnCards.addOrangecard();
		returnCards.addRedcard();
		returnCards.addPurplecard();
		returnCards.addWhitecard();

		return returnCards;

	}

	/**
	 * Getter method for color
	 * @return color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Getter method for color2
	 * @return color
	 */
	public String getColor2() {
		return this.color2;
	}

	/**
	 * Getter method for color3
	 * @return color
	 */
	public String getColor3() {
		return this.color3;
	}

	/**
	 * this method checks can a player make seven Unique dedication
	 * @param player current player
	 * @return flag 
	 */
	public boolean SevenUniqueState(Player player) {
		boolean flag=false;
		if (player.getLanternCards().blackCardCount() >= 1
				&& player.getLanternCards().blueCardCount() >= 1
				&& player.getLanternCards().greenCardCount() >= 1
				&& player.getLanternCards().orangeCardCount() >= 1
				&& player.getLanternCards().redCardCount() >= 1
				&& player.getLanternCards().purpleCardCount() >= 1
				&& player.getLanternCards().whiteCardCount() >= 1)
			flag=true;
		return flag;
			}

}
