package eclipse_project;

import java.util.Random;

/**
 * LakeTiles are created according to number of players.
 *
 */

public class LakeTiles {
	public String left,right,up,down;
	public boolean platform;
	LakeTiles lakeTiles[]=new LakeTiles[22];
	LakeTiles lakeTilesThreePlayers[]=new LakeTiles[27];
	LakeTiles lakeTilesFourPlayers[]=new LakeTiles[32];
	
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
				System.out.println("Right now you can't play with computer.Sorry for the inconveience");
				break;
			case 2:
				//16 tiles on stack + 6 tiles for players
				for(int i=0;i<lakeTiles.length;i++)
				{
					lakeTiles[i]=new LakeTiles();
					lakeTiles[i].left=lakeTiles[i].randomValues();
					lakeTiles[i].right=lakeTiles[i].randomValues();
					lakeTiles[i].up=lakeTiles[i].randomValues();
					lakeTiles[i].down=lakeTiles[i].randomValues();
					if(i%2==0)
					lakeTiles[i].platform=true;
				}	
				for(int i=0;i<lakeTiles.length;i++)
				{
					System.out.println("left"+lakeTiles[i].left+"right"+lakeTiles[i].right+"up"+lakeTiles[i].up+"down"+lakeTiles[i].down+"platform"+lakeTiles[i].platform);
				}
				break;
			case 3:
				//16 tiles on stack + 6 tiles for players
				for(int i=0;i<lakeTilesThreePlayers.length;i++)
				{
					lakeTilesThreePlayers[i]=new LakeTiles();
					lakeTilesThreePlayers[i].left=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].right=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].up=lakeTilesThreePlayers[i].randomValues();
					lakeTilesThreePlayers[i].down=lakeTilesThreePlayers[i].randomValues();
					if(i%2==0)
						lakeTilesThreePlayers[i].platform=true;
				}
				
				break;
			case 4:
				//16 tiles on stack + 6 tiles for players
				for(int i=0;i<lakeTilesFourPlayers.length;i++)
				{
					lakeTilesFourPlayers[i]=new LakeTiles();
					lakeTilesFourPlayers[i].left=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].right=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].up=lakeTilesFourPlayers[i].randomValues();
					lakeTilesFourPlayers[i].down=lakeTilesFourPlayers[i].randomValues();
					if(i%2==0)
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
		//String left,right,up,down;
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
