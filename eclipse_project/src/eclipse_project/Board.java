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
	 * method to display the current state of boardGame
	 * 
	 * @param board
	 *            gameBoard
	 * @param tilesOnBoard
	 *            LakeTiles placed on board
	 */
	public void displayBoard(int board[][], ArrayList<LakeTiles> tilesOnBoard) {
		System.out.println("Current state of boardGame: ");
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != -1) {
					for (int k = 0; k < tilesOnBoard.size(); k++)
						if (board[i][j] == tilesOnBoard.get(k).id) {
							System.out.println("LakeTile id :"
									+ tilesOnBoard.get(k).id + " " + "Row : "
									+ i + " " + "Column : " + j + " "
									+ "leftColor:"
									+ tilesOnBoard.get(k).leftColor
									+ " rightColor:"
									+ tilesOnBoard.get(k).rightColor
									+ " upColor:" + tilesOnBoard.get(k).upColor
									+ " downColor:"
									+ tilesOnBoard.get(k).downColor + " "
									+ tilesOnBoard.get(k).platform);
							/*
							 * System.out.println("leftNeighbour " +
							 * tilesOnBoard.get(k).left + " rightNeighbour " +
							 * tilesOnBoard.get(k).right + " downNeighbour " +
							 * tilesOnBoard.get(k).down + " upNeighbour " +
							 * tilesOnBoard.get(k).up);
							 */
						}
					System.out.println();
				}
			}
		}
		/*
		 * boolean lineEmpty = true; int firstOcurrence=0; for (int i = 0; i <
		 * board.length; i++) { for (int j = 0; j < board.length; j++) { for
		 * (int k = 0; k < board.length; k++) { if (board[i][k] != -1) {
		 * lineEmpty = false; firstOcurrence++; }
		 * 
		 * } if (!lineEmpty) { if(firstOcurrence==1) { if (board[i][j] == -1)
		 * System.out.print(" "); else { if (board[i][j] > 10){
		 * System.out.print("\t"+board[i][j]+"\t"); } else
		 * System.out.print("\t"+board[i][j]+"\t"); } } else { if (board[i][j]
		 * == -1) System.out.print(" "); else { if (board[i][j] > 10){
		 * System.out.print("\t"+board[i][j]+" \t"); } else
		 * System.out.print("\t"+board[i][j]+"\t"); } } //
		 * System.out.print("("+board[i][j]+")"); } } if (!lineEmpty)
		 * System.out.println(); lineEmpty = true; }
		 */

		System.out.println("View of the laketiles on board");
		System.out.println("-------------------------------------");
		int i = 0, j = 0;
		one: for (i = 0; i < board.length; i++) {
			for (j = 0; j < board.length; j++) {
				if (board[i][j] != -1)
					break one;
			}
		}
		int l = i;
		int m = j;
		int count = 0;
		two: for (i = l; i < board.length; i++) {
			for (j = m; j < board.length; j++) {
				if (board[i][j] == -1) {
					count++;
					System.out.print("   ");
				} else if (board[i][j] > 10) {
					System.out.print(" " + board[i][j]);
				} else {
					System.out.print("  " + board[i][j]);
				}
			}
			if (count == 36) {
				break two;
			}
			count = 0;
			System.out.println();
		}
		System.out.println();
		System.out.println("-------------------------------------");
	}

	
	public ArrayList availableSpaces() {
		ArrayList availableSpace = new ArrayList();
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
}