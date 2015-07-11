package eclipse_project;

import java.util.Stack;

public class DedicationTokens {

	public DedicationTokens() {
		// TODO Auto-generated constructor stub
	}
	Stack<Integer> fourOfKind = new Stack();
	Stack<Integer> threePair = new Stack();
	Stack<Integer> sevenUnique = new Stack();
	Stack<Integer> genericFour = new Stack();
	public void initializeDedicationTokens(int numberOfPlayers)
	{
		switch(numberOfPlayers)
		{
			case 1:
				System.out.println("Right now you can't play with computer.Sorry for the inconveience");
				break;
			case 2:
				System.out.println("Will be implemented in build 2.Right now all the dedicationTokens will be used");
				break;
			case 3:
				System.out.println("Will be implemented in build 2.Right now all the dedicationTokens will be used");
				break;
			case 4:
				int fourOfKindValue=8,threePairValue=9,sevenUniqueValue=10,genericFourValue=4;
				for(int i=0;i<9;i++)
				{
					fourOfKind.push(fourOfKindValue);
					threePair.push(threePairValue);
					sevenUnique.push(sevenUniqueValue);
					if(i==0)
						fourOfKindValue++;//We can have 2 8's as we need nine Dedication Tokens
					fourOfKindValue--;
					threePairValue--;
					sevenUniqueValue--;					
				}
				for(int i=0;i<3;i++)
				{
					genericFour.push(genericFourValue);
				}
					
		}
	}
	public int getFourOfKind()
	{
		int result=0;
		if(fourOfKind.isEmpty())
		{
			if(!genericFour.isEmpty())
			{
				result=genericFour.pop();
			}
		}
		else
		{
			result=fourOfKind.pop();
		}
		return result;
	}
	public int getThreePair()
	{
		int result=0;
		if(threePair.isEmpty())
		{
			if(!genericFour.isEmpty())
			{
				result=genericFour.pop();
			}
		}
		else
		{
			result=threePair.pop();
		}
		return result;
	}
	public int getSevenUnique()
	{
		int result=0;
		if(sevenUnique.isEmpty())
		{
			if(!genericFour.isEmpty())
			{
				result=genericFour.pop();
			}
		}
		else
		{
			result=sevenUnique.pop();
		}
		return result;
	}
	
	public static void main(String args[])
	{
		DedicationTokens Dt = new DedicationTokens();
		Dt.initializeDedicationTokens(4);
	}

}
