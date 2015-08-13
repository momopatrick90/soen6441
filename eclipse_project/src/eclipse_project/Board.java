package eclipse_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * GameBoard is created for the players.
 *
 */
public class Board {
	int board[][] = new int[73][73];
	ArrayList<LakeTiles> tilesOnBoard = new ArrayList<LakeTiles>();

	public Board(int board[][], ArrayList<LakeTiles> tilesOnBoard) {
		this.board = board;
		this.tilesOnBoard = tilesOnBoard;
	}

	/**
	 * Constructor for Board class
	 */
	public Board() {

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
	 * This method is used to calculate number of possible locations to place lake Tile
	 * @return availableSpace returns the number of possible locations to place lake Tile
	 */
	public ArrayList<String> availableSpaces() {
		ArrayList<String> availableSpace = new ArrayList();
		for (int x = 0; x < tilesOnBoard.size(); x++) {

			if (tilesOnBoard.get(x).left == -1)
				availableSpace.add(String.valueOf(tilesOnBoard.get(x).id)
						+ " left");

			if (tilesOnBoard.get(x).right == -1)
				availableSpace.add(String.valueOf(tilesOnBoard.get(x).id)
						+ " right");

			if (tilesOnBoard.get(x).up == -1)
				availableSpace.add(String.valueOf(tilesOnBoard.get(x).id)
						+ " up");
			if (tilesOnBoard.get(x).down == -1)
				availableSpace.add(String.valueOf(tilesOnBoard.get(x).id)
						+ " down");

		}
		return availableSpace;
	}
	
	
	/**
	 * @return return available spaces as rows and columns
	 */
	public ArrayList<int[]> availableSpacesPosition() {
		ArrayList<int[]> availableSpace = new ArrayList<int[]>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				boolean valid = false;
				if(board[i][j] == -1)
				{// has a good neighbour
					if(this.intBoardRange(i+1, j) && board[i+1][j] != -1)
					{
						valid = true;
					}else if(this.intBoardRange(i, j+1) && board[i][j+1] != -1)
					{
						valid = true;
					}else if(this.intBoardRange(i-1, j) && board[i-1][j] != -1)
					{
						valid = true;
					}else if(this.intBoardRange(i, j-1) && board[i][j-1] != -1)
					{
						valid = true;
					}
				}
				
				if(valid)
					availableSpace.add(new int[]{i, j});
			}
		}
		

		return availableSpace;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return true if the position is valid, else false
	 */
	public boolean intBoardRange(int row, int col)
	{
		if(row<0)
			return false;
		if(row>=this.board.length)
			return false;
		if(col<0)
			return false;
		if(col>=this.board[row].length)
			return false;
		return true;
	}
	
	/**
	 * String representation of the board
	 */
	public void displayBoard()
	{
		System.out.println("-----------------------");
		
		//
		int[] bounds = boundaries();
		

		
		//
		for(int i=bounds[0]; i<=bounds[1]; i++)
		{
			// print row
			// each "row" is made up of three lines"
			// 1 for      up
			// 1 for left id right
			// 1 for      down
			StringBuilder line1 = new StringBuilder("");
			StringBuilder line2 = new StringBuilder("");
			StringBuilder line3 = new StringBuilder("");
			StringBuilder line4 = new StringBuilder("");
		
			// through each column
			for(int j=bounds[2]; j<=bounds[3]; j++)
			{
				if(this.board[i][j] == -1)
				{
					line1.append("           |");
					line2.append("           |");
					line3.append("           |");
					line4.append("------------");
				}else
				{
					//
					LakeTiles lakeTile = this.lakeTileFromId(this.board[i][j]);
					String platformIndicator = lakeTile.platform? "P" : " ";
					
					line1.append(String.format("%3.3s%5.3s%3.3s|", " ", lakeTile.downColor, " "));
					line2.append(String.format("%3.3s %2.2s%s %3.3s|", lakeTile.leftColor, lakeTile.id, platformIndicator, lakeTile.rightColor));
					line3.append(String.format("%3.3s%5.3s%3.3s|", " ", lakeTile.upColor," "));
					line4.append("------------");
				}
				
			}
			
			System.out.println(line1);
			System.out.println(line2);
			System.out.println(line3);
			System.out.println(line4);
		}
	}
	
	/**
	 * @return an array indication a rectangle on the board where the tiles are found
	 */
	public int[] boundaries()
	{
		// [0, 1] row limits, [2, 3] col limits
		int[] result = new int[]{this.board.length-1, 0, this.board[0].length-1, 0};
		//
		for(int j=0; j<this.board[0].length; j++)
		{
			for(int i=0; i<this.board.length; i++)
			{
				if(this.board[i][j] != -1)
				{
					// leferer than left most
					if(j<result[2])
						result[2]=j;
					// righter than right most
					if(j>result[3])
						result[3]=j;
					
					if(i<result[0])
						result[0]=i;
					if(i>result[1])
						result[1]=i;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param id
	 * @return lake tile with the given id
	 */
	public LakeTiles lakeTileFromId(int id)
	{
		for(int i=0; i<this.tilesOnBoard.size(); i++)
		{
			if(this.tilesOnBoard.get(i).id == id)
			{
				return this.tilesOnBoard.get(i);
			}
		}
		
		return null;
	}

}

