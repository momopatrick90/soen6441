package eclipse_project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * LakeTiles are created according to number of players.
 *
 */

public class LakeTiles {
	public String leftColor,rightColor,upColor,downColor;
	public int left,right,up,down;
	public int id;
	public boolean platform;
	public LakeTiles lakeTiles[];
	public Stack<LakeTiles> globalLakeTiles = new Stack<LakeTiles>();
	
	public LakeTiles() {
		super();
	}
	
	/**
	 * method to generate lake tiles with according to number of players where some of the tiles will have platform 
	 * @param numberOfPlayers total number of players 
	 */
	public void initializeLakeTiles(int numberOfPlayers)
	{
		switch(numberOfPlayers)
		{
			case 1:
				System.out.println("rightColor now you can't play with computer.Sorry for the inconveience");
				break;
			case 2:
				lakeTiles = new LakeTiles[23];
				//16 tiles on stack + 6 tiles for players + start Tile
				for(int i=0;i<lakeTiles.length;i++)
				{
					lakeTiles[i]=new LakeTiles();
					lakeTiles[i].leftColor=lakeTiles[i].randomValues();
					lakeTiles[i].rightColor=lakeTiles[i].randomValues();
					lakeTiles[i].upColor=lakeTiles[i].randomValues();
					lakeTiles[i].downColor=lakeTiles[i].randomValues();
					lakeTiles[i].id=i+1;
					if(i%2!=0)
					lakeTiles[i].platform=true;
				}	
				
				
				break;
			case 3:
				//18 tiles on stack + 9 tiles for players + start Tile
				lakeTiles = new LakeTiles[28];
				for(int i=0;i<lakeTiles.length;i++)
				{
					lakeTiles[i]=new LakeTiles();
					lakeTiles[i].leftColor=lakeTiles[i].randomValues();
					lakeTiles[i].rightColor=lakeTiles[i].randomValues();
					lakeTiles[i].upColor=lakeTiles[i].randomValues();
					lakeTiles[i].downColor=lakeTiles[i].randomValues();
					lakeTiles[i].id=i+1;
					if(i%2!=0)
						lakeTiles[i].platform=true;
				}
				
				break;
			case 4:
				//20 tiles on stack + 12 tiles for players + start Tile
				lakeTiles = new LakeTiles[33];
				for(int i=0;i<lakeTiles.length;i++)
				{
					lakeTiles[i]=new LakeTiles();
					lakeTiles[i].leftColor=lakeTiles[i].randomValues();
					lakeTiles[i].rightColor=lakeTiles[i].randomValues();
					lakeTiles[i].upColor=lakeTiles[i].randomValues();
					lakeTiles[i].downColor=lakeTiles[i].randomValues();
					lakeTiles[i].id=i+1;
					if(i%2!=0)
						lakeTiles[i].platform=true;
				}			
				/*for(int i=0;i<lakeTiles.length;i++)
				{
					System.out.println("id"+lakeTiles[i].id+" "+"leftColor "+" "+lakeTiles[i].leftColor+"rightColor"+" "+lakeTiles[i].rightColor+"upColor"+" "+lakeTiles[i].upColor+"downColorColor"+" "+lakeTiles[i].downColor+"platform"+" "+lakeTiles[i].platform);
				}*/
				break;					
		}
	}
	
	/**
	 * method to assign 3 LakeTiles to each Player 
	 * @param numberOfPlayers total number of players 
	 * @return list of lakeTiles
	 */
	public ArrayList<LakeTiles> assignLakeTiles(int numberOfPlayers)
	{
		ArrayList<LakeTiles> assignedLakeTiles = new ArrayList<LakeTiles>();
		switch(numberOfPlayers)
		{
			case 2:
				for(int i=1;i<numberOfPlayers*3+1;i++)
					assignedLakeTiles.add(lakeTiles[i]);
				for(int i=numberOfPlayers*3+1;i<lakeTiles.length;i++)
				{
					globalLakeTiles.push(lakeTiles[i]);
				}
					break;
			case 3:
				for(int i=1;i<numberOfPlayers*3+1;i++)
					assignedLakeTiles.add(lakeTiles[i]);
				for(int i=numberOfPlayers*3+1;i<lakeTiles.length;i++)
				{
					globalLakeTiles.push(lakeTiles[i]);
				}
					break;
			case 4:
				for(int i=1;i<numberOfPlayers*3+1;i++)
					assignedLakeTiles.add(lakeTiles[i]);
				for(int i=numberOfPlayers*3+1;i<lakeTiles.length;i++)
				{
					globalLakeTiles.push(lakeTiles[i]);
				}
					break;
		}
		return assignedLakeTiles;		
	}
	

