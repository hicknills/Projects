import java.util.Scanner;
import java.io.*;

/*
 * Nickolas Hills
 * EECS 2500
 * Fall 2022
 * Project 2
 * "Four Lists"
 * the main class containing the main method.
 * program runs with an text file as the input, and scans through the file.
 * it generates 4 different lists using different methods, and displays 
 * the time it took to generate the list, # of distinct words in the file,
 * total # of w ords in the file, and the amount of reference changes that
 * happened while generating each list.
 */

public class lab2 {

	public static void main(String[] args) throws FileNotFoundException 
	{
		//File file = new File(args[0]);
		File file = new File("C:\\Users\\hickn\\OneDrive\\Documents\\Fall 2022\\EECS 2500 Linear Data Structures\\proj 2 2500 (four lists)\\Project 2\\Text Files\\Test.txt");
		//header
		System.out.println(" #    List Name      Run Time  Vocabulary  Total Words   Key Comp     Ref Chgs     h");
		//separator lines
		System.out.println("__ _________________ ________  __________  ___________ ____________ ____________  ___");
		int n = 0;
		for(int i = 0; i < 8; i++)
			/*loop 8 times. 
			 * on the 1st time:
			 * read every word, and close the file.
			 * 
			 * the 2nd time:
			 * read every word, time how long it takes
			 * ("overhead") to read the file, and remove punctuation.
			 * and close the file
			 * 
			 * on 3rd pass:
			 * open file, build list1, output information and close file
			 * 
			 * 
			 * on 4th pass:
			 * open file, build list2, output info and close
			 * 
			 * 
			 * on 5th:
			 * open file, build list 3, output and close
			 * 
			 *  
			 * on 6th and final:
			 * open file, build list 4, output and close
			 * 
			 * */
		{
			
			ListInterface[] Lists = new ListInterface[6]; // By creating the lists as an array,
			 // we can iterate with a subscript
			Lists[0] = new List1(); // Unsorted, insertions at beginning, no self-optimization
			
			Lists[1] = new List2(); // Sorted linked list
			Lists[2] = new List3(); // Unsorted, heavy-handed self-adjusting (moves repeated
			 // word to the front of the list)
			Lists[3] = new List4(); // Unsorted, conservative self-adjusting (moves repeated
			 // word one position towards front of list)
			//
			Lists[4] = new List5();
			
			Lists[5] = new List2a();
			
			
			String[] ListNames = {"Unsorted", "Sorted", "Self-Adj (Front)",
			 "Self-Adj (By One)", "Skip List" };
			
//			 		PUNCTUATION
//			           !@#$%^&*()_+-=[]\{}|;':"`~,./<>?
			
			
			if (i == 0 || i == 1)
			{
				//code for 1st and 2nd pass
				//open file and make scanner
				Scanner sc = new Scanner(file);
				
				//go through file and read every word
				//for 1st pass, only read and close the file
				
				String currWord;
				
				if(i == 0)//code for 1st pass
				{
					while(sc.hasNext())
					{
						currWord = sc.next();
					}
					sc.close();
				}
				
				if(i == 1)//code for 2nd pass
				{
					double startTime = System.currentTimeMillis();
					//read all words, and remove the punctuation
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						//method to remove all punctuation
						//trim leading and trailing punctuation.
						currWord = removePunc(currWord);
						
						//if string consists of no letters, discard word
						//and go back for another
						
						//if it is a word, add to list1.
						
					}
					
					double overhead = startTime - System.currentTimeMillis();
				}
				
			}//after pass 1 and 2
			
			
			if(i > 1)// code for pass 3,4,5, 6, 7
			{
				if(i == 2)//code for 3rd pass, build list1
				{
					//read list, build list, 
					//output the info
					
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List1) Lists[0]).add(currWord);
						}
					}
					
					sc.close();
				}
				
				if(i == 3) // build list2
				{
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List2) Lists[1]).add(currWord);
						}
					}
					
					sc.close();
					
					
				}
				
				if(i == 4)//build list 2a
				{
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List2a) Lists[5]).add(currWord);
						}
					}
					
					sc.close();
				}
			
				if(i == 5) //build list3
				{
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List3) Lists[2]).add(currWord);
						}
					}
					
					sc.close();
					
				}
				
				if(i == 6)//build list 4
				{
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List4) Lists[3]).add(currWord);
						}
					}
					sc.close();
					
				}
				
				if(i == 7)
				{//build list 5
					Scanner sc = new Scanner(file);
					String currWord;
					
					while(sc.hasNext())
					{
						currWord = sc.next().toLowerCase();
						currWord = removePunc(currWord);
						//strip punctuation
						if(!(currWord.equals("")))
						{//if word is empty skip the word
							((List5) Lists[4]).add(currWord);
						}
					}
					sc.close();
					
				}
				
				//final output for the lists and their information
				
				//list 1
				if(i == 2)
				{
					System.out.printf("%2d ", 1);
					System.out.printf("%-17s ", ListNames[0]);
					System.out.printf("%8.3f    ", ((List1)Lists[0]).getElapsedTime());
					System.out.printf("%7d   ", ((List1)Lists[0]).getDistinctWords());
					System.out.printf("%9d   ", ((List1)Lists[0]).getTotalWords());
					System.out.printf("%11d  ", ((List1)Lists[0]).getKeyCompare());
					System.out.printf("%11d %n", ((List1)Lists[0]).getRefChanges());
					
				}
				
				//list 2
				if(i == 3)
				{
					System.out.printf("%2d ", 2);
					System.out.printf("%-17s ", ListNames[1]);
					System.out.printf("%8.3f    ", ((List2)Lists[1]).getElapsedTime());
					System.out.printf("%7d   ", ((List2)Lists[1]).getDistinctWords());
					System.out.printf("%9d   ", ((List2)Lists[1]).getTotalWords());
					System.out.printf("%11d  ", ((List2)Lists[1]).getKeyCompare());
					System.out.printf("%11d %n", ((List2)Lists[1]).getRefChanges());
				}
				
				//list 2a
				if(i == 4)
				{
					System.out.print("2a ");
					System.out.printf("%-17s ", ListNames[1].concat(" Modified"));
					System.out.printf("%8.3f    ", ((List2a)Lists[5]).getElapsedTime());
					System.out.printf("%7d   ", ((List2a)Lists[5]).getDistinctWords());
					System.out.printf("%9d   ", ((List2a)Lists[5]).getTotalWords());
					System.out.printf("%11d  ", ((List2a)Lists[5]).getKeyCompare());
					System.out.printf("%11d %n", ((List2a)Lists[5]).getRefChanges());
				}
				
				//list 3
				if(i == 5)
				{
					System.out.printf("%2d ", 3);
					System.out.printf("%-17s ", ListNames[2]);
					System.out.printf("%8.3f    ", ((List3)Lists[2]).getElapsedTime());
					n = Lists[2].getDistinctWords();
					System.out.printf("%7d   ", ((List3)Lists[2]).getDistinctWords());
					System.out.printf("%9d   ", ((List3)Lists[2]).getTotalWords());
					System.out.printf("%11d  ", ((List3)Lists[2]).getKeyCompare());
					System.out.printf("%11d %n", ((List3)Lists[2]).getRefChanges());
				}
				
				//list 4
				if(i == 6)
				{
					System.out.printf("%2d ", 4);
					System.out.printf("%-17s ", ListNames[3]);
					System.out.printf("%8.3f    ", ((List4)Lists[3]).getElapsedTime());
					System.out.printf("%7d   ", ((List4)Lists[3]).getDistinctWords() + 1);
					System.out.printf("%9d   ", ((List4)Lists[3]).getTotalWords());
					System.out.printf("%11d  ", ((List4)Lists[3]).getKeyCompare());
					System.out.printf("%11d \n", ((List4)Lists[3]).getRefChanges());
				}
				
				if(i == 7)//list 5
				{
					System.out.printf("%2d ", 5);
					System.out.printf("%-17s ", ListNames[4]);
					System.out.printf("%8.3f    ", ((List5)Lists[4]).getElapsedTime());
					System.out.printf("%7d   ", n);
					System.out.printf("%9d   ", ((List5)Lists[4]).getTotalWords());
					System.out.printf("%11d  ", ((List5)Lists[4]).getKeyCompare());
					System.out.printf("%11d   ", ((List5)Lists[4]).getRefChanges());
					System.out.printf("%2d ", ((List5)Lists[4]).getHeight());
				}
				
				
			}//end of pass 3,4,5,6,7
			
			
		}//end of 8 loops through program
		
		
		
	}//end of main

	
	
	static String removePunc(String word)
	{	
		String[] badChars = {"!", "@", "#", "$", "%", "^", "&",
				"*", "(", ")", "_", "+", "-", "=", "\\[",
				"]", "\\", "{", "}", "|", ";", "'", ":", "\"",
				"`" , "~", ",", ".", "/", "<", ">", "?"};
		//check if the word ends or begins with any of the bad chars
		//if it does, remove them
		
		char[] bc = {'!', '@', '#', '$', '%', '^', '&',
				'*', '(', ')', '_', '+', '-', '=', '[',
				']', '\\', '{', '}', '|', ';', '\'', ':', '"',
				'`' , '~', ',', '.', '/', '<', '>', '?'};
		
		for(int i = 0; i < badChars.length; i++)
		{
			
			if(word.equals(badChars[i]) || word.equals(""))
			{
				return "";
			}
			
			if(word.endsWith(badChars[i]))
			{
				//if the word ends with punctuation
				//remove the punctuation
				String theBadChar = badChars[i];
				word = word.replace(theBadChar, "");
			}
			if(word.startsWith(badChars[i]))
			{
				//if the word starts with punctuation
				//remove the punctuation
				word = word.substring(1);
			}
		}
		return word;
		
	}
}
