/*
 * Nickolas Hills
 * EECS 2500
 * Fall 2022
 * Projects 1
 * 
 * CardStack class contains main method. gets inputs for 
 * code and instantiates required objects using the 
 * given data, and also outputs the results
 * for each case. 
 */

import java.util.Scanner;
public class CardStack

{
	public static Scanner input = new Scanner(System.in);
	
	final static int LOOPONCE = 1;
	private static int rows, columns, numCards, flippedCard, numFlips, i;
	private static int numTopFlip, numBottomFlip, numRightFlip, numLeftFlip = 0;
	protected static LinkedStack[][] arrayOfStacks;
	
	public static void main(String[] args)
	{
		//loop to test multiple times (CHANGE BACK TO HIGH NUMBER)
		for(i = 1; i < 50000; i++)
		{
			
			//get input for # of rows and columns of stacks, compute total cards
			rows = input.nextInt();
			columns = input.nextInt();
			
			//if the inputs for n and m are 0 and 0, end the program
			if(rows == 0 && columns == 0) System.exit(0);
			
			numCards = rows * columns;
			
			//compute the total number of flips that will happen
			numFlips = (rows + columns) - 2;
			
			//create an array for the layout of cards
			arrayOfStacks = new LinkedStack[rows][columns];
			
			//loop through each position in the array
			//and push the given card in the position's stack.
			for (int loopRows = 0; loopRows < rows; loopRows++)
			{
				for (int loopColumns = 0; loopColumns < columns; loopColumns++)
				{
					//across the array
					arrayOfStacks[loopRows][loopColumns] = getCard();
				}
				
				//down the array
			}
			
			/*
			 * perform each move as it is given
			 * made into method to clean up main
			 * more info given inside of the method
			 */
			
			performMoves();
			
			
			/*
			 * output the cards in the correct format
			 * more info given in the method
			 */
			outputCards();
			
		}//end of the program loop
		
	}//end of main
	
	
	//gets the card's info from input. more info enclosed
	private static LinkedStack getCard()
	{
		/*
		 * getCard() method receives information for the
		 * card, checks to see if it is valid format,
		 * converts it into an integer.
		 * then creates a stack and pushes it on top
		 * and returns that stack.
		 */
		
		int cardInt = 0;
		
		LinkedStack<Integer> card = new LinkedStack<Integer>();
		
		/* KEY FOR integer values for each card
		
		  	Suit	Value	  card#		(u is + and d is -)
			Dd		1-13	  A = 1 2=2 .... J = 11 Q = 12 K = 13
		  	Cc		14-26
		  	Hh		27-39
		  	Ss		40-52
		
		 */
		
		String cardInfo = input.next();
			
		if (cardInfo.matches("[UDud](.*)") == false) 
			{
			System.exit(0);
		}
		
		if (cardInfo.matches("(.*)[CDHScdhs](.*)") == false)
		{
			System.exit(0);
		}
		
		if (cardInfo.matches("(.*)[2-9]") == false && cardInfo.matches("(.*)[TtJjQqKkAa]") == false)
		{
			System.exit(0);
		}
		
		for (int j = 0; j < numCards; j++)
		{
			cardInt = 0;
			//add to get to suit
			
			// if Dd
			// cardInt = cardInt (0)
		
			if (cardInfo.matches("(.*)[Cc](.*)"))
			{
				cardInt = cardInt + 13;
			}
			if (cardInfo.matches("(.*)[Hh](.*)"))
			{
				cardInt = cardInt + 26;
			}
			if (cardInfo.matches("(.*)[Ss](.*)"))
			{
				cardInt = cardInt + 39;
			}
			
		//add to get to card number
			
			if (cardInfo.matches(("(.*)[Aa]")))
			{
				cardInt = cardInt + 1;
			}
		
			if (cardInfo.matches(("(.*)[2]")))
			{
				cardInt = cardInt + 2;
			}
		
			if (cardInfo.matches(("(.*)[3]")))
			{
				cardInt = cardInt + 3;
			}
			
			if (cardInfo.matches(("(.*)[4]")))
			{
				cardInt = cardInt + 4;
			}
			
			if (cardInfo.matches(("(.*)[5]")))
			{
				cardInt = cardInt + 5;
			}
			
			if (cardInfo.matches(("(.*)[6]")))
			{
				cardInt = cardInt + 6;
			}
			
			if (cardInfo.matches(("(.*)[7]")))
			{
				cardInt = cardInt + 7;
			}
			
			if (cardInfo.matches(("(.*)[8]")))
			{
				cardInt = cardInt + 8;
			}
			
			if (cardInfo.matches(("(.*)[9]")))
			{
				cardInt = cardInt + 9;
			}
			
			if (cardInfo.matches(("(.*)[Tt]")))
			{
				cardInt = cardInt + 10;
			}
			
			if (cardInfo.matches(("(.*)[Jj]")))
			{
				cardInt = cardInt + 11;
			}
			if (cardInfo.matches(("(.*)[Qq]")))
			{
				cardInt = cardInt + 12;
			}
			if (cardInfo.matches(("(.*)[Kk]")))
			{
				cardInt = cardInt + 13;
			}
			
			//if card is face down, it is negative
			if (cardInfo.matches("[Dd](.*)"))
			{
				cardInt = (cardInt * -1);
			}
				
		}
		
		//push the integer value of card onto stack and return stack.
			card.push(cardInt);
	
			return card;
		
	}

	
	//performs a top flip. more info enclosed
	private static void topFlip()
	{
		/*
		 * topFlip() method flips the top row of cards
		 * onto the row below it. changes the way cards face
		 * up -> down, down -> up. adds the card to 
		 * the stack below it.
		 */
		
		int currentCard;
		int currentRow = numTopFlip; //row# is 0 if you have not performed topflip, and 1 if you have. etc
		
		//loop through array, start row position according to how
		//many times you have performed top flip.
		//this ensures that you start in the top row of the array
		//so if you perform it once, you start in the 2nd row
		//stay in the same row
		
		int rowBelow = currentRow + 1;//the row below is current row + 1
		
		for (int loopColumns = 0; loopColumns < arrayOfStacks[currentRow].length; loopColumns++)
		{
			//loop through array always starting in the first column.
			//each time through loop goes to the column to the right
			//loop until you have reached the last column
			
			/*across the array
			get the current card and multiply by -1 so
			you flip its orientation*/
			
			//while the stack in the current array position contains something
			while (arrayOfStacks[currentRow][loopColumns].isEmpty() == false)
			{
				//get the top card and flip it.
				currentCard = (int) arrayOfStacks[currentRow][loopColumns].top();
				flippedCard = currentCard * -1;
				//remove the card we just took off the top of the stack
				arrayOfStacks[currentRow][loopColumns].pop();
				//add the flipped card to the stack directly below
				arrayOfStacks[rowBelow][loopColumns].push(flippedCard);
			}
			
		}//out of loop
		
		numTopFlip = numTopFlip + 1; //we have performed topFlip one more time
		
	}//end of topFlip()
	
