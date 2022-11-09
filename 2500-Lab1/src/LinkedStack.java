import support.LLObjectNode;

/*
 * basic stack class with 2 constructors
 * one to specify a size. contains
 * isEmpty, isFull, topIndex,
 * top, pop, push. each checks for it's 
 * corresponding exception.
 */


public class LinkedStack<T>
{
	protected LLObjectNode<T> top;
	
	public LinkedStack()
	{
		//make empty stack
		top = null;
	}
	
	public boolean isEmpty()
	{
		//return true if stack is completely empty, false if not
		if (top == null)
			return true;
		else 
			return false;
	}//end of isEmpty
	
	public void push(T element)
	{
		//create new node to hold element
		//make new node point to where top used to be
		//make top point to new node
		LLObjectNode<T> newNode = new LLObjectNode<T>(element);
		newNode.setLink(top);
		top = newNode;
		
	}//end of push
	
	public void pop() throws StackUnderflowException
	{
		//if stack empty throw stackUnderflowException
		//if stack is not empty, removes object from top of stack
		
		if(isEmpty())
		{
			throw new StackUnderflowException("Attempt to pop empty stack");
		}
		
		else
		{
			top = top.getLink();
		}
		
	}//end of pop
	
	public T topPop()
	{
		//method that performs top() and then pop().
		//used when you want to both get the value and remove it.
		
		T info = top();
		pop();
		return info;
	}//end of topPop
	
	public T top() throws StackUnderflowException
	{
		//if stack is empty throw exception
		if (isEmpty())
		{
			throw new StackUnderflowException("Top attempted on empty stack");
		}
		else
			//return the top element
		{
			return top.getInfo();
		}
	}//end of top
	
}
