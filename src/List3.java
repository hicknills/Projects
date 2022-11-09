
public class List3 extends BaseList implements ListInterface
{
	/*
	 * self adjusting list, when a word is found in the list
	 * that node is move to the head of the list. if word
	 * is not in list, it is made the head of the list.
	 * 
	 */
	
	
	double startTime = System.currentTimeMillis();
	
	LLNode head = null;
	LLNode tail = null;
	LLNode curr = null;
	
	List3()
	{
		super();
	}
	
	@Override
	public void add(String word) 
	{
		//add word to the list
		
		//if the word is in the list already
		//increment the count and make it the head
		//if the word is not in the list
		//add it as the head
		if(head == null)
		{
			head = new LLNode(word);
			refChanges++;
			tail = head;
			return;//go back for new word
		}
		
		if(head.word.equals(word))
		{
			head.count++;
			return;
		}
		
		if(head.next == null)//only one word in list
		{
			LLNode newHead = new LLNode(word, head);//make new node, points to head
			refChanges++;
			head = newHead;//head is now the new node
			refChanges++;
			return;//go back for new word
		}
		//check for word in list
		
		LLNode prev = null;
		curr = head;
		while(curr.next != null)
		{
			keyCompare++;
			if(word.equals(curr.word))
			{//if the word is in the list
				prev.next = curr.next;//disconnect the node
				refChanges++;
				curr.count++;//increment count
				curr.next = head;//make node point to head
				refChanges++;
				head = curr;//make head of list reference curr
				refChanges++;
				return;//go back for new word
			}
			//current word is not == word we are adding
			prev = curr;
			curr = curr.next;//continue through list
		}
		
		//code here is for word is not in the list
		LLNode newHead = new LLNode(word, head);
		refChanges++;
		head = newHead;
		refChanges++;
		
		
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
