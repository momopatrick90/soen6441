package eclipse_project;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameBoard is created for the players.
 *
 */
public class Board {
	int board[][] = new int[73][73];
	ArrayList<LakeTiles> tilesOnBoard = new ArrayList<LakeTiles>();

	public Board(int board[][], ArrayList<LakeTiles> tilesOnBoard)
	{
		this.board = board;
		this.tilesOnBoard = tilesOnBoard;
	}
	
	public Board()
	{
		
	}
	
	
	/**
	 * method to initialize the game board
	 * 
	 * @param start
	 *            LakeTile
	 */
	public void intializeGameBoard(LakeTiles startTile) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = -1;
			}
		}
		Board myBoard = new Board();
		myBoard.shuffleStartTile(startTile);
		tilesOnBoard.add(startTile);
		board[37][37] = startTile.id;
	}

	/**
	 * method to rotate the position of the start LakeTile randomly
	 * 
	 * @param start
	 *            LakeTile
	 */
	public void shuffleStartTile(LakeTiles startTile) {
		startTile.downColor = "red";
		startTile.upColor = "green";
		startTile.leftColor = "orange";
		startTile.rightColor = "black";
		startTile.platform = false;
		int x;
		Random ran = new Random();
		x = ran.nextInt(6) + 5;
		String up = startTile.upColor;
		String down = startTile.downColor;
		String left = startTile.leftColor;
		String right = startTile.rightColor;

		for (int i = 0; i < x; i++) {
			startTile.upColor = left;
			startTile.rightColor = up;
			startTile.downColor = right;
			startTile.leftColor = down;
			up = startTile.upColor;
			down = startTile.downColor;
			left = startTile.leftColor;
			right = startTile.rightColor;
		}
	}
	
	/**
	 * method to display the current state of boardGame
	 * 
	 * @param board gameBoard
	 * @param tilesOnBoard LakeTiles placed on board
	 */
	public void displayBoard(int board[][], ArrayList<LakeTiles> tilesOnBoard)
	{
		System.out.println("Current state of boardGame");
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				if(board[i][j]!=-1)
				{
					for(int k=0;k<tilesOnBoard.size();k++)
						if(board[i][j]==tilesOnBoard.get(k).id)
						{
							System.out.println("LakeTile id :"+tilesOnBoard.get(k).id+" X-coordinate of lakeTile "+i+"Y-coordinate of lakeTile "+j+ "leftColor: "+tilesOnBoard.get(k).leftColor+"rightColor: "+tilesOnBoard.get(k).rightColor+"upColor: "+tilesOnBoard.get(k).upColor+"downColor: "+tilesOnBoard.get(k).downColor+"leftNeighbour" + tilesOnBoard.get(k).left + "rightNeighbour"
									+ tilesOnBoard.get(k).right + "downNeighbour"
									+ tilesOnBoard.get(k).down + "upNeighbour"
									+ tilesOnBoard.get(k).up + "id"
									);
						}
				}
			}
		}
	}
}