	//performs bottom flip. more info enclosed
	private static void bottomFlip()
	{
		int currentCard;
		
		int currentRow = rows - 1 - numBottomFlip;//start at the bottom row
		int rowAbove = currentRow - 1;
		
		for (int loopColumns = 0; loopColumns < arrayOfStacks[currentRow].length; loopColumns++)
		{
			//loop through the columns in the same row
			//always starting at the first column
			//loop until you reach the last column
			
			//while the stack in the current position of the array is not empty
			while(arrayOfStacks[currentRow][loopColumns].isEmpty() == false)
			{
				//get the top card and flip it
				currentCard = (int) arrayOfStacks[currentRow][loopColumns].top();
				flippedCard = currentCard * -1;
				//remove the card off the top of the stack
				arrayOfStacks[currentRow][loopColumns].pop();
				//push the flipped card onto the row above it, in the same column
				arrayOfStacks[rowAbove][loopColumns].push(flippedCard);
			}
			
		}//out of loop
		
		numBottomFlip = numBottomFlip + 1;//we have performed bottom flip one more time
	}
	
	//performs left flip. more info enclosed
	private static void leftFlip()
	{
		/*start in the numLeftFlip'th column
		(which is the furthest left)
		and increment down through each row,
		taking each card and flipping it onto the 
		numLeftFlip + 1 column.
		this means you take each card, and
		add it to the stack directly to the right
		of it.
		*/
		
		int currentCard;
		int currentColumn = numLeftFlip;
		int nextColumn = currentColumn + 1;
		
		for (int loopRow = 0; loopRow < arrayOfStacks.length; loopRow++)
		{
			//loop down the rows in the same column
			//always start at top row
			//loop through to the bottom row
			
			//while the stack in the current position is not empty
			while (arrayOfStacks[loopRow][currentColumn].isEmpty() == false)
			{
				//get the card at top of stack and flip it
				currentCard = (int) arrayOfStacks[loopRow][currentColumn].top();
				flippedCard = currentCard * -1;
				//push the card onto the stack directly to the right of it
				//and pop it off of its original stack.
				arrayOfStacks[loopRow][currentColumn].pop();
				arrayOfStacks[loopRow][nextColumn].push(flippedCard);
				
			}
		}//out of loop
		
		numLeftFlip = numLeftFlip + 1;//increment the number of times this method has been performed
	}
	
