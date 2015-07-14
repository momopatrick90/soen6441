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
	 * method to loads the saved game board
	 */
	// public void initializeSavedGameBoard()
}
