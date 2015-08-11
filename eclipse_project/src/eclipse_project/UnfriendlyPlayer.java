package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnfriendlyPlayer extends Player implements PlayerStrategy {

	public UnfriendlyPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * The goals is if possible to get a lantern card such that
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	protected void exchangeLanternCards(GameEngine gameEngine, Player player)
	{
		
	}

	/**
	 * The goal is to place a lake tiles such that it minimizes the possible score gain on any given player
	 * @param gameEngine
	 * @param player
	 * @param br
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		//
		System.out.println("unfriendly: placing laketile for player "+player.name);
		
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("No lake tile available to play");
			return;
		}
		
		// The  lake tile position with the min case of a given player
		int[] minPosition = null; // the poosition leading to a min point for a given player
		int minRotation = 0;   // the rotation of the min point localtion
		int minScoreGain = Integer.MAX_VALUE; 	 	// The min points I can make any given player have
		int minId = 0; 			// An id of a lake tile adjacent to the min position
		Player minPlayer = null;
		
		// TODO replace this with board empty location
		ArrayList<int[]> emptyLocations = new ArrayList<int[]>();
		
		
		//foreach of the character laketiles
		ArrayList<LakeTiles> lakeTiles = player.getLakeTiles(); 
		for(int i=0; i<lakeTiles.size(); i++)
		{
			//
			LakeTiles lakeTile = lakeTiles.get(i);
		
			// for each of the possible locations
			for(int j=0; i<emptyLocations.size(); j++)
			{
				//
				int[] position = emptyLocations.get(j);
				
				// Go through the possible rotations
				int[] possibleRotations = new int[]{0, 90, 180, 270};
				for(int k=0; i<possibleRotations.length; k++)
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
							//
							int scoreGain_ = scoreGain(gameEngine, lakeTile, currentPlayer, position, rotation);
							
							// place lake tile such that minimizes the score gained
							if(minPosition == null || scoreGain_ < minScoreGain)
							{
								//
								minPosition = position;
								minRotation = rotation;
								minScoreGain = scoreGain_;
								minId = lakeTile.id;
								minPlayer = currentPlayer;
							}
						}
					}
				}
			}
		}
		
		//
		System.out.println("Unfriendly: placing laketile: "+minId+", min_player(victim): "+minPlayer.name+", min position: "+minPosition);

		
		// Place on the min
		// withdrawing the lake tile
		LakeTiles currentTileToPlace = player.placeLakeTile(minId);
		//
		gameEngine.lakeTiles.rotateLakeTile(currentTileToPlace, minRotation);
		//
		boolean placedLaketile = gameEngine.lakeTiles.placeTile(minPosition[1], minPosition[0], gameEngine.board, currentTileToPlace);
		
		//
		if(!placedLaketile)
		{
			System.out.println("unfriendly: Unexpected error placing lake tile");
		}

	}
	
	/**
	 * What possible score does the player gain if the tile is place at the position?
	 * @param gameEngine
	 * @param lakeTile
	 * @param player
	 * @param position
	 * @param rotation
	 * @return
	 */
	public int scoreGain(GameEngine gameEngine, LakeTiles lakeTile, Player player, int[] position, int rotation)
	{
		// current players lantern cards
		LanternCards lanternCards = player.getLanternCards();
		int currentScore = lanternCards.bestPossibleScore(gameEngine.dedicationTokens);
		
		// lantern cards of player after tile is placed
		LanternCards lanternAfterLakeTile = lanternCards.duplicate();
		int scoreAfterLakeTile = lanternAfterLakeTile.bestPossibleScore(gameEngine.dedicationTokens);
		
		// 
		return scoreAfterLakeTile - currentScore;
	}

}