	//performs right flip. more info enclosed
	private static void rightFlip()
	{
		/*start in the far right column
		and increment down through each row,
		taking each card and flipping it onto the 
		column directly left of it.
		*/
		
		int currentCard;
		int currentColumn = columns - 1 - numRightFlip;//start at the far right row
		
		//current column is total columns, minus how many times you've performed rightflip
		int leftColumn = currentColumn - 1;//column to the left
		
		for (int loopRow = 0; loopRow < arrayOfStacks.length; loopRow++)
		{
			//loop down the rows in the same column
			//always start at top row
			//loop through to the bottom row
			
			//while the stack in the current position is not empty
			while (arrayOfStacks[loopRow][currentColumn].isEmpty() == false)
			{
				//get the card at top of stack and flip it
				currentCard = (int) arrayOfStacks[loopRow][currentColumn].top();
				flippedCard = currentCard * -1;
				//push the card onto the stack directly to the right of it
				//and pop it off of its original stack.
				arrayOfStacks[loopRow][currentColumn].pop();
				arrayOfStacks[loopRow][leftColumn].push(flippedCard);
				
			}
		}//out of loop
		
		numRightFlip = numRightFlip + 1;//increment the number of times this method has been performed
	}
	
	//gets input for move and performs it
	private static void performMoves()
	{
		
		String flipString = "";
		if (rows == 1 && columns == 1)
			return;
		flipString = input.next();
		
		
		//loop through each character of the string
		//perform the move of each character
		
		for (int i = 0; i < flipString.length(); i++)
		{
			char flipType = flipString.charAt(i);
			
			if (flipType == 't' || flipType == 'T')
			{
				topFlip();
			}
			else if (flipType == 'b' || flipType == 'B')
			{
				bottomFlip();
			}
			else if (flipType == 'l' || flipType == 'L')
			{
				leftFlip();
			}
			else if (flipType == 'r' || flipType == 'R')
			{
				rightFlip();
			}
		}
			
	}//end of perform moves loop
	
	//outputs cards in the correct format
	private static void outputCards()
	{
		System.out.print("Test " + i + ": ");
		//reset all of the flips between each test.
		numTopFlip = 0;
		numBottomFlip = 0;
		numLeftFlip = 0;
		numRightFlip = 0;
		LinkedStack<String> cards = new LinkedStack<String>();
		for (int arrayRow = 0; arrayRow < arrayOfStacks.length; arrayRow++)
		{
			for (int arrayColumn = 0; arrayColumn < arrayOfStacks[arrayRow].length; arrayColumn++)
			{
				if (arrayOfStacks[arrayRow][arrayColumn].isEmpty() == false)
				{
					while(arrayOfStacks[arrayRow][arrayColumn].isEmpty() == false)
					{
						int cardInt = 0;
						cardInt = (int) arrayOfStacks[arrayRow][arrayColumn].topPop();
						cardInt = cardInt * -1;
						String cardString = "";
						/*
					  	Suit	Value	  card#		(u is + and d is -)
						Dd		1-13	  A = 1 2=2 .... J = 11 Q = 12 K = 13
					  	Cc		14-26
					  	Hh		27-39
					  	Ss		40-52
					 */
						if (cardInt <= 13)
						{
							cardString = "D";
						}
						else if (cardInt <= 26)
						{
							cardString = "C";
							cardInt = cardInt - 13;
						}
						else if (cardInt <= 39)
						{
							cardString = "H";
							cardInt = cardInt - 26;
						}
						else if (cardInt <= 52)
						{
							cardString = "S";
							cardInt = cardInt - 39;
						}
						
						if(cardInt == 1)
						{
							cardString = cardString + "A";
							cards.push(cardString);
							
						}
						if(cardInt == 2)
						{
							cardString = cardString + "2";
							cards.push(cardString);
						}
						if(cardInt == 3)
						{
							cardString = cardString + "3";
							cards.push(cardString);
						}
						if(cardInt == 4)
						{
							cardString = cardString + "4";
							cards.push(cardString);
						}
						if(cardInt == 5)
						{
							cardString = cardString + "5";
							cards.push(cardString);
						}
						if(cardInt == 6)
						{
							cardString = cardString + "6";
							cards.push(cardString);
						}
						if(cardInt == 7)
						{
							cardString = cardString + "7";
							cards.push(cardString);
						}
						if(cardInt == 8)
						{
							cardString = cardString + "8";
							cards.push(cardString);
						}
						if(cardInt == 9)
						{
							cardString = cardString + "9";
							cards.push(cardString);
						}
						if(cardInt == 10)
						{
							cardString = cardString + "T";
							cards.push(cardString);
						}
						if(cardInt == 11)
						{
							cardString = cardString + "J";
							cards.push(cardString);
						}
						if(cardInt == 12)
						{
							cardString = cardString + "Q";
							cards.push(cardString);
						}
						if(cardInt == 13)
						{
							cardString = cardString + "K";
							cards.push(cardString);
						}
						
					}
				}
				
			}
		}
		
		while(cards.isEmpty() == false)
		{
			System.out.print(cards.topPop() + " ");
		}
		System.out.println("");
		
	}//end of outputCard method
	
}//end of class
