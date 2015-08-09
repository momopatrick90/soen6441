package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class GreedyPlayer implements PlayerStrategy
{

	public GreedyPlayer(String name) {
		//super(name);
		// TODO Auto-generated constructor stub
	}


	

	/**
	 * This method performs the exchange Lantern cards operation for greedy player
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	protected void exchangeLanternCards(GameEngine gameEngine, Player player)
	{
		
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		
		// Print lanterns
		System.out.println("--Lantern cards you currently have:--");
		System.out.println(player.getLanternCards());
		System.out.println("----------------------------");
		
		
		int peekFourOfKind = gameEngine.dedicationTokens.peekFourOfKind();
		int peekThreePairs = gameEngine.dedicationTokens.peekThreePairs();
		int peekSevenUnique = gameEngine.dedicationTokens.peekSevenUnique();
		
		 int values[] = {peekFourOfKind,peekThreePairs,peekSevenUnique};
		 String key[] ={"FourOfKind","ThreePair","SevenUnique"};
		 for(int i=0;i<values.length-1;i++)
			{
				for(int j=0;j<values.length-1;j++)
				{
					int temp=0;
					String temp1="";
					if(values[j]>values[j+1])
					{
						temp=values[j];
						values[j]=values[j+1];
						values[j+1]=temp;
						temp1=key[j];
						key[j]=key[j+1];
						key[j+1]=temp1;
					}
				}
			}
		 boolean exchanged=false;
		one:for(int i=0;i<key.length;i++)
		 {
			 String x=key[i];
			 if(x.equalsIgnoreCase("FourOfKind"))
			 {
				 exchanged=checkFourOfKind(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("ThreePair"))
			 {
				 exchanged=checkThreePair(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("SevenUnique"))
			 {
				 exchanged=checkSevenUnique(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
		 }	
	}
	
	/**
	 * This method performs the greedy dedication for computer player
	 * @param gameEngine contains the entire state of the game
	 * @param player current player 
	 */
	protected void makeDedication(GameEngine gameEngine, Player player)
	{
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		System.out.println("--Lantern cards you currently have:--");

		// Prints the number of black Cards the player has.
		System.out.println(player.getLanternCards());

		System.out.println("----------------------------");
		
		int peekFourOfKind = gameEngine.dedicationTokens.peekFourOfKind();
		int peekThreePairs = gameEngine.dedicationTokens.peekThreePairs();
		int peekSevenUnique = gameEngine.dedicationTokens.peekSevenUnique();
		
		 int values[] = {peekFourOfKind,peekThreePairs,peekSevenUnique};
		 String key[] ={"FourOfKind","ThreePair","SevenUnique"};
		 for(int i=0;i<values.length-1;i++)
			{
				for(int j=0;j<values.length-1;j++)
				{
					int temp=0;
					String temp1="";
					if(values[j]>values[j+1])
					{
						temp=values[j];
						values[j]=values[j+1];
						values[j+1]=temp;
						temp1=key[j];
						key[j]=key[j+1];
						key[j+1]=temp1;
					}
				}
			}
		 boolean exchanged=false;
		one:for(int i=0;i<key.length;i++)
		 {
			 String x=key[i];
			 if(x.equalsIgnoreCase("FourOfKind"))
			 {
				 exchanged=checkFourOfKindDedication(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("ThreePair"))
			 {
				 exchanged=checkThreePairDedication(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("SevenUnique"))
			 {
				 exchanged=checkSevenUniqueDedication(player,gameEngine);
				 if(exchanged)
					 break one;
			 }
		 }			
		 if(exchanged)
		 {
			 System.out.println("Picked!");
				System.out.println("Score: four of a kind "
								+ player.playerScore_fourKind);
				System.out
						.println("Score: three of a kind "
								+ player.playerScore_threePair);
				System.out
						.println("Score: Seven unique "
								+ player.playerScore_sevenUnique);
		 }
		 else
			 System.out.println(" Please revisit the game rules!");
	}
	
	/**
	 * This method places lakeTile for greedy Player
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player)
	{
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("No lake tile available to play");
			return;
		}
		
		int degreeOfRotation = 0,indexOption =0;
		String option = "";
		String regex = "\\d+";
		boolean check = true;
		// board
		gameEngine.board.displayBoard(gameEngine.board.board, gameEngine.board.tilesOnBoard);
		System.out.println();
		
		// player
		player.displayPlayersLakeTile(player);
		System.out.println();
		
		//
		System.out.println("Enter the index of laketiles you want to put on board:");
		
		check = true;
		
		ArrayList<String> availableSpaces=new ArrayList();
		availableSpaces = gameEngine.board.availableSpaces();
		for(int i=0;i<availableSpaces.size();i++)
		{
			String lTPosition = availableSpaces.get(i);
			String[] parts = lTPosition.split(" ");
			int id = Integer.valueOf(parts[0]);
			String AdjacentPosition= parts[1];
		}

		LakeTiles currentTileToPlace = player.placeLakeTile(indexOption);

		//
		boolean flag = true;
		boolean placeLakeTile = false;
	
		//
		/*while (flag) {
			System.out
			.println("Enter the id of the adjacent tile (on board) where you want to place your LakeTile");
			//int id = Integer.parseInt(br.readLine());
			
			System.out
			.println("Enter the adjacent position (right, left, up, down)");
			String AdjacentPosition = br.readLine();
			int GetColumn=gameEngine.lakeTiles.getColumn(gameEngine.board,id,AdjacentPosition);
			int GetRow=gameEngine.lakeTiles.getRow(gameEngine.board,id,AdjacentPosition);
			//System.out.println("MyColumn "+GetColumn+" row "+GetRow);
	
			System.out
					.println("Enter the degree of roatation for the tile you want to place on board");
			System.out.println("Available options 0 90 180 270");
			
			check = true;
			while (check) {
				//in = new Scanner(System.in);
				//option = in.nextLine();
				option.trim();
				if (!option.matches(regex)) {
					System.out.println("Invalid degree. Enter again!");
					check = true;
				} else {
					degreeOfRotation = Integer.valueOf(option);
					if (degreeOfRotation == 0 || degreeOfRotation==90 || degreeOfRotation==180 || degreeOfRotation==270 ) {
						check = false;
					} else {
						System.out.println("Invalid degree. Enter again!");
					}
				}

			}
			
			currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
					currentTileToPlace, degreeOfRotation);

			placeLakeTile = gameEngine.lakeTiles.placeTile(GetColumn,
					GetRow, gameEngine.board, currentTileToPlace);
			if (placeLakeTile) {
				gameEngine.lanternCards.assignLanternCardsToPlayers(
						gameEngine.numOfPlayer, gameEngine.board,
						GetColumn,GetRow, currentTileToPlace,
							gameEngine.PlayerList, gameEngine.lanternCards, gameEngine.favorTokens);
								gameEngine.board.displayBoard(gameEngine.board.board,
										gameEngine.board.tilesOnBoard);
				player.displayPlayersLakeTile(player);
				System.out.println();
				System.out
						.println("Number of FavorTokens:"
								+ player.favorTokenScore);
				System.out.println();
				System.out
						.println("Details of the LanternCards Assigned to Each Player After Placing the LakeTile");
				for (int i = 0; i < gameEngine.PlayerList.size(); i++) {
					System.out
							.println("Player" + (i + 1) + ":");
					System.out
							.println(gameEngine.PlayerList.get(i).playerLCStack);
					System.out.println();
				}
				
				flag = false;
			} else
				player.pickLakeTileFromStack(currentTileToPlace);
			
			
			System.out.println();
			System.out.println("Player Pick up the new LakeTile from the Stack after placing one");
		}*/
	}

	/**
	 * This method checks which lantern cards should be exchanged to perform Dedication Seven Unique and performs the exchange
	 * @param player player playing the current move
	 * @param gameEngine contains the state of entire game
	 * @return moveState returns true if exchange lanternCard is successful for making Sevenunique
	 */
	public boolean checkSevenUnique(Player player,GameEngine gameEngine)
	{
		String returnLCard = "";
		String pickLCard = "";
		int numberOfCardsNeeded=0;
		boolean moveState=false;		
				if(player.getLanternCards().blackCardCount()==0)
				{
					 pickLCard="blackCard";	
					 numberOfCardsNeeded++;
				}
				if(player.getLanternCards().blueCardCount()==0)
				{
					 pickLCard="blueCard";	
					 numberOfCardsNeeded++;
				}
			    if(player.getLanternCards().greenCardCount()==0)
				{
					 pickLCard="greenCard";	
					 numberOfCardsNeeded++;
				}
				if(player.getLanternCards().whiteCardCount()==0)
				{
					 pickLCard="whiteCard";	
					 numberOfCardsNeeded++;
				}
			    if(player.getLanternCards().purpleCardCount()==0)
				{
					 pickLCard="purpleCard";	
					 numberOfCardsNeeded++;
				}
			    if(player.getLanternCards().orangeCardCount()==0)
				{
					 pickLCard="orangeCard";	
					 numberOfCardsNeeded++;
				}
				if(player.getLanternCards().redCardCount()==0)
				{
					 pickLCard="redCard";	
					 numberOfCardsNeeded++;
				}
				if(numberOfCardsNeeded==0)
					 pickLCard="NotPossible";	
				if(numberOfCardsNeeded==1)
				{
					if(player.getLanternCards().blackCardCount()>1)
						returnLCard="blackCard";
					else if(player.getLanternCards().blueCardCount()>1)
						returnLCard="blueCard";
					else if(player.getLanternCards().whiteCardCount()>1)
						returnLCard="whiteCard";
					else if(player.getLanternCards().purpleCardCount()>1)
						returnLCard="purpleCard";
					else if(player.getLanternCards().redCardCount()>1)
						returnLCard="redCard";
					else if(player.getLanternCards().greenCardCount()>1)
						returnLCard="greenCard";
					else if(player.getLanternCards().orangeCardCount()>1)
						returnLCard="orangeCard";
					else
						returnLCard="NotPossible";
				}
				if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
				{
					System.out.println("Exchange Lantern card for SevenUnique is not possible right now");
				}
				
				if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
				{
							moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
							returnLCard, pickLCard);
				}
				
		/*if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");*/
		return moveState;
	}

	/**
	 * This method checks which lantern cards should be exchanged to perform Dedication Four Of Kind and performs the exchange
	 * @param player player playing the current move
	 * @param gameEngine contains the state of entire game
	 * @return moveState returns true if exchange lanternCard is successful for making FourOfKind
	 */
	public boolean checkFourOfKind(Player player, GameEngine gameEngine) {
		String returnLCard = "";
		String pickLCard = "";
		boolean moveState=false;
		if(player.getLanternCards().blackCardCount()==3 && gameEngine.lanternCards.hasCard("blackCard"))
		{
			pickLCard="blackCard";
		}
		else if(player.getLanternCards().blueCardCount()==3 && gameEngine.lanternCards.hasCard("blueCard"))
		{
			pickLCard="blueCard";
		}
		else if(player.getLanternCards().greenCardCount()==3 && gameEngine.lanternCards.hasCard("greenCard"))
		{
			pickLCard="greenCard";
		}
		else if(player.getLanternCards().redCardCount()==3 && gameEngine.lanternCards.hasCard("redCard"))
		{
			pickLCard="redCard";
		}
		else if(player.getLanternCards().whiteCardCount()==3 && gameEngine.lanternCards.hasCard("whiteCard"))
		{
			pickLCard="whiteCard";
		}
		else if(player.getLanternCards().orangeCardCount()==3 && gameEngine.lanternCards.hasCard("orangeCard"))
		{
			pickLCard="orangeCard";
		}
		else if(player.getLanternCards().purpleCardCount()==3 && gameEngine.lanternCards.hasCard("purpleCard"))
		{
			pickLCard="purpleCard";
		}
		else
			pickLCard="NotPossible";
		
		if(player.getLanternCards().blackCardCount()<3 && player.getLanternCards().blackCardCount()>0)
			returnLCard="blackCard";
		else if(player.getLanternCards().blueCardCount()<3 && player.getLanternCards().blueCardCount()>0)
			returnLCard="blueCard";
		else if(player.getLanternCards().whiteCardCount()<3 && player.getLanternCards().whiteCardCount()>0)
			returnLCard="whiteCard";
		else if(player.getLanternCards().orangeCardCount()<3 && player.getLanternCards().orangeCardCount()>0)
			returnLCard="orangeCard";
		else if(player.getLanternCards().greenCardCount()<3 && player.getLanternCards().greenCardCount()>0)
			returnLCard="greenCard";
		else if(player.getLanternCards().redCardCount()<3 && player.getLanternCards().redCardCount()>0)
			returnLCard="redCard";
		else if(player.getLanternCards().purpleCardCount()<3 && player.getLanternCards().purpleCardCount()>0)
			returnLCard="purpleCard";
		else
			returnLCard="NotPossible";
		if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
		{
			System.out.println("Exchange Lantern card for SevenUnique is not possible right now");
		}
		
		if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
		{
					moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
					returnLCard, pickLCard);
		}
		
			/*if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");*/
	return moveState;
	}
	
	/**
	 * This method checks which lantern cards should be exchanged to perform Dedication Three Pair and performs the exchange
	 * @param player player playing the current move
	 * @param gameEngine contains the state of entire game
	 * @return moveState returns true if exchange lanternCard is successful for making ThreePair
	 */
	public boolean checkThreePair(Player player, GameEngine gameEngine)
	{
		String returnLCard = "";
		String pickLCard = "";
		boolean moveState=false;
		int numberOfPairs=0;
	    HashMap<String, Boolean> pairs = new HashMap<String, Boolean>();
	    pairs.put("redCard", false);
		pairs.put("blueCard", false);
		pairs.put("greenCard", false);
		pairs.put("whiteCard", false);
		pairs.put("purpleCard", false);
		pairs.put("blackCard", false);
		pairs.put("orangeCard", false);

		if(player.getLanternCards().blackCardCount()==2)
		{
			pairs.put("blackCard", true);
			numberOfPairs++;
		}
		if(player.getLanternCards().blueCardCount()==2)
		{
			pairs.put("blueCard", true);
			numberOfPairs++; 
		}
		if(player.getLanternCards().whiteCardCount()==2)
		{
			if(numberOfPairs!=2)
			pairs.put("whiteCard", true);
			numberOfPairs++; 
		}
		if(player.getLanternCards().redCardCount()==2)
		{
			if(numberOfPairs!=2)
				pairs.put("redCard", true);
			numberOfPairs++; 
		}
		if(player.getLanternCards().greenCardCount()==2)
		{
			if(numberOfPairs!=2)
				pairs.put("greenCard", true);
			numberOfPairs++; 
		}
		if(player.getLanternCards().orangeCardCount()==2)
		{
			if(numberOfPairs!=2)
				pairs.put("orangeCard", true);
			numberOfPairs++; 
		}
		if(player.getLanternCards().purpleCardCount()==2)
		{
			if(numberOfPairs!=2)
				pairs.put("purpleCard", true);
			numberOfPairs++; 
		}
		if(numberOfPairs==2)
		{
			if(player.getLanternCards().blackCardCount()==1 && gameEngine.lanternCards.hasCard("blackCard"))
			{
				pairs.put("blackCard", true);
				pickLCard="blackCard";
			}
			else if(player.getLanternCards().redCardCount()==1 && gameEngine.lanternCards.hasCard("redCard"))
			{
				pairs.put("redCard", true);
				pickLCard="redCard";
			}
			else if(player.getLanternCards().purpleCardCount()==1 && gameEngine.lanternCards.hasCard("purpleCard"))
			{
				pairs.put("purpleCard", true);
				pickLCard="purpleCard";
			}
			else if(player.getLanternCards().greenCardCount()==1 && gameEngine.lanternCards.hasCard("greenCard"))
			{
				pairs.put("greenCard", true);
				pickLCard="greenCard";
			}
			else if(player.getLanternCards().whiteCardCount()==1 && gameEngine.lanternCards.hasCard("whiteCard"))
			{
				pairs.put("whiteCard", true);
				pickLCard="whiteCard";
			}
			else if(player.getLanternCards().orangeCardCount()==1 && gameEngine.lanternCards.hasCard("orangeCard"))
			{
				pairs.put("orangeCard", true);
				pickLCard="orangeCard";
			}
			else if(player.getLanternCards().blueCardCount()==1 && gameEngine.lanternCards.hasCard("blueCard"))
			{
				pairs.put("blueCard", true);
				pickLCard="blueCard";
			}
		}
		
		if(pairs.get("blackCard")!=true && player.getLanternCards().blackCardCount()>1)
		{
			returnLCard="blackCard";
		}
		else if(pairs.get("redCard")!=true && player.getLanternCards().redCardCount()>1)
		{
			returnLCard="redCard";
		}
		else if(pairs.get("blueCard")!=true && player.getLanternCards().blueCardCount()>1)
		{
			returnLCard="blueCard";
		}
		else if(pairs.get("greenCard")!=true && player.getLanternCards().greenCardCount()>1)
		{
			returnLCard="greenCard";
		}
		else if(pairs.get("orangeCard")!=true && player.getLanternCards().orangeCardCount()>1)
		{
			returnLCard="orangeCard";
		}
		else if(pairs.get("purpleCard")!=true && player.getLanternCards().purpleCardCount()>1)
		{
			returnLCard="purpleCard";
		}
		else if(pairs.get("whiteCard")!=true && player.getLanternCards().whiteCardCount()>1)
		{
			returnLCard="whiteCard";
		}
		else
			returnLCard="NotPossible";
		if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
		{
			System.out.println("Exchange Lantern card for SevenUnique is not possible right now");
		}
		
		if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
		{
					moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
					returnLCard, pickLCard);
		}
		
			/*if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");*/
	return moveState;
	}
	
	

	/**
	 * This method checks whether FourOfKind Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @return state if dedication is successful
	 */
	public boolean checkFourOfKindDedication(Player player,
			GameEngine gameEngine) {
		int card=0;
		boolean flag=false,state=false;
		LanternCards returnedLanternCards = null;
		String moveString = "FourOfKind";
		if(player.getLanternCards().blackCardCount()>=4)
		{
			card=6;
			flag=true;
		}
		else if(player.getLanternCards().blueCardCount()>=4)
		{
			flag=true;
			card=2;
		}
		else if(player.getLanternCards().whiteCardCount()>=4)
		{
			flag=true;
			card=4;
		}
		else if(player.getLanternCards().purpleCardCount()>=4)
		{
			flag=true;
			card=5;
		}
		else if(player.getLanternCards().greenCardCount()>=4)
		{
			flag=true;
			card=3;
		}
		else if(player.getLanternCards().orangeCardCount()>=4)
		{
			flag=true;
			card=7;
		}
		else if(player.getLanternCards().redCardCount()>=4)
		{
			flag=true;
			card=1;
		}
		CardToReturn cardToReturn = new CardToReturn(
				card);
		returnedLanternCards = cardToReturn
				.returnStackFourOfKind();

		// take 4 cards with the third color inserted
		// from player
		player.getLanternCards()
				.getCard(cardToReturn.getColor());
		player.getLanternCards()
				.getCard(cardToReturn.getColor());
		player.getLanternCards()
				.getCard(cardToReturn.getColor());
		player.getLanternCards()
				.getCard(cardToReturn.getColor());
		if (returnedLanternCards != null) {
			state = player.pickDedicationToken(moveString,
							returnedLanternCards, gameEngine.lanternCards,
							gameEngine.dedicationTokens);
		}
		return state;
	}
	
	/**
	 * This method checks whether ThreePair Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @return state if dedication is successful
	 */
	public boolean checkThreePairDedication(Player player,
			GameEngine gameEngine) {
		int card=0,card1=0,card2=0;
		boolean state=false;
		LanternCards returnedLanternCards = null;
		String moveString = "threePair";
		
		if(player.getLanternCards().blackCardCount()>=2)
		{
			card=6;
		}
		if(player.getLanternCards().blueCardCount()>=2)
		{
			if(card!=0)
				card1=2;
			else
				card=2;
		}
		if(player.getLanternCards().whiteCardCount()>=2)
		{
			if(card!=0)
			{
				if(card1!=0)
				{
					card2=4;
				}
				else
					card1=4;
			}
			else
				card=4;
		}
		if(player.getLanternCards().redCardCount()>=2)
		{
			if(card!=0)
			{
				if(card1!=0)
				{
					card2=1;
				}
				else
					card1=1;
			}
			else
				card=1;
		}
		if(player.getLanternCards().greenCardCount()>=2)
		{
			if(card!=0)
			{
				if(card1!=0)
				{
					card2=3;
				}
				else
					card1=3;
			}
			else
				card=3;
		}
		if(player.getLanternCards().purpleCardCount()>=2)
		{
			if(card!=0)
			{
				if(card1!=0)
				{
					card2=5;
				}
				else
					card1=5;
			}
			else
				card=5;
		}
		if(player.getLanternCards().orangeCardCount()>=2)
		{
			if(card!=0)
			{
				if(card1!=0)
				{
					card2=7;
				}
				else
					card1=7;
			}
			else
				card=7;
		}
		if(card!=0 && card1!=0 && card2!=0)
		{
			CardToReturn cardToReturn = new CardToReturn(
					card, card1, card2);
	
			returnedLanternCards = cardToReturn
					.returnStackThreeOfKind();
			System.out
					.println("these are the cards to be returned 3:"
							+ returnedLanternCards
									.CardCount(card2));
			System.out
					.println("these are the cards to be returned 2:"
							+ returnedLanternCards
									.CardCount(card1));
	
			System.out
					.println("these are the cards to be returned 1:"
							+ returnedLanternCards
									.CardCount(card));
			// take 2 cards with the first color inserted
			// from player
			player.getLanternCards()
					.getCard(cardToReturn.getColor());
			player.getLanternCards()
					.getCard(cardToReturn.getColor());

			// take 2 cards with the second color inserted
			// from player
			player.getLanternCards()
					.getCard(cardToReturn.getColor2());
			player.getLanternCards()
					.getCard(cardToReturn.getColor2());

			// take 2 cards with the third color inserted
			// from player
			player.getLanternCards()
					.getCard(cardToReturn.getColor3());
			player.getLanternCards()
					.getCard(cardToReturn.getColor3());
		}
		if (returnedLanternCards != null) {
			state = player.pickDedicationToken(moveString,
							returnedLanternCards, gameEngine.lanternCards,
							gameEngine.dedicationTokens);
		}
		return state;
	}
		
	/**
	 * This method checks whether SevenUnique Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @return state if dedication is successful
	 */
	private boolean checkSevenUniqueDedication(Player player,
			GameEngine gameEngine) {
			boolean state=false;
			String moveString = "sevenUnique";
			LanternCards returnedLanternCards = null;
			
			CardToReturn cardToReturn = new CardToReturn();
			if (cardToReturn.SevenUniqueState(player)) {
				returnedLanternCards = cardToReturn
						.returnSeveUnique();
			} 	
			if (returnedLanternCards != null) {
				state = player.pickDedicationToken(moveString,
								returnedLanternCards, gameEngine.lanternCards,
								gameEngine.dedicationTokens);
			}
		return state;
	}

	@Override
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		
	}

	
}
