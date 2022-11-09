
public class List2a extends BaseList implements ListInterface
{
	/*
	 * list 2a
	 * save the node you ended on from the last search
	 * look before or after that node. if your word comes
	 * before that saved node, start at the beginning of the list
	 * if your word comes after that node, start at that node.
	 * has find and add method
	 */
	LLNode head, curr, lastNode, temp, tail;
	double startTime = System.currentTimeMillis();
	
	List2a()
	{
		super();
	}
	
	@Override
	public void add(String word) 
	{
		curr = head;
		if(head == null)
		{//empty list
			refChanges++;
			head = new LLNode(word);
			lastNode = head;
			refChanges++;
			tail = head;
			refChanges++;
			return;
		}//list is not empty here
		
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
		{//if last word is what we are looking for
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
		
		//check if the node comes before/after the last node
		if(word.compareTo(lastNode.word) == 0)
		{//word is the same as the last word
			lastNode.count++;
			return;
		}
		keyCompare++;
		if(word.compareTo(lastNode.word) > 0)
		{//word comes after the last word
			//start searching at the last node.
			curr = lastNode.next;//we already checked this node
			
			while(curr != null)
			{
				if(curr.word.equals(word))
				{
					curr.count++;
					return;
				}
					curr = curr.next;
			}//word wasnt found
			
			curr = lastNode.next;
			
			while(word.compareTo(curr.word) > 0 && curr.next != null)
			{//keep going
				keyCompare++;
				if(curr.word.equals(word))
				{//we found the word
					curr.count++;
					return;
				}
				curr = curr.next;
			}//we need to add the word after curr
			temp = curr.next;//curr.next cannot be null because we checked for tail already
			curr.next = new LLNode(word, temp);
			refChanges++;
			return;//go for next word
		}
		if(word.compareTo(lastNode.word) < 0)
		{//word comes before the last word
			//start at beginning of list
			curr = head;
			
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
				if(curr.word.equals(word))
				{//we found the word
					curr.count++;
					return;
				}
				curr = curr.next;
			}//word goes after curr
			temp = curr.next;
			curr.next = new LLNode(word, temp);
			refChanges++;
			return; //go for next word
		}
		
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
