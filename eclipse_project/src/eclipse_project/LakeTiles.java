package eclipse_project;

import java.util.Random;

/**
 * LakeTiles are created according to number of players.
 *
 */

public class LakeTiles {
	public String leftColor,rightColor,upColor,downColor;
	public String left,right,up,down;
	public int id;
	public boolean platform;
	LakeTiles lakeTiles[]=new LakeTiles[23];
	LakeTiles lakeTilesThreePlayers[]=new LakeTiles[28];
	LakeTiles lakeTilesFourPlayers[]=new LakeTiles[33];
	
	public LakeTiles() {
		// TODO Auto-generated constructor stub
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
				for(int i=0;i<lakeTiles.length;i++)
				{
					System.out.println("id"+lakeTiles[i].id+" "+"leftColor "+" "+lakeTiles[i].leftColor+"rightColor"+" "+lakeTiles[i].rightColor+"upColor"+" "+lakeTiles[i].upColor+"downColorColor"+" "+lakeTiles[i].downColor+"platform"+" "+lakeTiles[i].platform);
				}
				break;
			case 3:
				//18 tiles on stack + 9 tiles for players + start Tile
				for(int i=0;i<lakeTilesThreePlayers.length;i++)
				{
					lakeTilesThreePlayers[i]=new LakeTiles();
					lakeTilesThreePlayers[i].leftColor=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].rightColor=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].upColor=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].downColor=lakeTilesThreePlayers[i].randomValues();
					if(i%2!=0)
						lakeTilesThreePlayers[i].platform=true;
				}
				
				break;
			case 4:
				//20 tiles on stack + 12 tiles for players + start Tile
				for(int i=0;i<lakeTilesFourPlayers.length;i++)
				{
					lakeTilesFourPlayers[i]=new LakeTiles();
					lakeTilesFourPlayers[i].leftColor=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].rightColor=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].upColor=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].downColor=lakeTilesFourPlayers[i].randomValues();
					if(i%2!=0)
						lakeTilesFourPlayers[i].platform=true;
				}				
				break;					
		}
	}
	/**
	 * method to generate random values within range 5 to 11 where each number represents particular color for example 5:red 6:green 
	 *  
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
	
	public static void main(String args[])
	{
		LakeTiles lT = new LakeTiles();
		lT.initializeLakeTiles(2);
		
	}
}
