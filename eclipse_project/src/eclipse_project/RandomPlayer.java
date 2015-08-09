package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player implements PlayerStrategy {

	int indexMin = 0;
	int indexMax = 2;
	int randomIndex;

	public RandomPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void play(GameEngine gameEngine, Player player, BufferedReader br)
			throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		// Exchange lantern cards
		System.out
				.print("Type 1 if you want to exchange lantern cards, any other number to skip: ");
		int choice = Integer.parseInt(br.readLine());
		//
		if (choice == 1) {
			exchangeLanternCards(gameEngine, player, br);
		}

		// Make dedication
		System.out
				.print("Type 1 if you want to make a dedication, any other number to skip: ");
		choice = Integer.parseInt(br.readLine());
		if (choice == 1) {
			makeDedication(gameEngine, player, br);
		}

		// place lake tile
		placeLakeTile(gameEngine, player, br);

		if (gameEngine.lakeTiles.hasLakeTile()) {
			// Composury pick lake tile
			player.pickLakeTileFromStack(gameEngine.lakeTiles.getLakeTile());
			System.out.println();
			player.displayPlayersLakeTile(player);
		} else {
			System.out.println("No lake tiles left on board to pick");
		}
	}

	protected void exchangeLanternCards(GameEngine gameEngine, Player player,
			BufferedReader br) throws IOException {
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

		boolean check = true;
		while (check) {
			in = new Scanner(System.in);
			returnLCard = in.nextLine();
			returnLCard.trim();
			if (!returnLCard.matches(regex)) {
				System.out.println("Invalid lantern card option");
				check = true;
			} else {
				lanternCard = Integer.valueOf(returnLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid lantern card option");
				} else {
					check = false;
				}
			}

		}
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

		lanternCard = 0;
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n" + "2.Green Card\n" + "3.Purple Card\n"
				+ "4.Orange Card\n" + "5.White Card\n" + "6.Black Card\n"
				+ "7.Blue Card\n");

		check = true;
		while (check) {
			in = new Scanner(System.in);
			pickLCard = in.nextLine();
			pickLCard.trim();
			if (!pickLCard.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				lanternCard = Integer.valueOf(pickLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		//
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
					.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");
	}

	protected void makeDedication(GameEngine gameEngine, Player player,
			BufferedReader br) throws NumberFormatException, IOException {
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
		// TODO needs validation to be done before submitting
		// the code
		System.out
				.println("What Move do you want to make? Enter its corresponding integer");

		System.out.println("1:Three Pair 2:Four of a kind 3: Seven Unique");

		boolean check = true;

		while (check) {
			in = new Scanner(System.in);
			option = in.nextLine();
			option.trim();
			if (!option.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				move = Integer.valueOf(option);
				if (move < 1 || move > 3) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}

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

			int card = Integer.valueOf(br.readLine());
			int card2 = Integer.valueOf(br.readLine());
			int card3 = Integer.valueOf(br.readLine());

			if (player.getLanternCards().CardCount(card) >= 2
					&& player.getLanternCards().CardCount(card2) >= 2
					&& player.getLanternCards().CardCount(card3) >= 2) {

				System.out.println("This means that you succeded to "
						+ "get through the card check point");

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

			int card = Integer.valueOf(br.readLine());
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

	public void placeLakeTile(GameEngine gameEngine, Player player,
			BufferedReader br) throws NumberFormatException, IOException {
		//
		if (player.playerLTStack.size() == 0) {
			System.out.println("No lake tile available to play");
			return;
		}

		int degreeOfRotation = 0, indexOption = 0;
		String option = "";
		String regex = "\\d+";
		boolean check = true;

		Scanner in = new Scanner(System.in);

		// board
		gameEngine.board.displayBoard(gameEngine.board.board,
				gameEngine.board.tilesOnBoard);
		System.out.println();

		// player
		player.displayPlayersLakeTile(player);
		System.out.println();

		
		System.out
				.println("Choosing the index of laketile to put on board....");

		//gets the random index of the player's cards.
		randomIndex = RandomNumberGenerator(indexMin, indexMax);
		
		//picks the card from player stack
		LakeTiles currentTileToPlace = player.placeLakeTile(randomIndex);

		boolean flag = true;
		boolean placeLakeTile = false;

		while (flag) {
			System.out
					.println("Choosing the id of the adjacent tile (on board) where to place the LakeTile");
			
			gameEngine.board.tilesOnBoard.size();
			
			//randomly picking the laketile position from available spaces.
			
			ArrayList<String> availableSpace = gameEngine.board.availableSpaces();
			int maxRandPos = availableSpace.size()-1;
			int minRandPos = 0;
			
			// getting the string that contains the position.
			// example: "3 left"; 3 - id left - string
			int randPos = RandomNumberGenerator( minRandPos, maxRandPos);
			
			String lTPosition = availableSpace.get(randPos);
			
			String[] parts = lTPosition.split(" ");
			int id = Integer.valueOf(parts[0]);
			String AdjacentPosition= parts[1];
		
			System.out
					.println("Enter the adjacent position (right, left, up, down)");
			int GetColumn = gameEngine.lakeTiles.getColumn(gameEngine.board,
					id, AdjacentPosition);
			int GetRow = gameEngine.lakeTiles.getRow(gameEngine.board, id,
					AdjacentPosition);
	

			System.out
					.println("Choosing the degree of rotation for the tile you want to place on board....");
			
			
			int minRandDegree =1;
			int maxRandDegree =4;
			int randDegree = RandomNumberGenerator(minRandDegree, maxRandDegree);

			if(randDegree ==1)
				degreeOfRotation =0;
			if(randDegree ==2)
				degreeOfRotation = 90;
			if(randDegree ==3)
				degreeOfRotation = 180;
			if(randDegree ==4)
				degreeOfRotation = 270;
			
			

			currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
					currentTileToPlace, degreeOfRotation);

			placeLakeTile = gameEngine.lakeTiles.placeTile(GetColumn, GetRow,
					gameEngine.board, currentTileToPlace);
			if (placeLakeTile) {
				gameEngine.lanternCards.assignLanternCardsToPlayers(
						gameEngine.numOfPlayer, gameEngine.board, GetColumn,
						GetRow, currentTileToPlace, gameEngine.PlayerList,
						gameEngine.lanternCards, gameEngine.favorTokens);
				gameEngine.board.displayBoard(gameEngine.board.board,
						gameEngine.board.tilesOnBoard);
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

	@Override
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		
	}

}
