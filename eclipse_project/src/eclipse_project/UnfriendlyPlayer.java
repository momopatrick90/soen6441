package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class UnfriendlyPlayer implements PlayerStrategy {

	public UnfriendlyPlayer(String name) {

	}

	@Override
	/**
	 * @see eclipse_project.PlayerStrategy#play(eclipse_project.GameEngine, eclipse_project.Player)
	 */
	public void play(GameEngine gameEngine, Player player) {
		//
		System.out.println("--------------------");
		this.exchangeLanternCards(gameEngine, player);
		
		//
		System.out.println("--------------------");
		this.makeDedication(gameEngine, player);
		
		//
		System.out.println("--------------------");
		this.placeLakeTile(gameEngine, player);
		
		//
		System.out.println("--------------------");
		// TODO code duplicate
		if(gameEngine.lakeTiles.hasLakeTile())
		{// Composury pick lake tile
			System.out.println("unfriendly: picking  laketiles");
			player.pickLakeTileFromStack(gameEngine.lakeTiles.getLakeTile());
			System.out.println();
			//
			player.displayPlayersLakeTile(player);
		}else
		{
			System.out.println("unfriendly: No lake tiles left on board to pick");
		}
	}
	
	/**
	 * Make best dedication so no one can do
	 * @param gameEngine
	 * @param player
	 */
	public void makeDedication(GameEngine gameEngine, Player player)
	{
		boolean played = false;
		//
		{
			 if(player.playerLCStack.getSevenUniques()!=null)
			 {
				 // make dedications
				 LanternCards sevenUniques = player.playerLCStack.getSevenUniques();
				 player.pickDedicationToken("sevenUnique", sevenUniques, gameEngine.lanternCards, gameEngine.dedicationTokens);
				 player.playerLCStack.withdrawAll(sevenUniques);
				 
				 //
				 System.out.println("unfriendly:dedication: doing seven unique");

				 //
				 played=true;
			 }else if(player.playerLCStack.getFourOfKinds()!=null)
			 {
				 // make dedications
				 LanternCards fourOfKind = player.playerLCStack.getFourOfKinds();
				 player.pickDedicationToken("FourOfKind", fourOfKind, gameEngine.lanternCards, gameEngine.dedicationTokens);
				 player.playerLCStack.withdrawAll(fourOfKind);
				 
				 //
				 System.out.println("unfriendly:dedication: doing four of kind");
				 
				 played=true;
			 }else if(player.playerLCStack.getThreePairs()!=null)
			 {
				 // make dedications
				 LanternCards threePair = player.playerLCStack.getThreePairs();
				 player.pickDedicationToken("threePair", threePair, gameEngine.lanternCards, gameEngine.dedicationTokens);
				 player.playerLCStack.withdrawAll(threePair);
				 
				 //
				 System.out.println("unfriendly:dedication: doing three pair");
				 
				 //
				 played = true;
			 }
		}
		
		if(!played)
		{
			System.out.println("unfriendly:dedication: no dedication possible");
		}else
		{
			System.out.println("unfriendly:dedication: resulting lantern cards");
			System.out.println(player.getLanternCards().toString());
		}
	}

	/**
	 * The goal is if possible  is to get a lantern card such that one of the players can't get a better score for the next round
	 * @param gameEngine contains the entire state of the game
	 * @param player current player 
	 */
	public void exchangeLanternCards(GameEngine gameEngine, Player player)
	{
		if(player.favorTokenScore<2)
		{
			//
			System.out.println("unfriendly:exchange_lantern_card: can't do exchange lantern cards, not enough tokens: "+player.favorTokenScore);
			return;
		}
		
		// Available colors
		LinkedList<String> playerAvailableColors = player.playerLCStack.colorsWithAtLeastQuantity(1);
		if(playerAvailableColors.size() >= 1)
		{
			//
			System.out.println("unfriendly:exchange_lantern_card: can't do exchange lantern cards, player has no lantern cards ");
			return;
		}
		
		// Some cards are unique on the board, maybe someone needs them to make a dedication
		LinkedList<String> boardUniqueColors = gameEngine.lanternCards.colorsWithQuantity(1);
		//
		if(boardUniqueColors.size() == 0)
		{
			//
			System.out.println("unfriendly:exchange_lantern_card: no lantern cards on the board is unique, can't be unfriendly to any one");
			return;
		}
		
		
		//
		Player minPlayer = null;
		int minDedicationGain = Integer.MAX_VALUE;
		String minCardReturn = null;
		String minCardGet = null;
		
		//
		for(int i=0; i<playerAvailableColors.size(); i++)
		{
			//
			for(int j=0; j<boardUniqueColors.size(); j++)
			{
				//
				for(int k=0; k<gameEngine.PlayerList.size(); k++)
				{
					//
					if(gameEngine.PlayerList.get(k) == player)
						continue;

					//
					int gain = dedicationGain(gameEngine, gameEngine.PlayerList.get(k), playerAvailableColors.get(i), boardUniqueColors.get(j));
					
					if(gain < 0)
					{
						if(gain<minDedicationGain)
						{
							//
							minPlayer = gameEngine.PlayerList.get(k);
							minDedicationGain = gain;
							//
							minCardReturn = playerAvailableColors.get(i);
							minCardGet = boardUniqueColors.get(j);
						}
					}
				}
			}
		}
		
		if(minPlayer == null)
		{
			System.out.println("unfriendly:exchange_lantern_card found no one who needed a single color to increase his dedication posibility");
		}else
		{
			//
			System.out.println("unfriendly:exchange_lantern_card being unfriendly to "+minPlayer.name+" returning lantern card "+minCardReturn+" getting lantern card"+minCardGet+", so he can't make dedication");
			player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards, minCardReturn, minCardGet);
			//
			System.out.println("unfriendly:exchange_lantern_card PLAYER resulting lantern cards: ");
			System.out.println(player.getLanternCards().toString());
			//
			System.out.println("unfriendly:exchange_lantern_card PLAYER resulting favor tokens: ");
			System.out.println(player.favorTokenScore);
			//
			System.out.println("unfriendly:exchange_lantern_card GAME ENGINE resulting lantern cards: ");
			System.out.println(gameEngine.lanternCards);
			//
			System.out.println("unfriendly:exchange_lantern_card GAME ENGINE resulting favor tokens: ");
			System.out.println(gameEngine.favorTokens.getTokens());
		}
		
	}

	/**
	 * What possible dedications does the player gain if he gets lantern color2 instead of lantern color2
	 * @param player
	 * @param color1
	 * @param color2
	 * @return
	 */
	public int dedicationGain(GameEngine gameEngine, Player player, String color1, String color2)
	{
		// current players lantern cards
		LanternCards lanternCardWithColor1 = player.getLanternCards().duplicate();
		lanternCardWithColor1.addCard(color1);
		
		// current players lantern cards
		LanternCards lanternCardWithColor2 = player.getLanternCards().duplicate();
		lanternCardWithColor2.addCard(color2);
		
		// 
		return lanternCardWithColor2.possibleDedicationsCount(gameEngine.dedicationTokens) - lanternCardWithColor1.possibleDedicationsCount(gameEngine.dedicationTokens);
	}


	/**
	 * The goal is to place a lake tiles such that it minimizes the possible score gain on any given player
	 * @param gameEngine
	 * @param player
	 * @param br
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player) 
	{
		//
		System.out.println("unfriendly:place_lake_tile: "+player.name+" is about to place a lake tile");
		
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("unfriendly:place_lake_tile: No lake tile available to play");
			return;
		}
		
		// The  lake tile position with the min benefit case of a given player
		int[] minPosition = null; // the position leading to a min point for a given player
		int minRotation = 0;   	  // the rotation of the min point localtion
		int minDedicationGain = Integer.MAX_VALUE; 	 	// The min points I can make any given player have
		int minIndex = 0;
		Player minPlayer = null;
		String minReason = "";
		
		// empty location
		ArrayList<int[]> emptyLocations = gameEngine.board.availableSpacesPosition();
		// shuffle so as to give prefence to a position
		java.util.Collections.shuffle(emptyLocations);
		
		//foreach of the character laketiles
		ArrayList<LakeTiles> lakeTiles = player.getLakeTiles(); 
		for(int i=0; i<lakeTiles.size(); i++)
		{
			//
			LakeTiles lakeTile = lakeTiles.get(i);
		
			// for each of the possible locations
			for(int j=0; j<emptyLocations.size(); j++)
			{
				//
				int[] position = emptyLocations.get(j);
				
				// Go through the possible rotations
				int[] possibleRotations = new int[]{0, 90, 180, 270};
				for(int k=0; k<possibleRotations.length; k++)
				{
					//
					int rotation = possibleRotations[k];
					
					// for each of the other players
					for(int z=0; z<gameEngine.PlayerList.size(); z++)
					{
						//
						Player currentPlayer = gameEngine.PlayerList.get(z);
						
						//TODO test
						if(currentPlayer != player)
						{
							// obtained color
							String color = lakeTile.positionRotated(currentPlayer.boardPosition, rotation);
							// 
							int dedicationGain_ = dedicationGain(gameEngine, currentPlayer, color+"Card");
				
							//
							boolean isMin  = false;
							boolean end = false;

							// player will not be able to get card
							if(!gameEngine.lanternCards.hasCard(color+"Card"))
							{
								isMin = true;
								end = true;
								minReason = "prevent player from getting card: "+color;
							}else if(minPosition == null || dedicationGain_ < minDedicationGain)
							{//TODO all players
								isMin = true;
								end = false;
								
								if(dedicationGain_<=0)
									minReason = "player does not gain any dedication with: "+color;
							}
							
							
							// place lake tile such that minimizes the score gained
							if(isMin)
							{
								//
								minPosition = position;
								minRotation = rotation;
								minDedicationGain = dedicationGain_;
								minPlayer = currentPlayer;
								minIndex = i;
							}
							
							if(end)
							{
								break;
							}
						}
					}
				}
			}
		}
		
		//  widraw lake tile
		LakeTiles currentTileToPlace = player.placeLakeTile(minIndex);
		//
		System.out.println("Unfriendly:place_lake_tile: placing laketile.id: "+currentTileToPlace.id+", targeting player: "+minPlayer.name+", row: "+minPosition[0]+", col: "+minPosition[1]+" reason: "+minReason);
		
		//
		gameEngine.lakeTiles.rotateLakeTile(currentTileToPlace, minRotation);
		//
		boolean placedLaketile = gameEngine.lakeTiles.placeTile(minPosition[1], minPosition[0], gameEngine.board, currentTileToPlace);
		
		// TODO test distribute
		if(placedLaketile)
		{
			System.out.println("unfriendly:place_lake_tile:  assigning lake tiles to players");
			// 
			gameEngine.lanternCards.assignLanternCardsToPlayers(
					gameEngine.numOfPlayer, gameEngine.board,
					minPosition[1],minPosition[0], currentTileToPlace,
						gameEngine.PlayerList, gameEngine.lanternCards, gameEngine.favorTokens);
			//
			gameEngine.board.displayBoard();
			//gameEngine.board.displayBoard(gameEngine.board.board, gameEngine.board.tilesOnBoard);
		}else
		{
			System.out.println("unfriendly:place_lake_tile: Unexpected error placing lake tile");
		}
	}
	

	
	/**
	 * What possible dedications does the player if he gets the lanternCard of given color?
	 * @param gameEngine
	 * @param lakeTile
	 * @param player
	 * @param position
	 * @param rotation
	 * @return
	 */
	public int dedicationGain(GameEngine gameEngine, Player player, String cardColor)
	{
		// current players lantern cards
		LanternCards lanternCards = player.getLanternCards();
		int current = lanternCards.possibleDedicationsCount(gameEngine.dedicationTokens);
		
		// lantern cards of player after tile is placed
		LanternCards lanternAfterCard = lanternCards.duplicate();
		lanternAfterCard.addCard(cardColor);
		
		//
		int afterLakeTile = lanternAfterCard.possibleDedicationsCount(gameEngine.dedicationTokens);
		
		// 
		return afterLakeTile - current;
	}
}
