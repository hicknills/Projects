//exception to throw when the stack is empty and there is nothing to view/remove
public class StackUnderflowException extends RuntimeException 
{
	public StackUnderflowException()
	{
		super();
	}
	
	public StackUnderflowException(String message)
	{
		super(message);
	}
}