package eclipse_project;

import java.util.ArrayList;
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
	 * 
	 * @return count of orange cards
	 */
	public int redCardCount() {
		return this.redStack.size();
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
	 * 
	 * @return count of orange cards
	 */
	public int blueCardCount() {
		return this.blueStack.size();
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
	 * 
	 * @return count of orange cards
	 */
	public int greenCardCount() {
		return this.greenStack.size();
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
	 * 
	 * @return count of orange cards
	 */
	public int whiteCardCount() {
		return this.whiteStack.size();
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
	 * 
	 * @return count of orange cards
	 */
	public int purpleCardCount() {
		return this.purpleStack.size();
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
	 * 
	 * @return count of orange cards
	 */
	public int blackCardCount() {
		return this.blackStack.size();
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

	/**
	 * 
	 * @return count of orange cards
	 */
	public int orangeCardCount() {
		return this.orangeStack.size();
	}
	
	/**
	 * @param card the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public boolean hasCard(String card) {
		if (card.equals("redCard"))
		{
			 return this.redStack.size() > 0;
		}else if (card.equals("blueCard"))
		{
			 return this.blueStack.size() > 0;
		}else if (card.equals("greenCard"))
		{
			 return this.greenStack.size() > 0;
		}else if (card.equals("whiteCard"))
		{
			 return this.whiteStack.size() > 0;
		}else if (card.equals("purpleCard"))
		{
			 return this.purpleStack.size() > 0;
		}else if (card.equals("blackCard"))
		{
			 return this.blackStack.size() > 0;
		}else if (card.equals("orangeCard"))
		{
			 return this.orangeStack.size() > 0;
		}
		return false;
	}
	
	/**
	 * @param card the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public void addCard(String card) {
		if (card.equals("redCard"))
		{
			 this.addRedcard();
		}else if (card.equals("blueCard"))
		{
			this.addBluecard();
		}else if (card.equals("greenCard"))
		{
			this.addGreencard();
		}else if (card.equals("whiteCard"))
		{
			this.addWhitecard();
		}else if (card.equals("purpleCard"))
		{
			this.addPurplecard();
		}else if (card.equals("blackCard"))
		{
			this.addBlackcard();
		}else if (card.equals("orangeCard"))
		{
			this.addOrangecard();
		}
	}
	
	/**
	 * @param card the color of the card to with draw
	 * @return true if the card was Succesfully withdrawn
	 */
	public boolean getCard(String card) {
		if (card.equals("redCard"))
		{
			return this.getRedCard();
		}else if (card.equals("blueCard"))
		{
			return this.getBlueCard();
		}else if (card.equals("greenCard"))
		{
			return this.getGreenCard();
		}else if (card.equals("whiteCard"))
		{
			return this.getWhiteCard();
		}else if (card.equals("purpleCard"))
		{
			return this.getPurpleCard();
		}else if (card.equals("blackCard"))
		{
			return this.getBlackCard();
		}else if (card.equals("orangeCard"))
		{
			return this.getOrangeCard();
		}
		
		return false;
	}
	
	/**
	 * This method assigns lanternCards to each Player according to LakeTile and players position
	 * @param numOfPlayers - Number of Players
	 * @param playerList - Arraylist of Players
	 * @param startTile - LakeTile
	 * @param lanternCards - Object of lanternCard class
	 */
	public void assignLanternCards(int numOfPlayers,ArrayList<Player> playerList,LakeTiles startTile,LanternCards lanternCards)
	{
		String lanternCardtoAdd;
		switch(numOfPlayers)
		{		
		case 2:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;
		case 3:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.upColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;
		case 4:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.upColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.downColor+"Card";
			playerList.get(3).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;

		}
	}
}
