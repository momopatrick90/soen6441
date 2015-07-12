package eclipse_project;

import java.util.ArrayList;

/**
 * GameBoard is created for the players.
 *
 */
public class Board {
	int board[][] = new int[73][73];
	ArrayList<LakeTiles> tilesOnBoard = new ArrayList<LakeTiles>();
	/**
	 * method to initialize the game board 
	 */
	public void intializeGameBoard(LakeTiles startTile)
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				board[i][j]=-1;
			}
		}
		tilesOnBoard.add(startTile);		
		board[37][37]=startTile.id;
	}

	/**
	 * method to loads the saved game board 
	 */
	//public void initializeSavedGameBoard()
}
