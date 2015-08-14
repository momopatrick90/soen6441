package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RandomPlayer implements PlayerStrategy {

	int indexMin = 0;
	int indexMax = 2;
	int randomIndex;
	boolean fourKind = false;
	boolean sevenUnique = false;
	boolean threePair = false;
	int cardFourKind = 0;
	int threePairCard1 = 0, threePairCard2 = 0, threePairCard3 = 0;

	public RandomPlayer(String name) {
		//super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * @see eclipse_project.PlayerStrategy#play(eclipse_project.GameEngine, eclipse_project.Player)
	 */
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		// Exchange lantern cards
		System.out
				.println("Type 1 if you want to exchange lantern card, or any other number to skip:");
		int minChoice = 1;
		int maxChoice = 2;

		int choice = RandomNumberGenerator(minChoice, maxChoice);
		System.out.println("Choice made:" + choice);
		if (choice == 1) {
			try {
				exchangeLanternCards(gameEngine, player);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Make dedication

		fourKind = checkFourOfKindDedication(player, gameEngine);
		sevenUnique = checkSevenUniqueDedication(player, gameEngine);
		threePair = checkThreePairDedication(player, gameEngine);

		// if Random player can make any of the three dedications then there is
		// a 50/50 chance of choosing to make dedication or not. Else Random
		// Player chooses not to.

		if (fourKind || sevenUnique || threePair)
			choice = RandomNumberGenerator(minChoice, maxChoice);
		else
			choice = 2;
		System.out
				.println("Type 1 if you want to make a dedication, any other number to skip: ");
		System.out.println("Choice made: " + choice);

		if (choice == 1) {

			try {
				makeDedication(gameEngine, player);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// place lake tile
		try {
			placeLakeTile(gameEngine, player);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (gameEngine.lakeTiles.hasLakeTile()) {
			// Composury pick lake tile
			player.pickLakeTileFromStack(gameEngine.lakeTiles.getLakeTile());
			System.out.println();
			player.displayPlayersLakeTile(player);
		} else {
			System.out.println("No lake tiles left on board to pick");
		}
	}

	protected void exchangeLanternCards(GameEngine gameEngine, Player player)
			throws IOException {
		int lanternCard = 0;
		String returnLCard = "";
		String pickLCard = "";
		String regex = "\\d+";
		Scanner in = new Scanner(System.in);
		//
		System.out.println("This is the amount of tokens you have: "
				+ player.favorTokenScore);
		System.out.println("----------------------------");

		// Print lanterns
		System.out.println("--Lantern cards you currently have:--");
		System.out.println(player.getLanternCards());
		System.out.println("----------------------------");

		//
		System.out.println("Select the lantern card you want to return.\n"
				+ "1.Red Card\n" + "2.Green Card\n" + "3.Purple Card\n"
				+ "4.Orange Card\n" + "5.White Card\n" + "6.Black Card\n"
				+ "7.Blue Card\n");

		int minLanCard = 1;
		int maxLanCard = 7;
		lanternCard = RandomNumberGenerator(minLanCard, maxLanCard);
		System.out.println(lanternCard);
		switch (lanternCard) {
		case 1:
			returnLCard = "redCard";
			break;
		case 2:
			returnLCard = "greenCard";
			break;
		case 3:
			returnLCard = "purpleCard";
			break;
		case 4:
			returnLCard = "orangeCard";
			break;
		case 5:
			returnLCard = "whiteCard";
			break;
		case 6:
			returnLCard = "blackCard";
			break;
		case 7:
			returnLCard = "blueCard";
			break;
		default:
			System.out.println("Invalid option");
			break;
		}

		lanternCard = RandomNumberGenerator(minLanCard, maxLanCard);
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n" + "2.Green Card\n" + "3.Purple Card\n"
				+ "4.Orange Card\n" + "5.White Card\n" + "6.Black Card\n"
				+ "7.Blue Card\n");
		System.out.println(lanternCard);
		switch (lanternCard) {
		case 1:
			pickLCard = "redCard";
			break;
		case 2:
			pickLCard = "greenCard";
			break;
		case 3:
			pickLCard = "purpleCard";
			break;
		case 4:
			pickLCard = "orangeCard";
			break;
		case 5:
			pickLCard = "whiteCard";
			break;
		case 6:
			pickLCard = "blackCard";
			break;
		case 7:
			pickLCard = "blueCard";
			break;
		default:
			System.out.println("Invalid option");
			break;
		}

		boolean moveState = player.spendFavorTokens(gameEngine.favorTokens,
				gameEngine.lanternCards, returnLCard, pickLCard);
		if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out
					.print("unSuccessful Exchange. Make sure you get the rules right, Mr Random.");
	}

	protected void makeDedication(GameEngine gameEngine, Player player)
			throws NumberFormatException, IOException {
		int move = 0;
		String regex = "\\d+";
		String option = "";
		Scanner in = new Scanner(System.in);
		//
		System.out.println("This is the amount of tokens you have: "
				+ player.favorTokenScore);
		System.out.println("----------------------------");
		System.out.println("--Lantern cards you currently have:--");

		// Prints the number of black Cards the player has.
		System.out.println(player.getLanternCards());

		System.out.println("----------------------------");

		System.out
				.println("What Move do you want to make? Enter its corresponding integer");

		System.out.println("1:Three Pair 2:Four of a kind 3: Seven Unique");

		int min = 1;
		int max = 2;
		int choice = RandomNumberGenerator(min, max);

		if (fourKind && !sevenUnique && !threePair) {

			move = 2;
		} else if (!fourKind && sevenUnique && !threePair) {

			move = 3;
		} else if (!fourKind && !sevenUnique && threePair) {

			move = 1;

		} else if (fourKind && sevenUnique && threePair) {

			int minChoice = 1;
			int maxChoice = 3;
			move = RandomNumberGenerator(minChoice, maxChoice);

		} else if (fourKind && sevenUnique && !threePair) {

			if (choice == 1)
				move = 2;
			else
				move = 3;

		} else if (fourKind && !sevenUnique && threePair) {

			if (choice == 1)
				move = 1;
			else
				move = 2;

		} else if (!fourKind && sevenUnique && threePair) {

			if (choice == 1)
				move = 3;
			else
				move = 1;
		}

		System.out.println(move);
		String moveString = null;
		LanternCards returnedLanternCards = null;
		boolean state = false;
		if (move == 1) {
			moveString = "threePair";
			System.out
					.println("What Lantern cards do you want to return? Enter "
							+ "the three cards.");
			System.out.println("1: redCard 2: blueCard 3: greenCard 4: "
					+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

			// neeed to change this part.

			int card = threePairCard1;
			int card2 = threePairCard2;
			int card3 = threePairCard3;

			System.out.println(card);
			System.out.println(card2);
			System.out.println(card3);

			if (player.getLanternCards().CardCount(card) >= 2
					&& player.getLanternCards().CardCount(card2) >= 2
					&& player.getLanternCards().CardCount(card3) >= 2) {

				CardToReturn cardToReturn = new CardToReturn(card, card2, card3);

				returnedLanternCards = cardToReturn.returnStackThreeOfKind();
				System.out.println("these are the cards to be returned 3:"
						+ returnedLanternCards.CardCount(card3));
				System.out.println("these are the cards to be returned 2:"
						+ returnedLanternCards.CardCount(card2));

				System.out.println("these are the cards to be returned 1:"
						+ returnedLanternCards.CardCount(card));

				// take 2 cards with the first color inserted
				// from player
				player.getLanternCards().getCard(cardToReturn.getColor());
				player.getLanternCards().getCard(cardToReturn.getColor());

				// take 2 cards with the second color inserted
				// from player
				player.getLanternCards().getCard(cardToReturn.getColor2());
				player.getLanternCards().getCard(cardToReturn.getColor2());

				// take 2 cards with the third color inserted
				// from player
				player.getLanternCards().getCard(cardToReturn.getColor3());
				player.getLanternCards().getCard(cardToReturn.getColor3());
			} else {
				System.out
						.println("You do not have enough cards to make 'Three pair' move");
			}

		} else if (move == 2) {
			moveString = "FourOfKind";
			System.out.println("What Lantern cards do you want to return?"
					+ " Enter the card");
			System.out.println("1: redCard 2: blueCard 3: greenCard 4: "
					+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

			// neeed to change this part.

			System.out.println(cardFourKind);

			int card = cardFourKind;
			if (player.getLanternCards().CardCount(card) >= 4) {

				CardToReturn cardToReturn = new CardToReturn(card);
				returnedLanternCards = cardToReturn.returnStackFourOfKind();

				// take 4 cards with the third color inserted
				// from player
				player.getLanternCards().getCard(cardToReturn.getColor());
				player.getLanternCards().getCard(cardToReturn.getColor());
				player.getLanternCards().getCard(cardToReturn.getColor());
				player.getLanternCards().getCard(cardToReturn.getColor());
			} else {
				System.out
						.println("You do not have enough cards to make 'Four of a kind' move");
			}

		} else if (move == 3) {
			moveString = "sevenUnique";

			CardToReturn cardToReturn = new CardToReturn();
			if (cardToReturn.SevenUniqueState(player)) {
				returnedLanternCards = cardToReturn.returnSeveUnique();

			} else {
				System.out
						.println("You do not have enough cards to make 'Seven Unique' move");
			}
		} else if (move >= 4) {
			System.out.println("Invalid option");
		}
		if (returnedLanternCards != null) {
			state = player.pickDedicationToken(moveString,
					returnedLanternCards, gameEngine.lanternCards,
					gameEngine.dedicationTokens);
		}
		if (state) {
			System.out.println("Picked!");
			System.out.println("Score: four of a kind "
					+ player.playerScore_fourKind);
			System.out.println("Score: three of a kind "
					+ player.playerScore_threePair);
			System.out.println("Score: Seven unique "
					+ player.playerScore_sevenUnique);
		} else
			System.out.println(" Please revisit the game rules!");
	}

	public void placeLakeTile(GameEngine gameEngine, Player player)
			throws NumberFormatException, IOException {
		//
		if (player.playerLTStack.size() == 0) {
			System.out.println("No lake tile available to play");
			return;
		}

		int degreeOfRotation = 0, indexOption = 0;
		String option = "";
		String regex = "\\d+";
		boolean check = true;

		// board
		gameEngine.board.displayBoard();
		System.out.println();

		// player
		player.displayPlayersLakeTile(player);
		System.out.println();

		System.out
				.println("Enter the index of laketiles you want to put on board:");
		
		// gets the random index of the player's cards.
		indexMax = player.playerLTStack.size()-1;
		randomIndex = RandomNumberGenerator(indexMin, indexMax);
		System.out.println("Index chosen: " + randomIndex);
		// picks the card from player stack
		LakeTiles currentTileToPlace = player.placeLakeTile(randomIndex);

		boolean flag = true;
		boolean placeLakeTile = false;

		while (flag) {
			System.out
					.println("Enter the id of the adjacent tile (on board) where you want to place your LakeTile");

			gameEngine.board.tilesOnBoard.size();

			// randomly picking the laketile position from available spaces.

			ArrayList<String> availableSpace = gameEngine.board
					.availableSpaces();
			int maxRandPos = availableSpace.size() - 1;
			int minRandPos = 0;

			// getting the string that contains the position.
			// example: "3 left"; 3 - id left - string
			int randPos = RandomNumberGenerator(minRandPos, maxRandPos);

			String lTPosition = availableSpace.get(randPos);

			String[] parts = lTPosition.split(" ");
			int id = Integer.valueOf(parts[0]);
			String AdjacentPosition = parts[1];
			System.out.println("ID Chosen:" + id);
			System.out
					.println("Enter the adjacent position (right, left, up, down)");
			System.out.println("adjacent position Chosen:" + AdjacentPosition);

			int GetColumn = gameEngine.lakeTiles.getColumn(gameEngine.board,
					id, AdjacentPosition);
			int GetRow = gameEngine.lakeTiles.getRow(gameEngine.board, id,
					AdjacentPosition);

			System.out
					.println("Enter the degree of roatation for the tile you want to place on board");
			System.out.println("Available options 0 90 180 270");

			int minRandDegree = 1;
			int maxRandDegree = 4;
			int randDegree = RandomNumberGenerator(minRandDegree, maxRandDegree);

			if (randDegree == 1)
				degreeOfRotation = 0;
			if (randDegree == 2)
				degreeOfRotation = 90;
			if (randDegree == 3)
				degreeOfRotation = 180;
			if (randDegree == 4)
				degreeOfRotation = 270;

			System.out.println("Degree Chosen:" + degreeOfRotation);

			currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
					currentTileToPlace, degreeOfRotation);

			placeLakeTile = gameEngine.lakeTiles.placeTile(GetColumn, GetRow,
					gameEngine.board, currentTileToPlace);
			if (placeLakeTile) {
				gameEngine.lanternCards.assignLanternCardsToPlayers(
						gameEngine.numOfPlayer, gameEngine.board, GetColumn,
						GetRow, currentTileToPlace, gameEngine.PlayerList,
						gameEngine.lanternCards, gameEngine.favorTokens);
				gameEngine.board.displayBoard();
				player.displayPlayersLakeTile(player);
				System.out.println();
				System.out.println("Number of FavorTokens:"
						+ player.favorTokenScore);
				System.out.println();
				System.out
						.println("Details of the LanternCards Assigned to Each Player After Placing the LakeTile");
				for (int i = 0; i < gameEngine.PlayerList.size(); i++) {
					System.out.println("Player" + (i + 1) + ":");
					System.out
							.println(gameEngine.PlayerList.get(i).playerLCStack);
					System.out.println();
				}

				flag = false;
			} else
				player.pickLakeTileFromStack(currentTileToPlace);

			System.out.println();
			System.out
					.println("Player Pick up the new LakeTile from the Stack after placing one");
		}
	}

	public int RandomNumberGenerator(int min, int max) {
		// generate Random price
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int RandomNum = rand.nextInt((max - min) + 1) + min;
		return RandomNum;
	}

	/**
	 * This method checks whether FourOfKind Dedication is possible for
	 * greedyPlayer and does it if possible
	 * 
	 * @param player
	 *            current player
	 * @param gameEngine
	 *            contains the state of entire game
	 * @return state if dedication is successful
	 */
	public boolean checkFourOfKindDedication(Player player,
			GameEngine gameEngine) {

		boolean flag = false, state = false;
		LanternCards returnedLanternCards = null;
		String moveString = "FourOfKind";
		if (player.getLanternCards().blackCardCount() >= 4) {
			cardFourKind = 6;
			flag = true;
		} else if (player.getLanternCards().blueCardCount() >= 4) {
			flag = true;
			cardFourKind = 2;
		} else if (player.getLanternCards().whiteCardCount() >= 4) {
			flag = true;
			cardFourKind = 4;
		} else if (player.getLanternCards().purpleCardCount() >= 4) {
			flag = true;
			cardFourKind = 5;
		} else if (player.getLanternCards().greenCardCount() >= 4) {
			flag = true;
			cardFourKind = 3;
		} else if (player.getLanternCards().orangeCardCount() >= 4) {
			flag = true;
			cardFourKind = 7;
		} else if (player.getLanternCards().redCardCount() >= 4) {
			flag = true;
			cardFourKind = 1;
		}
		return flag;
	}

	/**
	 * This method checks whether ThreePair Dedication is possible for
	 * greedyPlayer and does it if possible
	 * 
	 * @param player
	 *            current player
	 * @param gameEngine
	 *            contains the state of entire game
	 * @return state if dedication is successful
	 */
	public boolean checkThreePairDedication(Player player, GameEngine gameEngine) {

		boolean state = false;
		threePairCard1=0;
		threePairCard2=0;
		threePairCard3=0;
		
		if (player.getLanternCards().blackCardCount() >= 2) {
			threePairCard1 = 6;
		}
		if (player.getLanternCards().blueCardCount() >= 2) {
			if (threePairCard1 != 0)
				threePairCard2 = 2;
			else
				threePairCard1 = 2;
		}
		
		if (player.getLanternCards().whiteCardCount() >= 2) {
			if (threePairCard1 != 0) {
				if (threePairCard2 != 0) {
					threePairCard3 = 4;
				} else
					threePairCard2 = 4;
			} else
				threePairCard1 = 4;
		}
		if (player.getLanternCards().redCardCount() >= 2) {
			if (threePairCard1 != 0) {
				if (threePairCard2 != 0) {
					threePairCard3 = 1;
				} else
					threePairCard2 = 1;
			} else
				threePairCard1 = 1;
		}
		if (player.getLanternCards().greenCardCount() >= 2) {
			if (threePairCard1 != 0) {
				if (threePairCard2 != 0) {
					threePairCard3 = 3;
				} else
					threePairCard2 = 3;
			} else
				threePairCard1 = 3;
		}
		if (player.getLanternCards().purpleCardCount() >= 2) {
			if (threePairCard1 != 0) {
				if (threePairCard2 != 0) {
					threePairCard3 = 5;
				} else
					threePairCard2 = 5;
			} else
				threePairCard1 = 5;
		}
		if (player.getLanternCards().orangeCardCount() >= 2) {
			if (threePairCard1 != 0) {
				if (threePairCard2 != 0) {
					threePairCard3 = 7;
				} else
					threePairCard2 = 7;
			} else
				threePairCard1 = 7;
		}
		if (threePairCard1 != 0 && threePairCard2 != 0 && threePairCard3 != 0) {
			state = true;
		}

		return state;
	}

	/**
	 * This method checks whether SevenUnique Dedication is possible for
	 * greedyPlayer and does it if possible
	 * 
	 * @param player
	 *            current player
	 * @param gameEngine
	 *            contains the state of entire game
	 * @return state if dedication is successful
	 */
	private boolean checkSevenUniqueDedication(Player player,
			GameEngine gameEngine) {
		boolean state = false;
		String moveString = "sevenUnique";
		LanternCards returnedLanternCards = null;

		CardToReturn cardToReturn = new CardToReturn();
		if (cardToReturn.SevenUniqueState(player)) {
			returnedLanternCards = cardToReturn.returnSeveUnique();
		}
		if (returnedLanternCards != null) {
			state = player.pickDedicationToken(moveString,
					returnedLanternCards, gameEngine.lanternCards,
					gameEngine.dedicationTokens);
		}
		return state;
	}

}
