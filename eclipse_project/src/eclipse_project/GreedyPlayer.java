package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class GreedyPlayer implements PlayerStrategy
{

	public GreedyPlayer(String name) {
		//super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(GameEngine gameEngine, Player player)
			throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		exchangeLanternCards(gameEngine,player);
		
	}

	protected void exchangeLanternCards(GameEngine gameEngine, Player player) throws IOException
	{
		int lanternCard = 0;
		String returnLCard = "";
		String pickLCard = "";
		String regex = "\\d+";
		Scanner in = new Scanner(System.in);
		//
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		
		// Print lanterns
		System.out.println("--Lantern cards you currently have:--");
		System.out.println(player.getLanternCards());
		System.out.println("----------------------------");
		
		//
		System.out.println("Select the lantern card you want to return.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		int peekFourOfKind = gameEngine.dedicationTokens.peekFourOfKind();
		int peekThreePairs = gameEngine.dedicationTokens.peekThreePairs();
		int peekSevenUnique = gameEngine.dedicationTokens.peekSevenUnique();
		
		
		int secondMax=(peekFourOfKind >peekThreePairs)?peekFourOfKind:peekThreePairs;
		int max=(peekSevenUnique >secondMax)?peekSevenUnique:secondMax;
		int thirdValue=0;
		if(max==peekFourOfKind)
		{
			if(secondMax==peekThreePairs)
				thirdValue=peekSevenUnique;
		}
		else if(max==peekThreePairs)
		{
			if(secondMax==peekSevenUnique)
				thirdValue=peekFourOfKind;
		}
		else
			thirdValue=peekThreePairs;
			
		/*exchangeLanternCardGreedy();
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		boolean check = true;
		while (check) {
			in = new Scanner(System.in);
			returnLCard = in.nextLine();
			returnLCard.trim();
			if (!returnLCard.matches(regex)) {
				System.out.println("Invalid lantern card option");
				check = true;
			} else {
				lanternCard = Integer.valueOf(returnLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid lantern card option");
				} else {
					check = false;
				}
			}

		}
		switch(lanternCard)
		{
			case 1: returnLCard = "redCard";break;
			case 2: returnLCard = "greenCard";break;
			case 3: returnLCard = "purpleCard";break;
			case 4: returnLCard = "orangeCard";break;
			case 5: returnLCard = "whiteCard";break;
			case 6: returnLCard = "blackCard";break;
			case 7: returnLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		lanternCard = 0;
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		check = true;
		while (check) {
			in = new Scanner(System.in);
			pickLCard = in.nextLine();
			pickLCard.trim();
			if (!pickLCard.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				lanternCard = Integer.valueOf(pickLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		//
		switch(lanternCard)
		{
			case 1: pickLCard = "redCard";break;
			case 2: pickLCard = "greenCard";break;
			case 3: pickLCard = "purpleCard";break;
			case 4: pickLCard = "orangeCard";break;
			case 5: pickLCard = "whiteCard";break;
			case 6: pickLCard = "blackCard";break;
			case 7: pickLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		boolean moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
						returnLCard, pickLCard);
		if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");
	}

}
