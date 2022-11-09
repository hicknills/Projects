import java.util.Random;
 
public class List5 extends BaseList implements ListInterface
{//skip list
	
	private SLNode head, tail = null;
	private int height;//#of lanes
	private Random r;
	private boolean found = false;
	private int count = 0;
	private int distinctWords = 0;
	double startTime = System.currentTimeMillis();
	List5()
	{
		super();
		head = new SLNode(true);//empty sentinel node true
		tail = new SLNode(true);//empty sentinel node true
		r = new Random();
		height = 1;//only one level
	}
	
	private SLNode search(String word)
	{//returns pointer to either the node containing the word
	//or the node before where the word should go
		found = false;
		SLNode curr = head.right;//start at 1st word of list
		while(true)
		{//loop until we return
			keyCompare++;
			while(curr.right.isSentinel != true &&
					curr.right.word.compareTo(word) <= 0)
			{	//while the next node is not a sentinel node
				// and until the next node contains the word
				curr = curr.right;//continue until we find the node
			}//end of the row, (sentinel is the next node)
			if(word.equals(curr.word))
			{//if the word is found
				found = true;
			}
			if(curr.down == null) return curr;//at lowest level
			curr = curr.down;//drop down and continue
		}
	}//end of search
	
	@Override
	public void add(String word) 
	{

		count++;//increment total num of words
		distinctWords++;
		if(count == 1)
		{//if first word on the list
			distinctWords++;
			head = new SLNode(true);//head is sent node
			refChanges++;
			head.right = new SLNode(word);
			tail = new SLNode(true);//tail is sent node
			refChanges++;
			head.right.right = tail;
			return;
		}
		
		SLNode curr = search(word);
		if(found)//if the word was found
		{
			distinctWords--;
			return;//go back for next word
		}//word was not found here
		
		//check if the word is the first word in the list
		if(word.equals(head.right.word))
		{
			return;
		}
		
		else
		{//word was not found
		 //add new node to the right of curr
			if(curr.right.isSentinel == true)
			{//if the node is right before the last node
				curr.right = new SLNode(word);
				curr.right.right = new SLNode(true);//make the new node point to sentinel again
				tail = curr.right.right;
				refChanges++;
				return;//go for next word
			}
			else
			{//new node is not the last in list
				//add node to the right of the current node
				SLNode temp = curr.right;//save the next node
				SLNode newNode = new SLNode(word);
				curr.right = newNode;//insert the new node
				newNode.left = curr;
				newNode.right = temp;
				temp.left = newNode;
				
				curr = curr.right;//go to the new node
				
				//flip a coin, tails do nothing
				int coin = r.nextInt(2);
				//coin is 0 or 1
				//head = 0, tails = 1
				if(coin == 1) return;//got tails go to next word\
				else
				{//got heads
					boolean sentinel = false;
					do {//do while coin is heads
						while(curr.pointsUp == false)
						{
							if(curr.left == null)
							{
								sentinel = true;//curr is sentinel node
								break;//got to the sentinel node
							}
							curr = curr.left;
						}
					
						if(sentinel || curr.isSentinel)//curr is sentinel
						{//make a new level
							SLNode leftSent = new SLNode(true);//make new sent nodes
							head = leftSent; refChanges++;
							SLNode rightSent = new SLNode(true);
							tail = rightSent; refChanges++;
							leftSent.down = curr; curr.up = leftSent;//make new nodes the top
							rightSent.down = tail; tail.up = rightSent;
							height++;//increment list height
							
							head.right = new SLNode(word);
							head.right.right = tail;
							break;
						}//we dont make an entirely new level
						curr = curr.up;//go up a level
						temp = curr.right;
						newNode = new SLNode(word);
						curr.right = newNode; newNode.left = curr;
						newNode.right = temp; temp.left = newNode;
						coin = r.nextInt(2);
					}while(coin == 0);//while coin is heads
				}//out of coins
			}//end of not last word else
			
		}//end of not found else
		
	}//end of add
	
	public int getTotalWords()
	{
		return count;
	}
	
	public int getDistinctWords()
	{
		//go down to the bottom level
		SLNode curr = head;
		while(curr.down != null)
		{
			curr = curr.down;
		}//curr is bottom level
		
		while (curr.right.isSentinel == false) 
		{
			count++; 
			curr = curr.right; 
		} 
		return count;
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
	
	public int getHeight()
	{
		return height;
	}
}