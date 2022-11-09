
public class List2 extends BaseList implements ListInterface
{ 
	/*
	 * A sorted (alphabetically) linked list. This list should have 
	 * shorter average search times, because once
you’ve determined where a word should be, if it’s not there
, there is no use in searching the rest of
the list (if you’re trying to add “absolute”, and you reach 
“absolve”, you know that “absolute” isn’t in
the list, and there’s no sense checking the remainder of the list. 
You do, however, have to create a
new node and link it in between the two existing nodes 
(or perhaps this is a new first node, or a new
last node – there will be multiple cases to consider for add)

	alphabetically sorted linked list.
	create a new node and link between the surrounding nodes
	 */
	double startTime = System.currentTimeMillis();
	
	LLNode head = null;//head of list
	LLNode tail = null;//tail of list
	LLNode curr = null;//current position in list
	LLNode prev, temp;
	
	List2()
	{
		super();
	}
	
	
	@Override
	public void add(String word) 
	{
		//adds word to the list
		//check if the word is on the list already
		//if it is, increment the count
		//if it is NOT, create a new node (which automatically increments the count)
		
		curr = head;
		
		if(head == null)//if the list is empty, make a new node
		{
			//make it the head of the list
			refChanges++;
			head = new LLNode(word);
			tail = head;
			refChanges++;
			return;//make head of list and go back for new word
		}
		
		if(head.word.equals(word))
		{//word is first in list
			head.count++;
			return;
		}
		
		if(tail == head)
		{//only one word
			if(word.compareTo(head.word) > 0)
			{//word goes after head
				head.next = new LLNode(word);
				refChanges++;
				tail = head.next;
				refChanges++;
				return;
			}
			else
			{//new word goes first
				temp = head;
				head = new LLNode(word, temp);
				refChanges++;
				tail = head.next;
				refChanges++;
				return;
				
			}
		}
		
		if(tail.word.equals(word))
		{//word is last in list
			tail.count++;
			return;
		}
		
		if(tail.word.compareTo(word) < 0)
		{//if word comes after tail
			tail.next = new LLNode(word);
			refChanges++;
			tail = tail.next;
			refChanges++;
			return;
		}
		
		curr = head;
		//search for the word
		while(curr != null)
		{
			if(curr.word.equals(word))
			{//word found
				curr.count++;
				return;
			}
				curr = curr.next;
		}//word wasnt found
		
		curr = head;
		while(curr.next.word.compareTo(word) < 0)
		{//while next node comes before word
			keyCompare++;
			curr = curr.next;
		}//word goes after curr
		temp = curr.next;
		curr.next = new LLNode(word, temp);
		refChanges++;
		return;
		
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
