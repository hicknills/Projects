
public class SLNode 
{
	boolean isSentinel = false;
	SLNode left, right, up, down = null;
	String word;
	boolean pointsUp = false;
	
	
	SLNode(boolean bool)
	{//used to create sentinel nodes
		isSentinel = bool;
	}
	
	SLNode(String word)
	{
		this.word = word;
	}
}
