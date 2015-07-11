package eclipse_project;

import java.util.Stack;

/**
 * Dedication Token - Four of kind, Three Pair and Seven unique colors can be replaced with honor value.
 *
 */
public class DedicationTokens {

	private Stack<Integer> fourOfKind = new Stack<Integer>();
	private Stack<Integer> threePair = new Stack<Integer>();
	private Stack<Integer> sevenUnique = new Stack<Integer>();
	private Stack<Integer> genericFour = new Stack<Integer>();
	
	private final int FOUR_KIND_MAX = 8;
	private final int THREE_PAIR_MAX = 9;
	private final int SEVEN_UNIQUE_MAX = 10;
	private final int GENERIC_FOUR_MAX = 4;
	
	public DedicationTokens(int numberOfPlayers,int fourOfKindCount,int threePairCount,int sevenUniqueCount,int genericFourCount) {
	
		switch(numberOfPlayers)
		{
			case 2:
				System.out.println("Will be implemented in build 2.Right now all the dedicationTokens will be used");
				break;
			case 3:
				System.out.println("Will be implemented in build 2.Right now all the dedicationTokens will be used");
				break;
			case 4:
				
				if(fourOfKindCount >= 1 && fourOfKindCount <= FOUR_KIND_MAX)
				{
					for(int i=1;i<=fourOfKindCount;i++)
						this.fourOfKind.push(i);
				}
				
				if(threePairCount >= 1 && threePairCount <= THREE_PAIR_MAX)
				{
				for(int i=1;i<=threePairCount;i++)
					this.threePair.push(i);
				}
				
				if(sevenUniqueCount >= 1 && sevenUniqueCount <= SEVEN_UNIQUE_MAX)
				{
				for(int i=1;i<=sevenUniqueCount;i++)
					this.sevenUnique.push(i);
				}
				/*for(int i=0; i<9; i++)
				{
					fourOfKind.push(fourOfKindValue);
					threePair.push(threePairValue);
					sevenUnique.push(sevenUniqueValue);
					
					if(i==0)
						fourOfKindValue++;//We can have 2 8's as we need nine Dedication Tokens
					
					fourOfKindValue--;
					threePairValue--;
					sevenUniqueValue--;					
				}*/
				
				if(genericFourCount >= 1 && genericFourCount <= GENERIC_FOUR_MAX)
				{
				for(int i=1; i<=genericFourCount;i++)
					this.genericFour.push(GENERIC_FOUR_MAX);
				}
					
		}
	}
	
	/**
	 * method to give one dedicationToken from FourOfKind Stack with its appropriate current value 
	 * @return honor value
	 */
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
	
	/**
	 * method to give one dedicationToken from threePair Stack with its appropriate current value
	 * @return honor value
	 */
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
	
	/**
	 * method to give one dedicationToken from sevenUnique Stack with its appropriate current value.
	 * @return honor value
	 */
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

}
