package eclipse_project;

public class CardToReturn {
	private String color;
	private String color2;
	private String color3;

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

	public CardToReturn() {
		
	}

	public LanternCards returnStackThreeOfKind() {
		LanternCards returnCards = new LanternCards();
		returnCards.addCard(color);
		returnCards.addCard(color2);
		returnCards.addCard(color3);

		return returnCards;

	}

	public LanternCards returnStackFourOfKind() {
		LanternCards returnCards = new LanternCards();
		returnCards.addCard(color);
		return returnCards;

	}
	
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
	public String getColor(){
		return this.color;
	}
	public String getColor2(){
		return this.color;
	}
	public String getColor3(){
		return this.color;
	}

}
