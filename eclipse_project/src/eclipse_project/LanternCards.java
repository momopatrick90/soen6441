package eclipse_project;

import java.util.Stack;

public class LanternCards {
	
	private Stack<String> redStack = new Stack();
	private Stack<String> blueStack = new Stack();
	private Stack<String> greenStack = new Stack();
	private Stack<String> whiteStack = new Stack();
	private Stack<String> purpleStack = new Stack();
	private Stack<String> blackStack = new Stack();
	private Stack<String> orangeStack = new Stack();

	/**
	 * Constructor to initialize the Stack of cards.
	 */
	public LanternCards() {

	}

	public void initializeLatternCards(int NumOfPlayers) {
		int cards = 0;

		// Determine how many cards to be push based on the number of players
		if (NumOfPlayers == 2)
			cards = 5;
		else if (NumOfPlayers == 3)
			cards = 7;
		else if (NumOfPlayers == 4)
			cards = 8;
		else
			System.out.println("The number of players is invalid");

		// push the specified number of cards in each stack
		for (int i = 0; i < cards; i++) {
			redStack.push("redCard");
			blueStack.push("blueCard");
			greenStack.push("greenCard");
			whiteStack.push("whiteCard");
			purpleStack.push("purpleCard");
			blackStack.push("blackStack");
			orangeStack.push("orangeCard");
		}
	}

	// Method called when user pick a red card.
	public boolean getRedCard() {

		boolean state = false;

		if (!redStack.isEmpty()) {
			state = true;
			redStack.pop();
		}

		return state;
	}

	// Method called when user returns a red card.
	public void addRedcard(String redCard) {
		redStack.push(redCard);
	}

	// Method called when user pick a blue card.
	public boolean getBlueCard() {

		boolean state = false;

		if (!blueStack.isEmpty()) {
			state = true;
			blueStack.pop();
		}

		return state;
	}

	// Method called when user returns a blue card.
	public void addBluecard(String blueCard) {
		blueStack.push(blueCard);
	}

	// Method called when user pick a green card.
	public boolean getGreenCard() {

		boolean state = false;

		if (!greenStack.isEmpty()) {
			state = true;
			greenStack.pop();
		}

		return state;
	}

	// Method called when user returns a green card.
	public void addGreencard(String greenCard) {
		greenStack.push(greenCard);
	}

	// Method called when user pick a white card.
	public boolean getWhiteCard() {

		boolean state = false;

		if (!whiteStack.isEmpty()) {
			state = true;
			whiteStack.pop();
		}

		return state;
	}

	// Method called when user returns a white card.
	public void addWhitecard(String whiteCard) {
		whiteStack.push(whiteCard);
	}

	// Method called when user pick a purple card.
	public boolean getPurpleCard() {

		boolean state = false;

		if (!purpleStack.isEmpty()) {
			state = true;
			purpleStack.pop();
		}

		return state;
	}

	// Method called when user returns a purple card.
	public void addPurplecard(String purpleCard) {
		purpleStack.push(purpleCard);
	}

	// Method called when user pick a black card.
	public boolean getBlackCard() {

		boolean state = false;

		if (!blackStack.isEmpty()) {
			state = true;
			blackStack.pop();
		}

		return state;
	}

	// Method called when user returns a black card.
	public void addBlackcard(String blackCard) {
		blackStack.push(blackCard);
	}

	// Method called when user pick a orange card.
	public boolean getOrangeCard() {

		boolean state = false;

		if (!orangeStack.isEmpty()) {
			state = true;
			orangeStack.pop();
		}

		return state;
	}

	// Method called when user returns a orange card.
	public void addOrangecard(String orangeCard) {
		orangeStack.push(orangeCard);
	}

}
