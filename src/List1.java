/* 
 * head inserted list
 */

public class List1 extends BaseList //implements ListInterface
{
	LLNode head = null;//head of list
	LLNode tail = null;//tail of list
	LLNode curr = null;//used for traversing the list
	
	double startTime = System.currentTimeMillis();
	
	List1()
	{
		super();
	}

	public void add(String word) 
	{
		//adds word to the list
		//check if the word is on the list already
		//if it is, increment the count
		//if it is NOT, create a new node (which automatically increments the count)
		
		boolean wordFound = false;
		curr = head;//start at the head of the list for traversing
		
		if(head == null)//if the list is empty, make a new node
		{
			//make it the head of the list
			head = new LLNode(word);
			refChanges++;
			tail = head;
			refChanges++;
		}
		
		else
		{
			//if the list is not empty
			//check if the word is on the list
			
			//do while because if the list only contains one word, 
			//it would skip checking for the word
			
			do{//traverse through list
				keyCompare++;
				if(curr.word.toLowerCase().equals(word.toLowerCase()))
				{//if the word is in the list already, increment the count
					curr.count++;
					wordFound = true;
					break;
				}
				else //continue
				{
					curr = curr.next;
				}
			}while(curr != null && curr.next != null);//end of loop to traverse lists
			
			//if word was not found, create new node for word
			//and make it the head
			if(wordFound == false)
			{
				curr = head;//use curr to save the current head
				head = new LLNode(word);//make the word the new head
				refChanges++;
				head.next = curr;//make head.next the old head
				refChanges++;
			}
			
		}
		
	}//end of add
	
	
	public int getTotalWords()
	{
		list = head;
		return super.getTotalWords();
	}
	
	public int getDistinctWords()
	{
		list = head;
		return super.getDistinctWords();
	}
	
	public long getKeyCompare()
	{
		return super.getKeyCompare();
	}
	
	public long getRefChanges()
	{	
		return super.getRefChanges();
	}
	
	public double getElapsedTime()
	{
		return System.currentTimeMillis() - startTime;
	}
	
}
