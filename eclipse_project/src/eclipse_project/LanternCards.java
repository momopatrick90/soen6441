package eclipse_project;

import java.util.Stack;

public class LanternCards {

	private String[] Colors = { "Red", "Blue", "Green", "White", "Purple",
			"Black", "Orange" };

	private Stack<String> redStack = new Stack<String>();
	private Stack<String> blueStack = new Stack<String>();
	private Stack<String> greenStack = new Stack<String>();;
	private Stack<String> whiteStack = new Stack<String>();
	private Stack<String> purpleStack = new Stack<String>();
	private Stack<String> blackStack = new Stack<String>();
	private Stack<String> orangeStack = new Stack<String>();

	private int numOfPlayers;

	/**
	 * Create an empty stack of seven colors.
	 */
	public LanternCards() {
		super();
	}

	/**
	 * Constructor to initialize the number of lanterns cards that depends on
	 * the number of players.
	 * 
	 * @param numOfPlayers
	 *            - Number of players playing game.
	 */
	public LanternCards(int numOfPlayers) {

		this.numOfPlayers = numOfPlayers;

		int cards = 0;

		// Determine how many cards to be push based on the number of players
		if (this.numOfPlayers == 2)
			cards = 5;
		else if (this.numOfPlayers == 3)
			cards = 7;
		else if (this.numOfPlayers == 4)
			cards = 8;
		else
			System.out.println("The number of players is invalid");

		for (int i = 0; i < cards; i++) {
			this.redStack.push(this.Colors[0]);
			this.blueStack.push(this.Colors[1]);
			this.greenStack.push(this.Colors[2]);
			this.whiteStack.push(this.Colors[3]);
			this.purpleStack.push(this.Colors[4]);
			this.blackStack.push(this.Colors[5]);
			this.orangeStack.push(this.Colors[6]);
		}
	}

	/**
	 * Constructor to initialize the number of lantern cards with some existing
	 * values - Existing Game Resume
	 * 
	 * @param numOfPlayers
	 *            - Number of Players playing the game.
	 * @param redCount
	 *            - Number of Red Cards.
	 * @param blueCount
	 *            - Number of Blue Cards.
	 * @param greenCount
	 *            - Number of Green Cards.
	 * @param whiteCount
	 *            - Number of White Cards.
	 * @param purpleCount
	 *            - Number of Purple Cards.
	 * @param blackCount
	 *            - Number of Black Cards.
	 * @param orangeCount
	 *            - Number of Orange Cards.
	 */
	public LanternCards(int numOfPlayers, int redCount, int blueCount,
			int greenCount, int whiteCount, int purpleCount, int blackCount,
			int orangeCount) {

		this.numOfPlayers = numOfPlayers;

		// push the specified number of cards in each stack
		for (int i = 0; i < redCount; i++)
			this.redStack.push(this.Colors[0]);

		for (int i = 0; i < blueCount; i++)
			this.blueStack.push(this.Colors[1]);

		for (int i = 0; i < greenCount; i++)
			this.greenStack.push(this.Colors[2]);

		for (int i = 0; i < whiteCount; i++)
			this.whiteStack.push(this.Colors[3]);

		for (int i = 0; i < purpleCount; i++)
			this.purpleStack.push(this.Colors[4]);

		for (int i = 0; i < blackCount; i++)
			this.blackStack.push(this.Colors[5]);

		for (int i = 0; i < orangeCount; i++)
			this.orangeStack.push(this.Colors[6]);
	}

	/**
	 * This method gives the red card to the player
	 * 
	 * @return true or false
	 */
	public boolean getRedCard() {

		boolean state = false;

		if (!this.redStack.isEmpty()) {
			state = true;
			this.redStack.pop();
		}

		return state;
	}

	/**
	 * This method add the red card to the stack
	 */
	public void addRedcard() {

		this.redStack.push(this.Colors[0]);
	}

	/**
	 * This method gives the blue card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlueCard() {

		boolean state = false;

		if (!this.blueStack.isEmpty()) {
			state = true;
			this.blueStack.pop();
		}

		return state;
	}

	/**
	 * This method add the blue card to the stack
	 */
	public void addBluecard() {

		this.blueStack.push(this.Colors[1]);
	}

	/**
	 * This method gives the green card to the player
	 * 
	 * @return true or false
	 */
	public boolean getGreenCard() {

		boolean state = false;

		if (!this.greenStack.isEmpty()) {
			state = true;
			this.greenStack.pop();
		}

		return state;
	}

	/**
	 * This method add the green card to the stack
	 */
	public void addGreencard() {

		this.greenStack.push(this.Colors[2]);
	}

	/**
	 * This method gives the white card to the player
	 * 
	 * @return true or false
	 */
	public boolean getWhiteCard() {

		boolean state = false;

		if (!this.whiteStack.isEmpty()) {
			state = true;
			this.whiteStack.pop();
		}

		return state;
	}

	/**
	 * This method add the white card to the stack
	 */
	public void addWhitecard() {

		this.whiteStack.push(this.Colors[3]);
	}

	/**
	 * This method gives the purple card to the player
	 * 
	 * @return true or false
	 */
	public boolean getPurpleCard() {

		boolean state = false;

		if (!this.purpleStack.isEmpty()) {
			state = true;
			this.purpleStack.pop();
		}

		return state;
	}

	/**
	 * This method add the purple card to the stack
	 */
	public void addPurplecard() {

		this.purpleStack.push(this.Colors[4]);
	}

	/**
	 * This method gives the black card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlackCard() {

		boolean state = false;

		if (!this.blackStack.isEmpty()) {
			state = true;
			this.blackStack.pop();
		}

		return state;
	}

	/**
	 * This method add the black card to the stack
	 */
	public void addBlackcard() {

		this.blackStack.push(this.Colors[5]);
	}

	/**
	 * This method gives the orange card to the player
	 * 
	 * @return true or false
	 */
	public boolean getOrangeCard() {

		boolean state = false;

		if (!this.orangeStack.isEmpty()) {
			state = true;
			this.orangeStack.pop();
		}

		return state;
	}

	/**
	 * This method add the orange card to the stack
	 */
	public void addOrangecard() {

		this.orangeStack.push(this.Colors[6]);
	}

}
