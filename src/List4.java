public class List4 extends BaseList implements ListInterface
{
	/*
	 * light handed adjustment list. if word is found, it moves up
	 * one position
	 */
	double startTime = System.currentTimeMillis();
	
	LLNode head = null;
	LLNode tail = null;
	LLNode curr = null;
	
	List4()
	{
		super();
	}
	
	@Override
	public void add(String word) 
	{

		
		//add word to the list
		
		//if the word is in the list already
		//increment the count and move it forward one spot
		//if the word is not in the list
		//add it as the head
		
		curr = head;
		
		if(head == null)
		{//list is empty
			head = new LLNode(word);//make head the new word
			refChanges++;
			tail = head;
			refChanges++;
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
			tail = head;//make tail refer to old head
			refChanges++;
			head = newHead;//head now refers to the new node
			refChanges++;
			return;//go back for new word
		}
		
		
		if(head.next.word.equals(word))
		{//if 2nd word is the word
			//word switch the 2 nodes
			curr = head.next;//curr is 2nd node in list
			//if 2nd word is the tail
			
			
			if(curr.word.equals(tail.word))
			{
				//switch head and tail around
				curr.count++;//increment count
				tail.next = head;//tail points to head
				refChanges++;
				head.next = null;//head now points to nothing
				refChanges++;
				head = tail;//head refers to the new head
				refChanges++;
				return;
			}
			
			
			if(curr.next == tail)
			{//if 2nd word is the word, and the next word is the tail
				curr.count++;//increment count
				curr.next = head;//curr is new head
				refChanges++;
				head.next = tail;//head points to tail(middle spot)
				refChanges++;
				head = curr;//head now refers to new head
				refChanges++;
				return;
			}
			
			curr = head.next;//it
			curr.count++;///increment count
			//flip the 2 nodes
			head.next = curr.next;
			refChanges++;
			curr.next = head;
			refChanges++;
			head = curr;
			refChanges++;
			
			return;
			
		}
		
		if(tail.word.equals(word) && head.next != tail)
		{//word is last in the list
			curr = head;
			LLNode next = head.next;
			LLNode prev = null;
			while(next != null)
			{
				if(next.next == null)
				{//if word is the last in the list
					tail.count++;
					//next == tail here
					prev.next = next;
					refChanges++;
					next.next = curr;
					refChanges++;
					curr.next = null;
					refChanges++;
					tail = curr;
					refChanges++;
					return;
				}
				prev = curr;
				curr = next;
				next = next.next;
			}
			
		}
		
		
		//check for word in list
		
		LLNode prev2 = head;//head (2 behind curr)
		LLNode prev1 = head.next;//2nd word (1 behind curr)
		LLNode curr = head.next.next;//3rd word
		//search for word in list
		//if found, move word up one position
		int loopNum  = 0; //used to set pointers in traversal
		while(curr != null)
		{
			keyCompare++;
			
			if(word.equals(curr.word))
			{
				//starts at the 3rd word
				//prev2 = head
				//prev1 = 2nd word
				//curr = 3rd word
				curr.count++;//increment count
				prev1.next = curr.next;//prev2 behind points to curr
				refChanges++;
				curr.next = prev1;//1 behind points to curr.next
				refChanges++;
				prev2.next = curr;//curr points to 1 behind (moves curr up 1)
				refChanges++;
			
				
				/*if(head.word.equals(curr.word))
				{//if curr is the new head
					head = curr;
					refChanges++;
				}*/
				
				
				return;
			}
			
			else
			{
				prev2 = prev1;
				prev1 = curr;
				curr = curr.next;
			}		
			
			loopNum++;
			
		}
		
		//code here is for word is not in the list
		LLNode newHead = new LLNode(word, head);
		refChanges++;
		head = newHead;
		refChanges++;
		
		
	}
	
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
