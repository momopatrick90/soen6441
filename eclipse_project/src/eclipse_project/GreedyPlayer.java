package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class GreedyPlayer implements PlayerStrategy
{

	/**
	 * This is GreedyPlayer Constructor
	 * @param name name of the player
	 */
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
					if(values[j]<values[j+1])
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
				 {
					 break one;
				 }
			 }
			 if(x.equalsIgnoreCase("ThreePair"))
			 {
				 exchanged=checkThreePair(player,gameEngine);
				 if(exchanged)
				 {
					 break one;
				 }
			 }
			 if(x.equalsIgnoreCase("SevenUnique"))
			 {
				 exchanged=checkSevenUnique(player,gameEngine);
				 if(exchanged)
				 {
					 break one;
				 }
			 }
		 }
		 if (exchanged)
				System.out.println("Successful Exchange");
			else
				System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");
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
		// TODO needs validation to be done before submitting
		// the code
		System.out.println("What Move do you want to make? Enter its corresponding integer");

		System.out.println("1:Three Pair 2:Four of a kind 3: Seven Unique 4:None" );
		
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
					if(values[j]<values[j+1])
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
		 boolean simulation=false;
		one:for(int i=0;i<key.length;i++)
		 {
			 String x=key[i];
			 if(x.equalsIgnoreCase("FourOfKind"))
			 {
				 exchanged=checkFourOfKindDedication(player,gameEngine,simulation);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("ThreePair"))
			 {
				 exchanged=checkThreePairDedication(player,gameEngine,simulation);
				 if(exchanged)
					 break one;
			 }
			 if(x.equalsIgnoreCase("SevenUnique"))
			 {
				 exchanged=checkSevenUniqueDedication(player,gameEngine,simulation);
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
				System.out.println(4);
	}
	
	/**
	 * <p>This method places lakeTile for greedy Player such that it can make best possible dedication in next move or 
	 * gets maximum number of lantern cards that leads to dedication and if number of cards are same then it checks
	 * maximum favor Token.</p>
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player)
	{
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
		
		//player
		player.displayPlayersLakeTile(player);
		System.out.println();
		
		check = true;

		int blackCount= gameEngine.lanternCards.blackCardCount();
		int redCount=  gameEngine.lanternCards.redCardCount();
		int greenCount=gameEngine.lanternCards.greenCardCount();
		int purpleCount=gameEngine.lanternCards.purpleCardCount();
		int orangeCount=gameEngine.lanternCards.orangeCardCount();
		int whiteCount=gameEngine.lanternCards.whiteCardCount();
		int blueCount=gameEngine.lanternCards.blueCardCount();
		
		int playerScore_threePairBeforeSim=player.playerScore_threePair;
		int playerScore_fourKindBeforeSim=player.playerScore_fourKind;
		int playerScore_sevenUniqueBeforeSim=player.playerScore_sevenUnique;
		
		int tokenScoreBeforSimulation=player.favorTokenScore;
		ArrayList<String> availableSpaces=new ArrayList();
		availableSpaces = gameEngine.board.availableSpaces();
		LakeTiles currentTileToPlace=null;
		boolean placeLakeTile = false;
		Vector dataSetToReturn= new Vector();
		int numberOfCardsGot=0;
		//available space * number of possible rotation of lakeTile i.e 4 * number of laketiles
		int total_Score[] = new int[availableSpaces.size()*4*3];
		int total_Score_Count=0;
		int max_Score_Possible=0;
		int max_Score_index=0;
		int max_number_Of_Cards=0;
		int max_id=0,maxdegreeOfRotation=0;
		int maxcurrentTileToPlaceid=0;
		int maxNumberOfFavorTokens=0;
		int numberOfFavorTokensGot=0;
		String maxAdjacentPosition="";
		
		for(int i=0;i<player.playerLTStack.size();i++)
		{
			
			for(int j=0;j<availableSpaces.size();j++)
			{				
				String lTPosition = availableSpaces.get(j);
				String[] parts = lTPosition.split(" ");
				int id = Integer.valueOf(parts[0]);
				String AdjacentPosition= parts[1];
				int GetColumn=gameEngine.lakeTiles.getColumn(gameEngine.board,id,AdjacentPosition);
				int GetRow=gameEngine.lakeTiles.getRow(gameEngine.board,id,AdjacentPosition);
				degreeOfRotation=0;
				for(int k=0;k<4;k++)
				{	
					currentTileToPlace=player.playerLTStack.get(i);
					currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
							currentTileToPlace, degreeOfRotation);
					
					dataSetToReturn=gameEngine.lanternCards.assignLanternCardsToPlayerSimulation(
								 gameEngine.board,GetColumn,GetRow,currentTileToPlace,player,
								 gameEngine.lanternCards,gameEngine.favorTokens,gameEngine.numOfPlayer);
					
				
					makeDedicationGreedySimulation(gameEngine,player);
					//return the dataset from vector to global lantern cards
					Enumeration e=dataSetToReturn.elements();
					numberOfCardsGot=(int) dataSetToReturn.lastElement();
			
					dataSetToReturn.removeElementAt(dataSetToReturn.size()-1);
					numberOfFavorTokensGot=(int) dataSetToReturn.lastElement();
					dataSetToReturn.removeElementAt(dataSetToReturn.size()-1);
					while (e.hasMoreElements()) {        		
						  // System.out.println("Cards to be returned" + e.nextElement());
						String cardToRemove=(String) e.nextElement();
						   player.playerLCStack.removeLanternCard(cardToRemove,player);
						   }  
					total_Score[total_Score_Count]=player.playerScore_fourKind+player.playerScore_sevenUnique+player.playerScore_threePair;
					if(total_Score[total_Score_Count]>max_Score_Possible)
					{
						max_Score_Possible=total_Score[total_Score_Count];
						max_Score_index=total_Score_Count;
						max_id=id;
						maxAdjacentPosition=AdjacentPosition;
						maxdegreeOfRotation=degreeOfRotation;
					}
					else
					{						
						if(numberOfCardsGot>max_number_Of_Cards)
						{
							max_number_Of_Cards=numberOfCardsGot;
							max_Score_Possible=total_Score[total_Score_Count];
							max_Score_index=total_Score_Count;
							max_id=id;
							maxAdjacentPosition=AdjacentPosition;
							maxdegreeOfRotation=degreeOfRotation;
							
							maxNumberOfFavorTokens=numberOfFavorTokensGot;
							maxcurrentTileToPlaceid=currentTileToPlace.id;
						}
						else if(numberOfCardsGot==max_number_Of_Cards && numberOfFavorTokensGot>maxNumberOfFavorTokens)
						{
							maxNumberOfFavorTokens=numberOfFavorTokensGot;
							max_id=id;
							maxAdjacentPosition=AdjacentPosition;
							maxdegreeOfRotation=degreeOfRotation;
							maxcurrentTileToPlaceid=currentTileToPlace.id;
						}
					}
					total_Score_Count++;
					degreeOfRotation+=90;
					player.favorTokenScore=tokenScoreBeforSimulation;
					player.playerScore_fourKind=playerScore_fourKindBeforeSim;
					player.playerScore_sevenUnique=playerScore_sevenUniqueBeforeSim;
					player.playerScore_threePair=playerScore_threePairBeforeSim;
					
						//create dummy player..copy data structures and pass that player..make best dedication for that 
						//place and store the totalHonorcount.return lanterncards back to global stacks..continue for all places..
						//get the highest honor value and then use actual player and place lakeTile
				}				
			}
		}
		int index=0;
		for(int i=0;i<player.playerLTStack.size();i++)
		{
			if(player.playerLTStack.get(i).id==maxcurrentTileToPlaceid)
			{
				index=i;
				currentTileToPlace=player.playerLTStack.get(i);
			}
		}
		System.out.println("Enter the index of laketiles you want to put on board:");
		System.out.println(index);
		System.out
		.println("Enter the id of the adjacent tile (on board) where you want to place your LakeTile");
		System.out.println(max_id);
		System.out
		.println("Enter the adjacent position (right, left, up, down)");
		System.out.println(maxAdjacentPosition);
		int GetColumn=gameEngine.lakeTiles.getColumn(gameEngine.board,max_id,maxAdjacentPosition);
		int GetRow=gameEngine.lakeTiles.getRow(gameEngine.board,max_id,maxAdjacentPosition);
		
		System.out
				.println("Enter the degree of roatation for the tile you want to place on board");
		System.out.println("Available options 0 90 180 270");
		System.out.println(maxdegreeOfRotation);
		currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
				currentTileToPlace, maxdegreeOfRotation);

		placeLakeTile = gameEngine.lakeTiles.placeTile(GetColumn,
				GetRow, gameEngine.board, currentTileToPlace);
		
		gameEngine.lanternCards.stacks.put("redCard", redCount);
		gameEngine.lanternCards.stacks.put("blueCard", blueCount);
		gameEngine.lanternCards.stacks.put("greenCard", greenCount);
		gameEngine.lanternCards.stacks.put("whiteCard", whiteCount);
		gameEngine.lanternCards.stacks.put("purpleCard", purpleCount);
		gameEngine.lanternCards.stacks.put("blackCard", blackCount);
		gameEngine.lanternCards.stacks.put("orangeCard",orangeCount);
		
		if (placeLakeTile) {
			gameEngine.lanternCards.assignLanternCardsToPlayers(
					gameEngine.numOfPlayer, gameEngine.board,
					GetColumn,GetRow, currentTileToPlace,
						gameEngine.PlayerList, gameEngine.lanternCards, gameEngine.favorTokens);
							gameEngine.board.displayBoard(gameEngine.board.board,
									gameEngine.board.tilesOnBoard);
							
							for(int i=0;i<player.playerLTStack.size();i++)
							{
								if(currentTileToPlace.id==player.playerLTStack.get(i).id)
									player.playerLTStack.remove(i);
							}
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
		} else
			player.pickLakeTileFromStack(currentTileToPlace);
			
		System.out.println();
		System.out.println("Player Pick up the new LakeTile from the Stack after placing one");
		player.displayPlayersLakeTile(player);
		
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
		int returnCard=0;
		int pickCard=0;
		boolean moveState=false;		
				if(player.getLanternCards().blackCardCount()==0)
				{
					 pickLCard="blackCard";	
					 numberOfCardsNeeded++;
					 pickCard=6;
				}
				if(player.getLanternCards().blueCardCount()==0)
				{
					 pickLCard="blueCard";	
					 numberOfCardsNeeded++;
					 pickCard=7;
				}
			    if(player.getLanternCards().greenCardCount()==0)
				{
					 pickLCard="greenCard";	
					 numberOfCardsNeeded++;
					 pickCard=2;
				}
				if(player.getLanternCards().whiteCardCount()==0)
				{
					 pickLCard="whiteCard";	
					 numberOfCardsNeeded++;
					 pickCard=5;
				}
			    if(player.getLanternCards().purpleCardCount()==0)
				{
					 pickLCard="purpleCard";	
					 numberOfCardsNeeded++;
					 pickCard=3;
				}
			    if(player.getLanternCards().orangeCardCount()==0)
				{
					 pickLCard="orangeCard";	
					 numberOfCardsNeeded++;
					 pickCard=4;
				}
				if(player.getLanternCards().redCardCount()==0)
				{
					 pickLCard="redCard";	
					 numberOfCardsNeeded++;
					 pickCard=1;
				}
				if(numberOfCardsNeeded==0)
					 pickLCard="NotPossible";	
				if(numberOfCardsNeeded==1)
				{
					if(player.getLanternCards().blackCardCount()>1)
						{returnLCard="blackCard";returnCard=6;}
					else if(player.getLanternCards().blueCardCount()>1)
						{returnLCard="blueCard";returnCard=7;}
					else if(player.getLanternCards().whiteCardCount()>1)
						{returnLCard="whiteCard";returnCard=5;}
					else if(player.getLanternCards().purpleCardCount()>1)
						{returnLCard="purpleCard";returnCard=3;}
					else if(player.getLanternCards().redCardCount()>1)
						{returnLCard="redCard";returnCard=1;}
					else if(player.getLanternCards().greenCardCount()>1)
						{returnLCard="greenCard";returnCard=2;}
					else if(player.getLanternCards().orangeCardCount()>1)
						{returnLCard="orangeCard";returnCard=4;}
				}
				else
					returnLCard="NotPossible";
				if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
				{
					//System.out.println("Exchange Lantern card for SevenUnique is not possible right now");
				}
				
				if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
				{
							moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
							returnLCard, pickLCard);
				}
				
		if (moveState)
			displayExchangeMove(player,returnCard,pickCard);
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
		int returnCard=0;
		int pickCard=0;
		if(player.getLanternCards().blackCardCount()==3 && gameEngine.lanternCards.hasCard("blackCard"))
		{
			pickLCard="blackCard";
			returnCard=6;
		}
		else if(player.getLanternCards().blueCardCount()==3 && gameEngine.lanternCards.hasCard("blueCard"))
		{
			pickLCard="blueCard";
			returnCard=7;
		}
		else if(player.getLanternCards().greenCardCount()==3 && gameEngine.lanternCards.hasCard("greenCard"))
		{
			pickLCard="greenCard";
			returnCard=2;
		}
		else if(player.getLanternCards().redCardCount()==3 && gameEngine.lanternCards.hasCard("redCard"))
		{
			pickLCard="redCard";
			returnCard=1;
		}
		else if(player.getLanternCards().whiteCardCount()==3 && gameEngine.lanternCards.hasCard("whiteCard"))
		{
			pickLCard="whiteCard";
			returnCard=5;
		}
		else if(player.getLanternCards().orangeCardCount()==3 && gameEngine.lanternCards.hasCard("orangeCard"))
		{
			pickLCard="orangeCard";
			returnCard=4;
		}
		else if(player.getLanternCards().purpleCardCount()==3 && gameEngine.lanternCards.hasCard("purpleCard"))
		{
			pickLCard="purpleCard";
			returnCard=3;
		}
		else
			pickLCard="NotPossible";
		
		if(player.getLanternCards().blackCardCount()<3 && player.getLanternCards().blackCardCount()>0)
			{returnLCard="blackCard";pickCard=6;}
		else if(player.getLanternCards().blueCardCount()<3 && player.getLanternCards().blueCardCount()>0)
			{returnLCard="blueCard";pickCard=7;}
		else if(player.getLanternCards().whiteCardCount()<3 && player.getLanternCards().whiteCardCount()>0)
			{returnLCard="whiteCard";pickCard=5;}
		else if(player.getLanternCards().orangeCardCount()<3 && player.getLanternCards().orangeCardCount()>0)
			{returnLCard="orangeCard";pickCard=4;}
		else if(player.getLanternCards().greenCardCount()<3 && player.getLanternCards().greenCardCount()>0)
			{returnLCard="greenCard";pickCard=2;}
		else if(player.getLanternCards().redCardCount()<3 && player.getLanternCards().redCardCount()>0)
			{returnLCard="redCard";pickCard=1;}
		else if(player.getLanternCards().purpleCardCount()<3 && player.getLanternCards().purpleCardCount()>0)
			{returnLCard="purpleCard";pickCard=3;}
		else
			returnLCard="NotPossible";
		if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
		{
			//System.out.println("Exchange Lantern card for FourOfKind is not possible right now");
		}
		
		if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
		{
					moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
					returnLCard, pickLCard);
		}
		if(moveState)
			displayExchangeMove(player,returnCard,pickCard);
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
		int returnCard=0;
		int pickCard=0;
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
				pickCard=1;
			}
			else if(player.getLanternCards().purpleCardCount()==1 && gameEngine.lanternCards.hasCard("purpleCard"))
			{
				pairs.put("purpleCard", true);
				pickLCard="purpleCard";
				pickCard=3;
			}
			else if(player.getLanternCards().greenCardCount()==1 && gameEngine.lanternCards.hasCard("greenCard"))
			{
				pairs.put("greenCard", true);
				pickLCard="greenCard";
				pickCard=2;
			}
			else if(player.getLanternCards().whiteCardCount()==1 && gameEngine.lanternCards.hasCard("whiteCard"))
			{
				pairs.put("whiteCard", true);
				pickLCard="whiteCard";
				pickCard=5;
			}
			else if(player.getLanternCards().orangeCardCount()==1 && gameEngine.lanternCards.hasCard("orangeCard"))
			{
				pairs.put("orangeCard", true);
				pickLCard="orangeCard";
				pickCard=4;
			}
			else if(player.getLanternCards().blueCardCount()==1 && gameEngine.lanternCards.hasCard("blueCard"))
			{
				pairs.put("blueCard", true);
				pickLCard="blueCard";
				pickCard=7;
			}
		}
		
		if(pairs.get("blackCard")!=true && player.getLanternCards().blackCardCount()>=1)
		{
			returnLCard="blackCard";
			returnCard=6;
		}
		else if(pairs.get("redCard")!=true && player.getLanternCards().redCardCount()>=1)
		{
			returnLCard="redCard";
			returnCard=1;
		}
		else if(pairs.get("blueCard")!=true && player.getLanternCards().blueCardCount()>=1)
		{
			returnLCard="blueCard";
			returnCard=7;
		}
		else if(pairs.get("greenCard")!=true && player.getLanternCards().greenCardCount()>=1)
		{
			returnLCard="greenCard";
			returnCard=2;
		}
		else if(pairs.get("orangeCard")!=true && player.getLanternCards().orangeCardCount()>=1)
		{
			returnLCard="orangeCard";
			returnCard=4;
		}
		else if(pairs.get("purpleCard")!=true && player.getLanternCards().purpleCardCount()>=1)
		{
			returnLCard="purpleCard";
			returnCard=3;
		}
		else if(pairs.get("whiteCard")!=true && player.getLanternCards().whiteCardCount()>=1)
		{
			returnLCard="whiteCard";
			returnCard=5;
		}
		else
			returnLCard="NotPossible";
		if(pickLCard.equalsIgnoreCase("NotPossible") || returnLCard.equalsIgnoreCase("NotPossible"))
		{
			//System.out.println("Exchange Lantern card for ThreePair is not possible right now");
		}
		
		if(!returnLCard.equalsIgnoreCase("NotPossible") && !pickLCard.equalsIgnoreCase("NotPossible") )
		{
					moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
					returnLCard, pickLCard);
		}
		
			if (moveState)
					displayExchangeMove(player,returnCard,pickCard);
	return moveState;
	}
	
	/**
	 * This method checks whether FourOfKind Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @param simulation whether its simultation mode or actual move
	 * @return state if dedication is successful
	 */
	public boolean checkFourOfKindDedication(Player player,
			GameEngine gameEngine,boolean simulation) {
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
		if(card!=0)
		{
			CardToReturn cardToReturn = new CardToReturn(
					card);
			returnedLanternCards = cardToReturn
					.returnStackFourOfKind();
	
			
			if (returnedLanternCards != null) {
			
				int simfourOfKind=gameEngine.dedicationTokens.peekFourOfKind();
				state = player.pickDedicationToken(moveString,
								returnedLanternCards, gameEngine.lanternCards,
								gameEngine.dedicationTokens);
				if(simulation)
				{
					gameEngine.lanternCards.returnAll(returnedLanternCards);
					if(state)
						gameEngine.dedicationTokens.fourOfKind.push(simfourOfKind);
				}
				else
				{
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
					if(state)
					{
						System.out.println(2);
						System.out
						.println("What Lantern cards do you want to return?"
								+ " Enter the card");
						System.out.println(card);
						System.out
						.println("1: redCard 2: blueCard 3: greenCard 4: "
								+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");
					}
				}
			}
		}
			return state;
	}
	
	/**
	 * This method checks whether ThreePair Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @param simulation whether its simultation mode or actual move
	 * @return state if dedication is successful
	 */
	public boolean checkThreePairDedication(Player player,
			GameEngine gameEngine,boolean simulation) {
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
		if(card!=0 && card1!=0 && card2!=0 && !simulation)
		{
			CardToReturn cardToReturn = new CardToReturn(
					card, card1, card2);
	
			returnedLanternCards = cardToReturn
					.returnStackThreeOfKind();
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
			int simThreePair=gameEngine.dedicationTokens.peekThreePairs();
			state = player.pickDedicationToken(moveString,
							returnedLanternCards, gameEngine.lanternCards,
							gameEngine.dedicationTokens);
			if(simulation)
			{
				gameEngine.lanternCards.returnAll(returnedLanternCards);
				if(state)
					gameEngine.dedicationTokens.threePair.push(simThreePair);
			}
			else
			{
				if(state)
				{
					System.out.println(1);
					System.out.println("What Lantern cards do you want to return? Enter "+ "the three cards.");
					System.out.println("1: redCard 2: blueCard 3: greenCard 4: "+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");
					System.out.println(card);
					System.out.println(card1);
					System.out.println(card2);
				}
			}
		}
		return state;
	}
		
	/**
	 * This method checks whether SevenUnique Dedication is possible for greedyPlayer and does it if possible
	 * @param player current player
	 * @param gameEngine contains the state of entire game
	 * @param simulation whether its simultation mode or actual move
	 * @return state if dedication is successful
	 */
	private boolean checkSevenUniqueDedication(Player player,
			GameEngine gameEngine,boolean simulation) {
			boolean state=false;
			String moveString = "sevenUnique";
			LanternCards returnedLanternCards = null;
			
			CardToReturn cardToReturn = new CardToReturn();
			if (cardToReturn.SevenUniqueState(player)) {
				returnedLanternCards = cardToReturn
						.returnSeveUnique();
			} 	
			
			if (returnedLanternCards != null) {
				int simSevenUnique=gameEngine.dedicationTokens.threePair.peek();
				state = player.pickDedicationToken(moveString,
								returnedLanternCards, gameEngine.lanternCards,
								gameEngine.dedicationTokens);
				if(simulation)
				{
					if(state)
						gameEngine.dedicationTokens.threePair.push(simSevenUnique);
					gameEngine.lanternCards.returnAll(returnedLanternCards);
				}
				else
				{
					System.out.println(3);
					//get cards from player
					player.getLanternCards()
					.getCard("blackCard");
					player.getLanternCards()
					.getCard("blueCard");
					player.getLanternCards()
					.getCard("greenCard");
					player.getLanternCards()
					.getCard("orangeCard");
					player.getLanternCards()
					.getCard("purpleCard");
					player.getLanternCards()
					.getCard("whiteCard");
					player.getLanternCards()
					.getCard("redCard");
				}
			}
		return state;
	}

	/**
	 * This method is used for simulation for making dedication
	 * @param gameEngine contains the state of the entire game
	 * @param dummyPlayer dummy greedy player used for simulation
	 */
	private void makeDedicationGreedySimulation(GameEngine gameEngine,
			Player dummyPlayer) {
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
					if(values[j]<values[j+1])
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
			 boolean simulation=true;
			 if(x.equalsIgnoreCase("FourOfKind"))
			 {
				 exchanged=checkFourOfKindDedication(dummyPlayer,gameEngine,simulation);
				 if(exchanged)
				 {
					 break one;
				 }
			 }
			 if(x.equalsIgnoreCase("ThreePair"))
			 {
				 exchanged=checkThreePairDedication(dummyPlayer,gameEngine,simulation);
				 if(exchanged)
				 {
					 break one;
				 }
			 }
			 if(x.equalsIgnoreCase("SevenUnique"))
			 {
				 exchanged=checkSevenUniqueDedication(dummyPlayer,gameEngine,simulation);
				 if(exchanged)
				 {
					 break one;
				 }
			 }
		 }			
		/* if(exchanged)
		 {
			 System.out.println("In Simulation Mode");
			 System.out.println("Picked!");
				System.out.println("Score: four of a kind "
								+ dummyPlayer.playerScore_fourKind);
				System.out
						.println("Score: three of a kind "
								+ dummyPlayer.playerScore_threePair);
				System.out
						.println("Score: Seven unique "
								+ dummyPlayer.playerScore_sevenUnique);
		 }
		 //else
			 //System.out.println(" Please revisit the game rules!");*/
	}
	
	/**
	 * This method displayes events of greedy player for exchange lantern card move
	 * @param player current player
	 * @param returnCard card to be returned for exchange lantern card move
	 * @param pickCard card to be picked for exchange lantern card move
	 */
	private void displayExchangeMove(Player player, int returnCard, int pickCard) {
		System.out.println("Select the lantern card you want to return.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card");
		System.out.println(pickCard);
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card");
		System.out.println(returnCard);
		
	}
	/**This method playes one move for greedy player 
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	@Override
	public void play(GameEngine gameEngine, Player player) {
		
		exchangeLanternCards(gameEngine,player);
		makeDedication(gameEngine,player);
		placeLakeTile(gameEngine,player);
		
		if(gameEngine.lakeTiles.hasLakeTile())
		{// Composury pick lake tile
			player.pickLakeTileFromStack(gameEngine.lakeTiles.getLakeTile());
			System.out.println();
			player.displayPlayersLakeTile(player);
		}else
		{
			System.out.println("No lake tiles left on board to pick");
		}
	}

	
}
