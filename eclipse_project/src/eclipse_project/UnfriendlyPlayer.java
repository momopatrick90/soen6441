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
		int minScore = Integer.MAX_VALUE; 	 	// The min points I can make any given player have
		int minId = 0; 			// An id of a lake tile adjacent to the min position
		Player minPlayer = null;
		
		// TODO replace this with board empty location
		ArrayList<int[]> emptyLocations = new ArrayList<int[]>();
		// 
		int[] possibleRotations = new int[]{0, 90, 180, 270};
		//
		ArrayList<LakeTiles> lakeTiles = player.getLakeTiles();
	
		// foreach of the character laketiles
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
							int score = maxScore(gameEngine, lakeTile, currentPlayer, position, rotation);
							
							// first time or found new lower points 
							// place the tile such that it minimizes the max possible score the player can have
							// from his lantern card
							if(minPosition == null || score < minScore)
							{
								//
								minPosition = position;
								minRotation = rotation;
								minScore = score;
								minId = lakeTile.id;
								minPlayer = currentPlayer;
							}
						}
					}
				}
			}
		}
		
		//
		System.out.println("Unfriendly: placing laketile: "+minId+", min_player(victim): "+player.name+", min position: "+minPosition);

		
		// Place on the min
		// withdrawing the lake tile
		LakeTiles currentTileToPlace = player.placeLakeTile(minId);
		gameEngine.lakeTiles.rotateLakeTile(currentTileToPlace, minRotation);
		boolean placedLaketile = gameEngine.lakeTiles.placeTile(minPosition[1], minPosition[0], gameEngine.board, currentTileToPlace);
		
		//
		if(!placedLaketile)
		{
			System.out.println("unfriendly: Unexpected error placing lake tile");
		}

	}
	
	// TODO implement
	public int maxScore(GameEngine gameEngine, LakeTiles lakeTile, Player player, int[] position, int rotation)
	{
		
		return 0;
	}

}
