//implementation of linked list data structure
public class LLNode
{
	protected LLNode next;
	protected int count = 0;
	protected String word;
	
	LLNode(String word)
	{
		this.word = word;
		count++;
	}
	LLNode(String word, LLNode next)
	{
		this.word = word;
		this.next = next;
		count++;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public LLNode getNext()
	{
		return next;
	}
}