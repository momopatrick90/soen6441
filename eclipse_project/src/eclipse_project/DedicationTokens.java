package eclipse_project;

import java.util.Stack;

/**
 * Dedication Token - Four of kind, Three Pair and Seven unique colors can be
 * replaced with honor value.
 *
 */
public class DedicationTokens {

	public Stack<Integer> fourOfKind = new Stack<Integer>();
	public Stack<Integer> threePair = new Stack<Integer>();
	public Stack<Integer> sevenUnique = new Stack<Integer>();
	public Stack<Integer> genericFour = new Stack<Integer>();

	private final int FOUR_KIND_MAX = 8;
	private final int THREE_PAIR_MAX = 9;
	private final int SEVEN_UNIQUE_MAX = 10;
	private final int GENERIC_FOUR_MAX = 4;

	/**
	 * Constructor to initialize the Dedication Tokens that depends on the
	 * number of players at the start of the game.
	 * 
	 * @param numberOfPlayers
	 *            - Number of players playing game.
	 */
	public DedicationTokens(int numberOfPlayers) {
		int fourOfKindValue = 1, threePairValue = 1, sevenUniqueValue = 2;
		
		if(numberOfPlayers == 2 || numberOfPlayers == 3 || numberOfPlayers == 4){
			for (int i = 0; i < 9; i++) {
				this.fourOfKind.push(fourOfKindValue);
				this.threePair.push(threePairValue);
				this.sevenUnique.push(sevenUniqueValue);

				fourOfKindValue++;
				threePairValue++;
				sevenUniqueValue++;

				if (i == 7) {
					fourOfKindValue--;
				}

			}
			for (int i = 0; i < 3; i++)
				this.genericFour.push(GENERIC_FOUR_MAX);
			
		}
	

	}

	/**
	 * Constructor to initialize the Dedication Tokens while loading the saved
	 * game.
	 * 
	 * @param numberOfPlayers
	 *            - Number of players playing game.
	 * @param fourOfKindCount
	 *            - Number of fourOfKindCount Dedication cards.
	 * @param threePairCount
	 *            - Number of threePairCount Dedication cards.
	 * @param sevenUniqueCount
	 *            - Number of sevenUniqueCount Dedication cards.
	 * @param genericFourCount
	 *            - Number of genericFourCount Dedication cards.
	 */
	public DedicationTokens(int numberOfPlayers, int fourOfKindCount,
			int threePairCount, int sevenUniqueCount, int genericFourCount) {

		if (fourOfKindCount >= 1 && fourOfKindCount <= 9) {
			for (int i = 1; i <= fourOfKindCount; i++)
				if (i == 9)
					this.fourOfKind.push(8);
				else
					this.fourOfKind.push(i);
		}

		if (threePairCount >= 1 && threePairCount <= 9) {
			for (int i = 1; i <= threePairCount; i++)
				this.threePair.push(i);
		}

		if (sevenUniqueCount >= 1 && sevenUniqueCount <= 9) {
			for (int i = 1; i <= sevenUniqueCount; i++)
				this.sevenUnique.push(i);
		}

		if (genericFourCount >= 1 && genericFourCount <= 3) {
			for (int i = 1; i <= genericFourCount; i++)
				this.genericFour.push(GENERIC_FOUR_MAX);
		}

	}

	/**
	 * method to give one dedicationToken from FourOfKind Stack with its
	 * appropriate current value
	 * 
	 * @return honor value
	 */
	public int getFourOfKind() {
		int result = 0;
		if (fourOfKind.isEmpty()) {
			if (!genericFour.isEmpty()) {
				result = genericFour.pop();
			}
		} else {
			result = fourOfKind.pop();
		}
		return result;
	}

	/**
	 * method to give one dedicationToken from threePair Stack with its
	 * appropriate current value
	 * 
	 * @return honor value
	 */
	public int getThreePair() {
		int result = 0;
		if (threePair.isEmpty()) {
			if (!genericFour.isEmpty()) {
				result = genericFour.pop();
			}
		} else {
			result = threePair.pop();
		}
		return result;
	}

	
	/**
	 * method to return the current size of threePair Stack
	 * 
	 * @return stack size
	 */
	public int threePairCount() {
		return this.threePair.size();
	}

	/**
	 * method to give one dedicationToken from sevenUnique Stack with its
	 * appropriate current value.
	 * 
	 * @return honor value
	 */
	public int getSevenUnique() {
		int result = 0;
		if (sevenUnique.isEmpty()) {
			if (!genericFour.isEmpty()) {
				result = genericFour.pop();
			}
		} else {
			result = sevenUnique.pop();
		}
		return result;
	}

	/**
	 * method to give the four of a kind dedication available cards count
	 * @return four of a kind dedication available cards count 
	 */
	public int fourOfKindCount() {
		return this.fourOfKind.size();
	}
	
	/**
	 * method to give the seven unique dedication available cards count
	 * @return seven unique dedication available cards count 
	 */
	public int sevenUniqueCount() {
		return this.sevenUnique.size();
	}

	/**
	 * method to give the generic four available cards count
	 * @return generic four dedication available cards count 
	 */
	public int genericFourCount() {
		return this.genericFour.size();
	}

	/**
	 * Prints the state of four of a kind dedication cards
	 */
	public void getFourOfKindInfo() {
		System.out
				.println("Correct Version with 3 dots and 4 dots dedication Tokens will be implemented in build 2.Right now all the dedicationTokens will be used");
		for (int i = fourOfKind.size() - 1; i >= 0; i--) {
			System.out.print(fourOfKind.get(i) + " ");
		}
	}

	/**
	 * Prints the state of three pair dedication cards
	 */
	public void getThreePairInfo() {
		for (int i = threePair.size() - 1; i >= 0; i--) {
			System.out.print(threePair.get(i) + " ");
		}
	}
	
	/**
	 * Prints the state of seven unique dedication cards
	 */
	public void getSevenUniqueInfo() {
		for (int i = sevenUnique.size() - 1; i >= 0; i--) {
			System.out.print(sevenUnique.get(i) + " ");
		}
	}

	/**
	 * Prints the state of generic four dedication cards
	 */
	public void getGenericFourInfo() {
		for (int i = genericFour.size() - 1; i >= 0; i--) {
			System.out.print(genericFour.get(i) + " ");
		}
	}
	/**
	 * method to return the top value of FourOFKind Dedication
	 * @return the top value FourOfKind Dedication 
	 */
	public int peekFourOfKind()
	{
		return this.fourOfKind.peek();
	}
	
	/**
	 * method to return the top value of ThreePairs Dedication
	 * @return the top value ThreePairs Dedication 
	 */
	public int peekThreePairs()
	{
		return this.threePair.peek();
	}
	
	/**
	 * method to return the top value of SevenUnique Dedication
	 * @return the top value SevenUnique Dedication 
	 */
	public int peekSevenUnique()
	{
		return this.sevenUnique.peek();
	}
}

