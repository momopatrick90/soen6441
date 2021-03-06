package eclipse_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class LanternCards {
	// stacks index by color
	public HashMap<String, Integer> stacks = new HashMap<String, Integer>();

	private int numOfPlayers;

	/**
	 * Create an empty stack of seven colors.
	 */
	public LanternCards() {
		// push the specified number of cards in each stack
		this.stacks.put("redCard", 0);
		this.stacks.put("blueCard", 0);
		this.stacks.put("greenCard", 0);
		this.stacks.put("whiteCard", 0);
		this.stacks.put("purpleCard", 0);
		this.stacks.put("blackCard", 0);
		this.stacks.put("orangeCard", 0);
	}

	/**
	 * Constructor to initialize the number of lanterns cards that depends on
	 * the number of players.
	 * 
	 * @param numOfPlayers
	 *            - Number of players playing game.
	 */
	public LanternCards(int numOfPlayers) {
		this();

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

		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			entry.setValue(cards);
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

		//
		this.stacks.put("redCard", redCount);
		//
		this.stacks.put("blueCard", blueCount);
		//
		this.stacks.put("greenCard", greenCount);
		//
		this.stacks.put("whiteCard", whiteCount);
		//
		this.stacks.put("purpleCard", purpleCount);
		//
		this.stacks.put("blackCard", blackCount);
		//
		this.stacks.put("orangeCard", orangeCount);
	}

	/**
	 * This method gives the red card to the player
	 * 
	 * @return true or false
	 */
	public boolean getRedCard() {
		return this.getCard("redCard");
	}

	/**
	 * This method add the red card to the stack
	 */
	public void addRedcard() {
		this.addCard("redCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int redCardCount() {
		return this.stacks.get("redCard");
	}

	/**
	 * This method gives the blue card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlueCard() {
		return this.getCard("blueCard");
	}

	/**
	 * This method add the blue card to the stack
	 */
	public void addBluecard() {
		this.addCard("blueCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int blueCardCount() {
		return this.stacks.get("blueCard");
	}

	/**
	 * This method gives the green card to the player
	 * 
	 * @return true or false
	 */
	public boolean getGreenCard() {
		return this.getCard("greenCard");
	}

	/**
	 * This method add the green card to the stack
	 */
	public void addGreencard() {
		this.addCard("greenCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int greenCardCount() {
		return this.stacks.get("greenCard");
	}

	/**
	 * This method gives the white card to the player
	 * 
	 * @return true or false
	 */
	public boolean getWhiteCard() {
		return this.getCard("whiteCard");
	}

	/**
	 * This method add the white card to the stack
	 */
	public void addWhitecard() {
		this.addCard("whiteCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int whiteCardCount() {
		return this.stacks.get("whiteCard");
	}

	/**
	 * This method gives the purple card to the player
	 * 
	 * @return true or false
	 */
	public boolean getPurpleCard() {
		return this.getCard("purpleCard");
	}

	/**
	 * This method add the purple card to the stack
	 */
	public void addPurplecard() {
		this.addCard("purpleCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int purpleCardCount() {
		return this.stacks.get("purpleCard");
	}

	/**
	 * This method gives the black card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlackCard() {
		return this.getCard("blackCard");
	}

	/**
	 * This method add the black card to the stack
	 */
	public void addBlackcard() {
		this.addCard("blackCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int blackCardCount() {
		return this.stacks.get("blackCard");
	}

	/**
	 * This method gives the orange card to the player
	 * 
	 * @return true or false
	 */
	public boolean getOrangeCard() {
		return this.getCard("orangeCard");
	}

	/**
	 * This method add the orange card to the stack
	 */
	public void addOrangecard() {
		this.addCard("orangeCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int orangeCardCount() {
		return this.stacks.get("orangeCard");
	}

	public int CardCount(int card) {
		String color = null;

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
		return this.stacks.get(color);
	}

	
	public int CardCount(String card) {
		return this.stacks.get(card);
	}
	
	/**
	 * @param card
	 *            the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public boolean hasCard(String card) {
		return this.stacks.containsKey(card) && this.stacks.get(card) >= 1;
	}

	/**
	 * @param card
	 *            the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public void addCard(String card) {
		this.stacks.put(card, this.stacks.get(card) + 1);
	}

	/**
	 * @param card
	 *            the color of the card to with draw
	 * @return true if the card was Succesfully withdrawn
	 */
	public boolean getCard(String card) {
		if (!this.stacks.containsKey(card) || this.stacks.get(card) == 0) {

			return false;
		}

		//
		this.stacks.put(card, this.stacks.get(card) - 1);
		return true;
	}

	/**
	 * @param quantity
	 * @return returns the number of stacks of a specific color, that have
	 *         quantity amount of cards
	 */
	public int numColorsWithQuantity(int quantity) {
		return this.colorsWithQuantity(quantity).size();
	}
	
	/** 
	 * @param quantity
	 * returns the number of stacks of a specific color, that have
	 *         quantity amount of cards
	 */
	public LinkedList<String> colorsWithQuantity(int quantity) {
		LinkedList<String> result = new LinkedList<String>();

		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			if(entry.getValue() == quantity)
				result.push(entry.getKey());
		}

		return result;
	}
	
	
	/**
	 * @param quantity
	 * @return returns the number of stacks of a specific color, that have
	 *         at least quantiy amount of cards
	 */
	public LinkedList<String> colorsWithAtLeastQuantity(int quantity) {
		LinkedList<String> result = new LinkedList<String>();

		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			if(entry.getValue() >= quantity)
				result.push(entry.getKey());
		}

		return result;
	}

	/**
	 * @return total number of stacks of a specific color that are not empty
	 */
	public int nonZeroColors() {
		int result = 0;

		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			if (entry.getValue() != 0) {
				result += 1;
			}
		}

		return result;
	}

	/**
	 * @param lanternCards
	 *            move all the latterns cards in to this objects lantern cards
	 */
	public void getAll(LanternCards lanternCards) {
		//
		for (Map.Entry<String, Integer> entry : lanternCards.stacks.entrySet()) {
			//
			this.stacks.put(entry.getKey(), this.stacks.get(entry.getKey())
					+ entry.getValue());
			//
			entry.setValue(0);
		}
	}
	
	/**
	 * Withdraw this amount of lanternCards from *this* stack
	 * @param lanternCards
	 */
	public void withdrawAll(LanternCards lanternCards) {
		//
		for (Map.Entry<String, Integer> entry : lanternCards.stacks.entrySet()) {
			//
			this.stacks.put(entry.getKey(), this.stacks.get(entry.getKey())
					- entry.getValue());
		}
	}

	/**
	 * @return color of the lanternCard in String format
	 */
	public String toString() {
		String result = "LanternCards: ";
		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			result += "\n - " + entry.getKey() + ":  " + entry.getValue();
		}

		return result;
	}

	/**
	 * This method assigns lanternCards to each Player according to LakeTile and
	 * players position
	 * 
	 * @param numOfPlayers
	 *            - Number of Players
	 * @param playerList
	 *            - Arraylist of Players
	 * @param startTile
	 *            - LakeTile
	 * @param lanternCards
	 *            - Object of lanternCard class
	 */
	public void assignLanternCards(int numOfPlayers,
			ArrayList<Player> playerList, LakeTiles startTile,
			LanternCards lanternCards) {
		String lanternCardtoAdd;
		switch (numOfPlayers) {
		case 2:
			lanternCardtoAdd = startTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.rightColor + "Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd, lanternCards);
			break;
		case 3:
			lanternCardtoAdd = startTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.upColor + "Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.rightColor + "Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd, lanternCards);
			break;
		case 4:
			lanternCardtoAdd = startTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.upColor + "Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.rightColor + "Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd, lanternCards);
			lanternCardtoAdd = startTile.downColor + "Card";
			playerList.get(3).pickLanternCard(lanternCardtoAdd, lanternCards);
			break;

		}
	}

	/**
	 * This method assigns lanternCards to each Player according to LakeTile and
	 * players position
	 * 
	 * @param numOfPlayers
	 *            - Number of Players
	 * @param board
	 *            - GameBoard
	 * @param x
	 *            - X coordinate of lakeTile
	 * @param y
	 *            - Y coordinate of lakeTile
	 * @param lakeTile
	 *            - LakeTile
	 * @param playerList
	 *            - Arraylist of Players
	 * @param lanternCard
	 *            - Available Lantern Cards
	 */
	public void assignLanternCardsToPlayers(int numOfPlayers, Board board,
			int col, int row, LakeTiles lakeTile, ArrayList<Player> playerList,
			LanternCards lanternCard, FavorTokens favorTokens) {
		String card;
		switch (numOfPlayers) {
		case 2:
			card = lakeTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(card, lanternCard);
			card = lakeTile.rightColor + "Card";
			playerList.get(1).pickLanternCard(card, lanternCard);
			break;
		case 3:
			card = lakeTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(card, lanternCard);
			card = lakeTile.upColor + "Card";
			playerList.get(1).pickLanternCard(card, lanternCard);
			card = lakeTile.rightColor + "Card";
			playerList.get(2).pickLanternCard(card, lanternCard);
			break;
		case 4:
			card = lakeTile.leftColor + "Card";
			playerList.get(0).pickLanternCard(card, lanternCard);
			card = lakeTile.upColor + "Card";
			playerList.get(1).pickLanternCard(card, lanternCard);
			card = lakeTile.rightColor + "Card";
			playerList.get(2).pickLanternCard(card, lanternCard);
			card = lakeTile.downColor + "Card";
			playerList.get(3).pickLanternCard(card, lanternCard);
			break;
		}
		for (int currentPlayer = 0; currentPlayer < playerList.size(); currentPlayer++) {
			String cardColor = "";
			if (playerList.get(currentPlayer).current) {
				// If lakeTile has platform on it assign favorToken to
				// currentUser
				if (lakeTile.platform) {
					favorTokens.decrementToken();
					playerList.get(currentPlayer).favorTokenScore++;
				}
				if (board.board[row - 1][col] != -1) {
					for (int i = 0; i < board.tilesOnBoard.size(); i++) {
						if (board.tilesOnBoard.get(i).id == board.board[row - 1][col]
								&& board.tilesOnBoard.get(i).downColor
										.equalsIgnoreCase(lakeTile.upColor)) {
							// assign favorToken to Player as it matched with
							// lakeTile and given LakeTile has platform on it
							if (board.tilesOnBoard.get(i).platform) {
								favorTokens.decrementToken();
								playerList.get(currentPlayer).favorTokenScore++;
							}
							card = board.tilesOnBoard.get(i).downColor
									+ "Card";
							playerList.get(currentPlayer).pickLanternCard(card,
									lanternCard);

						}
					}
				}

				if (board.board[row + 1][col] != -1) {
					for (int i = 0; i < board.tilesOnBoard.size(); i++) {
						if (board.tilesOnBoard.get(i).id == board.board[row + 1][col]
								&& board.tilesOnBoard.get(i).upColor
										.equalsIgnoreCase(lakeTile.downColor)) {
							// assign favorToken to Player as it matched with
							// lakeTile and given LakeTile has platform on it
							if (board.tilesOnBoard.get(i).platform) {
								favorTokens.decrementToken();
								playerList.get(currentPlayer).favorTokenScore++;
							}
							card = lakeTile.downColor + "Card";
							playerList.get(currentPlayer).pickLanternCard(card,
									lanternCard);
						}
					}
				}

				if (board.board[row][col + 1] != -1) {
					for (int i = 0; i < board.tilesOnBoard.size(); i++) {
						if (board.tilesOnBoard.get(i).id == board.board[row][col + 1]
								&& board.tilesOnBoard.get(i).leftColor
										.equalsIgnoreCase(lakeTile.rightColor)) {
							// assign favorToken to Player as it matched with
							// lakeTile and given LakeTile has platform on it
							if (board.tilesOnBoard.get(i).platform) {
								favorTokens.decrementToken();
								playerList.get(currentPlayer).favorTokenScore++;
							}
							card = board.tilesOnBoard.get(i).leftColor + "Card";
							playerList.get(currentPlayer).pickLanternCard(card,
									lanternCard);
						}
					}
				}

				if (board.board[row][col - 1] != -1) {
					for (int i = 0; i < board.tilesOnBoard.size(); i++) {

						if (board.tilesOnBoard.get(i).id == board.board[row][col - 1]
								&& board.tilesOnBoard.get(i).rightColor
										.equalsIgnoreCase(lakeTile.leftColor)) {
							// assign favorToken to Player as it matched with
							// lakeTile and given LakeTile has platform on it
							if (board.tilesOnBoard.get(i).platform) {
								favorTokens.decrementToken();
								playerList.get(currentPlayer).favorTokenScore++;
							}
							card = board.tilesOnBoard.get(i).rightColor + "Card";
							playerList.get(currentPlayer).pickLanternCard(card,
									lanternCard);
						}
					}
				}

			}
		}
	}

	public void popThreePair(int card) {
		String color = null;
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
	 * This method assigns lanternCards to each Player according to LakeTile and
	 * players position
	 * 
	 * @param numOfPlayers
	 *            - Number of Players
	 * @param board
	 *            - GameBoard
	 * @param x
	 *            - X coordinate of lakeTile
	 * @param y
	 *            - Y coordinate of lakeTile
	 * @param lakeTile
	 *            - LakeTile
	 * @param playerList
	 *            - Arraylist of Players
	 * @param lanternCard
	 *            - Available Lantern Cards
	 */
	/**
	 * This method assigns lanternCards and favorTokens to dummy Greedy Player according to lakeTile placed.
	 * @param board gameBoard
	 * @param col column of the board where tile will be placed
	 * @param row row of the board where tile will be placed
	 * @param currentTileToPlace lakeTile to be placed
	 * @param player dummy Player
	 * @param lanternCard lanternCards available for the game
	 * @param favorTokens favorTokens available for the game
	 * @param numOfPlayers number of Players
	 * @return dataSetToReturn To return the lanternCards and favorTokens used for simulation back to game.
	 */
	public Vector assignLanternCardsToPlayerSimulation(
			Board board, int col, int row,
			LakeTiles currentTileToPlace,Player player,
			LanternCards lanternCard, FavorTokens favorTokens,int numOfPlayers) {
		String card=null;
		Vector dataSetToReturn = new Vector();
		int numberOfCardsGot=0;
		boolean flag=false;
		switch(numOfPlayers)
		{
			case 2:
				if(player.boardPosition.equalsIgnoreCase("left"))
				{
					card = currentTileToPlace.leftColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("right"))
				{
					card = currentTileToPlace.rightColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				if(flag)
				{
					numberOfCardsGot++;
					dataSetToReturn.addElement(card);
				}
				break;
			case 3:
				if(player.boardPosition.equalsIgnoreCase("left"))
				{
					card = currentTileToPlace.leftColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("right"))
				{
					card = currentTileToPlace.rightColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("up"))
				{
					card = currentTileToPlace.upColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				if(flag)
				{
					numberOfCardsGot++;
					dataSetToReturn.addElement(card);
				}
				break;
			case 4:
				if(player.boardPosition.equalsIgnoreCase("left"))
				{
					card = currentTileToPlace.leftColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("right"))
				{
					card = currentTileToPlace.rightColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("up"))
				{
					card = currentTileToPlace.upColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				else if(player.boardPosition.equalsIgnoreCase("down"))
				{
					card = currentTileToPlace.downColor + "Card";
					flag=player.pickLanternCard(card, lanternCard);
				}
				if(flag)
				{
					numberOfCardsGot++;
					dataSetToReturn.addElement(card);
				}
				break;
		}
		flag=false;
		if (currentTileToPlace.platform && favorTokens.getTokens()>=1) 
			player.favorTokenScore++;
		if (board.board[row - 1][col] != -1) {
			for (int i = 0; i < board.tilesOnBoard.size(); i++) {
				if (board.tilesOnBoard.get(i).id == board.board[row - 1][col]
						&& board.tilesOnBoard.get(i).downColor
								.equalsIgnoreCase(currentTileToPlace.upColor)) {
					// assign favorToken to Player as it matched with
					// lakeTile and given LakeTile has platform on it
					if (board.tilesOnBoard.get(i).platform && favorTokens.getTokens()>=1) {
						player.favorTokenScore++;
					}
					card = board.tilesOnBoard.get(i).downColor+ "Card";
					flag=player.pickLanternCard(card,lanternCard);
					if(flag)
					{
						numberOfCardsGot++;
						dataSetToReturn.addElement(card);
					}
				}
			}
		}

		flag=false;
		if (board.board[row + 1][col] != -1) {
			for (int i = 0; i < board.tilesOnBoard.size(); i++) {
				if (board.tilesOnBoard.get(i).id == board.board[row + 1][col]
						&& board.tilesOnBoard.get(i).upColor
								.equalsIgnoreCase(currentTileToPlace.downColor)) {
					// assign favorToken to Player as it matched with
					// lakeTile and given LakeTile has platform on it
					if (board.tilesOnBoard.get(i).platform && favorTokens.getTokens()>=1) {
						player.favorTokenScore++;
					}
					card = currentTileToPlace.downColor + "Card";
					flag=player.pickLanternCard(card,lanternCard);
					if(flag)
					{
						numberOfCardsGot++;
						dataSetToReturn.addElement(card);
					}
				}
			}
		}

		flag=false;
		if (board.board[row][col + 1] != -1) {
			for (int i = 0; i < board.tilesOnBoard.size(); i++) {
				if (board.tilesOnBoard.get(i).id == board.board[row][col + 1]
						&& board.tilesOnBoard.get(i).leftColor
								.equalsIgnoreCase(currentTileToPlace.rightColor)) {
					// assign favorToken to Player as it matched with
					// lakeTile and given LakeTile has platform on it
					if (board.tilesOnBoard.get(i).platform  && favorTokens.getTokens()>=1) {
						player.favorTokenScore++;
					}
					card = board.tilesOnBoard.get(i).leftColor + "Card";
					flag=player.pickLanternCard(card,lanternCard);
					if(flag)
					{
						numberOfCardsGot++;
						dataSetToReturn.addElement(card);
					}
				}
			}
		}

		flag=false;
		if (board.board[row][col - 1] != -1) {
			for (int i = 0; i < board.tilesOnBoard.size(); i++) {

				if (board.tilesOnBoard.get(i).id == board.board[row][col - 1]
						&& board.tilesOnBoard.get(i).rightColor
								.equalsIgnoreCase(currentTileToPlace.leftColor)) {
					// assign favorToken to Player as it matched with
					// lakeTile and given LakeTile has platform on it
					if (board.tilesOnBoard.get(i).platform  && favorTokens.getTokens()>=1) {
						player.favorTokenScore++;
					}
					card = board.tilesOnBoard.get(i).rightColor + "Card";
					flag=player.pickLanternCard(card,lanternCard);
					if(flag)
					{
						numberOfCardsGot++;
						dataSetToReturn.addElement(card);
					}
				}
			}
		}
		dataSetToReturn.addElement(player.favorTokenScore);
		dataSetToReturn.addElement(numberOfCardsGot);
		return dataSetToReturn;
	}

	/**
	 * This method returns the lantern cards used for simulation from global lantern cards 
	 * @param returnedLanternCards
	 */
	public void returnAll(LanternCards returnedLanternCards) {
		for (Map.Entry<String, Integer> entry : returnedLanternCards.stacks.entrySet()) {
			//
			this.stacks.put(entry.getKey(), this.stacks.get(entry.getKey())
					- entry.getValue());
			//
			entry.setValue(0);
		}

	}
	
	/**
	 * This method removes the lanterncards used for simulation from player
	 */
	public void removeLanternCard(String card,Player dummyPlayer)
	{
		dummyPlayer.playerLCStack.stacks.put(card, this.stacks.get(card) - 1);
	}
	
	/**
	 * What is the best possible score for this lantern cards, for the next turn?
	 * considering the different combinations of 7 unique, 4 of kind, and 3 pairs
	 * TODO check really what dedication means
	 * TODO what id dedication=0, use generica four
	 * @param dedicationTokens
	 * @return
	 */
	public int possibleDedicationsCount(DedicationTokens dedicationTokens)
	{
		int total = 0;
		// number of seven unique
		if(this.getSevenUniques() != null && dedicationTokens.sevenUniqueCount()>=1)
			total++;
		
		// can do four of kind
		if(this.getFourOfKinds() != null && dedicationTokens.fourOfKindCount()>=1)
			total++;
		
		// 
		if(this.getThreePairs() != null && dedicationTokens.threePairCount()>=1)
			total++;
		
		return total;
	}
	
	/**
	 * Get any  four of kind
	 * @return return true if there was a NEXT possible combination
	 */
	public LanternCards getFourOfKinds()
	{
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			if(entry.getValue() >= 4)
			{
				LanternCards result = new LanternCards();
				result.stacks.put(entry.getKey(), 4);
				return result; 
			}
		}
		
		return null;
	}
	


	/**
	 * Get a 2 pair if there
	 * @return return true if there was a possible combination
	 */
	public LanternCards getThreePairs()
	{
		LanternCards result = new LanternCards();
		

		//
		int pairs = 0;
		
		
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			// get pair if there
			if(entry.getValue() >= 2)
			{
				pairs += 1;
				result.addCard(entry.getKey());
				result.addCard(entry.getKey());
			}
				
			//
			if(pairs == 3)
			{
				return result;
			}
		}

		//
		return null;
	}




	/**
	 * get a stack of seven uniques, without deduction from stack
	 * @param amount
	 * @return  true is this lanterncards can do a 7 unique
	 */
	public LanternCards getSevenUniques()
	{
		//
		LanternCards result = new LanternCards();
		
		//
		for (Map.Entry<String, Integer> entry : this.stacks.entrySet()) {
			//can't do seven unique
			if(entry.getValue() < 1)
			{
				return null;
			}
			
			result.addCard(entry.getKey());
			
		}
		return result;
	}
	
	/**
	 * 
	 * @param lanternCards
	 * @return make an exact copy of lanternCards
	 */
	public LanternCards duplicate()
	{
		LanternCards result = new LanternCards();
		result.stacks = (HashMap<String, Integer>) this.stacks.clone();
		return result;
	}
}