	/**
	 * method to get a single LakeTile from Stack of LakeTiles
	 * @return LakeTile
	 */
	public LakeTiles getLakeTile()
	{
		LakeTiles top = globalLakeTiles.pop();
		return top;
	}
	
	/**
	 * method to place a new Tile on board
	 * @param x coordinate of position where user want to place a tile
	 * @param y coordinate of position where user want to place a tile
	 * @param Board
	 * @param LakeTile to be placed on board
	 * @return true if the tile is placed successfully 
	 */
	public boolean placeTile(int x,int y,Board gameBoard,LakeTiles tileToPlace)
	{
		boolean flag=false;
		if(gameBoard.board[x][y]==-1)
		{
			gameBoard.board[x][y]=tileToPlace.id;
			gameBoard.tilesOnBoard.add(tileToPlace);
			if(gameBoard.board[x+1][y]!=-1)
			{
				tileToPlace.right=gameBoard.board[x+1][y];
				for(int i=0;i<gameBoard.tilesOnBoard.size();i++)
				{
					if(gameBoard.tilesOnBoard.get(i).id==gameBoard.board[x+1][y])
						gameBoard.tilesOnBoard.get(i).left=tileToPlace.id;
				}
			}
			if(gameBoard.board[x][y+1]!=-1)
			{
				tileToPlace.down=gameBoard.board[x][y+1];
				for(int i=0;i<gameBoard.tilesOnBoard.size();i++)
				{
					if(gameBoard.tilesOnBoard.get(i).id==gameBoard.board[x][y+1])
						gameBoard.tilesOnBoard.get(i).up=tileToPlace.id;
				}
			}
			if(gameBoard.board[x-1][y]!=-1)
			{
				tileToPlace.left=gameBoard.board[x-1][y];
				for(int i=0;i<gameBoard.tilesOnBoard.size();i++)
				{
					if(gameBoard.tilesOnBoard.get(i).id==gameBoard.board[x-1][y])
						gameBoard.tilesOnBoard.get(i).right=tileToPlace.id;
				}
			}
			if(gameBoard.board[x][y-1]!=-1)
			{
				tileToPlace.up=gameBoard.board[x][y-1];
				for(int i=0;i<gameBoard.tilesOnBoard.size();i++)
				{
					if(gameBoard.tilesOnBoard.get(i).id==gameBoard.board[x][y-1])
						gameBoard.tilesOnBoard.get(i).down=tileToPlace.id;
				}
			}
			flag=true;
		}
		else
			System.out.println("LakeTile is already present on that location.Please choose another loacation");
		return flag;
	}
	/**
	 * method to generate random values within range 5 to 11 where each number represents particular color for example 5:red 6:green 
	 * @return String with one of the given colors
	 */	
	public String randomValues()
	{
		String answer=null;
		//String leftColor,rightColor,upColor,downColorColor;
		int x;
		
			Random ran = new Random();
			x = ran.nextInt(6) + 5;
			//System.out.println(x);
			switch(x)
			{
				case 5:
					answer="red";
					break;
				case 6:
					answer="green";
					break;
				case 7:
					answer="blue";
					break;
				case 8:
					answer="white";
					break;
				case 9:
					answer="black";
					break;
				case 10:
					answer="orange";
					break;
				case 11:
					answer="purple";
					break;
			}
			return answer;		
	}
	
	
}
